package com.example.ilianachakarovaemployees.service;

import com.example.ilianachakarovaemployees.model.ResultDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface FileManagerService {
    public void init();

    public ResultDTO save(MultipartFile file) throws Exception;

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
}
