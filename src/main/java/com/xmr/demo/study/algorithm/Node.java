package com.xmr.demo.study.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private char root;
    private List<Node> childList;
    private boolean isLeaf;

    public Node(char root){
        this.root = root;
        childList = new ArrayList<>();
        isLeaf = false;
    }

    public Node subNode(char root){
        if(childList != null){
            for(Node child: childList){
                if(child.root == root){
                    return child;
                }
            }
        }
        return null;
    }

    public void setLeaf(boolean leaf){
        this.isLeaf = leaf;
    }

    public boolean isLeaf(){
        return this.isLeaf;
    }

    public void addChild(Node node){
        this.childList.add(node);
    }
}
