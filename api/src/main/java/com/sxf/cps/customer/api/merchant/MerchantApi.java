package com.sxf.cps.customer.api.merchant;

import com.sxf.cps.customer.api.merchant.dto.MerchantDto;
import com.sxf.cps.customer.api.merchant.form.CreateMerchantBrandForm;
import com.sxf.cps.customer.api.merchant.form.CreateMerchantForm;
import com.sxf.cps.customer.api.merchant.form.MerchantForm;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface MerchantApi {

    @PostMapping("getMerchantPage")
    Page<MerchantDto> getMerchantPage(@RequestBody @Valid MerchantForm form);

    /**
     *
     * FIXME 命令方式， 优点在哪里？
     * FIXME 使用springboot实现cqrs更容易 ,非业务性的功能，几乎都不需要实现了；
     * FIXME cqrs 不是特像，把消息发送到队列，然后有消费者，消费吗；spring-boot spring-cloud已经很成熟，根本没有必要使用axon框架；
     * FIXME 从学成成本来说，也没有必要；使用范围小；并且公司切换成这个框架的可能性，更小，根本不是主流；
     * FIXME 该框架的服务治理，也不完善；思想可以借鉴；
     *
     * 命令流程（前台）：
     * 1. 前台发送请求到网关
     * 2. 网关负载均衡到customer服务；
     * 3. customer接收请求，并转换为命令，发送命令到axonServer；
     * 4. axonServer收到命令，存储命令，负载均衡，发布命令；
     * 5. 后台接收该命名，并执行相关逻辑，返回结果；
     *
     * 非命令流程（前台）：
     * 1. 前台发送请求
     * 2. 网关负载均衡到customer服务；
     * 3. customer接收请求，并处理相关逻辑，返回结果；
     *
     * 总结：
     * 缺点：
     * 1. 多2个网络请求；
     *       1.1 发送命令到axonServer
     *       1.2 接收命令并处理
     * 2. 命令存储时间；
     * 3. 速度可能会慢些，但是该速度是内部网路速度；
     *
     * 优点：
     * 1. 存储了所有的命令，便于后期测试、重放执行命令；
     * 2. 1个请求，后台只要关注该请求的都可以接受并处理；
     * 3. 可以缓存大量的请求，增加了吞吐量；
     *
     *
     *
     *
     */
    @PostMapping("createMerchant")
    void createMerchant(@RequestBody @Valid CreateMerchantForm form);

    /**
     * 命令方式
     */
    @PostMapping("createMerchantBrand")
    void registerMerchantBrand(@RequestBody @Valid CreateMerchantBrandForm form);

    @PostMapping("addMerchantBrand")
    void addMerchantBrand(@RequestBody @Valid CreateMerchantBrandForm form);

    @PostMapping("addMerchant")
    void addMerchant(@RequestBody @Valid CreateMerchantForm form);

}
