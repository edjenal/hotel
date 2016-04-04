package bean.usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import base.PerfilUsuarioEnum;
import bean.GeneryBean;
import dto.UsuarioDto;
import service.UsuarioService;

@ManagedBean(name = "usuarioListBean")
public class UsuarioListBean extends GeneryBean{
	
	private UsuarioService usuarioService;
	
	private List<UsuarioDto> usuarioList;
	
	private static Map<String, String> perfis(){
		Map<String, String> retorno = new HashMap<>();
		retorno.put(PerfilUsuarioEnum.ADM.name(), PerfilUsuarioEnum.ADM.getDescricao());
		return retorno;
	}

	public UsuarioListBean() {
		super(true, perfis());
		usuarioService = new UsuarioService();
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
		addInfoMessageNow("Chave "+id+" desativada.");
		usuarioList = usuarioService.getAll();
	}

}
