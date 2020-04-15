package it.uniroma1.metodologie;

/**
 * Classe per la stampa che implementa l'interfaccia Istruzione
 */

public class Print implements Istruzione {

    /**
     * Espressione da stampare
     */

    private Espressione espressione;

    /**
     * Costruttore per la stampa
     * @param espressione espressione da stampare
     */

    public Print(Espressione espressione){ this.espressione = espressione; }

    /**
     * Stampa a video il valore dell'espressione
     * @throws MaggioreMinoreException
     * @throws TipoVariabiliDiversoException
     */

    @Override
    public void esegui() throws
            MaggioreMinoreException,
            TipoVariabiliDiversoException
    { System.out.println(espressione.getValore()); }
}
