package com.sxf.cps.customer.domain.merchant.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateMerchantCmd   implements Serializable {

    @NotNull(message = "uuid不能为空！")
    @NotBlank(message = "uuid不能为空！")
    @TargetAggregateIdentifier
    private Long uuid;

    @NotNull(message = "merchantCode不能为空！")
    @NotBlank(message = "merchantCode不能为空！")
    private String merchantCode;

    @NotNull(message="factId不能为空！")
    @NotBlank(message="factId不能为空！")
    private String factId;

    @NotNull(message="factSn不能为空！")
    @NotBlank(message="factSn不能为空！")
    private String factSn;

    @NotNull(message = "registerDate不能为空！")
    @NotBlank(message = "registerDate不能为空！")
    private Timestamp registerDate;

    @NotNull(message="name不能为空！")
    @NotBlank(message="name不能为空！")
    private String name;

    @NotNull(message="mobile不能为空！")
    @NotBlank(message="mobile不能为空！")
    private String mobile;

    @NotNull(message="mobileCipher不能为空！")
    @NotBlank(message="mobileCipher不能为空！")
    private String mobileCipher;

}
