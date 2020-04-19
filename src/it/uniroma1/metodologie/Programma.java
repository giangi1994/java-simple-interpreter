package it.uniroma1.metodologie;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe Programma
 */

public class Programma {

    /**
     * Lista di variabili da $0 a $9
     */

    private ArrayList<Espressione> variabili = new ArrayList<>();

    /**
     * Lista di tutte le righe del file
     */

    private ArrayList<String> righeFile = new ArrayList<>();

    /**
     * Lista etichette del programma
     */

    private ArrayList<String> etichette = new ArrayList<>();

    /**
     * Nome del file
     */

    private String nomeFile;

    /**
     * Lista di istruzioni
     */

    private ArrayList<Istruzione> istruzioni = new ArrayList<>();

    /**
     * Costruttore del programma con input il nome del file. Tutte le variabili all'inizio hanno il valore undefined
     * @param nomeFile nome del file
     */

    public Programma(String nomeFile) {
        for (int i=0; i<10; i++) this.variabili.add(i, new Stringa("undefined"));
        this.nomeFile = nomeFile;
    }

    /**
     * Costruttore del programma con input la lista delle istruzioni
     * @param istruzioni istruzioni da eseguire
     */

    public Programma(ArrayList<Istruzione> istruzioni) { this.istruzioni = istruzioni; }

    /**
     * Esegue il dispatcher dell'array di stringhe passato in input
     * @param s array contenente le stringhe su cui eseguire il dispatcher
     * @return Ritorna la lista di espressioni
     * @throws VariabileNonDefinitaException viene lanciata quando una variabile non e' definita
     * @throws MaggioreMinoreException viene lanciata quando nelle operazioni, MINORE, MAGGIORE, MINORE_UGUALE, MAGGIORE_UGUALE, una delle due espressioni non e' di tipo intero
     * @throws TipoVariabiliDiversoException viene lanciata quando il tipo delle variabili e' diverso
     */

    private ArrayList<Espressione> dispatcher(String[] s) throws
            VariabileNonDefinitaException,
            MaggioreMinoreException,
            TipoVariabiliDiversoException
    {
        ArrayList<Espressione> e1 = new ArrayList<>();
        for (int i=0; i<s.length; i++) {

            switch (s[i].charAt(0)) {
                //se la stringa inizia per $
                case '$':
                    if (variabili.get(Integer.parseInt(String.valueOf(s[i].charAt(1)))).getValore() != "undefined") e1.add(variabili.get(Integer.parseInt(String.valueOf(s[i].charAt(1)))));
                    else throw new VariabileNonDefinitaException(Integer.parseInt(String.valueOf(s[i].charAt(1))));break;
                //se la stringa inizia per "
                case '\"': e1.add(new Stringa(s[i].replace("\"", "")));break;
                //se la strina inizia per t o per f
                case 't': case 'f': e1.add(new Booleano(Boolean.parseBoolean(s[i])));break;
                //se la stringa è un numero
                default: e1.add(new Intero(Integer.parseInt(s[i])));break;
            }
        }
        return e1;
    }

    /**
     * ASSEGNAZIONE
     */

    /**
     * Prima esegue l'espressione composta e poi assegna il valore alla variabile verificando il tipo dell'espressione
     * @param arE lista di espressioni su cui eseguire l'operazione
     * @param operatore operatore
     * @param valore variabile
     * @return Ritorna un oggetto Assegnazione
     * @throws MaxConfrontiException viene lanciata quando si confrontano più di due espressioni
     * @throws MaggioreMinoreException viene lanciata quando nelle operazioni, MINORE, MAGGIORE, MINORE_UGUALE, MAGGIORE_UGUALE, una delle due espressioni non e' di tipo intero
     * @throws TipoVariabiliDiversoException viene lanciata quando il tipo delle variabili e' diverso
     * @throws SintassiNonValidaException viene lanciata quando la sintassi non e' valida
     */

    private Assegnazione assegna(ArrayList<Espressione> arE, Operatore operatore, int valore) throws
            MaxConfrontiException,
            MaggioreMinoreException,
            TipoVariabiliDiversoException,
            SintassiNonValidaException
    {
        String valoreFinale = "";
        int j = 2;
        Espressione a = new EspressioneComposta(arE.get(0), arE.get(1), operatore);

        if (arE.size() > 2 && (operatore == Operatore.DISUGUAGLIANZA || operatore == Operatore.UGUAGLIANZA || operatore == Operatore.MINORE || operatore == Operatore.MAGGIORE || operatore == Operatore.MAGGIORE_UGUALE || operatore == Operatore.MINORE_UGUALE)) {
            throw new MaxConfrontiException();
        }
        while (j < arE.size()) {
            Espressione b = new EspressioneComposta(a, arE.get(j), operatore);
            a = b;
            j++;
        }
        valoreFinale = a.getValore();
        switch (a.tipo) {
            case INTERO:
                Variabile v1 = new Variabile(valore);
                Assegnazione as1 = new Assegnazione(v1, new Intero(Integer.parseInt(valoreFinale)));
                this.variabili.set(valore, v1);
                return as1;
            case BOOLEANO:
                Variabile v2 = new Variabile(valore);
                Assegnazione as2 =  new Assegnazione(v2, new Booleano(Boolean.parseBoolean(valoreFinale)));
                this.variabili.set(valore, v2);
                return as2;
            case STRINGA:
                Variabile v3 = new Variabile(valore);
                Assegnazione as3 = new Assegnazione(v3, new Stringa(valoreFinale));
                this.variabili.set(valore, v3);
                return as3;
        }
        throw new SintassiNonValidaException();
    }

    /**
     * Assegna il valore alla variabile verificando il tipo dell'espressione
     * @param arE lista di espressioni da assegnare alla variabile
     * @param valore variabile
     * @return Ritorna un oggetto Assegnazione
     * @throws MaggioreMinoreException viene lanciata quando nelle operazioni, MINORE, MAGGIORE, MINORE_UGUALE, MAGGIORE_UGUALE, una delle due espressioni non e' di tipo intero
     * @throws TipoVariabiliDiversoException viene lanciata quando il tipo delle variabili e' diverso
     * @throws SintassiNonValidaException viene lanciata quando la sintassi non e' valida
     */

    private Assegnazione assegna(ArrayList<Espressione> arE, int valore) throws
            MaggioreMinoreException,
            TipoVariabiliDiversoException,
            SintassiNonValidaException
    {
        String valoreFinale = "";
        valoreFinale = arE.get(0).getValore();

        switch (arE.get(0).tipo) {
            case INTERO:
                Variabile v1 = new Variabile(valore);
                Assegnazione as1 = new Assegnazione(v1, new Intero(Integer.parseInt(valoreFinale)));
                this.variabili.set(valore,v1);
                return as1;
            case BOOLEANO:
                Variabile v2 = new Variabile(valore);
                Assegnazione as2 = new Assegnazione(v2, new Booleano(Boolean.parseBoolean(valoreFinale)));
                this.variabili.set(valore, v2);
                return as2;
            case STRINGA:
                Variabile v3 = new Variabile(valore);
               Assegnazione as3 = new Assegnazione(v3, new Stringa(valoreFinale));
                this.variabili.set(valore, v3);
                return as3;
        }
        throw new SintassiNonValidaException();
    }

    /**
     * Assegna un valore alla variabile
     * @param s riga del file
     * @return Ritorna un oggetto Assegnazione
     * @throws SintassiNonValidaException viene lanciata quando la sintassi non e' valida
     * @throws TipoVariabiliDiversoException viene lanciata quando il tipo delle variabili e' diverso
     * @throws MaggioreMinoreException viene lanciata quando nelle operazioni, MINORE, MAGGIORE, MINORE_UGUALE, MAGGIORE_UGUALE, una delle due espressioni non e' di tipo intero
     * @throws VariabileNonDefinitaException viene lanciata quando una variabile non e' definita
     * @throws MaxConfrontiException viene lanciata quando si confrontano più di due espressioni
     */

    private Assegnazione assegnazione(String s) throws
            SintassiNonValidaException,
            TipoVariabiliDiversoException,
            MaggioreMinoreException,
            VariabileNonDefinitaException,
            MaxConfrontiException
    {
        int valoreVariabile = Integer.parseInt(s.substring(1,2));
        String s1 = s.replace(" ", "").substring(3);
        ArrayList<Espressione> e1 = new ArrayList<>();

        //controllo se il nome della variabile è nel range $0-$9 e non permetto il confronto tra espressioni composte (es. $0+$5 == 11+$2)
        if ((s.replace(" ", "").charAt(2) != '=') || s1.equals("") || (s1.contains("+") && (s1.contains("==") || s1.contains("<>") || s1.contains(">") || s1.contains("<") || s1.contains("<=") || s1.contains(">=")) )) throw new SintassiNonValidaException();
        else if (s1.contains("+")) {
            String[] s2 = s1.replace("+", " ").split(" ");
            e1 = dispatcher(s2);
           return assegna(e1, Operatore.SOMMA, valoreVariabile);
        } else if (s.contains("==")) {
            String[] s2 = s1.replace("==", " ").split(" ");
            e1 = dispatcher(s2);
            return assegna(e1, Operatore.UGUAGLIANZA, valoreVariabile);
        } else if (s.contains("<>")) {
            String[] s2 = s1.replace("<>", " ").split(" ");
            e1 = dispatcher(s2);
           return assegna(e1, Operatore.DISUGUAGLIANZA, valoreVariabile);
        } else if (s.contains("<=")) {
            String s2 = s1.replace("<=", " ");
            String[] s3 = s2.replace("=", " ").split(" ");
            e1 = dispatcher(s3);
            return assegna(e1, Operatore.MINORE_UGUALE, valoreVariabile);
        } else if (s.contains(">=")) {
            String[] s2 = s1.replace(">=", " ").split(" ");
            e1 = dispatcher(s2);
            return assegna(e1, Operatore.MAGGIORE_UGUALE, valoreVariabile);
        } else if (s.contains("<")) {
            String[] s2 = s1.replace("<", " ").split(" ");
            e1 = dispatcher(s2);
            return assegna(e1, Operatore.MINORE, valoreVariabile);
        } else if (s.contains(">")) {
            String[] s2 = s1.replace(">", " ").split(" ");
            e1 = dispatcher(s2);
            return assegna(e1, Operatore.MAGGIORE, valoreVariabile);
        } else {
            String[] s2 = {s1};
            e1 = dispatcher(s2);
            return assegna(e1,valoreVariabile);
        }
    }

    /**
     * STAMPA
     */

    /**
     * Stampa la variabile o la costante di tipo stringa
     * @param s riga del file
     * @return Ritorna un oggetto Print
     * @throws MaggioreMinoreException viene lanciata quando nelle operazioni, MINORE, MAGGIORE, MINORE_UGUALE, MAGGIORE_UGUALE, una delle due espressioni non e' di tipo intero
     * @throws TipoVariabiliDiversoException viene lanciata quando il tipo delle variabili e' diverso
     * @throws SintassiNonValidaException viene lanciata quando la sintassi non e' valida
     */

    private Print print(String s) throws
            MaggioreMinoreException,
            TipoVariabiliDiversoException,
            SintassiNonValidaException
    {
        String s1 = s.replace(" ", "");
        switch (s1.charAt(5)) {
            case '$':
                var valoreVariabile = Integer.parseInt(s.substring(7));
                return new Print(new Stringa(variabili.get(valoreVariabile).getValore()));
            case '\"':
                if (s.charAt(s.length()-1) != '\"') throw new SintassiNonValidaException();
                return new Print(new Stringa(s.substring(7,s.length()-1)));
        }
        throw new SintassiNonValidaException();
    }

    /**
     * SELEZIONE
     */

    /**
     * Dispatcher per la selezione
     * @param e lista di espressioni su cui eseguire l'operazione
     * @param iIf array di stringhe per l'if
     * @param iElse array di stringhe per l'else
     * @param operatore operatore
     * @throws MaggioreMinoreException viene lanciata quando nelle operazioni, MINORE, MAGGIORE, MINORE_UGUALE, MAGGIORE_UGUALE, una delle due espressioni non e' di tipo intero
     * @throws TipoVariabiliDiversoException viene lanciata quando il tipo delle variabili e' diverso
     * @throws SintassiNonValidaException viene lanciata quando la sintassi non e' valida
     * @throws VariabileNonDefinitaException viene lanciata quando una variabile non e' definita
     * @throws MaxConfrontiException viene lanciata quando si confrontano più di due espressioni
     */

    private void dispatcherSelezione(ArrayList<Espressione> e,String[] iIf, String[] iElse, Operatore operatore) throws
            MaggioreMinoreException,
            TipoVariabiliDiversoException,
            SintassiNonValidaException,
            VariabileNonDefinitaException,
            MaxConfrontiException
    {
        EspressioneComposta eC = new EspressioneComposta(e.get(0), e.get(1),operatore);

        if (Boolean.parseBoolean(eC.getValore())) {
            for (int j=1; j<iIf.length; j++) {
                switch (iIf[j].charAt(0)) {
                    case '$': assegnazione(iIf[j]).esegui();break;
                    case 'P': print(iIf[j]).esegui();break;
                    case 'E': termineEsecuzione().esegui();break;
                }
            }
        } else {
            for (int k=0; k<iElse.length; k++) {
                switch (iElse[k].charAt(0)) {
                    case '$': assegnazione(iElse[k]).esegui();break;
                    case 'P': print(iElse[k]).esegui();break;
                    case 'E': termineEsecuzione().esegui();break;
                }
            }
        }
    }

    /**
     * Esegue la selezione
     * @param s s riga del file
     * @throws TipoVariabiliDiversoException viene lanciata quando il tipo delle variabili e' diverso
     * @throws MaggioreMinoreException viene lanciata quando nelle operazioni, MINORE, MAGGIORE, MINORE_UGUALE, MAGGIORE_UGUALE, una delle due espressioni non e' di tipo intero
     * @throws VariabileNonDefinitaException viene lanciata quando una variabile non e' definita
     * @throws SintassiNonValidaException viene lanciata quando la sintassi non e' valida
     * @throws MaxConfrontiException viene lanciata quando si confrontano più di due espressioni
     */

    private void selezione(String s) throws
            TipoVariabiliDiversoException,
            MaggioreMinoreException,
            VariabileNonDefinitaException,
            SintassiNonValidaException,
            MaxConfrontiException
    {
        ArrayList<Espressione> e1 = new ArrayList<>();
        String s1 = s.substring(3);
        String[] s2 = s1.replace("THEN", "").replace(":", "").split("ELSE ");
        //istruzioni per l'if
        String[] iIf = s2[0].trim().split("  ");
       //istruzioni per l'else
        String[] iElse = new String[0];
        if (s2.length>1) iElse = s2[1].trim().split("  ");

        if (s1.contains("==")) {
            String[] s3 = iIf[0].replace(" ", "").replace("==", " ").split(" ");
            e1 = dispatcher(s3);
            dispatcherSelezione(e1,iIf,iElse,Operatore.UGUAGLIANZA);
        } else if (s1.contains("<>")) {
            String[] s3 = iIf[0].replace(" ", "").replace("<>", " ").split(" ");
            e1 = dispatcher(s3);
            dispatcherSelezione(e1,iIf,iElse,Operatore.DISUGUAGLIANZA);
        } else if (s1.contains("<=")) {
            String[] s3 = iIf[0].replace(" ", "").replace("<=", " ").split(" ");
            e1 = dispatcher(s3);
            dispatcherSelezione(e1,iIf,iElse,Operatore.MINORE_UGUALE);
        } else if (s1.contains(">=")) {
            String[] s3 = iIf[0].replace(" ", "").replace(">=", " ").split(" ");
            e1 = dispatcher(s3);
            dispatcherSelezione(e1,iIf,iElse,Operatore.MAGGIORE_UGUALE);
        } else if (s1.contains("<")) {
            String[] s3 = iIf[0].replace(" ", "").replace("<", " ").split(" ");
            e1 = dispatcher(s3);
            dispatcherSelezione(e1,iIf,iElse,Operatore.MINORE);
        } else if (s1.contains(">")) {
            String[] s3 = iIf[0].replace(" ", "").replace(">", " ").split(" ");
            e1 = dispatcher(s3);
            dispatcherSelezione(e1,iIf,iElse,Operatore.MAGGIORE);
        } else throw new SintassiNonValidaException();
    }

    /**
     * ITERAZIONE
     */

    /**
     * Dispatcher per l'iterazione
     * @param s2 array di stringhe che contiene le istruzioni
     * @throws SintassiNonValidaException viene lanciata quando la sintassi non e' valida
     * @throws MaxConfrontiException viene lanciata quando si confrontano più di due espressioni
     * @throws MaggioreMinoreException viene lanciata quando nelle operazioni, MINORE, MAGGIORE, MINORE_UGUALE, MAGGIORE_UGUALE, una delle due espressioni non e' di tipo intero
     * @throws TipoVariabiliDiversoException viene lanciata quando il tipo delle variabili e' diverso
     * @throws VariabileNonDefinitaException viene lanciata quando una variabile non e' definita
     */

    private void dispatcherIterazione(String[] s2) throws
            SintassiNonValidaException,
            MaxConfrontiException,
            MaggioreMinoreException,
            TipoVariabiliDiversoException,
            VariabileNonDefinitaException
    {
        for (int j = 1; j < s2.length; j++) {
            switch (s2[j].charAt(0)) {
                case '$': assegnazione(s2[j]).esegui();break;
                case 'P': print(s2[j]).esegui();break;
                case 'E' : termineEsecuzione().esegui();break;
            }
        }
    }

    /**
     * Esegue l'iterazione
     * @param s riga del file
     * @throws TipoVariabiliDiversoException viene lanciata quando il tipo delle variabili e' diverso
     * @throws MaggioreMinoreException viene lanciata quando nelle operazioni, MINORE, MAGGIORE, MINORE_UGUALE, MAGGIORE_UGUALE, una delle due espressioni non e' di tipo intero
     * @throws VariabileNonDefinitaException viene lanciata quando una variabile non e' definita
     * @throws SintassiNonValidaException viene lanciata quando la sintassi non e' valida
     * @throws MaxConfrontiException viene lanciata quando si confrontano più di due espressioni
     */

    private void iterazione(String s) throws
            TipoVariabiliDiversoException,
            MaggioreMinoreException,
            VariabileNonDefinitaException,
            SintassiNonValidaException,
            MaxConfrontiException
    {
        ArrayList<Espressione> e1 = new ArrayList<>();
        String s1 = s.substring(6);
        ArrayList<Istruzione> i = new ArrayList<>();
        String[] s2 = s1.replace("DO", "").replace(":", "").split("  ");
        String[] confronto = s2[0].trim().split("  ");
        String s3 = "";
        for (int k=0; k<s2.length; k++) {
            if (s2[k].contains("=")) s3 = String.valueOf(s2[k].charAt(s2[k].length()-1));
        }

        if (s1.contains("==")) {
            String[] s4 = confronto[0].replace(" ", "").replace("==", " ").split(" ");
            e1 = dispatcher(s4);
            int inizio = Integer.parseInt(e1.get(0).getValore());
            int fine = Integer.parseInt(e1.get(1).getValore());
            while (inizio == fine) {
                dispatcherIterazione(s2);
                inizio+=Integer.parseInt(s3);
            }
        } else if (s1.contains("<>")) {
            String[] s4 = confronto[0].replace(" ", "").replace("<>", " ").split(" ");
            e1 = dispatcher(s4);
            int inizio = Integer.parseInt(e1.get(0).getValore());
            int fine = Integer.parseInt(e1.get(1).getValore());
            while (inizio != fine) {
                dispatcherIterazione(s2);
                inizio+=Integer.parseInt(s3);
            }
        } else if (s1.contains("<=")) {
            String[] s4 = confronto[0].replace(" ", "").replace("<=", " ").split(" ");
            e1 = dispatcher(s4);
            int inizio = Integer.parseInt(e1.get(0).getValore());
            int fine = Integer.parseInt(e1.get(1).getValore());
            while (inizio <= fine) {
                dispatcherIterazione(s2);
                inizio+=Integer.parseInt(s3);
            }
        } else if (s1.contains(">=")) {
            String[] s4 = confronto[0].replace(" ", "").replace(">=", " ").split(" ");
            e1 = dispatcher(s4);
            int inizio = Integer.parseInt(e1.get(0).getValore());
            int fine = Integer.parseInt(e1.get(1).getValore());
            while (inizio >= fine) {
                dispatcherIterazione(s2);
                inizio+=Integer.parseInt(s3);
            }
        } else if (s1.contains("<")) {
            String[] s4 = confronto[0].replace(" ", "").replace("<", " ").split(" ");
            e1 = dispatcher(s4);
            int inizio = Integer.parseInt(e1.get(0).getValore());
            int fine = Integer.parseInt(e1.get(1).getValore());
            while (inizio < fine) {
                dispatcherIterazione(s2);
                inizio+=Integer.parseInt(s3);
            }
        } else if (s1.contains(">")) {
            String[] s4 = confronto[0].replace(" ", "").replace(">", " ").split(" ");
            e1 = dispatcher(s4);
            int inizio = Integer.parseInt(e1.get(0).getValore());
            int fine = Integer.parseInt(e1.get(1).getValore());
            while (inizio > fine) {
                dispatcherIterazione(s2);
                inizio+=Integer.parseInt(s3);
            }
        } else throw new SintassiNonValidaException();
    }

    /**
     * COMMENTO
     */

    /**
     * @param s riga del file
     * @return Ritorna un oggetto Commento
     */

    private Commento commento(String s) {
        String s1 = s.substring(4);
        return new Commento(s1);
    }

    /**
     * TERMINE ESECUZIONE
     */

    /**
     * @return Ritorna un oggetto TermineEsecuzione
     */

    private TermineEsecuzione termineEsecuzione() { return new TermineEsecuzione(this.righeFile); }

    /**
     * ETICHETTA
     */

    /**
     * Aggiunge un'eticchetta alla lista di etichette con la rispettiva posizione nel file
     * @param s riga del file
     * @param index numero riga del file dove e' presente l'etichetta
     */

    private void setEtichetta(String s, int index) {
        String s1 = s.replace(":", "");
        etichette.add(s1);
        etichette.add(String.valueOf(index));
    }

    /**
     * SALTO
     */

    /**
     * @param s riga del file
     * @return Ritorna il numero della riga su cui bisogna ritornare
     */

    private int salto(String s) {
        String s1 = s.replace("GOTO", "").trim();
        int index = 0;
        for (int k = 0; k<etichette.size(); k++) {
           if (etichette.get(k).equals(s1)) index = Integer.parseInt(etichette.get(k+1));break;
        }
        return index+1;
    }

    /**
     * Legge le righe del file e le aggiunge alla lista di stringhe se la riga non è vuota
     * @return Ritorna la lista con tutte le righe del file
     */

    private ArrayList<String> readFile() {
        ArrayList<String> list = new ArrayList<>();
        String riga = "";

        try (BufferedReader br = Files.newBufferedReader(Paths.get(nomeFile))) {
            while (br.ready()) {
                riga = br.readLine().trim();
                if(!riga.equals("")) list.add(riga);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Esegue il programma
     * @throws SintassiNonValidaException viene lanciata quando la sintassi non e' valida
     * @throws MaxConfrontiException viene lanciata quando si confrontano più di due espressioni
     * @throws MaggioreMinoreException viene lanciata quando nelle operazioni, MINORE, MAGGIORE, MINORE_UGUALE, MAGGIORE_UGUALE, una delle due espressioni non e' di tipo intero
     * @throws TipoVariabiliDiversoException viene lanciata quando il tipo delle variabili e' diverso
     * @throws VariabileNonDefinitaException viene lanciata quando una variabile non eì definita
     */

    public void esegui() throws
            SintassiNonValidaException,
            MaxConfrontiException,
            MaggioreMinoreException,
            TipoVariabiliDiversoException,
            VariabileNonDefinitaException
    {
        if (istruzioni.size() > 0) {
            int k = 0;
            int salto = -1;
            while (k < istruzioni.size()) {
                    if (istruzioni.get(k) instanceof Etichetta) setEtichetta(istruzioni.get(k).toString(),k);
                    if (istruzioni.get(k) instanceof Salto) salto = salto(istruzioni.get(k).toString());
                    istruzioni.get(k).esegui();
                    if (salto<0) k++;
                    else {
                        k=salto;
                        salto=-1;
                    }
                }
        } else {
            righeFile = readFile();
            int i = 0;
            int salto = -1;
            while (i < righeFile.size()) {
                if (righeFile.get(i).charAt(righeFile.get(i).length() - 1) == ':') setEtichetta(righeFile.get(i), i);
                switch (righeFile.get(i).charAt(0)) {
                    case '$': assegnazione(righeFile.get(i)).esegui();break;
                    case 'P': print(righeFile.get(i)).esegui();break;
                    case 'I': selezione(righeFile.get(i));break;
                    case 'W': iterazione(righeFile.get(i));break;
                    case 'R': commento(righeFile.get(i));break;
                    case 'E': termineEsecuzione().esegui();break;
                    case 'G': salto = salto(righeFile.get(i));break;
                    default: break;
                }
                if (salto < 0) i++;
                else {
                    i = salto;
                    salto = -1;
                }
            }
        }
    }

    /**
     * @param nomeFile nome del file
     * @return Ritorna un'istanza di Programma con input il nome del file
     */

    public static Programma fromFile(String nomeFile) { return new Programma(nomeFile); }

    /**
     * @param istruzioni istruzioni da eseguire
     * @return Ritorna un'istanza di programma con input la lista di istruzioni
     */

    public static Programma of(ArrayList<Istruzione> istruzioni) { return new Programma(istruzioni); }
}
