package com.sxf.cps.customer.domain.merchant.repository;

import com.sxf.cps.customer.domain.merchant.entity.MerchantEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
@RestController
@RequestMapping("test/merchant")
public interface MerchantRepository extends JpaRepository<MerchantEntity, String> {
    @EntityGraph(value = "MerchantEntity.merchantBrandEntityList")
    Optional<MerchantEntity> findByMerchantCode(String merchantCode);


    @Override
    @EntityGraph(value = "MerchantEntity.merchantBrandEntityList")
    List<MerchantEntity> findAll();


    @Override
    @GetMapping("getOne/{id}")
    @EntityGraph(value = "MerchantEntity.merchantBrandEntityList")
    MerchantEntity getOne(@PathVariable String id);

    @Override
    @EntityGraph(value = "MerchantEntity.merchantBrandEntityList")
    <S extends MerchantEntity> Page<S> findAll(Example<S> example, Pageable pageable);


}
