package com.sxf.cps.customer.resources.façade;

import com.sxf.cps.customer.api.merchant.MerchantApi;
import com.sxf.cps.customer.api.merchant.dto.MerchantDto;
import com.sxf.cps.customer.api.merchant.form.MerchantForm;
import com.sxf.cps.customer.domain.merchant.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("v1/merchant")
public class MerchantResource implements MerchantApi {

    @Resource
    private MerchantService merchantService;

    @Override
    public Page<MerchantDto> getMerchantPage(MerchantForm form) {
        return merchantService.getMerchantPage(form);
    }
}
