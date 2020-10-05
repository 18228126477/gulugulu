package com.xmr.demo;

public class PrivateScope {

    private void f() {
        System.out.println("PrivateScope f()");
    }

    public static void main(String[] args) {
        PrivateScope p = new PrivateOverride();
        p.f();
    }
}

class PrivateOverride extends PrivateScope {

    private void f() {
        System.out.println("PrivateOverride f()");
    }
}