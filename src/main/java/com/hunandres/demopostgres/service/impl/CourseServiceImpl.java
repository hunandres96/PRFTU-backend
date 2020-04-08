package com.hunandres.demopostgres.service.impl;

import com.hunandres.demopostgres.dto.CourseDTO;
import com.hunandres.demopostgres.dto.CourseSearchRequest;
import com.hunandres.demopostgres.entity.Course;
import com.hunandres.demopostgres.repositories.CourseRepository;
import com.hunandres.demopostgres.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CourseDTO> search(CourseSearchRequest courseSearchRequest) {

        Pageable pageable = PageRequest.of(courseSearchRequest.getPageNo(), courseSearchRequest.getPageSize(), Sort.by(courseSearchRequest.getSortBy()));
        Page<Course> coursePage;

        if (courseSearchRequest.getDepartmentId() == null) {
            coursePage = courseRepository.findAll(pageable);
        } else {
            coursePage = courseRepository.findByDepartmentId(courseSearchRequest.getDepartmentId(), pageable);
        }

        List<CourseDTO> courseDTOS = new ArrayList<>();

        coursePage.stream().forEach(allCourses -> {
            courseDTOS.add(modelMapper.map(allCourses, CourseDTO.class));
        });

        return courseDTOS;

    }

    @Override
    public CourseDTO findCourseById(Integer id) {

        Optional<Course> result = courseRepository.findById(id);
        Course course;

        if (result.isPresent()) {
            course = result.get();
        } else {
            throw new RuntimeException("Course with id: " + id + " was not found");
        }

        CourseDTO courseDTO = modelMapper.map(course, CourseDTO.class);
        return courseDTO;
    }

    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {

        Course course = modelMapper.map(courseDTO, Course.class);
        course = courseRepository.save(course);
        return modelMapper.map(course, CourseDTO.class);

    }

    @Override
    public boolean deleteCourseById(Integer id) {

        Optional<Course> optionalCourse = courseRepository.findById(id);

        if (optionalCourse.isPresent()) {
            courseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }
}