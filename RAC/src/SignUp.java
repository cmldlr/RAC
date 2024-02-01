import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUp implements ActionListener{
	
	//private static final Object NULL = null;
	JFrame signupFrame=new JFrame();
	JLabel nameL=new JLabel("Name *");
	JLabel surnameL=new JLabel("Surname *");
	JLabel emailL=new JLabel("Email *");
	JLabel nicknameL=new JLabel("Nickname *");
	JLabel phonenumberL=new JLabel("Phone number *");
	JLabel passwordL=new JLabel("Password *");
	JLabel passwordagainL=new JLabel("Password again *");
	JTextField nameTF = new JTextField();
	JTextField surnameTF = new JTextField();
	JTextField emailTF = new JTextField();
	JTextField nicknameTF=new JTextField();
	JTextField phonenumberTF = new JTextField();
	JPasswordField passwordPF = new JPasswordField();
	JPasswordField passwordagainPF = new JPasswordField();
	
	JButton signupB=new JButton("Sign Up");
	JButton backB=new JButton("Back");
	
	SignUp(){
		
		nameL.setBounds(50,75,75,25);
		surnameL.setBounds(50,100,75,25);
	    emailL.setBounds(50,125,75,25);
	    nicknameL.setBounds(50,150,75,25);
	    phonenumberL.setBounds(50,175,100,25);
	    passwordL.setBounds(50,200,75,25);
	    passwordagainL.setBounds(50,225,100,25);
	    
	    nameTF.setBounds(185,75,150,25);
	    surnameTF.setBounds(185,100,150,25);
	    emailTF.setBounds(185,125,150,25);
	    nicknameTF.setBounds(185,150,150,25);
	    phonenumberTF.setBounds(185,175,150,25);
	    passwordPF.setBounds(185,200,150,25);
	    passwordagainPF.setBounds(185,225,150,25);
	    
	    signupB.setBounds(255,250,80,25);
	    signupB.setFocusable(false);
		signupB.addActionListener(this);
	    backB.setBounds(185,250,70,25);
	    backB.setFocusable(false);
		backB.addActionListener(this);
	    
		signupFrame.add(nameL);
		signupFrame.add(surnameL);
		signupFrame.add(emailL);
		signupFrame.add(nicknameL);
		signupFrame.add(phonenumberL);
		signupFrame.add(passwordL);
		signupFrame.add(passwordagainL);
		
		signupFrame.add(nameTF);
		signupFrame.add(surnameTF);
		signupFrame.add(emailTF);
		signupFrame.add(nicknameTF);
		signupFrame.add(phonenumberTF);
		signupFrame.add(passwordPF);
		signupFrame.add(passwordagainPF);
		
		signupFrame.add(signupB);
		signupFrame.add(backB);
		
		signupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signupFrame.setSize(420,420);
		signupFrame.setLayout(null);
		signupFrame.setVisible(true);
		signupFrame.setLocation(500,100);
		//signupFrame.setTitle("Sign Up");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==signupB) {
			if(nameTF.getText().length()!=0 &&surnameTF.getText().length()!=0&&emailTF.getText().length()!=0&&
					phonenumberTF.getText().length()!=0 && passwordPF.getPassword()!=null && passwordagainPF.getPassword()!=null) {
				String password ="";
				String passwordagain="";
				for (int i = 0; i <passwordPF.getPassword().length; i++) {
					password+=String.valueOf(passwordPF.getPassword()[i]);
				}
				for (int i = 0; i <passwordagainPF.getPassword().length; i++) {
					passwordagain+=String.valueOf(passwordagainPF.getPassword()[i]);
				}
				if(password.equals(passwordagain)) {
					User newCustomer=new User(nameTF.getText(),surnameTF.getText(),nicknameTF.getText(),emailTF.getText(), password,phonenumberTF.getText(),false);
					try {
					      FileWriter fw = new FileWriter("database/customers.txt",true);
					      BufferedWriter bw = new BufferedWriter(fw);
					      bw.newLine();
					      bw.write(newCustomer.getName()+";"+newCustomer.getSurname()+";"+newCustomer.getNickName()+";"+newCustomer.getMail()+";"+
					      newCustomer.getPassword()+";"+newCustomer.getPhoneNumber());
					      bw.close();
					      JOptionPane.showMessageDialog(null, "Your nickname is " + newCustomer.getNickName() + "\nYou will be directed to the login page..."
									, "Successful Sign-up", JOptionPane.INFORMATION_MESSAGE);
					      signupFrame.dispose();
					      LoginPage loginpage=new LoginPage();
					    } catch (IOException e1) {
					      System.out.println("An error occurred.");
					      e1.printStackTrace();
				    }
					
					
				}
				else {
					/*JFrame messageFrame=new JFrame();
					JLabel messageL=new JLabel("Passwords do not match."); 
					
					messageL.setBounds(75,75,200,15);
					messageFrame.add(messageL);
					messageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					messageFrame.setSize(300,200);
					messageFrame.setLayout(null);
					messageFrame.setVisible(true);
					messageFrame.setLocation(700,300);*/
				}
				
			}
				
		}
		if(e.getSource()==backB) {
			signupFrame.dispose();
			LoginPage loginpage=new LoginPage();
		}
		
	}
	
}

