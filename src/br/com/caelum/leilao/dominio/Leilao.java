package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;

	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}

	public void propor(Lance lance) {
		if (lances.isEmpty() || poderDarLance(lance.getUsuario()))
			lances.add(lance);
	}

	private boolean poderDarLance(Usuario usuario) {
		return !recuperarLanceAnterior().getUsuario().equals(usuario)
				&& quantidadeDeLancePorUsuario(usuario) < 5;
	}

	private int quantidadeDeLancePorUsuario(Usuario usuario) {
		int total = 0;
		for (Lance l : lances) {
			if (l.getUsuario().equals(usuario))
				total++;
		}
		return total;
	}

	private Lance recuperarLanceAnterior() {
		return lances.get(lances.size() - 1);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	public void dobrarLance(Usuario usuario) {
		Lance ultimoLance = recuperarUltimoLanceDo(usuario);
		if(ultimoLance!=null){
			propor(new Lance(usuario, ultimoLance.getValor() * 2));	
		}
	}

	private Lance recuperarUltimoLanceDo(Usuario usuario) {
		Lance ultimo = null;
		for (Lance lance : lances) {
			if (lance.getUsuario().equals(usuario))
				ultimo = lance;
		}
		return ultimo;
	}
}
