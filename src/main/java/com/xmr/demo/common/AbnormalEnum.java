package com.xmr.demo.common;


public enum AbnormalEnum {
    //
    ONE("类未找到"),
    //
    TWO("包路径未找到"),
    //
    THREE("文件转义失败"),
    //
    FOUR("jar转义失败"),
    //
    FIVE("文件协议出错"),
    //
    SIX("类无法加载异常:通常是该类不存在"),
    //
    SEVEN("当前文件路径无法找到该文件"),
    //
    EIGHT("自定义路径过滤器初始化成功");

    private String desc;
    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
    AbnormalEnum(String desc){
        this.desc=desc;
    }

    /**
     * 定义方法,返回描述,跟常规类的定义没区别
     * @return
     */
    public String getDesc(){
        return desc;
    }
}
