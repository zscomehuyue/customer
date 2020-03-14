package com.sxf.cps.customer.application.service;

import com.sxf.cps.customer.DefaultTransaction;
import com.sxf.cps.customer.domain.merchant.event.CreateMerchantBrandEvent;
import com.sxf.cps.customer.domain.merchant.mapstruct.MerchantStruct;
import com.sxf.cps.customer.domain.merchant.service.MerchantBrandService;
import com.sxf.cps.customer.domain.merchant.service.RateCheckInLogService;
import com.sxf.cps.customer.domain.merchant.service.RateCheckInService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MerchantBrandAppService {

    @Autowired
    private MerchantStruct merchantStruct;
    @Autowired
    private RateCheckInService rateCheckInService;
    @Autowired
    private RateCheckInLogService rateCheckInLogService;
    @Autowired
    private MerchantBrandService merchantBrandService;

    @EventHandler
    @DefaultTransaction
    public void mergeMerchantBrand(CreateMerchantBrandEvent event){
        merchantBrandService.mergeMerchantBrandEntity(event,()->{
            rateCheckInService.mergeRateCheckIn(merchantStruct.toRateCheckInEntity(event));
            rateCheckInLogService.saveRateCheckInLogEntity(merchantStruct.toRateCheckInLogEntity(event));
            return null;
        });
    }

}
