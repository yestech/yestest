package org.yestech.test;

import java.lang.reflect.Field;

/**
 * @author A.J. Wright
 */
public final class InjectionUtil {

    private InjectionUtil() {
    }

    public static void inject(String field, Object o, Object value) throws Exception {
        Field field1 = o.getClass().getField(field);
        field1.setAccessible(true);
        field1.set(o, value);
    }

}
