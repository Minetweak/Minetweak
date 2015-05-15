package io.minetweak.event.bus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Logan Gorence
 */
public class RegisteredMethod {
    private final Method method;

    public RegisteredMethod(Method method) {
        this.method = method;
    }

    public void invoke(Object instance, Object event) {
        try {
            method.invoke(instance, event);
        } catch (IllegalAccessException ignored) {
        } catch (InvocationTargetException ignored) {
        }
    }

    public Class<?> getEventType() {
        return method.getParameterTypes()[0];
    }
}