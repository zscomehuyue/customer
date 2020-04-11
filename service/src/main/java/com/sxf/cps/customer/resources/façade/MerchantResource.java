package com.sxf.cps.customer.resources.façade;

import com.sxf.cps.customer.api.merchant.MerchantApi;
import com.sxf.cps.customer.api.merchant.dto.MerchantDto;
import com.sxf.cps.customer.api.merchant.form.CreateMerchantBrandForm;
import com.sxf.cps.customer.api.merchant.form.CreateMerchantForm;
import com.sxf.cps.customer.api.merchant.form.MerchantForm;
import com.sxf.cps.customer.domain.merchant.cmd.CreateMerchantBrandCmd;
import com.sxf.cps.customer.domain.merchant.cmd.CreateMerchantCmd;
import com.sxf.cps.customer.domain.merchant.service.MerchantService;
import com.sxf.cps.customer.infrastructure.util.UuidUtils;
import com.sxf.cps.customer.resources.assembler.MerchantAssembler;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.sxf.cps.customer.infrastructure.util.LogUtils.info;

@Slf4j
@RestController
@RequestMapping("v1/merchant")
public class MerchantResource implements MerchantApi {

    @Resource
    private MerchantService merchantService;
    @Resource
    private MerchantAssembler merchantAssembler;
    @Resource
    private CommandGateway publish;


    @Override
    public Page<MerchantDto> getMerchantPage(@RequestBody @Valid MerchantForm form) {
        return merchantService.getMerchantPage(form);
    }

    @Override
    public void createMerchant(@RequestBody @Valid CreateMerchantForm form) {
        form.setRegisterDate(Timestamp.valueOf(LocalDateTime.now()))
                .setId(UuidUtils.creatUuid());
        info(() -> "=createMerchant=>form:%s", form);
        CreateMerchantCmd cmd = merchantAssembler.toMerchantCmd(form);
        info(() -> "=createMerchant=>cmd:%s", cmd);
        publish.sendAndWait(cmd);
    }

    @Override
    public void registerMerchantBrand(@RequestBody @Valid CreateMerchantBrandForm form) {
        form.setId(UuidUtils.creatUuid());
        info(() -> "=registerMerchantBrand=>form:%s", form);
        CreateMerchantBrandCmd cmd = merchantAssembler.toMerchantBrandCmd(form);
        info(() -> "=registerMerchantBrand=>cmd:%s", cmd);
        publish.sendAndWait(cmd);
    }


}
