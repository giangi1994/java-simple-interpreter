package it.uniroma1.metodologie;

/**
 * Classe MiniBASIC
 */

public class MiniBASIC {

    /**
     * Esegue il programma
     * @param p programma
     * @throws SintassiNonValidaException
     * @throws MaxConfrontiException
     * @throws MaggioreMinoreException
     * @throws TipoVariabiliDiversoException
     * @throws VariabileNonDefinitaException
     */

    public void esegui(Programma p) throws
            SintassiNonValidaException,
            MaxConfrontiException,
            MaggioreMinoreException,
            TipoVariabiliDiversoException,
            VariabileNonDefinitaException
    { p.esegui(); }
}
