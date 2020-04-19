package it.uniroma1.metodologie;

/**
 * Classe tipo di dato intero che estende la superclasse Espressione
 */

public class Intero extends Espressione {

    /**
     * Assumerà il valore dell'intero passato in input al costruttore
     */

    private int valore;

    /**
     * Costruttore del tipo di dato intero
     * @param valore valore intero in input
     */

    public Intero(int valore) {
        super(TipoDiDato.INTERO);
        this.valore = valore;
    }

    /**
     * @return Restituisce il valore intero sottoforma di stringa
     */

    @Override
    public String getValore() { return String.valueOf(valore); }
}
