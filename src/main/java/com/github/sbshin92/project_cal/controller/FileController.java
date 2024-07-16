package com.github.sbshin92.project_cal.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.sbshin92.project_cal.service.FileService;

@Controller
@RequestMapping("/fileupload")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping({"", "/form"})
    public String form() {
        return "fileupload/form";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model model) {
        try {
            String fileName = fileService.saveFile(file);
            model.addAttribute("message", "파일 업로드 성공: " + fileName);
            model.addAttribute("imageFilename", fileName);
        } catch (IOException e) {
            model.addAttribute("message", "파일 업로드 실패: " + e.getMessage());
        }
        return "fileupload/result";
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            Resource resource = fileService.loadAsResource(filename);
            return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/board/write")
    public String showUploadForm() {
        return "board/writeForm";
    }

    @PostMapping("/board/write")
    public String handleFileUpload(@RequestParam("title") String title,
                                   @RequestParam("content") String content,
                                   @RequestParam("file") MultipartFile file,
                                   Model model) {
        try {
            String fileName = fileService.saveFile(file);
            // TODO: 여기에 게시글 저장 로직 추가 (title, content, 파일 경로 등)
            model.addAttribute("message", "게시글 작성 및 파일 업로드 성공");
        } catch (IOException e) {
            model.addAttribute("message", "파일 업로드 실패: " + e.getMessage());
        }
        return "board/result";
    }
}