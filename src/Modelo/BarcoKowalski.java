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
public class BarcoKowalski extends Barco {

    public BarcoKowalski(int tamañoDelBarco, int vida) {
        super(tamañoDelBarco, vida);
    }

    @Override
    public void activarHabilidad(Jugador jugador, Jugador oponente) {
        if((jugador.getContadorAcertados()%6 == 0) && oponente.getContadorFallidos() > jugador.getContadorFallidos()){
            System.out.println("Almirante, nuestra precisión nos asegurará la victoria."); 
        } else if((jugador.getContadorAcertados()%6 == 0) && oponente.getContadorFallidos() < jugador.getContadorFallidos()){
            System.out.println("Almirante, si seguimos así, no podremos salir victoriosos."
                    + "\nRecuerde leer los informes enviados por nuestros buscadores.");
        }
    }
}
