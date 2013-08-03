1.关于hibernate的validator框架和spring结合的总结：

在做Login页面时，对输入域加了一个Length(min=?, max=?)的验证，结果min/max的值始终无法显示，
总是报min是无效的数字。
后来查了很多资料，做了一些实验
（如：
更换所有的spring*.jar为3.1版本的spring*.jar；
加入了一些其他的jar；
把controllers.xml里的内容合并到servlet-context.xml中，并删掉controllers.xml；
给LoginForm.java加上@Entity声明；
新建一个Validato-message.properties文件，把消息从Message.properties放入这个文件中；）
这些都不管用。

最后发现，需要把<mvc:annotation-driven/>改成如下：

	<mvc:annotation-driven validator="validator" />
	
    <bean id="validator" class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean">
        <property name="providerClass"  value="org.hibernate.validator.HibernateValidator"/>
        <!-- 如果不加默认到 使用classpath下的 ValidationMessages.properties -->
        <property name="validationMessageSource" ref="messageSource"/>
    </bean>
    
一切就都正常了。
这个是最精简版了，不能再少了。
如果去掉<property name="validationMessageSource" ref="messageSource"/>，则显示的错误消息就是“{error.length}”这样。


2.在LoginController里有关于编程处理输入check的例子:
	result.rejectValue("empe_num", "reserved.data", new Object[] {user.getEmpe_num()}, null);

	
3.****** 在LoginController里有关于validator的group的例子，不过不是很典型的那种。等有了其他的，再补上