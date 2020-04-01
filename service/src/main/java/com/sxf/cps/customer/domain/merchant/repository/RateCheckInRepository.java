package com.sxf.cps.customer.domain.merchant.repository;

import com.sxf.cps.customer.domain.merchant.entity.RateCheckInEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateCheckInRepository extends JpaRepository<RateCheckInEntity,String> {
    Optional<RateCheckInEntity> findByFactSnAndFactId(String factSn, String factId);
}
