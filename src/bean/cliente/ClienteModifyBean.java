package bean.cliente;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

import base.PerfilUsuarioEnum;
import bean.GeneryBean;
import dto.ClienteDto;
import service.ClienteService;

@ManagedBean(name = "clienteModifyBean")
public class ClienteModifyBean extends GeneryBean {

	private ClienteService clienteService;

	private ClienteDto cliente;

	private boolean msgCpf = false;

	private boolean edicao = false;

	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}

	public boolean isMsgCpf() {
		return msgCpf;
	}

	public void setMsgCpf(boolean msgCpf) {
		this.msgCpf = msgCpf;
	}

	public boolean isEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}
	
	private static Map<String, String> perfis(){
		Map<String, String> retorno = new HashMap<>();
		retorno.put(PerfilUsuarioEnum.ADM.name(), PerfilUsuarioEnum.ADM.getDescricao());
		retorno.put(PerfilUsuarioEnum.CON.name(), PerfilUsuarioEnum.CON.getDescricao());
		return retorno;
	}

	public ClienteModifyBean() {
		super(true, perfis());
		clienteService = new ClienteService();
		cliente = new ClienteDto();
	}

	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		int id = request.getParameter("id") != null ? Integer.valueOf(request.getParameter("id")) : 0;

		if (id > 0) {
			cliente = clienteService.getById(id);
			edicao = true;
		}
	}

	public void buttonAction(ActionEvent actionEvent) {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("hiddenId");
		cliente.setId(id != null ? Integer.valueOf(id) : null);
		
		clienteService.garavar(cliente);
		
		salvarInfoMenssage("Cliente salvo.");
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(this.getNomeProjeto()+"cliente/cliente-list.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void verificaCpf(AjaxBehaviorEvent actionEvent) {
		String cpfValido = clienteService.temCpf(cliente.getCpf());
		if (cpfValido != null) {
			addErroMessageNow(cpfValido);
			msgCpf = true;
		} else {
			msgCpf = false;
		}
	}

}
