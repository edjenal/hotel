package bean.usuario;

import java.util.List;

import javax.faces.bean.ManagedBean;

import bean.GeneryBean;
import dto.UsuarioDto;
import service.UsuarioService;

@ManagedBean(name = "usuarioListBean")
public class UsuarioListBean extends GeneryBean{
	
	private UsuarioService usuarioService = new UsuarioService();
	
	private List<UsuarioDto> usuarioList;

	public UsuarioListBean() {
		super(true);
		usuarioList = usuarioService.getAll();
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public List<UsuarioDto> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<UsuarioDto> usuarioList) {
		this.usuarioList = usuarioList;
	}
	
	public void desativar(int id){
		usuarioService.desativar(id);
		addMessage("Chave "+id+" desativada.");
		usuarioList = usuarioService.getAll();
	}

}
