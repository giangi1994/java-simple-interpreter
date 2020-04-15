package it.uniroma1.metodologie;

/**
 * Enumerazione per le operazioni che implementa l'interfaccia EseguiOperazione
 */

public enum Operatore implements EseguiOperazione {

    /**
     * Operatore ==
     */

    UGUAGLIANZA {

        /**
         * @param e1 espressione 1
         * @param e2 espressione 2
         * @return Ritorna l'espressione booleana dopo aver confrontato se le due espressioni in input sono uguali
         * @throws MaggioreMinoreException
         * @throws TipoVariabiliDiversoException
         */

        @Override
        public Espressione esegui(Espressione e1, Espressione e2) throws
                MaggioreMinoreException,
                TipoVariabiliDiversoException
        { return new Booleano(e1.getValore().equals(e2.getValore())); }
    },

    /**
     * Operatore <>
     */

    DISUGUAGLIANZA{

        /**
         * @param e1 espressione 1
         * @param e2 espressione 2
         * @return Ritorna l'espressione booleana dopo aver confrontato se le due espressioni in input sono diverse
         * @throws MaggioreMinoreException
         * @throws TipoVariabiliDiversoException
         */

        @Override
        public Espressione esegui(Espressione e1, Espressione e2) throws
                MaggioreMinoreException,
                TipoVariabiliDiversoException
        { return new Booleano(!e1.getValore().equals(e2.getValore())); }
    },

    /**
     * Operatore >
     */

    MAGGIORE{

        /**
         * @param e1 espressione 1 (INTERO)
         * @param e2 espressione 2 (INTERO)
         * @return Ritorna l'espressione booleana dopo aver confrontato se l'espressione e1 è maggiore dell'espressione e2.
         * @throws MaggioreMinoreException
         * @throws TipoVariabiliDiversoException
         */

        @Override
        public Espressione esegui(Espressione e1, Espressione e2) throws
                MaggioreMinoreException,
                TipoVariabiliDiversoException
        {
            if(e1.tipo != TipoDiDato.INTERO || e2.tipo != TipoDiDato.INTERO) throw new MaggioreMinoreException();
            return new Booleano(Integer.parseInt(e1.getValore()) > Integer.parseInt(e2.getValore()));
        }
    },

    /**
     * Operatore <
     */

    MINORE{

        /**
         * @param e1 espressione 1 (INTERO)
         * @param e2 espressione 2 (INTERO)
         * @return Ritorna l'espressione booleana dopo aver confrontato se l'espressione e1 è minore dell'espressione e2.
         * @throws MaggioreMinoreException
         * @throws TipoVariabiliDiversoException
         */

        @Override
        public Espressione esegui(Espressione e1, Espressione e2) throws
                MaggioreMinoreException,
                TipoVariabiliDiversoException
        {
            if(e1.tipo != TipoDiDato.INTERO || e2.tipo != TipoDiDato.INTERO) throw new MaggioreMinoreException();
            return new Booleano(Integer.parseInt(e1.getValore()) < Integer.parseInt(e2.getValore()));
        }
    },

    /**
     * Operatore >=
     */

    MAGGIOREUGUALE{

        /**
         * @param e1 espressione 1 (INTERO)
         * @param e2 espressione 2 (INTERO)
         * @return Ritorna l'espressione booleana dopo aver confrontato se l'espressione e1 è maggiore o uguale dell'espressione e2.
         * @throws MaggioreMinoreException
         * @throws TipoVariabiliDiversoException
         */

        @Override
        public Espressione esegui(Espressione e1, Espressione e2) throws
                MaggioreMinoreException,
                TipoVariabiliDiversoException
        {
            if(e1.tipo != TipoDiDato.INTERO || e2.tipo != TipoDiDato.INTERO) throw new MaggioreMinoreException();
            return new Booleano(Integer.parseInt(e1.getValore()) >= Integer.parseInt(e2.getValore()));
        }
    },

    /**
     * Operatore <=
     */

    MINOREUGUALE{

        /**
         * @param e1 espressione 1 (INTERO)
         * @param e2 espressione 2 (INTERO)
         * @return Ritorna l'espressione booleana dopo aver confrontato se l'espressione e1 è minore o uguale dell'espressione e2.
         * @throws MaggioreMinoreException
         * @throws TipoVariabiliDiversoException
         */

        @Override
        public Espressione esegui(Espressione e1, Espressione e2) throws
                MaggioreMinoreException,
                TipoVariabiliDiversoException
        {
            if(e1.tipo != TipoDiDato.INTERO || e2.tipo != TipoDiDato.INTERO) throw new MaggioreMinoreException();
            return new Booleano(Integer.parseInt(e1.getValore()) <= Integer.parseInt(e2.getValore()));
        }
    },

    /**
     * Operatore +
     */

    SOMMA{

        /**
         * @param e1 espressione 1
         * @param e2 espressione 2
         * @return Ritorna l'espressione dopo aver sommato le due espressioni in input, solo se sono dello stesso tipo.
         * @throws MaggioreMinoreException
         * @throws TipoVariabiliDiversoException
         */

        @Override
        public Espressione esegui(Espressione e1, Espressione e2) throws
                MaggioreMinoreException,
                TipoVariabiliDiversoException
        {
            if (e1.tipo != e2.tipo) throw new TipoVariabiliDiversoException();

            switch (e1.tipo) {
                    case INTERO: return new Intero(Integer.parseInt(e1.getValore()) + Integer.parseInt(e2.getValore()));
                    case STRINGA: return new Stringa(e1.getValore() + e2.getValore());
                    case BOOLEANO: return new Booleano(Boolean.parseBoolean(e1.getValore()) || Boolean.parseBoolean(e2.getValore()));
            }
                return null;
        }
    }
}
