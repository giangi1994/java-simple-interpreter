package it.uniroma1.metodologie;

/**
 * Interfaccia Istruzione
 */

public interface Istruzione {

    /**
     * Esegue una specifica istruzione
     * @throws MaggioreMinoreException
     * @throws TipoVariabiliDiversoException
     */

    void esegui() throws MaggioreMinoreException, TipoVariabiliDiversoException;
}
