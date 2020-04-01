package com.sxf.cps.customer.domain.merchant.repository;

import com.sxf.cps.customer.domain.merchant.entity.MerchantEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Repository
@RestController
@RequestMapping("test/merchant")
public interface MerchantRepository extends JpaRepository<MerchantEntity, String> {
    @EntityGraph(value = "MerchantEntity.merchantBrandEntityList")
    Optional<MerchantEntity> findByMerchantCode(String merchantCode);

    @EntityGraph(value = "MerchantEntity.merchantBrandEntityList")
    List<MerchantEntity> findAll();

    @GetMapping("getOne/{id}")
    @EntityGraph(value = "MerchantEntity.merchantBrandEntityList")
    MerchantEntity getOne(@PathVariable String id);
}
