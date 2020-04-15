package it.uniroma1.metodologie;

/**
 * Classe per le variabili che estende la superclasse Espressione
 */

public class Variabile extends Espressione {

    /**
     * Stringa che defiinisce la variabile
     */

    protected String variabile;

    /**
     * Espressione da assegnare alla variabile
     */

    protected Espressione espressione;

    /**
     * Costruttore della variabile
     * @param valore variabile
     */

    public Variabile(int valore){ this.variabile = "$" + valore; }

    /**
     * @return Ritorna il valore della varibile sottoforma di stringa
     * @throws MaggioreMinoreException
     * @throws TipoVariabiliDiversoException
     */

    @Override
   public String getValore() throws
            MaggioreMinoreException,
            TipoVariabiliDiversoException
    { return this.espressione.getValore(); }
}
