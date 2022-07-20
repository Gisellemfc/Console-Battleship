/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Fabián Arzola
 */
public class Mapa {
    
    Scanner sc = new Scanner(System.in);
    
    //ATRIBUTOS
    private ParteDeBarco[][] tablero; //Tablero que estará compuesto por partes de barco
    private static int coordFilaUbicado, coordColumnaUbicado, orientacionUbicado;
    
    //CONSTRUCTOR DEL MAPA
    public Mapa(int tamaño){ //Recibe el vector de barcos que se ubicarán en el mapa y el tamaño del tablero
        this.tablero = new ParteDeBarco[tamaño][tamaño]; //Se asigna el tamaño del tablero ingresado por parámetros
        this.llenarTablero(); //Se rellena el tablero con partes de barco por defecto
    }
    
    //LLENAR EL MAPA CON PARTES DE BARCO POR DEFECTO
    private void llenarTablero(){
        
        for(int i = 0; i < this.tablero.length; i++){ //Recorrer todas las filas
            for(int j = 0; j < this.tablero.length; j++){ //Recorrer todas las columnas
                this.tablero[i][j] = new ParteDeBarco(); //Rellenar la matriz con partes de barco por defecto
            }
        }
    }
    
    //IMPRIMIR EL TABLERO
    public void imprimirTablero(Jugador jugador, boolean esUsuario){
        ParteDeBarco[][] tablero = jugador.getTablero().getTablero();
        
        //IMPRIMIR EL TÍTULO DEL TABLERO
        if(esUsuario){ //Si es el usuario, imprimir que es su mapa
            if(tablero.length == 7){
                System.out.println("              TU MAPA");
            }else if(tablero.length == 8){
                System.out.println("                TU MAPA");
            }else{
                System.out.println("                  TU MAPA");
            }
        }else{ //Si es el cpu, imprimir que es el mapa del cpu
            if(tablero.length == 7){
                System.out.println("             MAPA CPU");
            }else if(tablero.length == 8){
                System.out.println("               MAPA CPU");
            }else{
                System.out.println("                 MAPA CPU");
            }
        }
        
        //IMPRIMIR LA ENUMERACIÓN DE LAS COLUMNAS
        System.out.print("  "); //Dos espacios
        for(int i = 0; i< tablero.length; i++){ //Imprimir tantos números como tamaño tenga el tablero
            System.out.print("   " + (i+1));  //Imprimir el indice + 1 para enumerar correctamente las columnas
        }
        System.out.println(""); //Salto de línea
        
        //IMPRIMIR TECHO DEL TABLERO
        System.out.print("    "); //Cuatro espacios
        for(int j = 0; j < tablero.length; j++){ //Imprimir tantos techos como tamaño tenga el tablero + 1 
            System.out.print("___ "); //Imprimir techo junto con un espacio
        } 
        System.out.println(""); //Salto de línea
        
        //RECORRER LA MATRIZ
        for(int i = 0; i < tablero.length; i++){
            
            //IMPRIMIR LA ENUMERACIÓN DE LAS FILAS
            System.out.print((i+1) + "  "); //Imprimir índice + 1 para enumerar correctamente las filas
            
            //IMPRIMIR LA MATRIZ
            if(esUsuario){ //Si es el usuario, imprimir su simbología
                for(int j = 0; j < tablero.length; j++){
                    //EN ESTE SE ALTERAN LOS SÍMBOLOS SEGÚN LA VIDA DE LA PARTE DE BARCO QUE SE ENCUENTRE EN X POSICIÓN
                    if(tablero[i][j].getVidaDeEstaParte() == 7){ //Si la vida es por defecto
                        System.out.print("|   "); //Entonces imprimir pared y espacios, que indica que no hay nada en esa posición               
                    }else if(tablero[i][j].getVidaDeEstaParte() == 6){ //Si la vida de la parte por defecto es una unidad menor que su vida original
                        System.out.print("| ~ "); //Entonces imprimir pared y guión eñe, que indica que la fue disparada esa posición y no hay nada
                    }else if(tablero[i][j].getVidaDeEstaParte() == jugador.getVida() || tablero[i][j].getVidaDeEstaParte() == (jugador.getVida() + 1)){ //Si la parte de barco no está dañada
                        System.out.print("| O "); //Entonces imprimir pared y un círculo, que indica que hay una parte de barco no dañado
                    }else if(tablero[i][j].getVidaDeEstaParte() == 0){ //Si la parte de barco está destruida
                        System.out.print("| X "); //Entonces imprimir pared y una equis, que indica que hay una parte de barco destruida
                    }else{ //Si la parte de barco está dañada
                        System.out.print("| × "); //Entonces imprimir una pared y una equis pequeña, que indica que hay una parte de barco dañada
                    }
                }
            }else{ //Si es el cpu, imprimir su simbología
                for(int j = 0; j < this.tablero.length; j++){
                    //EN ESTE SE ALTERAN LOS SÍMBOLOS SEGÚN LA VIDA DE LA PARTE DE BARCO QUE SE ENCUENTRE EN X POSICIÓN
                    if(tablero[i][j].getVidaDeEstaParte() == 7 || tablero[i][j].getVidaDeEstaParte() == (jugador.getVida() + 1) || tablero[i][j].getVidaDeEstaParte() == jugador.getVida()){ //Si la vida es por defecto o hay una parte de barco no dañada
                        System.out.print("|   "); //Entonces imprimir pared y espacios, que indica que no hay nada en esa posición               
                    }else if(tablero[i][j].getVidaDeEstaParte() == 6){ //Si la vida de la parte por defecto es una unidad menor que su vida original
                        System.out.print("| ~ "); //Entonces imprimir pared y guión eñe, que indica que la fue disparada esa posición y no hay nada
                    }else if(tablero[i][j].getVidaDeEstaParte() == 0){ //Si la parte de barco está destruida
                        System.out.print("| X "); //Entonces imprimir pared y una equis, que indica que hay una parte de barco destruida
                    }else if(tablero[i][j].getVidaDeEstaParte() <= (jugador.getVida() + 1) && tablero[i][j].getVidaDeEstaParte() != 0){ //Si la parte de barco está dañada
                        System.out.print("| × "); //Entonces imprimir una pared y una equis pequeña, que indica que hay una parte de barco dañada
                    }
                }
            }
            System.out.println("|"); //Pared del final de la línea
        }
        
            //IMPRIMIR PISO DEL TABLERO
            System.out.print("    "); //Cuatro espacios
            for(int j = 0; j < tablero.length; j++){ //Imprimir tantos números como tamaño tenga el tablero 
                System.out.print("¯¯¯ "); //Imprimir piso junto con un espacio
            }
                System.out.println(""); //Salto de línea
    }
    
    //MÉTODO PARA UBICAR LOS BARCOS DEL USUARIO
    public void ubicarBarcosUsuario(Jugador jugador){
              
        int coordenada, coordFila, coordColumna, orientacion;
        
        for(int i = 0; i < jugador.getFlota().length; i++){ //Para repetir el proceso de ubicación tantas veces como barcos existan
        
                //IMPRIMIR EL TABLERO DEL USUARIO
                this.imprimirTablero(jugador, true);

                if(i > 0){
                    if(reiniciar()){
                        this.llenarTablero();
                        i = -1;
                    } else{
                        do{
                            coordenada = establecerCoordenada(jugador, i, true); 
                            coordFila = ((coordenada/10) - 1); //Coordenada de la FILA escogida
                            coordColumna = ((coordenada%10) - 1); //Coordenada de la COLUMNA escogida

                            orientacion = establecerOrientacion(true);

                        }while(this.validarInserción(coordFila, coordColumna, jugador.getFlota()[i], orientacion, true) || this.validarBarcos(coordFila, coordColumna, jugador.getFlota()[i], orientacion, true));

                        //UBICAR EL BARCO E IMPRIMIR EL TABLERO
                        posicionarBarco(coordFila, coordColumna, jugador.getFlota()[i], orientacion);

                    }
                }else{
                        coordenada = establecerCoordenada(jugador, i, true);
                        coordFila = ((coordenada/10) - 1); //Coordenada de la FILA escogida aleatoriamente
                        coordColumna = ((coordenada%10) - 1); //Coordenada de la COLUMNA escogida aleatoriamente

                        orientacion = establecerOrientacion(true);
                    
                    //UBICAR EL BARCO E IMPRIMIR EL TABLERO
                    posicionarBarco(coordFila, coordColumna, jugador.getFlota()[i], orientacion);
                }
        }
        this.imprimirTablero(jugador, true);
    }
    
    //MÉTODO PARA UBICAR LOS BARCOS DEL CPU
    public void ubicarBarcosCPU(Jugador jugador, int coordenada, int orientacion, int i, int aux, int cont){
        boolean imposibleUbicar;
        
        if(i < jugador.getFlota().length && i >= 0){ //Si aun quedan barcos por ubicar y no es menor 0 el índice

            int coordFila = ((coordenada/10) - 1); //Coordenada de la FILA escogida aleatoriamente
            int coordColumna = ((coordenada%10) - 1); //Coordenada de la COLUMNA escogida aleatoriamente
            
            if(coordColumna >= this.tablero.length){
                ubicarBarcosCPU(jugador, establecerCoordenada(jugador, 0, false), orientacion, i, aux, cont);
            }else{

                //Saber si es imposible ubicar el barco o por salirse del tablero o por superposición
                imposibleUbicar = (validarInserción(coordFila, coordColumna, jugador.getFlota()[i], orientacion, false) || validarBarcos(coordFila, coordColumna, jugador.getFlota()[i], orientacion, false));

                if(imposibleUbicar && cont != 30){ //Si es imposible ubicar el barco y el contador no ha llegado a 30 vueltas

                    if(aux == 0){ //Si no se ha cambiado ni una vez la orientación, entonces se intercambia la orientación
                        if(orientacion == 1){ 
                            ubicarBarcosCPU(jugador, coordenada, 2, i, 1, cont);
                        }else{
                            ubicarBarcosCPU(jugador, coordenada, 1, i, 1, cont);
                        }
                    }else{ //Si ya se ha cambiado una vez la orientación, entonces se cambia de coordenada
                        cont = cont + 1; //Aumentar el contador
                        ubicarBarcosCPU(jugador, establecerCoordenada(jugador, 0, false), establecerOrientacion(false), i, 0, cont);
                    }
                } else if(cont == 30 && i >= 0){ //Si no se puede ubicar, el contador llego a 30 y i es mayor o igual que 0, entonces ubicar el barco anterior
                    if(i > 0){
                        quitarBarco(jugador.getFlota()[i-1], this.coordFilaUbicado, this.coordColumnaUbicado, this.orientacionUbicado);
                    }
                    ubicarBarcosCPU(jugador, establecerCoordenada(jugador, 0, false), establecerOrientacion(false), i - 1, 0, 0);
                } else{ //Si se puede ubicar
                    this.coordFilaUbicado = coordFila;
                    this.coordColumnaUbicado = coordColumna;
                    this.orientacionUbicado = orientacion;
                    
                    posicionarBarco(coordFila, coordColumna, jugador.getFlota()[i], orientacion); //Posicionar el barco
                    ubicarBarcosCPU(jugador, establecerCoordenada(jugador, 0, false), establecerOrientacion(false), i + 1, 0, 0); //Ubicar el siguiente barco
                }
            }
        }else if(i < 0){
            jugador.getTablero().mapaPredefinido(jugador);
        }else{
        }
    }
    
    //MÉTODO PARA ESTABLECER LA COORDENADA DONDE SE UBICARÁ EL BARCO
    public int establecerCoordenada(Jugador jugador, int i, boolean esUsuario){
        int coordenada;
        
        if(esUsuario){
            System.out.println("Barco a posicionar:"
                    + "\nTamaño: " + jugador.getFlota()[i].getTamañoDelBarco() + "       Habilidad: " + jugador.getFlota()[i].getHabilidad());
            //SOLICITAR COORDENADA
            do{
                System.out.println("Ingrese la coordenada en donde desea ubicar el barco. Ej: 25 (fila 2, columna 5)");
                coordenada = sc.nextInt();

                //VALIDAR COORDENADA
                if(coordenada < 10 || coordenada > (11*this.tablero.length) || (coordenada%10) == 0 || (coordenada%10 > this.tablero.length)){
                    System.out.println("Coordenada inválidada, intente nuevamente.");
                }
            }while(coordenada < 10 || coordenada > (11*this.tablero.length) || (coordenada%10) == 0 || (coordenada%10 > this.tablero.length)); //La coordenada no puede ser menor de 10, mayor que la última fila y columna, ni puede terminar en 0
            return coordenada;
        }else{
            coordenada = (int) (Math.random() * (11*this.tablero.length) + 1);
            while(coordenada < 10 || coordenada > (11*this.tablero.length) || (coordenada%10) == 0 || (coordenada%10 > this.tablero.length)){
                coordenada = (int) (Math.random() * (11*this.tablero.length) + 1);
            }
            return coordenada;
        }
    }
    
    //MÉTODO PARA ESTABLECER LA ORIENTACIÓN EN LA QUE SE COLOCARÁ EL BARCO
    public int establecerOrientacion(boolean esUsuario){
        int orientacion;
        
        if(esUsuario){
            do{
                //SOLICITAR ORIENTACIÓN
                System.out.println("Ingrese la orientación en la que desea colocar el barco."
                        + "\n1. Vertical"
                        + "\n2. Horizontal");
                orientacion = sc.nextInt();
                //VALIDAR ORIENTACIÓN
                if(!(orientacion == 1 || orientacion == 2)){
                    System.out.println("Por favor, ingrese una orientación válida.");
                }
            }while(!(orientacion == 1 || orientacion == 2));
            return orientacion;
        } else {
            orientacion = (int) ((Math.random() * 2) + 1);
            while(!(orientacion == 1 || orientacion == 2)){
                orientacion = (int) ((Math.random() * 2) + 1);
            }
            return orientacion;
        }
    }
    
    //MÉTODO PARA UBICAR EL BARCO EN EL TABLERO
    private void posicionarBarco(int fila, int columna, Barco barco, int orientacion){
        
        if(orientacion == 1){ // SI LO QUIERE EN VERTICAL
            for(int i = 0; i < barco.getTamañoDelBarco(); i++){
                this.tablero[fila][columna]= barco.partes[i];
                fila++;
            }
        } else if (orientacion == 2) { // SI LO QUIERE EN HORIZONTAL
            for(int i = 0; i < barco.getTamañoDelBarco(); i++){
                this.tablero[fila][columna]= barco.partes[i];
                columna++;
            }
        }
    }
    
    //MÉTODO PARA VALIDAR QUE EL BARCO ESTÁ DENTRO DE LOS LÍMITES DEL MAPA
    private boolean validarInserción(int fila, int columna, Barco barco, int orientación, boolean esUsuario){
        
        if(esUsuario){
            if(orientación == 1){ //Si se colocará vertical
                if((fila + barco.getTamañoDelBarco()) > this.tablero.length){ //Si la fila más el tamaño del barco es mayo que el tamaño de la columna, entonces la coordenada es inválida
                    System.out.println("Coordenada inválida, el barco se sale del tablero. Por favor, intente nuevamente.\n");
                    return true;
                }
            }else{ //Si se colocará horizontal
                if((columna + barco.getTamañoDelBarco()) > this.tablero.length){ //Si la columna más el tamaño del barco es mayor que el tamaño de la fila, entonces es inválida la coordenada
                    System.out.println("Coordenada inválida, el barco se sale del tablero. Por favor, intente nuevamente.\n");
                    return true;
                }
            }
        }else{
            if(orientación == 1){ //Si se colocará vertical
                if((fila + barco.getTamañoDelBarco()) > this.tablero.length){ //Si la fila más el tamaño del barco es mayo que el tamaño de la columna, entonces la coordenada es inválida
                    return true;
                }
            }else{ //Si se colocará horizontal
                if((columna + barco.getTamañoDelBarco()) > this.tablero.length){ //Si la columna más el tamaño del barco es mayor que el tamaño de la fila, entonces es inválida la coordenada
                    return true;
                }
            }
        }
        
        return false;
    }
    
    //MÉTODO PARA VALIDAR SI ES ESTÁ COLOCANDO UN BARCO SOBRE OTRO
    public boolean validarBarcos(int fila, int columna, Barco barco, int orientacion, boolean esUsuario){
        
        if(esUsuario){
            if(orientacion == 1){ // SI LO QUIERE EN VERTICAL
                for(int i = 0; i < barco.getTamañoDelBarco(); i++){
                    if(this.tablero[fila][columna].getVidaDeEstaParte() != 7){
                        System.out.println("Coordenada inválida, barcos superpuestos. Por favor, intente nuevamente.");
                        return true;
                    }
                    fila++;
                }
            } else if (orientacion == 2) { // SI LO QUIERE EN HORIZONTAL
                for(int i = 0; i < barco.getTamañoDelBarco(); i++){
                    if(this.tablero[fila][columna].getVidaDeEstaParte() != 7){
                        System.out.println("Coordenada inválida, barcos superpuestos. Por favor, intente nuevamente.");
                        return true;
                    }
                    columna++;
                }
            }
        }else{
            if(orientacion == 1){ // SI LO QUIERE EN VERTICAL
                for(int i = 0; i < barco.getTamañoDelBarco(); i++){
                    if(this.tablero[fila][columna].getVidaDeEstaParte() != 7){
                        return true;
                    }
                    fila++;
                }
            } else if (orientacion == 2) { // SI LO QUIERE EN HORIZONTAL
                for(int i = 0; i < barco.getTamañoDelBarco(); i++){
                    if(this.tablero[fila][columna].getVidaDeEstaParte() != 7){
                        return true;
                    }
                    columna++;
                }
            }
        }
        
        return false;
    }
    
    //MÉTODO PARA VACIAR EL TABLERO
    public boolean reiniciar(){
        int reiniciar;
        
        do{
            System.out.println("¿Desea vaciar el tablero?"
                    + "\n1. Si"
                    + "\n2. No");
            reiniciar = sc.nextInt();
            
            if(reiniciar != 1 && reiniciar != 2){
                System.out.println("Respuesta inválida, intente nuevamente.");
            }
        }while(reiniciar != 1 && reiniciar != 2);
            
            if(reiniciar == 1){
                return true;
            } return false;
            
    }
    
    //MÉTODO PARA QUITAR UN BARCO DEL MAPA
    private void quitarBarco(Barco barco, int fila, int columna, int orientacion){
        if(orientacion == 1){ // SI LO PUSO EN VERTICAL
            for(int i = 0; i < barco.getTamañoDelBarco(); i++){
                this.tablero[fila][columna]= new ParteDeBarco();
                fila++;
            }
        } else if (orientacion == 2) { // SI LO PUSO EN HORIZONTAL
            for(int i = 0; i < barco.getTamañoDelBarco(); i++){
                this.tablero[fila][columna]= new ParteDeBarco();
                columna++;
            }
        }
    }
    
    //MAPA PREDEFINIDO PARA CPU
    private void mapaPredefinido(Jugador jugador){
        for(int i = 0; i < jugador.getFlota().length; i++){
            switch(i){
                case 0:
                    posicionarBarco(0, 0, jugador.getFlota()[i], 1);
                    break;
                
                case 1: 
                    posicionarBarco(0, 2, jugador.getFlota()[i], 2);
                    break;
                    
                case 2: 
                    posicionarBarco(i, i, jugador.getFlota()[i], 2);
                    break;
                    
                case 3: 
                    posicionarBarco(this.tablero.length-1, 3, jugador.getFlota()[i], 2);
                    break;
                    
                case 4:
                    posicionarBarco(2, this.tablero.length-1, jugador.getFlota()[i], 1);
                    break;
                    
                case 5:
                    posicionarBarco(4, 4, jugador.getFlota()[i], 2);
                    break;
                    
                case 6:
                    posicionarBarco(7, 2, jugador.getFlota()[i], 2);
                    break;
            }
        }
    }
    
    public void disparar(Jugador j, boolean esUsuario){
        int coordenada;
        boolean noDisparó = true;
        
        do{
            if(esUsuario){
                coordenada = establecerCoordDisparo(true);
            }else{
                coordenada = establecerCoordDisparo(false);
            }

                int coordFila = ((coordenada/10) - 1); //Coordenada de la FILA escogida
                int coordColumna = ((coordenada%10) - 1); //Coordenada de la COLUMNA escogida

                if(j.getTablero().getTablero()[coordFila][coordColumna].getVidaDeEstaParte() == 7){
                    j.getTablero().getTablero()[coordFila][coordColumna].setVidaDeEstaParte(j.getTablero().getTablero()[coordFila][coordColumna].getVidaDeEstaParte() - 1);
                    noDisparó = false;
                    j.setContadorFallidos(j.getContadorFallidos() + 1);
                }else if(j.getTablero().getTablero()[coordFila][coordColumna].getVidaDeEstaParte() <= (j.getVida() + 1) && j.getTablero().getTablero()[coordFila][coordColumna].getVidaDeEstaParte() != 0){
                    j.getTablero().getTablero()[coordFila][coordColumna].setVidaDeEstaParte(j.getTablero().getTablero()[coordFila][coordColumna].getVidaDeEstaParte() - 1);
                    for(int i = 0; i < j.getFlota().length; i++){
                        for(int k = 0; k < j.getFlota()[i].getPartes().length; k++){
                            j.getFlota()[i].getPartes()[k].estáDañadaLaParte(j.getVida());
                            j.getFlota()[i].getPartes()[k].estáDestruidaLaParte();
                        }
                        j.getFlota()[i].estáDañadoElBarco();
                        j.getFlota()[i].estáDestruidoElBarco();
                    }
                    noDisparó = false;
                    j.setContadorAcertados(j.getContadorAcertados() + 1);
                }else{
                    if(esUsuario){
                        if(j.getTablero().getTablero()[coordFila][coordColumna].getVidaDeEstaParte() == 0){
                            System.out.println("La parte ya está destruida, seleccione otra coordenada.");
                        }else{
                            System.out.println("Ahí no hay nada, seleccione otra coordenada.");
                        }
                    }
                }    
        }while(noDisparó);
    }
    
    private int establecerCoordDisparo(boolean esUsuario){
        int coordenada;
        if(esUsuario){
            do{
                    System.out.println("Ingrese la coordenada en la que desea disparar. Ej: 25 (fila 2, columna 5)");
                    coordenada = sc.nextInt();

                    //VALIDAR COORDENADA
                    if(coordenada < 10 || coordenada > (11*this.tablero.length) || (coordenada%10) == 0 || (coordenada%10 > this.tablero.length)){
                        System.out.println("Coordenada inválidada, intente nuevamente.");
                    }
                }while(coordenada < 10 || coordenada > (11*this.tablero.length) || (coordenada%10) == 0 || (coordenada%10 > this.tablero.length)); //La coordenada no puede ser menor de 10, mayor que la última fila y columna, ni puede terminar en 0
            
        }else{
            do{
                coordenada = (int) (Math.random() * (11*this.tablero.length) + 1);
            }while(coordenada < 10 || coordenada > (11*this.tablero.length) || (coordenada%10) == 0 || (coordenada%10 > this.tablero.length));
        }
        return coordenada;
    }

    //GET Y SET
    public ParteDeBarco[][] getTablero() {
        return tablero;
    }

    public void setTablero(ParteDeBarco[][] tablero) {
        this.tablero = tablero;
    }
}
