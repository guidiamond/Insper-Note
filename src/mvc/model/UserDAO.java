package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	private Connection connection = null;

	public UserDAO() {
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

	public boolean autentica(Users user) {
		boolean existe = false;
		try {
			PreparedStatement stmt = connection.
			prepareStatement("SELECT COUNT(*) FROM User WHERE username=? AND password=?LIMIT 1");
					stmt.setString(1, user.getUsername());
					stmt.setString(2, user.getPassword());
					ResultSet rs = stmt.executeQuery();
					if(rs.next()){
						if(rs.getInt(1) != 0) {existe=true;}
					}
					rs.close();
					stmt.close();
		} catch(SQLException e) {System.out.println(e);}
		return existe;
	}

}
