package cn.nsu.ccl.teacher.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nsu.ccl.teacher.dao.impl.ExamDaoImpl;
import cn.nsu.ccl.teacher.dao.impl.StudentDaoImpl;
import cn.nsu.ccl.teacher.entity.ExamingInfoEntity;
import cn.nsu.ccl.teacher.entity.StudentInfoEntity;
import cn.nsu.ccl.teacher.service.ExamTakingInfoService;


/**
 * 
 * 描述：用于获取考试进行中的情况接口的实现类
 * 类名：ExamTakingInfoServiceImpl
 * 开发人员：暴沸
 * 联系方式：admin@baofeidyz.com
 * 创建时间：2016年8月30日 上午8:37:00
 */
@Service
public class ExamTakingInfoServiceImpl implements ExamTakingInfoService {
	
	@Autowired
	private ExamDaoImpl examDao;
	@Autowired
	private StudentDaoImpl studentDao;
	/**
	 * 获取学生信息（考试信息表中）By暴沸
	 */
	private ArrayList<StudentInfoEntity> getStudentInfo(int examId){
			List<Map<String, Object>> listMap = studentDao.getStudentInfo(examId);
			ArrayList<StudentInfoEntity> list = new ArrayList<StudentInfoEntity>();
			for (int i = 0; i < listMap.size(); i++) {
				Map<String, Object> map = listMap.get(i);
				StudentInfoEntity studentInfo = new StudentInfoEntity();
				studentInfo.setTeacherId(map.get("teacherUsername").toString());
				studentInfo.setStudentId(map.get("studentId").toString());
				studentInfo.setStudentName(map.get("studentName").toString());
				list.add(studentInfo);
			}
			return list;
	}
	
	/**
	 * 设置是否缺考信息，并且 获取考试状态信息-by暴沸
	 */
	public ArrayList<ExamingInfoEntity> getExamTokingInfo(int examId) {
		
		//用于保存新的考试信息
		ArrayList<ExamingInfoEntity> newExamTakingList = new ArrayList<>();
		
		ArrayList<ExamingInfoEntity> examTakingInfoList = examDao.getExamTakingInfo(examId);
		ArrayList<StudentInfoEntity> studentList = this.getStudentInfo(examId);
		if (!examTakingInfoList.isEmpty()&&!studentList.isEmpty()) {
			//将应参加的学生信息存入
			for(int i = 0; i < studentList.size(); i++){
				StudentInfoEntity studentInfo = studentList.get(i);
				ExamingInfoEntity examTakingInfo = new ExamingInfoEntity();
				examTakingInfo.setStudentNumber(studentInfo.getStudentId());
				examTakingInfo.setStudentName(studentInfo.getStudentName());
				examTakingInfo.setBrowserInfo("暂无");
				examTakingInfo.setIp("暂无");
				examTakingInfo.setIsLack("是");
				examTakingInfo.setState("未登录");
				newExamTakingList.add(examTakingInfo);
			}
			//将学生的相关信息存入
			for(int j = 0; j < examTakingInfoList.size();j++){
				ExamingInfoEntity examTakingInfo = examTakingInfoList.get(j);
				for(int x = 0; x < newExamTakingList.size();x++){
					ExamingInfoEntity newExamTakingInfo = newExamTakingList.get(x);
					if (newExamTakingInfo.getStudentNumber().equals(examTakingInfo.getStudentNumber())) {
						newExamTakingInfo.setBrowserInfo(examTakingInfo.getBrowserInfo());
						newExamTakingInfo.setIp(examTakingInfo.getIp());
						newExamTakingInfo.setNote(examTakingInfo.getNote());
						newExamTakingInfo.setState(examTakingInfo.getState());
						newExamTakingInfo.setIsLack("否");
					}
				}
			}
			return newExamTakingList;
		}
		return null;
	}
	
	/**
	 * 
	 * 描述：生成一个教师口令，为null则失败
	 * 方法名： createToken
	 * 类名：ExamTakingInfoServiceImpl
	 * 返回值类型：String
	 * 开发者：暴沸
	 * 联系方式：admin@baofeidyz.com
	 * 创建时间：2016年8月30日 下午8:17:48
	 * @param teacherId
	 * @return
	 */
	public String createToken(String teacherId,int examId) {
		String token = this.RandomString(6);
		//将token存入数据库
		if(examDao.updateToken(token, examId)==true)
			return token;
		return null;
	}
	
	/**
	 *  
	 * 描述：产生一个随机的字符串（不包括数字0和小写字母）
	 * 方法名： RandomString
	 * 类名：ExamTakingInfoServiceImpl
	 * 返回值类型：String
	 * 开发者：暴沸
	 * 联系方式：admin@baofeidyz.com
	 * 创建时间：2016年8月30日 下午8:04:34
	 * @param length
	 * @return
	 */
	private String RandomString(int length) {  
	    String str = "QWERTYUIPASDFGHJKLZXCVBNM123456789";  
	    Random random = new Random();  
	    StringBuffer buf = new StringBuffer();  
	    for (int i = 0; i < length; i++) {  
	        int num = random.nextInt(34);  
	        buf.append(str.charAt(num));
	    }  
	    return buf.toString();  
	}

	/**
	 * 存储备注信息
	 */
	public boolean setNote(String note, String studentNumber, int examId) {
		
		return examDao.updateNote(note, studentNumber, examId);
	} 

}
