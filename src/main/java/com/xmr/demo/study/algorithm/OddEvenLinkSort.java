package com.xmr.demo.study.algorithm;

public class OddEvenLinkSort {

    static class Node<V>{

        private final V value;

        private Node next;

        Node(V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }

        Node(V value) {
            this.value = value;
            this.next = null;
        }

        public final V getValue(){
            return this.value;
        }

        public final String toString(){
            return value + "";
        }
    }

    private static Node OddEvenGroup(Node line){
        if(line == null)
            return null;
        Node oddNode = line;
        if(line.next == null)
            return line;
        Node evenNode = line.next;
        Node p = oddNode;
        Node t = evenNode;
        while(p.next!=null && t.next!=null){
            p.next=t.next;
            p=t.next;
            t.next=p.next;
            t=p.next;
        }
        return mergeLine(oddNode,evenNode);
    }

    private static Node reversal(Node evenNode){
        if(evenNode==null || evenNode.next ==null)
            return evenNode;
        Node currentNode = evenNode;//当前节点
        Node newNode = null;//新的链表
        Node temNode;//临时链表
        while(currentNode!=null){
            temNode = currentNode.next;//先把当前节点后得数据存入临时表
            currentNode.next = newNode;//把新链表写入当前节点得子节点
            newNode = currentNode;//把写入好的链表赋值给新链表
            currentNode = temNode;//把临时表得数据作为下一个链表得循环
        }
        return newNode;//返回新链表
    }

    private static Node mergeLine(Node<Integer> oddNode,Node<Integer> evenNode){
        evenNode = reversal(evenNode);
        Node newNode = null;
        Node temNode = null;
        while(oddNode!=null && evenNode!=null){
            if(oddNode.value<evenNode.value){
                temNode = oddNode.next;
                oddNode.next = newNode;
                newNode = oddNode;
                oddNode = temNode;
            }else{
                temNode = evenNode.next;
                evenNode.next = newNode;
                newNode = evenNode;
                evenNode = temNode;
            }
        }
        if(oddNode == null){
            evenNode.next = newNode;
            newNode = evenNode;
        }
        if(evenNode == null){
            oddNode.next = newNode;
            newNode = oddNode;
        }
        while(newNode !=null){
            System.out.println(newNode.value);
            newNode = newNode.next;
        }
        return null;
    }

    public static void main(String[] args){
        Node<Integer> node9 = new Node<>(9);
        Node<Integer> node8 = new Node<>(2,node9);
        Node<Integer> node7 = new Node<>(7,node8);
        Node<Integer> node6 = new Node<>(4,node7);
        Node<Integer> node5 = new Node<>(5,node6);
        Node<Integer> node4 = new Node<>(6,node5);
        Node<Integer> node3 = new Node<>(3,node4);
        Node<Integer> node2 = new Node<>(8,node3);
        Node<Integer> node1 = new Node<>(1,node2);
        OddEvenGroup(node1);
    }
}