package it.uniroma1.metodologie;

/**
 * Classe per l'etichetta che implementa l'interfaccia Istruzione
 */

public class Etichetta implements Istruzione {

    /**
     * Stringa per l'etichetta
     */

    protected String etichetta;

    /**
     * Costruttore dell'etichetta
     * @param etichetta etichetta
     */

    public Etichetta(String etichetta){ this.etichetta = etichetta; }

    @Override
    public void esegui() { }

    /**
     * @return Ritorna l'etichetta sottoforma di stringa
     */

    @Override
    public String toString(){ return this.etichetta; }
}
