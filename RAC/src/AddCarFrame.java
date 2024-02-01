import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddCarFrame extends JFrame implements ActionListener{
	Car newCar;
	JLabel brandLabel;
	JTextField brandField;
	JLabel modelLabel;
	JTextField modelField;
	JLabel plateLabel;
	JTextField plateField;
	JLabel kmLabel;
	JTextField kmField;
	JLabel priceLabel;
	JTextField priceField;
	JLabel typeLabel;
	JTextField typeField;
	JLabel officeLabel;
	JTextField officeField;
	JLabel directoryLabel;
	JTextField directoryField;
	JButton saveButton;
	Date myDate;
	AddCarFrame(){
		brandField = new JTextField();
		modelField = new JTextField();
		plateField = new JTextField();
		kmField = new JTextField();
		priceField = new JTextField();
		typeField = new JTextField();
		officeField = new JTextField();
		directoryField = new JTextField();
		JTextField[] fields = {brandField,modelField, plateField, kmField, priceField, typeField, officeField, directoryField};
		String[] labelNames = {"Brand: ", "Model: ", "Plate: ", "Km: ", "Price: ", "Type: ", "Office: ", "Directory: "};
		JLabel[] labels = new JLabel[labelNames.length];
		
		this.setLayout(null);
		for(int i = 0; i < fields.length; i++) {
			fields[i].setBounds(65,5,150,25);
			fields[i].setFont(new Font("Consolas",Font.PLAIN,18));
			labels[i] = new JLabel(labelNames[i]);
			labels[i].setBounds(10, 10 + i*35, 300, 35);
			labels[i].add(fields[i]);
			this.add(labels[i]);
		}
		
		saveButton = new JButton("Save");
		saveButton.setBounds(10, 295, 215, 25);
		saveButton.addActionListener(this);
		myDate = new Date();
		this.setLocation(250,150);
		ImageIcon myImage = new ImageIcon("icons\\rac.png");//create an ImageIcon
		this.setIconImage(myImage.getImage());//change icon of frame
		this.setTitle("Add Car");//set title of frame
		this.setResizable(false);//prevent frame from being resized
		this.setSize(260,380);//set the x-dimension, and y-dimension of frame
		this.add(saveButton);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == saveButton ) {
			if(brandField.getText().isEmpty() || modelField.getText().isEmpty() ||
				plateField.getText().isEmpty() || kmField.getText().isEmpty()||
				priceField.getText().isEmpty() || typeField.getText().isEmpty() ||
				officeField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please check the features you entered"
						, "Unsufficient Feature", JOptionPane.ERROR_MESSAGE);
				return;
			}
			newCar = new Car(brandField.getText(), modelField.getText(), plateField.getText(),
					Integer.parseInt(kmField.getText()), Integer.parseInt(priceField.getText()),
					typeField.getText(), officeField.getText(), true, myDate);
			File file = new File(directoryField.getText());
			if(file.renameTo(new File("icons\\cars\\" + newCar.getBrand() + "-" + newCar.getModel() + ".png"))) {
				file.delete();
			}
			this.dispose();
		}
		
	}
	public Car getCar() {
		return newCar;
	}
}
