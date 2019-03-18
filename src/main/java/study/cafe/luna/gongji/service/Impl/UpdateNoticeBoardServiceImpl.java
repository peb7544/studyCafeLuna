package study.cafe.luna.gongji.service.Impl;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.cafe.luna.gongji.dao.AdminGongjiBoardDAO;
import study.cafe.luna.gongji.dao.Impl.AdminNoticeBoardDAOImpl;
import study.cafe.luna.gongji.dto.AdminGongjiBoardDTO;
import study.cafe.luna.gongji.service.UpdateNoticeBoardService;

@Service
public class UpdateNoticeBoardServiceImpl implements UpdateNoticeBoardService {
	
	@Inject
	AdminGongjiBoardDAO adminGongjiBoardDAO;
	@Autowired
	AdminNoticeBoardDAOImpl noticeBoardDAOImpl;
	
	
	@Override
	public void noticeupdate(AdminGongjiBoardDTO vo) throws Exception {
		adminGongjiBoardDAO.noticeupdate(vo);
		
	}
}
