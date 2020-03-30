package com.xmr.demo.study.demoApplicationContext;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class FilterCustom implements TypeFilter {

    /**
     * metadataReader:读取到的当前正在扫描的类的信息
     * metadataReaderFactory：可以获取到其他类的信息
     * */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前正在扫描的类的类信息（包括实现类啊，继承啊）
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //获取当前类的路劲信息
        Resource resource = metadataReader.getResource();
        String className = classMetadata.getClassName();
        System.out.println("----------->"+className);
        if(className.contains("tro")){
            return true;
        }
        return false;
    }
}
