package dto;

import java.util.Date;

import base.PerfilUsuarioEnum;
import util.FormataData;

public class UsuarioDto {
	
	private Integer id;
	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	private String obs;
	private Date dtCriacao;
	private Boolean ativo;
	private PerfilUsuarioEnum perfil;
	private String senha;
	
	public UsuarioDto () {};
	
	public UsuarioDto(Integer id, String nome, String cpf, String email, String telefone, Boolean ativo, PerfilUsuarioEnum perfil) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.ativo = ativo;
		this.perfil = perfil;
	}
	
	public UsuarioDto(Integer id, String nome, String cpf, String email, String telefone, String obs, Date dtCriacao,
			Boolean ativo, PerfilUsuarioEnum perfil) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.obs = obs;
		this.dtCriacao = dtCriacao;
		this.ativo = ativo;
		this.perfil = perfil;
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
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public PerfilUsuarioEnum getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilUsuarioEnum perfil) {
		this.perfil = perfil;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
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
