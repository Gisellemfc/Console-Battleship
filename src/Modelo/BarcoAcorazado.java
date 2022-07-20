/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Fabián Arzola
 */
public class BarcoAcorazado extends Barco {

    //CONSTRUCTOR DEL BARCO
    public BarcoAcorazado(int tamañoDelBarco, int vida) {
        super(tamañoDelBarco, vida);
    }

    //MÉTODO DE HABILIDAD DEL BARCO
    @Override
    public void activarHabilidad(Jugador jugador, Jugador oponente) { 
        for (ParteDeBarco parte : this.getPartes()) {
            parte.setVidaDeEstaParte(parte.getVidaDeEstaParte() + 1); //Añadir una unidad de vida a cada parte del barco
        }
    }
}
    

