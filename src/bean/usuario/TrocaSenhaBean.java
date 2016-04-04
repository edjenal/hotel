package bean.usuario;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import base.PerfilUsuarioEnum;
import bean.GeneryBean;
import dto.UsuarioDto;
import service.UsuarioService;

@ManagedBean(name = "trocaSenhaBean")
public class TrocaSenhaBean extends GeneryBean {

	private UsuarioService usuarioService;

	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	private static Map<String, String> perfis() {
		Map<String, String> retorno = new HashMap<>();
		retorno.put(PerfilUsuarioEnum.ADM.name(), PerfilUsuarioEnum.ADM.getDescricao());
		retorno.put(PerfilUsuarioEnum.CON.name(), PerfilUsuarioEnum.CON.getDescricao());
		return retorno;
	}

	public TrocaSenhaBean() {
		super(true, perfis());
		usuarioService = new UsuarioService();
	}

	public void buttonAction(ActionEvent actionEvent) {
		UsuarioDto Usuario = getUsuario();
		Usuario.setSenha(senha);
		usuarioService.alteraSenha(Usuario);

		this.salvarInfoMenssage("Senha modificada com sucesso.");

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(indexPage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
