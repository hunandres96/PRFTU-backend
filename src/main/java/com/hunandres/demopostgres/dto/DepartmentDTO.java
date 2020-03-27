package com.hunandres.demopostgres.dto;

import com.hunandres.demopostgres.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Integer id;
    private String name;
    //private List<Course> courses;

}
