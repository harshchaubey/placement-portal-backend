package com.placement.portal.service;

import com.placement.portal.dto.ApplicationRequestDTO;
import com.placement.portal.dto.ApplicationResponseDTO;
import com.placement.portal.entity.Application;

import java.util.List;

public interface ApplicationService {

    ApplicationResponseDTO applyForJob(ApplicationRequestDTO dto);
    List<ApplicationResponseDTO> getApplicationsByStudent(Long studentId);
    List<ApplicationResponseDTO> getApplicationsByJob(Long jobId);
   // ApplicationResponseDTO updateStatus(Long applicationId,String status);
}
