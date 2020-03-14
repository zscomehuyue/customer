package com.sxf.cps.customer.domain.merchant.service;

import com.sxf.cps.customer.domain.merchant.entity.MerchantBrandEntity;
import com.sxf.cps.customer.domain.merchant.entity.MerchantEntity;
import com.sxf.cps.customer.domain.merchant.event.CreateMerchantEvent;
import com.sxf.cps.customer.domain.merchant.mapstruct.MerchantStruct;
import com.sxf.cps.customer.domain.merchant.repository.MerchantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Service
public class MerchantService {

    @Autowired
    private MerchantStruct merchantStruct;
    @Autowired
    private MerchantRepository merchantRepository;

    public void mergeMerchantEntity(CreateMerchantEvent event, Supplier<Optional<MerchantBrandEntity>> brandFun) {
        Optional<MerchantEntity> merchant = merchantRepository.findByMerchantCode(event.getMerchantCode());
        merchant.ifPresent(b -> {
            log.info("=mergeMerchantEntity=>update:{}", event);
            merchantStruct.updateMerchantEntity(event, b);
            b.setModified(Timestamp.valueOf(LocalDateTime.now()));
            merchantRepository.flush();
        });
        merchant.orElseGet(() -> {
            log.info("=mergeMerchantEntity=>save:{}", event);
            MerchantEntity entity = merchantStruct.toMerchantEntity(event);
            entity.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            entity.setModified(Timestamp.valueOf(LocalDateTime.now()));

            MerchantEntity save = merchantRepository.save(entity);
            Optional<MerchantBrandEntity> brand = brandFun.get();
            brand.ifPresent(bd -> {
                bd.setMerchantCode(event.getMerchantCode());
                bd.setMerchantId(save.getUuid());
                bd.setModified(Timestamp.valueOf(LocalDateTime.now()));
                merchantRepository.flush();
            });
            brand.orElseGet(() -> {
                log.error("=mergeMerchantEntity=>not register factSn:{} ,factId:{}", event.getFactSn(), event.getFactId());
                return null;
            });
            return null;
        });
    }


}
