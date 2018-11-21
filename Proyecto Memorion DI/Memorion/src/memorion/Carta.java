/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorion;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 *
 * @author esmeralda
 */
public class Carta extends JButton{
    private boolean volteada=false;
    private ImageIcon imagenDorso;
    private ImageIcon imagenFrontal;
    private ControladorMenu controladorMenu;
    private String [] imagenes={"lazarillo1.jpg","lazarillo2.jpg"};
    private int indice;
    public Carta(ControladorMenu controladorMenu,int indice){
        URL url=getClass().getResource("trasera.png");
        imagenDorso=new ImageIcon(url);
        añadirImagenFrontal();
        establecerIcono();
        this.controladorMenu=controladorMenu;
        this.indice=indice;
        addActionListener(controladorMenu);
    }

    public ImageIcon getImagenDorso() {
        return imagenDorso;
    }

    public void setImagenDorso(ImageIcon imagenDorso) {
        this.imagenDorso = imagenDorso;
    }

    public ImageIcon getImagenFrontal() {
        return imagenFrontal;
    }

    public void setImagenFrontal(ImageIcon imagenFrontal) {
        this.imagenFrontal = imagenFrontal;
    }

    public ControladorMenu getControladorMenu() {
        return controladorMenu;
    }

    public void setControladorMenu(ControladorMenu controladorMenu) {
        this.controladorMenu = controladorMenu;
    }

    public String[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(String[] imagenes) {
        this.imagenes = imagenes;
    }
    
    public void añadirImagenFrontal(){
        
        URL url=getClass().getResource(imagenes[(int)(Math.random()*2)]);
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


