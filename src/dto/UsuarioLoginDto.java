package dto;

import java.io.Serializable;
import java.util.Map;

import base.PerfilUsuarioEnum;

public class UsuarioLoginDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Map<String, PerfilUsuarioEnum> perfils;

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

	public Map<String, PerfilUsuarioEnum> getPerfils() {
		return perfils;
	}

	public void setPerfils(Map<String, PerfilUsuarioEnum> perfils) {
		this.perfils = perfils;
	}

}
