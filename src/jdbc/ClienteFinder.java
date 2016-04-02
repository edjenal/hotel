package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.ClienteDto;

public class ClienteFinder {

	public List<ClienteDto> getAll() {
		String sql = "select id, nome, cpf, email, telefone from cliente";

		List<ClienteDto> retorno = new ArrayList<>();
		try (Connection connection = new ConnectionFactory().getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");

				retorno.add(new ClienteDto(id, nome, cpf, email, telefone));
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retorno;
	}

	public ClienteDto getById(int id) {
		String sql = "select nome, cpf, email, telefone, obs, date_created from cliente where id = ?";

		ClienteDto retorno = new ClienteDto();
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

				retorno = new ClienteDto(id, nome, cpf, email, telefone, obs, dtCriacao);
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
		String sql = "select 1 from cliente where cpf = ?";
		
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
