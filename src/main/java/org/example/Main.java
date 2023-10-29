package org.example;

public class Main {
    public static void main(String[] args) {

        Philosopher[] philosophers = new Philosopher[5];
        Fork[] forks = new Fork[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % forks.length];

            // Чтобы нарушить циклицескую взаимоблокировку, последний по списку философ сначала
            // берется за правую вилку. Все остальные философы берут сначала левую вилку.
            if (i == philosophers.length - 1) {
                philosophers[i] = new Philosopher(rightFork, leftFork);
            } else {
                philosophers[i] = new Philosopher(leftFork, rightFork);
            }

            Thread thread = new Thread(philosophers[i], "Философ " + (i + 1));
            thread.start();
        }
    }
}