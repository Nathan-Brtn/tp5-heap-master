package svg;

public class Rotate extends Transform {

	private final double a;
	
	public Rotate(double a, double x, double y) {
	
		super(x, y);
		this.a = a;
	}
	
	@Override
	public String toString() {
		
		return "rotate(" + a + " " + x + " " + y + ")";
	}
}