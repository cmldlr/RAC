import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PaymentWindow extends JFrame implements ActionListener{
	int price;
	JButton submitButton;
	JButton backButton;
	boolean status;
	JTextField nameField;
	JTextField cardNumberField;
	JComboBox monthBox;
	JComboBox yearBox;
	JTextField cvvField;
	JLabel priceLabel;
	PaymentWindow(int price){
		this.price = price;
		status = false;
		
		priceLabel = new JLabel("Price: " + price);
		priceLabel.setBounds(45,10,100,15);
		priceLabel.setFont(new Font("Consolas",Font.PLAIN,15));
		
		nameField = new JTextField();
		nameField.setBounds(45,35,300,45);
		nameField.setFont(new Font("Consolas",Font.PLAIN,20));
		nameField.setText("Name");
		
		cardNumberField = new JTextField();
		cardNumberField.setBounds(45,90,300,45);
		cardNumberField.setFont(new Font("Consolas",Font.PLAIN,20));
		cardNumberField.setText("Card Number");
		
		String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
		String[] years = {"2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032"};
		monthBox = new JComboBox(months);
		yearBox = new JComboBox(years);
		monthBox.setBounds(45, 145, 50, 30);
		yearBox.setBounds(130, 145, 60, 30);
		
		cvvField = new JTextField();
		cvvField.setBounds(220,145,70,30);
		cvvField.setFont(new Font("Consolas",Font.PLAIN,23));
		cvvField.setText("CVV");
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(190, 185, 80, 35);
		submitButton.addActionListener(this);
		
		backButton = new JButton("Back");
		backButton.setBounds(100, 185, 80, 35);
		backButton.addActionListener(this);
		
		ImageIcon myImage = new ImageIcon("icons\\rac.png");//create an ImageIcon
		this.setIconImage(myImage.getImage());//change icon of frame
		this.setTitle("Payment Window");//set title of frame
		this.setLocation(115,250);
		this.setResizable(true);//prevent frame from being resized
		this.setSize(400,280);//set the x-dimension, and y-dimension of frame
		this.setLayout(null);
		this.add(priceLabel);
		this.add(nameField);
		this.add(cardNumberField);
		this.add(monthBox);
		this.add(yearBox);
		this.add(submitButton);
		this.add(cvvField);
		this.add(backButton);
		this.setVisible(true);
	}
	public boolean getStatus() {
		return status;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submitButton) {
			String name = nameField.getText();
			String cardNumber = cardNumberField.getText();
			String month = monthBox.getSelectedItem().toString();
			String year = yearBox.getSelectedItem().toString();
			String cvv = cvvField.getText();
			String line = name + ";" + cardNumber + ";" + month + ";" + year + ";" + cvv;
			if(checkCards(line)) {
				if(checkBalance(cardNumber)) {
					JOptionPane.showMessageDialog(null, "Payment is successful."
							, "Successful Payment", JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
					status = true;
				}
				else {
					JOptionPane.showMessageDialog(null, "Insufficient Balance"
							, "Unsuccessful Payment", JOptionPane.ERROR_MESSAGE);
					this.dispose();
				}
					
			}
			else {
				JOptionPane.showMessageDialog(null, "Please check credit card information you entered"
						, "Unsuccessful Payment", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == backButton) {
			this.dispose();
		}
		
	}
	private boolean checkCards(String line) {
		File file = new File("database\\cards.txt");
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine())
				if(line.equals(sc.nextLine()))
					return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	private boolean checkBalance(String cardNumber) {
		File file = new File("database\\balances.txt");
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()) {
				String[] parsedLine = sc.nextLine().split(";");
				if(cardNumber.equals(parsedLine[0])){
					if(Integer.valueOf(parsedLine[1])>price)
						return true;
					else
						return false;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
