package cn.nsu.ccl.teacher.service;

import java.util.ArrayList;

import cn.nsu.ccl.teacher.entity.ExamingInfoEntity;

/**
 * 
 * 描述：用于获取考试进行中的情况的接口
 * 类名：ExamTakingInfoService
 * 开发人员：暴沸
 * 联系方式：admin@baofeidyz.com
 * 创建时间：2016年8月30日 上午8:35:32
 */
public interface ExamingInfoService {
	
	/**
	 * 
	 * 描述：获取考试状态信息
	 * 方法名： getExamTokingInfo
	 * 类名：ExamTakingInfoService
	 * 返回值类型：ArrayList<ExamTakingInfoEntity>
	 * 开发者：暴沸
	 * 联系方式：admin@baofeidyz.com
	 * 创建时间：2016年8月30日 下午7:52:31
	 * @return
	 */
	public ArrayList<ExamingInfoEntity> getExamTokingInfo(int examId);
	/**
	 * 
	 * 描述：生成并返回一个教师口令Token
	 * 方法名： createToken
	 * 类名：ExamTakingInfoService
	 * 返回值类型：String
	 * 开发者：暴沸
	 * 联系方式：admin@baofeidyz.com
	 * 创建时间：2016年8月30日 下午7:53:19
	 * @return
	 */
	public String createToken(String teacherId,int examId);
	
	/**
	 * 
	 * 描述：存储备注
	 * 方法名： setNote
	 * 类名：ExamTakingInfoService
	 * 返回值类型：boolean
	 * 开发者：暴沸
	 * 联系方式：admin@baofeidyz.com
	 * 创建时间：2016年9月4日 上午11:08:30
	 * @param note
	 * @param studentNumber
	 * @param examId
	 * @return
	 */
	public boolean setNote(String note,String studentNumber,int examId);
}
