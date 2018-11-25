/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorion;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author esmeralda
 */
public class Puntuacion implements Serializable,Comparable{
    private String nombre;
    private int minutos;
    private int segundos;
    private int jugadas;
    private LocalTime hora;
    private LocalDate fecha;

    public Puntuacion(String nombre, int minutos,int segundos ,int jugadas) {
        this.nombre = nombre;
        this.minutos=minutos;
        this.segundos=segundos;
        this.jugadas = jugadas;
        this.hora=LocalTime.now();
        this.fecha=LocalDate.now();
        
    }

    public String getNombre() {
        return nombre;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }
    

    

    public int getJugadas() {
        return jugadas;
    }

    public LocalTime getHora() {
        return hora;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    
    public  String mostrar(){
       return "Usuario: "+nombre+" Tiempo: "+crearTiempo()+
      " Jugadas:"+jugadas+" Fecha: "+fecha+" Hora: "+hora.getHour()+":"+hora.getMinute()+":"+hora.getSecond();
    }
    public String crearTiempo(){
        String tiempo;
        if (minutos < 10 && segundos >= 0 && segundos < 10) {
                tiempo = "0" + minutos + ":0" + segundos;
            } else if (minutos < 10 ) {
                tiempo= "0" +minutos + ":" + segundos;
            } else {
                tiempo = minutos + ":" + segundos;
            }
        return tiempo;
    }



    @Override
    public int compareTo(Object o) {
        int resultado;
        Puntuacion p=(Puntuacion)o;
        if(this.jugadas>p.getJugadas()){
            resultado=-1;
        }else if(this.jugadas<p.getJugadas()){
            resultado=1;
        }else {
            if(this.minutos>getMinutos()){
                resultado=-1;
            }else if(this.minutos>getMinutos()){
                resultado=1;
            }else{
                if(this.segundos>getSegundos()){
                resultado=-1;
            }else if(this.segundos>getSegundos()){
                resultado=1;
            }else{
                resultado=0;
            }
            }
        }
        return resultado;
    }
    
    
}
