package com.sxf.cps.customer.resources.assembler;

import com.sxf.cps.customer.api.merchant.dto.MerchantDto;
import com.sxf.cps.customer.api.merchant.form.MerchantForm;
import com.sxf.cps.customer.domain.merchant.entity.MerchantEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface MerchantAssembler {

    MerchantEntity toMerchantEntity(MerchantForm form);

    MerchantDto toMerchantDto(MerchantEntity entity);
}
