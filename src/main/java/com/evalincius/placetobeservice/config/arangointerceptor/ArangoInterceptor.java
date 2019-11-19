package com.evalincius.placetobeservice.config.arangointerceptor;

import com.arangodb.ArangoDatabase;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.function.Supplier;

public class ArangoInterceptor implements MethodInterceptor, InvocationHandler, Supplier {
    private Object target;
    private Supplier supplier;

    public ArangoInterceptor(Class<?> arangoDBClass, Object target, Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return ReflectionUtils.invokeMethod(method, supplier.get(),  objects);
    }

    @Override
    public Object invoke(Object thisProxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getReturnType());
        if (method.getName().equals("db")) {
            return createArangoObjectProxy(method.getReturnType(), thisProxy, new ProxyTargetSupplier(this, method, args));
        }

        return ReflectionUtils.invokeMethod(method, supplier.get(),  args);
    }

    private Object createArangoObjectProxy(Class<?> clazz, Object parentProxy, ProxyTargetSupplier proxyTargetSupplier) {
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new ArangoInterceptor(clazz, parentProxy, proxyTargetSupplier));
    }

    private Object getTarget(){
        return this.target;
    }

    private Object createTarget(){
        return supplier.get();
    }

    private Object createTargetIfNotExist(){
        if (this.target == null) {
            this.target = createTarget();
        }
        return this.target;
    }

    @Override
    public Object get() {
        return createTargetIfNotExist();
    }

    private class ArangoDatabaseSupplier  implements Supplier<ArangoDatabase> {
        @Override
        public ArangoDatabase get() {
            return (ArangoDatabase) supplier.get();
        }
    }
}
