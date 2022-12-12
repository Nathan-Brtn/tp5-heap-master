package svg;

public class Translate extends Transform{

	public Translate(double x,double y) {
	
		super(x, y);
	}
	
	@Override
	public String toString() {
	
		return "translate(" + x + " " + y + ")";
	}
}
