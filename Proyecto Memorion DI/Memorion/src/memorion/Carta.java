/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorion;

/**
 *
 * @author Sergio
 */
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 *
 * @author Sergio
 */
public class Carta extends JButton{
    private boolean volteada=false;
    private ImageIcon imagenDorso;
    private ImageIcon imagenFrontal;
    private ControladorMenu controladorMenu;
    private int indice;
    public Carta(ControladorMenu controladorMenu,int indice){
        URL url=getClass().getResource("trasera.png");
        imagenDorso=new ImageIcon(url);
        añadirImagenFrontal();
        establecerIcono();
        System.out.println(getIcon().toString());
        this.controladorMenu=controladorMenu;
        this.indice=indice;
        addActionListener(controladorMenu);
    }
    public void añadirImagenFrontal(){
        URL url=getClass().getResource("lazarillo1.jpg");
        imagenFrontal=new ImageIcon(url);
    } 
    public String imagenActual() {
        return getIcon().toString();
    }
    
    
    public void voltear(){
        if(getIcon().equals(imagenDorso)){
            setIcon(imagenFrontal);
        }else{
            setIcon(imagenDorso);
        }
    }
    public void establecerIcono(){
        if(!volteada){
            setIcon(imagenDorso);
        }else{
            setIcon(imagenFrontal);
        }
    }

    public int getIndice() {
        return indice;
    }

    public boolean isVolteada() {
        return volteada;
    }

    public void setVolteada(boolean volteada) {
        this.volteada = volteada;
    }
    
    
}

