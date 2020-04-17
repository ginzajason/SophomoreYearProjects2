import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Hut extends JPanel{
	private int checkSpeed = 1;
	private int removalSpeed = 25000;//set to 50k
	private Timer removalTimer, checkTimer;
	private JButton buildB;
	private boolean built = false;
	private int woodCost, leafCost;
	private JLabel woodL, hutL, leafL;
	private int maxWood;
	
	public Hut(int woodCost, int leafCost, int maxWood) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(75, 100));
		this.setBackground(Color.black);

		this.maxWood = maxWood;
		this.woodCost = woodCost;
		this.leafCost = leafCost;
		
		timerSetup();
	}
	
	private void buildHut() {
		setupComponents();
	}
	
	private void timerSetup() {
		removalTimer = new Timer(removalSpeed, new RemoveListener());
		removalTimer.start(); 
		
		checkTimer = new Timer(checkSpeed, new CheckListener());
		checkTimer.start(); 
	}
	
	public boolean isBuilt() {
		return built;
	}

	
	private void setupComponents() {
		buildB = new JButton("Build");
		woodL = new JLabel(woodCost + " Wood", SwingConstants.CENTER);
		leafL = new JLabel(leafCost + " Leaves", SwingConstants.CENTER);
		hutL = new JLabel("HUT", SwingConstants.CENTER);
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
			
		hutL.setForeground(Color.WHITE);
		woodL.setForeground(Color.WHITE);
		leafL.setForeground(Color.WHITE);
		
		buildB.addActionListener(new ButtonListener());
		
		hutL.setBounds(0, 0, 75, 25);
		buildB.setBounds(0, 25, 75, 25);
		woodL.setBounds(0, 50, 75, 25);
		leafL.setBounds(0, 75, 75, 25);
		
		add(buildB);
		add(woodL);
		add(hutL);
		add(leafL);
		
		this.updateUI();
	}
	
	private void removeHut() {
		buildB.setText("Build");
		woodL.setText(woodCost + " Wood");
		leafL.setText(leafCost + " Leaves");
		hutL.setText("HUT");
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		buildB.setSize(75, 25);
		
		hutL.setForeground(Color.WHITE);
		woodL.setForeground(Color.WHITE);
		leafL.setForeground(Color.WHITE);
		
		buildB.setEnabled(true);
		built = false;
		People.removePeople(1);
		
		
	}
	
	private void addHut() {
		Resource.allResources.get(0).remove(woodCost);
		Resource.allResources.get(1).remove(leafCost);
		Resource.allResources.get(0).addMultiplier(1);
		Resource.allResources.get(1).addMultiplier(1);
		People.addPeople(1);
		buildB.setBackground(Color.GRAY);
		built = true;
		buildB.setText("HUT");
		buildB.setSize(75, 75);
		buildB.setEnabled(false);
		woodL.setText("");
		hutL.setText("");
		leafL.setText("");
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == buildB && buildB.getText().equals("Build")) {
				if(Resource.allResources.get(0).get() >= woodCost && Resource.allResources.get(1).get() >= leafCost && built == false) {
					addHut();
				}
			}	
		}
	}

	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(built == false && woodL == null && Resource.allResources.get(0).getUsed() >= maxWood) {
				buildHut();
				Resource.allResources.get(2).setActive(true);
			}
		}
	}
}
