package it.uniroma1.metodologie;

/**
 * Classe astratta Espressione
 */

abstract public class Espressione {

    /**
     * Mantiene il tipo di dato dell'espressione
     */

    protected TipoDiDato tipo;

    /**
     * @return Ritorna il valore dell'espressione sottoforma di stringa
     * @throws MaggioreMinoreException
     * @throws TipoVariabiliDiversoException
     */

    abstract String getValore() throws MaggioreMinoreException, TipoVariabiliDiversoException;
}
