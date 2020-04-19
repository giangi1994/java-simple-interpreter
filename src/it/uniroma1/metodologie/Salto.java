package it.uniroma1.metodologie;

/**
 * Classe per il GOTO che implementa l'interfaccia Istruzione
 */

public class Salto implements Istruzione {

    /**
     * Riferimento all'etichetta
     */

    private Etichetta etichetta;

    /**
     * Costruttore per il salto
     * @param etichetta etichetta
     */

    public Salto(Etichetta etichetta) { this.etichetta = etichetta; }

    @Override
    public void esegui() { }

    /**
     * @return Ritorna l'etichetta sottoforma di stringa
     */

    @Override
    public String toString() { return this.etichetta.toString(); }
}
