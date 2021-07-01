package form;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import DAO.PessoaDAO;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;

public class Tela_Formulario extends JFrame{
	
	public JTextField textField_nome;
	public JFormattedTextField textField_dataNasc;
	public JTextField textField_cpf;
	public JTextField textField_rg;
	public JTextField textField_cidade;
	public JTextField textField_bairro;
	public JTextField textField_rua;
	public JTextField textField_numero;
	public JComboBox comboBox;
	public JRadioButton rdbtnM;
	public JRadioButton rdbtnF;
	private DateFormat formatter;
	private MaskFormatter mask_data;
	public int id = -1;
	private final String estados[] = {"PE", "PB", "RN", "AC", "AP", "BA", "AM", "CE", "DF", "SP",
			"ES", "GO", "MA", "MT", "MG", "MS", "PA", "PI", "RJ", "SE", "TO", "SC", "RO", "RR", "RS", "AL", "PR"};
	
	public Tela_Formulario() {
		
		this.setTitle("Formulário");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblDadosPessoais = new JLabel("DADOS PESSOAIS");
		lblDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDadosPessoais.setBounds(184, 11, 133, 30);
		getContentPane().add(lblDadosPessoais);
		
		JLabel lblNome = new JLabel("Nome Completo:");
		lblNome.setBounds(34, 67, 100, 14);
		getContentPane().add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(102, 117, 44, 14);
		getContentPane().add(lblCpf);
		
		JLabel lblRg = new JLabel("RG:");
		lblRg.setBounds(108, 142, 34, 14);
		getContentPane().add(lblRg);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(10, 92, 121, 14);
		getContentPane().add(lblDataDeNascimento);
		
		textField_nome = new JTextField();
		textField_nome.setBounds(152, 67, 272, 20);
		getContentPane().add(textField_nome);
		textField_nome.setColumns(10);
		
		try {
			mask_data = new MaskFormatter("####-##-##");
		} catch (ParseException e1) {e1.printStackTrace();}
		
		textField_dataNasc = new JFormattedTextField(mask_data);
		textField_dataNasc.setBounds(152, 92, 102, 20);
		getContentPane().add(textField_dataNasc);
		textField_dataNasc.setColumns(10);
		
		rdbtnM = new JRadioButton("M");
		rdbtnM.setBounds(260, 88, 44, 23);
		getContentPane().add(rdbtnM);
		
		rdbtnF = new JRadioButton("F");
		rdbtnF.setBounds(306, 88, 109, 23);
		getContentPane().add(rdbtnF);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnM);
		group.add(rdbtnF);
		
		JLabel lblEndereo = new JLabel("ENDERE\u00C7O");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereo.setBounds(206, 188, 88, 14);
		getContentPane().add(lblEndereo);
		
		textField_cpf = new JTextField();
		textField_cpf.setColumns(10);
		textField_cpf.setBounds(152, 117, 102, 20);
		getContentPane().add(textField_cpf);
		
		textField_rg = new JTextField();
		textField_rg.setColumns(10);
		textField_rg.setBounds(152, 142, 102, 20);
		getContentPane().add(textField_rg);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(85, 220, 46, 14);
		getContentPane().add(lblEstado);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(85, 249, 58, 14);
		getContentPane().add(lblCidade);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(90, 274, 56, 14);
		getContentPane().add(lblBairro);
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setBounds(103, 299, 47, 14);
		getContentPane().add(lblRua);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setBounds(80, 324, 65, 14);
		getContentPane().add(lblNmero);
		
		comboBox = new JComboBox(estados);
		comboBox.setBounds(152, 217, 56, 20);
		getContentPane().add(comboBox);
		
		textField_cidade = new JTextField();
		textField_cidade.setBounds(152, 249, 272, 20);
		getContentPane().add(textField_cidade);
		textField_cidade.setColumns(10);
		
		textField_bairro = new JTextField();
		textField_bairro.setBounds(152, 276, 272, 20);
		getContentPane().add(textField_bairro);
		textField_bairro.setColumns(10);
		
		textField_rua = new JTextField();
		textField_rua.setBounds(152, 301, 272, 20);
		getContentPane().add(textField_rua);
		textField_rua.setColumns(10);
		
		textField_numero = new JTextField();
		textField_numero.setBounds(152, 326, 86, 20);
		getContentPane().add(textField_numero);
		textField_numero.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(236, 396, 89, 23);
		getContentPane().add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				
				// id =-1 significa que iremos inserir um novo dado no banco de dados, caso contrário, iremos editar um dado
				// ja existente no banco de dados
				if(id == -1){
					
					PessoaDAO dao = new PessoaDAO();
					Pessoa pessoa = new Pessoa();
					Endereco endereco = new Endereco();
					formatter = new SimpleDateFormat("yyyy-MM-dd");
					
					try {
						
						// Carrega os dados do formulário no objeto pessoa
						pessoa.nome = textField_nome.getText();
						pessoa.dataNasc = new Date(formatter.parse(textField_dataNasc.getText()).getTime());
						pessoa.sexo = (rdbtnM.isSelected())? "Masculino": "Feminino";
						pessoa.cpf = textField_cpf.getText();
						pessoa.rg = textField_rg.getText();
						
						dao.inserir(pessoa);
						
						// Carrega os dados do formulário no objeto endereço
						/***************************** EXEMPLO 2 *********************************
						
						endereco.estado = comboBox.getSelectedItem().toString();
						endereco.cidade = textField_cidade.getText();
						endereco.bairro = textField_bairro.getText();
						endereco.rua = textField_rua.getText();
						endereco.numero = Integer.parseInt(textField_numero.getText());
						
						/***************************** EXEMPLO 2 *********************************/
						
					} catch (ParseException e) { e.printStackTrace();}
					
				}else{
				
					Pessoa p = new Pessoa();
					PessoaDAO dao = new PessoaDAO();
					formatter = new SimpleDateFormat("yyyy-MM-dd");
					
					
					try {
						
						p.id = id;
						p.nome = textField_nome.getText();
						p.dataNasc = new Date(formatter.parse(textField_dataNasc.getText()).getTime());
						p.sexo = (rdbtnM.isSelected())? "Masculino": "Feminino";
						p.cpf = textField_cpf.getText();
						p.rg = textField_rg.getText();
						
						dao.alterar(p);
						
					} catch (ParseException e) {e.printStackTrace();}
					
				}
			}
		});
		
		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(335, 396, 89, 23);
		getContentPane().add(btnListar);
		btnListar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				new Tela_ListarForm(Tela_Formulario.this).setVisible(true);
				
			}
		});
		

	
	}

	public static void main(String[] args) {
		new Tela_Formulario().setVisible(true);
	}
}
