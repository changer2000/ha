import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.etech.ha.peer.AttendanceStatusPeer;
//import com.etech.validator.constraints.FilterEmptyBean;

import filter.empty.bean.LoginForm;


public class A {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		LoginForm frm = new LoginForm();
		frm.getAtdncList().add(new AttendanceStatusPeer());
		frm.getAtdncList().add(new AttendanceStatusPeer());
		frm.getAtdncList().add(new AttendanceStatusPeer());
		
		frm.getAtdncList().get(0).setId(new Long(0));
		frm.getAtdncList().get(0).setAtndnc_name("name0");
		
		frm.getAtdncList().get(2).setId(new Long(1));
		frm.getAtdncList().get(2).setAtndnc_name("name2");
		
		LoginForm subFrm1 = new LoginForm();
		frm.getFrmList().add(subFrm1);
		subFrm1.getAtdncList().add(new AttendanceStatusPeer());
		subFrm1.getAtdncList().add(new AttendanceStatusPeer());
		subFrm1.getFrmList().add(new LoginForm());
		subFrm1.getFrmList().add(new LoginForm());
		
		LoginForm subFrm2 = new LoginForm();
		frm.getFrmList().add(subFrm2);
		subFrm2.getAtdncList().add(new AttendanceStatusPeer());
		subFrm2.getAtdncList().add(new AttendanceStatusPeer());
		//subFrm2.getAtdncList().get(0).setId(new Long(1));
		//subFrm2.getAtdncList().get(0).setAtndnc_name("name1");
		subFrm2.getFrmList().add(new LoginForm());
		subFrm2.getFrmList().add(new LoginForm());
		
		A a = new A();
		a.filterEmptyList(frm);
		
		System.out.println(frm.getAtdncList().size());
	}
	
	

	
	public void filterEmptyList(Object target) throws Exception {
		System.out.println(List.class.getName());
		/*
		PropertyDescriptor[] pdAry = BeanUtils.getPropertyDescriptors(target.getClass());
		if (pdAry!=null) {
			for (PropertyDescriptor pd : pdAry) {
				System.out.println("name=" + pd.getName() + ",value=" + pd.getValue(pd.getName()) + ",class=" + pd.getClass() + ",propertyType=" + pd.getPropertyType().getName());
				
				if (pd.getPropertyType().isAssignableFrom(List.class)) {
					System.out.println("is List.");
					//List<Object> list = (List) 
					//org.apache.commons.beanutils.BeanUtils.
					
				} else if (pd.getPropertyType().getName().equals(List.class.getName())) {
					System.out.println("equal List.");
				}
			}
		}
		*/
		System.out.println("--------------------");
		Class cls = target.getClass();
		Field[] fa = target.getClass().getDeclaredFields(); 
        Method[] methods = target.getClass().getDeclaredMethods();
		if (fa!=null) {
			String FilterEmptyBeanName = FilterEmptyBean.class.getName();
			for (Field f : fa) {
				System.out.println("name="+f.getName() + ",type=" + f.getType());
				
				boolean isNeedFilterEmptyBean = false;
				Annotation[] as = f.getAnnotations();
				if (as!=null && as.length>0) {
					for (Annotation a : as) {
						System.out.println("annotation name:" + a.annotationType().getName());
						if (FilterEmptyBeanName.equals( a.annotationType().getName())) {
							isNeedFilterEmptyBean = true;
							System.out.println(">>> found.");
							break;
						}
					}
				}
				if (isNeedFilterEmptyBean && f.getType().isAssignableFrom(List.class)) {
					String fieldGetName = parGetName(f.getName());
					if (!checkGetMet(methods, fieldGetName)) {  
	                    continue;  
	                }
					Method fieldGetMet = cls.getMethod(fieldGetName);
					List list = (List) fieldGetMet.invoke(target, new Class[] {});
					if (list!=null && list.size()>0) {
						for (int i=list.size()-1; i>=0; i--) {
							Object o = list.get(i);
							if (isEmpty(o)) {
								list.remove(i);
							}
						}
					}
				}
			}
		}
	}
	
	public static boolean isEmpty(Object target) throws Exception {
		boolean rs = true;
		
		Class cls = target.getClass();
		Field[] fa = cls.getDeclaredFields();
		Method[] methods = cls.getDeclaredMethods();
		if (fa!=null && fa.length>0) {
			for (Field f : fa) {
				String fieldGetName = parGetName(f.getName());
				if (checkGetMet(methods, fieldGetName)) {
					Method fieldGetMet = cls.getMethod(fieldGetName);
					Object o = fieldGetMet.invoke(target, new Class[] {});
					if (o!=null) {
						if (f.getType().isAssignableFrom(List.class)) {
							List list = (List) o;
							Annotation[] as = f.getAnnotations();
							if (as!=null && as.length>0) {
								boolean isNeedFilterEmptyBean = false;
								for (Annotation a : as) {
									if (a.annotationType().equals(FilterEmptyBean.class)) {
										isNeedFilterEmptyBean = true;
										break;
									}
								}
								if (isNeedFilterEmptyBean) {
									for (int i=list.size()-1; i>=0; i--) {
										Object o2 = list.get(i);
										if (isEmpty(o2)) {
											list.remove(i);
										}
									}
								}
							}
							
							if (list.size()>0)
								rs = false;
						} else {
							rs = false;	//not empty
						}
					}
				}
			}
		}
		
		return rs;
	}
	
	/** 
     * 拼接某属性的 get方法 
     * @param fieldName 
     * @return String 
     */  
    public static String parGetName(String fieldName) {  
        if (null == fieldName || "".equals(fieldName)) {  
            return null;  
        }  
        return "get" + fieldName.substring(0, 1).toUpperCase()  
                + fieldName.substring(1);  
    }  
  
    /** 
     * 拼接在某属性的 set方法 
     * @param fieldName 
     * @return String 
     */  
    public static String parSetName(String fieldName) {  
        if (null == fieldName || "".equals(fieldName)) {  
            return null;  
        }  
        return "set" + fieldName.substring(0, 1).toUpperCase()  
                + fieldName.substring(1);  
    }
    /** 
     * 判断是否存在某属性的 set方法 
     * @param methods 
     * @param fieldSetMet 
     * @return boolean 
     */  
    public static boolean checkSetMet(Method[] methods, String fieldSetMet) {
    	if (methods==null || methods.length==0)
    		return false;
    	
        for (Method met : methods) {  
            if (fieldSetMet.equals(met.getName())) {  
                return true;  
            }  
        }  
        return false;  
    }  
  
    /** 
     * 判断是否存在某属性的 get方法 
     * @param methods 
     * @param fieldGetMet 
     * @return boolean 
     */  
    public static boolean checkGetMet(Method[] methods, String fieldGetMet) {
    	if (methods==null || methods.length==0)
    		return false;
    	
        for (Method met : methods) {  
            if (fieldGetMet.equals(met.getName())) {  
                return true;  
            }  
        }  
        return false;  
    } 
}
