package br.sp.senai.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import br.sp.senai.model.Pessoa;
import br.sp.senai.model.Compromisso;
import br.sp.senai.sqlUtils.DBUtils;

public class CompromissosDAO {
	 // *** ADICIONAR COMPROMISSO ***
	public void addCompromisso(Compromisso compromisso){
		
		Connection con = DBUtils.getConexao();
		Statement st = null;
		
		String sql = null;
			
		sql = "INSERT INTO compromissos (idPessoa, assunto, local, contato, foneContato, data, horario)";
		sql += "values ('" + compromisso.getIdPessoa() + "', ";
		sql += "'" + compromisso.getAssunto() + "', ";
		sql += "'" + compromisso.getLocal() + "', ";
		sql += "'" + compromisso.getContato() + "', ";
		sql += "'" + compromisso.getFoneContato() + "', ";
		sql += "'" + compromisso.getData() + "', ";
		sql += "'" + compromisso.getHorario() + "') ";
	
		try {
			st = con.createStatement();
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Compromisso adicionado!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na inserção de dados, " + e.getMessage() );
		}
		
	}
	
	//*** EXCLUIR COMPROMISSO ***
	public void delCompromisso(int idCompromisso){
			Connection con = DBUtils.getConexao();
			Statement st = null;
			
			String sql = null;
			
			sql = "delete from compromissos where idCompromisso = " + idCompromisso;
			
			try {
				st = con.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro na excluão de dados!");
			}
	}
	
	//*** EDITAR COMPROMISSO ***
	public void updateCompromisso(Compromisso compromisso){
		
		Connection con = DBUtils.getConexao();
		Statement st = null;
		
		String sql = null;
			
		sql = "update compromissos set assunto = '"  + compromisso.getAssunto() + "', ";
		sql += "local = '" + compromisso.getLocal() + "', ";
		sql += "contato = '" + compromisso.getContato() + "', ";
		sql += "foneContato = '" + compromisso.getFoneContato() + "', ";
		sql += "data = '" + compromisso.getData() + "', ";
		sql += "horario = '" + compromisso.getHorario();
		sql += "' where idCompromisso = '" + compromisso.getIdCompromisso() + "'";
		
		
		
		try {
			st = con.createStatement();
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na alteração de dados!");
		}
		
	}
}
