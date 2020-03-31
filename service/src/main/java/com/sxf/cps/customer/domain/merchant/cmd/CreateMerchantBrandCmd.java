package com.sxf.cps.customer.domain.merchant.cmd;

import com.sxf.cps.customer.api.merchant.enumtype.BrandEnum;
import com.sxf.cps.customer.api.merchant.enumtype.BrandFlagEnum;
import com.sxf.cps.customer.api.merchant.enumtype.FactStateEnum;
import com.sxf.cps.customer.api.merchant.enumtype.FactStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateMerchantBrandCmd implements Serializable {

    @NotNull(message = "uuid不能为空！")
    @NotBlank(message = "uuid不能为空！")
    @TargetAggregateIdentifier
    private String uuid;

    @NotNull(message="factId不能为空！")
    @NotBlank(message="factId不能为空！")
    private String factId;

    @NotNull(message="factSn不能为空！")
    @NotBlank(message="factSn不能为空！")
    private String factSn;

    @NotNull(message="brandFlag不能为空！")
    @NotBlank(message="brandFlag不能为空！")
    private BrandFlagEnum brandFlag;

    @NotNull(message="name不能为空！")
    @NotBlank(message="name不能为空！")
    private String name;

    @NotNull(message="mobile不能为空！")
    @NotBlank(message="mobile不能为空！")
    private String mobile;

    @NotNull(message="mobileCipher不能为空！")
    @NotBlank(message="mobileCipher不能为空！")
    private String mobileCipher;

    @NotNull(message="brandId不能为空！")
    @NotBlank(message="brandId不能为空！")
    private BrandEnum brandId;

    @NotNull(message="userId不能为空！")
    @NotBlank(message="userId不能为空！")
    private String userId;

    @NotNull(message="factStatus不能为空！")
    private FactStatusEnum factStatus;

    @NotNull(message="factState不能为空！")
    private FactStateEnum factState;

    @NotNull(message="rateId不能为空！")
    private String rateId;

}
