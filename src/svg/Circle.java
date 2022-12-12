package svg;
import java.util.Locale;

public class Circle extends Tag2 {
    
    private final double cx;
    private final double cy;
    private final double r;

    public Circle(double cx, double cy, double r) {
        
        super("circle");
        this.cx = cx;
        this.cy = cy;
        this.r = r;
    }

    @Override
    public String getParameters() {

        return String.format(Locale.US, "cx=\"%f\" cy=\"%f\" r=\"%f\"", cx, cy, r);
    }
}
