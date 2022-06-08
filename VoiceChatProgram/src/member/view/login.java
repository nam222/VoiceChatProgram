package member.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class login {

	public JFrame frame;
	reallogin RL;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("START");
		btnNewButton.setFont(new Font("���� ���", Font.BOLD, 16));
		btnNewButton.setForeground(new Color(255, 240, 245));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("dd");
				RL = new reallogin();
				RL.frame.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(169, 146, 96, 43);
		frame.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("\u2661\uCEF4\uACF5\uACF5\uC8FC \uC74C\uC131\uCC44\uD305 \uD504\uB85C\uADF8\uB7A8\u2661");
		label.setBackground(new Color(255, 240, 245));
		label.setFont(new Font("���� ���", Font.BOLD, 20));
		label.setForeground(new Color(255, 240, 245));
		label.setBounds(74, 44, 294, 76);
		frame.getContentPane().add(label);
	}
}
