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
import cn.nsu.ccl.teacher.service.ExamingService;


/**
 * 
 * 描述：用于获取考试进行中的情况接口的实现类
 * 类名：ExamTakingInfoServiceImpl
 * 开发人员：暴沸
 * 联系方式：admin@baofeidyz.com
 * 创建时间：2016年8月30日 上午8:37:00
 */
@Service
public class ExamingServiceImpl implements ExamingService {
	
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
	 * 
	 * <p>覆盖的getExaming函数--获取不带考试缺考状态的考试状态信息</p>
	 * @param examid
	 * @return
	 * @see cn.nsu.ccl.teacher.service.ExamingService#getExaming(int)
	 */
	public ArrayList<ExamingInfoEntity> getExaming(int examid){
		List<Map<String, Object>> listMap = examDao.getExaming(examid);
		ArrayList<ExamingInfoEntity> list = new ArrayList<ExamingInfoEntity>();
		for (int i = 0; i < listMap.size(); i++) {
			ExamingInfoEntity examTakingInfo = new ExamingInfoEntity();
			Map<String, Object> map = listMap.get(i);
			examTakingInfo.setStudentNumber(map.get("studentId").toString());
			examTakingInfo.setStudentName(map.get("studentName").toString());
			examTakingInfo.setState(map.get("studentStatus").toString());
			examTakingInfo.setIp(map.get("ipAddr").toString());
			examTakingInfo.setNote(map.get("remark")+"");
			list.add(examTakingInfo);
		}
		return list;
	}
	
	/**
	 * 
	 * <p>setQuekao方法的描述</p>
	 * @Title: ExamingServiceImpl的setQuekao方法
	 * @Description: 获取带有缺考标记的考试状态信息
	 * @author 暴沸 baofeidyz@foxmail.com
	 * @date 2016年11月20日 下午7:10:27
	 * @param examId
	 * @return
	 */
	public ArrayList<ExamingInfoEntity> setQuekao(int examId) {
		//用于保存新的考试信息
		ArrayList<ExamingInfoEntity> newExamTakingList = new ArrayList<>();
		//根据考试id获取该堂考试的考试状态信息
		ArrayList<ExamingInfoEntity> examTakingInfoList = this.getExaming(examId);
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
		if(examDao.updateToken(token, examId)==true){
			return token;
		}
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
	 * 
	 * <p>实现在考试状态信息存储学生备注信息的功能</p>
	 * @param note
	 * @param studentNumber
	 * @param examId
	 * @return
	 * @see cn.nsu.ccl.teacher.service.ExamingService#setNote(java.lang.String, java.lang.String, int)
	 */
	public boolean setNote(String note, String studentNumber, int examId) {
		
		return examDao.updateNote(note, studentNumber, examId);
	} 

}
