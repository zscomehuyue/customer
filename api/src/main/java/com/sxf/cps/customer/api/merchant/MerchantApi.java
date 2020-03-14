package com.sxf.cps.customer.api.merchant;

import com.sxf.cps.customer.api.merchant.dto.MerchantBrandDto;
import com.sxf.cps.customer.api.merchant.form.MerchantForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Page;

public interface MerchantApi {

    @PostMapping("getMerchantPage")
    Page<MerchantBrandDto> getMerchantPage(@RequestBody MerchantForm form);
}
