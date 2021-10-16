package com.example.loanservice.controller;

import com.example.loanservice.model.LoanEntity;
import com.example.loanservice.service.LoanService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private static final String LOAN_SERVICE = "loanService";

    @Autowired
    LoanService loanService;

    @GetMapping("/{id}")
    public ResponseEntity<LoanEntity> getLoan(@PathVariable Long id){
        return ResponseEntity.of(loanService.findById(id));
    }

    @GetMapping("/entities")
    public List<LoanEntity> findAll() {
        return loanService.findAll();
    }

    public ResponseEntity<String> loanFallBack(Long id, Exception e){
        return new ResponseEntity<>("Loan Service is down", HttpStatus.OK);
    }

    @GetMapping("/check")
    public Map<String, Number> check(){
        return loanService.check();
    }

    @PostMapping("/")
    public LoanEntity Save(@RequestBody LoanEntity loanEntity){
        return loanService.save(loanEntity);
    }
}
