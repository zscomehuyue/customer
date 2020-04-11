package com.sxf.cps.customer.domain.merchant.mapstruct;

import com.sxf.cps.customer.domain.merchant.cmd.*;
import com.sxf.cps.customer.domain.merchant.entity.MerchantBrandEntity;
import com.sxf.cps.customer.domain.merchant.entity.MerchantEntity;
import com.sxf.cps.customer.domain.merchant.entity.RateCheckInEntity;
import com.sxf.cps.customer.domain.merchant.entity.RateCheckInLogEntity;
import com.sxf.cps.customer.domain.merchant.event.CreateMerchantBrandEvent;
import com.sxf.cps.customer.domain.merchant.event.CreateMerchantEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface MerchantStruct {
    MerchantStruct build = Mappers.getMapper(MerchantStruct.class);

    MerchantEntity toMerchantEntity(CreateMerchantEvent event);

    CreateMerchantEvent toCreateMerchantEvent(CreateMerchantCmd cmd);

    CreateMerchantBrandEvent toCreateMerchantBrandEvent(CreateMerchantBrandCmd cmd);

    MerchantBrandEntity toMerchantBrandEntity(CreateMerchantBrandEvent cmd);

    @Mappings({
            @Mapping(source = "userId", target = "creator"),
    })
    RateCheckInEntity toRateCheckInEntity(CreateMerchantBrandEvent cmd);

    @Mappings({
            @Mapping(source = "userId", target = "creator"),
    })
    RateCheckInLogEntity toRateCheckInLogEntity(CreateMerchantBrandEvent cmd);

    @Mappings({
            @Mapping(source = "userId", target = "creator"),
    })
    RateCheckIn toRateCheckIn(CreateMerchantBrandEvent event);

    @Mappings({
            @Mapping(source = "userId", target = "creator"),
    })
    RateCheckInLog toRateCheckInLog(CreateMerchantBrandEvent event);

    @Mappings({
            @Mapping(source = "userId", target = "creator"),
    })
    void updateRateCheckIn(CreateMerchantBrandEvent event, @MappingTarget RateCheckIn rateCheckIn);

    void updateMerchant(CreateMerchantEvent event, @MappingTarget Merchant merchant);

    void updateMerchantBrand(CreateMerchantBrandEvent event, @MappingTarget MerchantBrand merchantBrand);

    @Mapping(target = "id", ignore = true)
    void updateMerchantEntity(CreateMerchantEvent event, @MappingTarget MerchantEntity entity);

    @Mapping(target = "id", ignore = true)
    void updateRateCheckInEntity(RateCheckInEntity event, @MappingTarget RateCheckInEntity entity);

    @Mapping(target = "id", ignore = true)
    void updateMerchantBrandEntity(CreateMerchantBrandEvent event, @MappingTarget MerchantBrandEntity entity);

}
