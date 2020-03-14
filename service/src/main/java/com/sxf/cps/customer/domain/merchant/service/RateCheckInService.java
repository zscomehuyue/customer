package com.sxf.cps.customer.domain.merchant.service;

import com.sxf.cps.customer.domain.merchant.entity.RateCheckInEntity;
import com.sxf.cps.customer.domain.merchant.mapstruct.MerchantStruct;
import com.sxf.cps.customer.domain.merchant.repository.RateCheckInRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class RateCheckInService {
    @Resource
    private MerchantStruct merchantStruct;
    @Resource
    private RateCheckInRepository rateCheckInRepository;

    public void mergeRateCheckIn(RateCheckInEntity entity) {
        Optional<RateCheckInEntity> rate = rateCheckInRepository.findByFactSnAndFactId(entity.getFactSn(), entity.getFactId());
        rate.ifPresent(r -> {
            merchantStruct.updateRateCheckInEntity(entity, r);
            r.setModified(Timestamp.valueOf(LocalDateTime.now()));
            rateCheckInRepository.flush();
            log.info("=mergeRateCheckIn=>update entity:{}", entity);
        });
        rate.orElseGet(()->{
            entity.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            entity.setModified(Timestamp.valueOf(LocalDateTime.now()));
            rateCheckInRepository.save(entity);
            log.info("=mergeRateCheckIn=>save entity:{}", entity);
            return null;
        });
    }
}
