import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewContracts extends JFrame implements ActionListener{
	ArrayList<String> lines = new ArrayList<String>();
	JPanel carPanel = new JPanel();
	JLabel[] carLabels = new JLabel[6];
	JLabel [] carpropertiesL = new JLabel[8];
	JButton prevButton = new JButton();
	JButton nextButton = new JButton();
	int pageNumber = 0;
	User user;
	public ViewContracts(User user) {
		this.user = user;
		readTxtFile();
		
		carPanel.setBounds(10, 10,440,680);
		carPanel.setLayout(null);
		
		prevButton.setBounds(150,700,80,20);
		prevButton.addActionListener(this);
		prevButton.setFocusable(false);
		prevButton.setText("Prev");
		nextButton.setBounds(250,700,80,20);
		nextButton.addActionListener(this);
		nextButton.setFocusable(false);
		nextButton.setText("Next");
		
		this.setLocation(575,60);
		this.add(prevButton);
		this.add(nextButton);
		this.setTitle("Contracts");//set title of frame
		this.setResizable(false);//prevent frame from being resized
		this.setSize(450,780);//set the x-dimension, and y-dimension of frame
		this.setLayout(null);
		ImageIcon myImage = new ImageIcon("icons\\rac.png");//create an ImageIcon
		this.setIconImage(myImage.getImage());//change icon of frame
		refresh();
	}
	private void readTxtFile() {
		File file = new File("database\\Contracts.txt");
	    Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine())
				lines.add(sc.nextLine());
			for(int i = 0 ; i < lines.size(); i++) {
				String[] temp = lines.get(i).split(";");
				if(!user.isAdmin() && (!user.getName().equals(temp[7]) || !user.getSurname().equals(temp[8]))) {
					lines.remove(i);
					i--;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	   
	}
	private void refresh() {
			int iterationNumber = 6;
			carPanel.removeAll();
			if(lines.size() == 0) {
				this.add(carPanel);
				this.dispose();
				this.setVisible(true);
				return;
			}
			else {
				iterationNumber = iterationNumber < lines.size() - pageNumber *6 ? iterationNumber: lines.size() - pageNumber *6; 
			}
			for (int i = 0; i < iterationNumber; i++) {
				String[] data = lines.get(i + pageNumber * 6).split(";");
				ImageIcon car_image=new ImageIcon("icons\\cars\\"+data[0]+"-"+data[1]+".png");
				if(car_image.getImageLoadStatus() == 4)
					car_image=new ImageIcon("icons\\cars\\NOTFOUND.png");
				carpropertiesL[0]=new JLabel(data[0]);
				carpropertiesL[1]=new JLabel(data[1]);
				carpropertiesL[2]=new JLabel(data[2]);
				carpropertiesL[3]=new JLabel(data[3]);
				carpropertiesL[4]=new JLabel(data[4]);
				carpropertiesL[5]=new JLabel(data[5]);
				carpropertiesL[6]=new JLabel(data[6]);
				carpropertiesL[7]=new JLabel(data[7] + " " + data[8]);
				
				carpropertiesL[0].setBounds(250,i*115,100,13);
				carpropertiesL[1].setBounds(250,13+i*115,100,13);
				carpropertiesL[2].setBounds(250,26+i*115,100,13);
				carpropertiesL[3].setBounds(250,39+i*115,100,13);
				carpropertiesL[4].setBounds(250,52+i*115,100,13);
				carpropertiesL[5].setBounds(250,65+i*115,100,13);
				carpropertiesL[6].setBounds(250,78+i*115,100,13);
				carpropertiesL[7].setBounds(250,91+i*115,100,13);
				
				carLabels[i] = new JLabel();
				carLabels[i].setIcon(car_image);
				carLabels[i].setBounds(50,i*115,200,100);
				carPanel.add(carpropertiesL[0]);
				carPanel.add(carpropertiesL[1]);
				carPanel.add(carpropertiesL[2]);
				carPanel.add(carpropertiesL[3]);
				carPanel.add(carpropertiesL[4]);
				carPanel.add(carpropertiesL[5]);
				carPanel.add(carpropertiesL[6]);
				carPanel.add(carpropertiesL[7]);
				carPanel.setBounds(0,10,500,680);
				carPanel.add(carLabels[i]);
			}
			carPanel.setVisible(true);
			this.add(carPanel);
			this.dispose();
			this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == prevButton && pageNumber > 0) {
			pageNumber--;
			refresh();
		}
		if(e.getSource() == nextButton && pageNumber < (lines.size()-1)/6) {
			pageNumber++;
			refresh();
		}
		
	}

}
