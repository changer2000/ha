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


6.想做个去掉List中为空的对象的实验。
以下是可能的修改位置：
org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest

ServletModelAttributeMethodProcessor

org.springframework.web.method.annotation.ModelAttributeMethodProcessor.resolveArgument, line 95

org.springframework.web.bind.ServletRequestDataBinder.bind, line 105


org.springframework.validation.DataBinder, line 740


org.springframework.beans.AbstractPropertyAccessor.setPropertyValues, line 67

org.springframework.beans.BeanWrapperImpl, line 525


大位置：org.springframework.web.method.annotation.ModelAttributeMethodProcessor.resolveArgument(), line 106~107


(1)打包流程：
   a. 在C:下建立spring-web-3.2.1.RELEASE目录，把原始的jar里的文件解开，放到这个目录下
   b. eclipse的spring-web项目的java编译等级设为1.5
   c. spring-web目录下的bin\main\org\springframework\web\method\annotation\ModelAttributeMethodProcessor.class copy到c:\spring-web-3.2.1.RELEASE的对应目录
   d. 在C:\spring-web-3.2.1.RELEASE目录下运行 "jar cvf spring-web-3.2.1.RELEASE.jar ." ，得到目标jar
   
   
(2)网上的一个代替form:errors的方案:
<!-- instead of <form:errors path="name"> -->
<spring:bind path="name">
  <c:if test="${status.error}">
    <img src="<c:url value="/resources/images/warning.png"/>"
       width="31" height="32" class="error_tooltip" title="${status.errorMessage}" />
  </c:if>
</spring:bind>


(3)比较难看的一种显示多行validate错误方法:
					<c:forEach var="peer" items="${user.atdncList}" varStatus="status">
						......
						<c:set var="si" scope="page" value="${status.index}"/>
						<c:if test="${status.index == 0}">
							<c:out value="${si}"></c:out>
							<%=pageContext.getAttribute("si", pageContext.PAGE_SCOPE)%>
							<% Integer num=(Integer)pageContext.getAttribute("si", pageContext.PAGE_SCOPE);
								String fn = "atdncList["+num+"].atndnc_name";
							%>
							<form:errors path="<%=fn%>"/>
						</c:if>
						......
					</c:forEach>
********************************
经过艰苦卓绝的努力，终于找到一种比较不难看的方式。解决方案如下：可能会引起其他地方的bug，要有个清醒认识。
a) 修改spring-webmvc工程中，org.springframework.web.servlet.tags.form.AbstractDataBoundFormElementTag.java，
增加protected String parsePath(String path) throws JspException {...}方法，在getPath()的返回值处被调用，
返回解析后的path
b)jsp端，需要加入<c:set var="si" scope="page" value="${status.index}"/>，调用处：<form:input path="hldyListDtlList[si].hldy_start" ..../>

具体例子可以看hldy_list_info_b.jsp
********************************					


					
7.今天在用<form:select>时，发现一个非常奇怪的问题: items="${***}"总是无法解析成功。
调试了半天发现和下面这段程序有关：
ExpressionEvaluationUtils.java, line 72
	public static boolean isSpringJspExpressionSupportActive(PageContext pageContext) {
		ServletContext sc = pageContext.getServletContext();
		String springJspExpressionSupport = sc.getInitParameter(EXPRESSION_SUPPORT_CONTEXT_PARAM);
		if (springJspExpressionSupport != null) {
			return Boolean.valueOf(springJspExpressionSupport);
		}
		if (sc.getMajorVersion() >= 3) {
			// We're on a Servlet 3.0+ container: Let's check what the application declares...
			if (sc.getEffectiveMajorVersion() == 2 && sc.getEffectiveMinorVersion() < 4) {
				// Application declares Servlet 2.3- in its web.xml: JSP 2.0 expressions not active.
				// Activate our own expression support.
				return true;
			}
		}
		return false;
	}
	
这个工程用的是tomecat 7,sc.getMajorVersion() >= 3，(猜测是在project-->properties -->project facets --> Dynamic Web Module --> 3.0)
if (sc.getEffectiveMajorVersion() == 2 && sc.getEffectiveMinorVersion() < 4)，而在web.xml里配置的version=3.0，
所以这一段总是返回false，造成不进行el表达式解析。

解决方法：
把web.xml里的version改为"2.3"即可。
这个解决方法需要时间的检验！！！！
  \----- 不幸言中！ 上面这个解决方案并不好。
         比较好的一个办法是：在web.xml文件里，增加：
         <context-param>
			<description>Spring Expression Language Support</description>
			<param-name>springJspExpressionSupport</param-name>
			<param-value>true</param-value>
		</context-param>
	但这样做，会有隐患：http://www.dbappsecurity.com.cn/news/n2013/201301_28_01.html，造成远程代码执行漏洞。所以这个值只能设为false。
	也就是意味着<form:select>里的items="${***}"基本上是不能用了


后来找到一个不是很好看的写法，可以解决问题：
	<%List hldyList = (List) pageContext.getAttribute("hldyOptions", pageContext.REQUEST_SCOPE); %>
		<form:select path="holidayPeerId" items="<%= hldyList %>"
			itemLabel="name" itemValue="id" cssClass="span2"/><form:errors path="holidayPeerId" cssClass="error"/>


今天，修改了springmvc工程的SelectTag.java文件的setItems(Object items)，以后只要传入变量名就可以了
	<form:select path="searchBean.hldy_id" items="hldyOptions"
			itemLabel="name" itemValue="id" cssClass="span2"
		>

8.类似于struts中的BaseAction，可以把进入页面的代码统一写在一处，用以下方法进入
UserListController.java中：
mv = new ModelAndView("redirect:user_info?empe_num="+peer.getEmpe_num());

public String handle() {  
    // return "forward:/hello" => 转发到能够匹配 /hello 的 controller 上  
    // return "hello" => 实际上还是转发，只不过是框架会找到该逻辑视图名对应的 View 并渲染  
    // return "/hello" => 同 return "hello"  
    return "forward:/hello";  
} 


9.以前在每个Controller里，为了使用UserInfo，每次都是在Controller类的头部定义一个
import org.springframework.web.bind.annotation.SessionAttributes;
....
@SessionAttributes(value="SESSION_KEY_USER_INFO")
***Controller {

}

然后在Controller的方法中，声明如下使用：
public ModelAndView register(@ModelAttribute("SESSION_KEY_USER_INFO") UserInfo userInfo, 
	..... ) {
	
}


感觉这样做很不方便，后来参考了
http://lvdong5830.iteye.com/blog/1508987：
	描述Session中的User是怎么被放到具体方法里的，很经典。不过是针对Spring2.5的,所以所说的方法三已经不能用了。
	按照这个链接给出的思路，做了一个解决方法，即com.etech.system.resolver.UserArgumentResolver
		和servlet-context.xml文件中的：
			<beans:bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
				<beans:property name="customArgumentResolvers">
					<beans:list>
						<beans:ref bean="userArgumentResolver"/>	
					</beans:list>
				</beans:property>
			</beans:bean>
			<beans:bean id="userArgumentResolver" class="com.etech.system.resolver.UserArgumentResolver">
			</beans:bean>
		发现，日期型的数据，无法设定。会报Date对象创建错
		\-----------------------------------------------> 继续补充（2013-10-30）：
		今天在看spring-mvc-showcase的servlet-context.xml文件，发现它用的就是
		用我上面的方法，是能够起作用的。比较之后发现，应该如下例配置类似：
			<mvc:annotation-driven validator="validator" conversion-service="conversion-service">
				<mvc:argument-resolvers>
					<bean class="com.etech.system.resolver.UserArgumentResolver"/>
				</mvc:argument-resolvers>	
			</mvc:annotation-driven>
		这样整个都能用了，而且效率估计比下面的好一些（因为有support()方法）
		------------------------------------------------------------------------
	
后来又参考了http://starscream.iteye.com/blog/1098880：提出了新的方法，很合适目前这个问题的解决。
	按照这个链接给出的思路，做了一个解决方法，即com.etech.system.resolver.UserArgument2Resolver
		和controllers.xml 文件中的：
			<mvc:annotation-driven validator="validator" conversion-service="conversion-service">
				<mvc:argument-resolvers>
					<bean class="com.etech.system.resolver.UserArgument2Resolver"/>
				</mvc:argument-resolvers>	
			</mvc:annotation-driven>
		顺带发现controllers.xml头部定义的xsd是3.0的，不支持上面的语法，要改成3.2才行。
		然后，把所有的Controller的SessionAttributes和“@ModelAttribute("SESSION_KEY_USER_INFO")”都去掉就可以了

前一种方法，是实现HandlerMethodArgumentResolver接口，看api文档，Resolves a method parameter into an argument value from a given request.
后一种方法，是事项WebArgumentResolver接口，看api文档，Resolve an argument for the given handler method parameter within the given web request.
比较2者，猜测前一种方法会在数据绑定之前起作用，导致屏蔽了后面的spring数据parse/绑定，导致Date对象创建错。
而后一种方法，明确指定了传入handle（即Controller中的具体方法）的参数时起作用，估计切点更明确。
	
------------------------------------------	
1118.css的一些基本概念：
-------------------------------------
（1）逗号，空格，冒号，点号的含义
 一：#a,b{…………｝
 二：#a b{…………｝
 三：#a:b{…………｝
 四：#a.b{…………｝
一、一个id叫a和一个标签是b的样式
二、一个id叫a下面的一个标签是b的样式
三、一个id叫a的伪类b的样式
四、一个id叫a的下面的class叫b的样式 
-------------------------------------
伪类的语法是在原有的语法里加上一个伪类（pseudo-class）：

selector:pseudo-class {property: value}

（选择符:伪类 {属性: 值}）

伪类和类不同，是CSS已经定义好的，不能象类选择符一样随意用别的名字，根据上面的语法可以解释为对象（选择符）在某个特殊状态下（伪类）的样式。

类选择符及其他选择符也同样可以和伪类混用：

selector.class:pseudo-class {property: value}

（选择符.类:伪类 {属性: 值}）
-------------------------------------
1、CSS星号*选择器
   例如：*{padding:0; ... }
                  这里的“*”号是通配符，即指，网页html中所有标签意思

2、CSS选择器内以(*)星号开头CSS单词
在CSS选择器内星号+CSS样式属性单词，一般区别IE6和IE8、IE6和FF，IE7和IE8，IE7和FF浏览器之间属性CSS HAC
  例如：.divcss5{border:1px solid #000;width:220px;*width:300px;}
                 在IE6和IE7中宽度为300px，而在IE8及以上MSIE版本、谷歌浏览器、火狐(FF)浏览器却显示为220px宽度
------------------------------------------	
                 
9.关于外键：
(1)新建一个外键
alter table t_holiday_list add foreign key (hldy_id) references t_holiday(id);
无论把HolidayListPeer里的“private HolidayPeer holidayPeer;”处的@Cascade(value={CascadeType.???})改为DELETE或DELETE_ORPHAN，
注意：此时public void testRemove() 中的“hlPeer.setHolidayPeer(null);”没有被注释掉（如果注释掉，因为有外键约束，无法删除holidayListPeer）
当调用session.delete(holidayListPeer)时，都不会删除引用的HolidayPeer

(2)删掉外键
ALTER TABLE t_holiday_list DROP FOREIGN KEY t_holiday_list_ibfk_1;
   (1)无论把HolidayListPeer里的“private HolidayPeer holidayPeer;”处的@Cascade(value={CascadeType.???})改为DELETE或DELETE_ORPHAN，
   	     注意：此时public void testRemove() 中的“hlPeer.setHolidayPeer(null);”已经被注释掉
               当调用session.delete(holidayListPeer)时，都会删除引用的HolidayPeer

               
               
------------------------------------------	
10.eclipse的一些快捷键
-------------------------------------
ctrl+m：全屏
ctrl+e:显示打开的文件列表
ctrl+o:显示文件中的方法名列表
alt+shift+r:重命名变量
alt+shift+l:提取字符串中的内容为一个新的变量名
alt+shift+m:从大块的方法中，提取小块代码为新方法              
shift+enter:在当前行下建立一个空行
ctrl+shift+enter:在当前行上建立一个空行
alt+方向键：将当前行上移或下移
ctrl+2，L：为本地变量赋值