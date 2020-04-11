package com.sxf.cps.customer.resources.assembler;

import com.sxf.cps.customer.api.merchant.dto.MerchantDto;
import com.sxf.cps.customer.api.merchant.form.CreateMerchantBrandForm;
import com.sxf.cps.customer.api.merchant.form.CreateMerchantForm;
import com.sxf.cps.customer.api.merchant.form.MerchantForm;
import com.sxf.cps.customer.domain.merchant.cmd.CreateMerchantBrandCmd;
import com.sxf.cps.customer.domain.merchant.cmd.CreateMerchantCmd;
import com.sxf.cps.customer.domain.merchant.entity.MerchantEntity;
import com.sxf.cps.customer.domain.merchant.event.CreateMerchantBrandEvent;
import com.sxf.cps.customer.domain.merchant.event.CreateMerchantEvent;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface MerchantAssembler {

    MerchantEntity toMerchantEntity(MerchantForm form);

    MerchantDto toMerchantDto(MerchantEntity entity);

    CreateMerchantEvent toMerchantEvent(CreateMerchantForm entity);

    CreateMerchantBrandEvent toMerchantBrandEvent(CreateMerchantBrandForm entity);

    CreateMerchantCmd toMerchantCmd(CreateMerchantForm entity);

    CreateMerchantBrandCmd toMerchantBrandCmd(CreateMerchantBrandForm entity);
}
