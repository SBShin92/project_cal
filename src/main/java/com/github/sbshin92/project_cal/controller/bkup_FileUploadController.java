//package com.github.sbshin92.project_cal.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.UUID;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//@Controller
//@RequestMapping("/fileUpload")
//public class FileUploadController {
//
//	public String fileUploadFormView() {
//		return "upload/fileUploadForm";
//	}
//	
//	public String fileUploadView(@RequestParam("uploadFileMulti") ArrayList<MultipartFile> files,
//			Model model) throws IOException{
//		// 파일 저장 경로 설정 
//		String uploadPath = "/c:/uploads/";
//		
//		// 여러 개의 파일 이름 저장할 리스트 생성
//		ArrayList<String> originalFileNameList = new ArrayList<String>();
//		
//		for (MultipartFile file : files) {
//			// 원본 파일 이름 알아오기
//			String originalFileName = file.getOriginalFilename();
//			// 파일 이름을 리스트에 추가
//			originalFileNameList.add(originalFileName);
//			
//			// 파일이름 중복되지 않게 이름 변경 : 서버에 저장할 이름 /UUID클래스사용
//			UUID uuid = UUID.randomUUID();
//			String saveFileName = uuid.toString() + "_" + originalFileName;
//			
//			// 파일 생성
//			File newFile = new File(uploadPath + saveFileName);
//			
//			// 서버로 전송
//			file.transferTo(newFile);
//			
//			// 뷰 페이지에서 원본 파일 이름 출력
//			model.addAttribute("originalFileNameList", originalFileNameList);
//		}
//		return "upload/fileUploadResult";
//		
//	}
//	
//}




