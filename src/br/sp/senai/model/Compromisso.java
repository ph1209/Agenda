package br.sp.senai.model;

public class Compromisso {
	private int idCompromisso;
	private int idPessoa;
	private String assunto;
	private String local;
	private String contato;
	private String foneContato;
	private String data;
	private String horario;
	
	public int getIdCompromisso() {
		return idCompromisso;
	}
	public void setIdCompromisso(int idCompromisso) {
		this.idCompromisso = idCompromisso;
	}
	public int getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public String getFoneContato() {
		return foneContato;
	}
	public void setFoneContato(String foneContato) {
		this.foneContato = foneContato;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	

}
