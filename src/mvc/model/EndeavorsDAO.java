package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class EndeavorsDAO {
	private Connection connection = null;
	public EndeavorsDAO() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = System.getenv("mysql_url");
			String user = System.getenv("mysql_user");
			String password = System.getenv("mysql_password");
			connection = DriverManager.getConnection(url + user + password);
//			connection = DriverManager.getConnection(conectionString);
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
	public Integer contaToDo() {
		String sql= "SELECT COUNT(*) FROM Endeavor WHERE to_do IS NOT NULL AND to_do !=" +
				"\'" + "\'" + ";";

		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.execute();

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				stmt.close();
				return rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}
	public Integer contaDoing() {
		String sql= "SELECT COUNT(*) FROM Endeavor WHERE doing IS NOT NULL AND doing !=" +
				"\'" + "\'" + ";";

		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.execute();

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				stmt.close();
				return rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}
	public Integer contaDone() {
		String sql= "SELECT COUNT(*) FROM Endeavor WHERE done IS NOT NULL AND done !=" +
				"\'" + "\'" + ";";

		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.execute();

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				stmt.close();
				return rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}
	public List<Endeavors> getEndeavor() {
		List<Endeavors> endeavors = new ArrayList<Endeavors>();
		PreparedStatement stmt;
		try {
			stmt = connection.
					prepareStatement("SELECT * FROM Endeavor");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Endeavors endeavor = new Endeavors();
				endeavor.setId(rs.getInt("id"));
				endeavor.setTodo(rs.getString("to_do"));
				endeavor.setDoing(rs.getString("doing"));
				endeavor.setDone(rs.getString("done"));
				endeavors.add(endeavor);
			}
			rs.close();
			stmt.close();

			return endeavors;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
	public void addEndeavor(Endeavors endeavor) throws SQLException {
		String sql = "INSERT INTO Endeavor" +
				"(to_do, doing, done) values(?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1,endeavor.getTodo());
		stmt.setString(2,endeavor.getDoing());
		stmt.setString(3,endeavor.getDone());
		stmt.execute();
		stmt.close();
	}
	
	public void altera(Endeavors endeavor) throws SQLException {
		String sql = "UPDATE Endeavor SET " +
				"to_do=?, doing=?, done=? WHERE id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, endeavor.getTodo());
		stmt.setString(2, endeavor.getDoing());
		stmt.setString(3, endeavor.getDone());
		stmt.setInt(4, endeavor.getId());
		stmt.execute();
		stmt.close();
	}
	public void remove(Integer id) {
		PreparedStatement stmt;
		try {
			stmt = connection
					.prepareStatement("DELETE FROM Endeavor WHERE id=?");
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
