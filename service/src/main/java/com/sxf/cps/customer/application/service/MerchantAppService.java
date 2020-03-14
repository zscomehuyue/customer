package com.sxf.cps.customer.application.service;

import com.sxf.cps.customer.DefaultTransaction;
import com.sxf.cps.customer.domain.merchant.event.CreateMerchantEvent;
import com.sxf.cps.customer.domain.merchant.mapstruct.MerchantStruct;
import com.sxf.cps.customer.domain.merchant.service.MerchantBrandService;
import com.sxf.cps.customer.domain.merchant.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MerchantAppService {
    @Autowired
    private MerchantStruct merchantStruct;
    @Autowired
    private MerchantBrandService merchantBrandService;
    @Autowired
    private MerchantService merchantService;

    @EventHandler
    @DefaultTransaction
    public void mergeMerchant(CreateMerchantEvent event) {
        merchantService.mergeMerchantEntity(event, () -> {
            return merchantBrandService.getMerchantBrand(event.getFactSn(), event.getFactId());
        });
    }
}
