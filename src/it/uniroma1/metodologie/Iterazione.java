package it.uniroma1.metodologie;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe per l'iterazione che implementa l'interfaccia Istruzione e l'interfaccia Iterable
 */

public class Iterazione implements Istruzione, Iterable<Istruzione> {

    /**
     * Array di istruzioni da eseguire dentro al ciclo
     */

    private ArrayList<Istruzione> istruzioni = new ArrayList<>();

    /**
     * Espressioni da confrontare per entrare nel ciclo
     */

    private Espressione v;
    private Espressione e;

    /**
     * Operatore di confronto tra le due Espressioni
     */

    private Operatore operatore;

    /**
     * Incremento
     */

    private int incremento;

    /**
     * Costruttore per l'iterazione
     * @param v
     * @param e
     * @param operatore
     * @param incremento
     * @param istruzioni
     */

    public Iterazione(Espressione v, Espressione e, Operatore operatore, int incremento, ArrayList<Istruzione> istruzioni){
        this.v = v;
        this.e = e;
        this.istruzioni = istruzioni;
        this.operatore = operatore;
        this.incremento = incremento;
    }

    /**
     * @return Ritorna un iteratore
     */

   @Override
    public Iterator<Istruzione> iterator() {

       //classe in place
        return new Iterator<Istruzione>() {

            //contatore
            private int k = 0;

            @Override
            public boolean hasNext() {
                return k < istruzioni.size();
            }

            @Override
            public Istruzione next() {
                return hasNext() ? istruzioni.get(k++) : null;
            }
        };
    }

    /**
     * Esegue l'iterazione
     * @throws MaggioreMinoreException
     * @throws TipoVariabiliDiversoException
     */

    @Override
    public void esegui() throws
            MaggioreMinoreException,
            TipoVariabiliDiversoException
    {
        Intero incremento = new Intero(this.incremento);
        while (Boolean.parseBoolean(new EspressioneComposta(v, e, operatore).getValore())){
            Iterator<Istruzione> it = this.istruzioni.iterator();
            while (it.hasNext()) {
               it.next().esegui();
           }
            EspressioneComposta e = new EspressioneComposta(v, incremento, Operatore.SOMMA);
            new Assegnazione((Variabile)v, new Intero(Integer.parseInt(e.getValore())));
        }
    }
}
