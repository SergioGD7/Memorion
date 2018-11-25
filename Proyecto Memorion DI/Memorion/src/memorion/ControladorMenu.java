/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Sergio
 */
public class ControladorMenu extends WindowAdapter implements ActionListener {

    private Carta carta1 = null;
    private Carta carta2 = null;
    private Vista vista;
    
    ArrayList <Puntuacion> puntuaciones;
    //private Timer t;

    public ControladorMenu(Vista vista) {
        this.vista = vista;
    }

    @Override
    public synchronized void actionPerformed(ActionEvent ae) {
        System.out.println(ae.getActionCommand());
        
        
        switch (ae.getActionCommand()) {
            case "Easy":{
                carta1 = null;
                carta2 = null;
                vista.restablecerJugadas();
                vista.crearCartas(3, 4,ae.getActionCommand());
                if (vista.existeContador()) {
                    //vista.reanudarContador();   
                    vista.restablecerContador();
                    vista.estadoContador(true);
                } 
                vista.crearContador();
                
                
                
                break;
            }
            case "Normal":{
                carta1 = null;
                carta2 = null;
                vista.restablecerJugadas();
                vista.crearCartas(4, 5,ae.getActionCommand());
                if (vista.existeContador()) {
                    //vista.reanudarContador();   
                    vista.restablecerContador();
                    vista.estadoContador(true);
                } 
                vista.crearContador();
                break;
            }
            case "Difficult":{
                carta1 = null;
                carta2 = null;
                vista.restablecerJugadas();
                vista.crearCartas(5, 6,ae.getActionCommand());
                if (vista.existeContador()) {
                    //vista.reanudarContador();   
                    vista.restablecerContador();
                    vista.estadoContador(true);
                } 
                vista.crearContador();
                break;
            }
            case "See Ranking":{
                if (vista.existeContador()) {
                    vista.estadoContador(false);
                }
                vista.panelRanking();
                leerFichero();
                rellenarRanking();
                break;
            }
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
            } else {
                carta1 = null;
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

                //carta2.repaint();
                carta2.voltear();
                carta2.setVolteada(true);
                if(carta1!=null && carta2!=null){
                comprobarCartasIguales();
                vista.sumarJugadas();
                }
                

            } else {
                carta2 = null;
            }
        }
    }

    public static void escribirFichero(Puntuacion p) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            File fichero = new File("ranking.bin");
            if (!fichero.exists()) {
                fos = new FileOutputStream(fichero);
                oos = new ObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(fichero, true);
                oos=new MiObjectOutputStream(fos);
            }
            oos.writeObject(p);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

    }
    public void rellenarRanking(){
        Collections.sort(puntuaciones);
        for (int i = 0; i < puntuaciones.size(); i++) {
            vista.escribirRanking(puntuaciones.get(i).mostrar());
        }
    }

    public   void leerFichero() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
       puntuaciones=new ArrayList<>();
        File fichero=new File("ranking.bin");
        try {
            fis = new FileInputStream(fichero);
            ois = new ObjectInputStream(fis);
            while(true){
                puntuaciones.add((Puntuacion)ois.readObject());
                //Puntuacion puntuacion=(Puntuacion) ois.readObject();
                //System.out.println((Puntuacion) ois.readObject());
               // System.out.println(puntuacion.mostrar());
                //vista.escribirRanking(puntuacion.mostrar());
            }
            
        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
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
                        //vista.detenerContador();
                        vista.estadoContador(false);
                        guardarPuntuacion();
                    }
                }
                carta1 = null;
                carta2 = null;
            }
        });
        t.start();
        t.setRepeats(false);
        /*if (carta1.imagenActual().equals(carta2.imagenActual())) {

            
            carta1 = null;
            carta2 = null;
        }*/
    }

    public void guardarPuntuacion() {
        JOptionPane.showMessageDialog(null, "Partida acabada!!");
        int resguardar = JOptionPane.showConfirmDialog(null, "Quieres guardar tu puntuación?", "Guardar?", JOptionPane.YES_NO_OPTION);
        if (resguardar == JOptionPane.YES_OPTION) {

            String nombreusuario = JOptionPane.showInputDialog(null, "Introduce el nombre de usuario");
            Puntuacion p = new Puntuacion(nombreusuario, vista.getContadorMinutos(),vista.getContadorSegundos(), vista.jugadasUsadas());
            escribirFichero(p);
            
        }
    }

    @Override
    public void windowClosing(WindowEvent we) {
        vista.cerrarAplicacion();
    }
}
