package it.uniroma1.metodologie;

/**
 * Interfaccia per le operazioni binarie
 */

public interface EseguiOperazione {

    /**
     * @param e1 espressione 1
     * @param e2 espressione 2
     * @return Ritorna l'espressione finale
     * @throws MaggioreMinoreException
     * @throws TipoVariabiliDiversoException
     */

    Espressione esegui(Espressione e1, Espressione e2) throws MaggioreMinoreException, TipoVariabiliDiversoException;
}
