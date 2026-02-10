package com.placement.portal.service;

import com.placement.portal.dto.JobRequestDTO;
import com.placement.portal.dto.JobResponseDTO;
import com.placement.portal.entity.Company;
import com.placement.portal.entity.Job;
import com.placement.portal.exception.BadRequestException;
import com.placement.portal.exception.ResourceNotFoundException;
import com.placement.portal.repository.CompanyRepository;
import com.placement.portal.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

     private final JobRepository jobRepository;
     private final CompanyRepository companyRepository;

     @Override
    public JobResponseDTO postJob(JobRequestDTO dto, Long companyId ){

         Company company = companyRepository.findById(companyId)
                 .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

         if(!company.isVerified()){
             throw new BadRequestException("Company is not verified");
         }

         Job job  = new Job();
         job.setTitle(dto.getTitle());
         job.setDescription(dto.getDescription());
         job.setMinCgpa(dto.getMinCgpa());
         job.setEligibleBranch(dto.getEligibleBranch());
         job.setLastDate(dto.getLastDate());
         job.setCompany(company);

         Job savedJob = jobRepository.save(job);
         return mapToResponse(savedJob);
     }

     @Override
     public List<JobResponseDTO> getAllJobs(){
         return jobRepository.findAll()
                 .stream()
                 .map(this::mapToResponse)
                 .toList();
     }

     @Override
     public JobResponseDTO getJobById(Long id){
         Job job = jobRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

         return mapToResponse(job);
     }

     @Override
     public List<JobResponseDTO> getJobsByBranch(String branch){
         return jobRepository.findByEligibleBranch(branch)
                 .stream()
                 .map(this::mapToResponse)
                 .toList();}

         private JobResponseDTO mapToResponse(Job job) {
             return new JobResponseDTO(
                     job.getId(),
                     job.getTitle(),
                     job.getDescription(),
                     job.getMinCgpa(),
                     job.getEligibleBranch(),
                     job.getLastDate(),
                     job.getCompany().getId(),
                     job.getCompany().getCompanyName()
             );
         }
     }


