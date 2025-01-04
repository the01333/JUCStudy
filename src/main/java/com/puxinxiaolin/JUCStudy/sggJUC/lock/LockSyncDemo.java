package com.puxinxiaolin.JUCStudy.sggJUC.lock;

class Book extends Object {

}

public class LockSyncDemo {

    Object object = new Object();
    Book b1 = new Book();

    public void m1() {
        synchronized (b1) {
            System.out.println("----hello synchronized code block");
        }
    }

    public synchronized void m2() {
        System.out.println("----hello synchronized code block");
    }

    public static synchronized void m3() {
        System.out.println("----hello static synchronized code block");
    }

    public static void main(String[] args) {

    }
}
