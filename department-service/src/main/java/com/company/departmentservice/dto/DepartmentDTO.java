package com.company.departmentservice.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        name = "DepartmentDTO",
        description = "DepartmentDTO Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    private Long deptId;
    @Schema(
            description = "Department Name"
    )
    private String departmentName;
    @Schema(
            description = "Department Description"
    )
    private String departmentDescription;
    @Schema(
            description = "Department Code"
    )
    private String departmentCode;
}
