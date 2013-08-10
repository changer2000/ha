package com.etech.system.bean;

import java.io.Serializable;

public class BaseDomain implements Serializable {
	
	
	private static final long serialVersionUID = 7897062602533451861L;
	/*
	 * 由于把root-config.xml的<bean id="sessionFactory" class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
	 * 改成了<bean id="sessionFactory" class="org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean">，
	 * 想不用写***.hbm.xml文件。
	 * 
	 * 结果导致一定要在基类里定义的id不能用，以后只好在每个子类里面分别定义id了。
	 * 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	*/
	
	//将来所有的peer里都要定义自己的LOGIC_KEYS，以方便按逻辑主键进行的相关操作
	public static String[] LOGIC_KEYS=new String[]{"id"};
	
}
