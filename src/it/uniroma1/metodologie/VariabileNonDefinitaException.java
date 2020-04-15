package it.uniroma1.metodologie;

/**
 * Eccezione che viene lanciata quando una variabile non è definita
 */

public class VariabileNonDefinitaException extends Exception {

    VariabileNonDefinitaException(int varibile){
        super("$" + varibile + " non è definita");
    }
}
