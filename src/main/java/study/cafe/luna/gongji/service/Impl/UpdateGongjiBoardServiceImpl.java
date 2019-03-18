package study.cafe.luna.gongji.service.Impl;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.cafe.luna.gongji.dao.AdminGongjiBoardDAO;
import study.cafe.luna.gongji.dao.Impl.AdminGongiBoardDAOImpl;
import study.cafe.luna.gongji.dto.AdminGongjiBoardDTO;
import study.cafe.luna.gongji.service.UpdateGongjiBoardService;

@Service
public class UpdateGongjiBoardServiceImpl implements UpdateGongjiBoardService {
	
	@Inject
	AdminGongjiBoardDAO adminGongjiBoardDAO;
	@Autowired
	AdminGongiBoardDAOImpl noticeBoardDAOImpl;
	
	
	@Override
	public void noticeupdate(AdminGongjiBoardDTO vo) throws Exception {
		adminGongjiBoardDAO.noticeupdate(vo);
		
	}
}
