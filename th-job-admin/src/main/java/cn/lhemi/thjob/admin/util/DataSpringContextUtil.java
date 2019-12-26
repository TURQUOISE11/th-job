package cn.lhemi.thjob.admin.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author tutu11
 * @description SpringContextUtil
 * @date 2019-09-18 10:52:29
 */
@Component
public class DataSpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		DataSpringContextUtil.applicationContext=applicationContext;
	}
	public static <T> T getBean(Class<T> t) {
		return DataSpringContextUtil.applicationContext.getBean(t);
	}
	public static Object getBean(String name) {
		return DataSpringContextUtil.applicationContext.getBean(name);
	}
}
