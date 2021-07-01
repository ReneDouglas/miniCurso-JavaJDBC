package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AdmBancoSql {
	
	public static Connection getConnection(){
		Connection con=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/banco_de_dados_minicurso","root", "root");
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "N"+(char)227+"o foi possivel conectar com o banco. Erro no DriverManager.getConnection.");
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Driver n"+(char)227+"o pode ser carregado!");
			con = null;
		}
		return con;
	}
	
	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) rs.close( );
			if (stmt != null)stmt.close( );
			if (conn != null)conn.close( );
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao fechar Banco de dados");
			
		}
	}
	
}
