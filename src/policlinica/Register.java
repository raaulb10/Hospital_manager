package policlinica;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class Register {

	// Fereastra contine campurile pe care un utilizator le completeaza
	// Cand se apasa pe register, se introduce o inregistrare noua
	// In tabela utilizatori
	
	private JFrame frame;
	private JPanel panel;
	private Conexiune activeConnection;

	public Register() {
		
		activeConnection = new Conexiune();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 690);
		frame.setTitle("Register");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(51, 153, 255));
		panel.setLayout(null);
		frame.setContentPane(panel);
		
		JLabel iconRegister = new JLabel();
		iconRegister.setBounds(135, 10, 200, 200);
		iconRegister.setIcon(new ImageIcon((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagini/register.png")))
				.getImage().getScaledInstance(iconRegister.getWidth(), iconRegister.getHeight(), Image.SCALE_SMOOTH))));
		panel.add(iconRegister);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(25, 250, 130, 25);
		panel.add(lblEmail);
		
		JTextField txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtEmail.setBounds(165, 250, 245, 25);
		panel.add(txtEmail);
		
		JLabel lblPassword = new JLabel("Parola");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(25, 285, 130, 25);
		panel.add(lblPassword);
		
		JPasswordField txtPass = new JPasswordField();
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPass.setBounds(165, 285, 245, 25);
		panel.add(txtPass);
		
		JLabel lblTipUtilizator = new JLabel("Tip utilizator");
		lblTipUtilizator.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipUtilizator.setBounds(25, 320, 130, 25);
		panel.add(lblTipUtilizator);
		
		JComboBox<String> txtTip = new JComboBox<String>();
		txtTip.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTip.addItem("administrator");
		txtTip.addItem("super-administrator");
		txtTip.addItem("angajat");
		txtTip.setBounds(165, 320, 245, 25);
		panel.add(txtTip);
		
		JLabel lblCNP = new JLabel("CNP");
		lblCNP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCNP.setBounds(25, 355, 130, 25);
		panel.add(lblCNP);
		
		JTextField txtCNP = new JTextField();
		txtCNP.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtCNP.setBounds(165, 355, 245, 25);
		panel.add(txtCNP);
		
		JTextField txtNume = new JTextField();
		txtNume.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNume.setBounds(165, 390, 245, 25);
		panel.add(txtNume);
		
		JLabel lblNume = new JLabel("Nume");
		lblNume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNume.setBounds(25, 390, 130, 25);
		panel.add(lblNume);
		
		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrenume.setBounds(25, 425, 130, 25);
		panel.add(lblPrenume);
		
		JTextField txtPrenume = new JTextField();
		txtPrenume.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrenume.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPrenume.setBounds(165, 425, 245, 25);
		panel.add(txtPrenume);
		
		JTextField txtOras = new JTextField();
		txtOras.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtOras.setBounds(165, 460, 245, 25);
		panel.add(txtOras);
		
		JLabel lblOras = new JLabel("Oras");
		lblOras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOras.setBounds(25, 460, 130, 25);
		panel.add(lblOras);
		
		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefon.setBounds(25, 495, 130, 25);
		panel.add(lblTelefon);
		
		JTextField txtTelefon = new JTextField();
		txtTelefon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTelefon.setBounds(165, 495, 245, 25);
		panel.add(txtTelefon);
		
		JTextField txtIBAN = new JTextField();
		txtIBAN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtIBAN.setBounds(165, 530, 245, 25);
		panel.add(txtIBAN);
		
		JLabel lblIBAN = new JLabel("IBAN");
		lblIBAN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIBAN.setBounds(25, 530, 130, 25);
		panel.add(lblIBAN);
		
		JButton btnBack = new JButton("<-");
		btnBack.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new WelcomeScreen().getFrame().setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnBack.setBounds(25, 600, 115, 45);
		panel.add(btnBack);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnRegister.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(txtEmail.getText().trim().isBlank() || txtNume.getText().trim().isBlank() || txtPrenume.getText().trim().isBlank() ||
					txtCNP.getText().trim().isBlank() || txtOras.getText().trim().isBlank() || txtTelefon.getText().trim().isBlank() ||
					txtIBAN.getText().trim().isBlank() || txtPass.getText().trim().isBlank()) {
						JLabel errField = new JLabel("Eroare! Toate campurile trebuie completate.");
						errField.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errField, "Eroare", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						PreparedStatement selStmt = activeConnection.getConnection().prepareStatement(
								"SELECT email, CNP, telefon, IBAN FROM utilizatori WHERE CNP = ?");
						selStmt.setString(1, txtEmail.getText());
						ResultSet rSet = selStmt.executeQuery();
						if(true == rSet.next()) {
							JLabel errUser = new JLabel("Eroare! Angajatul are deja cont.");
							errUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, errUser, "Eroare", JOptionPane.ERROR_MESSAGE);
						} else {
							PreparedStatement selStmt2 = activeConnection.getConnection().prepareStatement(
									"SELECT nume, prenume FROM angajati WHERE CNP = ?");
							selStmt2.setString(1, txtCNP.getText());
							ResultSet rSet2 = selStmt2.executeQuery();
							rSet2.next();
							String userNume = rSet2.getString("nume");
							String userPrenume = rSet2.getString("prenume");
							if(userNume.equals(txtNume.getText()) == false || userPrenume.equals(txtPrenume.getText()) == false) {
								JLabel errUser2 = new JLabel("Eroare! Numele nu este corect!");
								errUser2.setFont(new Font("Tahoma", Font.PLAIN, 20));
								JOptionPane.showMessageDialog(frame, errUser2, "Eroare", JOptionPane.ERROR_MESSAGE);
							} else {
								PreparedStatement insStmt = activeConnection.getConnection().prepareStatement(
										"INSERT INTO utilizatori(email, parola, CNP, oras, telefon, IBAN, tip) " +
										"values(?, ?, ?, ?, ?, ?, ?)");
								insStmt.setString( 1, txtEmail.getText());
								insStmt.setString( 2, txtPass.getText());
								insStmt.setString( 3, txtCNP.getText());
								insStmt.setString( 4, txtOras.getText());
								insStmt.setString( 5, txtTelefon.getText());
								insStmt.setString( 6, txtIBAN.getText());
								insStmt.setString( 7, txtTip.getSelectedItem().toString());
								insStmt.executeUpdate();
								JLabel newUser = new JLabel("Cont creeat cu succes!");
								newUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
								JOptionPane.showMessageDialog(frame, newUser, "Eroare", JOptionPane.INFORMATION_MESSAGE);
								frame.setVisible(false);
								String userFunctie = null;
								int userSalariu = 0;
								try {
									PreparedStatement selStmt3 = activeConnection.getConnection().prepareStatement("SELECT functie, salariu FROM angajati WHERE CNP = ?");
									selStmt3.setString(1, txtCNP.getText());
									ResultSet rSet3 = selStmt3.executeQuery();
									rSet3.next();
									userFunctie = rSet3.getString("functie");
									userSalariu = rSet3.getInt("salariu");
								} catch (SQLException sqlEx) {
									JLabel errLabel = new JLabel(sqlEx.getMessage());
									errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
									JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
								}
								new SelectModul(new User(txtNume.getText(), txtPrenume.getText(), txtEmail.getText(), txtPass.getText(), txtCNP.getText(), txtOras.getText(),
										txtTelefon.getText(), txtIBAN.getText(), txtTip.getSelectedItem().toString(), userFunctie, userSalariu)).getFrame().setVisible(true);	
							}
						}
					} catch (Exception sqlEx) {
						JLabel errLabel = new JLabel(sqlEx.getMessage());
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRegister.setBounds(165, 600, 245, 45);
		panel.add(btnRegister);
		
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
}
