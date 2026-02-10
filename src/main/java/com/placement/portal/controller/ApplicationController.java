package com.placement.portal.controller;

import com.placement.portal.dto.ApplicationRequestDTO;
import com.placement.portal.dto.ApplicationResponseDTO;
import com.placement.portal.entity.Application;
import com.placement.portal.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/apply")
    public ApplicationResponseDTO applyForJob(@Valid @RequestBody ApplicationRequestDTO  dto){
        return applicationService.applyForJob(dto);
    }
    @GetMapping("/job/{jobId}")
    public List<ApplicationResponseDTO> getApplicationsByJobId(@PathVariable Long jobId){
        return applicationService.getApplicationsByJob(jobId);
    }

    @GetMapping("/student/{studentId}")
    public List<ApplicationResponseDTO> getApplicationsByStudentId(@PathVariable Long studentId){
        return applicationService.getApplicationsByStudent(studentId);
    }

   // @GetMapping("/status/{applicationId}")
   // public Application getApplicationStatus(@PathVariable Long applicationId ,
    //                                        @RequestParam String status){
    //    return applicationService.updateStatus(applicationId,status);
    //}
}
