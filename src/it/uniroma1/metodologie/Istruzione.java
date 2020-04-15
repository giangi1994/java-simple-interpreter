package it.uniroma1.metodologie;

/**
 * Interfaccia Istruzione
 */

public interface Istruzione {

    /**
     * Esegue una specifica istruzione
     * @throws MaggioreMinoreException viene lanciata quando nelle operazioni, MINORE, MAGGIORE, MINORE_UGUALE, MAGGIORE_UGUALE, una delle due espressioni non e' di tipo intero
     * @throws TipoVariabiliDiversoException viene lanciata quando il tipo delle variabili e' diverso
     */

    void esegui() throws MaggioreMinoreException, TipoVariabiliDiversoException;
}
