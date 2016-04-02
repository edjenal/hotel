package bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dto.UsuarioLoginDto;

@ManagedBean(name = "generyBean")
public class GeneryBean {

	private String nomeProjeto = "/hotel/";

	public String loginPage() {
		return this.nomeProjeto + "login.xhtml";
	}

	public String indexPage() {
		return this.nomeProjeto + "index.xhtml";
	}

	public String getNomeProjeto() {
		return this.nomeProjeto;
	}

	public GeneryBean(boolean usuarioLogado) {
		if (usuarioLogado) {
			getUsuario();
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

	public UsuarioLoginDto getUsuario() {
		UsuarioLoginDto usuario = (UsuarioLoginDto) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("usuarioLogado");
		if (usuario != null) {
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

}
