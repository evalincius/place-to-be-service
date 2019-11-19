package com.evalincius.placetobeservice.config.arangointerceptor;

import java.lang.reflect.Method;
import java.util.function.Supplier;

public class ProxyTargetSupplier implements Supplier {
    private Supplier targetSupplier;
    private Method method;
    private Object[] args;
    public ProxyTargetSupplier(Supplier targetSupplier, Method method, Object[] args){
        this.targetSupplier = targetSupplier;
        this.method = method;
        this.args = args;
    }
    @Override
    public Object get() {
        try{
            return this.method.invoke(this.targetSupplier.get(), this.args);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create proxy object", e);
        }
    }
}
