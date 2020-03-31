package com.sxf.cps.customer.domain.merchant.repository;

import com.sxf.cps.customer.domain.merchant.entity.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
@RestController
@RequestMapping("test/merchant")
public interface MerchantRepository extends JpaRepository<MerchantEntity,Long> {
    Optional<MerchantEntity> findByMerchantCode(String merchantCode);

    @GetMapping("getOne/{id}")
    MerchantEntity getOne(@PathVariable Long id);
}
