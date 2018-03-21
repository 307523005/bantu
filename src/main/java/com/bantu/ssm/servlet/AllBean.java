package com.bantu.ssm.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * 写的工具类为AllBean，实现ApplicationContextAware接口，并加入Component注解，让spring扫描到该bean
 * @author Administrator
 *
 */
@Component
public class AllBean implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	   @Override
	public void setApplicationContext(ApplicationContext context) {
		if (AllBean.applicationContext == null) {
			AllBean.applicationContext = context;
		}

		System.out
				.println("---------------------------------------------------------------------");
		System.out
				.println("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象,applicationContext="
						+ AllBean.applicationContext + "========");
		System.out
				.println("---------------------------------------------------------------------");
	}

	// 通过name获取 Bean.
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	// 获取applicationContex
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}