package study.cafe.luna.user.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import study.cafe.luna.member.dto.MemberDTO;
import study.cafe.luna.notice.dto.NoticeReplyDTO;
import study.cafe.luna.notice.service.NoticeBoardService;
import study.cafe.luna.notice.service.NoticeReplyService;
import study.cafe.luna.util.BoardPager;

@Controller
public class ViewNoticeController {
	@Autowired
	NoticeBoardService noticeBoardService;
	@Autowired
	NoticeReplyService noticeReplyService;
	
	//공지사항_질문사항 상세보기
	@RequestMapping(value="/viewNotice.do", method=RequestMethod.GET)
	public ModelAndView viewNoticeView(int num, HttpSession session,MemberDTO memcom,HttpServletRequest request) throws Exception {
		
		//여서부터
		Map<String, ?> flashMap=RequestContextUtils.getInputFlashMap(request);
			if(flashMap!=null) {
				memcom.setId(flashMap.get("id").toString());
				session.setAttribute("member", memcom);
			}else {
				memcom=(MemberDTO)session.getAttribute("member");
			}
						
			session.setAttribute("member", memcom);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/viewNotice");
		mav.addObject("nbv", noticeBoardService.read(num));
		return mav;
	}
	
	//댓글 목록
	@RequestMapping(value="/nReplyList.do", method=RequestMethod.GET)
	public @ResponseBody JSONObject nReplyListView(@RequestParam(value="num") int num, @RequestParam(defaultValue="1") int curPage,
			MemberDTO memcom, NoticeReplyDTO nReplyVO,  HttpSession session) {
		
		nReplyVO.setNum(num);
		memcom=(MemberDTO)session.getAttribute("member");
		//페이징 처리
		int count = noticeReplyService.countnReply(num);
		int page_scale = 5; // 페이지당 게시물 수
		int block_sclae = 10; // 화면당 페이지 수
		// 페이지 나누기처리 
		BoardPager boardPager = new BoardPager(count, curPage,page_scale,block_sclae);

		int start = boardPager.getPageBegin();
		int end = boardPager.getPageEnd();
		
		List<NoticeReplyDTO> nReplyList = noticeReplyService.nReplyList(num, start, end, session);
		
		JSONObject obj = new JSONObject();
		if(memcom!=null) {
			obj.put("userid", memcom.getId());
		}
		obj.put("nReplyList", nReplyList);
		obj.put("nReplyPage", boardPager);
		return obj;
	}
	
	//댓글 입력
	@RequestMapping(value="/nReplyInsert.do", method=RequestMethod.POST)
	public @ResponseBody void nReplyInsert(@RequestParam(value="content") String content,@RequestParam(value="bnum") int num,NoticeReplyDTO nReplyVO, HttpSession session) {
		MemberDTO memcom= (MemberDTO) session.getAttribute("member");
		
		nReplyVO.setReplyer(memcom.getId());
		nReplyVO.setReplytext(content);
		nReplyVO.setNum(num);
		noticeReplyService.create(nReplyVO);
	}
	
	//댓글 수정
	@RequestMapping(value="/nReplyUpdate.do", method=RequestMethod.POST)
	public @ResponseBody void nReplyUpdate(NoticeReplyDTO nReplyVO,HttpSession session, @RequestParam(value="rno", defaultValue="1") int rno,
											@RequestParam(value="content") String content) {
		nReplyVO.setReplytext(content);
		nReplyVO.setRno(rno);
		noticeReplyService.nReplyUp(nReplyVO);
	}
	 
	//댓글 삭제
	@RequestMapping(value="/nReplyDelete.do", method=RequestMethod.POST)
	public @ResponseBody void nReplyDelete(NoticeReplyDTO nReplyVO,HttpSession session, @RequestParam(value="rno", defaultValue="1") int rno) {
		nReplyVO.setRno(rno);
		noticeReplyService.nReplyDe(nReplyVO);
	}
}
