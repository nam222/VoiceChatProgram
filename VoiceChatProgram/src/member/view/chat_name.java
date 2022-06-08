package member.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class chat_name {

	JFrame frame;
	private JTextField textField;
	chatRoom CR;
	String PORT;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chat_name window = new chat_name("0");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param string 
	 */
	public chat_name() {
		
	}
	public chat_name(String port) {
		this.PORT = port;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 398, 244);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uB300\uD654\uBA85\uC744 \uC785\uB825\uD574\uC8FC\uC138\uC694");
		lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 17));
		lblNewLabel.setForeground(new Color(255, 240, 245));
		lblNewLabel.setBounds(101, 42, 199, 30);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("���� ���", Font.BOLD, 18));
		textField.setBackground(new Color(0, 0, 0));
		textField.setForeground(new Color(255, 240, 245));
		textField.setBounds(64, 90, 255, 39);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\uD655\uC778");
		button.setBackground(new Color(255, 240, 245));
		button.setFont(new Font("���� ���", Font.BOLD, 14));
		button.setBounds(143, 150, 97, 30);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PORT번호 인자로 주고 방 입장.
				int port = Integer.parseInt(PORT);
				CR = new chatRoom(port, textField.getText());
				CR.setVisible(true);
			}
		});
		frame.getContentPane().add(button);
	}

}
