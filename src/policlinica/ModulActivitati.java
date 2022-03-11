package policlinica;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;

public class ModulActivitati {

	// Fereastra folosita de receptioneri, asistenti si medici
	
	private JFrame frame;
	private JPanel panel;
	private Conexiune activeConnection;
	private String pret;
	private String durata;
	private int ok = 0;
	
	public ModulActivitati(User activeUser) {
		
		activeConnection = new Conexiune();

		frame = new JFrame();
		frame.setBounds(100, 100, 600, 751);
		frame.setTitle("Gestionare Activitati Financiare");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		panel.setLayout(null);
		panel.setVisible(true);
		frame.setContentPane(panel);
		
		JLabel userIcon = new JLabel();
		userIcon.setBounds(60, 10, 100, 100);
		userIcon.setIcon(new ImageIcon((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagini/user.png")))
				.getImage().getScaledInstance(userIcon.getWidth(), userIcon.getHeight(), Image.SCALE_SMOOTH))));
		panel.add(userIcon);
		
		JPanel panelUser = new JPanel();
		panelUser.setLayout(null);
		panelUser.setBounds(0, 30, 600, 55);
		panelUser.setBackground(new Color(230, 30, 60));
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
		btnDisconnect.setBounds(440, 0, 155, 55);
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LogIn().getFrame().setVisible(true);
			}
		});
		panelUser.add(btnDisconnect);
		
		
		
		// Paneluri pt receptioner
		JPanel panelReceptionerMain = new JPanel();
		panelReceptionerMain.setBounds(0, 120, 600, 474);
		panelReceptionerMain.setBackground(new Color(240, 230, 140));
		panelReceptionerMain.setLayout(null);
		panel.add(panelReceptionerMain);
		
		JPanel pProg  = new JPanel();
		pProg.setBackground(new Color(240, 230, 140));
		pProg.setBounds(10, 55, 575, 432);
		pProg.setVisible(false);
		panelReceptionerMain.add(pProg);
		pProg.setLayout(null);
		
		
		

		JPanel panelReceptioner = new JPanel();
		panelReceptioner.setBackground(new Color(240, 230, 140));
		panelReceptioner.setBounds(10, 55, 575, 432);
		panelReceptioner.setLayout(null);
		panelReceptioner.setVisible(false);
		panelReceptionerMain.add(panelReceptioner);
		
		JLabel lblAccesR = new JLabel("Acces");
		lblAccesR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAccesR.setBounds(10, 0, 50, 45);
		panelReceptionerMain.add(lblAccesR);
		
		JButton btnProgramareR = new JButton("Creeaza Programare");
		btnProgramareR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelReceptioner.setVisible(true);
				pProg.setVisible(false);
			}
		});
		btnProgramareR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProgramareR.setBounds(70, 10, 225, 35);
		panelReceptionerMain.add(btnProgramareR);
		
		JLabel lblID = new JLabel("ID Programare");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblID.setBounds(100, 0, 160, 35);
		panelReceptioner.add(lblID);
		
		JTextField txtID = new JTextField();
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtID.setBounds(270, 5, 200, 25);
		panelReceptioner.add(txtID);
		
		JLabel lblNume = new JLabel("Nume pacient");
		lblNume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNume.setBounds(100, 65, 160, 35);
		panelReceptioner.add(lblNume);
		
		JTextField txtNume = new JTextField();
		txtNume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNume.setBounds(270, 70, 200, 25);
		panelReceptioner.add(txtNume);
		
		JLabel lblPrenume = new JLabel("Prenume pacient");
		lblPrenume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrenume.setBounds(100, 100, 160, 35);
		panelReceptioner.add(lblPrenume);
		
		JTextField txtPrenume = new JTextField();
		txtPrenume.setBounds(270, 105, 200, 25);
		txtPrenume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelReceptioner.add(txtPrenume);
		
		JLabel lblCNP = new JLabel("CNP pacient");
		lblCNP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCNP.setBounds(100, 30, 160, 35);
		panelReceptioner.add(lblCNP);
		
		JTextField txtCNP = new JTextField();
		txtCNP.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				try {
					PreparedStatement userCNP = activeConnection.getConnection().prepareStatement("SELECT nume_pacient, prenume_pacient FROM programari WHERE CNP_pacient = ?");
					userCNP.setString(1, txtCNP.getText());
					ResultSet userSet = userCNP.executeQuery();
					if(true == userSet.next()) {
						txtCNP.setEditable(false);
						txtNume.setText(userSet.getString("nume_pacient"));
						txtNume.setEditable(false);
						txtPrenume.setText(userSet.getString("prenume_pacient"));
						txtPrenume.setEditable(false);
					}
				} catch (SQLException sqlEx) {}
			}
		});
		txtCNP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCNP.setBounds(270, 35, 200, 25);
		panelReceptioner.add(txtCNP);
		
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblData.setBounds(100, 135, 160, 35);
		panelReceptioner.add(lblData);
		
		JDateChooser txtData = new JDateChooser();
		txtData.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtData.setBounds(270, 140, 200, 25);
		Date currentDate = null;
		try {
			Statement dataStmt = activeConnection.getConnection().createStatement();
			dataStmt.execute("SELECT current_date()");
			ResultSet dataSet = dataStmt.getResultSet();
			dataSet.next();
			currentDate = dataSet.getDate("current_date()");
		} catch (SQLException sqlEx) {}
		txtData.setMinSelectableDate(currentDate);
		panelReceptioner.add(txtData);
		
		JLabel lblOra = new JLabel("Ora");
		lblOra.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOra.setBounds(100, 170, 160, 35);
		panelReceptioner.add(lblOra);
		
		JComboBox<String> txtOra = new JComboBox<String>();
		txtOra.setBounds(270, 175, 200, 25);
		for(int i = 0; i < 24; i++) {
			if(10 > i) {
				txtOra.addItem("0" + i + ":00");
			} else {
				txtOra.addItem(i + ":00");
			}
		}
		txtOra.setSelectedIndex(8);
		txtOra.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelReceptioner.add(txtOra);
		
		JLabel lblCNPM = new JLabel("CNP medic");
		lblCNPM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCNPM.setBounds(100, 210, 160, 35);
		panelReceptioner.add(lblCNPM);
		
		JComboBox<String> txtCNPM = new JComboBox<String>();
		txtCNPM.setBounds(270, 215, 200, 25);
		txtCNPM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		try {
			Statement medStatement = activeConnection.getConnection().createStatement();
			medStatement.execute("SELECT cnp FROM medici");
			ResultSet mSet = medStatement.getResultSet();
			while(true == mSet.next()) {
				txtCNPM.addItem(mSet.getString("cnp"));
			}
		} catch (SQLException sqlEx) {}
		panelReceptioner.add(txtCNPM);
		
		JLabel lblNumeMR = new JLabel("<html>Nume medic<br>recomandat</html>");
		lblNumeMR.setVerticalAlignment(SwingConstants.TOP);
		lblNumeMR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumeMR.setBounds(100, 236, 160, 38);
		panelReceptioner.add(lblNumeMR);
		
		JTextField txtNumeMR = new JTextField();
		txtNumeMR.setBounds(270, 249, 200, 25);
		txtNumeMR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelReceptioner.add(txtNumeMR);
		
		JLabel lblPrenumeMR = new JLabel("<html>Prenume medic<br>recomandat</html>");
		lblPrenumeMR.setVerticalAlignment(SwingConstants.TOP);
		lblPrenumeMR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrenumeMR.setBounds(100, 276, 160, 40);
		panelReceptioner.add(lblPrenumeMR);
		
		JTextField txtPrenumeMR = new JTextField();
		txtPrenumeMR.setBounds(270, 283, 200, 25);
		txtPrenumeMR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelReceptioner.add(txtPrenumeMR);
		
		JLabel lblSpecialitate = new JLabel("Specialitate");
		lblSpecialitate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSpecialitate.setBounds(100, 315, 160, 35);
		panelReceptioner.add(lblSpecialitate);
		
		JComboBox<String> txtSpecialitate = new JComboBox<String>();
		txtSpecialitate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSpecialitate.setBounds(270, 315, 200, 25);
		try {
			Statement medStatement = activeConnection.getConnection().createStatement();
			medStatement.execute("SELECT DISTINCT specialitate FROM servicii");
			ResultSet mSet = medStatement.getResultSet();
			while(true == mSet.next()) {
				txtSpecialitate.addItem(mSet.getString("specialitate"));
			}
		} catch (SQLException sqlEx) {}
		panelReceptioner.add(txtSpecialitate);
		
		ArrayList<String> idInvestigatii = new ArrayList<String>();
		
		JLabel lblInvestigatie = new JLabel("Investigatie");
		lblInvestigatie.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInvestigatie.setBounds(100, 350, 160, 35);
		panelReceptioner.add(lblInvestigatie);
		
		JLabel lblCnt = new JLabel("(0)");
		lblCnt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCnt.setBounds(491, 350, 50, 35);
		panelReceptioner.add(lblCnt);
		
		JButton btnInvestigatie = new JButton("Investigatie noua");
		btnInvestigatie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> invOptiuni = new JComboBox<String>();
				invOptiuni.setFont(new Font("Tahoma", Font.PLAIN, 20));
				try {
					PreparedStatement invStatement = activeConnection.getConnection().prepareStatement("SELECT nume FROM servicii WHERE specialitate = ?");
					invStatement.setString(1, txtSpecialitate.getSelectedItem().toString());
					ResultSet rSet = invStatement.executeQuery();
					while(true == rSet.next()) {
						invOptiuni.addItem(rSet.getString("nume"));
					}
				} catch (SQLException sqlEx) {}
				JLabel invLabel = new JLabel("Alege un serviciu de mai jos");
				invLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				Object[] invDialog = {invLabel, invOptiuni};
				int userAns = JOptionPane.showConfirmDialog(frame, invDialog, "Adauga servicii", JOptionPane.OK_CANCEL_OPTION);
				String invID = null;
				try {
					PreparedStatement invSelect = activeConnection.getConnection().prepareStatement("SELECT id_serviciu FROM servicii WHERE nume = ?");
					invSelect.setString(1, invOptiuni.getSelectedItem().toString());
					ResultSet rSet = invSelect.executeQuery();
					rSet.next();
					invID = rSet.getString("id_serviciu");
				} catch (SQLException sqlEx) {}
				if(userAns == 0) {
					txtSpecialitate.setEnabled(false);
					if(true == idInvestigatii.contains(invID)) {
						JLabel errDuplicat = new JLabel("Serviciul este deja adaugat!");
						errDuplicat.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errDuplicat, "Eroare", JOptionPane.ERROR_MESSAGE);
					} else {
						idInvestigatii.add(invID);
						lblCnt.setText("(" + idInvestigatii.size() + ")");
						JLabel invSucces = new JLabel("Investigatie adaugata cu succes.");
						invSucces.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, invSucces, "Succes", JOptionPane.INFORMATION_MESSAGE);
						
						JLabel invPret = new JLabel("Doresti sa modifici pretul si durata?");
						invPret.setFont(new Font("Tahoma", Font.PLAIN, 20));
						userAns = JOptionPane.showConfirmDialog(frame, invPret, "Dialog", JOptionPane.OK_CANCEL_OPTION);
						if(userAns == 0) {
						JLabel invPret2 = new JLabel("Pret nou");
						invPret2.setFont(new Font("Tahoma", Font.PLAIN, 20));

						String userAns1 = JOptionPane.showInputDialog(frame, invPret2, "Dialog", JOptionPane.OK_CANCEL_OPTION);
						pret = userAns1;
						
						JLabel invDurata2 = new JLabel("Durata noua");
						invDurata2.setFont(new Font("Tahoma", Font.PLAIN, 20));
						
						String userAns2 = JOptionPane.showInputDialog(frame, invDurata2, "Dialog", JOptionPane.OK_CANCEL_OPTION);
						durata = userAns2;
						ok = 1;
						}
						
						
					}
				}
			}
		});
		btnInvestigatie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInvestigatie.setBounds(270, 350, 200, 25);
		panelReceptioner.add(btnInvestigatie);

		JButton btnAddProgramare = new JButton("Adauga Programare");
		btnAddProgramare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(true == txtID.getText().trim().isBlank() || true == txtCNP.getText().trim().isBlank() || true == txtNume.getText().trim().isBlank() ||
					true == txtPrenume.getText().trim().isBlank() || true == txtData.toString().trim().isBlank() || true == idInvestigatii.isEmpty() ||
					true == txtNumeMR.getText().trim().isBlank() || true == txtPrenumeMR.getText().trim().isBlank()) {
						JLabel errLabel = new JLabel("Toate campurile trebuie completate!");
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						PreparedStatement progStmt = activeConnection.getConnection().prepareStatement("SELECT addProgramare(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) AS Rezultat");
						progStmt.setString(1, txtID.getText());
						progStmt.setDate(2, new Date(txtData.getDate().getTime()));
						progStmt.setInt(3, txtOra.getSelectedIndex());
						progStmt.setString(4, activeUser.getCNP());
						progStmt.setString(5, txtCNPM.getSelectedItem().toString());
						progStmt.setString(6, txtCNP.getText());
						progStmt.setString(7, txtNume.getText());
						progStmt.setString(8, txtPrenume.getText());
						progStmt.setString(9, txtNumeMR.getText());
						progStmt.setString(10, txtPrenumeMR.getText());
						ResultSet pSet = progStmt.executeQuery();
						pSet.next();
						int canAdd = pSet.getInt("Rezultat");
						if(1 == canAdd) {
							String bonTxt = "<html>--- Bon fiscal ---<br><br>";
							float pretTotal = 0;

							int timpTotal = 0;
							for(int i = 0; i < idInvestigatii.size(); i++) {
								PreparedStatement invStmt = activeConnection.getConnection().prepareStatement("SELECT addInvestigatie(?, ?, ?) as Rezultat");
								invStmt.setString(1, activeUser.getCNP());
								invStmt.setString(2, txtID.getText());
								invStmt.setString(3, idInvestigatii.get(i));
								invStmt.execute();
								
								PreparedStatement bonStmt = activeConnection.getConnection().prepareStatement("SELECT * FROM servicii WHERE id_serviciu = ?");
								bonStmt.setString(1, idInvestigatii.get(i));
								ResultSet rSet = bonStmt.executeQuery();
								rSet.next();
								bonTxt += (rSet.getString("nume") + "<br>" + rSet.getFloat("pret") + " LEI<br>----------<br>");
								pretTotal += rSet.getFloat("pret");
								timpTotal += rSet.getInt("durata");
							}
							if(ok == 0) {
								pret = Float.toString(pretTotal);
								durata = Integer.toString(timpTotal);
							}
							bonTxt += ("<br>Pret total: " + pret + " LEI<br>");
							bonTxt += ("Durata totala: " + durata + " min<br>");
							bonTxt += ("Programare la: " + new Date(txtData.getDate().getTime()).toString() + "<br>Ora: " + txtOra.getSelectedIndex() + "</html>");
							JLabel progSucces = new JLabel("Programare adaugata cu succes.");
							progSucces.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, progSucces, "Succes", JOptionPane.INFORMATION_MESSAGE);
							JLabel bonFiscal = new JLabel(bonTxt);
							bonFiscal.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, bonFiscal);
							
							txtID.setText("");
							txtCNP.setText("");
							txtNume.setText("");
							txtPrenume.setText("");
							txtData.setCalendar(null);
							txtOra.setSelectedIndex(7);
							txtNumeMR.setText("");
							txtPrenumeMR.setText("");
							lblCnt.setText("0");
							txtCNP.setEditable(true);
							txtNume.setEditable(true);
							txtPrenume.setEditable(true);
							txtSpecialitate.setEnabled(true);
							idInvestigatii.removeAll(idInvestigatii);
							panelReceptioner.setVisible(false);
						} else if(2 == canAdd){
							JLabel errLabel = new JLabel("Pacientul are o programare la acea ora!");
							errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
						} else if(3 == canAdd){
							JLabel errLabel = new JLabel("Medicul lucreaza la acea ora!");
							errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
						} else if(4 == canAdd){
							JLabel errLabel = new JLabel("Medicul nu lucreaza atunci!");
							errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
						} else {
							JLabel errLabel = new JLabel("Nu esti in timpul programului!");
							errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException sqlEx) {
						JLabel errLabel = new JLabel(sqlEx.getMessage());
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAddProgramare.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddProgramare.setBounds(185, 380, 228, 35);
		panelReceptioner.add(btnAddProgramare);	
		
		JButton btnListaCabinete = new JButton("Lista cabinete");
		btnListaCabinete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pProg.setVisible(true);
				panelReceptioner.setVisible(false);
				Statement selectStmt;
				ResultSet rSet;
				
				try {
				 selectStmt = activeConnection.getConnection().createStatement();
				 selectStmt.execute("select cnp as CNP_medic,id_unitate,id_cabinet from program_angajati where id_cabinet is not null and data_program=current_date()");
				 rSet = selectStmt.getResultSet();
				 JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(10, 57, 555, 354);
					pProg.add(scrollPane);
					
					JTable tabbedPane = new JTable();
					scrollPane.setViewportView(tabbedPane);
				
					DefaultTableModel m = new DefaultTableModel();
					Object[] c = {"cnp", "unitate", "cabinet"};
					m.setColumnIdentifiers(c);
					m.setRowCount(0);
					tabbedPane.setModel(m);
					while(rSet.next()){
						
						Object rowData[] = {rSet.getString("CNP_medic"), rSet.getString("id_unitate"), rSet.getString("id_cabinet")};
						m.addRow(rowData);
						} 
					tabbedPane.setVisible(true);
				} catch (SQLException sq) {
					JLabel errLabel = new JLabel(sq.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
				
			

			}
		});
		btnListaCabinete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnListaCabinete.setBounds(316, 10, 225, 35);
		panelReceptionerMain.add(btnListaCabinete);
		

		
		
		
		
		// Paneluri pt asistent
		JPanel panelAsistentMain = new JPanel();
		panelAsistentMain.setBackground(new Color(240, 230, 140));
		panelAsistentMain.setBounds(0, 120, 600, 435);
		panelAsistentMain.setLayout(null);
		panel.add(panelAsistentMain);
		
		JPanel panelProgramariA = new JPanel();
		panelProgramariA.setBounds(10, 55, 580, 370);
		panelProgramariA.setBackground(new Color(240, 230, 140));
		panelProgramariA.setVisible(false);
		panelProgramariA.setLayout(null);
		panelAsistentMain.add(panelProgramariA);
		
		JPanel panelInvestigatiiA = new JPanel();
		panelInvestigatiiA.setLayout(null);
		panelInvestigatiiA.setBackground(new Color(240, 230, 140));
		panelInvestigatiiA.setBounds(10, 55, 580, 370);
		panelInvestigatiiA.setVisible(false);
		panelAsistentMain.add(panelInvestigatiiA);
		
		JLabel lblAccesA = new JLabel("Operatii: ");
		lblAccesA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAccesA.setBounds(10, 0, 125, 45);
		panelAsistentMain.add(lblAccesA);
		
		JLabel lblProgramariA = new JLabel("Programari din saptamana curenta");
		lblProgramariA.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramariA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProgramariA.setBounds(107, 0, 345, 45);
		panelProgramariA.add(lblProgramariA);
		
		JScrollPane scrollA = new JScrollPane();
		scrollA.setBounds(10, 50, 550, 250);
		scrollA.setVisible(false);
		panelProgramariA.add(scrollA);
		
		JTable tableA = new JTable();
		scrollA.setViewportView(tableA);
		
		JButton btnVerificaA = new JButton("Verifica programari");
		btnVerificaA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelProgramariA.setVisible(true);
				panelInvestigatiiA.setVisible(false);
				
				try {
					Statement progStmt = activeConnection.getConnection().createStatement();
					progStmt.execute("SELECT * FROM verificaProgramari");
					ResultSet rSet = progStmt.getResultSet();
					
					DefaultTableModel progModel = new DefaultTableModel();
					Object[] progColumns = {"id", "data", "ora", "CNP_medic", "CNP_pacient", "nume", "prenume", "durata"};
					progModel.setColumnIdentifiers(progColumns);
					progModel.setRowCount(0);
					tableA.setModel(progModel);
					if(false == rSet.next()) {
						JLabel noResult = new JLabel("Nu exista programari pentru saptamana curenta.");
						noResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, noResult, "Rezultate", JOptionPane.ERROR_MESSAGE);
						panelProgramariA.setVisible(false);
					} else {
						do {
							Object rowData[] = {rSet.getString("id_programare"), rSet.getDate("data_programare"), rSet.getInt("ora_programare"), rSet.getString("CNP_medic"), 
									rSet.getString("CNP_pacient"), rSet.getString("nume_pacient"), rSet.getString("prenume_pacient"), rSet.getInt("durata_consultatie")};
							progModel.addRow(rowData);
						} while(rSet.next());
						scrollA.setVisible(true);
					}
				} catch (SQLException sqlEx) {}
			}
		});
		btnVerificaA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVerificaA.setBounds(95, 5, 225, 35);
		panelAsistentMain.add(btnVerificaA);
		
		JLabel lblConcluziiA = new JLabel("Concluzii");
		lblConcluziiA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConcluziiA.setBounds(100, 80, 175, 25);
		panelInvestigatiiA.add(lblConcluziiA);

		JTextPane txtConcluziiA = new JTextPane();
		txtConcluziiA.setBounds(285, 80, 235, 135);
		txtConcluziiA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtConcluziiA.setEditable(false);
		panelInvestigatiiA.add(txtConcluziiA);
		
		JLabel lblIDIA = new JLabel("Serviciu");
		lblIDIA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIDIA.setBounds(100, 45, 175, 25);
		panelInvestigatiiA.add(lblIDIA);
		
		JComboBox<String> txtIDIA = new JComboBox<String>();
		txtIDIA.setBounds(285, 45, 235, 25);
		txtIDIA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelInvestigatiiA.add(txtIDIA);

		JLabel lblIDPA = new JLabel("ID Programare");
		lblIDPA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIDPA.setBounds(100, 10, 175, 25);
		panelInvestigatiiA.add(lblIDPA);
		
		JComboBox<String> txtIDPA = new JComboBox<String>();
		txtIDPA.setBounds(285, 10, 235, 25);
		txtIDPA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		try {
			Statement idStmt = activeConnection.getConnection().createStatement();
			idStmt.execute("SELECT id_programare FROM programari");
			ResultSet idSet = idStmt.getResultSet();
			while(true == idSet.next()) {
				txtIDPA.addItem(idSet.getString("id_programare"));
			}
		} catch (SQLException sqlEx) {}
		txtIDPA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIDIA.removeAllItems();
				if(0 != txtIDPA.getItemCount()) {
					try {
						PreparedStatement invStmt = activeConnection.getConnection().prepareStatement("SELECT nume FROM servicii WHERE servicii.id_serviciu IN"
								+ "(SELECT id_serviciu FROM investigatii WHERE id_programare = ?)");
						invStmt.setString(1, txtIDPA.getSelectedItem().toString());
						ResultSet invSet = invStmt.executeQuery();
						while(true == invSet.next()) {
							txtIDIA.addItem(invSet.getString("nume"));
							txtConcluziiA.setEditable(true);
						}
					} catch (SQLException sqlEx) {}
				}
			}
			
		});
		panelInvestigatiiA.add(txtIDPA);
		
		JButton btnCompletareA = new JButton("Completare investigatie");
		btnCompletareA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelProgramariA.setVisible(false);
				panelInvestigatiiA.setVisible(true);
			}
		});
		btnCompletareA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCompletareA.setBounds(330, 5, 255, 35);
		panelAsistentMain.add(btnCompletareA);

		JButton btnAddInvestigatieA = new JButton("Completeaza");
		btnAddInvestigatieA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddInvestigatieA.setBounds(140, 225, 275, 35);
		btnAddInvestigatieA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(true == txtConcluziiA.getText().trim().isBlank()) {
					JLabel errLabel = new JLabel("Toate campurile trebuie completate!");
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						String idServiciu = null;
						try {
							PreparedStatement invStmt = activeConnection.getConnection().prepareStatement("SELECT id_serviciu FROM servicii WHERE nume = ?");
							invStmt.setString(1, txtIDIA.getSelectedItem().toString());
							ResultSet rS = invStmt.executeQuery();
							rS.next();
							idServiciu = rS.getString("id_serviciu");
						} catch (SQLException sqlEx) {}
						PreparedStatement invStmt = activeConnection.getConnection().prepareStatement("SELECT completareInvestigatie(?, ?, ?, ?) AS Rezultat");
						invStmt.setString(1, activeUser.getCNP());
						invStmt.setString(2, txtIDPA.getSelectedItem().toString());
						invStmt.setString(3, idServiciu);
						invStmt.setString(4, txtConcluziiA.getText());
						ResultSet rSet = invStmt.executeQuery();
						rSet.next();
						int canAdd = rSet.getInt("Rezultat");
						if(1 == canAdd) {
							JLabel invSucces = new JLabel("Investigatie completata cu succes.");
							invSucces.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, invSucces, "Succes", JOptionPane.INFORMATION_MESSAGE);
						} else if (2 == canAdd) {
							JLabel invError = new JLabel("Deja a fost stabilita concluzia!");
							invError.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, invError, "Eroare", JOptionPane.ERROR_MESSAGE);
						} else if (3 == canAdd) {
							JLabel invError = new JLabel("Raportul a fost inchis de medic!");
							invError.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, invError, "Eroare", JOptionPane.ERROR_MESSAGE);
						} else if (4 == canAdd) {
							JLabel invError = new JLabel("Nu esti in timpul programului!");
							invError.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, invError, "Eroare", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException sqlEx) {
						JLabel errLabel = new JLabel(sqlEx.getMessage());
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				}
				txtConcluziiA.setText("");
				txtConcluziiA.setEditable(false);
				panelInvestigatiiA.setVisible(false);
			}
		});
		panelInvestigatiiA.add(btnAddInvestigatieA);
		
		
		
		
		// Paneluri pt medic
		JPanel panelMedicMain = new JPanel();
		panelMedicMain.setBackground(new Color(240, 230, 140));
		panelMedicMain.setBounds(0, 120, 600, 435);
		panelMedicMain.setLayout(null);
		panel.add(panelMedicMain);
		
		JPanel panelProgramariM = new JPanel();
		panelProgramariM.setBounds(10, 105, 575, 335);
		panelProgramariM.setBackground(new Color(240, 230, 140));
		panelProgramariM.setLayout(null);
		panelProgramariM.setVisible(false);
		panelMedicMain.add(panelProgramariM);
		
		JLabel lblProgramariM = new JLabel("Programarile din ziua curenta");
		lblProgramariM.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramariM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProgramariM.setBounds(10, 10, 545, 25);
		panelProgramariM.add(lblProgramariM);
		
		JScrollPane scrollProgramariM = new JScrollPane();
		scrollProgramariM.setBounds(10, 55, 545, 245);
		panelProgramariM.add(scrollProgramariM);
		
		JTable tabelProgramariM = new JTable();
		scrollProgramariM.setViewportView(tabelProgramariM);
		
		JPanel panelIstoricM = new JPanel();
		panelIstoricM.setBounds(10, 105, 575, 335);
		panelIstoricM.setBackground(new Color(240, 230, 140));
		panelIstoricM.setLayout(null);
		panelIstoricM.setVisible(false);
		panelMedicMain.add(panelIstoricM);
		
		JLabel lblIstoricM = new JLabel();
		lblIstoricM.setHorizontalAlignment(SwingConstants.CENTER);
		lblIstoricM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIstoricM.setBounds(10, 10, 545, 25);
		panelIstoricM.add(lblIstoricM);
		
		JScrollPane scrollIstoricM = new JScrollPane();
		scrollIstoricM.setBounds(10, 55, 545, 245);
		panelIstoricM.add(scrollIstoricM);
		
		JTable tabelIstoricM = new JTable();
		scrollIstoricM.setViewportView(tabelIstoricM);
		
		JPanel panelCheckM = new JPanel();
		panelCheckM.setBounds(10, 105, 575, 335);
		panelCheckM.setBackground(new Color(240, 230, 140));
		panelCheckM.setLayout(null);
		panelCheckM.setVisible(false);
		panelMedicMain.add(panelCheckM);
		
		JScrollPane scrollCheckM = new JScrollPane();
		scrollCheckM.setBounds(10, 150, 545, 200);
		panelCheckM.add(scrollCheckM);
		
		JTable tabelCheckM = new JTable();
		scrollCheckM.setViewportView(tabelCheckM);
		
		JLabel lblIDPMS = new JLabel();
		lblIDPMS.setHorizontalAlignment(SwingConstants.CENTER);
		lblIDPMS.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIDPMS.setBounds(10, 120, 545, 25);
		panelCheckM.add(lblIDPMS);
		
		JLabel lblIDPMT = new JLabel("Alege o programare");
		lblIDPMT.setHorizontalAlignment(SwingConstants.CENTER);
		lblIDPMT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIDPMT.setBounds(10, 15, 545, 25);
		panelCheckM.add(lblIDPMT);
		
		JLabel lblIDPM5 = new JLabel("ID Programare");
		lblIDPM5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIDPM5.setBounds(100, 55, 175, 25);
		panelCheckM.add(lblIDPM5);
		
		JComboBox<String> txtIDPM5 = new JComboBox<String>();
		txtIDPM5.setBounds(322, 55, 196, 25);
		txtIDPM5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		try {
			PreparedStatement progStmt = activeConnection.getConnection().prepareStatement("SELECT id_programare FROM programari WHERE cnp_medic = ?");
			progStmt.setString(1, activeUser.getCNP());
			ResultSet progSet = progStmt.executeQuery();
			while(true == progSet.next()) {
				txtIDPM5.addItem(progSet.getString("id_programare"));
			}
		} catch (SQLException sqlEx) {}
		panelCheckM.add(txtIDPM5);
		
		JButton btnCheckM = new JButton("Verifica");
		btnCheckM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CallableStatement istStmt = activeConnection.getConnection().prepareCall("call consultaInvestigatii(?)");
					istStmt.setString(1, txtIDPM5.getSelectedItem().toString());
					istStmt.execute();
					ResultSet rSet = istStmt.getResultSet();
					lblIDPMS.setText("Investigatiile programarii " + txtIDPM5.getSelectedItem().toString());
					DefaultTableModel checkModel = new DefaultTableModel();
					Object[] checkColumns = {"serviciu", "concluzie", "nume_asistent", "prenume_asistent"};
					checkModel.setColumnIdentifiers(checkColumns);
					checkModel.setRowCount(0);
					tabelCheckM.setModel(checkModel);
					while(true == rSet.next()) {
						Object rowData[] = {rSet.getString("nume"), rSet.getString("concluzie"), rSet.getString("nume_asistent"), rSet.getString("prenume_asistent")};
						checkModel.addRow(rowData);
					}
					scrollCheckM.setVisible(true);
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCheckM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCheckM.setBounds(161, 85, 274, 30);
		panelCheckM.add(btnCheckM);
		
		JPanel panelAddIM = new JPanel();
		panelAddIM.setBounds(10, 105, 575, 335);
		panelAddIM.setBackground(new Color(240, 230, 140));
		panelAddIM.setLayout(null);
		panelAddIM.setVisible(false);
		panelMedicMain.add(panelAddIM);
		
		JLabel lblAddIM = new JLabel("Adauga o investigatie");
		lblAddIM.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddIM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddIM.setBounds(10, 20, 545, 25);
		panelAddIM.add(lblAddIM);
		
		JLabel lblIDSM1 = new JLabel("Serviciu");
		lblIDSM1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIDSM1.setBounds(100, 100, 175, 25);
		panelAddIM.add(lblIDSM1);
		
		JComboBox<String> txtIDSM1 = new JComboBox<String>();
		txtIDSM1.setBounds(285, 100, 235, 25);
		txtIDSM1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelAddIM.add(txtIDSM1);
		
		JLabel lblIDPM1 = new JLabel("ID Programare");
		lblIDPM1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIDPM1.setBounds(100, 60, 175, 25);
		panelAddIM.add(lblIDPM1);
		
		JComboBox<String> txtIDPM1 = new JComboBox<String>();
		txtIDPM1.setBounds(285, 60, 235, 25);
		txtIDPM1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		try {
			PreparedStatement progStmt = activeConnection.getConnection().prepareStatement("SELECT id_programare FROM programari WHERE cnp_medic = ?");
			progStmt.setString(1, activeUser.getCNP());
			ResultSet progSet = progStmt.executeQuery();
			while(true == progSet.next()) {
				txtIDPM1.addItem(progSet.getString("id_programare"));
			}
		} catch (SQLException sqlEx) {}
		txtIDPM1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIDSM1.removeAllItems();
				try {
					PreparedStatement invStatement = activeConnection.getConnection().prepareStatement("SELECT nume FROM servicii WHERE specialitate IN"
							+ "(SELECT DISTINCT specialitate FROM servicii WHERE id_serviciu IN"
							+ "(SELECT investigatii.id_serviciu FROM investigatii WHERE investigatii.id_programare = ?))");
					invStatement.setString(1, txtIDPM1.getSelectedItem().toString());
					ResultSet rSet = invStatement.executeQuery();
					while(true == rSet.next()) {
						txtIDSM1.addItem(rSet.getString("nume"));
					}
				} catch (SQLException sqlEx) {}
			}
		});
		panelAddIM.add(txtIDPM1);
		
		JButton btnAddInvestigatieM = new JButton("Adauga");
		btnAddInvestigatieM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String idServiciu = null;
					try {
						PreparedStatement invStmt = activeConnection.getConnection().prepareStatement("SELECT id_serviciu FROM servicii WHERE nume = ? AND ");
						invStmt.setString(1, txtIDSM1.getSelectedItem().toString());
						ResultSet rS = invStmt.executeQuery();
						rS.next();
						idServiciu = rS.getString("id_serviciu");
					} catch (SQLException sqlEx) {}
					PreparedStatement invStmt = activeConnection.getConnection().prepareStatement("SELECT addInvestigatie(?, ?, ?) AS Rezultat");
					invStmt.setString(1, activeUser.getCNP());
					invStmt.setString(2, txtIDPM1.getSelectedItem().toString());
					invStmt.setString(3, idServiciu);
					ResultSet invSet = invStmt.executeQuery();
					invSet.next();
					int canAdd = invSet.getInt("Rezultat");
					if(1 == canAdd) {
						JLabel invSucces = new JLabel("Investigatie adaugata cu succes.");
						invSucces.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, invSucces, "Succes", JOptionPane.INFORMATION_MESSAGE);
					} else if (2 == canAdd){
						JLabel invError = new JLabel("Serviciu deja existent!");
						invError.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, invError, "Eroare", JOptionPane.ERROR_MESSAGE);
					} else if (3 == canAdd){
						JLabel invError = new JLabel("Raportul a fost parafat!");
						invError.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, invError, "Eroare", JOptionPane.ERROR_MESSAGE);
					} else {
						JLabel invError = new JLabel("Nu esti in timpul programului!");
						invError.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, invError, "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
				panelAddIM.setVisible(false);
			}
		});
		btnAddInvestigatieM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddInvestigatieM.setBounds(180, 140, 225, 35);
		panelAddIM.add(btnAddInvestigatieM);
		
		JPanel panelRemoveIM = new JPanel();
		panelRemoveIM.setBounds(10, 105, 575, 335);
		panelRemoveIM.setBackground(new Color(240, 230, 140));
		panelRemoveIM.setLayout(null);
		panelRemoveIM.setVisible(false);
		panelMedicMain.add(panelRemoveIM);
		
		JLabel lblRemoveIM = new JLabel("Sterge o investigatie");
		lblRemoveIM.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveIM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRemoveIM.setBounds(10, 20, 545, 25);
		panelRemoveIM.add(lblRemoveIM);
		
		JLabel lblIDSM2 = new JLabel("Serviciu");
		lblIDSM2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIDSM2.setBounds(100, 100, 175, 25);
		panelRemoveIM.add(lblIDSM2);
		
		JComboBox<String> txtIDSM2 = new JComboBox<String>();
		txtIDSM2.setBounds(285, 100, 235, 25);
		txtIDSM2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelRemoveIM.add(txtIDSM2);
		
		JLabel lblIDPM2 = new JLabel("ID Programare");
		lblIDPM2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIDPM2.setBounds(100, 60, 175, 25);
		panelRemoveIM.add(lblIDPM2);
		
		JComboBox<String> txtIDPM2 = new JComboBox<String>();
		txtIDPM2.setBounds(285, 60, 235, 25);
		txtIDPM2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		try {
			PreparedStatement progStmt = activeConnection.getConnection().prepareStatement("SELECT id_programare FROM programari WHERE cnp_medic = ?");
			progStmt.setString(1, activeUser.getCNP());
			ResultSet progSet = progStmt.executeQuery();
			while(true == progSet.next()) {
				txtIDPM2.addItem(progSet.getString("id_programare"));
			}
		} catch (SQLException sqlEx) {}
		txtIDPM2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIDSM2.removeAllItems();
				try {
					PreparedStatement invStatement = activeConnection.getConnection().prepareStatement("SELECT nume FROM servicii WHERE servicii.id_serviciu " 
							+ "IN (SELECT investigatii.id_serviciu FROM investigatii WHERE investigatii.id_programare = ?)");
					invStatement.setString(1, txtIDPM2.getSelectedItem().toString());
					ResultSet rSet = invStatement.executeQuery();
					while(true == rSet.next()) {
						txtIDSM2.addItem(rSet.getString("nume"));
					}
				} catch (SQLException sqlEx) {}
			}
		});
		panelRemoveIM.add(txtIDPM2);
		
		JButton btnRemoveInvestigatieM = new JButton("Sterge");
		btnRemoveInvestigatieM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String idServiciu = null;
					try {
						PreparedStatement invStmt = activeConnection.getConnection().prepareStatement("SELECT id_serviciu FROM servicii WHERE nume = ?");
						invStmt.setString(1, txtIDSM2.getSelectedItem().toString());
						ResultSet rS = invStmt.executeQuery();
						rS.next();
						idServiciu = rS.getString("id_serviciu");
					} catch (SQLException sqlEx) {}
					CallableStatement invStmt = activeConnection.getConnection().prepareCall("SELECT delInvestigatie(?, ?, ?) AS Rezultat");
					invStmt.setString(1, activeUser.getCNP());
					invStmt.setString(2, txtIDPM2.getSelectedItem().toString());
					invStmt.setString(3, idServiciu);
					ResultSet invSet = invStmt.executeQuery();
					invSet.next();
					int canDelete = invSet.getInt("Rezultat");
					if (1 == canDelete) {
						JLabel invSucces = new JLabel("Investigatie stearsa cu succes.");
						invSucces.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, invSucces, "Succes", JOptionPane.INFORMATION_MESSAGE);
					} else if(2 == canDelete) {
						JLabel invError = new JLabel("Investigatia nu exista!");
						invError.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, invError, "Succes", JOptionPane.ERROR_MESSAGE);
					} else if(3 == canDelete) {
						JLabel invError = new JLabel("Nu poti sterge singura investigatie a consultatiei!");
						invError.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, invError, "Succes", JOptionPane.ERROR_MESSAGE);
					} else if (4 == canDelete){
						JLabel invError = new JLabel("Raportul a fost parafat!");
						invError.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, invError, "Eroare", JOptionPane.ERROR_MESSAGE);
					} else {
						JLabel invError = new JLabel("Nu esti in timpul programului1");
						invError.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, invError, "Succes", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
				panelRemoveIM.setVisible(false);
			}
		});
		btnRemoveInvestigatieM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRemoveInvestigatieM.setBounds(180, 140, 225, 35);
		panelRemoveIM.add(btnRemoveInvestigatieM);
		
		JPanel panelCRaportM = new JPanel();
		panelCRaportM.setBounds(10, 105, 575, 335);
		panelCRaportM.setBackground(new Color(240, 230, 140));
		panelCRaportM.setLayout(null);
		panelCRaportM.setVisible(false);
		panelMedicMain.add(panelCRaportM);
		
		JLabel lblCRaportM = new JLabel("Completare raport medical");
		lblCRaportM.setHorizontalAlignment(SwingConstants.CENTER);
		lblCRaportM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCRaportM.setBounds(10, 20, 545, 25);
		panelCRaportM.add(lblCRaportM);
		
		JLabel lblSimptome = new JLabel("Simptome");
		lblSimptome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSimptome.setBounds(104, 95, 175, 25);
		panelCRaportM.add(lblSimptome);
		
		JTextPane txtSimptome = new JTextPane();
		txtSimptome.setBounds(322, 95, 196, 50);
		txtSimptome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSimptome.setEditable(false);
		panelCRaportM.add(txtSimptome);
		
		JLabel lblDiagnostic = new JLabel("Diagnostic");
		lblDiagnostic.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDiagnostic.setBounds(104, 155, 175, 25);
		panelCRaportM.add(lblDiagnostic);
		
		JTextPane txtDiagnostic = new JTextPane();
		txtDiagnostic.setBounds(322, 155, 196, 50);
		txtDiagnostic.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDiagnostic.setEditable(false);
		panelCRaportM.add(txtDiagnostic);
		
		JLabel lblRecomandari = new JLabel("Recomandari");
		lblRecomandari.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRecomandari.setBounds(104, 215, 175, 50);
		panelCRaportM.add(lblRecomandari);
		
		JTextPane txtRecomandari = new JTextPane();
		txtRecomandari.setBounds(322, 215, 196, 50);
		txtRecomandari.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtRecomandari.setEditable(false);
		panelCRaportM.add(txtRecomandari);
		
		JLabel lblIDPM3 = new JLabel("ID Programare");
		lblIDPM3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIDPM3.setBounds(104, 60, 175, 25);
		panelCRaportM.add(lblIDPM3);
		
		JComboBox<String> txtIDPM3 = new JComboBox<String>();
		txtIDPM3.setBounds(322, 60, 196, 25);
		txtIDPM3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		try {
			PreparedStatement progStmt = activeConnection.getConnection().prepareStatement("SELECT id_programare FROM programari WHERE cnp_medic = ?");
			progStmt.setString(1, activeUser.getCNP());
			ResultSet progSet = progStmt.executeQuery();
			while(true == progSet.next()) {
				txtIDPM3.addItem(progSet.getString("id_programare"));
			}
		} catch (SQLException sqlEx) {}
		txtIDPM3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSimptome.setEditable(true);
				txtDiagnostic.setEditable(true);
				txtRecomandari.setEditable(true);
			}
		});
		panelCRaportM.add(txtIDPM3);
		
		JButton btnComletareRM = new JButton("Completeaza");
		btnComletareRM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(true == txtSimptome.getText().trim().isBlank() || true == txtDiagnostic.getText().trim().isBlank() || true == txtRecomandari.getText().trim().isBlank()) {
					JLabel errLabel = new JLabel("Toate campurile trebuie completate!");
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				} else {
					String asistentNume = null;
					String asistentPrenume = null;
					try {
						PreparedStatement asistentStmt = activeConnection.getConnection().prepareStatement("SELECT nume_asistent, prenume_asistent FROM investigatii WHERE id_programare = ?");
						asistentStmt.setString(1, txtIDPM3.getSelectedItem().toString());
						ResultSet asistentSet = asistentStmt.executeQuery();
						asistentSet.next();
						asistentNume = asistentSet.getString("nume_asistent");
						asistentPrenume = asistentSet.getString("prenume_asistent");
					} catch (SQLException sqlEx) {}
					try {
						PreparedStatement invStmt = activeConnection.getConnection().prepareStatement("SELECT completeazaRaport(?, ?, ?, ?, ?, ?, ?) as Rezultat");
						invStmt.setString(1, txtIDPM3.getSelectedItem().toString());
						invStmt.setString(2, asistentNume);
						invStmt.setString(3, asistentPrenume);
						invStmt.setString(4, txtSimptome.getText());
						invStmt.setString(5, txtDiagnostic.getText());
						invStmt.setString(6, txtRecomandari.getText());	
						invStmt.setString(7, activeUser.getCNP());
						ResultSet invSet = invStmt.executeQuery();
						invSet.next();
						int canComplete = invSet.getInt("Rezultat");
						if (1 == canComplete) {
							JLabel rapSucces = new JLabel("Raport completat cu succes.");
							rapSucces.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, rapSucces, "Succes", JOptionPane.INFORMATION_MESSAGE);
						} else if(2 == canComplete) {
							JLabel rapError = new JLabel("Investigatiile nu au fost completate!");
							rapError.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, rapError, "Eroare", JOptionPane.ERROR_MESSAGE);
						} else if(3 == canComplete) {
							JLabel rapError = new JLabel("Raportul a fost deja completat!");
							rapError.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, rapError, "Eroare", JOptionPane.ERROR_MESSAGE);
						} else if(4 == canComplete) {
							JLabel rapError = new JLabel("Raportul a fost parafat si nu mai poate fi completat!");
							rapError.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, rapError, "Eroare", JOptionPane.ERROR_MESSAGE);
						} else {
							JLabel rapError = new JLabel("Nu esti in timpul programului!");
							rapError.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, rapError, "Eroare", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException sqlEx) {
						JLabel errLabel = new JLabel(sqlEx.getMessage());
						errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
					}
				}
				txtSimptome.setText("");
				txtSimptome.setEditable(false);
				txtDiagnostic.setText("");
				txtDiagnostic.setEditable(false);
				txtRecomandari.setText("");
				txtRecomandari.setEditable(false);
				panelCRaportM.setVisible(false);
			}
		});
		btnComletareRM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnComletareRM.setBounds(161, 290, 274, 35);
		panelCRaportM.add(btnComletareRM);
		
		JLabel lblAccesM = new JLabel("Ai acces la operatiile");
		lblAccesM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAccesM.setBounds(10, 0, 200, 25);
		panelMedicMain.add(lblAccesM);
		
		JButton btnProgramariM = new JButton("Verifica programari");
		btnProgramariM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelProgramariM.setVisible(true);
				panelIstoricM.setVisible(false);
				panelAddIM.setVisible(false);
				panelRemoveIM.setVisible(false);
				panelCRaportM.setVisible(false);
				
				scrollIstoricM.setVisible(false);
				try {
					CallableStatement progStmt = activeConnection.getConnection().prepareCall("call consultaProgramari(?)");
					progStmt.setString(1, activeUser.getCNP());
					progStmt.execute();
					ResultSet rSet = progStmt.getResultSet();
					
					DefaultTableModel progModel = new DefaultTableModel();
					Object[] progColumns = {"id", "data", "ora", "CNP_pacient", "nume_pacient", "prenume_pacient", "durata"};
					progModel.setColumnIdentifiers(progColumns);
					progModel.setRowCount(0);
					tabelProgramariM.setModel(progModel);
					if(false == rSet.isBeforeFirst()) {
						JLabel noResult = new JLabel("Nu exista intrari in baza de date.");
						noResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, noResult, "Rezultate", JOptionPane.ERROR_MESSAGE);
						panelProgramariM.setVisible(false);
					} else {
						while(rSet.next()) {
							Object rowData[] = {rSet.getString("id_programare"), rSet.getDate("data_programare"), rSet.getInt("ora_programare"), rSet.getString("CNP_pacient"), 
									rSet.getString("nume_pacient"), rSet.getString("prenume_pacient"), rSet.getInt("durata_consultatie")};
							progModel.addRow(rowData);
						}
						scrollProgramariM.setVisible(true);
					}
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnProgramariM.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnProgramariM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnProgramariM.setBounds(200, 0, 180, 25);
		panelMedicMain.add(btnProgramariM);
		
		JButton btnIstoricM = new JButton("Consulta istoric");
		btnIstoricM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelProgramariM.setVisible(false);
				panelIstoricM.setVisible(true);
				panelAddIM.setVisible(false);
				panelRemoveIM.setVisible(false);
				panelCRaportM.setVisible(false);

				String pacientCNP;
				JLabel inputCNP = new JLabel("Introdu CNP-ul pacientului caruia vrei sa-i verifici istoricul");
				inputCNP.setFont(new Font("Tahoma", Font.PLAIN, 20));
				JLabel errorCNP = new JLabel("Nume invalid!");
				errorCNP.setFont(new Font("Tahoma", Font.PLAIN, 20));
				do {
					pacientCNP = JOptionPane.showInputDialog(frame, inputCNP, "Enter Name",
							JOptionPane.INFORMATION_MESSAGE | JOptionPane.OK_OPTION);
					if (pacientCNP.trim().isBlank()) {
						JOptionPane.showMessageDialog(frame, errorCNP, "CNP invalid!", JOptionPane.ERROR_MESSAGE);
					}
				} while (true == pacientCNP.trim().isBlank());
				
				try {
					CallableStatement istStmt = activeConnection.getConnection().prepareCall("call consultaIstoric(?)");
					istStmt.setString(1, pacientCNP);
					istStmt.execute();
		
					ResultSet rSet = istStmt.getResultSet();
					lblIstoricM.setText("Istoricul pacientului cu CNP: " + pacientCNP);
					DefaultTableModel istModel = new DefaultTableModel();
					Object[] istColumns = {"id", "cnp_medic", "nume_asistent", "prenume_asistent", "simptome", "diagnostic", "recomandari", "istoric"};
					istModel.setColumnIdentifiers(istColumns);
					istModel.setRowCount(0);
					tabelIstoricM.setModel(istModel);
					if(false == rSet.isBeforeFirst()) {
						JLabel noResult = new JLabel("Nu exista intrari in baza de date.");
						noResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(frame, noResult, "Rezultate", JOptionPane.ERROR_MESSAGE);
						panelIstoricM.setVisible(false);
					} else {
						while(rSet.next()) {
							Object rowData[] = {rSet.getString("id_programare"), rSet.getString("cnp_medic"), rSet.getString("nume_asistent"), rSet.getString("prenume_asistent"),
								rSet.getString("simptome"), rSet.getString("diagnostic"), rSet.getString("recomandari"), rSet.getString("istoric")};
							istModel.addRow(rowData);
						}
						scrollIstoricM.setVisible(true);
					}
				} catch (SQLException sqlEx) {
					JLabel errLabel = new JLabel(sqlEx.getMessage());
					errLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, errLabel, "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnIstoricM.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnIstoricM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnIstoricM.setBounds(390, 0, 180, 25);
		panelMedicMain.add(btnIstoricM);
		
		JButton btnCheckIM = new JButton("Verifica investigatii");
		btnCheckIM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelProgramariM.setVisible(false);
				panelIstoricM.setVisible(false);
				panelCheckM.setVisible(true);
				panelAddIM.setVisible(false);
				panelRemoveIM.setVisible(false);
				panelCRaportM.setVisible(false);
				scrollCheckM.setVisible(false);
			}
		});
		btnCheckIM.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCheckIM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCheckIM.setBounds(200, 80, 180, 25);
		panelMedicMain.add(btnCheckIM);
		
		JButton btnAddIM = new JButton("Adauga investigatie");
		btnAddIM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelProgramariM.setVisible(false);
				panelIstoricM.setVisible(false);
				panelCheckM.setVisible(false);
				panelAddIM.setVisible(true);
				panelRemoveIM.setVisible(false);
				panelCRaportM.setVisible(false);
			}
		});
		btnAddIM.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAddIM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddIM.setBounds(200, 40, 180, 25);
		panelMedicMain.add(btnAddIM);
		
		JButton btnRemoveIM = new JButton("Sterge investigatie");
		btnRemoveIM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelProgramariM.setVisible(false);
				panelIstoricM.setVisible(false);
				panelCheckM.setVisible(false);
				panelAddIM.setVisible(false);
				panelRemoveIM.setVisible(true);
				panelCRaportM.setVisible(false);
			}
		});
		btnRemoveIM.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnRemoveIM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRemoveIM.setBounds(390, 40, 180, 25);
		panelMedicMain.add(btnRemoveIM);
		
		JButton btnCompletareM = new JButton("Completeaza raport");
		btnCompletareM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelProgramariM.setVisible(false);
				panelIstoricM.setVisible(false);
				panelCheckM.setVisible(false);
				panelAddIM.setVisible(false);
				panelRemoveIM.setVisible(false);
				panelCRaportM.setVisible(true);
			}
		});
		btnCompletareM.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCompletareM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCompletareM.setBounds(390, 80, 180, 25);
		panelMedicMain.add(btnCompletareM);
		
		Panel panelModul = new Panel();
		panelModul.setLayout(null);
		panelModul.setBackground(new Color(199, 21, 133));
		panelModul.setBounds(0, 613, 600, 100);
		panel.add(panelModul);
		
		JLabel iconActivitati = new JLabel();
		iconActivitati.setBounds(10, 0, 100, 100);
		iconActivitati.setIcon(new ImageIcon((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagini/operational.png")))
				.getImage().getScaledInstance(iconActivitati.getWidth(), iconActivitati.getHeight(), Image.SCALE_SMOOTH))));
		panelModul.add(iconActivitati);
		
		JLabel lblActivitati = new JLabel("GESTIONARE  ACTIVITATI  OPERATIONALE", SwingConstants.CENTER);
		lblActivitati.setVerticalAlignment(SwingConstants.BOTTOM);
		lblActivitati.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		lblActivitati.setBounds(120, 30, 470, 30);
		panelModul.add(lblActivitati);
		
		if(activeUser.getFunctie().equals("receptioner")) {
			panelReceptionerMain.setVisible(true);
			panelAsistentMain.setVisible(false);
			panelMedicMain.setVisible(false);	
		} else if(activeUser.getFunctie().equals("asistent medical")) {
			panelReceptionerMain.setVisible(false);
			panelAsistentMain.setVisible(true);
			panelMedicMain.setVisible(false);
		} else {
			panelReceptionerMain.setVisible(false);
			panelAsistentMain.setVisible(false);
			panelMedicMain.setVisible(true);
		}
		
	}

	public JFrame getFrame() {
		return this.frame;
	}
}
