package policlinica;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
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
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class ModulFinanciar {

	// Fereastra pentru modulul financiar
	
	private JFrame frame;
	private JPanel panel;
	private Conexiune activeConnection;
	
	public ModulFinanciar(User activeUser) {
		
		activeConnection = new Conexiune();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 700);
		frame.setTitle("Operatii Financiar-Contabile");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(210, 180, 140));
		panel.setLayout(null);
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
		
		JLabel lblLuna = new JLabel("Alege luna pentru a vedea salariul");
		lblLuna.setBounds(10, 115, 320, 35);
		lblLuna.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblLuna);
		
		JComboBox<String> txtLuna = new JComboBox<String>();
		txtLuna.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtLuna.addItem("Ianuarie");
		txtLuna.addItem("Februarie");
		txtLuna.addItem("Martie");
		txtLuna.addItem("Aprilie");
		txtLuna.addItem("Mai");
		txtLuna.addItem("Iunie");
		txtLuna.addItem("Iulie");
		txtLuna.addItem("August");
		txtLuna.addItem("Septembrie");
		txtLuna.addItem("Octombrie");
		txtLuna.addItem("Noiembrie");
		txtLuna.addItem("Decembrie");
		txtLuna.setBounds(340, 115, 150, 35);
		panel.add(txtLuna);

		JLabel lblSalariu = new JLabel();
		lblSalariu.setBounds(10, 145, 545, 35);
		lblSalariu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblSalariu);
		
		JButton btnV = new JButton("V");
		btnV.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CallableStatement salStmt = activeConnection.getConnection().prepareCall("SELECT verificaSalariu(?, ?, ?) AS Rezultat");
					salStmt.setString(1, activeUser.getCNP());
					salStmt.setString(2, activeUser.getCNP());
					salStmt.setInt(3, txtLuna.getSelectedIndex() + 1);
					salStmt.execute();
					ResultSet rSet = salStmt.getResultSet();
					rSet.next();
					int canCheck = rSet.getInt("Rezultat");
					String angLuna = txtLuna.getSelectedItem().toString();
					if(2 == canCheck) {
						lblSalariu.setText("Nu ti-ai indeplinit norma de ore pe luna " + angLuna + "!");
					} else {
						lblSalariu.setText("Salariul pe luna " + angLuna + ": " + canCheck + " LEI");
					}
					lblSalariu.setVisible(true);
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnV.setBounds(510, 115, 45, 30);
		panel.add(btnV);
		
		JPanel panelMedic = new JPanel();
		panelMedic.setBounds(10, 180, 576, 369);
		panelMedic.setBackground(new Color(210, 180, 140));
		panelMedic.setLayout(null);
		panel.add(panelMedic);
		
		JComboBox<String> txtLunaM = new JComboBox<String>();
		txtLunaM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtLunaM.addItem("Ianuarie");
		txtLunaM.addItem("Februarie");
		txtLunaM.addItem("Martie");
		txtLunaM.addItem("Aprilie");
		txtLunaM.addItem("Mai");
		txtLunaM.addItem("Iunie");
		txtLunaM.addItem("Iulie");
		txtLunaM.addItem("August");
		txtLunaM.addItem("Septembrie");
		txtLunaM.addItem("Octombrie");
		txtLunaM.addItem("Noiembrie");
		txtLunaM.addItem("Decembrie");
		txtLunaM.setBounds(330, 0, 150, 35);
		panelMedic.add(txtLunaM);
		
		JLabel lblLunaM = new JLabel("Salariul (programari) pe luna ");
		lblLunaM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLunaM.setBounds(0, 0, 320, 35);
		panelMedic.add(lblLunaM);
		
		JLabel lblProfitM = new JLabel();
		lblProfitM.setBounds(0, 30, 550, 35);
		lblProfitM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelMedic.add(lblProfitM);
		
		JButton btnVM = new JButton("V");
		btnVM.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnVM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CallableStatement venitStmt = activeConnection.getConnection().prepareCall("SELECT verificaProfitMedic(?, ?, ?) AS Rezultat");
					venitStmt.setString(1, activeUser.getCNP());
					venitStmt.setString(2, activeUser.getCNP());
					venitStmt.setInt(3, txtLunaM.getSelectedIndex() + 1);
					venitStmt.execute();
					ResultSet rSet = venitStmt.getResultSet();
					rSet.next();
					int canCheck = rSet.getInt("Rezultat");
					if(2 == canCheck) {	
						JLabel errLabel = new JLabel("Nu esti in timpul programului!");
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
					} else {
						lblProfitM.setText("Salariul (doar programari) pe luna " + txtLunaM.getSelectedItem().toString() + ": " + canCheck + " LEI");
					} 
					lblProfitM.setVisible(true);
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnVM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVM.setBounds(505, 0, 45, 35);
		panelMedic.add(btnVM);
		
		JPanel panelExpert = new JPanel();
		panelExpert.setBounds(10, 180, 576, 369);
		panelExpert.setBackground(new Color(210, 180, 140));
		panelExpert.setLayout(null);
		panel.add(panelExpert);
		
		JPanel panelPE = new JPanel();
		panelPE.setBounds(0, 105, 565, 255);
		panelPE.setBackground(new Color(210, 180, 140));
		panelPE.setLayout(null);
		panelPE.setVisible(false);
		panelExpert.add(panelPE);
		
		JLabel lblVenit1 = new JLabel();
		lblVenit1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVenit1.setBounds(10, 88, 545, 157);
		panelPE.add(lblVenit1);
		
		JLabel lblLuna1 = new JLabel("Luna");
		lblLuna1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLuna1.setBounds(112, 10, 160, 30);
		panelPE.add(lblLuna1);
		
		JComboBox<String> txtLuna1 = new JComboBox<String>();
		txtLuna1.setBounds(282, 10, 188, 30);
		txtLuna1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtLuna1.addItem("Ianuarie");
		txtLuna1.addItem("Februarie");
		txtLuna1.addItem("Martie");
		txtLuna1.addItem("Aprilie");
		txtLuna1.addItem("Mai");
		txtLuna1.addItem("Iunie");
		txtLuna1.addItem("Iulie");
		txtLuna1.addItem("August");
		txtLuna1.addItem("Septembrie");
		txtLuna1.addItem("Octombrie");
		txtLuna1.addItem("Noiembrie");
		txtLuna1.addItem("Decembrie");
		panelPE.add(txtLuna1);
		
		JButton btnVerifica1 = new JButton("Verifica");
		btnVerifica1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CallableStatement venitStmt = activeConnection.getConnection().prepareCall("SELECT verificaVenitPoliclinica(?, ?) AS Venit");
					venitStmt.setString(1, activeUser.getCNP());
					venitStmt.setInt(2, txtLuna1.getSelectedIndex() + 1);
					venitStmt.execute();
					ResultSet rSet1 = venitStmt.getResultSet();
					rSet1.next();
					int venit = rSet1.getInt("Venit");
					CallableStatement cheltuieliStmt = activeConnection.getConnection().prepareCall("SELECT verificaCheltuieliPoliclinica(?, ?) AS Cheltuieli");
					cheltuieliStmt.setString(1, activeUser.getCNP());
					cheltuieliStmt.setInt(2, txtLuna1.getSelectedIndex() + 1);
					cheltuieliStmt.execute();
					ResultSet rSet2 = cheltuieliStmt.getResultSet();
					rSet2.next();
					int cheltuieli = rSet2.getInt("Cheltuieli");
					if(2 == venit || 2 == cheltuieli) {
						JLabel errLabel = new JLabel("Nu esti in timpul programului!");
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
					} else {
						lblVenit1.setText("<html><p align = 'center'>Luna " + txtLuna1.getSelectedItem().toString() + "</p><br>"
								+ ">> Venit: " + venit + " LEI<br>"
								+ ">> Cheltuieli: " + cheltuieli + " LEI<br>"
								+ ">> Profit: " + (venit - cheltuieli) + " LEI</html>");
					}
					lblVenit1.setVisible(true);
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnVerifica1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVerifica1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnVerifica1.setBounds(190, 53, 188, 25);
		panelPE.add(btnVerifica1);
		
		
		JPanel panelUE = new JPanel();
		panelUE.setBounds(0, 105, 565, 255);
		panelUE.setBackground(new Color(210, 180, 140));
		panelUE.setLayout(null);
		panelUE.setVisible(false);
		panelExpert.add(panelUE);
		
		JLabel lblVenit2 = new JLabel();
		lblVenit2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVenit2.setBounds(10, 170, 545, 30);
		panelUE.add(lblVenit2);
		
		JLabel lblID2 = new JLabel("ID Unitate");
		lblID2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblID2.setBounds(112, 10, 160, 30);
		panelUE.add(lblID2);
		
		JComboBox<Integer> txtID2 = new JComboBox<Integer>();
		txtID2.setBounds(282, 10, 188, 30);
		txtID2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		try {
			Statement idStmt = activeConnection.getConnection().createStatement();
			idStmt.execute("SELECT id_unitate FROM unitati");
			ResultSet idSet = idStmt.getResultSet();
			while(true == idSet.next()) {
				txtID2.addItem(idSet.getInt("id_unitate"));
			}
		} catch (SQLException sqlEx) {}
		panelUE.add(txtID2);
		
		JLabel lblLuna2 = new JLabel("Luna");
		lblLuna2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLuna2.setBounds(112, 50, 160, 30);
		panelUE.add(lblLuna2);
		
		JComboBox<String> txtLuna2 = new JComboBox<String>();
		txtLuna2.setBounds(282, 50, 188, 30);
		txtLuna2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtLuna2.addItem("Ianuarie");
		txtLuna2.addItem("Februarie");
		txtLuna2.addItem("Martie");
		txtLuna2.addItem("Aprilie");
		txtLuna2.addItem("Mai");
		txtLuna2.addItem("Iunie");
		txtLuna2.addItem("Iulie");
		txtLuna2.addItem("August");
		txtLuna2.addItem("Septembrie");
		txtLuna2.addItem("Octombrie");
		txtLuna2.addItem("Noiembrie");
		txtLuna2.addItem("Decembrie");
		panelUE.add(txtLuna2);
		
		JButton btnVerifica2 = new JButton("Verifica");
		btnVerifica2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CallableStatement venitStmt = activeConnection.getConnection().prepareCall("SELECT verificaProfitUnitate(?, ?, ?) AS Rezultat");
					venitStmt.setString(1, activeUser.getCNP());
					venitStmt.setInt(2, Integer.parseInt(txtID2.getSelectedItem().toString()));
					venitStmt.setInt(3, txtLuna2.getSelectedIndex() + 1);
					venitStmt.execute();
					ResultSet rSet = venitStmt.getResultSet();
					rSet.next();
					int canCheck = rSet.getInt("Rezultat");
					if(2 == canCheck) {	
						JLabel errLabel = new JLabel("Nu esti in timpul programului!");
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
					} else {
						lblVenit2.setText("Profitul pe luna " + txtLuna2.getSelectedItem().toString() + ": " + canCheck + " LEI");
					} 
					lblVenit2.setVisible(true);
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnVerifica2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVerifica2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnVerifica2.setBounds(190, 85, 188, 25);
		panelUE.add(btnVerifica2);
		
		
		JPanel panelSE = new JPanel();
		panelSE.setBounds(0, 105, 565, 255);
		panelSE.setBackground(new Color(210, 180, 140));
		panelSE.setLayout(null);
		panelSE.setVisible(false);
		panelExpert.add(panelSE);
		
		JLabel lblVenit3 = new JLabel();
		lblVenit3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVenit3.setBounds(10, 170, 545, 30);
		panelSE.add(lblVenit3);
		
		JLabel lblID3 = new JLabel("Specialitate");
		lblID3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblID3.setBounds(112, 10, 160, 30);
		panelSE.add(lblID3);
		
		JComboBox<String> txtID3 = new JComboBox<String>();
		txtID3.setBounds(282, 10, 188, 30);
		txtID3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		try {
			Statement idStmt = activeConnection.getConnection().createStatement();
			idStmt.execute("SELECT DISTINCT specialitate FROM servicii");
			ResultSet idSet = idStmt.getResultSet();
			while(true == idSet.next()) {
				txtID3.addItem(idSet.getString("specialitate"));
			}
		} catch (SQLException sqlEx) {}
		panelSE.add(txtID3);
		
		JLabel lblLuna3 = new JLabel("Luna");
		lblLuna3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLuna3.setBounds(112, 50, 160, 30);
		panelSE.add(lblLuna3);
		
		JComboBox<String> txtLuna3 = new JComboBox<String>();
		txtLuna3.setBounds(282, 50, 188, 30);
		txtLuna3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtLuna3.addItem("Ianuarie");
		txtLuna3.addItem("Februarie");
		txtLuna3.addItem("Martie");
		txtLuna3.addItem("Aprilie");
		txtLuna3.addItem("Mai");
		txtLuna3.addItem("Iunie");
		txtLuna3.addItem("Iulie");
		txtLuna3.addItem("August");
		txtLuna3.addItem("Septembrie");
		txtLuna3.addItem("Octombrie");
		txtLuna3.addItem("Noiembrie");
		txtLuna3.addItem("Decembrie");
		panelSE.add(txtLuna3);
		
		JButton btnVerifica3 = new JButton("Verifica");
		btnVerifica3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CallableStatement venitStmt = activeConnection.getConnection().prepareCall("SELECT verificaProfitSpecialitate(?, ?, ?) AS Rezultat");
					venitStmt.setString(1, activeUser.getCNP());
					venitStmt.setString(2, txtID3.getSelectedItem().toString());
					venitStmt.setInt(3, txtLuna3.getSelectedIndex() + 1);
					venitStmt.execute();
					ResultSet rSet = venitStmt.getResultSet();
					rSet.next();
					int canCheck = rSet.getInt("Rezultat");
					if(2 == canCheck) {	
						JLabel errLabel = new JLabel("Nu esti in timpul programului!");
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
					} else {
						lblVenit3.setText("Profitul pe luna " + txtLuna3.getSelectedItem().toString() + ": " + canCheck + " LEI");
					} 
					lblVenit3.setVisible(true);
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnVerifica3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVerifica3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnVerifica3.setBounds(190, 85, 188, 25);
		panelSE.add(btnVerifica3);
		
		
		JPanel panelME = new JPanel();
		panelME.setBounds(0, 105, 565, 255);
		panelME.setBackground(new Color(210, 180, 140));
		panelME.setLayout(null);
		panelME.setVisible(false);
		panelExpert.add(panelME);
		
		JLabel lblVenit4 = new JLabel();
		lblVenit4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVenit4.setBounds(10, 170, 545, 30);
		panelME.add(lblVenit4);
		
		JLabel lblID4 = new JLabel("CNP Medic");
		lblID4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblID4.setBounds(112, 10, 160, 30);
		panelME.add(lblID4);
		
		JComboBox<String> txtID4 = new JComboBox<String>();
		txtID4.setBounds(282, 10, 188, 30);
		txtID4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		try {
			Statement idStmt = activeConnection.getConnection().createStatement();
			idStmt.execute("SELECT cnp FROM medici");
			ResultSet idSet = idStmt.getResultSet();
			while(true == idSet.next()) {
				txtID4.addItem(idSet.getString("cnp"));
			}
		} catch (SQLException sqlEx) {}
		panelME.add(txtID4);
		
		JLabel lblLuna4 = new JLabel("Luna");
		lblLuna4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLuna4.setBounds(112, 50, 160, 30);
		panelME.add(lblLuna4);
		
		JComboBox<String> txtLuna4 = new JComboBox<String>();
		txtLuna4.setBounds(282, 50, 188, 30);
		txtLuna4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtLuna4.addItem("Ianuarie");
		txtLuna4.addItem("Februarie");
		txtLuna4.addItem("Martie");
		txtLuna4.addItem("Aprilie");
		txtLuna4.addItem("Mai");
		txtLuna4.addItem("Iunie");
		txtLuna4.addItem("Iulie");
		txtLuna4.addItem("August");
		txtLuna4.addItem("Septembrie");
		txtLuna4.addItem("Octombrie");
		txtLuna4.addItem("Noiembrie");
		txtLuna4.addItem("Decembrie");
		panelME.add(txtLuna4);
		
		JButton btnVerifica4 = new JButton("Verifica");
		btnVerifica4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CallableStatement venitStmt = activeConnection.getConnection().prepareCall("SELECT verificaProfitMedic(?, ?, ?) AS Rezultat");
					venitStmt.setString(1, activeUser.getCNP());
					venitStmt.setString(2, txtID4.getSelectedItem().toString());
					venitStmt.setInt(3, txtLuna4.getSelectedIndex() + 1);
					venitStmt.execute();
					ResultSet rSet = venitStmt.getResultSet();
					rSet.next();
					int canCheck = rSet.getInt("Rezultat");
					if(2 == canCheck) {	
						JLabel errLabel = new JLabel("Nu esti in timpul programului!");
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
					} else {
						lblVenit4.setText("Profitul pe luna " + txtLuna4.getSelectedItem().toString() + ": " + canCheck + " LEI");
					} 
					lblVenit4.setVisible(true);
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnVerifica4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVerifica4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnVerifica4.setBounds(190, 85, 188, 25);
		panelME.add(btnVerifica4);
		
		
		JPanel panelAE = new JPanel();
		panelAE.setBounds(0, 105, 565, 255);
		panelAE.setBackground(new Color(210, 180, 140));
		panelAE.setLayout(null);
		panelAE.setVisible(false);
		panelExpert.add(panelAE);
		
		JLabel lblVenit5 = new JLabel();
		lblVenit5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVenit5.setBounds(10, 170, 545, 30);
		panelAE.add(lblVenit5);
		
		JLabel lblID5 = new JLabel("CNP Angajat");
		lblID5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblID5.setBounds(112, 10, 160, 30);
		panelAE.add(lblID5);
		
		JComboBox<String> txtID5 = new JComboBox<String>();
		txtID5.setBounds(282, 10, 188, 30);
		txtID5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		try {
			Statement idStmt = activeConnection.getConnection().createStatement();
			idStmt.execute("SELECT cnp FROM angajati");
			ResultSet idSet = idStmt.getResultSet();
			while(true == idSet.next()) {
				txtID5.addItem(idSet.getString("cnp"));
			}
		} catch (SQLException sqlEx) {}
		panelAE.add(txtID5);
		
		JLabel lblLuna5 = new JLabel("Luna");
		lblLuna5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLuna5.setBounds(112, 50, 160, 30);
		panelAE.add(lblLuna5);
		
		JComboBox<String> txtLuna5 = new JComboBox<String>();
		txtLuna5.setBounds(282, 50, 188, 30);
		txtLuna5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtLuna5.addItem("Ianuarie");
		txtLuna5.addItem("Februarie");
		txtLuna5.addItem("Martie");
		txtLuna5.addItem("Aprilie");
		txtLuna5.addItem("Mai");
		txtLuna5.addItem("Iunie");
		txtLuna5.addItem("Iulie");
		txtLuna5.addItem("August");
		txtLuna5.addItem("Septembrie");
		txtLuna5.addItem("Octombrie");
		txtLuna5.addItem("Noiembrie");
		txtLuna5.addItem("Decembrie");
		panelAE.add(txtLuna5);
		
		JButton btnVerifica5 = new JButton("Verifica");
		btnVerifica5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CallableStatement venitStmt = activeConnection.getConnection().prepareCall("SELECT verificaSalariu(?, ?, ?) AS Rezultat");
					venitStmt.setString(1, activeUser.getCNP());
					venitStmt.setString(2, txtID5.getSelectedItem().toString());
					venitStmt.setInt(3, txtLuna5.getSelectedIndex() + 1);
					venitStmt.execute();
					ResultSet rSet = venitStmt.getResultSet();
					rSet.next();
					int canCheck = rSet.getInt("Rezultat");
					if(2 == canCheck) {	
						JLabel errLabel = new JLabel("Angajatul nu si-a indeplinit norma de ore!");
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
					} else if(3 == canCheck) {	
						JLabel errLabel = new JLabel("Nu esti in timpul programului!");
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
					} else {
						lblVenit5.setText("Salariul pe luna " + txtLuna5.getSelectedItem().toString() + ": " + canCheck + " LEI");
					}
					lblVenit5.setVisible(true);
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnVerifica5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVerifica5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnVerifica5.setBounds(190, 85, 188, 25);
		panelAE.add(btnVerifica5);
		
		
		JLabel lblVerifica1 = new JLabel("Verifica profit ");
		lblVerifica1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVerifica1.setBounds(0, 6, 160, 37);
		panelExpert.add(lblVerifica1);
		
		JButton btnPoliclinica = new JButton("Policlinica");
		btnPoliclinica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPE.setVisible(true);
				panelUE.setVisible(false);
				panelSE.setVisible(false);
				panelME.setVisible(false);
				panelAE.setVisible(false);
				lblVenit1.setVisible(false);
			}
		});
		btnPoliclinica.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnPoliclinica.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPoliclinica.setBounds(180, 12, 188, 25);
		panelExpert.add(btnPoliclinica);
		
		JButton btnUnitate = new JButton("Unitate");
		btnUnitate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPE.setVisible(false);
				panelUE.setVisible(true);
				panelSE.setVisible(false);
				panelME.setVisible(false);
				panelAE.setVisible(false);
				lblVenit2.setVisible(false);
			}
		});
		btnUnitate.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnUnitate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUnitate.setBounds(378, 12, 188, 25);
		panelExpert.add(btnUnitate);
		
		JButton btnSpecialitate = new JButton("Specialitate");
		btnSpecialitate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPE.setVisible(false);
				panelUE.setVisible(false);
				panelSE.setVisible(true);
				panelME.setVisible(false);
				panelAE.setVisible(false);
				lblVenit3.setVisible(false);
			}
		});
		btnSpecialitate.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSpecialitate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSpecialitate.setBounds(180, 47, 188, 25);
		panelExpert.add(btnSpecialitate);
		
		JButton btnMedic = new JButton("Medic");
		btnMedic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPE.setVisible(false);
				panelUE.setVisible(false);
				panelSE.setVisible(false);
				panelME.setVisible(true);
				panelAE.setVisible(false);
				lblVenit4.setVisible(false);
			}
		});
		btnMedic.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnMedic.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMedic.setBounds(378, 47, 188, 25);
		panelExpert.add(btnMedic);
		
		JLabel lblVerifica2 = new JLabel("Verifica salariu");
		lblVerifica2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVerifica2.setBounds(0, 68, 160, 37);
		panelExpert.add(lblVerifica2);
		
		JButton btnAngajatE = new JButton("Angajat");
		btnAngajatE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelPE.setVisible(false);
				panelUE.setVisible(false);
				panelSE.setVisible(false);
				panelME.setVisible(false);
				panelAE.setVisible(true);
				lblVenit5.setVisible(false);
			}
		});
		btnAngajatE.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAngajatE.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAngajatE.setBounds(180, 80, 188, 25);
		panelExpert.add(btnAngajatE);
		
		
		
		Panel panelModul = new Panel();
		panelModul.setLayout(null);
		panelModul.setBackground(new Color(199, 21, 133));
		panelModul.setBounds(0, 562, 610, 100);
		panel.add(panelModul);
		
		JLabel iconOperatii = new JLabel();
		iconOperatii.setBounds(10, 0, 100, 100);
		iconOperatii.setIcon(new ImageIcon((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagini/financiar.png")))
				.getImage().getScaledInstance(iconOperatii.getWidth(), iconOperatii.getHeight(), Image.SCALE_SMOOTH))));
		panelModul.add(iconOperatii);
		
		JLabel lblOperatii = new JLabel("OPERATII  FINANCIAR  CONTABILE", SwingConstants.CENTER);
		lblOperatii.setVerticalAlignment(SwingConstants.BOTTOM);
		lblOperatii.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 22));
		lblOperatii.setBounds(120, 30, 470, 30);
		panelModul.add(lblOperatii);
		
		if(false == activeUser.getFunctie().equals("expert financiar-contabil")) {
			panelExpert.setVisible(false);
		}
		if(false == activeUser.getFunctie().equals("medic")) {
			panelMedic.setVisible(false);
		}

	}

	public JFrame getFrame() {
		return this.frame;
	}
}

