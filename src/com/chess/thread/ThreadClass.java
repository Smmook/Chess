package com.chess.thread;

public class ThreadClass implements Runnable {

    @Override
    public void run() {
        System.out.println("Esto es otro thread");
    }
}
