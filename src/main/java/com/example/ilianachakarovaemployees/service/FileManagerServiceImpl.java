package com.example.ilianachakarovaemployees.service;

import com.example.ilianachakarovaemployees.model.EmployeeDTO;
import com.example.ilianachakarovaemployees.model.ResultDTO;
import com.example.ilianachakarovaemployees.util.CsvFileToDTOConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.util.List;

import java.util.stream.Stream;
@Service
@Log4j2
public class FileManagerServiceImpl implements FileManagerService{

    @Override
    public ResultDTO handleFile(MultipartFile file) {
        CsvFileToDTOConverter<EmployeeDTO> converter = new CsvFileToDTOConverter<>();
        try{
            InputStream inputStream = file.getInputStream();
            List<EmployeeDTO> converted = converter.convert(inputStream, EmployeeDTO.class);

            return findLongestCollaboration(converted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private ResultDTO findLongestCollaboration(List<EmployeeDTO> converted) {

        long longestCollaborationInDays = 0;
        EmployeeDTO nomenee1 = null;
        EmployeeDTO nomenee2 = null;
        long projectId = 0;
        for (int i = 0; i <= converted.size() - 1; i++) {
            EmployeeDTO employee1 = converted.get(i);
            for (int j = 0; j <= converted.size() - 1; j++) {
                EmployeeDTO employee2 = converted.get(j);
                if (employee1.getProjectId() == employee2.getProjectId() && employee1.getEmployeeId() != employee2.getEmployeeId()) {
                    LocalDate startCollaboration = employee1.getDateFrom().isAfter(employee2.getDateFrom()) ? employee1.getDateFrom() : employee2.getDateFrom();
                    LocalDate endCollaboration = employee1.getDateTo().isBefore(employee2.getDateTo()) ? employee1.getDateTo() : employee2.getDateTo();
                    long difference = endCollaboration.toEpochDay() - startCollaboration.toEpochDay();
                    if (difference > longestCollaborationInDays) {
                        longestCollaborationInDays = difference;
                        nomenee1 = employee1;
                        nomenee2 = employee2;
                        projectId = employee1.getProjectId();
                    }
                }
            }
        }
        return new ResultDTO(longestCollaborationInDays, nomenee1.getEmployeeId(), nomenee2.getEmployeeId(),projectId);
    }
}
