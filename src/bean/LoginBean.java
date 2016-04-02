package bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import dto.UsuarioLoginDto;
import service.UsuarioService;

@ManagedBean(name = "loginBean")
public class LoginBean extends GeneryBean{
	
	public LoginBean(){
		super(false);
	}

	private String login;
	
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void buttonAction(ActionEvent actionEvent) {
		//Coleta Usuario da base
		UsuarioLoginDto usuario = UsuarioService.getUsuario(login, senha);
		
		//Verifica se ele existe
		if (usuario!= null){
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuario);
			addMessage(usuario.getNome());
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(indexPage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			addErroMessage("Usuário não localizado");
		}
	}
}