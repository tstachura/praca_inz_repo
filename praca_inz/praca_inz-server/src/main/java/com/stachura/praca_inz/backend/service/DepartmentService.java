package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;

import java.util.List;

public interface DepartmentService {
    Department getDepartmentById(Long id);

    Department getDepartmentByName(String name);

    List<CompanyStructuresListElementDto> getAllDepartments(String username);

    List<CompanyStructuresListElementDto> getAllDepartmentsForCompany(Long id);

    void createNewDepartment(CompanyStructureAddDto companyStructureAddDto) throws ServiceException;

    void updateDepartment(CompanyStructureEditDto companyStructureEditDto) throws ServiceException;

    void deleteDepartmentById(Long id);

    void deleteDepartment(Department department);
}
