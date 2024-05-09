package com.example.ilianachakarovaemployees.controller;

import com.example.ilianachakarovaemployees.model.EmployeeDTO;
import com.example.ilianachakarovaemployees.model.ResultDTO;
import com.example.ilianachakarovaemployees.service.FileManagerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.util.List;


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
    public String fileUpload(MultipartFile file, Model model) throws Exception {
        ResultDTO result =  fileManagerService.save(file);
        model.addAttribute("result",result);
//        model.addAttribute("employee1Id", result.getEmployee1Id().toString());
//        model.addAttribute("employee2Id", result.getEmployee2Id().toString());
//        model.addAttribute("projectId","3");
//        model.addAttribute("days", result.getLongestCollaborationInDays());
        return "employees";
    }
}
