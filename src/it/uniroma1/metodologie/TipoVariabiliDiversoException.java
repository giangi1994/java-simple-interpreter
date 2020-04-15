package it.uniroma1.metodologie;

/**
 * Eccezione che viene lanciata quando il tipo delle variabili e' diverso
 */

public class TipoVariabiliDiversoException extends Exception {

    TipoVariabiliDiversoException(){ super("Il tipo delle variabili e' diverso"); }
}
