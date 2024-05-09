package com.example.ilianachakarovaemployees.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeDTO {

    private long employeeId;

    private long projectId;

    private LocalDate dateFrom;

    private LocalDate dateTo;

}
