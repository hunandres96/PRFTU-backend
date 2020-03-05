package com.hunandres.demopostgres.service;

import com.hunandres.demopostgres.dto.StudentDTO;
import com.hunandres.demopostgres.dto.StudentSearchRequest;

import java.util.List;

public interface StudentService {

    List<StudentDTO> findAll(StudentSearchRequest studentSearchRequest);
    StudentDTO findStudentById(Integer id);

}
