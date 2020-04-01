package com.sxf.cps.customer.domain.merchant.service;

import com.sxf.cps.customer.api.merchant.dto.MerchantDto;
import com.sxf.cps.customer.api.merchant.enumtype.BrandEnum;
import com.sxf.cps.customer.api.merchant.enumtype.BrandFlagEnum;
import com.sxf.cps.customer.api.merchant.form.CreateMerchantForm;
import com.sxf.cps.customer.api.merchant.form.MerchantForm;
import com.sxf.cps.customer.domain.merchant.entity.MerchantBrandEntity;
import com.sxf.cps.customer.domain.merchant.entity.MerchantEntity;
import com.sxf.cps.customer.domain.merchant.entity.RateCheckInEntity;
import com.sxf.cps.customer.domain.merchant.entity.RateCheckInLogEntity;
import com.sxf.cps.customer.domain.merchant.event.CreateMerchantBrandEvent;
import com.sxf.cps.customer.domain.merchant.event.CreateMerchantEvent;
import com.sxf.cps.customer.domain.merchant.mapstruct.MerchantStruct;
import com.sxf.cps.customer.domain.merchant.repository.MerchantBrandRepository;
import com.sxf.cps.customer.domain.merchant.repository.MerchantRepository;
import com.sxf.cps.customer.domain.merchant.repository.RateCheckInLogRepository;
import com.sxf.cps.customer.domain.merchant.repository.RateCheckInRepository;
import com.sxf.cps.customer.infrastructure.util.BeanValueUtils;
import com.sxf.cps.customer.infrastructure.util.DefaultTransaction;
import com.sxf.cps.customer.infrastructure.util.api.ExampleMatchers;
import com.sxf.cps.customer.resources.assembler.MerchantAssembler;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.junit.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Service
@RestController
@RequestMapping("test/merchant")
public class MerchantService {

    @Resource
    private MerchantStruct merchantStruct;
    @Resource
    private MerchantRepository merchantRepository;
    @Resource
    private MerchantAssembler merchantAssembler;
    @Resource
    private MerchantBrandRepository merchantBrandRepository;
    @Resource
    private RateCheckInLogRepository rateCheckInLogRepository;
    @Resource
    private RateCheckInRepository rateCheckInRepository;


    @EventHandler
    @DefaultTransaction
    public void mergeMerchant(CreateMerchantEvent event) {
        mergeMerchantEntity(event, () -> getMerchantBrand(event.getFactSn(), event.getFactId()));
    }

    public void mergeMerchant(CreateMerchantForm form) {
        mergeMerchant(merchantAssembler.toMerchantEvent(form));
    }

    private void mergeMerchantEntity(CreateMerchantEvent event, Supplier<Optional<MerchantBrandEntity>> brandFun) {
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
                bd.getMerchantVo().setMerchantCode(event.getMerchantCode());
                bd.getMerchantVo().setMerchantId(save.getId());
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


    /**
     * 级联查询，并解决N+1问题；
     *
     * @param form
     * @return
     */
    public Page<MerchantDto> getMerchantPage(MerchantForm form) {

        //FIXME ExampleMatcher 既然解决了多线程问题，又解决了多次创建对象，数据存储问题；采用中间保存结果；
        //FIXME 完美解决：ExampleMatcher扩展问题；
        ExampleMatcher matcher = ExampleMatchers.builder()
                .withMatcher(MerchantForm::getMobile, ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher(MerchantForm::getName, ExampleMatcher.GenericPropertyMatchers.startsWith())
                .build();

        Example<MerchantEntity> example = Example.of(merchantAssembler.toMerchantEntity(form), matcher);

        //FIXME Page 提供一个map方法很好，轻松解决映射问题；并且直接返回接需要的结果；有点中间拦截器的感觉，完美；
        return merchantRepository.findAll(example, PageRequest.of(form.getPage(), form.getSize()))
                .map(merchantAssembler::toMerchantDto);
    }


    @Test
    public void matcherTest() {
        ExampleMatcher matcher = ExampleMatchers.builder()
                .withMatcher(MerchantForm::getMobile, ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher(MerchantForm::getName, ExampleMatcher.GenericPropertyMatchers.startsWith())
                .build();
        System.out.println(matcher.getPropertySpecifiers().getSpecifiers().size());

        ExampleMatcher matcher2 = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("name2", ExampleMatcher.GenericPropertyMatchers.startsWith());
        System.out.println(matcher2.getPropertySpecifiers().getSpecifiers().size());
    }


    public Optional<MerchantBrandEntity> getMerchantBrand(String factSn, String factId) {
        return merchantBrandRepository.findByFactVoFactSnAndFactVoFactId(factSn, factId);
    }

    @EventHandler
    @DefaultTransaction
    public void mergeMerchantBrand(CreateMerchantBrandEvent event) {
        mergeMerchantBrandEntity(event, () -> {
            mergeRateCheckIn(merchantStruct.toRateCheckInEntity(event));
            saveRateCheckInLogEntity(merchantStruct.toRateCheckInLogEntity(event));
            return null;
        });
    }


    private void mergeRateCheckIn(RateCheckInEntity entity) {
        Optional<RateCheckInEntity> rate = rateCheckInRepository.findByFactSnAndFactId(entity.getFactSn(), entity.getFactId());
        rate.ifPresent(r -> {
            merchantStruct.updateRateCheckInEntity(entity, r);
            r.setModified(Timestamp.valueOf(LocalDateTime.now()));
            rateCheckInRepository.flush();
            log.info("=mergeRateCheckIn=>update entity:{}", entity);
        });
        rate.orElseGet(() -> {
            entity.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            entity.setModified(Timestamp.valueOf(LocalDateTime.now()));
            rateCheckInRepository.save(entity);
            log.info("=mergeRateCheckIn=>save entity:{}", entity);
            return null;
        });
    }

    private void saveRateCheckInLogEntity(RateCheckInLogEntity entity) {
        entity.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        entity.setModified(Timestamp.valueOf(LocalDateTime.now()));
        rateCheckInLogRepository.save(entity);
    }

    private void mergeMerchantBrandEntity(CreateMerchantBrandEvent event, Supplier<String> afterMergeBrand) {
        Optional<MerchantBrandEntity> brand = merchantBrandRepository.findByFactVoFactSnAndFactVoFactId(event.getFactSn(), event.getFactId());
        brand.ifPresent(b -> {
            merchantStruct.updateMerchantBrandEntity(event, b);
            b.setModified(Timestamp.valueOf(LocalDateTime.now()));
            merchantBrandRepository.flush();
            log.info("=mergeMerchantBrandEntity=>update event:{}", event);
        });
        brand.orElseGet(() -> {
            MerchantBrandEntity entity = merchantStruct.toMerchantBrandEntity(event);
            entity.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            entity.setModified(Timestamp.valueOf(LocalDateTime.now()));
            merchantBrandRepository.save(entity);
            log.info("=mergeMerchantBrandEntity=>save event:{}", event);
            return null;
        });
        afterMergeBrand.get();
    }



    @Test
    @GetMapping("save/{id}")
    @DefaultTransaction
    public void saveAll(@PathVariable int id) {
        MerchantEntity merchant = BeanValueUtils.initValue(MerchantEntity.class, id);
        //FIXME id 必须为空，这个值hibernat会自动生成；
        merchant.setId(null);

        System.err.println("=saveAll=>"+merchant);
        merchantRepository.save(merchant);

        MerchantBrandEntity brandEntity = BeanValueUtils.initValue(MerchantBrandEntity.class, id);
        brandEntity.getMerchantVo().setMerchantId(merchant.getId());
        brandEntity.getFactVo().setBrandId(BrandEnum.MPOS);
        brandEntity.getFactVo().setBrandFlag(BrandFlagEnum.DEFAULT_VALUE);
        System.err.println("=saveAll=>"+brandEntity);
        brandEntity.setRateCheckInEntity(null);
        merchantBrandRepository.save(brandEntity);

        RateCheckInEntity checkInEntity = BeanValueUtils.initValue(RateCheckInEntity.class, id);
        checkInEntity.setMerchantBrandId(brandEntity.getId());
        System.err.println("=saveAll=>"+checkInEntity);
        rateCheckInRepository.save(checkInEntity);

        RateCheckInLogEntity log = BeanValueUtils.initValue(RateCheckInLogEntity.class, id);
        log.setMerchantBrandId(brandEntity.getId());
        System.err.println("=saveAll=>"+log);
        rateCheckInLogRepository.save(log);





    }

}
