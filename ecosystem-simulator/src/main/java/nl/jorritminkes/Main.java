package nl.jorritminkes;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Random rand = new Random();
        int value = rand.nextInt(4);
        System.out.println(value);
    }
}