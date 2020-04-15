package it.uniroma1.metodologie;

/**
 * Eccezione che viene lanciata quando il tipo delle variabili è divero
 */

public class TipoVariabiliDiversoException extends Exception {

    TipoVariabiliDiversoException(){ super("Il tipo delle variabili è diverso"); }
}
