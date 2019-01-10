package cn.nsu.ccl.teacher.service.impl;

import cn.nsu.ccl.teacher.dao.impl.MarkDaoImpl;
import cn.nsu.ccl.teacher.entity.Answer;
import cn.nsu.ccl.teacher.entity.Mark;
import cn.nsu.ccl.teacher.service.MarkService;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class MarkServiceImpl implements MarkService{
	
	@Autowired
	private MarkDaoImpl markDao;
	
	
	private ArrayList<Mark> getMarkList(int examId){
		List<Map<String, Object>> listMap = markDao.getStudentGrade(examId);
		ArrayList<Mark> list = new ArrayList<>();
		for (int i = 0; i < listMap.size(); i++) {
			Mark mEntity = new Mark();
			Map<String, Object> map = (Map<String, Object>) listMap.get(i);
			mEntity.setExamName(map.get("examName").toString());
			mEntity.setStudentNumber(map.get("studentId").toString());
			mEntity.setStudentName(map.get("studentName").toString());
			mEntity.setMark((float) map.get("mark"));
			mEntity.setIp(map.get("ipAddr").toString());
			list.add(mEntity);
		}
		return list;
	}
	
	private List<Answer> getAnsers(int examId){
		List<Answer> list = markDao.getAnswer(examId);
		return list;
	}
	
	/**
	 * 
	 * <p>覆盖的downloadGrade函数
	 * 从数据库中获取考试成绩信息，并返回excel文件的文件路径
	 * </p>
	 * @param examId
	 * @param contextPath
	 * @param examName
	 * @return
	 * @see MarkService#downloadGrade(int, String, String)
	 */
	public String downloadGrade(int examId,String contextPath,String examName){

		//从数据库中获取到的该考试所有考生的成绩的结果集
		ArrayList<Mark> list = this.getMarkList(examId);


		String[] title = {"学号","姓名","成绩","ip"};

		//创建Excel工作簿
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		//创建一个工作表sheet
		XSSFSheet sheet = workbook.createSheet();
		//创建第一行
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = null;
		//插入第一行数据 学号,姓名，成绩，ip地址
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
		}
		//追加数据
		int j = 1;
		for (int i = 0; i < list.size(); i++) {
			Mark markEntity = list.get(i);
			XSSFRow nextrow = sheet.createRow(j);
			XSSFCell cell2 = nextrow.createCell(0);
			cell2.setCellValue(markEntity.getStudentNumber());
			cell2 = nextrow.createCell(1);
			cell2.setCellValue(markEntity.getStudentName() );
			cell2 = nextrow.createCell(2);
			cell2.setCellValue(markEntity.getMark() );
			cell2 = nextrow.createCell(3);
			cell2.setCellValue(markEntity.getIp() );
			j++;
		}
		//创建一个文件
		String path = contextPath+"/"+examName+".xlsx";
		File file = new File(path);
		//将Excel内容存盘
		FileOutputStream stream;
		try {
			file.createNewFile();
			stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}


	/**
	 *
	 * <p>覆盖的setStudentGrade函数
	 * 计算学生成绩，并将结果返回到数据库中
	 * </p>
	 * @param examId
	 * @return
	 * @see MarkService#setStudentGrade(int)
	 */
	public boolean setStudentGrade(int examId) {
		//从数据库中获取学生和答案的相关信息
		List<Answer> answerEntities = this.getAnsers(examId);
		//初始化返回值
		boolean index = false;
		//遍历这个集合
		for(int i = 0; i < answerEntities.size(); i++){
			Answer answerEntity = answerEntities.get(i);
			//当学生的考试状态为完成状态的情况下计算成绩
			if (answerEntity.getState()==2||answerEntity.getState()==1) {
				String answerStudent = answerEntity.getStudentAnswer();		//学生的答案
				String answerReal = answerEntity.getTrueAnswer();			//正确答案
				String score = answerEntity.getScore();						//分数
				String eachType = answerEntity.getEachType();				//获取类型
				//最后的成绩
				float grade=0;
				String []temp1=answerStudent.split("##");
				String []temp2=answerReal.split("##");
				String []eachTypes=eachType.split("##");
				String []scores=score.split(",");
				for (int j = 0; j < temp1.length; j++) {
					if (temp1[j].equals(temp2[j])&&eachTypes[j].equals("单选题")) {
						grade=grade+Float.parseFloat(scores[0]);
					}
					else if (temp1[j].equals(temp2[j])&&eachTypes[j].equals("多选题")) {
						grade=grade+Float.parseFloat(scores[1]);
					}
					else if (temp1[j].equals(temp2[j])&&eachTypes[j].equals("判断题")) {
						grade=grade+Float.parseFloat(scores[2]);
					}
				}
				index = markDao.setGrade(examId, answerEntity.getStudentNumber(), grade);
				}
			if (answerEntity.getState()==0) {
				index = markDao.setGrade(examId, answerEntity.getStudentNumber(), 0);
			}
		}
		return index;
	}

	/**
	 *
	 * <p>覆盖的getScoreExcel函数
	 * 此方法主要针对于在controller中调用，实现获取学生成绩EXCEL文件的路径
	 * </p>
	 * @param examId
	 * @param contextPath
	 * @param examName
	 * @return
	 * @see MarkService#getScoreExcel(int, String, String)
	 */
	public String getScoreExcel(int examId,String contextPath,String examName){
		//Excel文件存放路径
		String path = null;
		//判断是否已经下载成绩
		boolean index = false;
		//判断当前的examId是否已经生成了成绩表
		ArrayList<Integer> list = this.getExamId();
		for (int i = 0; i < list.size(); i++) {
			//如果已经生成了成绩表，则直接下载即可
			if (list.get(i)==examId) {
				path = this.downloadGrade(examId,contextPath,examName);
				index = true;
			}
		}
		if (!index) {
			//计算并存入学生成绩
			this.setStudentGrade(examId);
			//下载学生成绩表EXCEL
			path = this.downloadGrade(examId,contextPath,examName);
		}
		return path;
	}

	/**
	 *
	 * <p>覆盖的getExamId函数
	 * 从成绩表中获取考试id集合
	 * </p>
	 * @return
	 * @see MarkService#getExamId()
	 */
	public ArrayList<Integer> getExamId() {
		
		return markDao.getExamId();
		
	}


	
	
}
