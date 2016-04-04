package bean.cliente;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import base.PerfilUsuarioEnum;
import bean.GeneryBean;
import dto.ClienteDto;
import service.ClienteService;

@ManagedBean(name = "clienteListBean")
public class ClienteListBean extends GeneryBean{
	
	private ClienteService clienteService = new ClienteService();
	
	private List<ClienteDto> clienteList;

	public List<ClienteDto> getClienteList() {
		return clienteList;
	}

	public void setClienteList(List<ClienteDto> clienteList) {
		this.clienteList = clienteList;
	}
	
	public ClienteListBean () {
		super(true, perfis());
		clienteList = clienteService.getAll();
	}

	public void deletar(Integer id){
		clienteService.apaga(id);
		addInfoMessageNow("Chave "+id+" removida.");
		clienteList = clienteService.getAll();
	}
	
	private static Map<String, String> perfis(){
		Map<String, String> retorno = new HashMap<>();
		retorno.put(PerfilUsuarioEnum.ADM.name(), PerfilUsuarioEnum.ADM.getDescricao());
		retorno.put(PerfilUsuarioEnum.CON.name(), PerfilUsuarioEnum.CON.getDescricao());
		return retorno;
	}
}
