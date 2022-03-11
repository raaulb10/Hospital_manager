package policlinica;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;


public class ModulResurse {
	
	// Fereastra pentru modulul resurse umane

	private JFrame frame;
	private JPanel panel;
	private Conexiune activeConnection;
	
	public ModulResurse(User activeUser) {
		
		activeConnection = new Conexiune();

		frame = new JFrame();
		frame.setBounds(100, 100, 615, 635);
		frame.setTitle("Gestionare Resurse Umane");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(102, 255, 153));
		panel.setLayout(null);
		frame.setContentPane(panel);
		
		JLabel userIcon = new JLabel();
		userIcon.setBounds(60, 5, 100, 100);
		userIcon.setIcon(new ImageIcon((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagini/user.png")))
				.getImage().getScaledInstance(userIcon.getWidth(), userIcon.getHeight(), Image.SCALE_SMOOTH))));
		panel.add(userIcon);
		
		JPanel panelUser = new JPanel();
		panelUser.setLayout(null);
		panelUser.setBackground(new Color(220, 20, 60));
		panelUser.setBounds(0, 25, 610, 55);
		panel.add(panelUser);
		
		JLabel userInfo_1 = new JLabel("Logged in as: " + activeUser.getEmail(), SwingConstants.LEFT);
		userInfo_1.setBounds(160, 0, 270, 25);
		userInfo_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelUser.add(userInfo_1);
		
		JLabel userInfo_2 = new JLabel(activeUser.getTip() + " (" + activeUser.getFunctie() + ")", SwingConstants.LEFT);
		userInfo_2.setFont(new Font("Tahoma", Font.ITALIC, 15));
		userInfo_2.setBounds(160, 30, 270, 25);
		panelUser.add(userInfo_2);
		
		JButton btnBack = new JButton("<-");
		btnBack.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBack.setBounds(0, 0, 55, 55);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new SelectModul(activeUser).getFrame().setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelUser.add(btnBack);
		
		JButton btnDisconnect = new JButton("DISCONNECT");
		btnDisconnect.setBounds(450, 0, 155, 55);
		btnDisconnect.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDisconnect.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LogIn().getFrame().setVisible(true);
			}
		});
		panelUser.add(btnDisconnect);
		
		JLabel lblOrar = new JLabel("Orar luna curenta", SwingConstants.CENTER);
		lblOrar.setVerticalAlignment(SwingConstants.BOTTOM);
		lblOrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOrar.setBounds(10, 100, 580, 30);
		panel.add(lblOrar);
		
		JScrollPane orarScroll = new JScrollPane();
		orarScroll.setBounds(10, 155, 580, 100);
		panel.add(orarScroll);
		
		JTable orarData = new JTable();
		orarScroll.setViewportView(orarData);
		orarData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		try {
			PreparedStatement selectStmt = activeConnection.getConnection().prepareStatement("SELECT * FROM program_angajati WHERE CNP = ?");
			selectStmt.setString(1, activeUser.getCNP());
			ResultSet rSet = selectStmt.executeQuery();
			
			DefaultTableModel orarModel = new DefaultTableModel();
			Object[] orarColumns = {"data", "zi", "ora_inceput", "ora_sfarsit", "zi_lucratoare", "unitate"};
			orarModel.setColumnIdentifiers(orarColumns);
			orarModel.setRowCount(0);
			orarData.setModel(orarModel);
			if(false == rSet.next()) {
				JLabel lblErr = new JLabel("Orarul este gol. Ia-ti o vacanta :).", SwingConstants.CENTER);
				lblErr.setVerticalAlignment(SwingConstants.BOTTOM);
				lblErr.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblErr.setBounds(10, 160, 580, 30);
				panel.add(lblErr);
				orarScroll.setVisible(false);
			} else {
				do {
					Object rowData[] = {rSet.getDate("data_program"), rSet.getString("zi_calendaristica"), rSet.getInt("ora_inceput"), 
						rSet.getInt("ora_sfarsit"), rSet.getBoolean("zi_lucratoare"), rSet.getString("id_unitate")};
					orarModel.addRow(rowData);
				} while (true == rSet.next());
			}
		} catch (SQLException sqlEx) {}
		
		JScrollPane angScroll = new JScrollPane();
		angScroll.setBounds(10, 360, 580, 125);
		angScroll.setVisible(false);
		panel.add(angScroll);
		
		JTable angData = new JTable();
		angScroll.setColumnHeaderView(angData);
		angData.setDragEnabled(true);
		angData.setCellSelectionEnabled(true);
		angData.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		angData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		angData.setFillsViewportHeight(true);
		angScroll.setViewportView(angData);

		JLabel lblFind = new JLabel("Cauta angajat dupa: ", SwingConstants.LEFT);
		lblFind.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFind.setBounds(10, 265, 210, 40);
		panel.add(lblFind);
				
		JButton btnNume = new JButton("Nume");
		btnNume.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNume.setBounds(215, 270, 115, 30);
		btnNume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String angNume;
				JLabel inputName = new JLabel("Introdu numele angajatului pe care vrei sa-l cauti:");
				inputName.setFont(new Font("Tahoma", Font.PLAIN, 20));
				JLabel errorName = new JLabel("Nume invalid!");
				errorName.setFont(new Font("Tahoma", Font.PLAIN, 20));
				do {
					angNume = JOptionPane.showInputDialog(frame, inputName, "Enter Name",
							JOptionPane.INFORMATION_MESSAGE | JOptionPane.OK_OPTION);
					if (angNume.trim().isBlank()) {
						JOptionPane.showMessageDialog(frame, errorName, "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				} while (true == angNume.trim().isBlank());
				try {
					PreparedStatement selectStmt = activeConnection.getConnection().prepareStatement("SELECT * FROM angajati WHERE nume = ?");
					selectStmt.setString(1, angNume);
					ResultSet rSet = selectStmt.executeQuery();
					
					DefaultTableModel angModel = new DefaultTableModel();
					Object[] angColumns = {"CNP", "nume", "prenume", "functie"};
					angModel.setColumnIdentifiers(angColumns);
					angModel.setRowCount(0);
					angData.setModel(angModel);
					
					if(false == rSet.isBeforeFirst()) {
						JLabel noResult = new JLabel("Nu exista intrari in baza de date.");
						noResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, noResult, "Rezultate", JOptionPane.ERROR_MESSAGE);
						angScroll.setVisible(false);
					} else {
						angScroll.setVisible(true);
					}
					while(rSet.next()) {
						Object rowData[] = {rSet.getString("CNP"), rSet.getString("nume"), rSet.getString("prenume"), rSet.getString("functie")};
						angModel.addRow(rowData);
					}
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnNume);
				
		JButton btnPrenume = new JButton("Prenume");
		btnPrenume.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnPrenume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPrenume.setBounds(345, 270, 115, 30);
		btnPrenume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String angPrenume;
				JLabel inputName = new JLabel("Introdu prenumele angajatului pe care vrei sa-l cauti:");
				inputName.setFont(new Font("Tahoma", Font.PLAIN, 20));
				JLabel errorName = new JLabel("Nume invalid!");
				errorName.setFont(new Font("Tahoma", Font.PLAIN, 20));
				do {
					angPrenume = JOptionPane.showInputDialog(frame, inputName, "Enter Name",
							JOptionPane.INFORMATION_MESSAGE | JOptionPane.OK_OPTION);
					if (angPrenume.trim().isBlank()) {
						JOptionPane.showMessageDialog(frame, errorName, "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				} while (true == angPrenume.trim().isBlank());
				try {
					PreparedStatement selectStmt = activeConnection.getConnection().prepareStatement("SELECT * FROM angajati WHERE prenume = ?");
					selectStmt.setString(1, angPrenume);
					ResultSet rSet = selectStmt.executeQuery();
					
					DefaultTableModel angModel = new DefaultTableModel();
					Object[] angColumns = {"CNP", "nume", "prenume", "functie"};
					angModel.setColumnIdentifiers(angColumns);
					angModel.setRowCount(0);
					angData.setModel(angModel);
					
					if(false == rSet.isBeforeFirst()) {
						JLabel noResult = new JLabel("Nu exista intrari in baza de date.");
						noResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, noResult, "Rezultate", JOptionPane.ERROR_MESSAGE);
						angScroll.setVisible(false);
					} else {
						angScroll.setVisible(true);
					}
					while(rSet.next()) {
						Object rowData[] = {rSet.getString("CNP"), rSet.getString("nume"), rSet.getString("prenume"), rSet.getString("functie")};
						angModel.addRow(rowData);
					}
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnPrenume);
		
		JButton btnFunctie = new JButton("Functie");
		btnFunctie.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnFunctie.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFunctie.setBounds(475, 270, 115, 30);
		btnFunctie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String angFunctie;
				JLabel inputName = new JLabel("Introdu functia angajatului pe care vrei sa-l cauti:");
				inputName.setFont(new Font("Tahoma", Font.PLAIN, 20));
				JLabel errorName = new JLabel("Nume invalid!");
				errorName.setFont(new Font("Tahoma", Font.PLAIN, 20));
				do {
					angFunctie = JOptionPane.showInputDialog(frame, inputName, "Enter Name",
							JOptionPane.INFORMATION_MESSAGE | JOptionPane.OK_OPTION);
					if (angFunctie.trim().isBlank()) {
						JOptionPane.showMessageDialog(frame, errorName, "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				} while (true == angFunctie.trim().isBlank());
				try {
					PreparedStatement selectStmt = activeConnection.getConnection().prepareStatement("SELECT * FROM angajati WHERE functie = ?");
					selectStmt.setString(1, angFunctie);
					ResultSet rSet = selectStmt.executeQuery();
					
					DefaultTableModel angModel = new DefaultTableModel();
					Object[] angColumns = {"CNP", "nume", "prenume", "functie"};
					angModel.setColumnIdentifiers(angColumns);
					angModel.setRowCount(0);
					angData.setModel(angModel);
					if(false == rSet.isBeforeFirst()) {
						JLabel noResult = new JLabel("Nu exista intrari in baza de date.");
						noResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, noResult, "Rezultate", JOptionPane.ERROR_MESSAGE);
						angScroll.setVisible(false);
					} else {
						angScroll.setVisible(true);
					}
					while(rSet.next()) {
						Object rowData[] = {rSet.getString("CNP"), rSet.getString("nume"), rSet.getString("prenume"), rSet.getString("functie")};
						angModel.addRow(rowData);
					}
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnFunctie);

		JLabel lblFunctie = new JLabel("Functii: ", SwingConstants.LEFT);
		lblFunctie.setVerticalAlignment(SwingConstants.TOP);
		lblFunctie.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFunctie.setBounds(10, 310, 100, 40);
		panel.add(lblFunctie);
		
		JButton btnOrarS = new JButton("Orar saptamanal");
		btnOrarS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement orarStmt = activeConnection.getConnection().createStatement();
					orarStmt.execute("SELECT * FROM verificaOrar ORDER BY data_program");
					ResultSet rSet = orarStmt.getResultSet();
					
					DefaultTableModel angModel = new DefaultTableModel();
					Object[] angColumns = {"CNP", "data", "zi", "ora_inceput", "ora_sfarsit", "zi_lucratoare", "unitate"};
					angModel.setColumnIdentifiers(angColumns);
					angModel.setRowCount(0);
					angData.setModel(angModel);
					if(false == rSet.isBeforeFirst()) {
						JLabel noResult = new JLabel("Nu exista intrari in baza de date.");
						noResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, noResult, "Rezultate", JOptionPane.ERROR_MESSAGE);
						angScroll.setVisible(false);
					} else {
						while(rSet.next()) {
							Object rowData[] = {rSet.getString("CNP"), rSet.getDate("data_program"), rSet.getString("zi_calendaristica"), rSet.getInt("ora_inceput"), 
								rSet.getInt("ora_sfarsit"), rSet.getBoolean("zi_lucratoare"), rSet.getString("id_unitate")};
							angModel.addRow(rowData);
						}
						angScroll.setVisible(true);
					}
					if (rSet != null) { 
						try { rSet.close(); } 
						catch(SQLException ex) {}  
						rSet = null;  
					}
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnOrarS.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOrarS.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnOrarS.setBounds(110, 310, 175, 30);
		panel.add(btnOrarS);
		
		JButton btnOrarA = new JButton("Orar angajat");
		btnOrarA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOrarA.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnOrarA.setBounds(300, 310, 175, 30);
		btnOrarA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				angScroll.setVisible(false);
				
				DefaultTableModel angModel = new DefaultTableModel();
				Object[] angColumns = {"cnp", "data", "zi", "ora_inceput", "ora_sfarsit", "zi_lucratoare", "unitate"};
				angModel.setColumnIdentifiers(angColumns);
				angData.setModel(angModel);
				
				String angCNP = null;
				JLabel inputCNP = new JLabel("Introdu CNP angajatului caruia vrei sa-i verifici orarul lunii ");
				inputCNP.setFont(new Font("Tahoma", Font.PLAIN, 20));
				JLabel angError = new JLabel("CNP invalid!");
				angError.setFont(new Font("Tahoma", Font.PLAIN, 20));
				do {
					angCNP = JOptionPane.showInputDialog(frame, inputCNP, "Enter Name",
							JOptionPane.INFORMATION_MESSAGE | JOptionPane.OK_OPTION);
					if (angCNP.trim().isBlank()) {
						JOptionPane.showMessageDialog(frame, angError, "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				} while (true == angCNP.trim().isBlank());
				try {
					PreparedStatement orarStmt = activeConnection.getConnection().prepareStatement(
							"SELECT * FROM program_angajati WHERE CNP = ? AND MONTH(data_program) = MONTH(CURRENT_DATE())");
					orarStmt.setString(1, angCNP);
					ResultSet rSet = orarStmt.executeQuery();
					if(false == rSet.isBeforeFirst()) {
						JLabel noResult = new JLabel("Nu exista intrari in baza de date.");
						noResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, noResult, "Rezultate", JOptionPane.ERROR_MESSAGE);
						angScroll.setVisible(false);
					} else {
						while(rSet.next()) {
							Object rowData[] = {rSet.getString("CNP"), rSet.getDate("data_program"), rSet.getString("zi_calendaristica"), rSet.getInt("ora_inceput"), 
								rSet.getInt("ora_sfarsit"), rSet.getBoolean("zi_lucratoare"), rSet.getString("id_unitate")};
							angModel.addRow(rowData);
						}
						angScroll.setVisible(true);
					}
					if (rSet != null) { 
						try { rSet.close(); } 
						catch(SQLException ex) {}  
						rSet = null;  
					}
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		panel.add(btnOrarA);
		
		JButton btnConcediu = new JButton("Concediu");
		btnConcediu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				angScroll.setVisible(false);
				
				DefaultTableModel angModel = new DefaultTableModel();
				Object[] angColumns = {"data", "zi", "ora_inceput", "ora_sfarsit", "zi_lucratoare", "unitate"};
				angModel.setColumnIdentifiers(angColumns);
				angData.setModel(angModel);
				
				String angCNP = null;
				JLabel inputCNP = new JLabel("Introdu CNP angajatului caruia vrei sa-i oferi concediu: ");
				inputCNP.setFont(new Font("Tahoma", Font.PLAIN, 20));
				JLabel angError = new JLabel("CNP invalid!");
				angError.setFont(new Font("Tahoma", Font.PLAIN, 20));
				do {
					angCNP = JOptionPane.showInputDialog(frame, inputCNP, "Enter Name",
							JOptionPane.INFORMATION_MESSAGE | JOptionPane.OK_OPTION);
					if (angCNP.trim().isBlank()) {
						JOptionPane.showMessageDialog(frame, angError, "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				} while (true == angCNP.trim().isBlank());
				try {
					
					PreparedStatement selectStmt = activeConnection.getConnection().prepareStatement("SELECT * FROM program_angajati WHERE CNP = ?");
					selectStmt.setString(1, angCNP);
					ResultSet rSet = selectStmt.executeQuery();
					angModel.setRowCount(0);
					if(false == rSet.isBeforeFirst()) {
						JLabel noResult = new JLabel("Nu exista intrari in baza de date.");
						noResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, noResult, "Rezultate", JOptionPane.ERROR_MESSAGE);
						angScroll.setVisible(false);
					} else {
						
						while(rSet.next()) {
							Object rowData[] = {rSet.getDate("data_program"), rSet.getString("zi_calendaristica"), rSet.getInt("ora_inceput"), 
								rSet.getInt("ora_sfarsit"), rSet.getBoolean("zi_lucratoare"), rSet.getString("id_unitate")};
							angModel.addRow(rowData);
						}
						angScroll.setVisible(true);
						Date currentDate = null;
						try {
							Statement dataStmt = activeConnection.getConnection().createStatement();
							dataStmt.execute("SELECT current_date()");
							ResultSet dataSet = dataStmt.getResultSet();
							dataSet.next();
							currentDate = dataSet.getDate("current_date()");
						} catch (SQLException sqlEx) {}
						String angMsgFirst = "Selecteaza inceputul concediului: ";
						JDateChooser angDayFirst = new JDateChooser();
						angDayFirst.setMinSelectableDate(currentDate);
						String angMsgLast = "Selecteaza finalul concediului: ";
						JDateChooser angDayLast = new JDateChooser();
						angDayLast.setMinSelectableDate(currentDate);
						Object[] angDialog = { angMsgFirst, angDayFirst, angMsgLast, angDayLast};
						JOptionPane.showConfirmDialog(frame, angDialog);
						rSet = selectStmt.executeQuery();
						if( angDayFirst.getDate().getTime() > angDayLast.getDate().getTime()) {
							JLabel noResult = new JLabel("Perioada invalida.");
							noResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, noResult, "Rezultate", JOptionPane.ERROR_MESSAGE);
							angScroll.setVisible(false);
						} else {
							CallableStatement callStmt = activeConnection.getConnection().prepareCall("SELECT setConcediu(?, ?, ?, ?) AS Rezultat");
							callStmt.setString(1, activeUser.getCNP());
							callStmt.setString(2, angCNP);
							callStmt.setDate(3, new Date(angDayFirst.getDate().getTime()));
							callStmt.setDate(4, new Date(angDayLast.getDate().getTime()));
							ResultSet callSet = callStmt.executeQuery();
							callSet.next();
							int canSet = callSet.getInt("Rezultat");
							if(1 == canSet) {
								JLabel ziSucces = new JLabel("Concediu setat cu succes!");
								ziSucces.setFont(new Font("Tahoma", Font.PLAIN, 20));
								JOptionPane.showMessageDialog(frame, ziSucces, "Zi concediu", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JLabel ziError = new JLabel("Nu esti in timpul programului!");
								ziError.setFont(new Font("Tahoma", Font.PLAIN, 20));
								JOptionPane.showMessageDialog(frame, ziError, "Zi concediu", JOptionPane.ERROR_MESSAGE);
							}
						}
						rSet = selectStmt.executeQuery();
						angModel.setRowCount(0);
						while(rSet.next()) {
							Object rowData[] = {rSet.getDate("data_program"), rSet.getString("zi_calendaristica"), rSet.getInt("ora_inceput"), 
								rSet.getInt("ora_sfarsit"), rSet.getBoolean("zi_lucratoare"), rSet.getString("id_unitate")};
							angModel.addRow(rowData);
						}
						angScroll.setVisible(true);
					}
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnConcediu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConcediu.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnConcediu.setBounds(490, 310, 100, 30);
		panel.add(btnConcediu);
		
		Panel panelModul = new Panel();
		panelModul.setBackground(new Color(199, 21, 133));
		panelModul.setBounds(0, 500, 610, 100);
		panel.add(panelModul);
		panelModul.setLayout(null);
		
		JLabel iconResurse = new JLabel();
		iconResurse.setBounds(10, 0, 100, 100);
		iconResurse.setIcon(new ImageIcon((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagini/resurse.png")))
				.getImage().getScaledInstance(iconResurse.getWidth(), iconResurse.getHeight(), Image.SCALE_SMOOTH))));
		panelModul.add(iconResurse);
		
		JLabel lblResurse = new JLabel("GESTIONARE  RESURSE  UMANE", SwingConstants.CENTER);
		lblResurse.setVerticalAlignment(SwingConstants.BOTTOM);
		lblResurse.setBounds(120, 30, 470, 30);
		panelModul.add(lblResurse);
		lblResurse.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 22));
		
		if(true == activeUser.getFunctie().equals("inspector resurse umane") || 
				true == activeUser.getFunctie().equals("expert financiar-contabil")) {
			lblFind.setVisible(true);
			btnNume.setVisible(true);
			btnPrenume.setVisible(true);
			btnFunctie.setVisible(true);
			lblFunctie.setVisible(true);
			btnOrarS.setVisible(true);
			btnOrarA.setVisible(true);
			angData.setVisible(true);
			if(true == activeUser.getFunctie().equals("expert financiar-contabil")) {
				btnConcediu.setVisible(false);
			}
		} else {
			lblFind.setVisible(false);
			btnNume.setVisible(false);
			btnPrenume.setVisible(false);
			btnFunctie.setVisible(false);
			lblFunctie.setVisible(false);
			btnOrarS.setVisible(false);
			btnOrarA.setVisible(false);
			btnConcediu.setVisible(false);
			angData.setVisible(false);
		}
	}

	public JFrame getFrame() {
		return this.frame;
	}
}
