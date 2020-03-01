package com.hunandres.demopostgres.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Integer id;
    private String student_name;
    private String student_email;

}