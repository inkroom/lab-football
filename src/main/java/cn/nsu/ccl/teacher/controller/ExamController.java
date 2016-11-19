package cn.nsu.ccl.teacher.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExamController {
	@RequestMapping(value="teacherDownloadExamDemo")
	public ResponseEntity<byte[]> downloadExamDemo(HttpSession session){
		System.out.println(session.getServletContext().getRealPath("/")+"WEB-INF/");
		System.out.println("downloadExamDemo");
		String path = session.getServletContext().getRealPath("/")+"WEB-INF/teacher/demo.xlsx";
		System.out.println(path);
		File file = new File(path);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentDispositionFormData("attachment", "student.xlsx");
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    try {
	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	    } catch (IOException e) {
	    	
	    }
	    return null;
	}
}
