package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {
	
	@Test
	public void validarLancesEmOrdemCrescente() {
		// Parte 1: cenario
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Usuario jose = new Usuario("Jose");
		
		Leilao leilao = new Leilao("Playstation 3 novo");
		
		leilao.propor(new Lance(joao, 250.0));
		leilao.propor(new Lance(maria, 300.0));
		leilao.propor(new Lance(jose, 400.0));
		
		// Parte 2: Acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avaliar(leilao);
		
		// Parte 3: Validacao
		double maiorEsperado = 400;
		double menorEsperado = 250;
		 		
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void validarLeilaoComApenasUmLance(){
		
		Usuario joao = new Usuario("Joao");
		Leilao leilao = new Leilao("Playstation 3 novo");
		
		leilao.propor(new Lance(joao, 1000.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avaliar(leilao);
		
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
		
	}
	
	@Test
	public void encontrarTresMaioresLances(){
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Playstation 3 Novo");
		
		leilao.propor(new Lance(joao, 100.0));
		leilao.propor(new Lance(maria, 200.0));
		leilao.propor(new Lance(joao, 300.0));
		leilao.propor(new Lance(maria, 400.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avaliar(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
		
		
	}

}
