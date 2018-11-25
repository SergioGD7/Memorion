/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorion;

import java.awt.Label;
import java.awt.Panel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio
 */
public class Contador extends Thread {

    private Vista v;
    private int minutos = 0, segundos = 0;
    private String min, seg;
    private String contador;
    private boolean contar = true;

    Contador(Vista v) {
        this.v = v;
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            
            synchronized(this){
                while (!contar) {
                    try {
                        wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            }
            
            //reanudarContador();
            System.out.println(minutos + ":" + segundos);
            min = Integer.toString(minutos);
            seg = Integer.toString(segundos);
            if (segundos == 59) {
                minutos = minutos + 1;
                segundos = -1;
            }
            if (minutos < 10 && segundos >= 0 && segundos < 10) {
                contador = "0" + min + ":0" + seg;
            } else if (minutos < 10 ) {
                contador = "0" + min + ":" + seg;
            } else {
                contador = min + ":" + seg;
            }
            v.actualizarMarcador(contador);
            System.out.println(contador);
            segundos++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Error");
            }

        }

    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }
    

    public String getContador() {
        return contador;
    }

    public void restablecerContador() {
        minutos = 0;
        segundos = 0;
    }

    public synchronized void controlarContador(boolean contar) {
        this.contar = contar;
        notify();
    }
/*
    public synchronized void paraContador() {
        contar=false;
        notify();
    }

    public synchronized void reanudarContador() {
        contar=true;
        notify();
        
    }*/

}
