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
public class BarcoVeterano extends Barco {

    public BarcoVeterano(int tamañoDelBarco, int vida) {
        super(tamañoDelBarco, vida);
    }

    @Override
    public void activarHabilidad(Jugador jugador, Jugador oponente) {
        int conteo = 0;
        Barco vivo = null;
        
        for (int i = 0; i < jugador.getFlota().length; i++) {
            if (jugador.getFlota()[i].isBarcoDestruido()) {
                conteo++;
            }
        }
        
        if ((conteo == jugador.getFlota().length - 1) && (vivo.getPartes()[0].getVidaDeEstaParte() == jugador.getVida())) {
     
            for(int i = 0; i < jugador.getFlota().length; i++){
                if(!jugador.getFlota()[i].isBarcoDestruido()){
                    vivo = jugador.getFlota()[i];
                }
            }
            
            System.out.println("Barco Veterano activado!");
            for (int i = 0; i < vivo.partes.length; i++) {
                vivo.getPartes()[i].setVidaDeEstaParte(vivo.getPartes()[i].getVidaDeEstaParte() + 1);
            }
        }
    }

}
