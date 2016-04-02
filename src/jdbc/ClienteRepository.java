package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.ClienteDto;

public class ClienteRepository {
	
	/*
	 * java.sql.Date dataParaGravar = new java.sql.Date(Calendar.getInstance().getTimeInMillis()); 
	 * stmt.setDate(4, dataParaGravar);
	 */
	
	public void novo(ClienteDto obj){
		String sql = "insert into cliente (nome, cpf, email, telefone, obs) values (?,?,?,?,?)";
		
		try (Connection connection = new ConnectionFactory().getConnection()){
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			int i = 1;
			stmt.setString(i++, obj.getNome());
			stmt.setString(i++, obj.getCpf());
			stmt.setString(i++, obj.getEmail());
			stmt.setString(i++, obj.getTelefone());
			stmt.setString(i++, obj.getObs());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void altera(ClienteDto obj){
		String sql = "update cliente set nome=?, email=?, telefone=?, obs=? where id=?";
		
		try (Connection connection = new ConnectionFactory().getConnection()){
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			int i = 1;
			stmt.setString(i++, obj.getNome());
			stmt.setString(i++, obj.getEmail());
			stmt.setString(i++, obj.getTelefone());
			stmt.setString(i++, obj.getObs());
			
			stmt.setInt(i++, obj.getId());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void apaga(int id){
		String sql = "delete from cliente where id=?";
		
		try (Connection connection = new ConnectionFactory().getConnection()){
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
