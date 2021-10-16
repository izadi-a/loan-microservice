package com.example.loanservice.service;

import com.example.loanservice.model.LoanEntity;
import com.example.loanservice.repository.LoanRepository;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @Autowired
    private RateLimiterRegistry rateLimiterRegistry;

    @Autowired
    private RetryRegistry retryRegistry;

    @Transactional(readOnly = true)
    public Optional<LoanEntity> findById(Long id) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitBreaker.run(() -> loanRepository.findLoanById(id), throwable -> getDefaultAlbumList());

//        return loanRepository.findLoanById(id);
    }

    private Optional<LoanEntity> getDefaultAlbumList() {
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    public List<LoanEntity> findAll() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

        return circuitBreaker.run(() -> loanRepository.findAll());
//        return loanRepository.findAll();
    }

    @Transactional
    public LoanEntity save(LoanEntity loanEntity) {
        return loanRepository.save(loanEntity);
    }

    public Map<String, Number> check() {
        Map<String, Number> result = new HashMap<>();
        result.put("CircuitBreaker failureRateThreshold", circuitBreakerRegistry.getDefaultConfig().getFailureRateThreshold());
        result.put("RateLimiter limitForPeriod", rateLimiterRegistry.getDefaultConfig().getLimitForPeriod());
        result.put("Retry max retry", retryRegistry.getDefaultConfig().getMaxAttempts());
        return result;
    }
}
