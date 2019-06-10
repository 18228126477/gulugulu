package com.xmr.demo.algorithm;

public class TrieFilter {

    private Node head;

    public TrieFilter(){
        this.head = new Node(' ');
    }

    public void insert(String word){
        if(word.equals(search(word))) return;
        Node node = head;
        for(int i = 0; i<word.length(); i++){
            Node child = node.subNode(word.charAt(i));
            if(child != null){
                node = child;
            }else{
                node.addChild(new Node(word.charAt(i)));
                node = node.subNode(word.charAt(i));
            }
        }
        node.setLeaf(true);
    }

    public String search(String word){
        Node node = this.head;
        StringBuffer str = new StringBuffer();
        for(int i = 0; i<word.length(); i++){
            if(node.isLeaf()) return str.toString();
            if(word.charAt(i) == ' ' || word.charAt(i) == ','){
                str.append(word.charAt(i));
                continue;
            }
            if(node.subNode(word.charAt(i)) == null) return "";
            node = node.subNode(word.charAt(i));
            str.append(word.charAt(i));
        }
        if(node.isLeaf()) return str.toString();
        else return "";
    }

    public String searchFilter(String article){
        String str = "";
        String articleBackups = article;
        for(int i = 0; i<article.length(); i++){
            str = search(article.substring(i));
            if(!"".equals(str)){
                articleBackups=articleBackups.replace(str,"**");
            }
            i += str.length();
        }
        return articleBackups;
    }

    public static void main(String[] args){
        TrieFilter trieFilter = new TrieFilter();
        long a= System.nanoTime();
        trieFilter.insert("白痴");
        trieFilter.insert("猪");
        trieFilter.insert("插座");
        trieFilter.insert("脸就");
        trieFilter.insert("看你");
        trieFilter.insert("知道");
        trieFilter.insert("机似");
        trieFilter.insert("部位克");
        String s = trieFilter.searchFilter("你左看像白, 痴，右看像傻子，上看像头猪，下看像头驴，" +
                "脸型跟电视机似的，不知道看你脸就像电视机配的插座，男人看到你就阳痿，你不是从你妈那部位生的，" +
                "你是你妈那部位克隆来的。");
        System.out.print("程序执行时间为：");
        System.out.println(System.nanoTime()-a+"毫秒");
        System.out.println(s);
        long b= System.nanoTime();
        String s1 = ("你左看像白,痴，右看像傻子，上看像头猪，下看像头驴，" +
                "脸型跟电视机似的，不知道看你脸就像电视机配的插座，男人看到你就阳痿，你不是从你妈那部位生的，" +
                "你是你妈那部位克隆来的。").replace("白痴", "**");
        String s2 = s1.replace("猪", "**");
        String s3 = s2.replace("插座", "**");
        String s4 = s3.replace("脸就", "**");
        String s5 = s4.replace("看你", "**");
        String s6 = s5.replace("知道", "**");
        String s7 = s6.replace("机似", "**");
        String s8 = s7.replace("部位克", "**");
        String s9 = s8.replace("妈那", "**");
        String s10 = s9.replace("你妈", "**");
        String s11 = s10.replace("阳痿", "**");
        System.out.print("程序执行时间为：");
        System.out.println(System.nanoTime()-b+"毫秒");
        System.out.println(s11);
    }
}
