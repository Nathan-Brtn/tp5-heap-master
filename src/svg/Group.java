package svg;

public class Group extends Tag2 {
    
    private final SVG svg;
    
    public Group() {

        super("g");
        this.svg = new SVG("g");
    }

    public void add(Tag2 t) {
        
        svg.add(t);
    }
    
    @Override
    public String getContent() {
    
        return svg.getContent();
    }
}
