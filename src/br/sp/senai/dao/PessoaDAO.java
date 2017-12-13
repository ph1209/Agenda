package br.sp.senai.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import br.sp.senai.model.Pessoa;
import br.sp.senai.sqlUtils.DBUtils;

public class PessoaDAO {
	
	// *** ADICIONAR PESSOA ***
	public void addPessoa(Pessoa pessoa){
		
		Connection con = DBUtils.getConexao();
		Statement st = null;
		
		String sql = null;
			
		sql = "INSERT INTO pessoa (nome, email, genero, dtNasc)";
		sql += "values ('" + pessoa.getNome() + "', ";
		sql += "'" + pessoa.getEmail() + "', ";
		sql += "'" + pessoa.getGenero() + "', ";
		sql += "'2000-12-31')";
		
		
		try {
			st = con.createStatement();
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na inserção de dados!");
		}
		
	}
	
	//*** EXCLUIR PESSOA ***
	public void delPessoa(int id){
			Connection con = DBUtils.getConexao();
			Statement st = null;
			
			String sql = null;
			
			sql = "delete from pessoa where id = " + id;
			
			try {
				st = con.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro na excluão de dados!");
			}
	}
	
	public void updatePessoa(Pessoa pessoa){
		
		Connection con = DBUtils.getConexao();
		Statement st = null;
		
		String sql = null;
			
		sql = "update pessoa set nome = '"  + pessoa.getNome() + "', ";
		sql += "email = '" + pessoa.getEmail() + "', ";
		sql += "genero = '" + pessoa.getGenero() + "', ";
		sql += "dtNasc = '2000-12-31'";
		sql += "where id = " + pessoa.getId();
		
		
		try {
			st = con.createStatement();
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na alteração de dados!");
		}
		
	}
}
