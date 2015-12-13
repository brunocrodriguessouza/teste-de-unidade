package br.com.caelum.desafio;

import org.junit.Test;
import org.junit.Assert;

public class PalindromoTest {
	@Test
	public void identificarPalindromoEFiltrarCaracteresInvalidos() {
		Palindromo p = new Palindromo();
		boolean resultado = p
				.ehPalindromo("Socorram-me subi no onibus em Marrocos");
		Assert.assertTrue(resultado);
	}

	@Test
	public void identificarPalindromo() {
		Palindromo p = new Palindromo();
		boolean resultado = p.ehPalindromo("Anotaram a data da maratona");
		Assert.assertTrue(resultado);
	}

	@Test
	public void identificarSeNaoEhPalindromo() {
		Palindromo p = new Palindromo();
		boolean resultado = p
				.ehPalindromo("E preciso amar as pessoas como se nao houvesse amanha");
		Assert.assertFalse(resultado);
	}
}
