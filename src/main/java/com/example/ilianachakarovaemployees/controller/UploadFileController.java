package com.example.ilianachakarovaemployees.controller;

import com.example.ilianachakarovaemployees.service.FileManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadFileController {

    private final FileManagerService fileManagerService;

    public UploadFileController (FileManagerService fileManagerService){
        this.fileManagerService = fileManagerService;
    }

    @GetMapping("/")
    public String home(){
        return "redirect:employees";
    }
    @GetMapping("/employees")
    public String fileUpload(){
        return "upload_form";
    }

    @PostMapping("/employees")
    public String fileUpload(MultipartFile file){
        //todo validate file
        fileManagerService.save(file);
        return "redirect:employees";
    }
}
