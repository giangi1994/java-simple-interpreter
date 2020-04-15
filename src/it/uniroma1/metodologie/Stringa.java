package it.uniroma1.metodologie;

/**
 * Classe tipo di dato stringa che estende la superclasse Espressione
 */

public class Stringa extends Espressione {

    /**
     * Assumerà il valore della stringa passata in input al costruttore
     */

    private String valore;

    /**
     * Costruttore del tipo di dato stringa
     * @param valore valore della stringa in input
     */

    public Stringa(String valore){
        super.tipo = TipoDiDato.STRINGA;
        this.valore = valore;
    }

    /**
     * @return Restituisce il valore della stringa
     */

    @Override
    public String getValore() { return valore; }
}
