package it.uniroma1.metodologie;

/**
 * Classe per le espressioni composte che estende la superclasse Espressione
 */

public class EspressioneComposta extends Espressione{

    /**
     * Espressione 1
     */

    protected Espressione e1;

    /**
     * Espressione 2
     */

    protected Espressione e2;

    /**
     * Operatore
     */

    protected Operatore op;

    /**
     * Costruttore espressione composta
     * @param e1 espressione 1
     * @param e2 espressione 2
     * @param op operatore
     */

    public EspressioneComposta(Espressione e1, Espressione e2, Operatore op)
    {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
        super.tipo = this.e1.tipo;
    }

    /**
     * @return Ritorna il valore dell'espressione, sottoforma di stringa, dopo aver eseguito l'operazione.
     * L'espressione finale assume il tipo delle due espressioni.
     * @throws MaggioreMinoreException
     * @throws TipoVariabiliDiversoException
     */

    @Override
    public String getValore() throws
            MaggioreMinoreException,
            TipoVariabiliDiversoException
    {
        super.tipo = op.esegui(e1,e2).tipo;
        return op.esegui(e1,e2).getValore();
    }
}
