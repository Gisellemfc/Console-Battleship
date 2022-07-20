package Modelo;

/**
 *
 * @author Estudiantes
 */
public abstract class Barco {
    
    //ATRIBUTOS DEL BARCO
    protected int tamañoDelBarco, vida;
    protected ParteDeBarco[] partes;
    protected boolean barcoDañado, barcoDestruido;
    protected String habilidad;
    
    //CONSTRUCTOR DEL BARCO
    public Barco(int tamañoDelBarco, int vida){//Recibe el tamaño y la vida que tendrá cada parte
        this.tamañoDelBarco = tamañoDelBarco;
        this.vida = vida;
        this.partes = new ParteDeBarco[tamañoDelBarco]; //Inicializar el vector de partes del barco que tiene el mismo tamaño que el barco
        for(int i = 0; i < this.partes.length; i++){ //Rellenar el vector con partes de barco
            this.partes[i] = new ParteDeBarco(vida); //Inicializar dichas parte recibiendo su respectiva vida por parámetro
        }
        this.barcoDestruido = false; //Por defecto el barco no está ni dañado ni destruido
        this.barcoDañado = false;
        this.habilidad = ""; //Por defecto el barco no tiene nombrada su habilidad
    }
    
    //MÉTODO PARA SABER SI EL BARCO ESTÁ DAÑADO
    public void estáDañadoElBarco(){
        for(int i = 0; i < this.partes.length; i++){ //Recorrer todas las partes del barco
            if(this.partes[i].isParteDañada() || this.partes[i].isParteDestruida()){ // Si una parte está dañada o destruida
                this.barcoDañado = true; //Entonces el barco está dañado
            }
        }
    }
    
    //MÉTODO PARA SABER SI EL BARCO ESTÁ DESTRUIDO
    public void estáDestruidoElBarco(){
        int contadorDestruidos = 0; //Para contar cuantas partes están destruidas
        
        for(int i = 0; i < this.partes.length; i++){
            if(this.partes[i].isParteDestruida()){ //Si una parte está destruida aumentar el contador
                contadorDestruidos++;
            }
        }
        
        if(contadorDestruidos == this.partes.length){ //Si todas las partes están destruidas, establecer que el barco está destruido
            this.barcoDestruido = true;
        }
    }
    
    //MÉTODO PARA ACTIVAR LA HABILIDAD DE LOS BARCOS
    public abstract void activarHabilidad(Jugador jugador, Jugador oponente);
    
    //GET Y SET
    public ParteDeBarco[] getPartes() {
        return partes;
    }

    public void setPartes(ParteDeBarco[] partes) {
        this.partes = partes;
    }

    public boolean isBarcoDañado() {
        return barcoDañado;
    }

    public void setBarcoDañado(boolean barcoDañado) {
        this.barcoDañado = barcoDañado;
    }

    public boolean isBarcoDestruido() {
        return barcoDestruido;
    }

    public void setBarcoDestruido(boolean barcoDestruido) {
        this.barcoDestruido = barcoDestruido;
    }

    public int getTamañoDelBarco() {
        return tamañoDelBarco;
    }

    public void setTamañoDelBarco(int tamañoDelBarco) {
        this.tamañoDelBarco = tamañoDelBarco;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Barco barco) {
        this.habilidad = barco.getClass().getSimpleName().substring(5);   
    }
    
    
}