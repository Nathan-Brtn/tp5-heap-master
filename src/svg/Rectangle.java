package svg;
import java.util.Locale;

public class Rectangle extends Tag2 {

    private final double x;
    private final double y;
    private final double width;
    private final double height;
    private double rx;
    private double ry;

    public Rectangle(double x, double y, double width, double height) {
        
        super("rect");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public Rectangle(double x, double y, double width, double height, double rx, double ry) {
        
        super("rect");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rx = rx;
        this.ry = ry;
    }
    
    @Override
    public String getParameters() {
        
        if (rx == 0 && ry == 0 ) {
            return String.format(Locale.US, "x=\"%f\" y=\"%f\" width=\"%f\" height=\"%f\"", x, y, width, height);
        }
        else {
            return String.format(Locale.US, "x=\"%f\" y=\"%f\" width=\"%f\" height=\"%f\" rx=\"%f\" ry=\"%f\"", x, y, width, height, rx, ry);
        }
    }
}