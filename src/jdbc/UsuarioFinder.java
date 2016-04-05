package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import base.PerfilUsuarioEnum;
import dto.UsuarioDto;

public class UsuarioFinder {

	public List<UsuarioDto> getAll() {
		String sql = "select id, nome, cpf, email, telefone, ativo, perfil from usuario where cpf <> 'adm'";

		List<UsuarioDto> retorno = new ArrayList<>();
		try (Connection connection = new ConnectionFactory().getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");
				Boolean ativo = rs.getBoolean("ativo");
				PerfilUsuarioEnum perfil = PerfilUsuarioEnum.valueOf(rs.getString("perfil"));

				retorno.add(new UsuarioDto(id, nome, cpf, email, telefone, ativo, perfil));
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retorno;
	}

	public UsuarioDto getById(int id) {
		String sql = "select nome, cpf, email, telefone, obs, date_created, ativo, perfil from usuario where id = ?";

		UsuarioDto retorno = null;
		try (Connection connection = new ConnectionFactory().getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");
				String obs = rs.getString("obs");
				Date dtCriacao = rs.getTimestamp("date_created");
				Boolean ativo = rs.getBoolean("ativo");
				PerfilUsuarioEnum perfil = PerfilUsuarioEnum.valueOf(rs.getString("perfil"));

				retorno = new UsuarioDto(id, nome, cpf, email, telefone, obs, dtCriacao, ativo, perfil);
				break;
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retorno;
	}
	
	public UsuarioDto getByLogin(String cpf, String senha) {
		String sql = "select id, nome, email, telefone, obs, date_created, ativo, perfil from usuario where cpf = ? and senha = ?";

		UsuarioDto retorno = null;
		try (Connection connection = new ConnectionFactory().getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			stmt.setString(2, senha);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");
				String obs = rs.getString("obs");
				Date dtCriacao = rs.getTimestamp("date_created");
				Boolean ativo = rs.getBoolean("ativo");
				PerfilUsuarioEnum perfil = PerfilUsuarioEnum.valueOf(rs.getString("perfil"));

				retorno = new UsuarioDto(id, nome, cpf, email, telefone, obs, dtCriacao, ativo, perfil);
				break;
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retorno;
	}
	
	public boolean temCpf(String cpf) {
		String sql = "select 1 from usuario where cpf = ?";
		
		boolean retorno = false;
		try (Connection connection = new ConnectionFactory().getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cpf.replaceAll("\\D+", ""));

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				retorno = true;
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

}
