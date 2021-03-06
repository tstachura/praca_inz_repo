package com.stachura.praca_inz.backend.web.dto.user;

import lombok.*;

import java.util.List;

/**
 * Obiekt przesyłany do widoku z informacjami o  użytkowniku z danym loginem
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserInfoDto {
    private Long id;
    private String username;
    private String position;
    private String company;
    private Long companyId;
    private String department;
    private Long departmentId;
    private String office;
    private Long officeId;
    private String name;
    private String surname;
    private String city;
    private String email;
    private String street;
    private String houseNumber;
    private String flatNumber;
    private List<String> roles;
    private String dateOfJoining;
    private String workplace;
    private String language;
}
