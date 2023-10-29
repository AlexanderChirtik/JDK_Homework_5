package org.example;

/**
 * Класс Fork обозначает вилку с одной boolean переменной forkTaken (взята вилка кем-нибудь или нет)
 */
public class Fork {

    private boolean forkTaken;

    public void setForkTaken(boolean forkTaken) {
        this.forkTaken = forkTaken;
    }

    public boolean isForkTaken() {
        return forkTaken;
    }
}
