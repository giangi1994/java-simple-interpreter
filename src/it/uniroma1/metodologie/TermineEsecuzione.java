package it.uniroma1.metodologie;

import java.util.ArrayList;

/**
 * Classe per la terminazione delle istruzioni che implementa l'interfaccia Istruzione
 */

public class TermineEsecuzione implements Istruzione {

    /**
     * Lista generica
     */

    private ArrayList<?> list = new ArrayList<>();

    /**
     * Costruttore termine esecuzione
     * @param list lista generica
     */

    public TermineEsecuzione(ArrayList<?> list){
        this.list = list;
    }

    /**
     * Svuota la lista
     */

    @Override
    public void esegui(){ list.clear(); }
}
