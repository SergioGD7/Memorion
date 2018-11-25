/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorion;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author Sergio
 */
public class Memorion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SplashScreen s=new SplashScreen();
        s.setVisible(true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Memorion.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.dispose();
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                //Aquí crean el objeto hacía su aplicación, para hacer visible
                System.out.println("Hola: Bienvenido a la aplicación...");
            }
        });
        Vista v = new Vista();
        //Thread t=new Thread(new Contador());
        //t.start();

    }
    
    
}
