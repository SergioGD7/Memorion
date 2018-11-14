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
public class Contador extends Panel implements Runnable{
    
    private int minutos=0,segundos=0,contadorCartas=0;
    private String min,seg;
    private Label contador;

    Contador() {
        
    }
    
    @Override
    public void run() {
        for(segundos=0;segundos>=0;segundos++){
            try {
                    System.out.println(minutos+":"+segundos);
                    min=Integer.toString(minutos);
                    seg=Integer.toString(segundos);
                    //contador.setText(min+":"+seg);
                    //Thread.sleep(1000);
                    if(segundos==59){
                        minutos=minutos+1;
                        segundos=-1;
                    }
                    if(minutos<10 && segundos>=0 && segundos<10){
                        contador.setText("0"+min+":0"+seg);
                    }else if(minutos<10){
                        contador.setText("0"+min+":"+seg);
                    }else{
                        contador.setText(min+":"+seg);
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Error");
                }
            
        }
        setVisible(true);
    }
    
}
