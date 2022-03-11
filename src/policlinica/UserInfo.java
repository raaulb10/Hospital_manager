package policlinica;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class UserInfo {
	
	// Fereastra ce contine date despre utilizator

	private JFrame frame;
	private JPanel panel;

	public UserInfo(User activeUser) {

		frame = new JFrame();
		frame.setBounds(100, 100, 600, 552);
		frame.setTitle("Selectare modul");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(150, 125, 210));
		panel.setLayout(null);
		frame.setContentPane(panel);
		
		JLabel userIcon = new JLabel();
		userIcon.setBounds(60, 10, 100, 100);
		userIcon.setIcon(new ImageIcon((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagini/user.png")))
				.getImage().getScaledInstance(userIcon.getWidth(), userIcon.getHeight(), Image.SCALE_SMOOTH))));
		panel.add(userIcon);
		
		JPanel panelUser = new JPanel();
		panelUser.setBackground(new Color(220, 20, 60));
		panelUser.setBounds(0, 30, 600, 55);
		panel.add(panelUser);
		panelUser.setLayout(null);
		
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
		btnDisconnect.setBounds(440, 0, 155, 55);
		panelUser.add(btnDisconnect);
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LogIn().getFrame().setVisible(true);
			}
		});
		btnDisconnect.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblInformatii = new JLabel("Informatii personale");
		lblInformatii.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformatii.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblInformatii.setBounds(150, 120, 354, 30);
		panel.add(lblInformatii);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(150, 180, 140, 33);
		panel.add(lblEmail);
		
		JTextField txtEmail = new JTextField(activeUser.getEmail());
		txtEmail.setEditable(false);
		txtEmail.setBounds(303, 180, 200, 30);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(txtEmail);
		
		JLabel lblParola = new JLabel("Parola");
		lblParola.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblParola.setBounds(150, 220, 140, 33);
		panel.add(lblParola);
		
		JTextField txtParola = new JTextField(activeUser.getParola());
		txtParola.setEditable(false);
		txtParola.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtParola.setBounds(303, 220, 200, 30);
		panel.add(txtParola);
		
		JLabel lblCNP = new JLabel("CNP");
		lblCNP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCNP.setBounds(150, 260, 140, 33);
		panel.add(lblCNP);
		
		JTextField txtCNP = new JTextField(activeUser.getCNP());
		txtCNP.setEditable(false);
		txtCNP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCNP.setBounds(303, 260, 200, 30);
		panel.add(txtCNP);
		
		JLabel lblNume = new JLabel("Nume");
		lblNume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNume.setBounds(150, 300, 140, 33);
		panel.add(lblNume);
		
		JTextField txtNume = new JTextField(activeUser.getNume());
		txtNume.setEditable(false);
		txtNume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNume.setBounds(303, 300, 200, 30);
		panel.add(txtNume);
		
		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrenume.setBounds(150, 340, 140, 33);
		panel.add(lblPrenume);
		
		JTextField txtPrenume = new JTextField(activeUser.getPrenume());
		txtPrenume.setEditable(false);
		txtPrenume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPrenume.setBounds(303, 340, 200, 30);
		panel.add(txtPrenume);
		
		JLabel lblOras = new JLabel("Adresa");
		lblOras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOras.setBounds(150, 380, 140, 33);
		panel.add(lblOras);
		
		JTextField txtOras = new JTextField(activeUser.getOras());
		txtOras.setEditable(false);
		txtOras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtOras.setBounds(303, 380, 200, 30);
		panel.add(txtOras);
		
		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefon.setBounds(150, 420, 140, 33);
		panel.add(lblTelefon);
		
		JTextField txtTelefon = new JTextField(activeUser.getTelefon());
		txtTelefon.setEditable(false);
		txtTelefon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTelefon.setBounds(303, 420, 200, 30);
		panel.add(txtTelefon);
		
		JLabel lblIBAN = new JLabel("IBAN");
		lblIBAN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIBAN.setBounds(150, 460, 140, 33);
		panel.add(lblIBAN);
		
		JTextField txtIBAN = new JTextField(activeUser.getIBAN());
		txtIBAN.setEditable(false);
		txtIBAN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtIBAN.setBounds(303, 460, 200, 30);
		panel.add(txtIBAN);

	}
	
	public JFrame getFrame() {
		return this.frame;
	}
}
