package com.example.ilianachakarovaemployees.service;
import com.example.ilianachakarovaemployees.model.ResultDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FileManagerService {

    ResultDTO handleFile(MultipartFile file) throws Exception;

}
