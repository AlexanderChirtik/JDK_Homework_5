package org.example;

public class Philosopher implements Runnable{

    private Fork leftFork;
    private Fork rightFork;

    public Philosopher(Fork leftFork, Fork rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (true) {
                doAction(System.nanoTime() + ": Философ размышляет");
                if (!leftFork.isForkTaken() && !rightFork.isForkTaken()) {
                    // гарантируем, что только этот поток будет работать с вилкой, расположенной слева
                    synchronized (leftFork) {
                            leftFork.setForkTaken(true);
                            // гарантируем, что только этот поток будет работать с вилкой, расположенной справа
                            synchronized (rightFork) {
                                rightFork.setForkTaken(true);
                                doAction(System.nanoTime() + ": Взял обе вилки и ест");
                                doAction(System.nanoTime() + ": Положил обе вилки и продолжил размышлять");
                                leftFork.setForkTaken(false);
                                rightFork.setForkTaken(false);
                                Thread.sleep(500);  // поле еды философ делает паузу перед следующим блюдом
                            }
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Метод выводит в консоль текст действия со временем этого действия. После действия выдерживается пауза.
     * @param action текст действия философа
     * @throws InterruptedException
     */
    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep((int) (Math.random() * 100));
    }
}
