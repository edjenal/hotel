package base;

public enum PerfilUsuarioEnum {
	ADM("adminstrador"), CON("consulta");
	
	private PerfilUsuarioEnum(String descricao){
		this.descricao = descricao;
	}
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

}
