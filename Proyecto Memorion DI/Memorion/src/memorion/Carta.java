/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorion;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
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
     private Image imagetrasera,imagefrontal;
    private ControladorMenu controladorMenu;
    private ArrayList<String>imagenes;
    private int indice;
    public Carta(ControladorMenu controladorMenu,ArrayList<String> imagenes,int indice){
        this.controladorMenu=controladorMenu;
        this.imagenes=imagenes;
        this.indice=indice;
        this.controladorMenu=controladorMenu;
        URL url=getClass().getResource("trasera.png");
        imagenDorso=new ImageIcon(url);
        añadirImagenFrontal();
        
        establecerIcono();
        
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

    public ArrayList<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<String> imagenes) {
        this.imagenes = imagenes;
    }
    
    public void añadirImagenFrontal(){
        
        URL url=getClass().getResource(imagenes.get(indice));
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
    public void reescalarImagen(int x,int y){
        //URL url=getClass().getResource("trasera.png");
        //imagenDorso=new ImageIcon(url);
        imagetrasera = imagenDorso.getImage();
        imagetrasera = imagetrasera.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        imagenDorso.setImage(imagetrasera);
        imagefrontal=imagenFrontal.getImage();
        imagefrontal= imagefrontal.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        imagenFrontal.setImage(imagefrontal);
    }
    
}
