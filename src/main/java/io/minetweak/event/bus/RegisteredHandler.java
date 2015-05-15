package io.minetweak.event.bus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Logan Gorence
 */
public class RegisteredHandler {
    private final Object object;
    private final Collection<RegisteredMethod> methods = new ArrayList<RegisteredMethod>();
    private Class<? extends Annotation> annotationType;

    public RegisteredHandler(Object object) {
        this.object = object;
    }

    public RegisteredHandler setAnnotationType(Class<? extends Annotation> annotationType) {
        this.annotationType = annotationType;
        return this;
    }

    public RegisteredHandler registerMethods() {
        for (Method method : object.getClass().getMethods()) {
            if (isEventMethod(method)) {
                methods.add(new RegisteredMethod(method));
            }
        }
        return this;
    }

    private boolean isEventMethod(Method method) {
        return method.isAnnotationPresent(annotationType) && method.getParameterTypes().length == 1;
    }

    public boolean executeEvent(Object event) {
        boolean executed = false;
        for (RegisteredMethod m : methods) {
            if (m.getEventType().isAssignableFrom(event.getClass())) {
                m.invoke(object, event);
                executed = true;
            }
        }
        return executed;
    }

    public Object getObject() {
        return object;
    }
}