package com.hunandres.demopostgres.service;

import com.hunandres.demopostgres.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> findAll(Integer pageNo, Integer pageSize, String sortBy);
    StudentDTO findStudentById(Integer id);
    StudentDTO saveStudent(StudentDTO studentDTO);
    void deleteStudentById(Integer id);

}
