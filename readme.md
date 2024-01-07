## 微服務項目

1. springboot 每个版本之间的设置是有区别的，有可能这个版本这样设置可以，下个版本就不行了

2. springboot-start-redis 这个库在对接redis 的时候，设置方面需要格外注意，它不一样

3. 反射的时候 java.lang.InstantiationException 是因为你没有空参数构造

4. stream() 流之后，对原数组并不会产生任何改变

5. stream() 流调用外部变量得为final，但是是地址值不能变，对象里的内容可以变

6. security 里面的权限认证是基于aop实现的

7. security 内部登录信息基于session

8. 可以不使用 security 自带的权限认证，使用aop自己做权限认证

9. @Transactional(rollbackFor = Exception.class) 事务回滚是基于 AOP 做的