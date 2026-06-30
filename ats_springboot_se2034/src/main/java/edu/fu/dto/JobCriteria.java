package edu.fu.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobCriteria {
    private String keyword;
    private String location;
    private Integer experience;
    private String jobType;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
}
