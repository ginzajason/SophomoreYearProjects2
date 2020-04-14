public class Wheat{
	private static int wheat, overallWheat, multiplier;
	private static boolean active;
	
	public Wheat(int wheat, int overall) {
		this.wheat = wheat;
		this.overallWheat = overall;
		this.active = false;
		this.multiplier = 1;
	}
	
	public static int getMultiplier() {
		return multiplier;
	}
	
	public static void addMultiplier(int add) {
		multiplier += add;
	}
	
	public static int getWheat() {
		return wheat;
	}
	
	public static void addWheat(int add) {
		wheat += add * multiplier;
		overallWheat += add * multiplier;
	}
	
	public static void removeWheat(int remove) {
		wheat -= remove;
	}
	
	public static void setActive(boolean v) {
		active = v;
	}
	
	public static boolean isActive() {
		return active;
	}
	
	public static int getOverall() {
		return overallWheat;
	}
}
