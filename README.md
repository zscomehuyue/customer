# command 、event 放在一起；
## command只要mq发消息
## event只要从mq接收消息
## 生产者和消费者关系，感觉是可以独立部署；但是从逻辑上还是很紧密的；因此放在一个项目里；但是包名称要分开；
## 命令可以独立，针对前端的请求；并发量和后台不一样；中间有mq进行缓存；


#问题：
## 自动更新执行，语句如下：
### Hibernate: update token_entry set timestamp=? where processor_name=? and segment=? and owner=?