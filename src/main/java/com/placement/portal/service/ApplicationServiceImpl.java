package com.placement.portal.service;

import com.placement.portal.dto.ApplicationRequestDTO;
import com.placement.portal.dto.ApplicationResponseDTO;
import com.placement.portal.entity.Application;
import com.placement.portal.entity.Job;
import com.placement.portal.entity.Student;
import com.placement.portal.exception.ResourceNotFoundException;
import com.placement.portal.repository.ApplicationRepository;
import com.placement.portal.repository.JobRepository;
import com.placement.portal.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final StudentRepository studentRepository;

    @Override
    public ApplicationResponseDTO applyForJob(ApplicationRequestDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Job job = jobRepository.findById(dto.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        if (student.getCgpa() < job.getMinCgpa()) {
            throw new ResourceNotFoundException("student Cgpa cannot be less than student's cgpa");
        }

        Application application = new Application();
        application.setStudent(student);
        application.setJob(job);
        application.setStatus("APPLIED");

        Application saved = applicationRepository.save(application);
        return mapToResponse(saved);
    }
    @Override
    public List<ApplicationResponseDTO> getApplicationsByStudent(Long studentId){
        return applicationRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ApplicationResponseDTO> getApplicationsByJob(Long jobId){
        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }



    private ApplicationResponseDTO mapToResponse(Application application){
          return new ApplicationResponseDTO(
                  application.getId(),
                  application.getStatus(),
                  application.getStudent().getId(),
                  application.getStudent().getName(),
                  application.getJob().getId(),
                  application.getJob().getTitle(),
                  application.getJob().getCompany().getCompanyName()
          );
    }

}
