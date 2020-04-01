package com.sxf.cps.customer.resources.façade;

import com.sxf.cps.customer.api.merchant.MerchantApi;
import com.sxf.cps.customer.api.merchant.dto.MerchantDto;
import com.sxf.cps.customer.api.merchant.form.CreateMerchantForm;
import com.sxf.cps.customer.api.merchant.form.MerchantForm;
import com.sxf.cps.customer.domain.merchant.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1/merchant")
public class MerchantResource implements MerchantApi {

    @Autowired
    private MerchantService merchantService;

    @Override
    public Page<MerchantDto> getMerchantPage(MerchantForm form) {
        return merchantService.getMerchantPage(form);
    }

    public void mergeMerchant(CreateMerchantForm form ){
        merchantService.mergeMerchant(form);
    }

}
