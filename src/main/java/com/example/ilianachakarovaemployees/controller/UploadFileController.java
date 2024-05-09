package com.example.ilianachakarovaemployees.controller;

import com.example.ilianachakarovaemployees.model.ResultDTO;
import com.example.ilianachakarovaemployees.service.FileManagerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

@Controller
@Log4j2
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
    public String fileUpload(MultipartFile file, Model model) {
        try {
            ResultDTO result =  fileManagerService.save(file);
            model.addAttribute("result",result);
        } catch (Exception e) {
            model.addAttribute("message",e.getLocalizedMessage());
        }

        return "employees";
    }
}
