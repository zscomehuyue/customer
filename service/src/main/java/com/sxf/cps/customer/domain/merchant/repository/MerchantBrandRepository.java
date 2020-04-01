package com.sxf.cps.customer.domain.merchant.repository;

import com.sxf.cps.customer.domain.merchant.entity.MerchantBrandEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface MerchantBrandRepository extends JpaRepository<MerchantBrandEntity, Long> {
    Optional<MerchantBrandEntity> findByFactVoFactSnAndFactVoFactId(String factSn, String factId);

    @EntityGraph(value = "MerchantBrandEntity.rateCheckInLogEntityList")
    List<MerchantBrandEntity> findAll();

    @EntityGraph(value = "MerchantBrandEntity.rateCheckInLogEntityList")
    MerchantBrandEntity getOne(@PathVariable Long id);

}
