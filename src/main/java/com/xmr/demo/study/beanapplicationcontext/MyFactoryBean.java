package com.xmr.demo.study.beanapplicationcontext;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<ImportFactoryBean> {

    @Override
    public ImportFactoryBean getObject() throws Exception {
        return new ImportFactoryBean();
    }

    @Override
    public Class<?> getObjectType() {
        return ImportFactoryBean.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
