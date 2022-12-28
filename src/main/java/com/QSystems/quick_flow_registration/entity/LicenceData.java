package com.QSystems.quick_flow_registration.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "licenses")
public class LicenceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "param_name")
    private String paramName;

    @Column(name = "value")
    private String value;

}
