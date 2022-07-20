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
public class Jugador {
 
    //ATRIBUTOS DEL JUGADOR
    private int vida, contadorAcertados, contadorFallidos, partidasGanadas, tamañoSextoBarco, tamañoDeMapa;
    private Barco[] flota;
    private Mapa tablero;
   
    //CONSTRUCTOR DE LOS JUGADORES
    public Jugador (int vida, int tamañoSextoBarco, int tamañoDeMapa){ //Constructor del jugador que recibe la vida, el tamaño de su sexto barco y el tamaño del tablero
        this.vida = vida; 
        this.contadorAcertados = 0;
        this.contadorFallidos = 0;
        this.partidasGanadas = 0;
        this.flota = new Barco[6]; //Vector de barcos que tendrá los 6 barcos del jugador
        this.tamañoSextoBarco = tamañoSextoBarco; 
        this.crearFlota(); //Método que inicializa cada barco
        this.tamañoDeMapa = tamañoDeMapa;
        this.tablero = new Mapa(this.tamañoDeMapa); //Inicializar el tablero con el tamaño del tablero y los barcos que estarán en él
    }

    //MÉTODO PARA CREAR LOS BARCOS CON PODERES ALEATORIOS
    private void crearFlota(){ 
        
        //VARIABLE DE LA HABILIDAD
        int habilidad;
        
        //CICLO PARA INICIALIZAR LOS BARCOS SEGÚN SU PODER ALEATORIO
        for(int i = 0; i < 6; i++){ //Recorre el vector de los barcos
            habilidad = (int) (Math.random()*5+1); //Escoge un número aleatorio entre los 5 poderes
            switch(habilidad){ //Según el aleatorio crea el barco con la habilidad correspondiente y se asigna el nombre de la habilidad
                case 1: this.flota[i] = new BarcoNormal(establecerTamañoBarcos(i) , this.vida);
                        this.flota[i].setHabilidad(this.flota[i]);
                    break;
                case 2: this.flota[i] = new BarcoAcorazado(establecerTamañoBarcos(i), this.vida);
                        this.flota[i].setHabilidad(this.flota[i]);
                this.flota[i].activarHabilidad(this, this); //Si es un barco acorazado, entonces se activa la habilidad para aumentarle la vida
                    break;
                case 3: this.flota[i] = new BarcoBuscador(establecerTamañoBarcos(i), this.vida);
                        this.flota[i].setHabilidad(this.flota[i]);
                    break;
                case 4: this.flota[i] = new BarcoKowalski(establecerTamañoBarcos(i), this.vida);
                        this.flota[i].setHabilidad(this.flota[i]);
                    break;
                case 5: this.flota[i] = new BarcoVeterano(establecerTamañoBarcos(i), this.vida);
                        this.flota[i].setHabilidad(this.flota[i]);
            }
        }
        
        this.ordenarBarcos(this.flota); //Ordenar los barcos antes de proceder a colocarlos en el tablero
    }
    
    //MÉTODO PARA ESTABLECER LOS TAMAÑOS DE LOS BARCOS
    private int establecerTamañoBarcos(int i){
            if(i==0){
                return 5;
            } else if(i==1){
                return 4;
            } else if(i==2 || i == 3){
                return 3;
            } else if(i == 4){
                return 2;
            } else {
                return this.tamañoSextoBarco;
            }
    }

    //MÉTODO PARA ORDENAR LOS BARCOS ANTES DE EMPEZAR A COLOCARLOS.
    public void ordenarBarcos(Barco[] flota) {
           int mayor;
           Barco auxiliar;
           
            for (int i = 0; i < flota.length ; i++) {
                mayor = i;
                for (int j = i; j < flota.length; j++) {
                    if (flota[j].tamañoDelBarco > flota[mayor].tamañoDelBarco) {
                        mayor = j;
                    }
                }
                auxiliar = flota[mayor];
                flota[mayor] = flota[i];
                flota[i] = auxiliar;
            }
    }
    
    public boolean estáVivo(){
        int cont = 0;
        
        for(int i = 0; i < this.getFlota().length; i++){
            if(this.getFlota()[i].isBarcoDestruido()){
                cont++;
            }
        }
        if(cont == 6){
            return false;
        }else{
            return true;
        }
    }
    
    //MÉTODO PARA CALCULAR EL PORCENTAJE DE DISPAROS ACERTADOS
    public double porcentajeAcertados(){
        return (double) ((contadorAcertados * 100) / (contadorAcertados + contadorFallidos));
    }

    //GET Y SET
    public int getTamañoSextoBarco() {
        return tamañoSextoBarco;
    }

    public void setTamañoSextoBarco(int tamañoBarco) {
        this.tamañoSextoBarco = tamañoBarco;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getContadorAcertados() {
        return contadorAcertados;
    }

    public void setContadorAcertados(int contadorAcertados) {
        this.contadorAcertados = contadorAcertados;
    }

    public int getContadorFallidos() {
        return contadorFallidos;
    }

    public void setContadorFallidos(int contadorFallidos) {
        this.contadorFallidos = contadorFallidos;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public Barco[] getFlota() {
        return flota;
    }

    public void setFlota(Barco[] flota) {
        this.flota = flota;
    }

    public Mapa getTablero() {
        return tablero;
    }

    public void setTablero(Mapa tablero) {
        this.tablero = tablero;
    }
}
