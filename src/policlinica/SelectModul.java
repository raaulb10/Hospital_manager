package policlinica;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class SelectModul {
	
	// Meniul principal, de unde userul poate alege modulul dorit

	private JFrame frame;
	private JPanel panel;

	public SelectModul(User activeUser) {

		frame = new JFrame();
		frame.setBounds(100, 100, 600, 475);
		frame.setTitle("Selectare modul");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(175, 238, 238));
		panel.setLayout(null);
		frame.setContentPane(panel);
		
		JLabel userIcon = new JLabel();
		userIcon.setBounds(60, 10, 100, 100);
		userIcon.setIcon(new ImageIcon((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagini/user.png")))
				.getImage().getScaledInstance(userIcon.getWidth(), userIcon.getHeight(), Image.SCALE_SMOOTH))));
		userIcon.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mouseEntered(MouseEvent mEv) {
				frame.setCursor(Cursor.HAND_CURSOR);
			}
			@SuppressWarnings("deprecation")
			public void mouseExited(MouseEvent mEv) {
				frame.setCursor(Cursor.DEFAULT_CURSOR);
			}
			public void mouseClicked(MouseEvent mEv) {
				frame.dispose();
				new UserInfo(activeUser).getFrame().setVisible(true);
			}
		});
		panel.add(userIcon);
		
		JPanel panelUser = new JPanel();
		panelUser.setBackground(new Color(220, 20, 60));
		panelUser.setBounds(0, 30, 600, 55);
		panel.add(panelUser);
		panelUser.setLayout(null);
		
		JLabel userInfo_1 = new JLabel("Logged in as: " + activeUser.getEmail(), SwingConstants.LEFT);
		userInfo_1.setBounds(160, 0, 270, 25);
		userInfo_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelUser.add(userInfo_1);
		
		JLabel userInfo_2 = new JLabel(activeUser.getTip() + " (" + activeUser.getFunctie() + ")", SwingConstants.LEFT);
		userInfo_2.setFont(new Font("Tahoma", Font.ITALIC, 15));
		userInfo_2.setBounds(160, 30, 270, 25);
		panelUser.add(userInfo_2);
		
		JButton btnDisconnect = new JButton("DISCONNECT");
		btnDisconnect.setBounds(440, 0, 155, 55);
		panelUser.add(btnDisconnect);
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LogIn().getFrame().setVisible(true);
			}
		});
		btnDisconnect.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel selectIcon = new JLabel();
		selectIcon.setBounds(20, 195, 180, 180);
		selectIcon.setIcon(new ImageIcon((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagini/select.png")))
				.getImage().getScaledInstance(selectIcon.getWidth(), selectIcon.getHeight(), Image.SCALE_SMOOTH))));
		panel.add(selectIcon);
		
		JLabel lblSelect = new JLabel("Selecteaza unul din modulele de mai jos", SwingConstants.CENTER);
		lblSelect.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSelect.setBounds(10, 120, 575, 50);
		panel.add(lblSelect);
		
		JButton btnResurse = new JButton("Gestionare Resurse Umane");
		btnResurse.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnResurse.setBounds(220, 200, 350, 35);
		btnResurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ModulResurse(activeUser).getFrame().setVisible(true);
			}
		});
		btnResurse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnResurse);
		
		JButton btnFinanciar = new JButton("Operatii Financiar-Contabile");
		btnFinanciar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnFinanciar.setBounds(220, 260, 350, 35);
		btnFinanciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ModulFinanciar(activeUser).getFrame().setVisible(true);
			}
		});
		btnFinanciar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnFinanciar);
		
		JButton btnActivitati = new JButton("Gestionare Activitati Operationale");
		btnActivitati.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnActivitati.setBounds(220, 320, 350, 35);
		btnActivitati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(true == activeUser.getFunctie().equals("inspector resurse umane") || true == activeUser.getFunctie().equals("expert financiar-contabil")) {
					JLabel noAccess = new JLabel("Nu ai acces la acest modul.");
					noAccess.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, noAccess, "Acces respins", JOptionPane.ERROR_MESSAGE);
				} else {
					frame.dispose();
					new ModulActivitati(activeUser).getFrame().setVisible(true);
				}
			}
		});
		btnActivitati.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnActivitati);
		
		JButton btnAdmin = new JButton("Admin Panel");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new AdminPanel(activeUser).getFrame().setVisible(true);
			}
		});
		btnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdmin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAdmin.setBounds(220, 385, 350, 35);
		panel.add(btnAdmin);
		
		if(true == activeUser.getTip().equals("administrator") || true == activeUser.getTip().equals("super-administrator")) {
			btnAdmin.setVisible(true);
		} else {
			btnAdmin.setVisible(false);
		}
		
	}
	
	public JFrame getFrame() {
		return this.frame;
	}

}
