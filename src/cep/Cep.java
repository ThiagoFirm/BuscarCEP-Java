package cep;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cep extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JComboBox cbUF;
	private JLabel labelStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cep frame = new Cep();
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
	public Cep() {
		setTitle("Busca CEP");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cep.class.getResource("/img/3669170_home_ic_icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(null);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
			
		});
		btnLimpar.setBounds(10, 201, 85, 21);
		contentPane.add(btnLimpar);

		setContentPane(contentPane);

		JLabel labelCep = new JLabel("CEP");
		labelCep.setBounds(26, 54, 45, 13);
		contentPane.add(labelCep);

		txtCep = new JTextField();
		txtCep.setBounds(68, 51, 126, 19);
		contentPane.add(txtCep);
		txtCep.setColumns(10);

		JLabel labelEndereco = new JLabel("Endereço");
		labelEndereco.setBounds(10, 95, 71, 13);
		contentPane.add(labelEndereco);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(68, 92, 240, 19);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel labelBairro = new JLabel("Bairro");
		labelBairro.setBounds(20, 124, 45, 13);
		contentPane.add(labelBairro);

		txtBairro = new JTextField();
		txtBairro.setBounds(68, 121, 240, 19);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel labelCidade = new JLabel("Cidade");
		labelCidade.setBounds(20, 157, 45, 13);
		contentPane.add(labelCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(68, 154, 240, 19);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		cbUF = new JComboBox();
		cbUF.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE ", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR ", "SC", "SP", "SE ", "TO", "DF" }));
		cbUF.setBounds(344, 148, 45, 30);
		contentPane.add(cbUF);

		JLabel labelUF = new JLabel("UF");
		labelUF.setBounds(318, 157, 18, 13);
		contentPane.add(labelUF);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
		});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP");
					txtCep.requestFocus();
				} else {
					buscarCep();
				}
			}
		});
		btnBuscar.setBounds(223, 50, 85, 21);
		contentPane.add(btnBuscar);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setToolTipText("Sobre");
		btnSobre.setBackground(SystemColor.control);
		btnSobre.setIcon(new ImageIcon(Cep.class.getResource("/img/3017956_examination_inquiry_interrogation_investigation_outline_icon (1).png")));
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBounds(363, 40, 48, 48);
		contentPane.add(btnSobre);

		/* Uso da biblioteca Atxy2k para o txt.Cep */
		RestrictedTextField validar = new RestrictedTextField(txtCep);
		
		labelStatus = new JLabel("");
		labelStatus.setBounds(193, 54, 20, 20);
		contentPane.add(labelStatus);
		validar.setOnlyNums(true);
		validar.setLimit(8);
	}

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cbUF.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if(element.getQualifiedName().equals("resultado")) {
	        		resultado = element.getText();
	        		if (resultado.equals("1")) {
	        			labelStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/3669170_check_circle_icon.png")));
	        		} else {
	        			JOptionPane.showMessageDialog(null, "CEP não encontrado");
	        			}
				}
			// configurar o campo endereco
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
			
			
	}
		} catch (Exception e) {
			System.out.println(e);}
		}

		private void limpar() {
			txtCep.setText(null);
			txtEndereco.setText(null);
			txtBairro.setText(null);
			txtCidade.setText(null);
			cbUF.setKeySelectionManager(null);
			txtCep.requestFocus();
			labelStatus.setIcon(null);
		}
		
}

