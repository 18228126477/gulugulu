package com.xmr.demo.study.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TrieFilter {

    private Node head;

    public TrieFilter(){
        this.head = new Node(' ');
    }

    private void insert(String word){
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

    private String searchFilter(String article){
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

    public static String charactersFilter (String str){
        TrieFilter trieFilter = new TrieFilter();
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            read = new InputStreamReader(TrieFilter.class.getClassLoader().getResourceAsStream("CensorWords.txt"),"UTF-8");
            bufferedReader = new BufferedReader(read);
            for(String txt = null;(txt = bufferedReader.readLine()) != null;){
                trieFilter.insert(txt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trieFilter.searchFilter(str);
    }

    public static void main(String[] args){
        System.out.println(charactersFilter("mmp"));
    }
}
