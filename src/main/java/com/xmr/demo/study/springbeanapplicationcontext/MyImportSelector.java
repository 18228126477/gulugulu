package com.xmr.demo.study.springbeanapplicationcontext;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义逻辑返回需要的组件
 * */
public class MyImportSelector implements ImportSelector {

    /**
     * 返回值就是需要导入得组件全类名
     * */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.xmr.demo.study.demoapplicationcontext.PersonDao"};
    }
}
