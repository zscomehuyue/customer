package com.sxf.cps.customer.domain.merchant.service;

import com.sxf.cps.customer.api.merchant.dto.MerchantDto;
import com.sxf.cps.customer.api.merchant.form.MerchantForm;
import com.sxf.cps.customer.domain.merchant.entity.MerchantBrandEntity;
import com.sxf.cps.customer.domain.merchant.entity.MerchantEntity;
import com.sxf.cps.customer.domain.merchant.event.CreateMerchantEvent;
import com.sxf.cps.customer.domain.merchant.mapstruct.MerchantStruct;
import com.sxf.cps.customer.domain.merchant.repository.MerchantRepository;
import com.sxf.cps.customer.infrastructure.util.api.ExampleMatchers;
import com.sxf.cps.customer.infrastructure.util.api.FiledHelper;
import com.sxf.cps.customer.resources.assembler.MerchantAssembler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Service
public class MerchantService {

    @Resource
    private MerchantStruct merchantStruct;
    @Resource
    private MerchantRepository merchantRepository;
    @Resource
    private MerchantAssembler merchantAssembler;

    public void mergeMerchantEntity(CreateMerchantEvent event, Supplier<Optional<MerchantBrandEntity>> brandFun) {
        Optional<MerchantEntity> merchant = merchantRepository.findByMerchantCode(event.getMerchantCode());
        merchant.ifPresent(b -> {
            log.info("=mergeMerchantEntity=>update:{}", event);
            merchantStruct.updateMerchantEntity(event, b);
            b.setModified(Timestamp.valueOf(LocalDateTime.now()));
            merchantRepository.flush();
        });
        merchant.orElseGet(() -> {
            log.info("=mergeMerchantEntity=>save:{}", event);
            MerchantEntity entity = merchantStruct.toMerchantEntity(event);
            entity.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            entity.setModified(Timestamp.valueOf(LocalDateTime.now()));

            MerchantEntity save = merchantRepository.save(entity);
            Optional<MerchantBrandEntity> brand = brandFun.get();
            brand.ifPresent(bd -> {
                bd.setMerchantCode(event.getMerchantCode());
                bd.setMerchantId(save.getUuid());
                bd.setModified(Timestamp.valueOf(LocalDateTime.now()));
                merchantRepository.flush();
            });
            brand.orElseGet(() -> {
                log.error("=mergeMerchantEntity=>not register factSn:{} ,factId:{}", event.getFactSn(), event.getFactId());
                return null;
            });
            return null;
        });
    }


    public Page<MerchantDto> getMerchantPage(MerchantForm form) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher(FiledHelper.field(MerchantForm::getMobile), ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher(FiledHelper.field(MerchantForm::getName), ExampleMatcher.GenericPropertyMatchers.contains());
        Example<MerchantEntity> example = Example.of(merchantAssembler.toMerchantEntity(form), matcher);
        return merchantRepository.findAll(example, PageRequest.of(form.getPage(), form.getSize()))
                .map(merchantAssembler::toMerchantDto);
    }

    public static void main(String[] args) {
        //FIXME ExampleMatchers fn 功能
        ExampleMatcher matcher = ExampleMatcher.matching();
        matcher = ExampleMatchers.withMatcher(MerchantForm::getMobile, ExampleMatcher.GenericPropertyMatchers.startsWith(), matcher);
        matcher = ExampleMatchers.withMatcher(MerchantForm::getName, ExampleMatcher.GenericPropertyMatchers.startsWith(), matcher);

        System.out.println(matcher.getPropertySpecifiers().getSpecifiers().size());

        ExampleMatcher matcher2 = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith());
        System.out.println(matcher2.getPropertySpecifiers().getSpecifiers().size());
    }

}
