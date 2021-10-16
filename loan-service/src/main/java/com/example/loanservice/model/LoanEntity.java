package com.example.loanservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LOAN")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nationalCode;

    @Column
    private String type;

    @Column
    private Long amount;

    @Column
    private Date requestDate;

}
