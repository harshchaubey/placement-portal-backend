package com.placement.portal.controller;

import com.placement.portal.dto.JobRequestDTO;
import com.placement.portal.dto.JobResponseDTO;
import com.placement.portal.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;
    @PostMapping("/post/{companyId}")
    public JobResponseDTO postJob(@RequestBody JobRequestDTO dto, @PathVariable Long companyId){
         return jobService.postJob(dto,companyId);
    }
    @GetMapping
    public List<JobResponseDTO> getAllJobs(){
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobResponseDTO getJobById(@PathVariable Long id){
        return jobService.getJobById(id);
    }

    @GetMapping("/branch/{branch}")
    public List<JobResponseDTO> getJobsByBranch(@PathVariable String branch){
        return jobService.getJobsByBranch(branch);
    }



}
