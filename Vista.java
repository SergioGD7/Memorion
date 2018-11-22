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
import java.awt.event.WindowAdapter;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Sergio
 */
public class Vista extends JFrame{
    
    private ControladorMenu controladorMenu;
    
    private MenuBar menuJuego;
    private Menu partidaNueva,salir;
    private MenuItem facil,normal,dificil,salirJuego;
    private MenuShortcut atajoFacil,atajoNormal,atajoDificil,atajoSalir;
    private Carta[][] cartas;
    private ImageIcon imagentrasera;
    private Panel panelCartas;
    private Label nombreContador,contador,labelInicio,nombrejugadas,jugadas;
    private int minutos=0,segundos=0,contadorCartas=0,contadorJugadas=0;
    private String min,seg;
    private Contador c=null;

    
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
        dificil = new MenuItem("Difficult",atajoDificil);
        
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
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(controladorMenu);
        //Para que la ventana salga centrada
        setLocationRelativeTo(null);
    }
    
    public void crearPanel(){
        panelCartas=new Panel();
        labelInicio=new Label("Elija en el menu superior la opción que desea ya sea iniciar una partida o cargar una ya existente.");
        //labelInicio.setBackground(Color.BLUE);
        labelInicio.setFont(new Font("Courier", Font.ITALIC, 25));
        labelInicio.setForeground(Color.GRAY);
        //panelCartas.setBackground(Color.BLUE);
        panelCartas.setBounds(10,10,1150,700);
        panelCartas.add(labelInicio);
        add(BorderLayout.CENTER,panelCartas);
        /*panelCartas.setBounds(10,10,1000,700);
        add(BorderLayout.CENTER,panelCartas);*/
        JOptionPane.showMessageDialog(null, "Wellcome to the Memorion !!\nPress enter to continue");
    }
    
    public void crearCartas(int filas,int columnas){
        remove(panelCartas);
        panelCartas=new Panel();
        panelCartas.setBounds(10,10,1150,700);
        cartas=new Carta[filas][columnas];
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
       //crearContador();
       //actualizarContador();
       setVisible(true);
       contadorCartas=0;
    }
    
    public synchronized void crearContador(){
        nombreContador=new Label("Time");
        nombreContador.setBounds(1200,2,150,50);
        nombreContador.setFont(new Font("Serif", Font.BOLD, 48));
        
        c=new Contador(this);
        
        contador=new Label();
        contador.setBounds(1200,80,150,50);
        contador.setFont(new Font("Serif", Font.BOLD, 48));
        
        nombrejugadas=new Label("Jugadas");
        nombrejugadas.setBounds(1180,200,150,50);
        nombrejugadas.setFont(new Font("Serif", Font.BOLD, 42));
        jugadas=new Label("0");
        jugadas.setBounds(1235,300,150,50);
        jugadas.setFont(new Font("Serif", Font.BOLD, 48));
        
        add(nombreContador);
        add(contador);
        add(nombrejugadas);
        add(jugadas);
    }
    
   public void actualizarContador(String tiempo){
        contador.setText(tiempo);
        
    }
   
   public void sumarJugadas(){
       contadorJugadas+=1;
       jugadas.setText(Integer.toString(contadorJugadas));
   }
   
   public void restablecerJugadas(){
       contadorJugadas=0;
       jugadas.setText(Integer.toString(contadorJugadas));
   }
   
    public boolean existeContador(){
       if(c==null){
           return false;
       }else{
           return true;
       }
    }
    
    public void restablecerContador(){
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
    
    public void cerrarAplicacion(){
        int res = JOptionPane.showConfirmDialog(null, "Estás seguro de que quieres salir?\nPerderás todos los datos que no hayas guardado","Alerta",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if ( res == JOptionPane.YES_OPTION ){
            JOptionPane.showMessageDialog(null, "Created By:\n\nSergio González Díaz\nÁngel Íñiguez Amorín");
            System.exit( 0 ); 
        }   
    }
}

