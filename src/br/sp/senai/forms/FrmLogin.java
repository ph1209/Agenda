package br.sp.senai.forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.sp.senai.sqlUtils.DBUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 414, 359);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(222, 96, -211, -103);
		contentPane.add(panel_1);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setBounds(61, 23, 284, 64);
		lblLogin.setBackground(new Color(255, 255, 255));
		lblLogin.setForeground(new Color(30, 144, 255));
		lblLogin.setFont(new Font("Yu Gothic", Font.PLAIN, 38));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLogin);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblUsurio.setBounds(25, 131, 68, 31);
		contentPane.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblSenha.setBounds(25, 208, 62, 31);
		contentPane.add(lblSenha);
		
		txtUser = new JTextField();
		txtUser.setBackground(new Color(255, 255, 255));
		txtUser.setBounds(97, 138, 180, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(211, 211, 211));
		btnLogin.setForeground(new Color(30, 144, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql = ("select * from login where user = '" + txtUser.getText() + "' and password = '" + txtSenha.getText() + "'");
				Connection con = DBUtils.getConexao();
				ResultSet rs = DBUtils.getResultSet(con, sql);
				
				try{
					if(rs.next()){
						new FrmPrincipal();
						setVisible(false);
					} else{
						JOptionPane.showMessageDialog(null, "Campo usuário ou senha incorreto.");
					}
				}
				catch (SQLException e){
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
			
			}
		});
		btnLogin.setBounds(25, 269, 68, 23);
		contentPane.add(btnLogin);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(97, 215, 180, 20);
		contentPane.add(txtSenha);
		
		setVisible(true);
	}
}
