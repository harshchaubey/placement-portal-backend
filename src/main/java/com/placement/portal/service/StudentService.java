package com.placement.portal.service;

import com.placement.portal.dto.StudentRequestDTO;
import com.placement.portal.dto.StudentResponseDTO;
import com.placement.portal.entity.Student;

import java.util.List;

public interface StudentService {

    StudentResponseDTO addStudent(StudentRequestDTO dto);

    List<StudentResponseDTO> getAllStudents();
    StudentResponseDTO getStudentById(Long id);
    StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto);

    void deleteStudent(Long id);
}
