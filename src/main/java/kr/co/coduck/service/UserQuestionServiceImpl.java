package kr.co.coduck.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.coduck.dao.UserDao;
import kr.co.coduck.dao.UserQuestionDao;
import kr.co.coduck.dto.UserQuestionDto;
import kr.co.coduck.form.userQuestionToAdm;
import kr.co.coduck.vo.AdQna;
import kr.co.coduck.vo.AdQnaFile;
import kr.co.coduck.vo.User;
@Service
public class UserQuestionServiceImpl implements UserQuestionService{

	// 파일을 저장할 경로 지정하기
	@Value("${user.question.img.directory}")
	private String imageDirectory;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserQuestionDao userQuestionDao;
	
	@Override
	public int getAllAdQnaCntByUserNo(int userNo){
		return userQuestionDao.getAllAdQnaCntByUserNo(userNo);
	}
	
	@Override
	public List<AdQnaFile> getAdQnaFilesByQnaNo(int qnaNo) {
		System.out.println("=========================서비스임플");
		System.out.println("파일들 : " +  userQuestionDao.getAdQnaFilesByQnaNo(qnaNo));
		return userQuestionDao.getAdQnaFilesByQnaNo(qnaNo);
	}
	
	@Override
	public List<AdQna> getAdQnasByCriteria(Map<String, Integer> criteria) {
		return userQuestionDao.getAdQnasByCriteria(criteria);
	}
	
	@Override
	public List<AdQna> getAdQnasByUserNo(int userNo) {
		return userQuestionDao.getAdQnasByUserNo(userNo);
	}
	
	@Override
	public AdQna getAdQnaByNo(int no) {
		return userQuestionDao.getAdQnaByNo(no);
	}
	
	@Override
	public void insertAdQnaFile(AdQnaFile file) {
		userQuestionDao.insertAdQnaFile(file);
	}
	
	//관리자에게 1:1질문
	@Override
	public int insertAdQna(userQuestionToAdm form, int userNo) throws IOException {
		AdQna adQna = new AdQna();
        adQna.setUserNo(userNo);
        adQna.setTitle(form.getTitle());
        adQna.setContent(form.getContent());
        userQuestionDao.insertAdQna(adQna);
        
        System.out.println("imageDirectory :"+ imageDirectory);
        System.out.println("form.getFiledatas() : " +form.getFiledatas());
        System.out.println("form.getFiledatas().size : " +form.getFiledatas().size());
        System.out.println("form.getFiledatas()[0] : " +form.getFiledatas().get(0));
        
        if(form.getFiledatas().size() != 0) {
	        for(MultipartFile image : form.getFiledatas()) {
	        	if (!image.isEmpty()) {
	        		String originalName = image.getOriginalFilename();
	        		
	        		System.out.println("===================================================");
	        		
	        		System.out.println("originName :"+ originalName);
	        		System.out.println("imageDirectory :"+ imageDirectory);
	        		
	        		//image.transferTo(new File(imageDirectory, originalName));
	        		FileCopyUtils.copy(image.getBytes(), new File(imageDirectory, originalName));
	        		
	        		AdQnaFile qnaFile = new AdQnaFile();
	        		qnaFile.setFileName(originalName);
	        		qnaFile.setAdQnaNo(adQna.getNo());
	        		userQuestionDao.insertAdQnaFile(qnaFile);
	        		
	        	}
	            
	        }
        } 
        return adQna.getNo();
	}
	
	@Override
	public List<UserQuestionDto> getUserQuestionListByUserNo(int userNo) {
		User user = userDao.getUserProfilByNo(userNo);
		List<UserQuestionDto> userQuestionList = userQuestionDao.getUserQuestionListByUserNo(user.getNo());
		return userQuestionList;
	}

	@Override
	public List<UserQuestionDto> searchQuestion(Map<String, Object> criteria) {
		return userQuestionDao.searchQuestion(criteria);
	}

}
