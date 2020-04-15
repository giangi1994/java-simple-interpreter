package it.uniroma1.metodologie;

/**
 * Interfaccia per le operazioni binarie
 */

public interface EseguiOperazione {

    /**
     * @param e1 espressione 1
     * @param e2 espressione 2
     * @return Ritorna l'espressione finale
     * @throws MaggioreMinoreException viene lanciata quando nelle operazioni, MINORE, MAGGIORE, MINORE_UGUALE, MAGGIORE_UGUALE, una delle due espressioni non e' di tipo intero
     * @throws TipoVariabiliDiversoException viene lanciata quando il tipo delle variabili e' diverso
     */

    Espressione esegui(Espressione e1, Espressione e2) throws MaggioreMinoreException, TipoVariabiliDiversoException;
}
