package com.example.loanservice.repository;

import com.example.loanservice.model.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    public Optional<LoanEntity> findLoanById(Long id);

}
