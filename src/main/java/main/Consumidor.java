package main;

import java.util.Random;

public class Consumidor extends Thread {
    private int id;
    private long iteracoes;
    private Cafeteria cafeteria;

    private final int SLEEP_MIN = 2000;
    private final int SLEEP_MAX = 10000;
    private Random rng = new Random();

    private Integer chave;

    public Consumidor(int id, long iteracoes, Cafeteria cafeteria) {
        this.id = id;
        this.iteracoes = iteracoes;
        this.cafeteria = cafeteria;
    }

    @Override
    public void run() {
        try {
            loop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loop() throws InterruptedException {
        entrar();
        for (long c = 0; c < iteracoes; c++) {
            beberCafe();
            pegarChave();
            usarBanheiro();
            devolverChave();
        }
        sair();
    }

    private void entrar() {
        System.out.println("Consumidor " + id + " entrou na cafeteria");
    }

    private void sair() {
        System.out.println("Consumidor " + id + " deixa a cafeteria");
    }

    private void beberCafe() throws InterruptedException {
        System.out.println("Consumidor " + id + " esta tomando cafe");
        Thread.sleep(SLEEP_MIN + rng.nextInt(SLEEP_MAX - SLEEP_MIN + 1));
    }

    private void usarBanheiro() throws InterruptedException {
        System.out.println("Consumidor " + id + " esta usando o banheiro " + chave);
        Thread.sleep(SLEEP_MIN + rng.nextInt(SLEEP_MAX - SLEEP_MIN + 1));
    }

    private void pegarChave() throws InterruptedException {
        chave = cafeteria.pegarChave();
    }

    private void devolverChave() {
        cafeteria.devolverChave(chave);
    }
}
