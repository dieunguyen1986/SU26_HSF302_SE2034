package edu.fu.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobRequest {
    private String title;
    private String description;
    private String location;
    // 9 999 999 999 999.99
    private Double minSalary;

    private Double maxSalary;
    private String utmSource;
    private String utmMedium;

    private LocalDate deadline;

    private Long departmentId;

    // Danh sách id skill được chọn trên form
    private List<Long> skillIds;
}
