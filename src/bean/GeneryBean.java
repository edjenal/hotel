package bean;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import base.PerfilUsuarioEnum;
import dto.UsuarioDto;

@ManagedBean(name = "generyBean")
public class GeneryBean {
	
	public void message() {
		String msgSucesso = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("msgSucesso");
		if (msgSucesso != null && !"".equals(msgSucesso)){
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("msgSucesso", null);
			
			addMessage(msgSucesso);
		}
	}

	private String nomeProjeto = "/hotel/";

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public String loginPage() {
		return this.nomeProjeto + "login.xhtml";
	}

	public String indexPage() {
		return this.nomeProjeto + "index.xhtml";
	}

	public String getNomeProjeto() {
		return this.nomeProjeto;
	}

	private UsuarioDto usuario;

	public GeneryBean(boolean usuarioLogado, Map<String, String> perfis) {
		if (usuarioLogado) {
			getUsuarioLogado(perfis);
		}
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addErroMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public UsuarioDto getUsuarioLogado(Map<String, String> perfis) {

		UsuarioDto usuario = (UsuarioDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogado");

		if (usuario != null && perfis.containsKey(usuario.getPerfil().name())) {
			this.usuario = usuario;
			return usuario;
		} else {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(loginPage());
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}

	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public boolean isAdm() {
		return PerfilUsuarioEnum.ADM == usuario.getPerfil() ? true : false;
	}

	public boolean isConsulta() {
		return PerfilUsuarioEnum.CON == usuario.getPerfil() ? true : false;
	}
}
