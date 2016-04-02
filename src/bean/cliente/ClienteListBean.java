package bean.cliente;

import java.util.List;

import javax.faces.bean.ManagedBean;

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
		super(true);
		clienteList = clienteService.getAll();
	}

	public void deletar(Integer id){
		clienteService.apaga(id);
		addMessage("Chave "+id+" removida.");
		clienteList = clienteService.getAll();
	}
}
