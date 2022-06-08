package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.StringTokenizer;

public class chatServerThread implements Runnable {

	Socket child;
	BufferedReader ois;
	PrintWriter oos;

	String user_id;
	HashMap<String, PrintWriter> hm;
	InetAddress ip;
	String msg;
	String members;

	public chatServerThread(Socket s, HashMap<String, PrintWriter> h) {
		child = s;
		hm = h;

		try {
			ois = new BufferedReader(new InputStreamReader(child.getInputStream()));
			oos = new PrintWriter(child.getOutputStream());
			user_id = ois.readLine();
			ip = child.getInetAddress();

			synchronized (hm) {
				textServer.idList.add(user_id);
				hm.put(user_id, oos);
				System.out.println(hm);
			}
			updateMembersList();
			broadcast(user_id, "님이 접속하셨습니다.", members);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateMembersList() {
		members = "";
		for (int i = 0; i < textServer.idList.size(); i++) {
			members += textServer.idList.get(i) + ", ";
			if (i == textServer.idList.size() - 1) {
				members += textServer.idList.get(i);
			}
		}
	}

	public void run() {
		String receiveData;

		try {
			while ((receiveData = ois.readLine()) != null) {
				if (hm.isEmpty())
					break;
				else {
					StringTokenizer stk = new StringTokenizer(receiveData, "$");
					String command = stk.nextToken();
					user_id = stk.nextToken();
					String message = stk.nextToken();
					if (command == "/q") {
						quitChat();
						break;
					} else if (command == "/c") {
						changeUserId(user_id);
					} else if (command == "/s") {
						System.out.println(user_id + " : " + message);
						broadcast(user_id, message, members);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			synchronized (hm) {
				hm.remove(user_id);
				textServer.idList.remove(user_id);
			}
			broadcast(user_id, "퇴장하셨습니다.",members);

			try {
				if (child != null) {
					ois.close();
					oos.close();
					child.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public void quitChat() {
		synchronized (hm) {
			String temp = user_id;
			hm.remove(user_id);
			textServer.idList.remove(user_id);
			updateMembersList();
			broadcast(temp, "퇴장하셨습니다.",members);
			
		}
	}
	public void changeUserId(String ID) {
		String name = user_id;
		int index = textServer.idList.indexOf(name);
		textServer.idList.set(index, ID);
		user_id = ID;
		updateMembersList();
		broadcast(user_id, "로 닉네임을 변경하였습니다. ("+name+")"  ,members);
	}


	public void broadcast(String id, String message, String membersList) {
		synchronized (hm) {
			try {
				for (PrintWriter oos : hm.values()) {
					oos.println(id + "$" + message + "$" + membersList);
					oos.flush(); // 문자열 연결된 socket으로 전송
				}
			} catch (Exception e) {
			}
		}
	}
}
