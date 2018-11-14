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
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Sergio
 */
public class Vista extends JFrame{
    
    private ControladorMenu controladorMenu;
    
    private MenuBar menuJuego;
    private Menu partidaNueva;
    private MenuItem facil,normal,dificil;
    private MenuShortcut atajoFacil,atajoNormal,atajoDificil;
    private JButton[][] cartas;
    private ImageIcon imagentrasera;
    private Panel panelCartas;
    private Label nombreContador,contador,labelInicio;
    private int minutos=0,segundos=0,contadorCartas=0;
    private String min,seg;

    
    public Vista(){
        crearMenu();
        crearInterfaz();
        crearPanel();
    }
    
    public void crearMenu(){
         //Creamos barra de Menu
        menuJuego = new MenuBar();
        
        //Creamos los 2 elementos menu
        partidaNueva= new Menu("New Game");
        
        atajoFacil=new MenuShortcut(KeyEvent.VK_E,true);
        atajoNormal=new MenuShortcut(KeyEvent.VK_N,true);
        atajoDificil=new MenuShortcut(KeyEvent.VK_D,true);
        
        //Creamos el elemento menuItem y lo configuramos
        facil = new MenuItem("Easy",atajoFacil);
        normal = new MenuItem("Normal",atajoNormal);
        dificil = new MenuItem("Dificult",atajoDificil);
        
        partidaNueva.add(facil);
        partidaNueva.add(normal);
        partidaNueva.add(dificil);
        
        facil.addActionListener(new ControladorMenu(this));
        normal.addActionListener(new ControladorMenu(this));
        dificil.addActionListener(new ControladorMenu(this));
        
        //Añadimos los elementos Menu a la barra de menu
        menuJuego.add(partidaNueva);
        
        //Establecemos la barra de menu al Frame
        this.setMenuBar(menuJuego);
        
    }
    
    public void crearInterfaz(){
        setTitle("MEMORION");
        
        controladorMenu=new ControladorMenu(this);
        //Cambiamos el icono de la aplicación
        Image icon = new ImageIcon(getClass().getResource("trasera.png")).getImage();
        setIconImage(icon);
        
        setLayout(null);
        
        setResizable(false);
        setSize(1366,768);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void crearPanel(){
        panelCartas=new Panel();
        labelInicio=new Label("Elija en el menu superior la opción que desea ya sea iniciar una partida o cargar una ya existente.");
        //labelInicio.setBackground(Color.BLUE);
        labelInicio.setFont(new Font("Courier", Font.ITALIC, 25));
        labelInicio.setForeground(Color.GRAY);
        panelCartas.setBackground(Color.BLUE);
        panelCartas.setBounds(10,10,1000,700);
        panelCartas.add(labelInicio);
        add(BorderLayout.CENTER,panelCartas);
        /*panelCartas.setBounds(10,10,1000,700);
        add(BorderLayout.CENTER,panelCartas);*/
    }
    
    public void crearCartas(int filas,int columnas){
        remove(panelCartas);
        panelCartas=new Panel();
        panelCartas.setBounds(10,10,1000,700);
        cartas=new Carta[filas][columnas];
        URL url=getClass().getResource("trasera.png");
        imagentrasera=new ImageIcon(url);
        panelCartas.setLayout(new GridLayout(filas,columnas));
        
        for(int i=0;i<filas;i++){
            for(int j=0;j<columnas;j++){
                System.out.println("carta "+i+"|"+j);
                cartas[i][j]=new Carta(controladorMenu,contadorCartas);
                panelCartas.add(cartas[i][j]);
                contadorCartas++;
            }
            
        }
       add(BorderLayout.CENTER,panelCartas);
       //contador=new Contador();
       //crearContador();
       //actualizarContador();
       setVisible(true);
       contadorCartas=0;
    }
    
    public void crearContador(){
        nombreContador=new Label("Time");
        nombreContador.setBounds(1150,2,150,50);
        nombreContador.setFont(new Font("Serif", Font.BOLD, 48));
        contador=new Label();
        contador.setBounds(1150,80,150,50);
        contador.setFont(new Font("Serif", Font.BOLD, 48));
        
        add(nombreContador);
        add(contador);
    }
    
   /* public void actualizarContador(){
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
                    Thread c=new Thread();
                    c.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Error");
                }
            
        }
        setVisible(true);
        
    }*/
}
