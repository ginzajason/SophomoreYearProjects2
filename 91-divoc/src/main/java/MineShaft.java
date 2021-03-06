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

public class MineShaft extends Building{
	private int checkSpeed = 1;
	private Timer checkTimer;
	private JButton buildB;
	private boolean built = false;
	private int woodCost, rabbitCost, wheatCost;
	private JLabel woodL, wheatL, rabbitL;
	private int maxWheat;
	
	public MineShaft(int woodCost, int rabbitCost, int wheatCost, int maxWheat) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(Color.black);

		this.maxWheat = maxWheat;
		this.woodCost = woodCost;
		this.wheatCost = wheatCost;
		this.rabbitCost = rabbitCost;
		
		BuiltBuildings.add(this);
		
		timerSetup();
	}
	
	private void build() {
		setupComponents();
	}
	
	private void timerSetup() {
		checkTimer = new Timer(checkSpeed, new CheckListener());
		checkTimer.start(); 
	}
	
	public boolean isBuilt() {
		return built;
	}

	
	private void setupComponents() {
		buildB = new JButton("MineShaft");
		woodL = new JLabel(woodCost + " Wood", SwingConstants.CENTER);
		wheatL = new JLabel(wheatCost + " Wheat", SwingConstants.CENTER);
		rabbitL = new JLabel(rabbitCost + " Rabbits", SwingConstants.CENTER);
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		woodL.setForeground(Color.WHITE);
		wheatL.setForeground(Color.WHITE);
		rabbitL.setForeground(Color.WHITE);
		
		buildB.addActionListener(new ButtonListener());
		
		buildB.setBounds(0, 0, 100, 25);
		woodL.setBounds(0, 25, 100, 25);
		wheatL.setBounds(0, 50, 100, 25);
		rabbitL.setBounds(0, 75, 100, 25);
		
		add(buildB);
		add(woodL);
		add(wheatL);
		add(rabbitL);
		
		this.updateUI();
	}
	
	public void remove() {
		buildB.setText("MineShaft");
		woodL.setText(woodCost + " Wood");
		wheatL.setText(wheatCost + " Leaves");
		rabbitL.setText(rabbitCost + " Rabbits");
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		buildB.setLocation(0, 0);
		buildB.setSize(100, 25);

		woodL.setForeground(Color.WHITE);
		wheatL.setForeground(Color.WHITE);
		rabbitL.setForeground(Color.WHITE);
		
		buildB.setEnabled(true);
		built = false;
		Resource.allResources.get(4).removeMultiplier(1);
		
	}
	
	private void add() {
		Resource.allResources.get(0).remove(woodCost);
		Resource.allResources.get(3).remove(wheatCost);
		Resource.allResources.get(2).remove(rabbitCost);
		Resource.allResources.get(4).addMultiplier(1);
		Resource.allResources.get(4).setActive(true);
		buildB.setBackground(Color.GRAY);
		built = true;
		buildB.setText("MineShaft");
		buildB.setLocation(0, 0);
		buildB.setSize(100, 100);
		buildB.setEnabled(false);
		woodL.setText("");
		wheatL.setText("");
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == buildB && buildB.getText().equals("MineShaft")) {
				if(Resource.allResources.get(0).get() >= woodCost && Resource.allResources.get(3).get() >= wheatCost && Resource.allResources.get(2).get() >= rabbitCost && built == false) {
					add();
				}
			}	
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(built == false && woodL == null && Resource.allResources.get(3).getOverall() >= maxWheat) {
				build();
			}
		}
	}
}
