package bean.usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

import base.PerfilUsuarioEnum;
import bean.GeneryBean;
import dto.UsuarioDto;
import service.UsuarioService;

@ManagedBean(name = "usuarioModifyBean")
public class UsuarioModifyBean extends GeneryBean {

	private UsuarioService usuarioService;
	private boolean msgCpf;
	private boolean edicao;
	private UsuarioDto usuario;
	private PerfilUsuarioEnum[] perfis;

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

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}
	
	public PerfilUsuarioEnum[] getPerfis() {
		return perfis;
	}

	public void setPerfis(PerfilUsuarioEnum[] perfis) {
		this.perfis = perfis;
	}

	public UsuarioModifyBean() {
		super(true);
		usuarioService = new UsuarioService();
		usuario = new UsuarioDto();
		msgCpf = false;
		edicao = false;
		perfis = PerfilUsuarioEnum.values();
	}

	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		int id = request.getParameter("id") != null ? Integer.valueOf(request.getParameter("id")) : 0;

		if (id > 0) {
			usuario = usuarioService.getById(id);
			edicao = true;
		}
	}

	public void buttonAction(ActionEvent actionEvent) {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("hiddenId");
		usuario.setId(id != null ? Integer.valueOf(id) : null);

		usuarioService.garavar(usuario);

		addMessage("Sucesso.");
	}

	public void verificaCpf(AjaxBehaviorEvent actionEvent) {
		String cpfValido = usuarioService.temCpf(usuario.getCpf());
		if (cpfValido != null) {
			addErroMessage(cpfValido);
			msgCpf = true;
		} else {
			msgCpf = false;
		}
	}
}
