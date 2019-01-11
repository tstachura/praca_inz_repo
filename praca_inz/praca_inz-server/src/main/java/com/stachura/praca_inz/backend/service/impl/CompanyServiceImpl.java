package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Address;
import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.repository.AddressRepository;
import com.stachura.praca_inz.backend.repository.CompanyRepository;
import com.stachura.praca_inz.backend.service.CompanyService;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.CompanyStructureConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('COMPANY_READ')")
    public Company getCompanyById(Long id) throws ServiceException {
        Company company = companyRepository.findById(id).orElseThrow(() -> new ServiceException());
        if (company.isDeleted()) {
            return null;
        }
        return company;
    }


    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('COMPANY_LIST_READ')")
    public List<CompanyStructuresListElementDto> getAllCompanies() {
        List<Company> companies = (List<Company>) companyRepository.findAll();
        List<CompanyStructuresListElementDto> companiesDto = new ArrayList<>();
        for (Company a : companies) {
            if (!a.isDeleted()) {
                companiesDto.add(CompanyStructureConverter.toCompanyStructureListElement(a));
            }
        }
        return companiesDto;
    }


    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_CREATE')")
    public Long createNewCompany(CompanyStructureAddDto companyStructureAddDto) {

        Address address = new Address();
        address.setCity(companyStructureAddDto.getCity());
        address.setStreet(companyStructureAddDto.getStreet());
        address.setBuildingNumber(companyStructureAddDto.getBuildingNumber());
        address.setFlatNumber(companyStructureAddDto.getFlatNumber());
        address.setDeleted(false);
        Company company = new Company();
        company.setDeleted(false);
        company.setName(companyStructureAddDto.getName());
        company.setDescription(companyStructureAddDto.getDescription());
        addressRepository.save(address);
        company.setAddress(address);
        companyRepository.save(company);
        return company.getId();

    }


    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_UPDATE')")
    public void updateCompany(CompanyStructureEditDto companyStructureEditDto) throws ServiceException {
        Company beforeCompany = companyRepository.findById(companyStructureEditDto.getId()).orElseThrow(() -> new ServiceException());
        companyRepository.save(CompanyStructureConverter.toCompany(companyStructureEditDto, beforeCompany));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void deleteCompanyById(Long id) throws ServiceException {
        companyRepository.findById(id).orElseThrow(() -> new ServiceException()).setDeleted(true);
    }

}
