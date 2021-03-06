package study.cafe.luna.qna.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import study.cafe.luna.qna.dto.QnABoardDTO;

public interface QnABoardService {
	public List<QnABoardDTO> qnaAll(int start, int end, HttpSession session); //질문목록
	public int countQnA(String subject); //질문 갯수
	
    public void insert(QnABoardDTO vo) throws Exception;

    public QnABoardDTO read(int num) throws Exception;

    public void update(QnABoardDTO vo) throws Exception;

    public void delete(int num) throws Exception;

    /*user_inform*/
    public QnABoardDTO qnaRead(int num) throws Exception; //질문 상세보기
}