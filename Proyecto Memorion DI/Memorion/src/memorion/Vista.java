/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Sergio
 */
public class Vista extends JFrame {

    private MenuBar menuJuego;
    private Menu partidaNueva, cargar, guardar;
    private MenuItem facil, normal, dificil,cargarPartida, guardarPartida;
    private MenuShortcut atajoFacil, atajoNormal, atajoDificil;
    private Carta[][] cartas;
    private ControladorMenu controladorMenu;
    private Panel panelCartas;
    private Label l1;
    private Contador c;

    public Vista() {
        setTitle("MEMORION");
        this.controladorMenu = new ControladorMenu(this);
        crearMenu();

        //crearInterfaz();
        crearPanelInicial();

        setSize(1366, 768);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.println("jjj");
    }

    /**
     * En este método creamos un panel inicial que posteriormente sera
     * sustituido por el juego.Aquí se le explica al usuario que tiene que
     * elegir entre iniciar una partida o cargar una ya existente.
     */
    public void crearPanelInicial() {
        panelCartas = new Panel();
        JTextArea textArea = new JTextArea("Elija en el menu superior la opción que desea ya sea iniciar una partida o cargar una ya existente.");
        textArea.setBackground(new Color(29, 187, 240));
        textArea.setFont(new Font("Courier", Font.ITALIC, 25));
        textArea.setForeground(Color.GRAY);
        panelCartas.setBackground(new Color(29, 187, 240));
        panelCartas.add(textArea);
        add(BorderLayout.CENTER, panelCartas);
    }

    public void crearMenu() {
        //Creamos barra de Menu
        menuJuego = new MenuBar();
        //Creamos los 2 elementos menu
        partidaNueva = new Menu("Nueva partida");
        atajoFacil = new MenuShortcut(KeyEvent.VK_E, true);
        atajoNormal = new MenuShortcut(KeyEvent.VK_N, true);
        atajoDificil = new MenuShortcut(KeyEvent.VK_D, true);

        //Creamos el elemento menuItem y lo configuramos
        facil = new MenuItem("Easy", atajoFacil);
        normal = new MenuItem("Normal", atajoNormal);
        dificil = new MenuItem("Difficult", atajoDificil);

        partidaNueva.add(facil);
        partidaNueva.add(normal);
        partidaNueva.add(dificil);

        facil.addActionListener(controladorMenu);
        normal.addActionListener(controladorMenu);
        dificil.addActionListener(controladorMenu);

        //Añadimos los elementos Menu a la barra de menu
        menuJuego.add(partidaNueva);
        cargar = new Menu("Load");
        cargarPartida=new MenuItem("Load Game");
        cargar.addActionListener(controladorMenu);
        cargar.add(cargarPartida);
        
        guardar = new Menu("Save");
        guardarPartida=new MenuItem("Save Game");
        guardar.addActionListener(controladorMenu);
        guardar.add(guardarPartida);
        
        menuJuego.add(cargar);
        menuJuego.add(guardar);
        //Establecemos la barra de menu al Frame
        this.setMenuBar(menuJuego);

    }

    public void voltear() {
        /*prponer idea de cambiar el array bidimensional a uno unidimensional lo que facilitaria este metodo ya que pasando por parametro
        el indice de la carta se elige la carta que voltear facilmente
        cartas[indice].voltear();
         */
    }

    
    public void crearCartas(int filas, int columnas) {
        int contador = 0;
        remove(panelCartas);
        panelCartas = new Panel();
        panelCartas.setSize(800, 600);
        cartas = new Carta[filas][columnas];

        panelCartas.setLayout(new GridLayout(filas, columnas));
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.println("carta " + i + "|" + j);
                cartas[i][j] = new Carta(controladorMenu, contador);

                //cartas[i][j].setIcon(imagentrasera);
                panelCartas.add(cartas[i][j]);
                contador++;
            }

        }
        add(BorderLayout.CENTER, panelCartas);
        // Thread t=new Thread(new Contador());
        // t.start();

        setVisible(true);
        contador = 0;
    }

    public synchronized void crearContador() {
        if (!existeContador()) {
            c = new Contador(this);
        }
//c.start();

        Panel p2 = new Panel();
        l1 = new Label("Time: 00:00");
        p2.add(l1);
        add(BorderLayout.NORTH, p2);

    }

    public void actualizarMarcador(String tiempo) {
        l1.setText("Time: " + tiempo);
        //setVisible(true);
    }

    public boolean existeContador() {
        if (c == null) {
            return false;
        } else {
            return true;
        }
    }

    public void restablecerContador() {
        c.restablecerContador();
        
    }
    public boolean partidaAcabada(){
        boolean acabada=true;
        for (int i = 0; i < cartas.length; i++) {
            for (int j = 0; j < cartas[i].length; j++) {
                if(!cartas[i][j].isVolteada()){
                    acabada=false;
                }
            }            
        }
        return acabada;
    }
    public void estadoContador(boolean estado){
        c.setContar(estado);
       
    }
    
}

