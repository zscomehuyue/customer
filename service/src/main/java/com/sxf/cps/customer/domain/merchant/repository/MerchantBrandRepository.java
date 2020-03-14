package com.sxf.cps.customer.domain.merchant.repository;

import com.sxf.cps.customer.domain.merchant.entity.MerchantBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantBrandRepository extends JpaRepository<MerchantBrandEntity, String> {
    Optional<MerchantBrandEntity> findByFactSnAndFactId(String factSn, String factId);
}
