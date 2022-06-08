package member.view;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import voiceChat.sendVoiceThread;
import voiceChat.speakerThread;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class chatRoom extends JFrame {

   JFrame frame;
   static String ipAddress = "211.215.211.226";
   static int port;
   String id;
   public BufferedReader ois;
   public PrintWriter oos;

   private JPanel contentPane;
   private JTextField chatsInput;
   private JTable table;
   private JTextArea chatArea;
   private JTextField textField;
   private JScrollPane scrollPane;
   TextThread clientThread;
   VoiceThread voiceThread;
   private JTable table_1;
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               chatRoom frame = new chatRoom();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   public chatRoom(int port, String text) {
      this.port = port;
      this.id = text;
      start();
   }

   public chatRoom() {
      // TODO Auto-generated constructor stub
      startFrame();
   }

   public void start() {
      startFrame();
      clientThread = new TextThread(port, id);
      clientThread.start();
   }

   /**
    * Create the frame.
    */
   public void startFrame() {
      setResizable(false);
      setAlwaysOnTop(true);
      setBackground(Color.BLACK);
      setForeground(Color.BLACK);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 569, 578);
      contentPane = new JPanel();
      contentPane.setBackground(Color.BLACK);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JPanel panel = new JPanel();
      panel.setBorder(new LineBorder(Color.PINK));
      panel.setForeground(Color.PINK);
      panel.setBackground(Color.BLACK);
      panel.setBounds(0, 0, 564, 40);
      contentPane.add(panel);

      JLabel Title = new JLabel("♡컴공 공주 보이스 챗♡");
      Title.setFont(new Font("굴림", Font.PLAIN, 18));
      Title.setHorizontalAlignment(SwingConstants.LEFT);
      Title.setForeground(Color.PINK);
      panel.add(Title);

      chatArea = new JTextArea();
      scrollPane = new JScrollPane(chatArea);
      scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      scrollPane.setBounds(41, 58, 462, 240);
      chatArea.setBackground(Color.BLACK);
      chatArea.setForeground(Color.WHITE);
      contentPane.add(scrollPane);

      chatsInput = new JTextField();
      chatsInput.setForeground(Color.PINK);
      chatsInput.setBackground(Color.BLACK);
      chatsInput.setBounds(41, 297, 398, 32);
      contentPane.add(chatsInput);
      chatsInput.setColumns(10);

      JButton submitBtn = new JButton("전송");
      submitBtn.setBackground(Color.BLACK);
      submitBtn.setForeground(Color.PINK);
      submitBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            clientThread.sendMsg("/s",chatsInput.getText());
            chatsInput.setText("");
         }
      });
      submitBtn.setBounds(438, 297, 65, 32);
      contentPane.add(submitBtn);

      JLabel onlineLabel = new JLabel("ONLINE");
      onlineLabel.setFont(new Font("굴림", Font.BOLD, 16));
      onlineLabel.setHorizontalAlignment(SwingConstants.CENTER);
      onlineLabel.setBackground(Color.BLACK);
      onlineLabel.setForeground(Color.WHITE);
      onlineLabel.setBounds(86, 348, 111, 40);
      contentPane.add(onlineLabel);

      JPanel panel_1 = new JPanel();
      panel_1.setBounds(41, 387, 210, 137);
      contentPane.add(panel_1);
      panel_1.setLayout(null);
      
      table_1 = new JTable();
      table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      table_1.setSurrendersFocusOnKeystroke(true);
      table_1.setFont(new Font("굴림", Font.PLAIN, 17));
      table_1.setForeground(Color.WHITE);
      table_1.setModel(new DefaultTableModel(
      	new Object[][] {
      		{"dd"},
      		{"dd"},
      		{"dd"},
      		{"dd"},
      	},
      	new String[] {
      		"Title"
      	}
      ));
      table_1.setBounds(0, 0, 210, 137);
      panel_1.add(table_1);
      table_1.setBackground(Color.PINK);

      
      
      String[] headings= new String[] {"접속자명","접속 상태"};
      Object[][] data = new Object[][] {{"nam","on"}, {"jee","on"}, {"sung","off"}};
      
      JLabel profileLabel = new JLabel("PROFILE");
      profileLabel.setHorizontalAlignment(SwingConstants.CENTER);
      profileLabel.setForeground(Color.WHITE);
      profileLabel.setFont(new Font("굴림", Font.BOLD, 16));
      profileLabel.setBackground(Color.BLACK);
      profileLabel.setBounds(341, 348, 111, 40);
      contentPane.add(profileLabel);

      JPanel panelProfile = new JPanel();
      panelProfile.setBorder(new LineBorder(Color.WHITE, 3));
      panelProfile.setBackground(Color.BLACK);
      panelProfile.setBounds(285, 386, 226, 137);
      contentPane.add(panelProfile);
      panelProfile.setLayout(null);

      JPanel panel_2 = new JPanel(); 
      panel_2.setBackground(Color.BLACK);
      panel_2.setBounds(24, 31, 190, 23);
      panelProfile.add(panel_2);
      panel_2.setLayout(null);

      JLabel lblNewLabel = new JLabel("채팅명");
      lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
      lblNewLabel.setBounds(0, 3, 44, 15);
      panel_2.add(lblNewLabel);
      lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 13));
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setForeground(Color.WHITE);
      lblNewLabel.setBackground(Color.BLACK);

      textField = new JTextField();
      textField.setText(id);
      textField.setForeground(Color.WHITE);
      textField.setBackground(Color.BLACK);
      textField.setBounds(44, 1, 90, 21);
      panel_2.add(textField);
      textField.setColumns(10);

      JButton btnNewButton = new JButton("OK");
      btnNewButton.setForeground(Color.PINK);
      btnNewButton.setBackground(Color.BLACK);
      btnNewButton.setBounds(135, 0, 52, 23);
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            clientThread.sendMsg("/c",textField.getText());
            id = textField.getText();
            textField.setText("id");
            }
      });
      panel_2.add(btnNewButton);

      JButton btnNewButton_1 = new JButton("시작");
      btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 16));
      btnNewButton_1.setForeground(Color.PINK);
      btnNewButton_1.setBackground(Color.BLACK);
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            voiceThread = new VoiceThread(port+1);
            voiceThread.start();
         }
         
      });
      btnNewButton_1.setBounds(12, 87, 97, 23);
      panelProfile.add(btnNewButton_1);
      

      JButton btnNewButton_2 = new JButton("종료");
      btnNewButton_2.setFont(new Font("굴림", Font.PLAIN, 16));
      btnNewButton_2.setBackground(Color.BLACK);
      btnNewButton_2.setForeground(Color.PINK);
      btnNewButton_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            voiceThread.leave();
         }
      });
      btnNewButton_2.setBounds(121, 87, 97, 23);
      panelProfile.add(btnNewButton_2);
   }

   class TextThread extends Thread {

      int textPort = 12345;
      Socket client = null;
      String id;
      String receiveData;
      ArrayList<String> Online;
      
      public TextThread(int port, String id) {
         textPort = port;
         this.id = id;
      }
      
      public void run() {
         try {
            client = new Socket(ipAddress, textPort);
            
            chatArea.append(client.getLocalAddress().getHostAddress());
            Online = new ArrayList<String>();
            ois = new BufferedReader(new InputStreamReader(client.getInputStream()));
            oos = new PrintWriter(client.getOutputStream());
            oos.println(id);
            oos.flush();
            while ((receiveData = ois.readLine()) != null) {
            	StringTokenizer stk = new StringTokenizer(receiveData, "$");
				String user_id = stk.nextToken();
				String message = stk.nextToken();
				String userArray = stk.nextToken();
				chageToArray(userArray);
               chatArea.append("[" + user_id + "]" + message + "\n");
               scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
            }
         } catch (Exception e) {
            
         }
      }
      
      public void sendMsg(String option, String content) {
         String send = option + "$" + id + "$" + content;
         oos.println(send);
         oos.flush();
      }
      private void chageToArray(String userArray) {
  		// TODO Auto-generated method stub
  		Online.clear();
  		StringTokenizer stk = new StringTokenizer(userArray, ",");
  		while(stk.hasMoreTokens()) {
  			Online.add(stk.nextToken());
  		}
  		System.out.println(Online);
  	}
      
   }
   class VoiceThread extends Thread{
      public int port = 12345;

      private DataOutputStream _out;
      DataInputStream _streamIn;
      Socket _server;
      sendVoiceThread send;
      speakerThread speaker;
      
      public VoiceThread(int port){
         this.port = port;
      }
      public void leave() {
         // TODO Auto-generated method stub
         try {
            _server.close();
            send.close();
            speaker.close();
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      public void run() {
         try {
            _server = new Socket(ipAddress, 12346);
            chatArea.append("보이스톡에 연결 완료.");

//            연결된 소켓으로부터 dataInputSteam과 dataOutputStream 정보 얻어오기
            _out = new DataOutputStream(_server.getOutputStream());
            _streamIn = new DataInputStream(_server.getInputStream());
            
            send = new sendVoiceThread(_out);
            speaker = new speakerThread(_streamIn);
            
         } catch (Exception e) {
            chatArea.append("보이스 서버에 연결하지 못했습니다.");
         }
      }
   }
}