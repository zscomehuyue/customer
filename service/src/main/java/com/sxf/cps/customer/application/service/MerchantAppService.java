package com.sxf.cps.customer.application.service;

import com.sxf.cps.customer.infrastructure.util.DefaultTransaction;
import com.sxf.cps.customer.domain.merchant.event.CreateMerchantEvent;
import com.sxf.cps.customer.domain.merchant.mapstruct.MerchantStruct;
import com.sxf.cps.customer.domain.merchant.service.MerchantBrandService;
import com.sxf.cps.customer.domain.merchant.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class MerchantAppService {
    @Resource
    private MerchantStruct merchantStruct;
    @Resource
    private MerchantBrandService merchantBrandService;
    @Resource
    private MerchantService merchantService;

    @EventHandler
    @DefaultTransaction
    public void mergeMerchant(CreateMerchantEvent event) {
        merchantService.mergeMerchantEntity(event, () -> merchantBrandService.getMerchantBrand(event.getFactSn(), event.getFactId()));
    }
}
