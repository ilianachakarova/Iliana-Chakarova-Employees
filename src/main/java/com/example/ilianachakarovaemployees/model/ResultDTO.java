package com.example.ilianachakarovaemployees.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class ResultDTO {
    private Long longestCollaborationInDays;
    private Long employee1Id;
    private Long employee2Id;
    private Long projectId;
}
