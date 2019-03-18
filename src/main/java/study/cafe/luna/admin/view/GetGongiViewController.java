package study.cafe.luna.admin.view;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import study.cafe.luna.member.dto.MemberDTO;
import study.cafe.luna.notice.service.NoticeBoardService;
import study.cafe.luna.notice.service.GetNoticeBoardService;

@Controller
public class GetGongiViewController {
	
	@Autowired
	GetNoticeBoardService getNoticeBoardService;
	
	//공지사항 상세보기
	@RequestMapping(value = "/gongjiview.do", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam int num, HttpSession session, MemberDTO memcom) throws Exception {

		memcom = (MemberDTO) session.getAttribute("member");
		ModelAndView mav = new ModelAndView();
		if (memcom.getPosition().equals("총관리자") | memcom.getPosition().equals("관리자")) {
			memcom = (MemberDTO) session.getAttribute("member");
			session.setAttribute("member", memcom);

			mav.setViewName("/admin/gongjiview");
			mav.addObject("num", getNoticeBoardService.noticeread(num));
			return mav;
		}
		mav.setViewName("/admin/cannotAccess");
		return mav;
	}
}
