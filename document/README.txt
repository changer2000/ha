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


4.昨天在弄spring + tiles2的时候，做了个baseLayout的tiles template，想把资源文件里的每个jsp页面的title
用参数传入。
在baseLayout.jsp中用如下方式实现了：
	<tiles:useAttribute id="titleKey" name="titleKey"/><!-- 如果没有这一行，下面的title显示就是乱码?????? -->
	<title><fmt:message key="${titleKey}"/></title>
	
今天无意中发现如下3个问题：
（1）<tiles:useAttribute id="titleKey" name="titleKey"/>，如果不加这一行，下面的title显示就是乱码。
猜测，tiles.xml里定义的：<put-attribute name="titleKey" value="title.main" />，
页面上定义的<fmt:message key="${titleKey}"/>实际上是取得request或page里定义的titleKey，
如果没有定义，<fmt:message key="${titleKey}"/>中的titleKey就取不到，而显示??????。
而<tiles:useAttribute id="titleKey" name="titleKey"/>的作用就是在request或page中定义一个titleKey。

（2）关于fmt.tld
这个文件原来是直接从公司的项目里copy进去的，没想到歪打正着。
E:\eclise_svn\workspace\ha\src\main\webapp\WEB-INF\lib\com.springsource.org.apache.taglibs.standard-1.1.2.jar
中定义了
fmt-1_0-rt.tld
fmt-1_0.tld
fmt.tld
只有fmt-1_0.tld和项目里的fmt.tld是一致的。
fmt-1_0-rt.tld 和 fmt-1_0.tld之间的区别只有一个：<rtexprvalue>true/false</rtexprvalue>
上面2个和fmt-1_0.tld的区别很多，最主要在文件的开头。
尝试用jar中的fmt.tld，覆盖项目里的fmt.tld，结果title就不显示正常的文字了。
只能换回原来的fmt.tld。
猜测真正的原因就是在【每个文件的开头定义的那一段上】

（3）关于fmt.tld
把<rtexprvalue>false</rtexprvalue>改成true，也能正常显示title。
实在是搞不懂为什么rtexprvalue可以随便乱设值。


5.BaseDomain.java的修改：
由于把root-config.xml的<bean id="sessionFactory" class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
改成了<bean id="sessionFactory" class="org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean">，
想不用写***.hbm.xml文件。

结果导致一定要在基类里定义的id不能用，以后只好在每个子类里面分别定义id了。
