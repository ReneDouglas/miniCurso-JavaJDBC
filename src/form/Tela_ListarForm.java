package form;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.PessoaDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Tela_ListarForm extends JDialog{
	
	private JTable tabela;
	private Tela_Formulario tela_pai;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JButton btnExcluir;
	private JButton btnEditar;

	public Tela_ListarForm(Tela_Formulario pai) {
		
		this.tela_pai = pai;
		this.setTitle("Lista de Cadastros");
		this.setSize(641, 353);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 605, 259);
		getContentPane().add(scrollPane);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(526, 14, 89, 23);
		getContentPane().add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(tabela.getSelectedRow() > -1){
					
					PessoaDAO dao = new PessoaDAO();
					dao.excluir(Integer.parseInt((String)modelo.getValueAt(tabela.getSelectedRow(), 0)));
					
					modelo.removeRow(tabela.getSelectedRow());
					
				}
				else JOptionPane.showMessageDialog(null, "Selecione uma linha.");
			}
		});	
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(427, 14, 89, 23);
		getContentPane().add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				//verificar se existe alguma linha da tabela selecionada
				if(tabela.getSelectedRow() > -1){
					
					//Aqui iremos adicionar no formulário os dados da linha selecionada na tabela
					// repare que o atributo id receberá um novo valor diferente de -1
					
					tela_pai.id = Integer.parseInt((String) modelo.getValueAt(tabela.getSelectedRow(), 0));
					tela_pai.textField_nome.setText((String) modelo.getValueAt(tabela.getSelectedRow(), 1));
					tela_pai.textField_dataNasc.setText((String) modelo.getValueAt(tabela.getSelectedRow(), 2));
					tela_pai.textField_cpf.setText((String) modelo.getValueAt(tabela.getSelectedRow(), 4));
					tela_pai.textField_rg.setText((String) modelo.getValueAt(tabela.getSelectedRow(), 5));
					tela_pai.comboBox.setSelectedItem((String) modelo.getValueAt(tabela.getSelectedRow(), 6));
					tela_pai.textField_cidade.setText((String) modelo.getValueAt(tabela.getSelectedRow(), 7));
					tela_pai.textField_bairro.setText((String) modelo.getValueAt(tabela.getSelectedRow(), 8));
					tela_pai.textField_rua.setText((String) modelo.getValueAt(tabela.getSelectedRow(), 9));
					tela_pai.textField_numero.setText((String) modelo.getValueAt(tabela.getSelectedRow(), 10));
					
					if(((String) modelo.getValueAt(tabela.getSelectedRow(), 3)).equals("Masculino")){
						
						tela_pai.rdbtnM.setSelected(true);
						
					}else tela_pai.rdbtnF.setSelected(true);
					
					tela_pai.repaint();
					
				}
				else JOptionPane.showMessageDialog(null, "Selecione uma linha.");
			}
		});	
		
		//Vetor de colunas da Tabela
		
		String[] colunas = new String[11];
		
		colunas[0] = "ID";
		colunas[1] = "Nome";
		colunas[2] = "Data de Nasc.";
		colunas[3] = "Sexo";
		colunas[4] = "CPF";
		colunas[5] = "RG";
		colunas[6] = "Estado";
		colunas[7] = "Cidade";
		colunas[8] = "Bairro";
		colunas[9] = "Rua";
		colunas[10] = "Numero";

		//criando o modelo da tabela
		modelo = new DefaultTableModel(new Object[][]{}, colunas);	
		//criando tabela
		tabela = new JTable(modelo);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tabela.getColumnModel().getColumn(0).setPreferredWidth(30);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(90);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(90);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(90);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(90);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(7).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(8).setPreferredWidth(130);
		tabela.getColumnModel().getColumn(9).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(10).setPreferredWidth(100);
		
		//adicionando a tabela a um scrollpanel
		scrollPane.setViewportView(tabela);
		
		preencher_tabela();
	}
	
	private void preencher_tabela(){
				
		PessoaDAO dao = new PessoaDAO();
		ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
		
		lista = dao.listar();
		
		for (int i = 0; i < lista.size(); i++) {
			
			modelo.addRow(new String[]{""+lista.get(i).id, ""+lista.get(i).nome,
					""+lista.get(i).dataNasc, ""+lista.get(i).sexo, 
					""+lista.get(i).cpf, ""+lista.get(i).rg});
			
		}
				
	}

}
