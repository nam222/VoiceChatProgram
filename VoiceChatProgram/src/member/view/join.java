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
import member.model.vo.Member;

public class join {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					join window = new join();
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
	public join() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 341, 362);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("ID");
		label.setForeground(new Color(255, 240, 245));
		label.setFont(new Font("���� ���", Font.BOLD, 16));
		label.setBounds(32, 68, 57, 15);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("PW");
		label_1.setForeground(new Color(255, 240, 245));
		label_1.setFont(new Font("���� ���", Font.BOLD, 16));
		label_1.setBounds(21, 117, 48, 15);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setForeground(new Color(255, 240, 245));
		textField.setFont(new Font("���� ���", Font.BOLD, 14));
		textField.setColumns(10);
		textField.setBackground(Color.BLACK);
		textField.setBounds(67, 61, 152, 31);
		frame.getContentPane().add(textField);
		
		JButton btnNewButton = new JButton("\uC911\uBCF5\uD655\uC778");
		btnNewButton.setBackground(new Color(255, 240, 245));
		btnNewButton.setFont(new Font("���� ���", Font.BOLD, 12));
		btnNewButton.setBounds(231, 61, 82, 31);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = textField.getText();
				System.out.println(ID);
				MemberController mc = new MemberController();
				int result = mc.idCheck(ID);
				if(result == 1) {
					JOptionPane.showMessageDialog(null, "아이디 중복!");
					textField.setText(" ");
				}else {
					JOptionPane.showMessageDialog(null, "아이디 사용가능!");
					textField.disable();
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnNewButton_1.setBackground(new Color(255, 240, 245));
		btnNewButton_1.setFont(new Font("���� ���", Font.BOLD, 16));
		btnNewButton_1.setBounds(101, 226, 107, 59);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				String ID = textField.getText();
				String pass = passwordField.getText();
				String passCheck = passwordField_1.getText();
				if(pass.equals(passCheck)) {
					Member member = new Member();
					member.setName(ID);
					member.setPassword(pass);
					MemberController mc = new MemberController();
					result = mc.insertMember(member);
				}else {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다!");
				}
				
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
					frame.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "회원가입이 실패하였습니다.");
				}
			}
		});
		
		JLabel label_2 = new JLabel("\uC7AC\uC785\uB825");
		label_2.setForeground(new Color(255, 240, 245));
		label_2.setFont(new Font("���� ���", Font.BOLD, 16));
		label_2.setBounds(12, 165, 57, 15);
		frame.getContentPane().add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setText("  \u2661\uD68C\uC6D0\uAC00\uC785\u2661");
		textField_3.setFont(new Font("���� ���", Font.BOLD, 17));
		textField_3.setForeground(new Color(255, 240, 245));
		textField_3.setBackground(new Color(0, 0, 0));
		textField_3.setBounds(0, 0, 325, 31);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(255, 240, 245));
		passwordField.setFont(new Font("���� ���", Font.BOLD, 14));
		passwordField.setBackground(Color.BLACK);
		passwordField.setBounds(67, 116, 229, 31);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setForeground(new Color(255, 240, 245));
		passwordField_1.setFont(new Font("���� ���", Font.BOLD, 14));
		passwordField_1.setBackground(Color.BLACK);
		passwordField_1.setBounds(67, 164, 229, 31);
		frame.getContentPane().add(passwordField_1);
	}

}
