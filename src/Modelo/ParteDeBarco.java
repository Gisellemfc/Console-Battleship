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
public class ParteDeBarco {
    
    //ATRIBUTOS
    private int vidaDeEstaParte;
    private boolean parteDañada, parteDestruida;

    //CONSTRUCTOR QUE RECIBE LA VIDA QUE TENDRÁ CADA PARTE DEL BARCO
    public ParteDeBarco(int vida) {
        this.vidaDeEstaParte = vida;
        this.parteDañada = false; //Por defecto las partes de barco no están ni dañadas, ni destruidas
        this.parteDestruida = false;
    }

    //CONSTRUCTOR DE PARTE DE BARCO POR DEFECTO USADA COMO IDENTIFICADOR EN EL TABLERO
    public ParteDeBarco() { //
        this.vidaDeEstaParte = 7; //Vida por defecto diferenciadora en el tablero
        this.parteDañada = false;
        this.parteDestruida = false;
        
    }

    //MÉTODO PARA SABER SI LA PARTE ESTÁ DESTRUIDA
    public void estáDestruidaLaParte(){
        if(this.vidaDeEstaParte == 0){ //Si su vida es 0, entonces está destruida
            this.parteDestruida = true;
        }
    }
    
    //MÉTODO PARA SABER SI LA PARTE ESTÁ DAÑADA
    public void estáDañadaLaParte(int vida){
        if(this.vidaDeEstaParte < vida && this.vidaDeEstaParte != 0 ){ //Si su vida es menor que la vida original y es diferente de 0, está dañada
            this.parteDañada = true;
        }
    }
    
    //GET Y SET
    public int getVidaDeEstaParte() {
        return vidaDeEstaParte;
    }

    public void setVidaDeEstaParte(int vidaDeEstaParte) {
        this.vidaDeEstaParte = vidaDeEstaParte;
    }

    public boolean isParteDañada() {
        return parteDañada;
    }

    public void setParteDañada(boolean parteDañada) {
        this.parteDañada = parteDañada;
    }

    public boolean isParteDestruida() {
        return parteDestruida;
    }

    public void setParteDestruida(boolean parteDestruida) {
        this.parteDestruida = parteDestruida;
    }
    
    
    
    
}
