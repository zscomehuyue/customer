package com.sxf.cps.customer.domain.merchant.service;

import com.sxf.cps.customer.domain.merchant.entity.MerchantBrandEntity;
import com.sxf.cps.customer.domain.merchant.event.CreateMerchantBrandEvent;
import com.sxf.cps.customer.domain.merchant.mapstruct.MerchantStruct;
import com.sxf.cps.customer.domain.merchant.repository.MerchantBrandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Service
public class MerchantBrandService {

    @Resource
    private MerchantStruct merchantStruct;
    @Resource
    private MerchantBrandRepository merchantBrandRepository;

    public Optional<MerchantBrandEntity> getMerchantBrand(String factSn, String factId) {
        return merchantBrandRepository.findByFactSnAndFactId(factSn, factId);
    }

    public void mergeMerchantBrandEntity(CreateMerchantBrandEvent event, Supplier<String> afterMergeBrand) {
        Optional<MerchantBrandEntity> brand = merchantBrandRepository.findByFactSnAndFactId(event.getFactSn(), event.getFactId());
        brand.ifPresent(b -> {
            merchantStruct.updateMerchantBrandEntity(event, b);
            b.setModified(Timestamp.valueOf(LocalDateTime.now()));
            merchantBrandRepository.flush();
            log.info("=mergeMerchantBrandEntity=>update event:{}", event);
        });
        brand.orElseGet(() -> {
            MerchantBrandEntity entity = merchantStruct.toMerchantBrandEntity(event);
            entity.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            entity.setModified(Timestamp.valueOf(LocalDateTime.now()));
            merchantBrandRepository.save(entity);
            log.info("=mergeMerchantBrandEntity=>save event:{}", event);
            return null;
        });
        afterMergeBrand.get();

    }


}
