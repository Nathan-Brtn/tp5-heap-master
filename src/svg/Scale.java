package svg;
public class Scale extends Transform {
	
	public Scale(double x, double y) {
		
		super(x, y);
	}
	
	@Override
	public String toString() {
		
		return "scale(" + x + " " + y + ")";
	}
}