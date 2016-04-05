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

	public GeneryBean(boolean usuarioLogado, Map<String, String> perfis) {
		if (usuarioLogado) {
			getUsuarioLogado(perfis);
		}
	}

	/*
	 * Valida se o usuário está logado no sistema e se possui permissão de acesso para a tela
	 */
	public void getUsuarioLogado(Map<String, String> perfis) {

		UsuarioDto usuario = (UsuarioDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogado");

		if (usuario != null) {
			if (perfis.containsKey(usuario.getPerfil().name())) {
				this.usuario = usuario;
			} else {
				try {
					salvarWarnMenssage("Você não tem acesso à essa funcionalidade.");
					FacesContext.getCurrentInstance().getExternalContext().redirect(indexPage());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(loginPage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Exibe mensagens na mesma tela
	 */
	public void addInfoMessageNow(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addWarnMessageNow(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addErroMessageNow(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addFatalMessageNow(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/*
	 * Salva mensagem para exibir na próxima tela
	 */
	public void salvarInfoMenssage(String valor) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("msgInfo", valor);
	}

	public void salvarWarnMenssage(String valor) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("msgWarn", valor);
	}

	public void salvarErrorMenssage(String valor) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("msgError", valor);
	}

	public void salvarFatalMenssage(String valor) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("msgFatal", valor);
	}

	/*
	 * Exibe mensagens na proxima tela
	 */
	public void menssage() {
		String msgInfo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("msgInfo");
		if (msgInfo != null && !"".equals(msgInfo)) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("msgInfo", null);

			addInfoMessageNow(msgInfo);
		}

		String msgWarn = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("msgWarn");
		if (msgWarn != null && !"".equals(msgWarn)) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("msgWarn", null);

			addWarnMessageNow(msgWarn);
		}

		String msgError = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("msgError");
		if (msgError != null && !"".equals(msgError)) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("msgError", null);

			addWarnMessageNow(msgError);
		}

		String msgFatal = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("msgFatal");
		if (msgFatal != null && !"".equals(msgFatal)) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("msgFatal", null);

			addWarnMessageNow(msgFatal);
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
