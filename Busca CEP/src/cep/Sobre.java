package cep;

import java.awt.Cursor;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sobre extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/3669170_home_ic_icon.png")));
		setTitle("Sobre");
		setResizable(false);
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar CEP - 1.0");
		lblNewLabel.setBounds(31, 44, 161, 13);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Autor: Thiago Firmino");
		lblNewLabel_1.setBounds(31, 88, 123, 13);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Web Service: ");
		lblNewLabel_2.setBounds(31, 139, 123, 13);
		getContentPane().add(lblNewLabel_2);
		
		JLabel labelWebService = new JLabel("viacep.com.br");
		labelWebService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://viacep.com.br/");
			}
		});
		labelWebService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		labelWebService.setForeground(SystemColor.textHighlight);
		labelWebService.setBounds(114, 139, 169, 13);
		getContentPane().add(labelWebService);
		
		JButton btnGithub = new JButton("");
		btnGithub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://github.com/ThiagoFirm");
			}
		});
		btnGithub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGithub.setToolTipText("Github");
		btnGithub.setIcon(new ImageIcon(Sobre.class.getResource("/img/317712_code repository_github_repository_resource_icon.png")));
		btnGithub.setBounds(34, 195, 48, 48);
		getContentPane().add(btnGithub);
	}
		
		private void link (String site) {
			Desktop desktop = Desktop.getDesktop();
			try {
				URI uri = new URI(site);
				desktop.browse(uri);
			}catch(Exception e) {
				System.out.print(e);
			}
		}
		
}
