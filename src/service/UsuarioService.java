package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.PerfilUsuarioEnum;
import dto.UsuarioDto;
import dto.UsuarioLoginDto;
import jdbc.UsuarioFinder;
import jdbc.UsuarioRepository;
import util.ValidaCpf;

public class UsuarioService {

	private UsuarioFinder usuarioFinder = new UsuarioFinder();
	private UsuarioRepository usuarioRepository = new UsuarioRepository();

	public static UsuarioLoginDto getUsuario(String login, String senha) {

		if (login != null && !login.equals("") && senha != null && !senha.equals("")) {
			UsuarioLoginDto usuario = new UsuarioLoginDto();
			usuario.setId(1);
			usuario.setNome("Fulano");
			Map<String, PerfilUsuarioEnum> perfils = new HashMap<>();
			perfils.put(PerfilUsuarioEnum.ADM.name(), PerfilUsuarioEnum.ADM);
			usuario.setPerfils(perfils);
			return usuario;
		} else {
			return null;
		}

	}

	public List<UsuarioDto> getAll() {
		return usuarioFinder.getAll();
	}

	public UsuarioDto getById(int id) {
		if (id > 0) {
			return usuarioFinder.getById(id);
		} else {
			return null;
		}
	}

	public String temCpf(String cpf) {
		String cpfValido = ValidaCpf.cpfValido(cpf);
		if (cpfValido == null) {
			if (usuarioFinder.temCpf(cpf)) {
				return "CPF já cadastrado.";
			} else {
				return null;
			}
		} else {
			return cpfValido;
		}
	}

	public void garavar(UsuarioDto obj) {
		if (obj != null) {
			obj.tratamento();
			if (obj.getId() != null && obj.getId() > 0) {
				usuarioRepository.altera(obj);
			} else {
				usuarioRepository.novo(obj);
			}
		}
	}

	public void alteraSenha(UsuarioDto obj) {
		if (obj != null) {
			usuarioRepository.alteraSenha(obj);
		}
	}

	public void desativar(int id) {
		if (id > 0) {
			usuarioRepository.desativar(id);
		}
	}
}