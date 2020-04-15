package it.uniroma1.metodologie;

/**
 * Eccezione che viene lanciata quando nelle operazioni, MINORE, MAGGIORE, MINOREUGUALE, MAGGIOREUGUALE, una delle due espressioni non e' di tipo intero
 */

public class MaggioreMinoreException extends Exception {

    MaggioreMinoreException(){
        super("Uno dei due tipi non e' intero");
    }
}
