package svg;

public abstract class Transform {
	
	protected final double x;
	protected double y;
	
	public Transform(double x, double y) {
		
		this.x = x;
		this.y = y;
	}
	
	public Transform(double x) {
		
		this.x = x;
	}
}