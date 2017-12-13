package br.sp.senai.forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.sp.senai.dao.CompromissosDAO;
import br.sp.senai.dao.PessoaDAO;
import br.sp.senai.model.Pessoa;
import br.sp.senai.model.Compromisso;
import br.sp.senai.sqlUtils.DBUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class FrmCompromissos extends JFrame {

	 JPanel contentPane;
	 JTextField txtID;
	 JTextField txtNome;
	 JTextField txtAssunto;
	 JTextField txtContato;
	 JTextField txtLocal;
	 JTextField txtData;
	 JTextField txtHorario;
	 JTextField txtTelefone;
	 
	
	DefaultTableModel modelo;
	JTable table;
	JTextField txtIDComp;
	
		
	public FrmCompromissos(String idPessoa, String nome) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 829, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Compromisso = new JPanel();
		Compromisso.setBorder(new TitledBorder(null, "Compromissos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		Compromisso.setBounds(10, 11, 803, 158);
		contentPane.add(Compromisso);
		Compromisso.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 783, 122);
		Compromisso.add(scrollPane);
		
		table = new JTable();
		table.setModel(modelo = new DefaultTableModel(
			new Object[][] {
				{},
			},
			new String[] {
				"ID:", "Data:", "Horario:", "Assunto do compromisso:"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(52);
		table.getColumnModel().getColumn(1).setPreferredWidth(83);
		table.getColumnModel().getColumn(2).setPreferredWidth(83);
		table.getColumnModel().getColumn(3).setPreferredWidth(242);
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				exibirDados();
				
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Detalhes do compromisso:", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_1.setBounds(20, 180, 783, 249);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 25, 20, 14);
		panel_1.add(lblId);
		
		txtID = new JTextField(idPessoa);
		txtID.setEditable(false);
		txtID.setBounds(10, 44, 26, 20);
		panel_1.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(59, 25, 46, 14);
		panel_1.add(lblNome);
		
		txtNome = new JTextField(nome);
		txtNome.setEditable(false);
		txtNome.setBounds(59, 44, 276, 20);
		panel_1.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblAssunto = new JLabel("Assunto:");
		lblAssunto.setBounds(10, 88, 95, 14);
		panel_1.add(lblAssunto);
		
		txtAssunto = new JTextField();
		txtAssunto.setBounds(10, 105, 242, 20);
		panel_1.add(txtAssunto);
		txtAssunto.setColumns(10);
		
		JLabel lblContato = new JLabel("Contato:");
		lblContato.setBounds(289, 88, 73, 14);
		panel_1.add(lblContato);
		
		txtContato = new JTextField();
		txtContato.setBounds(289, 105, 158, 20);
		panel_1.add(txtContato);
		txtContato.setColumns(10);
		
		JLabel lblLocal = new JLabel("Local:");
		lblLocal.setBounds(10, 157, 46, 14);
		panel_1.add(lblLocal);
		
		txtLocal = new JTextField();
		txtLocal.setBounds(10, 175, 276, 20);
		panel_1.add(txtLocal);
		txtLocal.setColumns(10);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(316, 157, 46, 14);
		panel_1.add(lblData);
		
		txtData = new JTextField();
		txtData.setBounds(316, 175, 93, 20);
		panel_1.add(txtData);
		txtData.setColumns(10);
		
		JLabel lblHorario = new JLabel("Horario:");
		lblHorario.setBounds(433, 157, 46, 14);
		panel_1.add(lblHorario);
		
		txtHorario = new JTextField();
		txtHorario.setBounds(433, 175, 86, 20);
		panel_1.add(txtHorario);
		txtHorario.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(548, 157, 73, 14);
		panel_1.add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(548, 175, 93, 20);
		panel_1.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblIdCompromisso = new JLabel("ID Compromisso:");
		lblIdCompromisso.setBounds(375, 25, 144, 14);
		panel_1.add(lblIdCompromisso);
		
		txtIDComp = new JTextField();
		txtIDComp.setEditable(false);
		txtIDComp.setBounds(375, 44, 26, 20);
		panel_1.add(txtIDComp);
		txtIDComp.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 444, 793, 89);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnAdicionar = new JButton("");
		btnAdicionar.setIcon(new ImageIcon(FrmCompromissos.class.getResource("/br/sp/senai/Imagens/\u00EDcones/Add16.png")));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Compromisso compromisso = new Compromisso();
				compromisso.setIdPessoa(Integer.parseInt(txtID.getText()));
				compromisso.setAssunto(txtAssunto.getText());
				compromisso.setLocal(txtLocal.getText());
				compromisso.setContato(txtContato.getText());
				compromisso.setFoneContato(txtTelefone.getText());
				compromisso.setData(txtData.getText());
				compromisso.setHorario(txtHorario.getText());
				
				
				CompromissosDAO compromissoDAO = new CompromissosDAO();
				
				compromissoDAO.addCompromisso(compromisso);
				
				montarTabela(Integer.parseInt(idPessoa));
			}
		});
		btnAdicionar.setBounds(399, 35, 89, 23);
		panel_2.add(btnAdicionar);
		
		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(FrmCompromissos.class.getResource("/br/sp/senai/Imagens/\u00EDcones/Modify16.png")));
		btnEditar.setBounds(597, 35, 89, 23);
		btnEditar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja modificar o compromisso selecionado? ");
				
				if(resposta == 0){
					alterarDados();
					montarTabela(Integer.parseInt(idPessoa));
				}
				
			}
		});
		panel_2.add(btnEditar);
		
		JButton btnSair = new JButton("");
		btnSair.setIcon(new ImageIcon(FrmCompromissos.class.getResource("/br/sp/senai/Imagens/\u00EDcones/Exit16.png")));
		btnSair.setBounds(694, 35, 89, 23);
		panel_2.add(btnSair);
		
		
		JButton btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(FrmCompromissos.class.getResource("/br/sp/senai/Imagens/\u00EDcones/Delete16.png")));  
		btnExcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o compromisso selecionado? ");
				
				if (resposta == 0){
					CompromissosDAO compromissosDAO = new CompromissosDAO();
					compromissosDAO.delCompromisso(Integer.parseInt(txtIDComp.getText()));
				}
				montarTabela(Integer.parseInt(idPessoa));
			}
		});
			
			

		btnExcluir.setBounds(498, 35, 89, 23);
		panel_2.add(btnExcluir);

		
		this.setVisible(true);
		montarTabela(Integer.parseInt(idPessoa));
		
	}
	
	public void montarTabela(int idPessoa){
		modelo.setNumRows(0);
		DBUtils t = new DBUtils();
		String sql = "select * from compromissos where idPessoa = " + idPessoa;
		ResultSet rs = t.getResultSet(t.getConexao(), sql);
		
		
		try {
			while(rs.next()){
				modelo.addRow(new Object[]{rs.getString("idCompromisso"), rs.getString("data"), rs.getString("horario"), rs.getString("assunto")});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro /n Tente novamente mais tarde");
		} 
	}
	
	public void exibirDados(){
		this.txtIDComp.setText(modelo.getValueAt(table.getSelectedRow(), 0).toString());
		this.txtData.setText(modelo.getValueAt(table.getSelectedRow(), 1).toString());
		this.txtHorario.setText(modelo.getValueAt(table.getSelectedRow(), 2).toString());
		this.txtAssunto.setText(modelo.getValueAt(table.getSelectedRow(), 3).toString());
		
		DBUtils teste = new DBUtils();
		ResultSet rsTeste = teste.getResultSet(teste.getConexao(),"select * from compromissos");
		
		try{
			while (rsTeste.next()){
				if(Integer.parseInt(txtIDComp.getText()) == Integer.parseInt(rsTeste.getString("idCompromisso"))){
					this.txtContato.setText(rsTeste.getString("contato"));
					this.txtTelefone.setText(rsTeste.getString("foneContato"));
					this.txtLocal.setText(rsTeste.getString("local"));
				}
				
			}
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void alterarDados(){
		Compromisso compromisso = new Compromisso();
		compromisso.setIdCompromisso(Integer.parseInt(txtIDComp.getText()));
		compromisso.setIdPessoa(Integer.parseInt(txtID.getText()));
		compromisso.setAssunto(txtAssunto.getText());
		compromisso.setLocal(txtLocal.getText());
		compromisso.setHorario(txtHorario.getText());
		compromisso.setData(txtData.getText());
		compromisso.setContato(txtContato.getText());
		compromisso.setFoneContato(txtTelefone.getText());
		
		CompromissosDAO compromissosDAO = new CompromissosDAO();
		compromissosDAO.updateCompromisso(compromisso);
	}
	
}
