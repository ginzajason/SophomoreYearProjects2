import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Inventory extends JPanel{
	private Timer gameTimer;
	private int gameSpeed = 1;
	private JLabel infoL;
	private InventoryStory story;
	
	public Inventory() {
		this.setPreferredSize(new Dimension(575, 700));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		setupComponents();
		
		timerSetup();
	}
	
	private void setupComponents() {
		story = new InventoryStory();
		infoL = new JLabel("test");
		
		infoL.setBounds(0, 650, 575, 25);
		
		add(infoL);
		
		for(int x = 0; x < Resource.allResources.size(); x++) {
			add(Resource.allResources.get(x).getItem());
		}
	}
	
	private void updateLocation() {
		int x = 25;
		int y = 0;
		
		infoL.setText(InventoryStory.getStory());
		
		for(int z = 0; z < Resource.allResources.size(); z++) {
			if(x == 550) {
				x = 25;
				y += 75;
			}
			if(Resource.allResources.get(z).isActive() == true && Resource.allResources.get(z).get() > 0) {
				Resource.allResources.get(z).setBounds(x, y, 75, 75);
				x += 75;
			}
			else {
				//set it outside of the screen
				Resource.allResources.get(z).setBounds(-100, -100, 75, 75);
			}
		}
	}
	
	private void timerSetup() {
		gameTimer = new Timer(gameSpeed, new GameListener());
		gameTimer.start();
	}
	
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			updateLocation();
		}
	}
	
}
