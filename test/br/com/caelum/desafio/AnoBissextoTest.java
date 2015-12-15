package br.com.caelum.desafio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AnoBissextoTest {
	
	private AnoBissexto anoBissexto;
	
	@Before
	public void criarInstanciaAnoBissexto(){
		this.anoBissexto = new AnoBissexto();
	}
	
	@Test
	public void verificarSeUmAnoEhBissexto(){
		assertTrue(anoBissexto.ehBissexto(2000));
	}
	
	@Test
	public void verificarSeUmAnoNaoEhBissexto(){
		assertFalse(anoBissexto.ehBissexto(1900));
	}

	@Test
	public void AnoZeroEhBissexto(){
		assertTrue(anoBissexto.ehBissexto(0));
	}
}
