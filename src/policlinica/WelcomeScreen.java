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
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class WelcomeScreen {
	
	// Aceasta este prima fereastra, ca continea imaginea, titlul
	// Si cele doua butoane, login sau register

	private JFrame frame;
	private JPanel panel;

	public WelcomeScreen() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 725, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("InfoMed");
		frame.setResizable(false);
		
		panel = new JPanel();
		panel.setBackground(new Color(175, 238, 238));
		panel.setLayout(null);
		frame.setContentPane(panel);
		
		JLabel docIcon = new JLabel();
		docIcon.setBounds(0, 60, 360, 410);
		docIcon.setIcon(new ImageIcon((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagini/doctor.png")))
				.getImage().getScaledInstance(docIcon.getWidth(), docIcon.getHeight(), Image.SCALE_SMOOTH))));
		panel.add(docIcon);
		
		JLabel titlu = new JLabel("InfoMed", SwingConstants.CENTER);
		titlu.setForeground(Color.RED);
		titlu.setFont(new Font("Century", Font.BOLD | Font.ITALIC, 65));
		titlu.setBounds(350, 0, 340, 85);
		panel.add(titlu);
		
		JLabel subtitlu = new JLabel("<html><p align='center'>aplicatia viitorului<br>pentru medici</p></html>", SwingConstants.CENTER);
		subtitlu.setForeground(Color.GRAY);
		subtitlu.setFont(new Font("Tahoma", Font.PLAIN, 30));
		subtitlu.setBounds(350, 90, 340, 70);
		panel.add(subtitlu);
		
		JButton btnLogIn = new JButton("LOGIN");
		btnLogIn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new LogIn().getFrame().setVisible(true);
			}
		});
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnLogIn.setBounds(350, 250, 350, 75);
		panel.add(btnLogIn);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new Register().getFrame().setVisible(true);
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnRegister.setBounds(350, 350, 350, 75);
		panel.add(btnRegister);
		
		JLabel lblVersiune = new JLabel("ver 6.4_21.23 ");
		lblVersiune.setForeground(Color.GRAY);
		lblVersiune.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVersiune.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblVersiune.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblVersiune.setVerticalAlignment(SwingConstants.BOTTOM);
		lblVersiune.setBounds(515, 435, 185, 25);
		panel.add(lblVersiune);

	}
	
	public JFrame getFrame() {
		return this.frame;
	}
}
