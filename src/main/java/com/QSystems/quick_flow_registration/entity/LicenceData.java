package com.QSystems.quick_flow_registration.entity;


import lombok.Data;


import javax.persistence.*;


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
