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
public class BarcoBuscador extends Barco{

    public BarcoBuscador(int tamañoDelBarco, int vida) {
        super(tamañoDelBarco, vida);
    }
    @Override
    public void activarHabilidad(Jugador jugador, Jugador oponente){
        if(this.isBarcoDestruido() == false) {
                boolean aux = true;
                for (int i = 0; i < oponente.getTablero().getTablero().length ; i++) {     //
                    if (aux) {                           
                        for (int j = 0; j < oponente.getTablero().getTablero().length; j++) {  //
                            if (oponente.getTablero().getTablero()[i][j].getVidaDeEstaParte() == oponente.getVida() && aux) {        
                                System.out.println("Se detectó barco enemigo en las coordenadas:\n" 
                                        + (i + 1) + " , " + (j + 1));
                                aux = false;                           

                            }

                        }
                    }
                }
        }
    }
   

    
    
}
