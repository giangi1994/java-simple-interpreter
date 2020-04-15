package it.uniroma1.metodologie;

/**
 * Eccezione che viene lanciata quando la sintassi non e' valida
 */

public class SintassiNonValidaException extends Exception {

    SintassiNonValidaException(){
        super("Sintassi non valida");
    }
}
