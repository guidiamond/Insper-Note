package br.edu.insper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private Connection connection = null;

	public DAO() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/insper_note?user=root&password=batata");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e);
		}
		catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.print(e1);
		}
	}

	public void close() {
		try {
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Users> getLista() {
		List<Users> pessoas = new ArrayList<Users>();
		PreparedStatement stmt;
		try {
			stmt = connection.
					prepareStatement("SELECT * FROM User");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Users pessoa = new Users();
				pessoa.setId(rs.getInt("id"));
				pessoa.setUsername(rs.getString("username"));
				pessoa.setPassword(rs.getString("password"));
				pessoa.setEmail(rs.getString("email"));
				pessoas.add(pessoa);
			}
			rs.close();
			stmt.close();

			return pessoas;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

		public List<Endeavors> getEndeavor() {
			List<Endeavors> pessoas = new ArrayList<Endeavors>();
			PreparedStatement stmt;
			try {
				stmt = connection.
						prepareStatement("SELECT * FROM Endeavor");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Endeavors pessoa = new Endeavors();
					pessoa.setTodo(rs.getString("to_do"));
					pessoa.setDoing(rs.getString("doing"));
					pessoa.setDone(rs.getString("done"));
					pessoas.add(pessoa);
				}
				rs.close();
				stmt.close();

				return pessoas;
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}		
		}

	public void adiciona(Users user) throws SQLException {
		String sql = "INSERT INTO User" +
				"(username, password, email) values(?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1,user.getUsername());
		stmt.setString(2,user.getPassword());
		stmt.setString(3,user.getEmail());
		stmt.execute();
		stmt.close();
	}
	public boolean autentica(String username, String pass) {
		List<Users> pessoas = new ArrayList<Users>();
		String sql = "SELECT * FROM User WHERE " +
				"username=" + "\'" + username + "\'" + " AND " + "password=" + "\'" + pass + "\'";
		PreparedStatement stmt;
		try {
			stmt = connection.
					prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Users pessoa = new Users();
				pessoa.setId(rs.getInt("id"));
				pessoa.setUsername(rs.getString("username"));
				pessoa.setPassword(rs.getString("password"));
				pessoa.setEmail(rs.getString("email"));
				pessoas.add(pessoa);
			}
			if (pessoas.size() != 0 ) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//	public void altera(Pessoas pessoa) throws SQLException {
	//		String sql = "UPDATE Pessoas SET " +
	//		 "nome=?, nascimento=?, altura=? WHERE id=?";
	//		PreparedStatement stmt = connection.prepareStatement(sql);
	//		stmt.setString(1, pessoa.getNome());
	//		stmt.setDate(2, new Date(pessoa.getNascimento()
	//		.getTimeInMillis()));
	//		stmt.setDouble(3, pessoa.getAltura());
	//		stmt.setInt(4, pessoa.getId());
	//		stmt.execute();
	//		stmt.close();
	//	}
	//	public void remove(Integer id) {
	//		PreparedStatement stmt;
	//		try {
	//			stmt = connection
	//			 .prepareStatement("DELETE FROM Pessoas WHERE id=?");
	//			stmt.setLong(1, id);
	//			stmt.execute();
	//			stmt.close();
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		
	//		}
}
