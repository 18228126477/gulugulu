package com.xmr.demo.algorithm;

import java.io.Serializable;

public class HashTable<K,V> implements Serializable {

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    Node<K,V>[] table;

    static class Node<K,V>{
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public V put(K key, V value) {
        return putVal(key,value);
    }


    final V putVal( K key, V value){
        if(value == null){
            throw new NullPointerException();
        }
        Node<K,V>[] tab = table;
        int hash = key.hashCode();
        int index = 11;
        Node<K,V> node = tab[index];
        for(;node != null;node = node.next){
            if((node.hash==hash) && node.key.equals(key)){
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }
        return null;
    }
}
