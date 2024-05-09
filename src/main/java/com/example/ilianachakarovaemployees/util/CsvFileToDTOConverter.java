package com.example.ilianachakarovaemployees.util;
import com.example.ilianachakarovaemployees.model.EmployeeDTO;
import io.micrometer.common.util.StringUtils;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvFileToDTOConverter <V> {
    private static final String BASE_ERROR_MSG = "Input data is incorrect\r\n";

    public List<V> convert(InputStream stream, Class<V> outputClass) throws Exception {
        List<V> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        String line = null;
        while ((line = reader.readLine()) != null) {
            if (outputClass != null) {
                result.add(fromCsvLineToDTO(line, outputClass));
            }
        }
        return result;
    }

    private <V extends Object> V fromCsvLineToDTO(String line, Class<V> type) {

        if (type.toString().equals(EmployeeDTO.class.toString())) {
            String[] props = line.split(",");
            validateLineInput(props);

            long employeeId = Long.parseLong(props[0]);
            long projectId = Long.parseLong(props[1]);
            LocalDate dateFrom = DateUtil.parseDate(props[2]);

            LocalDate dateTo = LocalDate.now();
            if (props.length == 4 && !StringUtils.isEmpty(props[3])) {
                dateTo = DateUtil.parseDate(props[3]);
            }

            EmployeeDTO employeeDTO = new EmployeeDTO(employeeId, projectId, dateFrom, dateTo);

            return (V) employeeDTO;
        }
        return null;
    }

    private void validateLineInput(String[] props) {
        StringBuilder message = new StringBuilder(BASE_ERROR_MSG);
        String employeeId = (props[0]);
        String projectId = (props[1]);
        String dateFrom = props[2];
        String dateTo = null;
        if (props.length == 4) {
            dateTo = props[3];
        }

        if(StringUtils.isEmpty(employeeId)){
            message.append("Missing employerID\r\n");
        }
        if(StringUtils.isEmpty(projectId)){
            message.append("Missing projectID\r\n");
        }
        if(StringUtils.isEmpty(dateFrom)){
            message.append("Missing project start date\r\n");
        }
        if(!dateFrom.matches(DateUtil.DATE_REGEX1) && !dateFrom.matches(DateUtil.DATE_REGEX2) && !dateFrom.matches(DateUtil.DATE_REGEX3) && !dateFrom.matches(DateUtil.DATE_REGEX4)){
            message.append("Incorrect format for start date\r\n");
        }
        if(!StringUtils.isEmpty(dateTo) && !dateTo.matches(DateUtil.DATE_REGEX1) && !dateTo.matches(DateUtil.DATE_REGEX2) && !dateTo.matches(DateUtil.DATE_REGEX3) && !dateTo.matches(DateUtil.DATE_REGEX4)){
            message.append("Incorrect format for end date\r\n");
        }

        if(!message.toString().equals(BASE_ERROR_MSG)){
            throw new RuntimeException(message.toString());
        }
    }
}
