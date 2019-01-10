package cn.nsu.ccl.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理service
 * @author 墨盒
 */
@Service
public class StudentServiceManager {
	/* 考试科目组 */
	@Autowired
	private LoginService loginService;
	/* 考试页面组 */
	@Autowired
	private ExamService examService;
	/**
	 * <p>获取SubjectService</p>
	 * @return 获取SubjectService
	 */
	public LoginService getLoginService() {
		return loginService;
	}

	/**
	 * <p>获取ExamService</p>
	 * @return 获取ExamService
	 */
	public ExamService getExamService() {
		return examService;
	}

}
