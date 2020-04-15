package it.uniroma1.metodologie;

/**
 * Classe MiniBASIC
 */

public class MiniBASIC {

    /**
     * Esegue il programma
     * @param p programma
     * @throws SintassiNonValidaException viene lanciata quando la sintassi non è valida
     * @throws MaxConfrontiException viene lanciata quando si confrontano più di due espressioni
     * @throws MaggioreMinoreException viene lanciata quando nelle operazioni, MINORE, MAGGIORE, MINORE_UGUALE, MAGGIORE_UGUALE, una delle due espressioni non e' di tipo intero
     * @throws TipoVariabiliDiversoException viene lanciata quando il tipo delle variabili e' diverso
     * @throws VariabileNonDefinitaException viene lanciata quando una variabile non e' definita
     */

    public void esegui(Programma p) throws
            SintassiNonValidaException,
            MaxConfrontiException,
            MaggioreMinoreException,
            TipoVariabiliDiversoException,
            VariabileNonDefinitaException
    { p.esegui(); }
}
