package it.uniroma1.metodologie;

/**
 * Classe tipo di dato booleano che estende la superclasse Espressione
 */

public class Booleano extends Espressione {

    /**
     * Assumerà il valore del booleano passato in input al costruttore
     */

    private boolean valore;

    /**
     * Costruttore del tipo di dato booleano
     * @param valore valore booleano in input
     */

    public Booleano(boolean valore) {
        super(TipoDiDato.BOOLEANO);
        this.valore = valore;
    }

    /**
     * @return Restituisce il valore del booleano sottoforma di stringa
     */

    @Override
    public String getValore() { return String.valueOf(valore); }
}
