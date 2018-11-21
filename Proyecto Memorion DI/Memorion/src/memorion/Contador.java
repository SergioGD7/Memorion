/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorion;

import java.awt.Label;
import java.awt.Panel;

/**
 *
 * @author Sergio
 */
public class Contador extends Thread {

    private Vista v;
    private int minutos = 0, segundos = 0, contadorCartas = 0;
    private String min, seg;
    private String contador;
    private boolean contar=true;

    Contador(Vista v) {
        this.v = v;
        this.start();
    }

    @Override
    public void run() {
        while (true) {

            if(contar){
            System.out.println(minutos + ":" + segundos);
            min = Integer.toString(minutos);
            seg = Integer.toString(segundos);
            if (segundos == 59) {
                minutos = minutos + 1;
                segundos = -1;
            }
            if (minutos < 10 && (segundos == 0 || segundos < 10)) {
                contador = "0" + min + ":0" + seg;
            } else if (minutos < 10) {
                contador = "0" + min + ":" + seg;
            } else {
                contador = min + ":" + seg;
            }
            v.actualizarMarcador(contador);          
            segundos++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Error");
            }
            }
        }       
    }

    public void restablecerContador() {
        minutos = 0;
        segundos = 0;
    }

    public void setContar(boolean contar) {
        this.contar = contar;
    }

}
