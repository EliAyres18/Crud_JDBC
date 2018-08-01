package Conexao.banco.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Teste {

	public static void main(String[] args) throws SQLException {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		User user = null;
		String url = "jdbc:postgresql://192.168.7.27:5432/teste";
		String query = "select * FROM teste";

		try {
			System.out.println("Vou passar");
			Class.forName("org.postgresql.Driver"); // registro do driver
			con = DriverManager.getConnection(url, "sti", "sti_trf");
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		
			
//			 String inserir = "INSERT INTO teste(id, nome, idade) VALUES(10,'Marcos',100)"; 
//			 stmt.executeUpdate(inserir);

   		//if (rs.next()) {
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setNome(rs.getString("nome"));
				user.setIdade(rs.getInt("idade"));
				System.out.println(user.getNome() + " - " + user.getIdade() + " - " + user.getId());
				
			}

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("NÃO ENTRA");

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();

			} catch (SQLException ex) {
				ex.printStackTrace();

			}

		}

	}
}
