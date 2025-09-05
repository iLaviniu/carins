package com.example.carins.scheduler;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.carins.model.InsurancePolicy;
import com.example.carins.repo.InsurancePolicyRepository;

@Component
public class PolicyExpiryLogger {

    private final InsurancePolicyRepository policyRepository;
    private static final Logger logger = LoggerFactory.getLogger(PolicyExpiryLogger.class);

    public PolicyExpiryLogger(InsurancePolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    // Run every 15 minutes
    @Scheduled(fixedRate = 15 * 60 * 1000)
    @Transactional
    public void logExpiredPolicies() {
        LocalDate now = LocalDate.now();

        // Policies that expired yesterday or today but not yet logged
        List<InsurancePolicy> policies = policyRepository.findExpiredNotLogged(now.minusDays(1), now);

        for (InsurancePolicy p : policies) {
            logger.info("Policy {} for car {} expired on {}", p.getId(), p.getCar().getId(), p.getEndDate());
            p.setExpiryLogged(true); // mark as logged
            policyRepository.save(p);
        }
    }
}