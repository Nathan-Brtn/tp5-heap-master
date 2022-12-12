package svg;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class Style { //Modélisation des paramètres de style d'une balise SVG

    private String fill;        //Couleur de remplissage
    private String stroke;      //Couleur du tracé
    private Double strokeWidth; //Épaisseur du trait
    private Double fillOpacity; //Transparence du remplissage
    private final List<Transform> transforms = new LinkedList<>();

    /**
     * Renvoie la représentation du style sous forme d'une chaîne de caractères qui peut être insérée dans une
     * balise SVG.
     *
     * @return représentation du style au format SVG
     */
    public String toSVG() {

        StringJoiner elements = new StringJoiner(" ");
        StringJoiner transformStr = new StringJoiner(" ");
        if (fill != null) {
            elements.add("fill=\"" + fill + "\"");
        }
        if (stroke != null) {
            elements.add("stroke=\"" + stroke + "\"");
        }
        if (strokeWidth != null) {
            elements.add("stroke-width=\"" + strokeWidth + "\"");
        }
        if (fillOpacity != null) {
            elements.add("fill-opacity=\"" + fillOpacity + "\"");
        }
        if (!transforms.isEmpty()) {
            for (Transform transform: transforms) {
                transformStr.add(transform.toString());
            }
            elements.add("transform=\"" + transformStr + "\"");
        }
        
        return elements.toString();
    }
    
    public void addTransform(Transform transform) {
    
        transforms.add(transform);
    }
    
    public String getFill() {
        return fill;
    }
    
    public String getStroke() {
        return stroke;
    }
    
    public Double getStrokeWidth() {
        return strokeWidth;
    }
    
    public Double getFillOpacity() {
        return fillOpacity;
    }
    
    public void setFill(String fill) {
        this.fill = fill;
    }
    
    public void setStroke(String stroke) {
        this.stroke = stroke;
    }
    
    public void setStrokeWidth(Double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
    
    public void setFillOpacity(Double fillOpacity) {
        this.fillOpacity = fillOpacity;
    }
}
