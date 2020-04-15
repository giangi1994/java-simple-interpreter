package it.uniroma1.metodologie;

/**
 * Eccezione che viene lanciata quando nelle operazioni <, >, <=, >= una delle due espressioni non è di tipo intero
 */

public class MaggioreMinoreException extends Exception {

    MaggioreMinoreException(){
        super("Uno dei due tipi non è intero");
    }
}
