package org.derryfield.isp2025.mlswebsite.util;

import org.hibernate.proxy.HibernateProxy;

public class HibernateUtils {
    public static <T> T unproxy(T entity) {
    if (entity instanceof HibernateProxy proxy) {
        return (T) proxy.getHibernateLazyInitializer().getImplementation();
    }
    return entity;
}
}
