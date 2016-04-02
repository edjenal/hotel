package dto;

import java.util.Date;

import util.FormataData;

public class ClienteDto {
	
	private Integer id;
	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	private String obs;
	private Date dtCriacao;
	
	public ClienteDto(){}
	
	public ClienteDto(Integer id, String nome, String cpf, String email, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
	}
	
	public ClienteDto(Integer id, String nome, String cpf, String email, String telefone, String obs, Date dtCriacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.obs = obs;
		this.dtCriacao = dtCriacao;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public Date getDtCriacao() {
		return dtCriacao;
	}
	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	public String getDtCriacaoFormatada(){
		return FormataData.ddMMyyyy(dtCriacao);
	}
	
	public void tratamento(){
		if (nome!=null) {
			nome = nome.trim();
		}
		if (cpf != null){
			cpf = cpf.replaceAll("\\D+", "");
		}
		if (email != null){
			email = email.trim();
		}
		if (telefone != null) {
			telefone = telefone.replaceAll("\\D+", "");
		}
		if (obs != null) {
			obs = obs.trim();
		}
	}
}
