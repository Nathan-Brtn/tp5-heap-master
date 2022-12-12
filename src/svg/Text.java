package svg;

import java.util.Locale;

public class Text extends Tag2{
    private final String s;
    private final double x;
    private final double y;
    private final double fontSize;

    public Text(String s, double x, double y, int fontSize){
        super("text");
        this.s=s;
        this.x=x;
        this.y=y;
        this.fontSize= fontSize;
    }

    public String getParameters() {

        return String.format(Locale.US, "x=\"%f\" y=\"%f\" font-family=\"Verdana\" font-size=\"%f\"", x, y, fontSize);

    }

    @Override
    public String getContent() {
        return s;
    }
}
