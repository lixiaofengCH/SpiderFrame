package cn.aidou.aop;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * A factory for creating Proxy objects.
 * @author Debadatta Mishra
 */
public class ProxyFactory {

	/**
	 * Gets the proxy.
	 *
	 * @param targetObject 目标对象
	 * @param handlers 处理类集合
	 * @return Object 对象
	 */
	public static Object getProxy(Object targetObject,
			List<AbstractHandler> handlers) {
		Object proxyObject = null;
		if (handlers.size() > 0) {
			proxyObject = targetObject;
			for (int i = 0; i < handlers.size(); i++) {
				handlers.get(i).setTargetObject(proxyObject);
				proxyObject = Proxy.newProxyInstance(targetObject.getClass()
						.getClassLoader(), targetObject.getClass()
						.getInterfaces(), handlers.get(i));
			}
			return proxyObject;
		} else {
			return targetObject;
		}
	}
}
