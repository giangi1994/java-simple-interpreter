package it.uniroma1.metodologie;

/**
 * Classe per l'assegnazione che implementa l'interfaccia Istruzione
 */

public class Assegnazione implements Istruzione {

    /**
     * Variabile per l'assegnazione
     */

    protected Variabile variabile;

    /**
     * Espressione da assegnare alla variabile
     */

    protected Espressione espressione;

    /**
     * Costruttore dell'assegnazione
     * @param variabile variabile per l'assegnazione
     * @param espressione espressione da assegnare alla variabile
     */

    public Assegnazione(Variabile variabile, Espressione espressione){
        this.variabile = variabile;
        this.espressione = espressione;
    }

    /**
     * Assegna l'espressione alla variabile
     */

    @Override
    public void esegui(){
        this.variabile.espressione = espressione;
        this.variabile.tipo = espressione.tipo;
    }
}
