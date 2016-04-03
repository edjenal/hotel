package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.UsuarioDto;
import util.ConvertPasswordToMD5;

public class UsuarioRepository {

	public void novo(UsuarioDto obj) {
		String sql = "insert into usuario (nome, cpf, email, telefone, obs, ativo, perfil, senha) values (?,?,?,?,?,?,?,?)";

		try (Connection connection = new ConnectionFactory().getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(sql);

			int i = 1;
			stmt.setString(i++, obj.getNome());
			stmt.setString(i++, obj.getCpf());
			stmt.setString(i++, obj.getEmail());
			stmt.setString(i++, obj.getTelefone());
			stmt.setString(i++, obj.getObs());
			stmt.setBoolean(i++, obj.getAtivo());
			stmt.setString(i++, obj.getPerfil().name());
			stmt.setString(i++, ConvertPasswordToMD5.convertPasswordToMD5(obj.getCpf()));

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void altera(UsuarioDto obj) {
		String sql = "update usuario set nome = ?, email = ?, telefone = ?, obs = ?, ativo = ?, perfil = ? where id = ?";

		try (Connection connection = new ConnectionFactory().getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(sql);

			int i = 1;
			stmt.setString(i++, obj.getNome());
			stmt.setString(i++, obj.getEmail());
			stmt.setString(i++, obj.getTelefone());
			stmt.setString(i++, obj.getObs());
			stmt.setBoolean(i++, obj.getAtivo());
			stmt.setString(i++, obj.getPerfil().name());
			
			stmt.setInt(i++, obj.getId());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alteraSenha(UsuarioDto obj) {
		String sql = "update usuario set senha = ? where id = ?";

		try (Connection connection = new ConnectionFactory().getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(sql);

			int i = 1;
			stmt.setString(i++, ConvertPasswordToMD5.convertPasswordToMD5(obj.getSenha()));
			
			stmt.setInt(i++, obj.getId());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void desativar(int id) {
		String sql = "update usuario set ativo = 0 where id = ?";

		try (Connection connection = new ConnectionFactory().getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(sql);

			int i = 1;
			stmt.setInt(i++, id);

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
