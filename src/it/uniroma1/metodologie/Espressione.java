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
     * @throws MaggioreMinoreException viene lanciata quando nelle operazioni, MINORE, MAGGIORE, MINORE_UGUALE, MAGGIORE_UGUALE, una delle due espressioni non e' di tipo intero
     * @throws TipoVariabiliDiversoException viene lanciata quando il tipo delle variabili e' diverso
     */

    abstract String getValore() throws MaggioreMinoreException, TipoVariabiliDiversoException;
}
