package com.example.ilianachakarovaemployees.util;
import com.example.ilianachakarovaemployees.model.EmployeeDTO;
import io.micrometer.common.util.StringUtils;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvFileToDTOConverter <V> {

    private static final String PATTERN_1 = "yyyy-MM-dd";
    private static final String BASE_ERROR_MSG = "Input data is incorrect";
    private static final String REGEX_PATTERN_1 = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(PATTERN_1);

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
            LocalDate dateFrom = LocalDate.parse(props[2], formatter1);
            LocalDate dateTo = LocalDate.now();
            if (props.length == 4 && !StringUtils.isEmpty(props[3])) {
                dateTo = LocalDate.parse(props[3], formatter1);
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
            message.append("Missing employerID /n/r");
        }
        if(StringUtils.isEmpty(projectId)){
            message.append("Missing projectID /n/r");
        }
        if(StringUtils.isEmpty(dateFrom)){
            message.append("Missing project start date /n/r");
        }
        if(!dateFrom.matches(REGEX_PATTERN_1)){
            message.append("Incorrect format for start date /n/r");
        }
        if(!StringUtils.isEmpty(dateTo) && !dateTo.matches(REGEX_PATTERN_1)){
            message.append("Incorrect format for end date /n/r");
        }

        if(!message.toString().equals(BASE_ERROR_MSG)){
            throw new RuntimeException(message.toString());
        }
    }
}
