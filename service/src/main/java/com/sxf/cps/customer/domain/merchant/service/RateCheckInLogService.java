package com.sxf.cps.customer.domain.merchant.service;

import com.sxf.cps.customer.domain.merchant.entity.RateCheckInLogEntity;
import com.sxf.cps.customer.domain.merchant.mapstruct.MerchantStruct;
import com.sxf.cps.customer.domain.merchant.repository.RateCheckInLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Service
public class RateCheckInLogService {
    @Autowired
    private MerchantStruct merchantStruct;
    @Autowired
    private RateCheckInLogRepository rateCheckInLogRepository;


    public void saveRateCheckInLogEntity(RateCheckInLogEntity entity){
        entity.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        entity.setModified(Timestamp.valueOf(LocalDateTime.now()));
        rateCheckInLogRepository.save(entity);
    }
}
