package bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "indexBean")
public class IndexBean extends GeneryBean{
	
	public IndexBean(){
		super(true);
	}
	
	public void exit(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(loginPage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
