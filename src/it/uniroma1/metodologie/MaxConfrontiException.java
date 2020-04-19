package it.uniroma1.metodologie;

/**
 * Eccezione che viene lanciata quando si confrontano più di due espressioni
 */

public class MaxConfrontiException extends Exception {

    MaxConfrontiException() { super("Non si posso confrontare più di due espressioni"); }
}
