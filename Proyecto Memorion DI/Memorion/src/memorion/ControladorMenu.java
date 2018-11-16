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

/**
 *
 * @author Sergio
 */
public class ControladorMenu implements ActionListener{
    
    private Carta carta1 = null;
    private Carta carta2 = null;
    private Vista vista;
    
    public ControladorMenu(Vista vista){
        this.vista=vista;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println(ae.getActionCommand());
        switch (ae.getActionCommand()) {
            case "Fácil":
                if (vista.existeContador()) {
                    vista.restablecerContador();
                }else{
                vista.crearContador();
                }
                vista.crearCartas(3, 4);
                break;
            case "Normal":
                if (vista.existeContador()) {
                    vista.restablecerContador();
                }else{
                vista.crearContador();
                }
                vista.crearCartas(4, 5);
                break;
            case "Difícil":
                if (vista.existeContador()) {
                    vista.restablecerContador();
                }else{
                vista.crearContador();
                }
                vista.crearCartas(5, 6);
                break;
            case "": {
                if (carta1 == null) {
                    carta1 = (Carta) ae.getSource();
                    if (!carta1.isVolteada()) {
                        carta1.voltear();
                        carta1.setVolteada(true);
                    }
                    /*
                Hay que añadir otro if para comprobar si la imagen de la carta 1 volteada coincide con la imagen de la carta 2 volteada y si esto es asi
                no se hara nada en caso contrario se volveran a poner ambas a false y  a darles la vuelta.
                Enlace transicion:
                http://www.jc-mouse.net/java/crea-un-efecto-flip-en-java-swing
                Enlace imagenes Lazarillo:
                https://sites.google.com/site/luisrc3blit/imagenes
                     */

                } else {
                    carta2 = (Carta) ae.getSource();
                    if (!carta2.isVolteada()) {
                        
                        System.out.println("Hikkajsdkhwkegfgwlvigehocuoyuftikryt9ysrkt9rsjyt9j");
                        //carta2.repaint();
                        try {
                            carta2.voltear();
                        carta2.setVolteada(true);
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (!carta1.imagenActual().equals(carta2.imagenActual())) {
                            /* try {
                               Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);
                            
                        carta1.voltear();
                        carta1.setVolteada(false);
                        carta2.voltear();
                        carta2.setVolteada(false);
                             */
                            carta1.voltear();
                            carta1.setVolteada(false);
                            carta2.voltear();
                            carta2.setVolteada(false);
                            System.out.println("hhhfhj");
                        }
                        carta1 = null;
                        carta2 = null;
                    }
                }

                break;
            }
            default:
                System.out.println("Ha habido un problema");
        }
    }
}
