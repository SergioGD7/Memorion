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
import java.awt.Image;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Sergio
 */
public class Vista extends JFrame {

    private MenuBar menuJuego;
    private Menu partidaNueva, cargar, guardar, ranking;
    private MenuItem facil, normal, dificil, cargarPartida, guardarPartida, verRanking;
    private MenuShortcut atajoFacil, atajoNormal, atajoDificil, atajoRanking;
    private Carta[][] cartas;
    private ControladorMenu controladorMenu;
    private Panel panelCartas,p2 = new Panel();
    private Label l1, nombrejugadas, jugadas;
    private int contadorJugadas = 0,contador=0;
    private JTextArea puntuacion;
    private Contador c;

    public Vista() {
        this.controladorMenu = new ControladorMenu(this);
        crearMenu();
        crearPanelInicial();
        crearInterfaz();
    }

    /**
     * En este método creamos un panel inicial que posteriormente sera
     * sustituido por el juego.Aquí se le explica al usuario que tiene que
     * elegir entre iniciar una partida o cargar una ya existente.
     */
    public void crearInterfaz() {
        setTitle("MEMORION");

        controladorMenu = new ControladorMenu(this);
        //Cambiamos el icono de la aplicación
        Image icon = new ImageIcon(getClass().getResource("trasera.png")).getImage();
        setIconImage(icon);

        //setLayout(null);
        setResizable(false);
        setSize(1366, 768);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(controladorMenu);
        //Para que la ventana salga centrada
        setLocationRelativeTo(null);
    }

    public void crearPanelInicial() {
        panelCartas = new Panel();
        JTextArea textArea = new JTextArea("Choose the option you want in the top menu.");
        textArea.setBackground(new Color(29, 187, 240));
        textArea.setFont(new Font("Courier", Font.ITALIC, 25));
        textArea.setForeground(Color.GRAY);
        panelCartas.setBackground(new Color(29, 187, 240));
        panelCartas.add(textArea);
        add(BorderLayout.CENTER, panelCartas);
        JOptionPane.showMessageDialog(null, "Wellcome to the Memorion !!\nPress enter to continue");
    }

    public void crearMenu() {
        //Creamos barra de Menu
        menuJuego = new MenuBar();
        //Creamos los 2 elementos menu
        partidaNueva = new Menu("Nueva partida");
        atajoFacil = new MenuShortcut(KeyEvent.VK_E, true);
        atajoNormal = new MenuShortcut(KeyEvent.VK_N, true);
        atajoDificil = new MenuShortcut(KeyEvent.VK_D, true);
        atajoRanking = new MenuShortcut(KeyEvent.VK_R, true);
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
        cargarPartida = new MenuItem("Load Game");
        cargar.addActionListener(controladorMenu);
        cargar.add(cargarPartida);

        guardar = new Menu("Save");
        guardarPartida = new MenuItem("Save Game");
        guardar.addActionListener(controladorMenu);
        guardar.add(guardarPartida);
        ranking = new Menu("Ranking");
        verRanking = new MenuItem("See Ranking", atajoRanking);
        verRanking.addActionListener(controladorMenu);
        ranking.add(verRanking);
        menuJuego.add(cargar);
        menuJuego.add(guardar);
        menuJuego.add(ranking);

        //Establecemos la barra de menu al Frame
        this.setMenuBar(menuJuego);

    }

    public void voltear() {
        /*prponer idea de cambiar el array bidimensional a uno unidimensional lo que facilitaria este metodo ya que pasando por parametro
        el indice de la carta se elige la carta que voltear facilmente
        cartas[indice].voltear();
         */
    }

    /*
    public void crearInterfaz(){
        setTitle("MEMORION");   
        panelCartas=new Panel();
        add(BorderLayout.NORTH,panelCartas);
        setResizable(false);
        setSize(1366,768);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }*/
    public void crearCartas(int filas, int columnas,String modo) {
        contador = 0;
        remove(panelCartas);
        ArrayList<String> imagenes2=new ArrayList<>();
        //String []imagenes={"lazarillo1.jpg","lazarillo2.jpg","lazarillo3.jpg","lazarillo4.jpg","lazarillo5.jpg","lazarillo6.jpg","lazarillo1.jpg","lazarillo2.jpg","lazarillo3.jpg","lazarillo4.jpg","lazarillo5.jpg","lazarillo6.jpg"};
        elegirImagenes(modo, imagenes2);
        Collections.shuffle(imagenes2);
        panelCartas = new Panel();
        panelCartas.setSize(800, 600);
        cartas = new Carta[filas][columnas];
        int h;
        panelCartas.setLayout(new GridLayout(filas, columnas));
        rellenarPanelCartas(filas, columnas, imagenes2);
        add(BorderLayout.CENTER, panelCartas);
        // Thread t=new Thread(new Contador());
        // t.start();
        setVisible(true);
        
    }
    public void elegirImagenes(String modo,ArrayList<String> imagenes2){
        
        switch(modo){
            case "Easy":{
                String []imagenes={"lazarillo1.jpg","lazarillo2.jpg","lazarillo3.jpg","lazarillo4.jpg","lazarillo5.jpg","lazarillo6.jpg",
                    "lazarillo1.jpg","lazarillo2.jpg","lazarillo3.jpg","lazarillo4.jpg","lazarillo5.jpg","lazarillo6.jpg"};
                for (int i = 0; i < imagenes.length; i++) {
                    imagenes2.add(imagenes[i]);
                }
                break;
            }
            case "Normal":{
                String []imagenes={"lazarillo1.jpg","lazarillo2.jpg","lazarillo3.jpg","lazarillo4.jpg","lazarillo5.jpg","lazarillo6.jpg",
                    "lazarillo7.jpg","lazarillo8.jpg","lazarillo9.jpg","lazarillo10.jpg","lazarillo1.jpg","lazarillo2.jpg","lazarillo3.jpg",
                    "lazarillo4.jpg","lazarillo5.jpg","lazarillo6.jpg","lazarillo7.jpg","lazarillo8.jpg","lazarillo9.jpg","lazarillo10.jpg"};
                for (int i = 0; i < imagenes.length; i++) {
                    imagenes2.add(imagenes[i]);
                }
                break;
            }
            case "Difficult":{
                String []imagenes={"lazarillo1.jpg","lazarillo2.jpg","lazarillo3.jpg","lazarillo4.jpg","lazarillo5.jpg","lazarillo6.jpg",
                    "lazarillo7.jpg","lazarillo8.jpg","lazarillo9.jpg","lazarillo10.jpg","lazarillo11.jpg","lazarillo12.jpg","lazarillo13.jpg",
                    "lazarillo14.jpg","lazarillo15.jpg","lazarillo1.jpg","lazarillo2.jpg","lazarillo3.jpg","lazarillo4.jpg","lazarillo5.jpg",
                    "lazarillo6.jpg","lazarillo7.jpg","lazarillo8.jpg","lazarillo9.jpg","lazarillo10.jpg","lazarillo11.jpg","lazarillo12.jpg",
                    "lazarillo13.jpg","lazarillo14.jpg","lazarillo15.jpg"};
                for (int i = 0; i < imagenes.length; i++) {
                    imagenes2.add(imagenes[i]);
                }
            }
        }
        
    }
public void rellenarPanelCartas(int filas, int columnas,ArrayList<String>imagenes){
    for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.println("carta " + i + "|" + j);
                cartas[i][j] = new Carta(controladorMenu,imagenes,contador);
                cartas[i][j].reescalarImagen(this.getWidth() / columnas, (this.getHeight() / filas));

                //cartas[i][j].setIcon(imagentrasera);
                panelCartas.add(cartas[i][j]);
                contador++;
            }

        }
    contador = 0;
}
    public synchronized void crearContador() {
        //p2 = new Panel();
        if (!existeContador()) {
            c = new Contador(this);
            l1 = new Label("Time: 00:00");
            p2.add(l1);
        }
        
//c.start();
        add(BorderLayout.NORTH, p2);
        setVisible(true);

    }

    public void panelRanking() {
        remove(panelCartas);
        remove(p2);
        panelCartas = new Panel();
        panelCartas.setSize(800, 600);
        panelCartas.setBackground(Color.yellow);
        puntuacion=new JTextArea("Ranking: \n");
        puntuacion.setSize(400, 400);
        puntuacion.setBackground(Color.yellow);
        panelCartas.add(puntuacion);
        /*panelCartas.setLayout(new GridLayout(puntuaciones.size(), 5));
        for (int i = 0; i < (puntuaciones.size()); i++) {
            for (int j = 5; j < 10; j++) {
                elegirColumna(j, puntuaciones.get(i));
            }
            //panelCartas.add(puntuacion);
            

        }*/
        add(BorderLayout.CENTER, panelCartas);
            setVisible(true);
    }
    public void elegirColumna(int posicion,Puntuacion p){
       Label l1; 
       switch(posicion){
            
            case 0:{
                l1=new Label(p.getNombre());
                panelCartas.add(l1);
                break;
            }
            case 1:{
                l1=new Label(p.getMinutos()+":"+p.getSegundos());
                panelCartas.add(l1);
                break;
            }
            case 2:{
                l1=new Label(String.valueOf(p.getJugadas()));
                panelCartas.add(l1);
                break;
            }
            case 3:{
                l1=new Label(p.getFecha().toString());
                panelCartas.add(l1);
                break;
            }
            case 4:{
                l1=new Label(p.getHora().getHour()+":"+p.getHora().getMinute()+":"+p.getHora().getSecond());
                panelCartas.add(l1);
                break;
            }
        }
    }

    public void escribirRanking(String puntos) {
        puntuacion.append(puntos + "\n");
        setVisible(true);
    }

    public String tiempoPartida() {
        return c.getContador();
    }

    public void actualizarMarcador(String tiempo) {
        l1.setText("Time: " + tiempo);

        //setVisible(true);
    }
    public int getContadorMinutos(){
        return c.getMinutos();
    }
    public int getContadorSegundos(){
        return c.getSegundos();
    }

    public boolean existeContador() {
        if (c == null) {
            return false;
        } else {
            return true;
        }
    }

    public void sumarJugadas() {
        contadorJugadas += 1;
        //jugadas.setText(Integer.toString(contadorJugadas));
    }

    public void restablecerJugadas() {
        contadorJugadas = 0;
        //jugadas.setText(Integer.toString(contadorJugadas));
    }

    public void restablecerContador() {
        c.restablecerContador();
        // setVisible(true);
        //c.run();
    }

    public int jugadasUsadas() {
        return contadorJugadas;
    }

    public boolean partidaAcabada() {
        boolean acabada = true;
        for (int i = 0; i < cartas.length; i++) {
            for (int j = 0; j < cartas[i].length; j++) {
                if (!cartas[i][j].isVolteada()) {
                    acabada = false;
                }
            }
        }
        return acabada;
    }

    public void estadoContador(boolean estado) {
        c.controlarContador(estado);

    }

    /*public synchronized void detenerContador(){
        c.paraContador();
    }
    public synchronized void reanudarContador() {
        c.reanudarContador();
        
    }*/
    public void cerrarAplicacion() {
        int res = JOptionPane.showConfirmDialog(null, "Estás seguro de que quieres salir?\nPerderás todos los datos que no hayas guardado", "Alerta", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Created By:\n\nSergio González Díaz\nÁngel Íñiguez Amorín");
            System.exit(0);
        }
    }
}
