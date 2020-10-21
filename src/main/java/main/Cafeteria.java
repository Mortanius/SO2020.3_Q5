package main;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Cafeteria implements Runnable {
    //private Queue<Integer> chaves;
    private Semaphore[] chaves;
    private Consumidor[] consumidores;
    private Semaphore chavesDisponiveis;

    public Cafeteria(int consumidores, int banheiros, long iteracoes) {
        this.consumidores = new Consumidor[consumidores];
        for (int c = 0; c < consumidores; c++) {
            this.consumidores[c] = new Consumidor(c, iteracoes, this);
        }
        chaves = new Semaphore[banheiros];
        for (int c = 0; c < banheiros; c++) {
            chaves[c] = new Semaphore(1);
        }
        chavesDisponiveis = new Semaphore(banheiros);
    }

    @Override
    public void run() {
        try {
            for (Consumidor c : consumidores) {
                c.start();
            }
            for (Consumidor c : consumidores) {
                c.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Integer pegarChave() throws InterruptedException {
        chavesDisponiveis.acquire();
        for (int c = 0; c < chaves.length; c++) {
            if (chaves[c].tryAcquire())
                return c;
        }
        return null;
    }

    public void devolverChave(Integer chave) {
        chaves[chave].release();
        chavesDisponiveis.release();
    }

}
