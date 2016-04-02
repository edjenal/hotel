package service;

import java.util.List;

import dto.ClienteDto;
import jdbc.ClienteFinder;
import jdbc.ClienteRepository;
import util.ValidaCpf;

public class ClienteService {

	private ClienteFinder clienteFinder = new ClienteFinder();
	private ClienteRepository clienteRepository = new ClienteRepository();
	
	public List<ClienteDto> getAll(){
		return clienteFinder.getAll();
	}
	
	public ClienteDto getById(int id){
		if (id > 0){
			return clienteFinder.getById(id);
		} else {
			return null;
		}
	}
	
	public void garavar(ClienteDto obj){
		if (obj!= null){
			obj.tratamento();
			if (obj.getId() != null && obj.getId() > 0){
				clienteRepository.altera(obj);
			} else {
				clienteRepository.novo(obj);		
			}
		}
	}
	
	public void apaga(int id){
		if (id > 0) {
			clienteRepository.apaga(id);
		}
	}
	
	public String temCpf(String cpf) {
		String cpfValido = ValidaCpf.cpfValido(cpf);
		if(cpfValido==null) {
			if(clienteFinder.temCpf(cpf)){
				return "CPF já cadastrado.";
			} else {
				return null;
			}
		} else {
			return cpfValido;
		}
	}
	
}
