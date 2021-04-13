package it.polito.tdp.anagrammi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.anagrammi.db.ConnectDB;

public class AnagrammaDAO {

	public boolean isCorrect(String anagramma) {
		boolean ritorno = false;
		String sql = "SELECT * "
				+ "FROM parola "
				+ "WHERE parola.nome = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, anagramma);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("id") != null && rs.getString("nome")!=null)
					ritorno = true;
			}
			
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException();
		}
		return ritorno;
	}
	
}
