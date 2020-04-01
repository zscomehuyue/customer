package com.sxf.cps.customer.domain.merchant.repository;

import com.sxf.cps.customer.domain.merchant.entity.MerchantBrandEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface MerchantBrandRepository extends JpaRepository<MerchantBrandEntity, String> {
    Optional<MerchantBrandEntity> findByFactVoFactSnAndFactVoFactId(String factSn, String factId);

    @Override
    @EntityGraph(value = "MerchantBrandEntity.rateCheckInLogEntityList")
    List<MerchantBrandEntity> findAll();

    @Override
    @EntityGraph(value = "MerchantBrandEntity.rateCheckInLogEntityList")
    MerchantBrandEntity getOne(@PathVariable String id);

    @Override
    @EntityGraph(value = "MerchantBrandEntity.rateCheckInLogEntityList")
    <S extends MerchantBrandEntity> Page<S> findAll(Example<S> example, Pageable pageable);

}
