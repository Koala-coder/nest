package com.ywkj.nest.ddd.builder;

import com.ywkj.nest.ddd.EntityObject;

import java.lang.reflect.Modifier;

/**
 * 用于仓储预加载时的实体构建器
 *
 * @param <T>
 */
 class PreLoadBuilder<T extends EntityObject> implements IBuilder<T> {
    Class<T> tClass;

    public PreLoadBuilder(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public T build(String id) {
        if (Modifier.isAbstract(tClass.getModifiers()))  throw new RuntimeException(tClass+"是一个抽象类");
        T t = EntityObjectFactory.createForLoad(tClass);
        t.setId(id);
        return t;
    }

    @Override
    public <U extends T> U build(Class<U> uClass, String id) {
        U u = EntityObjectFactory.createForLoad(uClass);
        u.setId(id);
        return u;
    }
}