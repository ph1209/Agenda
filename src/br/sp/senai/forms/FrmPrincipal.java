package br.sp.senai.forms;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.sp.senai.dao.PessoaDAO;
import br.sp.senai.model.Pessoa;
import br.sp.senai.sqlUtils.DBUtils;



	

public class FrmPrincipal extends JFrame {
	JTextField txtId;
	JTextField txtNome;
	JTextField txtEmail;
	JRadioButton radioMasc;
	JRadioButton radioFem;
	
	DefaultTableModel modelo;
	
	ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
	JTable tabela;
	int idNum = 1;
	
	public FrmPrincipal(){
		
		// ** CORES **
		Color red = new Color (234, 77, 19);
		Color azul = new Color (50, 122, 203);
		Color orange = new Color(235, 153, 41);
		Color gray = new Color(210, 210, 210);
		
		// *** DEFINIÇÃO DO FORMULÁRIO ***
		this.setTitle("Agenda Pessoal");
		this.setSize(600, 600);
		this.setLocation(20, 20);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// *** CRIAR PAINEL PRINCIPAL ***
		JPanel painelPrincipal = new JPanel();
		painelPrincipal.setLayout(null);
		this.add(painelPrincipal);
		
		// *** CRIAR PAINEL SUPERIOR - TÍTULO ***
		JPanel painelTitulo = new JPanel();
		painelTitulo.setLayout(null);
		painelTitulo.setBounds(0, 0, 600, 50);
		painelPrincipal.add(painelTitulo);
		painelTitulo.setBackground(azul);
		
		JLabel lblTitulo = new JLabel();
		lblTitulo.setText("Agenda Pessoal");
		lblTitulo.setBounds(200, 5, 300, 35);;
		Font fonteLabel = new Font("Arial", Font.BOLD, 28);
		
		lblTitulo.setFont(fonteLabel);
		lblTitulo.setForeground(orange);
		painelTitulo.add(lblTitulo);
		
		// *** PAINEL DETALHES ***
		JPanel painelDetalhes = new JPanel();
		painelDetalhes.setLayout(null);
		painelDetalhes.setBounds(7, 60, 580, 200);
		
		TitledBorder bordaDetalhes = new TitledBorder("Detalhes: ");
		painelDetalhes.setBorder(bordaDetalhes);
		painelPrincipal.add(painelDetalhes);
		
		JLabel lblId = new JLabel("ID: ");
		lblId.setBounds(10, 20, 50, 30);
		painelDetalhes.add(lblId);
		
		
		txtId = new JTextField();
		txtId.setBounds(30, 25, 50, 20);
		txtId.setEnabled(false);
		txtId.setBackground(gray);
		painelDetalhes.add(txtId);
		
		JLabel lblNome = new JLabel ("Nome: ");
		lblNome.setBounds(10, 70, 50, 30 );
		painelDetalhes.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(55, 75, 200, 20);
		painelDetalhes.add(txtNome);
		
		JLabel lblEmail = new JLabel ("E-mail: ");
		lblEmail.setBounds(10, 120, 50, 30 );
		painelDetalhes.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(55, 125, 200, 20);
		painelDetalhes.add(txtEmail);
		
		//*** BOTÃO ADICIONAR ***
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(400, 25, 100, 25);
		btnAdicionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Pessoa pessoa = new Pessoa();
				pessoa.setNome(txtNome.getText());
				pessoa.setEmail(txtEmail.getText());
				
				if(radioMasc.isSelected()){
					pessoa.setGenero("M");
				} else if (radioFem.isSelected()){
					pessoa.setGenero("F");
				}
				PessoaDAO pessoaDAO = new PessoaDAO();
				pessoaDAO.addPessoa(pessoa);
				montarTabela();
			}
		});
		
		painelDetalhes.add(btnAdicionar);
		
		
		// *** BOTÃO ALTERAR ***
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(400, 55, 100, 25 );
		btnAlterar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja alterar as informações da pessoa selecionada? ");

				if (resposta == 0)
				alterarDados(Integer.parseInt(txtId.getText()));
			}
		});
		painelDetalhes.add(btnAlterar);
		
		
		
		// *** BOTÃO EXCLUIR ***	
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(400, 85, 100, 25 );
		btnExcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir a pessoa selecionada? ");

				if (resposta == 0)
				excluirDados(Integer.parseInt(txtId.getText()));
				}	
		});
		painelDetalhes.add(btnExcluir);
		
		
		// *** BOTÃO AGENDA ***
		JButton btnAgenda = new JButton("Agenda");
		btnAgenda.setBounds(400, 115, 100, 25 );
		
		btnAgenda.addActionListener(new ActionListener() {
		
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FrmCompromissos(txtId.getText(), txtNome.getText());
			}
		});
		
		painelDetalhes.add(btnAgenda);
		
		
		// *** BOTÃO SAIR ***
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(400, 165, 100, 25 );
		painelDetalhes.add(btnSair);
		btnSair.setBackground(red);
		
		btnSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "Você clicou no botão sair?");
			}
		});
		
		
		// *** BOTÃO RADIO ***
		radioMasc = new JRadioButton("Masculino");
		radioMasc.setBounds(10, 170, 100, 25);
		painelDetalhes.add(radioMasc);
		
		radioFem = new JRadioButton("Feminino");
		radioFem.setBounds(110, 170, 100, 25);
		painelDetalhes.add(radioFem);
		
		ButtonGroup grupoRadio = new ButtonGroup();
		grupoRadio.add(radioMasc);
		grupoRadio.add(radioFem);
		
		
		// *** PAINEL LISTA DE PESSOAS ***
		JPanel painelPessoas = new JPanel();
		painelPessoas.setLayout(null);
		painelPessoas.setBounds(7, 260, 580, 300);
		TitledBorder bordaPessoas = new TitledBorder("Lista de Pessoas: ");
		painelPessoas.setBorder(bordaPessoas);
		painelPrincipal.add(painelPessoas);
		
		
		// *** TABELA ***
		modelo = new DefaultTableModel();
		tabela = new JTable(modelo);
		modelo.addColumn("ID");
		modelo.addColumn("Nome");
		modelo.addColumn("E-Mail");
		modelo.addColumn("Gênero");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(260);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(200);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
	
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(10, 15, 560, 280);
		
		tabela.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {

			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {

				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
					exibirDados();
				}
		});
		
		painelPessoas.add(scroll);
		
		
		// *** TORNA A JANELA VISÍVEL PARA O USUÁRIO ***
		this.setVisible(true);
		
		montarTabela();
	}
	
	
	// *** Adicionar ***
	public void gravarPessoa(){
		Pessoa pessoa = new Pessoa();
		pessoa.setId(idNum);
		pessoa.setNome(txtNome.getText());
		pessoa.setEmail(txtEmail.getText());
		
		if(radioFem.isSelected()){
			pessoa.setGenero("F");
		} else{
			pessoa.setGenero("M");
		}
		
		listaPessoas.add(pessoa);
		montarTabela();
	}
	
	
	
	public void montarTabela(){
		modelo.setNumRows(0);
		Connection con = DBUtils.getConexao();
		ResultSet rs = DBUtils.getResultSet(con, "select * from pessoa");
		
		try {
			while(rs.next()){
				modelo.addRow(new Object[]{rs.getInt("id"), 
						rs.getString("nome"), 
						rs.getString("email"), 
						rs.getString("genero")});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro /n Tente novamente mais tarde");
		} 
	}
	
	// *** Exibir ***
	public void exibirDados(){
		this.txtId.setText(modelo.getValueAt(tabela.getSelectedRow(),0).toString());
		this.txtNome.setText(modelo.getValueAt(tabela.getSelectedRow(),1).toString());
		this.txtEmail.setText(modelo.getValueAt(tabela.getSelectedRow(), 2).toString());
		
		String gen;
		gen = modelo.getValueAt(tabela.getSelectedRow(), 3).toString();
		if(gen.equals("F")){
			radioFem.setSelected(true);
		} else{
			radioMasc.setSelected(true);
		}
	}
	
	// *** Alterar ***
	public void alterarDados(int id){
		Pessoa pessoa = new Pessoa();
		pessoa.setId(Integer.parseInt(txtId.getText()));
		pessoa.setNome(txtNome.getText());
		pessoa.setEmail(txtEmail.getText());
		
		if(radioMasc.isSelected()){
			pessoa.setGenero("M");
		} else if(radioFem.isSelected()){
			pessoa.setGenero("F");
		}
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.updatePessoa(pessoa);
		
		
		montarTabela();
	}
	
	// *** Excluir ***
	public void excluirDados(int id){
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.delPessoa(id);
		montarTabela();
	}
}