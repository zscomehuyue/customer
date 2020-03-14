package com.sxf.cps.customer.domain.merchant.service;

import com.sxf.cps.customer.api.merchant.dto.MerchantDto;
import com.sxf.cps.customer.api.merchant.form.MerchantForm;
import com.sxf.cps.customer.domain.merchant.entity.MerchantBrandEntity;
import com.sxf.cps.customer.domain.merchant.entity.MerchantEntity;
import com.sxf.cps.customer.domain.merchant.event.CreateMerchantEvent;
import com.sxf.cps.customer.domain.merchant.mapstruct.MerchantStruct;
import com.sxf.cps.customer.domain.merchant.repository.MerchantRepository;
import com.sxf.cps.customer.infrastructure.util.api.ExampleMatchers;
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
    public void matcherTest(){
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

}
