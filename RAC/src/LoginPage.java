import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.swing.*;



public class LoginPage implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton loginButton = new JButton("Log In");
	JButton resetButton = new JButton("Reset");
	JButton signupButton = new JButton("Sign Up");
	JTextField nickNameField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel nickNameLabel = new JLabel("nickName:");
	JLabel userPasswordLabel = new JLabel("password:");
	JLabel messageLabel = new JLabel();
	
	LoginPage(){
		
		
		nickNameLabel.setBounds(50,100,75,25);
		userPasswordLabel.setBounds(50,150,75,25);
		
		messageLabel.setBounds(125,250,250,35);
		messageLabel.setFont(new Font(null,Font.ITALIC,25));
		
		nickNameField.setBounds(125,100,200,25);
		userPasswordField.setBounds(125,150,200,25);
		
		loginButton.setBounds(125,200,100,25);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		
		resetButton.setBounds(225,200,100,25);
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		
		signupButton.setBounds(125,235,200,25);
		signupButton.setFocusable(false);
		signupButton.addActionListener(this);
		ImageIcon logo=new ImageIcon("icons/rac.png");
		frame.setIconImage(logo.getImage());
		frame.add(nickNameLabel);
		frame.add(userPasswordLabel);
		frame.add(messageLabel);
		frame.add(nickNameField);
		frame.add(userPasswordField);
		frame.add(loginButton);
		frame.add(resetButton);
		frame.add(signupButton); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setLocation(500,100);
		frame.setTitle("RAC SYSTEM");
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==loginButton) {
			boolean userFount = false;
			String nickName = nickNameField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			
			File file = new File("database\\Customers.txt");
			try {
				BufferedReader br=new BufferedReader(new FileReader(file));
				String str=" ";
				while((str=br.readLine())!=null) {
					String[] parsedLine = str.split(";");
					if(nickName.equals(parsedLine[2])||nickName.equals(parsedLine[3])) {
						if(password.equals(parsedLine[4])) {
							User myUser = new User(parsedLine[0],parsedLine[1],parsedLine[2],parsedLine[3],parsedLine[4],parsedLine[5],false);
							frame.dispose();
							RACSystem sys = new RACSystem(myUser);
						}
							
					}
				}	
			} catch (FileNotFoundException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			file = new File("database\\Admins.txt");
			try {
				Scanner sc = new Scanner(file);
				while(sc.hasNextLine()) {
					String[] parsedLine = sc.nextLine().split(";");
					if(nickName.equals(parsedLine[2])||nickName.equals(parsedLine[3])) {
						if(password.equals(parsedLine[4])) {
							User myUser = new User(parsedLine[0],parsedLine[1],parsedLine[2],parsedLine[3],parsedLine[4],parsedLine[5],true);
							frame.dispose();
							RACSystem sys = new RACSystem(myUser);
						}
							
					}
				}
				
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			/*if(logininfo.containsKey(nickName)) {
				if(logininfo.get(nickName).equals(password)) {
					messageLabel.setForeground(Color.green);
					messageLabel.setText("Login successful");
					frame.dispose();
					WelcomePage welcomePage = new WelcomePage(nickName);
				}
				else {
					messageLabel.setForeground(Color.red);
					messageLabel.setText("Wrong password");
				}

			}
			else {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("username not found");
			}*/ catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		if(e.getSource()==resetButton) {
			nickNameField.setText("");
			userPasswordField.setText("");
		}
		
		if(e.getSource()==signupButton) {
			
			frame.dispose();
			SignUp signUp=new SignUp();
						
		}
		
		
	}	
}