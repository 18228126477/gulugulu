package com.xmr.demo.untils;

import com.xmr.demo.common.AbnormalEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class CustomAnnotationScan {

    private static final Logger logger = LoggerFactory.getLogger(CustomAnnotationScan.class);

    public static List<Class<?>> getClassesWithAnnotationFromPackage(String packageName, Class<? extends Annotation> annotation) {
        List<Class<?>> classList = new ArrayList<>();
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs = null;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);//获取包下的路劲
        } catch (IOException e) {
            logger.error(packageName + AbnormalEnum.TWO.getDesc() + e.getMessage());
        }
        while (dirs.hasMoreElements()) {
            URL url = dirs.nextElement();
            String protocol = url.getProtocol();//获取协议或者类型
            if ("file".equals(protocol)) {
                String filePath = null;
                try {
                    filePath = URLDecoder.decode(url.getFile(), "UTF-8");//对路径进行转义
                } catch (IOException e) {
                    logger.error(AbnormalEnum.THREE.getDesc()+ e.getMessage());
                }
                if(null != filePath)
                    filePath = filePath.substring(1);
                getClassesWithAnnotationFromFilePath(packageName,filePath,classList,annotation);
            } else if ("jar".equals(protocol)) {
                JarFile jar = null;
                try {
                    jar = ((JarURLConnection) url.openConnection()).getJarFile();//扫描jar包文件 并添加到集合中
                } catch (IOException e) {
                    logger.error(AbnormalEnum.FOUR.getDesc()+ e.getMessage());
            }
                List<Class<?>> alClassList = new ArrayList<>();
                if(null != jar){
                    findClassesByjar(packageName,jar,alClassList);
                    getClassesWithAnnotationFromAllClasses(alClassList,annotation,classList);
                }
            } else {
                logger.error(protocol + AbnormalEnum.FIVE.getDesc());
            }
        }
        return classList;
    }

    private static void findClassesByjar(String pkgName, JarFile jar, List<Class<?>> classes){
        String pkgDir = pkgName.replace(".", "/");
        Enumeration<JarEntry> entry = jar.entries();
        while (entry.hasMoreElements()){
            JarEntry jarEntry = entry.nextElement();
            String name = jarEntry.getName();
            // 如果是以/开头的
            if (name.charAt(0) == '/') {
                // 获取后面的字符串
                name = name.substring(1);
            }
            if (jarEntry.isDirectory() || !name.startsWith(pkgDir) || !name.endsWith(".class")) {
                continue;
            }
            //如果是一个.class文件 而且不是目录
            // 去掉后面的".class" 获取真正的类名
            String className = name.substring(0, name.length() - 6);
            Class<?> aClass = loadClass(className.replace("/", "."));
            classes.add(aClass);
        }
    }

    private static Class<?> loadClass(String fullClsName ) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(fullClsName );
        } catch (ClassNotFoundException e) {
            logger.error(fullClsName + AbnormalEnum.SIX.getDesc()+ e.getMessage());
        }
        return null;
    }

    private static void getClassesWithAnnotationFromFilePath(String packageName, String filePath, List<Class<?>> classList,Class<? extends Annotation> annotation) {
        Path dir = Paths.get(filePath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for(Path path:stream){
                String fileName = String.valueOf(path.getFileName());
                boolean isDir = Files.isDirectory(path);
                if(isDir) {
                    getClassesWithAnnotationFromFilePath(packageName + "." + fileName , path.toString(), classList, annotation);
                }else{
                    String className = fileName.substring(0, fileName.length() - 6);
                    Class<?> classes = null;
                    String fullClassPath = packageName + "." + className;
                    try{
                        classes  = Thread.currentThread().getContextClassLoader().loadClass(fullClassPath);
                    }catch (ClassNotFoundException e){
                        logger.error(fullClassPath + AbnormalEnum.SIX.getDesc()+ e.getMessage());
                    }
                    if(null != classes && null != classes.getAnnotation(annotation)){
                        classList.add(classes);
                    }
                }
            }
        }catch (IOException e){
            logger.error(filePath + AbnormalEnum.SEVEN.getDesc()+ e.getMessage());
        }
    }

    private static void getClassesWithAnnotationFromAllClasses(List<Class<?>> inClassList,Class<? extends Annotation> annotation, List<Class<?>> outClassList) {
        for(Class<?> myClass : inClassList) {
            if (null != myClass && null != myClass.getAnnotation(annotation)) {
                outClassList.add(myClass);
            }
        }
    }
}
