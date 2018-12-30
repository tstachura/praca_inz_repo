package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.repository.interfaces.CompanyRepository;
import com.stachura.praca_inz.backend.service.CompanyService;
import com.stachura.praca_inz.backend.service.DepartmentService;
import com.stachura.praca_inz.backend.web.dto.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.CompanyStructuresListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.CompanyStructureConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/secured/structure/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CompanyRepository companyRepository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<CompanyStructuresListElementDto> getAll() {
        return departmentService.getAllDepartments();
    }


    @RequestMapping(value = "/company/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<CompanyStructuresListElementDto> getAllDepartmentsForCompany(@PathVariable Long id) {
        return departmentService.getAllDepartmentsForCompany(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    CompanyStructureEditDto get(@PathVariable Long id) {
        return CompanyStructureConverter.toCompanyStructureEdit(departmentService.getDepartmentById(id));
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Department get(@RequestParam String name) {
        return departmentService.getDepartmentByName(name);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody CompanyStructureAddDto department) {
        departmentService.createNewDepartment(CompanyStructureConverter.toDepartment(department,companyRepository));
        HttpHeaders headers = new HttpHeaders();
//        ControllerLinkBuilder linkBuilder = linkTo(methodOn(DepartmentController.class).getOfficeById(department.getId()));
//        headers.setLocation(linkBuilder.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody CompanyStructureEditDto companyStructureEditDto) {
        Department beforeDepartment=departmentService.getDepartmentById(companyStructureEditDto.getId());
        departmentService.updateDepartment(CompanyStructureConverter.toDepartment(companyStructureEditDto,beforeDepartment,companyRepository));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
    }
}
