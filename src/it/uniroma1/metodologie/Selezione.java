package it.uniroma1.metodologie;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe per la selezione che implementa l'interfaccia Istruzione
 */

public class Selezione implements Istruzione {

    /**
     * Espressione booleana da verificare
     */

    private Espressione espressione;

    /**
     * Istruzioni per l'if
     */

    private ArrayList<Istruzione> istruzioniIf = new ArrayList<>();

    /**
     * Istruzioni per l'else
     */

    private ArrayList<Istruzione> istruzioniElse = new ArrayList<>();

    /**
     * Costruttore Selezione con istruzioni per l'if
     * @param espressione espressione booleana da verificare
     * @param istruzioniIf istruzioni per l'if
     */

    public Selezione(Espressione espressione, ArrayList<Istruzione> istruzioniIf){
        this.espressione = espressione;
        this.istruzioniIf = istruzioniIf;
    }

    /**
     * Costruttore Selezione con istruzioni per l'if espressione per l'else
     * @param espressione
     * @param istruzioniIf
     * @param istruzioniElse
     */

    public Selezione(Espressione espressione, ArrayList<Istruzione> istruzioniIf, ArrayList<Istruzione> istruzioniElse){
        this(espressione, istruzioniIf);
        this.istruzioniElse = istruzioniElse;
    }

    /**
     * Esegue la selezione
     * @throws MaggioreMinoreException
     * @throws TipoVariabiliDiversoException
     */

    @Override
    public void esegui() throws
            MaggioreMinoreException,
            TipoVariabiliDiversoException
    {

        //iteratore per le istruzione dell'if
        Iterator<Istruzione> itIf = this.istruzioniIf.iterator();

        if (this.istruzioniElse.size() == 0) { if (Boolean.parseBoolean(espressione.getValore())) while(itIf.hasNext()) itIf.next().esegui(); }
        else {

            //iteratore per le istruzioni dell'else
            Iterator<Istruzione> itElse = this.istruzioniElse.iterator();

            if (Boolean.parseBoolean(espressione.getValore())) while(itIf.hasNext()) itIf.next().esegui();
            else while(itElse.hasNext()) itElse.next().esegui();
        }
    }
}

