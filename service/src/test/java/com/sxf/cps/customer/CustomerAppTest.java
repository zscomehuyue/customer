package com.sxf.cps.customer;

import com.sxf.cps.customer.api.merchant.enumtype.BrandEnum;
import com.sxf.cps.customer.api.merchant.enumtype.BrandFlagEnum;
import com.sxf.cps.customer.domain.merchant.entity.MerchantBrandEntity;
import com.sxf.cps.customer.domain.merchant.entity.MerchantEntity;
import com.sxf.cps.customer.domain.merchant.entity.RateCheckInEntity;
import com.sxf.cps.customer.domain.merchant.entity.RateCheckInLogEntity;
import com.sxf.cps.customer.domain.merchant.repository.MerchantBrandRepository;
import com.sxf.cps.customer.domain.merchant.repository.MerchantRepository;
import com.sxf.cps.customer.domain.merchant.repository.RateCheckInLogRepository;
import com.sxf.cps.customer.domain.merchant.repository.RateCheckInRepository;
import com.sxf.cps.customer.domain.merchant.service.MerchantService;
import com.sxf.cps.customer.infrastructure.util.BeanValueUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author: zscome
 * DateTime: 2020-04-01 15:52
 */
//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CustomerApp.class)
@WebAppConfiguration
public class CustomerAppTest {

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private RateCheckInRepository rateCheckInRepository;
    @Autowired
    private RateCheckInLogRepository rateCheckInLogRepository;
    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private MerchantBrandRepository merchantBrandRepository;


    @Test
    public void save() {
        save(1);
        save(2);
        save(3);
    }

    private void save(int id) {
        MerchantEntity merchant = BeanValueUtils.initValue(MerchantEntity.class, id);
        merchantRepository.save(merchant);
        MerchantBrandEntity brandEntity = BeanValueUtils.initValue(MerchantBrandEntity.class, id);
        brandEntity.getMerchantVo().setMerchantId(merchant.getId());
        brandEntity.getFactVo().setBrandId(BrandEnum.MPOS);
        brandEntity.getFactVo().setBrandFlag(BrandFlagEnum.DEFAULT_VALUE);
        merchantBrandRepository.save(brandEntity);

        RateCheckInEntity checkInEntity = BeanValueUtils.initValue(RateCheckInEntity.class, id);
        checkInEntity.setMerchantBrandId(brandEntity.getId());
        rateCheckInRepository.save(checkInEntity);

        RateCheckInLogEntity log = BeanValueUtils.initValue(RateCheckInLogEntity.class, id);
        log.setMerchantBrandId(brandEntity.getId());
        rateCheckInLogRepository.save(log);
    }

    public static void main(String[] args) {
    }


}
