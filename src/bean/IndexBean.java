package bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import base.PerfilUsuarioEnum;

@ManagedBean(name = "indexBean")
public class IndexBean extends GeneryBean {

	private static Map<String, String> perfis() {
		Map<String, String> retorno = new HashMap<>();
		retorno.put(PerfilUsuarioEnum.ADM.name(), PerfilUsuarioEnum.ADM.getDescricao());
		retorno.put(PerfilUsuarioEnum.CON.name(), PerfilUsuarioEnum.CON.getDescricao());
		return retorno;
	}

	public IndexBean() {
		super(true, perfis());
	}

	public void exit() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(loginPage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
