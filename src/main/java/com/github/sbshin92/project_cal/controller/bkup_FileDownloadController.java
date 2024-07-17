//package com.github.sbshin92.project_cal.controller;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import jakarta.servlet.http.HttpServletResponse;
//
//@Controller
//@RequestMapping({"/fileDownloadList","/fileDownload/{file}"})
//public class FileDownloadController {
//
//	// 파일 목록 보여주기
//	
//	public ModelAndView fileDownloadList(ModelAndView mv) {
//		File path = new File("/c:/uploads/");
//		String[] fileList = path.list();
//		
//		mv.addObject("fileList" + fileList);
//		mv.setViewName("uploads/fileDownloadListView");
//		
//		return mv;
//	}
//	
//	// 파일 다운로드
//	
//	public void fileDownload(@PathVariable String file, HttpServletResponse response) throws IOException {
//		File f = new File("/c:/uploads/", file);
//		// 파일명 인코딩
//		String encodedFileName = new String(file.getBytes("UTF-8"), "ISO-8859-1");
//		
//		// file 다운로드 설정
//		response.setContentType("application/download");
//		response.setContentLength((int)f.length());
//		response.setHeader("Content-Disposition","attatchment;filename=\"" +  encodedFileName + "\"");
//		//다운로드 시 저장되는 이름은 response Header의 "Content-Disposition"에 명시
//		
//		OutputStream os = response.getOutputStream();
//		
//		FileInputStream fis = new FileInputStream(f);
//		FileCopyUtils.copy(fis, os);
//		
//		fis.close();
//		os.close();
//		
//	}
//}



