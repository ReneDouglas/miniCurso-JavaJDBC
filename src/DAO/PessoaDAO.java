package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import form.Pessoa;

public class PessoaDAO {
	
	public void inserir (Pessoa p){
		
		 java.sql.Connection conn = AdmBancoSql.getConnection();
		 java.sql.PreparedStatement ps = null;
		
		try {
			
			String sql = "INSERT INTO pessoa (nome, data_nasc,"
					+ " sexo, cpf, rg) VALUES (?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.nome);
			ps.setDate(2, p.dataNasc);
			ps.setString(3, p.sexo);
			ps.setString(4, p.cpf);
			ps.setString(5, p.rg);
			
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AdmBancoSql.closeConnection(conn, ps, null);
		}
		
	}
	
	public void excluir (int id){
		
		java.sql.Connection conn = AdmBancoSql.getConnection();
		java.sql.PreparedStatement ps = null;

		try {
			
			String sql = "DELETE FROM pessoa WHERE idpessoa=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Pessoa Excluída com Sucesso");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			AdmBancoSql.closeConnection(conn, ps, null);
		}
	}
	
	public void alterar (Pessoa p){
		
		java.sql.Connection conn = null;
		java.sql.PreparedStatement ps = null;
		
		String sql = "UPDATE pessoa SET nome=?, data_nasc=?, "
				+ "sexo=?, cpf=?, rg=? WHERE idpessoa=?";
		
		try {
			conn = AdmBancoSql.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.nome);
			ps.setDate(2, p.dataNasc);
			ps.setString(3, p.sexo);
			ps.setString(4, p.cpf);
			ps.setString(5, p.rg);
			ps.setInt(6, p.id);
			
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			AdmBancoSql.closeConnection(conn, ps, null);
		}
	}
	
	public ArrayList<Pessoa> listar(){
		
		java.sql.Connection conn = AdmBancoSql.getConnection();
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
		Pessoa p;
		
		String sql = "SELECT * FROM pessoa";
		try {
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				p = new Pessoa();
				p.id = rs.getInt("idpessoa");
				p.nome = rs.getString("nome");
				p.dataNasc = rs.getDate("data_nasc");
				p.sexo = rs.getString("sexo");
				p.cpf = rs.getString("cpf");
				p.rg = rs.getString("rg");
				
				lista.add(p);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			AdmBancoSql.closeConnection(conn, ps, rs);
		}
		
		return lista;
	}

}
