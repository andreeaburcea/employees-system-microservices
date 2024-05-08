package com.company.employeesservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//@Schema(
//        description="EmployeeDTO Model Information"
//)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long empId;
  //  @Schema(description="Employee First Name")
    private String firstName;
   // @Schema(description="Employee Last Name")
    private String lastName;
  //  @Schema(description="Employee Email")
    private String email;
   // @Schema(description="Employee Phone Number")
    private String phoneNumber;
   // @Schema(description="Employee Hire Date")
    private LocalDate hireDate;
   // @Schema(description="Employee's Department Code")
    private String departmentCode;
    private String deptId;
   // @Schema(description="Employee's Organization Code")
    private String organizationCode;
}
