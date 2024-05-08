package com.company.organizationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(
        name = "OrganizationDTO",
        description = "Organization Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDTO {

    private Long orgId;
    @Schema(description = "Organization Name")
    private String organizationName;
    @Schema(description = "Organization Description")
    private String organizationDescription;
    @Schema(description = "Organization Code")
    private String organizationCode;
    @Schema(description = "Organization Created Date")
    private LocalDateTime organizationCreatedDate;
}
