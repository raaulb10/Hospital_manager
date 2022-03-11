package policlinica;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

public class AdminPanel {
	
	// Valabila numai adminilor/ super-adminilor

	private JFrame frame;
	private JPanel panel;
	private Conexiune activeConnection;
	
	public AdminPanel(User activeUser) {
		
		activeConnection = new Conexiune();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 605, 575);
		frame.setTitle("Admin Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 205, 155));
		frame.setContentPane(panel);
		
		JLabel userIcon = new JLabel();
		userIcon.setBounds(60, 10, 100, 100);
		userIcon.setIcon(new ImageIcon((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagini/user.png")))
				.getImage().getScaledInstance(userIcon.getWidth(), userIcon.getHeight(), Image.SCALE_SMOOTH))));
		panel.add(userIcon);
		
		JPanel panelUser = new JPanel();
		panelUser.setLayout(null);
		panelUser.setBackground(new Color(220, 20, 60));
		panelUser.setBounds(0, 30, 596, 55);
		panel.add(panelUser);
		
		JButton btnBack = new JButton("<-");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBack.setBounds(0, 0, 55, 55);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new SelectModul(activeUser).getFrame().setVisible(true);
			}
		});
		panelUser.add(btnBack);
		
		JLabel userInfo_1 = new JLabel("Logged in as: " + activeUser.getEmail(), SwingConstants.LEFT);
		userInfo_1.setBounds(160, 0, 270, 25);
		userInfo_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelUser.add(userInfo_1);
		
		JLabel userInfo_2 = new JLabel(activeUser.getTip() + " (" + activeUser.getFunctie() + ")", SwingConstants.LEFT);
		userInfo_2.setFont(new Font("Tahoma", Font.ITALIC, 15));
		userInfo_2.setBounds(160, 30, 270, 25);
		panelUser.add(userInfo_2);
		
		JButton btnDisconnect = new JButton("DISCONNECT");
		btnDisconnect.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDisconnect.setBounds(441, 0, 155, 55);
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LogIn().getFrame().setVisible(true);
			}
		});
		panelUser.add(btnDisconnect);
		
		
		
		JPanel panelE = new JPanel();
		panelE.setBounds(10, 195, 571, 237);
		panelE.setBackground(new Color(255, 205, 155));
		panelE.setVisible(false);
		panelE.setLayout(null);
		panel.add(panelE);
		
		JLabel lblEmailV = new JLabel("Email vechi");
		lblEmailV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmailV.setBounds(135, 50, 125, 35);
		panelE.add(lblEmailV);
		
		JTextField txtEmailV = new JTextField();
		txtEmailV.setBounds(280, 55, 200, 30);
		txtEmailV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEmailV.setEditable(false);
		panelE.add(txtEmailV);
		
		JLabel lblUtilizator1 = new JLabel("Utilizator");
		lblUtilizator1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUtilizator1.setBounds(135, 10, 125, 35);
		panelE.add(lblUtilizator1);
		
		JComboBox<String> txtUtilizator1 = new JComboBox<String>();
		txtUtilizator1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PreparedStatement selStmt = activeConnection.getConnection().prepareStatement("SELECT email FROM utilizatori WHERE email = ?");
					selStmt.setString(1, txtUtilizator1.getSelectedItem().toString());
					ResultSet selSet = selStmt.executeQuery();
					selSet.next();
					txtEmailV.setText(selSet.getString("email"));
				} catch (SQLException sqlEx) {}
			}
		});
		txtUtilizator1.setBounds(280, 15, 200, 30);
		txtUtilizator1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelE.add(txtUtilizator1);
		
		JLabel lblEmailN = new JLabel("Email nou");
		lblEmailN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmailN.setBounds(135, 90, 125, 35);
		panelE.add(lblEmailN);
		
		JTextField txtEmailN = new JTextField();
		txtEmailN.setBounds(280, 95, 200, 30);
		txtEmailN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelE.add(txtEmailN);
		
		JButton btnMod1 = new JButton("Modifica");
		btnMod1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtEmailN.getText().trim().isBlank() == true) {
					JLabel errLabel = new JLabel("Toate campurile trebuie completate!");
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						Statement selStmt = activeConnection.getConnection().createStatement();
						selStmt.execute("SELECT email FROM utilizatori");
						ResultSet selSet = selStmt.getResultSet();
						boolean ok = true;
						while(true == selSet.next()) {
							if(txtEmailN.getText().equals(selSet.getString("email"))) {
								JLabel errLabel = new JLabel("Emailul trebuie sa fie unic!");
								errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
								JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
								ok = false;
							}
						}
						if(ok == true) {
							PreparedStatement updStmt = activeConnection.getConnection().prepareStatement("UPDATE utilizatori SET email = ? WHERE email = ?");
							updStmt.setString(1, txtEmailN.getText());
							updStmt.setString(2, txtUtilizator1.getSelectedItem().toString());
							updStmt.executeUpdate();
							JLabel successLabel = new JLabel("Email setat cu succes!");
							successLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, successLabel, "Succes", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (SQLException sqlEx) {
						JLabel errLabel = new JLabel(sqlEx.getMessage());
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				}
				txtUtilizator1.setSelectedIndex(0);
				txtEmailV.setText("");
				txtEmailN.setText("");
				panelE.setVisible(false);
			}
		});
		btnMod1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMod1.setBounds(205, 140, 175, 25);
		panelE.add(btnMod1);
		
		
		
		JPanel panelP = new JPanel();
		panelP.setBounds(10, 195, 571, 237);
		panelP.setBackground(new Color(255, 205, 155));
		panelP.setVisible(false);
		panelP.setLayout(null);
		panel.add(panelP);
		
		JLabel lblParolaV = new JLabel("Parola veche");
		lblParolaV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblParolaV.setBounds(135, 50, 125, 35);
		panelP.add(lblParolaV);
		
		JTextField txtParolaV = new JTextField();
		txtParolaV.setBounds(280, 55, 200, 30);
		txtParolaV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtParolaV.setEditable(false);
		panelP.add(txtParolaV);
		
		JLabel lblUtilizator2 = new JLabel("Utilizator");
		lblUtilizator2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUtilizator2.setBounds(135, 10, 125, 35);
		panelP.add(lblUtilizator2);
		
		JComboBox<String> txtUtilizator2 = new JComboBox<String>();
		txtUtilizator2.setBounds(280, 15, 200, 30);
		txtUtilizator2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUtilizator2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PreparedStatement selStmt = activeConnection.getConnection().prepareStatement("SELECT parola FROM utilizatori WHERE email = ?");
					selStmt.setString(1, txtUtilizator2.getSelectedItem().toString());
					ResultSet selSet = selStmt.executeQuery();
					selSet.next();
					txtParolaV.setText(selSet.getString("parola"));
				} catch (SQLException sqlEx) {}
			}
		});
		
		panelP.add(txtUtilizator2);
		
		JLabel lblParolaN = new JLabel("Parola noua");
		lblParolaN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblParolaN.setBounds(135, 90, 125, 35);
		panelP.add(lblParolaN);
		
		JPasswordField txtParolaN = new JPasswordField();
		txtParolaN.setBounds(280, 95, 200, 30);
		txtParolaN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelP.add(txtParolaN);
		
		JButton btnMod2 = new JButton("Modifica");
		btnMod2.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(txtParolaN.getText().trim().isBlank() == true) {
					JLabel errLabel = new JLabel("Toate campurile trebuie completate!");
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						PreparedStatement updStmt = activeConnection.getConnection().prepareStatement("UPDATE utilizatori SET parola = ? WHERE email = ?");
						updStmt.setString(1, txtParolaN.getText());
						updStmt.setString(2, txtUtilizator2.getSelectedItem().toString());
						updStmt.executeUpdate();
						JLabel successLabel = new JLabel("Parola setata cu succes!");
						successLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, successLabel, "Succes", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException sqlEx) {
						JLabel errLabel = new JLabel(sqlEx.getMessage());
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				}
				txtUtilizator2.setSelectedIndex(0);
				txtParolaV.setText("");
				txtParolaN.setText("");
				panelP.setVisible(false);
			}
		});
		btnMod2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMod2.setBounds(205, 140, 175, 25);
		panelP.add(btnMod2);
		
		
		
		JPanel panelA = new JPanel();
		panelA.setBounds(10, 195, 571, 237);
		panelA.setBackground(new Color(255, 205, 155));
		panelA.setVisible(false);
		panelA.setLayout(null);
		panel.add(panelA);
		
		JLabel lblOrasV = new JLabel("Oras vechi");
		lblOrasV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOrasV.setBounds(135, 50, 125, 35);
		panelA.add(lblOrasV);
		
		JTextField txtOrasV = new JTextField();
		txtOrasV.setBounds(280, 55, 200, 30);
		txtOrasV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtOrasV.setEditable(false);
		panelA.add(txtOrasV);
		
		JLabel lblUtilizator3 = new JLabel("Utilizator");
		lblUtilizator3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUtilizator3.setBounds(135, 10, 125, 35);
		panelA.add(lblUtilizator3);
		
		JComboBox<String> txtUtilizator3 = new JComboBox<String>();
		txtUtilizator3.setBounds(280, 15, 200, 30);
		txtUtilizator3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUtilizator3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PreparedStatement selStmt = activeConnection.getConnection().prepareStatement("SELECT oras FROM utilizatori WHERE email = ?");
					selStmt.setString(1, txtUtilizator3.getSelectedItem().toString());
					ResultSet selSet = selStmt.executeQuery();
					selSet.next();
					txtOrasV.setText(selSet.getString("oras"));
				} catch (SQLException sqlEx) {}
			}
		});
		panelA.add(txtUtilizator3);
		
		JLabel lblOrasN = new JLabel("Oras nou");
		lblOrasN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOrasN.setBounds(135, 90, 125, 35);
		panelA.add(lblOrasN);
		
		JTextField txtOrasN = new JTextField();
		txtOrasN.setBounds(280, 95, 200, 30);
		txtOrasN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelA.add(txtOrasN);
		
		JButton btnMod3 = new JButton("Modifica");
		btnMod3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtOrasN.getText().trim().isBlank() == true) {
					JLabel errLabel = new JLabel("Toate campurile trebuie completate!");
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						PreparedStatement updStmt = activeConnection.getConnection().prepareStatement("UPDATE utilizatori SET oras = ? WHERE email = ?");
						updStmt.setString(1, txtOrasN.getText());
						updStmt.setString(2, txtUtilizator3.getSelectedItem().toString());
						updStmt.executeUpdate();
						JLabel successLabel = new JLabel("Oras setat cu succes!");
						successLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, successLabel, "Succes", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException sqlEx) {
						JLabel errLabel = new JLabel(sqlEx.getMessage());
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				}
				txtUtilizator3.setSelectedIndex(0);
				txtOrasV.setText("");
				txtOrasN.setText("");
				panelA.setVisible(false);
			}
		});
		btnMod3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMod3.setBounds(205, 140, 175, 25);
		panelA.add(btnMod3);
		
		
		
		JPanel panelT = new JPanel();
		panelT.setBounds(10, 195, 571, 237);
		panelT.setBackground(new Color(255, 205, 155));
		panelT.setVisible(false);
		panelT.setLayout(null);
		panel.add(panelT);
		
		JLabel lblTelefonV = new JLabel("Nr vechi");
		lblTelefonV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefonV.setBounds(135, 50, 125, 35);
		panelT.add(lblTelefonV);
		
		JTextField txtTelefonV = new JTextField();
		txtTelefonV.setBounds(280, 55, 200, 30);
		txtTelefonV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTelefonV.setEditable(false);
		panelT.add(txtTelefonV);
		
		JLabel lblUtilizator4 = new JLabel("Utilizator");
		lblUtilizator4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUtilizator4.setBounds(135, 10, 125, 35);
		panelT.add(lblUtilizator4);
		
		JComboBox<String> txtUtilizator4 = new JComboBox<String>();
		txtUtilizator4.setBounds(280, 15, 200, 30);
		txtUtilizator4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUtilizator4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PreparedStatement selStmt = activeConnection.getConnection().prepareStatement("SELECT telefon FROM utilizatori WHERE email = ?");
					selStmt.setString(1, txtUtilizator4.getSelectedItem().toString());
					ResultSet selSet = selStmt.executeQuery();
					selSet.next();
					txtTelefonV.setText(selSet.getString("telefon"));
				} catch (SQLException sqlEx) {}
			}
		});
		panelT.add(txtUtilizator4);
	
		JLabel lblTelefonN = new JLabel("Nr nou");
		lblTelefonN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefonN.setBounds(135, 90, 125, 35);
		panelT.add(lblTelefonN);
		
		JTextField txtTelefonN = new JTextField();
		txtTelefonN.setBounds(280, 95, 200, 30);
		txtTelefonN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelT.add(txtTelefonN);
		
		JButton btnMod4 = new JButton("Modifica");
		btnMod4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtTelefonN.getText().trim().isBlank() == true) {
					JLabel errLabel = new JLabel("Toate campurile trebuie completate!");
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						Statement selStmt = activeConnection.getConnection().createStatement();
						selStmt.execute("SELECT telefon FROM utilizatori");
						ResultSet selSet = selStmt.getResultSet();
						boolean ok = true;
						while(true == selSet.next()) {
							if(txtTelefonN.getText().equals(selSet.getString("telefon"))) {
								JLabel errLabel = new JLabel("Nr de telefon trebuie sa fie unic!");
								errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
								JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
								ok = false;
							}
						}
						if(ok == true) {
							PreparedStatement updStmt = activeConnection.getConnection().prepareStatement("UPDATE utilizatori SET telefon = ? WHERE email = ?");
							updStmt.setString(1, txtTelefonN.getText());
							updStmt.setString(2, txtUtilizator4.getSelectedItem().toString());
							updStmt.executeUpdate();
							JLabel successLabel = new JLabel("Nr de telefon setat cu succes!");
							successLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, successLabel, "Succes", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (SQLException sqlEx) {
						JLabel errLabel = new JLabel(sqlEx.getMessage());
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				}
				txtUtilizator4.setSelectedIndex(0);
				txtTelefonV.setText("");
				txtTelefonN.setText("");
				panelT.setVisible(false);
			}
		});
		btnMod4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMod4.setBounds(205, 140, 175, 25);
		panelT.add(btnMod4);
		
		
		
		JPanel panelI = new JPanel();
		panelI.setBounds(10, 195, 571, 237);
		panelI.setBackground(new Color(255, 205, 155));
		panelI.setVisible(false);
		panelI.setLayout(null);
		panel.add(panelI);
		
		JLabel lblIBANV = new JLabel("IBAN vechi");
		lblIBANV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIBANV.setBounds(135, 50, 125, 35);
		panelI.add(lblIBANV);
		
		JTextField txtIBANV = new JTextField();
		txtIBANV.setBounds(280, 55, 200, 30);
		txtIBANV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelI.add(txtIBANV);
		
		JLabel lblUtilizator5 = new JLabel("Utilizator");
		lblUtilizator5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUtilizator5.setBounds(135, 10, 125, 35);
		txtIBANV.setEditable(false);
		panelI.add(lblUtilizator5);
		
		JComboBox<String> txtUtilizator5 = new JComboBox<String>();
		txtUtilizator5.setBounds(280, 15, 200, 30);
		txtUtilizator5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUtilizator5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PreparedStatement selStmt = activeConnection.getConnection().prepareStatement("SELECT IBAN FROM utilizatori WHERE email = ?");
					selStmt.setString(1, txtUtilizator5.getSelectedItem().toString());
					ResultSet selSet = selStmt.executeQuery();
					selSet.next();
					txtIBANV.setText(selSet.getString("IBAN"));
				} catch (SQLException sqlEx) {}
			}
		});
		panelI.add(txtUtilizator5);
		
		JLabel lblIBANN = new JLabel("IBAN nou");
		lblIBANN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIBANN.setBounds(135, 90, 125, 35);
		panelI.add(lblIBANN);
		
		JTextField txtIBANN = new JTextField();
		txtIBANN.setBounds(280, 95, 200, 30);
		txtIBANN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelI.add(txtIBANN);
		
		JButton btnMod5 = new JButton("Modifica");
		btnMod5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtIBANN.getText().trim().isBlank() == true) {
					JLabel errLabel = new JLabel("Toate campurile trebuie completate!");
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						Statement selStmt = activeConnection.getConnection().createStatement();
						selStmt.execute("SELECT IBAN FROM utilizatori");
						ResultSet selSet = selStmt.getResultSet();
						boolean ok = true;
						while(true == selSet.next()) {
							if(txtIBANN.getText().equals(selSet.getString("IBAN"))) {
								JLabel errLabel = new JLabel("Ibanul trebuie sa fie unic!");
								errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
								JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
								ok = false;
							}
						}
						if(ok == true) {
							PreparedStatement updStmt = activeConnection.getConnection().prepareStatement("UPDATE utilizatori SET IBAN = ? WHERE email = ?");
							updStmt.setString(1, txtIBANN.getText());
							updStmt.setString(2, txtUtilizator5.getSelectedItem().toString());
							updStmt.executeUpdate();
							JLabel successLabel = new JLabel("IBAN setat cu succes!");
							successLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, successLabel, "Succes", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (SQLException sqlEx) {
						JLabel errLabel = new JLabel(sqlEx.getMessage());
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				}
				txtUtilizator5.setSelectedIndex(0);
				txtIBANV.setText("");
				txtIBANN.setText("");
				panelI.setVisible(false);
			}
		});
		btnMod5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMod5.setBounds(205, 140, 175, 25);
		panelI.add(btnMod5);
		
		
		
		JLabel lblAcces = new JLabel("Modifica");
		lblAcces.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAcces.setBounds(10, 127, 100, 25);
		panel.add(lblAcces);
		
		JButton btnEmail = new JButton("Email");
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUtilizator1.removeAllItems();
				try {
					PreparedStatement selStmt;
					if(activeUser.getTip().equals("administrator")) {
						selStmt = activeConnection.getConnection().prepareStatement("SELECT email FROM utilizatori WHERE tip = ?");
						selStmt.setString(1, "angajat");
					} else {
						selStmt = activeConnection.getConnection().prepareStatement("SELECT email FROM utilizatori WHERE tip != ?");
						selStmt.setString(1, "super-administrator");
					}
					ResultSet selSet = selStmt.executeQuery();
					while(true == selSet.next()) {
						txtUtilizator1.addItem(selSet.getString("email"));
					}
				} catch (SQLException sqlEx) {}
				txtEmailV.setText(txtEmailN.getText());
				panelE.setVisible(true);
				panelP.setVisible(false);
				panelA.setVisible(false);
				panelT.setVisible(false);
				panelI.setVisible(false);
			}
		});
		btnEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEmail.setBounds(97, 125, 150, 25);
		panel.add(btnEmail);
		
		JButton btnParola = new JButton("Parola");
		btnParola.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnParola.setBounds(257, 127, 150, 25);
		btnParola.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				txtUtilizator2.removeAllItems();
				try {
					PreparedStatement selStmt;
					if(activeUser.getTip().equals("administrator")) {
						selStmt = activeConnection.getConnection().prepareStatement("SELECT email FROM utilizatori WHERE tip = ?");
						selStmt.setString(1, "angajat");
					} else {
						selStmt = activeConnection.getConnection().prepareStatement("SELECT email FROM utilizatori WHERE tip != ?");
						selStmt.setString(1, "super-administrator");
					}
					ResultSet selSet = selStmt.executeQuery();
					while(true == selSet.next()) {
						txtUtilizator2.addItem(selSet.getString("email"));
					}
				} catch (SQLException sqlEx) {}
				txtParolaV.setText(txtParolaN.getText());
				panelE.setVisible(false);
				panelP.setVisible(true);
				panelA.setVisible(false);
				panelT.setVisible(false);
				panelI.setVisible(false);
			}
		});
		panel.add(btnParola);
		
		JButton btnOras = new JButton("Oras");
		btnOras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOras.setBounds(417, 127, 150, 25);
		btnOras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUtilizator3.removeAllItems();
				try {
					PreparedStatement selStmt;
					if(activeUser.getTip().equals("administrator")) {
						selStmt = activeConnection.getConnection().prepareStatement("SELECT email FROM utilizatori WHERE tip = ?");
						selStmt.setString(1, "angajat");
					} else {
						selStmt = activeConnection.getConnection().prepareStatement("SELECT email FROM utilizatori WHERE tip != ?");
						selStmt.setString(1, "super-administrator");
					}
					ResultSet selSet = selStmt.executeQuery();
					while(true == selSet.next()) {
						txtUtilizator3.addItem(selSet.getString("email"));
					}
				} catch (SQLException sqlEx) {}
				txtOrasV.setText(txtOrasN.getText());
				panelE.setVisible(false);
				panelP.setVisible(false);
				panelA.setVisible(true);
				panelT.setVisible(false);
				panelI.setVisible(false);
			}
		});
		panel.add(btnOras);
		
		JButton btnTelefon = new JButton("Telefon");
		btnTelefon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTelefon.setBounds(179, 160, 150, 25);
		btnTelefon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUtilizator4.removeAllItems();
				try {
					PreparedStatement selStmt;
					if(activeUser.getTip().equals("administrator")) {
						selStmt = activeConnection.getConnection().prepareStatement("SELECT email FROM utilizatori WHERE tip = ?");
						selStmt.setString(1, "angajat");
					} else {
						selStmt = activeConnection.getConnection().prepareStatement("SELECT email FROM utilizatori WHERE tip != ?");
						selStmt.setString(1, "super-administrator");
					}
					ResultSet selSet = selStmt.executeQuery();
					while(true == selSet.next()) {
						txtUtilizator4.addItem(selSet.getString("email"));
					}
				} catch (SQLException sqlEx) {}
				txtTelefonV.setText(txtTelefonN.getText());
				panelE.setVisible(false);
				panelP.setVisible(false);
				panelA.setVisible(false);
				panelT.setVisible(true);
				panelI.setVisible(false);
			}
		});
		panel.add(btnTelefon);
		
		JButton btnIban = new JButton("IBAN");
		btnIban.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnIban.setBounds(337, 162, 150, 25);
		btnIban.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUtilizator5.removeAllItems();
				try {
					PreparedStatement selStmt;
					if(activeUser.getTip().equals("administrator")) {
						selStmt = activeConnection.getConnection().prepareStatement("SELECT email FROM utilizatori WHERE tip = ?");
						selStmt.setString(1, "angajat");
					} else {
						selStmt = activeConnection.getConnection().prepareStatement("SELECT email FROM utilizatori WHERE tip != ?");
						selStmt.setString(1, "super-administrator");
					}
					ResultSet selSet = selStmt.executeQuery();
					while(true == selSet.next()) {
						txtUtilizator5.addItem(selSet.getString("email"));
					}
				} catch (SQLException sqlEx) {}
				txtIBANV.setText(txtIBANN.getText());
				panelE.setVisible(false);
				panelP.setVisible(false);
				panelA.setVisible(false);
				panelT.setVisible(false);
				panelI.setVisible(true);
			}
		});
		panel.add(btnIban);
		
		
		
		Panel panelModul = new Panel();
		panelModul.setBackground(new Color(199, 21, 133));
		panelModul.setBounds(0, 438, 610, 100);
		panel.add(panelModul);
		panelModul.setLayout(null);
		
		JLabel iconAdmin = new JLabel();
		iconAdmin.setBounds(10, 0, 100, 100);
		iconAdmin.setIcon(new ImageIcon((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagini/admin.png")))
				.getImage().getScaledInstance(iconAdmin.getWidth(), iconAdmin.getHeight(), Image.SCALE_SMOOTH))));
		panelModul.add(iconAdmin);
		
		JLabel lblAdmin = new JLabel("ADMIN   PANEL", SwingConstants.CENTER);
		lblAdmin.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAdmin.setBounds(120, 30, 470, 30);
		panelModul.add(lblAdmin);
		lblAdmin.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 22));
		
	}


	public JFrame getFrame() {
		return this.frame;
	}
}
