package policlinica;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class LogIn {
	
	// Fereastra de login, care contine textul si campurile necesare
	// Care trebuie copmletate de un utilizator la logare
	// Cand se apasa pe butonul login, se verifica existenta userului
	// Sau corectitudinea acreditarilor

	private JFrame frame;
	private JPanel panel;
	private Conexiune activeConnection;

	public LogIn() {
		
		activeConnection = new Conexiune();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Log In");
		frame.setResizable(false);
		
		panel = new JPanel();
		panel.setBackground(new Color(102, 255, 153));
		panel.setLayout(null);
		frame.setContentPane(panel);
		
		JLabel loginIcon = new JLabel();
		loginIcon.setBounds(100, 15, 200, 200);
		loginIcon.setIcon(new ImageIcon((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagini/login.png")))
				.getImage().getScaledInstance(loginIcon.getWidth(), loginIcon.getHeight(), Image.SCALE_SMOOTH))));
		panel.add(loginIcon);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(10, 250, 115, 35);
		panel.add(lblEmail);
		
		JTextField txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEmail.setBounds(155, 250, 225, 35);
		panel.add(txtEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(10, 300, 115, 35);
		panel.add(lblPassword);
		
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPassword.setBounds(155, 300, 225, 35);
		panel.add(txtPassword);
	
		JButton btnBack = new JButton("<-");
		btnBack.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new WelcomeScreen().getFrame().setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnBack.setBounds(10, 350, 115, 45);
		panel.add(btnBack);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLogIn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(txtEmail.getText().trim().isBlank() || txtPassword.getText().trim().isBlank()) {
					JLabel errField = new JLabel("Eroare! Toate campurile trebuie completate.");
					errField.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errField, "Eroare", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						String userPass = null, userCNP = null, userOras = null, userTel = null, userIBAN = null;
						PreparedStatement selStmt = activeConnection.getConnection().prepareStatement("SELECT email, parola, CNP, oras, telefon, IBAN, tip FROM utilizatori WHERE email = ?");
						selStmt.setString(1, txtEmail.getText());
						ResultSet rSet = selStmt.executeQuery();
						if(false == rSet.next()) {
							JLabel errUser = new JLabel("Eroare! Utilizator inexistent.");
							errUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, errUser, "Eroare", JOptionPane.ERROR_MESSAGE);
						} else {
							userPass = rSet.getString("parola");
							userCNP = rSet.getString("CNP");
							userOras = rSet.getString("oras");
							userTel = rSet.getString("telefon");
							userIBAN = rSet.getString("IBAN");
							if(false == txtPassword.getText().equals(userPass)) {
								JLabel errPass = new JLabel("Eroare! Parola este incorecta.");
								errPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
								JOptionPane.showMessageDialog(frame, errPass, "Eroare", JOptionPane.ERROR_MESSAGE);
							} else {
								JLabel newLogin = new JLabel("Logare cu succes!");
								newLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
								JOptionPane.showMessageDialog(frame, newLogin, "Succes", JOptionPane.INFORMATION_MESSAGE);
								frame.dispose();
								PreparedStatement selStmt2 = activeConnection.getConnection().prepareStatement("SELECT nume, prenume, functie, salariu FROM angajati WHERE CNP = ?");
								selStmt2.setString(1, userCNP);
								ResultSet rSet2 = selStmt2.executeQuery();
								rSet2.next();
								new SelectModul(new User(rSet2.getString("nume"), rSet2.getString("prenume"), txtEmail.getText(), txtPassword.getText().toString(), userCNP, 
										userOras, userTel, userIBAN, rSet.getString("tip"), rSet2.getString("functie"), rSet2.getInt("salariu"))).getFrame().setVisible(true);
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
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnLogIn.setBounds(155, 350, 225, 45);
		panel.add(btnLogIn);
		
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
}
