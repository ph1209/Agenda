package br.sp.senai.sqlUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;


	public class DBUtils{

	public static Connection getConexao(){
		Connection con = null;
		String conexao = "jdbc:mysql://127.0.0.1/compromissos";
		
		try{
			// *** Conexão ao banco ***
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(conexao, "root", "bcd127");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Não foi possível se conectar ao banco!" + e.getMessage());
		}
		return con;
	}
	
	public static ResultSet getResultSet(Connection con, String sql){
		Statement st = null;
		ResultSet rs = null;
		
		try{
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Não foi possível se conectar ao banco!" + e.getMessage());
		}
		return rs;
	}
}


	 
	

