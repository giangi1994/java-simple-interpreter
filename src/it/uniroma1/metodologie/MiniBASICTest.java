package it.uniroma1.metodologie;

import org.junit.jupiter.api.Test;

/**
 * Nota bene: questo codice non deve essere in nessun modo modificato
 * 
 * @author navigli
 *
 */
class MiniBASICTest
{
	@Test
	void testSuccessione28()
	{
		System.out.println("Successione/28\n---");
		try
		{
			Programma p = Programma.fromFile("prg/28/successione.bas");
			new MiniBASIC().esegui(p);
		}
		catch(Exception e) { e.printStackTrace(); }
	}

	@Test
	void testFibonacci28()
	{
		System.out.println("Fibonacci/28\n---");
		try
		{
			Programma p = Programma.fromFile("prg/28/fibonacci.bas");
			new MiniBASIC().esegui(p);
		}
		catch(Exception e) { e.printStackTrace(); }
	}

	@Test
	void testPrg1()
	{
		System.out.println("Prg1/full\n---");
		try
		{
			Programma p = Programma.fromFile("prg/full/prg1.bas");
			new MiniBASIC().esegui(p);
		}
		catch(Exception e) { e.printStackTrace(); }
	}

	@Test
	void testFibonacci()
	{
		System.out.println("Fibonacci/full\n---");
		try
		{
			Programma p = Programma.fromFile("prg/full/fibonacci.bas");
			new MiniBASIC().esegui(p);
		}
		catch(Exception e) { e.printStackTrace(); }
	}

	@Test
	void testPariDispari()
	{
		System.out.println("PariDispari/full\n---");
		try
		{
			Programma p = Programma.fromFile("prg/full/pari_dispari.bas");
			new MiniBASIC().esegui(p);
		}
		catch(Exception e) { e.printStackTrace(); }
	}
}
