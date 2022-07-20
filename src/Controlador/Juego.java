/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.Jugador;
import Modelo.Mapa;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Giselle Ferreira
 */
public class Juego {
    
    //VARIABLES
    private Scanner sc = new Scanner(System.in); 
    private int modo, dificultad, tamañoDeMapa, tamañoDeBarco;
    private Jugador usuario, cpu; //Crear los dos jugadores de la partida
    
    //MÉTODO QUE CORRE TODO EL JUEGO
    public void jugar(){
        
        //BIENVENIDA
        System.out.println("\n******************************");
        System.out.println("* BIENVENIDO A BATALLA NAVAL *");
        System.out.println("******************************");
        
        do{//Ciclo para volver a jugar al terminar una partida si así se desea
            
            //ESTABLECER LOS VALORES INICIALES DEL JUGADOR
            setModo(); //Modo de juego
            setDificultad(); //Dificultad
            setTamañoMapa(); //Tamaño del tablero
            setTamañoBarco(); //Tamaño del sexto barco
            
            //INICIALIZAR LOS JUGADORES
            this.usuario = new Jugador(this.establecerVida(true), this.tamañoDeBarco, this.establecerTamañoDeTablero(this.tamañoDeMapa)); //Inicializar usuario con su vida, tamaño de barco escogido y tamaño del tablero
            if(this.usuario.getTamañoSextoBarco() == 7){
                this.cpu = new Jugador(this.establecerVida(false), 7, this.establecerTamañoDeTablero(this.tamañoDeMapa)); //Inicializar CPU con su vida, tamaño de barco aleatorio entre el escogido por el jugador y 7, y su tamaño de tablero
            }else{
                this.cpu = new Jugador(this.establecerVida(false), ThreadLocalRandom.current().nextInt(this.usuario.getTamañoSextoBarco(), 7), this.establecerTamañoDeTablero(this.tamañoDeMapa)); //Inicializar CPU con su vida, tamaño de barco aleatorio entre el escogido por el jugador y 7, y su tamaño de tablero
            }
            
            // EJECUTAR MODO DE JUEGO
            switch(this.modo){
                case 1:
                    this.batalla(); //Modo de Batalla
                    break;
                case 2:
                    break;
            }
        }while(volverAJugar());
        
    }
    
    //MÉTODO PARA GUARDAR EL MODO DE JUEGO
    private void setModo(){
        
        do{
            //SOLICITAR
            System.out.println("\n¿A qué modo de juego desea jugar?"
                    + "\n1. BATALLA         2. CAMPAÑA");
            this.modo = sc.nextInt();
            
            //VALIDAR
            if(!(this.modo == 1 || this.modo == 2)){
                System.out.println("\nOpción inválida. Intente nuevamente");
            }
        }while(!(this.modo == 1 || this.modo == 2));
        
    }
    
    //MÉTODO PARA GUARDAR LA DIFICULTAD
    private void setDificultad(){
        
        do{
            //SOLICITAR
            System.out.println("\nSeleccione la dificultad en la que desea jugar"
                    + "\n1. MUY FÁCIL"
                    + "\n2. FÁCIL"
                    + "\n3. NORMAL"
                    + "\n4. DIFÍCIL"
                    + "\n5. MUY DIFÍCIL");
            this.dificultad = sc.nextInt();
            
            //VALIDAR
            if(this.dificultad < 1 ||this.dificultad > 5){
                System.out.println("\nOpción inválida. Intente nuevamente.");
            }
        }while(this.dificultad < 1 ||this.dificultad > 5);
    }
    
    //MÉTODO PARA GUARDAR EL TAMAÑO DEL MAPA
    private void setTamañoMapa(){
        
        do{
            //SOLICITAR
            System.out.println("\n¿Cuál tamaño de mapa desea?"
                    + "\n1. 7x7           2. 8x8            3. 9x9");
            this.tamañoDeMapa = sc.nextInt();

            //VALIDAR
            if(this.tamañoDeMapa < 1 || this.tamañoDeMapa > 3){
                System.out.println("\nOpción inválida. Intente nuevamente.");
            }
        }while(this.tamañoDeMapa < 1 || this.tamañoDeMapa > 3);
    }
    
    //MÉTODO PARA GUARDAR EL TAMAÑO DEL SEXTO BARCO
    private void setTamañoBarco(){
        
        do{
            //SOLICITAR
            System.out.println("\nFLOTA ACTUAL: 1 Barco tamaño 2, 2 Barcos tamaño 3, 1 Barco tamaño 4 y 1 Barco tamaño 5");
            System.out.println("¿Cuál será el tamaño de tu sexto barco?"
                    + "\nIngrese un tamaño entre 2 y 7.");
            this.tamañoDeBarco= sc.nextInt();

            //VALIDAR
            if(this.tamañoDeBarco < 2 || this.tamañoDeBarco > 7){
                System.out.println("\nTamaño inválido. Intente nuevamente.");
            }    
        }while(this.tamañoDeBarco < 2 || this.tamañoDeBarco > 7);
        
        System.out.println("\nFLOTA ACTUAL: 1 Barco tamaño 2, 2 Barcos tamaño 3, 1 Barco tamaño 4, 1 Barco tamaño 5 y"
                + " 1 barco adicional tamaño " + this.tamañoDeBarco + "\n");
            
    }
    
    //MÉTODO PARA ESTABLECER LA VIDA DE ACUERDO A LA DIFICULTAD
    private int establecerVida(boolean esUsuario){
        switch(this.dificultad){ //Dependiendo de la dificultad se asigna la vida de los jugadores
            case 1: //Si la dificultad es muy fácil
                if(esUsuario){ //Si es usuario
                    return 3;
                }else{ //Si es CPU
                    return 1;
                }
            case 2: //Si la dificultad es fácil
                if(esUsuario){ //Si es usuario
                    return 2;
                }else{ //Si es CPU
                    return 1;
                }
            case 3: //Si la dificultad es normal
                if(esUsuario){ //Si es usuario
                    return 1;
                }else{ //Si es CPU
                    return 1;
                }
            case 4: //Si la dificultad es difícil
                if(esUsuario){ //Si es usuario
                    return 1;
                }else{ //Si es CPU
                    return 2;
                }
            case 5: //Si la dificultaqd es muy difícil
                if(esUsuario){ //Si es usuario
                    return 1;
                }else{ //Si es CPU
                    return 3;
                }
            default: return 0;
        }
    }
    
    //MÉTODO PARA ESTABLECER EL TAMAÑO DE LA MATRÍZ DEL TABLERO
    private int establecerTamañoDeTablero(int tamañoDeMapa){
        switch(tamañoDeMapa){ //Establecer el tamaño del tablero (matriz)
            case 1: return 7; //7x7
            case 2: return 8; //8x8
            case 3: return 9; //9x9
            default: return 0;    
        }
    }
    
    //MÉTODO PARA EL MODO DE JUEGO BATALLA
    private void batalla(){
        
        //UBICAR LOS BARCOS DEL USUARIO
        this.usuario.getTablero().ubicarBarcosUsuario(this.usuario);
        this.cpu.getTablero().ubicarBarcosCPU(this.cpu, this.cpu.getTablero().establecerCoordenada(this.cpu, 0, false), this.cpu.getTablero().establecerOrientacion(false), 0, 0, 0);
        
        Instant before = Instant.now();
        
        System.out.println("\n\n\n¡QUE COMIENCE LA BATALLA!\n");
        
        do{
            
                for(int i = 0; i < this.usuario.getFlota().length; i++){
                        if(this.usuario.getFlota()[i].getHabilidad().toString().equalsIgnoreCase("Kowalski")){
                            this.usuario.getFlota()[i].activarHabilidad(this.usuario, this.cpu);
                        }
                }
                
            this.usuario.getTablero().imprimirTablero(this.usuario, true);
            this.cpu.getTablero().imprimirTablero(this.cpu, false);
            
            if((this.usuario.getContadorFallidos() % 5) == 0 && this.usuario.getContadorFallidos() != 0){
                boolean aux = true;
                for(int i = 0; i < this.usuario.getFlota().length; i++){
                    if(aux){
                        if(this.usuario.getFlota()[i].getHabilidad().toString().equalsIgnoreCase("Buscador") && aux){
                            this.usuario.getFlota()[i].activarHabilidad(this.usuario, this.cpu);
                            aux = false;
                        }
                    }
                }
            }
            
            int resp;
            
            do{
                System.out.println("MENÚ (1) "
                        + "\nSEGUIR (2)");
                resp = sc.nextInt();
                
                
                if(resp != 1 && resp != 2){
                    System.out.println("Respuesta inválida, intente nuevamente.");
                }
            }while(resp != 1 && resp != 2);
            
                if(resp == 1){
                    menú();
                }
                
            this.usuario.getTablero().disparar(this.cpu, true);
            this.cpu.getTablero().disparar(this.usuario, false);
            
        }while(this.usuario.estáVivo() && this.cpu.estáVivo());
        
        Instant after = Instant.now();
        
        long miliTotal = Duration.between(before, after).toMillis();
        
        int segundos = (int) (miliTotal / 1000) % 60;
        int minutos = (int) ((miliTotal / (1000*60)) % 60);
        int milisegundos = (int) (miliTotal - ((segundos * 1000) + (minutos * 1000 * 60)));
             
        
        if(this.usuario.estáVivo()){
            System.out.println("¡FELICIDADES, ES EL GANADOR!");
        }else{
            System.out.println("Oh no, ha perdido. El ganador es el CPU.");
        }
        
        System.out.println("Tiempo de la partida: " + minutos + ":" + segundos + ":" + milisegundos);
        
        System.out.println("USUARIO"
                + "\nDisparos acertados: " + this.cpu.getContadorAcertados() + "        Disparos fallados: " + this.cpu.getContadorFallidos()
                + "\nCPU"
                + "\nDisparos acertados: " + this.usuario.getContadorAcertados() + "        Disparos fallados: " + this.usuario.getContadorFallidos());
        
        System.out.println("Porcentajes de disparos acertados:"
                + "\nUSUARIO: " + this.cpu.porcentajeAcertados() 
                + "\nCPU: " + this.usuario.porcentajeAcertados());
    }
    
    public void menú(){
        int resp;
        do{
            System.out.println("¿Qué desea hacer?"
                    + "\n 1. Reiniciar"
                    + "\n 2. Cerrar el juego");
            resp = sc.nextInt();
            
            if(resp != 1 && resp != 2){
                System.out.println("Respuesta inválida, intente nuevamente.");
            }
        }while(resp != 1 && resp != 2);
        
        if(resp == 1){
            ProyectoArzolaFerreira.main(null);
            System.out.println("\n\n\n\n\n\n\n\n");
        }else{
            System.exit(0);
        }
    }
    
    //MÉTODO PARA SABER SI DESEA VOLVER A JUGAR
    private boolean volverAJugar(){
        //VERIABLE PARA REGISTRAR LA RESPUESTA
        int respuesta;
        
        do{
            //SOLICITAR
            System.out.println("¿Desea volver a jugar?"
                    + "\n1. SI"
                    + "\n2. NO");
            respuesta = sc.nextInt();

            //VALIDAR
            if(!(respuesta == 1 || respuesta == 2)){
                System.out.println("\nOpción inválida. Intente nuevamente");
            }
        }while(!(respuesta == 1 || respuesta == 2));
        
        
        //DEVOLVER EL VALOR DEL BOOLEANO
        if(respuesta == 1){ //Si quiere volver a jugar
            return true;
        }else{ //Si no quiere volver a jugar
            return false;
        }
    }

}
