package study.cafe.luna.admin.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import study.cafe.luna.Question.dto.QuestionBoardDTO;
import study.cafe.luna.Question.service.GetQuestionBoardListService;
import study.cafe.luna.util.BoardPager;

@Controller
public class InquireListController {
	@Autowired
	GetQuestionBoardListService service;

	@Autowired
	private JavaMailSender mailSender; 
	  
	//문의계시판
		@RequestMapping(value = "/inquireList.do", method=RequestMethod.GET)
		public @ResponseBody JSONObject  inquireList(@RequestParam(defaultValue="1") int curPage,
				QuestionBoardDTO qboardVO, HttpSession session,
										@RequestParam(value="searchOption",defaultValue = "title") String searchOption,
										@RequestParam(value="keyword",defaultValue = "") String keyword) {
			//페이징 처리
	    	int count = service.countInqure(searchOption, keyword);
	    			
	    	int page_scale = 10; // 페이지당 게시물 수 
	    	int block_sclae = 5; // 화면당 페이지 수
	    	// 페이지 나누기처리 
	    	BoardPager boardPager = new BoardPager(count, curPage, page_scale, block_sclae);

	    	int start = boardPager.getPageBegin();
	    	int end = boardPager.getPageEnd();
	    	
	    	
			List<QuestionBoardDTO> inquireList = service.inquireList(start, end, searchOption, keyword, session);
			
			JSONObject obj = new JSONObject();

	    	obj.put("inquireList", inquireList);
	    	obj.put("inquirePage", boardPager);
	    	return obj;
		}
	
}
