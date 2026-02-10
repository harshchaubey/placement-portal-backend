package com.placement.portal.service;

import com.placement.portal.dto.CompanyRequestDTO;
import com.placement.portal.dto.CompanyResponseDTO;

import java.util.List;

public interface CompanyService {

    CompanyResponseDTO registerCompany(CompanyRequestDTO dto);
    List<CompanyResponseDTO> getAllCompanies();
    CompanyResponseDTO getCompanyById(Long id);
    CompanyResponseDTO verifyCompany(Long id);
    List<CompanyResponseDTO> getPendingCompanies();
}
