package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;

public class LeilaoTest {
	
	private Usuario steveJobs;
	private Usuario billGates;
	private Usuario steveWozniak;
	private static final String MCBOOKPRO15 = "Mcbook Pro 15";
	
	@Before
	public void setUp(){
		steveJobs = new Usuario("Steve Jobs");
		steveWozniak = new Usuario("Steve Wozniak");
		billGates = new Usuario("Bill Gates");
	}

	@Test
	public void receberUmLance() {
		Leilao leilao = new CriadorDeLeilao().para(MCBOOKPRO15).construir();
		assertEquals(0, leilao.getLances().size());

		leilao.propor(new Lance(steveJobs, 2000));

		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void receberVariosLances() {
		// Leilao leilao = new Leilao("Mcbook Pro 15");
		// leilao.propor(new Lance(new Usuario("Steve Jobs"), 2000));
		// leilao.propor(new Lance(new Usuario("Steve Wozniak"), 3000.0));

		Leilao leilao = new CriadorDeLeilao().para(MCBOOKPRO15)
				.lance(steveJobs, 2000)
				.lance(steveWozniak, 3000).construir();

		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
	}

	@Test
	public void naoAceitarDoisLancesSeguidosDoMesmoUsuario() {
		
		Leilao leilao = new CriadorDeLeilao().para(MCBOOKPRO15)
				.lance(steveJobs, 2000)
				.lance(steveJobs, 2000).construir();

		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void naoAceitarMaisDoQueCincoLancesDeUmMesmoUsuario() {
		
		Leilao leilao = new CriadorDeLeilao().para(MCBOOKPRO15)
				.lance(steveJobs, 2000.0)
				.lance(billGates, 3000.0)
				.lance(steveJobs, 4000.0)
				.lance(billGates, 5000.0)
				.lance(steveJobs, 6000.0)
				.lance(billGates, 7000.0)
				.lance(steveJobs, 8000.0)
				.lance(billGates, 9000.0)
				.lance(steveJobs, 10000.0)
				.lance(billGates, 11000.0)
				//Deve ser ignorado
				.lance(steveJobs, 12000.0)
				.construir();

		assertEquals(10, leilao.getLances().size());
		assertEquals(11000.0,
				leilao.getLances().get(leilao.getLances().size() - 1)
						.getValor(), 0.00001);

	}

	@Test
	public void dobrarOuUltimoLanceDado() {
		Leilao leilao = new CriadorDeLeilao().para(MCBOOKPRO15)
		.lance(steveJobs, 2000)
		.lance(billGates, 3000)
		.construir();
		
		leilao.dobrarLance(steveJobs);

		assertEquals(4000, leilao.getLances().get(2).getValor(), 0.0001);
	}

	@Test
	public void naoDobrarCasoNaoHajaLanceAnterior() {
		Leilao leilao = new CriadorDeLeilao().para(MCBOOKPRO15)
				.construir();

		leilao.dobrarLance(steveJobs);

		assertEquals(0, leilao.getLances().size());
	}

}
