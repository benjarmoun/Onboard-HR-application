package com.example.onboard.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "salary", nullable = false)
    private Long salary;

    @Basic
    @Column(name = "type", nullable = false)
    private String type;

    @Basic
    @Column(name = "fonction", nullable = false)
    private String fonction;

    @JsonIgnore
    @OneToOne(mappedBy = "contract")
    private Employee employee;

    @Basic
    @Column
    private Date startDate;

    @Basic
    @Column
    private Date endDate;

}
