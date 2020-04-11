package com.sxf.cps.customer.api.merchant;

import com.sxf.cps.customer.api.merchant.dto.MerchantDto;
import com.sxf.cps.customer.api.merchant.form.CreateMerchantBrandForm;
import com.sxf.cps.customer.api.merchant.form.CreateMerchantForm;
import com.sxf.cps.customer.api.merchant.form.MerchantForm;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface MerchantApi {

    @PostMapping("getMerchantPage")
    Page<MerchantDto> getMerchantPage(@RequestBody @Valid MerchantForm form);

    @PostMapping("createMerchant")
    void createMerchant(@RequestBody @Valid CreateMerchantForm form);

    @PostMapping("createMerchantBrand")
    void registerMerchantBrand(@RequestBody @Valid CreateMerchantBrandForm form);
}
