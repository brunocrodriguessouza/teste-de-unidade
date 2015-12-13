package br.com.caelum.leilao.dominio;

public class Usuario {

	static int incremento = 0;
	private int id;
	private String nome;
	
	public Usuario(String nome) {
		this(++incremento, nome);
	} 

	public Usuario(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}	
}
