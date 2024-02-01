import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RACSystem extends JFrame implements ActionListener, WindowListener{
	String howManyDays = "8";
	AddCarFrame addCarFrame;
	PaymentWindow paymentWindow;
	ViewContracts contractsWindow;
	ArrayList<Car> cars = new ArrayList<Car>();
	ArrayList<Car> filteredCars = new ArrayList<Car>();
	JButton paymentButton = new JButton();
	JButton filterButton = new JButton();
	JButton logInButton = new JButton();
	JTextField userText = new JTextField();
	JPasswordField passwordText = new JPasswordField();
	
	JLabel[] carLabels = new JLabel[6];
	JButton[] carButtons = new JButton[6];
	JLabel [] carpropertiesL = new JLabel[6];
	
	JLabel carbrandL=new JLabel("CAR BRAND");
	JLabel cartypeL=new JLabel("CAR TYPE");
	JLabel officenameL=new JLabel ("OFFICE NAME");
	JLabel sublimitL=new JLabel("SUB LIMIT");
	JLabel uplimitL=new JLabel("UP LIMIT");
	
	Date today;
	DateFormat myDateFormat;
	JLabel dateLabel;
	JButton dateButton;
	String pattern = "dd/MM/yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	
	JComboBox carbrandCB;
	JComboBox cartypeCB;
	JComboBox officenameCB;
	JComboBox sortCB;
	JTextField uplimitT=new JTextField();
	JTextField sublimitT=new JTextField();
	JButton clearButton = new JButton();
	JButton sortB=new JButton("Sort");
	
	JPanel carPanel = new JPanel();
	
	JButton prevButton = new JButton();
	JButton nextButton = new JButton();
	JButton RDUButton = new JButton();//Rent-Delete-Update Button
	JButton contractsButton = new JButton();
	int RDUCounter = 0;
	String[] RDUStates = {"Rent", "Delete", "Update"};
	JButton addCarButton = new JButton();
	
	JFrame updatecarF;
	JLabel iconLabel;
	JLabel priceLabel;
	JTextField priceField;
	JLabel officeLabel;
	JTextField officeField;
	JButton updateButton;
	JButton backupdateButton;
	int pageNumber;
	User system_user;
	boolean paymentStatus;
	Car selectedCar;
	boolean adminMode;
	
	RACSystem(User user) throws IOException{
		system_user=user;
		adminMode = true;
		pageNumber = 0;
		initiliazeSystem();
		carPanel.setBounds(10, 100,600,500);
		carPanel.setLayout(null);
		JLabel myLabel = new JLabel();
		myLabel.setBounds(180, 10, 250, 20);
		myLabel.setText("Welcome "+ user.getName());
		myLabel.setOpaque(true);;
		myLabel.setHorizontalTextPosition(JLabel.CENTER);
		myLabel.setVerticalTextPosition(JLabel.TOP);
		myLabel.setFont(new Font("MV Boli",Font.PLAIN,20));
		myLabel.setVerticalAlignment(JLabel.CENTER);
		myLabel.setHorizontalAlignment(JLabel.CENTER);
		
		
		filterButton.setBounds(530,70,80,20);
		filterButton.addActionListener(this);
		filterButton.setFocusable(false);
		filterButton.setText("Filter");
		clearButton.setBounds(530,50,80,20);
		clearButton.addActionListener(this);
		clearButton.setFocusable(false);
		clearButton.setText("Clear");
		
		prevButton.setBounds(300,710,80,20);
		prevButton.addActionListener(this);
		prevButton.setFocusable(false);
		prevButton.setText("Prev");
		nextButton.setBounds(400,710,80,20);
		nextButton.addActionListener(this);
		nextButton.setFocusable(false);
		nextButton.setText("Next");
		
		RDUButton.setBounds(10,5,80,20);
		RDUButton.addActionListener(this);
		RDUButton.setFocusable(false);
		RDUButton.setText(RDUStates[RDUCounter]);
		addCarButton.setBounds(10,25,80,20);
		addCarButton.addActionListener(this);
		addCarButton.setFocusable(false);
		addCarButton.setText("Add Car");
		
		contractsButton.setBounds(100,5,90,20);
		if(!system_user.isAdmin())
			contractsButton.setBounds(10,10,90,20);
		contractsButton.addActionListener(this);
		contractsButton.setFocusable(false);
		contractsButton.setText("Contracts");
		
		
		carbrandL.setBounds(10,50,80,20);
		cartypeL.setBounds(130,50,80,20);
		officenameL.setBounds(250,50,90,20);
		sublimitL.setBounds(380,50,80,20);
		uplimitL.setBounds(460,50,80,20);
		
		uplimitT.setText("0");
		sublimitT.setText("0");
		this.add(carbrandL);
		this.add(cartypeL);
		this.add(officenameL);
		this.add(uplimitL);
		this.add(sublimitL);
		
		ArrayList <String> brands=new ArrayList<String>(); 
		ArrayList <String> type=new ArrayList<String>();
		ArrayList <String> officenames=new ArrayList<String>();
		brands.add("SELECT");
		type.add("SELECT");
		officenames.add("SELECT");
 		for (int i = 0; i < cars.size(); i++) {
 			if(!brands.contains(cars.get(i).getBrand())) {
 				brands.add(cars.get(i).getBrand());
 			}
 			if(!type.contains(cars.get(i).getType())) {
 				type.add(cars.get(i).getType());
 			}
 			if(!officenames.contains(cars.get(i).getOffice())) {
 				officenames.add(cars.get(i).getOffice());
 			}
		}
 		String [] carbrands=new String[brands.size()];
		String [] cartype=new String[type.size()];
		String [] officename=new String[officenames.size()];
		String[] sortStrings = {"SELECT", "ALPHA:ASC", "ALPA:DEC", "PRICE:ASC", "PRICE:DEC"};
		int a=0,b=0,c=0;
		for (int i = 0; i <cars.size(); i++) {
			if(i<brands.size()) {
				carbrands[a]=brands.get(i);
				a++;
			}
			if(i<type.size()) {
				cartype[b]=type.get(i);
				b++;
			}
			if(i<officenames.size()) {
				officename[c]=officenames.get(i);
				c++;
			}
		}
		carbrandCB=new JComboBox(carbrands);
		cartypeCB=new JComboBox(cartype);
		officenameCB=new JComboBox(officename);
		sortCB = new JComboBox(sortStrings);

		
		carbrandCB.setBounds(10,70,100,20);
		cartypeCB.setBounds(130,70,100,20);
		officenameCB.setBounds(250,70,100,20);
		sortCB.setBounds(50,710,90,20);
		sortB.setBounds(145,710,65,20);
		sortB.addActionListener(this);
		sublimitT.setBounds(380,70,60,20);
		uplimitT.setBounds(460,70,60,20);
		
		this.add(carbrandCB);
		this.add(cartypeCB);
		this.add(officenameCB);
		this.add(uplimitT);
		this.add(sublimitT);
		
		
		
		
		this.setTitle("Rent-A-Car");//set title of frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Exit out of application
		this.setResizable(false);//prevent frame from being resized
		this.setSize(630,780);//set the x-dimension, and y-dimension of frame
		this.setLayout(null);
		ImageIcon myImage = new ImageIcon("icons\\rac.png");//create an ImageIcon
		this.setIconImage(myImage.getImage());//change icon of frame
		if(user.isAdmin()) {
			this.add(RDUButton);
			this.add(addCarButton);
		}
			
		
		this.add(filterButton);
		this.add(clearButton);
		this.add(prevButton);
		this.add(nextButton);
		this.add(contractsButton);
		this.add(dateLabel);
		this.setLocation(500,50);
		this.add(myLabel);
		this.add(sortCB);
		this.add(sortB);
		refresh(pageNumber);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == clearButton) {
			carbrandCB.setSelectedIndex(0);
			cartypeCB.setSelectedIndex(0);
			officenameCB.setSelectedIndex(0);
			sublimitT.setText("0");
			uplimitT.setText("0");
		}
		if(e.getSource()== filterButton) {
			pageNumber = 0;
			if(Integer.parseInt(sublimitT.getText()) >  Integer.parseInt(uplimitT.getText())) {
				String temp = sublimitT.getText();
				sublimitT.setText(uplimitT.getText());
				uplimitT.setText(temp);
			}
			refresh(pageNumber);
		}
		if(e.getSource() == prevButton && pageNumber > 0) {
			pageNumber--;
			refresh(pageNumber);
		}
		if(e.getSource() == nextButton && pageNumber < (filteredCars.size()-1)/6) {
			pageNumber++;
			refresh(pageNumber);
		}
		if(e.getSource() == dateButton) {
			today.setDate(today.getDate()+1);
			dateLabel.setText(myDateFormat.format(today));
			checkAvailability();
			refresh(pageNumber);
		}
		if(e.getSource()== carButtons[0]) {
			selectedCar = filteredCars.get(pageNumber* 6 + 0);
			carButtonProcess();
		}
		if(e.getSource()== carButtons[1]) {
			selectedCar = filteredCars.get(pageNumber* 6 + 1);
			carButtonProcess();
		}
		if(e.getSource()== carButtons[2]) {
			selectedCar = filteredCars.get(pageNumber* 6 + 2);
			carButtonProcess();
		}
		if(e.getSource()== carButtons[3]) {
			selectedCar = filteredCars.get(pageNumber* 6 + 3);
			carButtonProcess();
		}
		if(e.getSource()== carButtons[4]) {
			selectedCar = filteredCars.get(pageNumber* 6 + 4);
			carButtonProcess();
		}
		if(e.getSource()== carButtons[5]) {
			selectedCar = filteredCars.get(pageNumber* 6 + 5);
			carButtonProcess();
		}
		if(e.getSource() == RDUButton) {
			RDUCounter++;
			if(RDUCounter == 3)
				RDUCounter = 0;
			RDUButton.setText(RDUStates[RDUCounter]);
		}
		if(e.getSource() == addCarButton) {
			addCarFrame = new AddCarFrame();
			addCarFrame.addWindowListener(this);
		}
		if(e.getSource()==sortB) {
			refresh(pageNumber);
		}
		if(e.getSource() == backupdateButton) {
			updatecarF.dispose();
		}
		if(e.getSource() == updateButton) {
			selectedCar.setPrice(Integer.parseInt(priceField.getText()));
			selectedCar.setOffice(officeField.getText());
			try {
				updateCarsTxt();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			refresh(pageNumber);
			updatecarF.dispose();
		}
		if(e.getSource() == contractsButton) {
			contractsWindow = new ViewContracts(system_user);
			contractsWindow.addWindowListener(this);
			this.dispose();
		}
	}
	private void carButtonProcess() {
		if(!adminMode || RDUButton.getText().equals("Rent")) {
			howManyDays="8";
			while(howManyDays != null && Integer.parseInt(howManyDays) > 7) {
				howManyDays =JOptionPane.showInputDialog(this,"Please enter how many day you want to rent\nMax 7 days");  
			}
			if(howManyDays != null) {
				paymentWindow = new PaymentWindow(selectedCar.getPrice() * Integer.parseInt(howManyDays));
				paymentWindow.addWindowListener(this);
				
			}
			else {
				JOptionPane.showMessageDialog(null, "You pressed cancel."
						, "Cancelled Renting", JOptionPane.ERROR_MESSAGE);
			}		
		}
		else if(RDUButton.getText().equals("Delete")){
			String yesNo =JOptionPane.showInputDialog(this,"Please enter YES to delete the selected car.");
			if(yesNo == null || (yesNo != null && !yesNo.equals("YES")))
				return;
			cars.remove(selectedCar);
			refresh(pageNumber);
			try {
				updateCarsTxt();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			updateCar();
		}
	}
	
	private void createContract() throws IOException, ParseException {
		String start_time = simpleDateFormat.format(new Date());;
		Date finish_date=new Date();
		finish_date.setDate(today.getDate()+Integer.parseInt(howManyDays));
		String finish_time = simpleDateFormat.format(finish_date);
		Contract newContract= new Contract(selectedCar, start_time, finish_time, system_user);
		selectedCar.setAvaliableOn(finish_date);
		selectedCar.setAvaliable(false);
		updateCarsTxt();
		try {
		      FileWriter fw1 = new FileWriter("database//Contracts.txt",true);
		      BufferedWriter bw1 = new BufferedWriter(fw1);
		      bw1.newLine();
		      bw1.write(newContract.getCar().getBrand()+";"+newContract.getCar().getModel()+";"+newContract.getCar().getPlate()+
		    		  ";"+newContract.getCar().getOffice()+";"+(newContract.getCar().getPrice()*Integer.parseInt(howManyDays))+";"+newContract.getStart_time()+";"+
		    		  newContract.getFinish_time()+";"+newContract.getUser().getName()+";"+newContract.getUser().getSurname());
		      bw1.flush();
	    } catch (IOException e1) {
		      System.out.println("An error occurred.");
		      e1.printStackTrace();
	    }
		howManyDays="8";
		
	}
	
	private void updateCarsTxt() throws IOException {
		FileWriter fw = new FileWriter("database//Cars.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		for (int i = 0; i < cars.size(); i++) {
			String availableon_date ;
			availableon_date = simpleDateFormat.format(cars.get(i).getAvaliableOn());
			bw.write(cars.get(i).getBrand()+";"+cars.get(i).getModel()+";"+cars.get(i).getPlate()+";"+
			  cars.get(i).getKm()+";"+cars.get(i).getPrice()+";"+cars.get(i).getType()+";"+cars.get(i).getOffice()+";"+availableon_date);
			bw.newLine();
		}
		bw.flush();
	}
	private void updateCar() {
		updatecarF=new JFrame();
        iconLabel=new JLabel();
		priceField = new JTextField();
		officeField = new JTextField();
		JTextField[] fields = {null,null, null, null, priceField, null, officeField};
		String[] labelNames = {selectedCar.getBrand(), selectedCar.getModel(), selectedCar.getPlate(),String.valueOf(selectedCar.getKm()),
				String.valueOf(selectedCar.getPrice()), selectedCar.getType(), selectedCar.getOffice()};
		JLabel[] labels = new JLabel[labelNames.length];
		
		updatecarF.setLayout(null);
		for(int i = 0; i < fields.length; i++) {
			if(i == 4 || i == 6) {
				fields[i].setText(labelNames[i]);
				fields[i].setBounds(180, 30 + i*25, 80, 25);
				updatecarF.add(fields[i]);
			}
			else {
				labels[i] = new JLabel(labelNames[i]);
				labels[i].setBounds(180, 30 + i*25, 80, 25);
				updatecarF.add(labels[i]);
			}	
		}
		
		ImageIcon car_image=new ImageIcon("icons\\cars\\"+selectedCar.getBrand()+"-"+selectedCar.getModel()+".png");
		iconLabel.setIcon(car_image);
		iconLabel.setBounds(0, 75, 170, 90);
		updateButton = new JButton("Update");
		updateButton.setBounds(150,225 , 80, 25);
		updateButton.addActionListener(this);
		backupdateButton=new JButton("Back");
		backupdateButton.setBounds(10,225,100,25);
		backupdateButton.addActionListener(this);
		updatecarF.setTitle("Update Car");//set title of frame
		updatecarF.setResizable(false);//prevent frame from being resized
		updatecarF.setSize(300,290);//set the x-dimension, and y-dimension of frame
		updatecarF.add(updateButton);
		updatecarF.add(backupdateButton);
		updatecarF.add(iconLabel);
		updatecarF.setLocation(280,300);
		updatecarF.setVisible(true);
	}
	private void filterCars(String brand, String type, String office, int subLimit, int upLimit,int mode) {
		filteredCars.clear();
		filteredCars.addAll(cars);
		for(int i = 0; i < filteredCars.size(); i++) {
			if( !filteredCars.get(i).isAvaliable()
				||(brand != "SELECT" && !brand.equals(filteredCars.get(i).getBrand()))
				||(type != "SELECT" && !type.equals(filteredCars.get(i).getType()))
				||(office != "SELECT" && !office.equals(filteredCars.get(i).getOffice()))
				||(subLimit != 0 && subLimit > filteredCars.get(i).getPrice())
				||(upLimit != 0 && upLimit < filteredCars.get(i).getPrice())){
				filteredCars.remove(i);
				i--;
			}
		}
		if(mode == 1) 
			Collections.sort(filteredCars, (a, b) -> (a.getBrand()+a.getModel()).compareTo(b.getBrand()+b.getModel()));
		else if(mode == 2)
			Collections.sort(filteredCars, (b, a) -> (a.getBrand()+a.getModel()).compareTo(b.getBrand()+b.getModel()));
		else if(mode == 3)
			Collections.sort(filteredCars, (a, b) -> a.getPrice() - b.getPrice());
		else if(mode==4)
			Collections.sort(filteredCars, (b, a) -> a.getPrice() - b.getPrice());
	}
	private void refresh(int page) {
		filterCars(carbrandCB.getSelectedItem().toString()
				, cartypeCB.getSelectedItem().toString(),
				officenameCB.getSelectedItem().toString(), 
				Integer.valueOf(sublimitT.getText())  , Integer.valueOf(uplimitT.getText())
				,sortCB.getSelectedIndex());
		int a=0, iterationNumber = 6;
		carPanel.removeAll();
		if(filteredCars.isEmpty()) {
			this.add(carPanel);
			this.dispose();
			this.setVisible(true);
			return;
		}
		else {
			iterationNumber = iterationNumber < filteredCars.size() - page *6 ? iterationNumber: filteredCars.size() - page *6; 
		}
		for (int i = 0; i < iterationNumber; i++) {
			ImageIcon car_image=new ImageIcon("icons\\cars\\"+filteredCars.get(i + page * 6).getBrand()+"-"+filteredCars.get(i + page * 6).getModel()+".png");
			if(car_image.getImageLoadStatus() == 4)
				car_image=new ImageIcon("icons\\cars\\NOTFOUND.png");
			carpropertiesL[0]=new JLabel(filteredCars.get(i + page * 6).getBrand());
			carpropertiesL[1]=new JLabel(filteredCars.get(i + page * 6).getModel());
			carpropertiesL[2]=new JLabel(filteredCars.get(i + page * 6).getPlate());
			carpropertiesL[3]=new JLabel(String.valueOf(filteredCars.get(i + page * 6).getPrice()));
			carpropertiesL[4]=new JLabel(filteredCars.get(i + page * 6).getType());
			carpropertiesL[5]=new JLabel(filteredCars.get(i + page * 6).getOffice());
			
			carpropertiesL[0].setBounds(250,10+i*100,100,13);
			carpropertiesL[1].setBounds(250,23+i*100,100,13);
			carpropertiesL[2].setBounds(250,36+i*100,100,13);
			carpropertiesL[3].setBounds(250,49+i*100,100,13);
			carpropertiesL[4].setBounds(250,62+i*100,100,13);
			carpropertiesL[5].setBounds(250,75+i*100,100,13);
			
			carButtons[i] = new JButton("Select");
			carButtons[i].setBounds(375,40+100*i,120,20);
			carButtons[i].addActionListener(this);
			
			carLabels[i] = new JLabel();
			carLabels[i].setIcon(car_image);
			carLabels[i].setBounds(50,i*100,200,100);
			carPanel.add(carpropertiesL[0]);
			carPanel.add(carpropertiesL[1]);
			carPanel.add(carpropertiesL[2]);
			carPanel.add(carpropertiesL[3]);
			carPanel.add(carpropertiesL[4]);
			carPanel.add(carpropertiesL[5]);
			carPanel.add(carButtons[i]);
			carPanel.setBounds(0,100,500,600);
			carPanel.add(carLabels[i]);
		}
		carPanel.setVisible(true);
		this.add(carPanel);
		this.dispose();
		this.setVisible(true);
	}
	
	private void initiliazeSystem() throws IOException {
		today = new Date();
		myDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateButton = new JButton("Day+1");
		dateLabel = new JLabel();
		dateLabel.setBounds(530,5,100,50);
		dateButton.setBounds(0,0,80,20);
		dateButton.setFocusable(false);
		dateLabel.add(dateButton);
		dateButton.addActionListener(this);
		dateLabel.setText(myDateFormat.format(today));
		File file = new File("database\\Cars.txt");
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		try {
			String str=" ";
			while((str=br.readLine())!=null) {
				
				String [] parts=str.split(";"); //brand,model,plate,km,price,type,office,available
				if(parts.length==8) {
					Date exampleDate = null;
					try {
						exampleDate = myDateFormat.parse(parts[7]);
					
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Car car=new Car(parts[0], parts[1], parts[2],  Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), parts[5], parts[6], false,exampleDate);
					cars.add(car);
				}
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkAvailability();
		filterCars("SELECT", "SELECT","SELECT", 0,0,0);
	}
	
	public void checkAvailability() {
		for(int i = 0; i < cars.size(); i++) {
			if(cars.get(i).getAvaliableOn().before(today)) {
				cars.get(i).setAvaliable(true);
			}
				
		}
	}
	@Override
	public void windowOpened(WindowEvent e) {
		if(e.getSource() == paymentWindow) {
			for(int i = 0 ; i < carButtons.length; i++) {
				carButtons[i].removeActionListener(this);
			}
		}
	}
	@Override
	public void windowClosing(WindowEvent e) {
		if(e.getSource() == paymentWindow) {
			for(int i = 0 ; i < carButtons.length; i++) {
				carButtons[i].addActionListener(this);
			}
		}
		if(e.getSource() == contractsWindow) {
			this.setVisible(true);
		}
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		if(e.getSource() == paymentWindow) {
			paymentStatus = paymentWindow.getStatus();
			if(paymentStatus) {
				try {
					createContract();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//user, car, and howManyDays are sent
                catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			    } catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			for(int i = 0 ; i < carButtons.length; i++) {
				carButtons[i].addActionListener(this);
			}
		}
		if(e.getSource() == addCarFrame) {
			Car newCar = addCarFrame.getCar();
			if(newCar != null) {
				cars.add(newCar);
				refresh(pageNumber);
				try {
					updateCarsTxt();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
}