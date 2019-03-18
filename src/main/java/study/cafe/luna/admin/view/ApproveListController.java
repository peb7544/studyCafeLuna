package study.cafe.luna.admin.view;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import study.cafe.luna.mailSending.service.AdminManagerApproveService;
import study.cafe.luna.member.dto.MemberDTO;
import study.cafe.luna.member.service.MemberService;
import study.cafe.luna.util.BoardPager;

@Controller
public class ApproveListController {
	@Inject
	AdminManagerApproveService adminManagerApproveService;
	@Autowired
	MemberService memberService;
	
	
	//지점장 승인 목록
		@RequestMapping(value = "/approveList.do", method=RequestMethod.GET)
		public @ResponseBody JSONObject approveListView(@RequestParam(defaultValue="1") int curPage,
				MemberDTO memberCommand, HttpSession session){
			//페이징 처리
			int count = memberService.countApprove(memberCommand.getId());
			   			
			int page_scale = 10; // 페이지당 게시물 수
			int block_sclae = 5; // 화면당 페이지 수
			// 페이지 나누기처리 
			BoardPager boardPager = new BoardPager(count, curPage,page_scale,block_sclae);

			int start = boardPager.getPageBegin();
			int end = boardPager.getPageEnd();
			   	
			List<MemberDTO> approveList = memberService.approveList(start, end, session);
			
			JSONObject obj = new JSONObject();

		   	obj.put("approveList", approveList);
		   	obj.put("approvePage", boardPager);
		   	return obj;
		 }
}
