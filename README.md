# command 、event 放在一起；
## command只要mq发消息
## event只要从mq接收消息
## 生产者和消费者关系，感觉是可以独立部署；但是从逻辑上还是很紧密的；因此放在一个项目里；但是包名称要分开；
## 命令可以独立，针对前端的请求；并发量和后台不一样；中间有mq进行缓存；


#问题：
## 自动更新执行，语句如下：
### Hibernate: update token_entry set timestamp=? where processor_name=? and segment=? and owner=?
## idea 直接执行main方法，感觉没有编译导致，找不到些包，其实是对的；
    ### 不知如何，就好了，file->清理缓存
## SQLSyntaxErrorException: Specified key was too long; max key length is 1000 bytes

## 启动测试用例时，提示MerchantService 加载不到；

## hibernate rpc 时，如果客户端，这个时候获取延迟加载的类，应该为null；预警超过了session的范围？

## JPA 手动指定id时，报告错误；该id是业务无关的，不应该手动指定；
## devtools不能使用，否则会导致启动异常

