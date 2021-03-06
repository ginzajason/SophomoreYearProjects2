import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class City extends JPanel{
	private JButton upgradeB;
	private JLabel upgradeL;
	private Timer checkTimer;
	private int gameSpeed = 1;
	private boolean complete = false;
	private VilliageButton villiage;
	private Wall wall;
	private Office office1, office2, office3, office4;
	private Boat boat1, boat2, boat3, boat4;
	private StoryLabel story;
	private BadEvent event;
	
	public City() {
		this.setLayout(null);
		
		setupComponents();
		setupTimers();
		
		this.setBackground(Color.black);
		
		this.setPreferredSize(new Dimension(575, 700));
	}
	
	private void setupTimers() {
		checkTimer = new Timer(gameSpeed, new CheckListener());
		checkTimer.start(); 
	}
	
	private void setupUpgrade() {
		upgradeB = new JButton("Click to upgrade the city");
		upgradeL = new JLabel("Cost: 5000 Wood, 2500 Leaves, 1000 Rabbit, 500 stone, 300 iron, 200 water, 100 beef, 50 fish", SwingConstants.CENTER);
		
		upgradeB.setBackground(Color.WHITE);
		upgradeL.setForeground(Color.WHITE);
		
		upgradeB.setBounds(0, 0, 575, 25);
		upgradeL.setBounds(0, 25, 575, 25);
		
		upgradeB.addActionListener(new ButtonListener());
		
		add(upgradeB);
		add(upgradeL);
		
		this.updateUI();
	}
	
	private void destroyUpgrade() {
		remove(upgradeB);
		remove(upgradeL);
		
		
		upgradeB = null;
		upgradeL = null;
	}
	
	private void setupComponents() {
		villiage = new VilliageButton();
		
		Resource.allResources.get(6).setActive(true);
		
		story = new StoryLabel();
		wall = new Wall(1000, 1000);
		
		int woodc = Resource.allResources.get(0).getUsed();
		
		office1 = new Office(1200, 500, woodc + 1);
		office2 = new Office(1500, 700, woodc + 5);
		office3 = new Office(2000, 1000, woodc + 10);
		office4 = new Office(2500, 1500, woodc + 15);
		
		boat1 = new Boat(1250, 900, 100, 1);
		boat2 = new Boat(1300, 1000, 200, 110);
		boat3 = new Boat(1500, 1200, 800, 250);
		boat4 = new Boat(2000, 1600, 1000, 900);
		
		villiage.setBounds(245, 300, 75, 75);
		
		office1.setBounds(245, 150, 75, 100);
		office2.setBounds(365, 275, 75, 100);
		office3.setBounds(120, 275, 75, 100);
		office4.setBounds(245, 400, 75, 100);
		
		boat1.setBounds(405, 100, 100, 100);
		boat2.setBounds(405, 450, 100, 100);
		boat3.setBounds(70, 450, 100, 100);
		boat4.setBounds(70, 100, 100, 100);
		
		story.setBounds(0, 600, 575, 75);
		
		wall.setBounds(0, 50, 575, 700);

		event = new BadEvent();
		event.setBounds(0, 0, 575, 700);
		add(event);
		
		add(villiage);
		add(office1);
		add(office2);
		add(office3);
		add(office4);
		
		add(boat1);
		add(boat2);
		add(boat3);
		add(boat4);
		
		add(story);
		
		add(wall);
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == upgradeB) {
				//5000 Wood, 2500 Leaves, 1000 Rabbit, 500 stone, 300 iron, 200 water, 100 beef, 50 fish
				if(Resource.allResources.get(0).get() >= 5000 && Resource.allResources.get(1).get() >= 2500 && Resource.allResources.get(2).get() >= 1000 && Resource.allResources.get(4).get() >= 500 &&
						Resource.allResources.get(6).get() >= 300 && Resource.allResources.get(5).get() >= 200 && Resource.allResources.get(7).get() >= 100 && Resource.allResources.get(8).get() >= 50) {
					Resource.allResources.get(0).remove(5000);
					Resource.allResources.get(1).remove(2500);
					Resource.allResources.get(2).remove(1000);
					Resource.allResources.get(4).remove(500);
					Resource.allResources.get(6).remove(300);
					Resource.allResources.get(5).remove(200);
					Resource.allResources.get(7).remove(100);
					Resource.allResources.get(8).remove(50);
					complete = true;
				}
			}
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(wall.isBought()) {
				if(BuiltBuildings.getAmount() >= 8 && upgradeL == null) {
					setupUpgrade();
				}
				if(BuiltBuildings.getAmount() < 8 && upgradeL != null) {
					destroyUpgrade();
				}
			}
		}
	}
}
