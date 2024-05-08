package com.company.organizationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "organization")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    //instances variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orgId;
    @Column(nullable = false)
    private String organizationName;
    private String organizationDescription;
    @Column(nullable = false, unique = true)
    private String organizationCode;
    // whenever we create and save, hibernate will automatically add the value of this field.
    @CreationTimestamp
    private LocalDateTime organizationCreatedDate;

}
