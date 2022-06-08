package member.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import member.controller.MemberController;

public class reallogin {

	JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	join Join;
	chat_name CH;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reallogin window = new reallogin();
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
	public reallogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("���� ���", Font.PLAIN, 14));
		frame.getContentPane().setForeground(new Color(255, 240, 245));
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 458, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("���� ���", Font.BOLD, 16));
		lblId.setForeground(new Color(255, 240, 245));
		lblId.setBounds(79, 96, 57, 15);
		frame.getContentPane().add(lblId);
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setFont(new Font("���� ���", Font.BOLD, 16));
		lblPw.setForeground(new Color(255, 240, 245));
		lblPw.setBounds(69, 145, 57, 15);
		frame.getContentPane().add(lblPw);
		
		textField = new JTextField();
		textField.setForeground(new Color(255, 240, 245));
		textField.setFont(new Font("���� ���", Font.BOLD, 14));
		textField.setBackground(new Color(0, 0, 0));
		textField.setBounds(118, 90, 152, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("���� ���", Font.BOLD, 12));
		btnNewButton.setEnabled(true);
		btnNewButton.setForeground(new Color(255, 240, 245));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("login");
				String ID = textField.getText();
				String pass = passwordField.getText();
				MemberController mc = new MemberController();
				int result = mc.userLogin(ID, pass);
				
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "로그인 성공!");
					CH = new chat_name("12345");
					CH.frame.setVisible(true);
				} else if (result == 0){
					JOptionPane.showMessageDialog(null, "틀린 비밀번호 입니다!");
				} else{
					JOptionPane.showMessageDialog(null, "존재하지 않는 ID 입니다!");
				}
			}
		});
		btnNewButton.setBounds(282, 96, 73, 67);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("JOIN");
		btnNewButton_1.setBackground(new Color(0, 0, 0));
		btnNewButton_1.setEnabled(true);
		btnNewButton_1.setFont(new Font("���� ���", Font.BOLD, 14));
		btnNewButton_1.setForeground(new Color(255, 240, 245));
		btnNewButton_1.setBounds(361, 96, 69, 67);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Join");
				Join = new join();
				Join.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(btnNewButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("���� ���", Font.BOLD, 14));
		passwordField.setForeground(new Color(255, 240, 245));
		passwordField.setBackground(new Color(0, 0, 0));
		passwordField.setBounds(118, 137, 152, 26);
		frame.getContentPane().add(passwordField);
	}
}
