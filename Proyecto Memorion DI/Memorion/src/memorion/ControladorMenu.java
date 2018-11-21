/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Sergio
 */
public class ControladorMenu implements ActionListener {

    private Carta carta1 = null;
    private Carta carta2 = null;
    private Vista vista;
    private Timer t;

    public ControladorMenu(Vista vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println(ae.getActionCommand());
        switch (ae.getActionCommand()) {
            case "Easy":
                if (vista.existeContador()) {
                    vista.estadoContador(true);
                    vista.restablecerContador();
                } else {
                    vista.crearContador();
                }
                vista.crearCartas(3, 4);
                break;
            case "Normal":
                if (vista.existeContador()) {
                    vista.estadoContador(true);
                    vista.restablecerContador();
                } else {
                    vista.crearContador();
                }
                vista.crearCartas(4, 5);
                break;
            case "Difficult":
                if (vista.existeContador()) {
                    vista.estadoContador(true);
                    vista.restablecerContador();
                } else {
                    vista.crearContador();
                }
                vista.crearCartas(5, 6);
                break;
            case "": {
                voltearCartas(ae);
                break;
            }
            default:
                System.out.println("Ha habido un problema");
        }
    }

    public void voltearCartas(ActionEvent ae) {
        if (carta1 == null) {
            carta1 = (Carta) ae.getSource();
            if (!carta1.isVolteada()) {
                carta1.voltear();
                carta1.setVolteada(true);
            }
            /*
                
                Enlace transicion:
                http://www.jc-mouse.net/java/crea-un-efecto-flip-en-java-swing
                Enlace imagenes Lazarillo:
                https://sites.google.com/site/luisrc3blit/imagenes
             */

        } else {
            carta2 = (Carta) ae.getSource();
            if (!carta2.isVolteada()) {

                //carta2.repaint();
                carta2.voltear();
                carta2.setVolteada(true);
                comprobarCartasIguales();

            }
        }
    }

    public void comprobarCartasIguales() {
        Timer t = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!carta1.imagenActual().equals(carta2.imagenActual())) {
                    carta1.voltear();
                    carta1.setVolteada(false);
                    carta2.voltear();
                    carta2.setVolteada(false);
                } else {
                    carta1 = null;
                    carta2 = null;
                    if (vista.partidaAcabada()) {
                        System.out.println("Partida Acabada");
                        vista.estadoContador(false);
                    }
                }
                carta1 = null;
                carta2 = null;
            }
        });
        t.start();
        t.setRepeats(false);
       
    }
}
