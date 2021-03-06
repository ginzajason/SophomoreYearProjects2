import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class divocMainPanel extends JPanel{
	
	private final int WIDTH = 575, HEIGHT = 700;
	private int speed = 75;
	private Timer timer;
	private Camp camp;
	private JTabbedPane tabs;
	private Inventory inventory;
	private Forest forest;
	private People people;
	private StrongHold stronghold;
	private Villiage villiage;
	private Town town;
	private City city;
	private State state;
	private Nation nation;
	private boolean admin = false;
	private Tradepost tradepost; 
	private Shop shop;
	private Resource wood, leaf, rabbit, wheat, stone, water, iron, beef, fish;
	private Story story;
	private Outro outro;
	private Frame main;
	
	public divocMainPanel(Frame main) {
		timer = new Timer(speed, new GameListener());
		
		setupValues();
		setupStories();
		setupComponents();
		
		this.main = main;
		
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		timer.start();
		
	}
	
	private void setupValues() {
		if(admin == false) {
			people = new People(1);
			wood = new Resource("Wood", 0, 0);
			leaf = new Resource("Leaf", 0, 0);
			wood.setActive(true);
			leaf.setActive(true);
			leaf.setOdds(50);
			rabbit = new Resource("Rabbit", 0, 0);
			wheat = new Resource("Wheat", 0, 0);
			stone = new Resource("Stone", 0, 0);
			water = new Resource("Water", 0, 0);
			iron = new Resource("Iron", 0, 0);
			iron.setOdds(50);
			beef = new Resource("Beef", 0, 0);
			fish = new Resource("Fish", 0, 0);
		}
		if(admin == true) {
			people = new People(1);
			wood = new Resource("Wood", 100000, 100000);
			wood.addUsed(10000);
			leaf = new Resource("Leaf", 100000, 100000);
			wood.setActive(true);
			leaf.setActive(true);
			leaf.setOdds(50);
			rabbit = new Resource("Rabbit", 100000, 100000);
			wheat = new Resource("Wheat", 100000, 100000);
			stone = new Resource("Stone", 100000, 100000);
			water = new Resource("Water", 100000, 100000);
			iron = new Resource("Iron", 100000, 100000);
			iron.setOdds(50);
			beef = new Resource("Beef", 100000, 100000);
			fish = new Resource("Fish", 100000, 100000);
		}
	}
	
	private void setupStories() {
		wood.setStory("Old rotten wood found near dead corpses");
		leaf.setStory("Leaves found around the wood");
		rabbit.setStory("Has green swolen bumps all over the skin");
		wheat.setStory("It looks like dirt and not worth eating");
		stone.setStory("Some hard material for hard buildings");
		water.setStory("Grayish in color but water is water");
		iron.setStory("A harder material than stone to build bigger buildings");
		beef.setStory("No way to make sure this meat is clean but atleast its something to eat");
		fish.setStory("Most are dead and floating on top of the water");
	}
	
	private void setupComponents() {
		tabs = new JTabbedPane(); 
		inventory = new Inventory();
		forest = new Forest();
		camp = new Camp();
		story = new Story();
		
		tabs.add("Camp", camp);
		tabs.add("Inventory", inventory);
		tabs.add("Forest", forest);
		
		add(tabs);
	}
	
	private void updateTabs() {
		if(camp.isComplete() && stronghold == null) {
			BuiltBuildings.removeall();
			stronghold = new StrongHold();
			tabs.remove(0);
			tabs.add(stronghold, 0);
			tabs.setTitleAt(0, "Stronghold");
			tabs.setSelectedIndex(0);
		}
		if(camp.isComplete() && stronghold.isComplete() && villiage == null) {
			BuiltBuildings.removeall();
			villiage = new Villiage();
			tabs.remove(0);
			tabs.add(villiage, 0);
			tabs.setTitleAt(0, "Villiage");
			tabs.setSelectedIndex(0);
		}
		if(camp.isComplete() && stronghold.isComplete() && villiage.isComplete() && town == null) {
			BuiltBuildings.removeall();
			town = new Town();
			tabs.remove(0);
			tabs.add(town, 0);
			tabs.setTitleAt(0, "Town");
			tabs.setSelectedIndex(0);
		}
		if(camp.isComplete() && stronghold.isComplete() && villiage.isComplete() && town.isComplete() && city == null) {
			BuiltBuildings.removeall();
			city = new City();
			tabs.remove(0);
			tabs.add(city, 0);
			tabs.setTitleAt(0, "City");
			tabs.setSelectedIndex(0);
		}
		if(camp.isComplete() && stronghold.isComplete() && villiage.isComplete() && town.isComplete() && city.isComplete() && state == null) {
			BuiltBuildings.removeall();
			state = new State();
			tabs.remove(0);
			tabs.add(state, 0);
			tabs.setTitleAt(0, "State");
			tabs.setSelectedIndex(0);
		}
		if(camp.isComplete() && stronghold.isComplete() && villiage.isComplete() && town.isComplete() && city.isComplete() && state.isComplete() && nation == null) {
			BuiltBuildings.removeall();
			nation = new Nation();
			tabs.remove(0);
			tabs.add(nation, 0);
			tabs.setTitleAt(0, "Nation");
			tabs.setSelectedIndex(0);
		}
		if(camp.isComplete() && stronghold.isComplete() && villiage.isComplete() && town.isComplete() && city.isComplete() && state.isComplete() && nation.isComplete() == true && outro == null) {
			outro = new Outro(main);
			remove(tabs);
			outro.setBounds(0, 0, 575, 700);
			add(outro);
			this.updateUI();
		}
		if(Tradepost.isActive()) {
			tradepost = new Tradepost();
			tabs.add("Tradepost", tradepost);
		}
		if(Shop.isActive()) {
			shop = new Shop();
			tabs.add("Shop", shop);
		}
	}
	
	private class GameListener implements ActionListener
	{
		//--------------------------------------------------------------
		//  Updates the position of the image and possibly the direction
		//  of movement whenever the timer fires an action event.
		//--------------------------------------------------------------
		public void actionPerformed(ActionEvent event)
		{
			updateTabs();
		}
	}
}
	