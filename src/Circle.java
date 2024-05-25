import java.util.Objects;

public class Circle implements Figure{
    private String type; // basic shape element type
    private int x; // x coordinate
    private int y; // y coordinate
    private String fill; // inner colour of the figure
    private String stroke; // stroke colour
    private int strokeWidth; // stroke width
    private int radius; // radius

    public Circle(String type, int x, int y, int radius, String fill,
                  String stroke, int strokeWidth){
        this.type = type;
        this.x = x;
        this.y = y;
        this.fill = fill;
        this.stroke = stroke;
        this.strokeWidth = strokeWidth;
        this.radius = radius;
    }

    public String getType() {
        return type;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getRadius() {
        return radius;
    }
    @Override
    public void setX2(int x) {
    }
    @Override
    public void setY2(int y) {
    }
    public void setX(int x) {
        this.x += x;
    }
    public void setY(int y) {
        this.y += y;
    }
    @Override
    public int getX2() {
        return 0;
    }
    @Override
    public int getY2() {
        return 0;
    }
    @Override
    public int getWidth() {
        return 0;
    }
    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void display() {
        StringBuilder info = new StringBuilder();
        info.append(type).append(" ");
        info.append(x).append(" ");
        info.append(y).append(" ");
        if(radius!=0) info.append(radius).append(" ");
        if(Objects.nonNull(fill)) info.append(fill).append(" ");
        if(Objects.nonNull(stroke)) info.append(stroke).append(" ");
        if(strokeWidth!=0) info.append(strokeWidth).append(" ");
        System.out.println(info);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("<");
        info.append("circle ");
        info.append("x=\"").append(x).append("\" ");
        info.append("y=\"").append(y).append("\" ");
        if(radius!=0) info.append("r=\"").append(radius).append("\" ");
        if(Objects.nonNull(fill)) info.append("fill=\"").append(fill).append("\" ");
        if(Objects.nonNull(stroke)) info.append("stroke=\"").append(stroke).append("\" ");
        if(strokeWidth!=0) info.append("stroke-width=\"").append(strokeWidth).append("\" ");
        info.append(" />");
        return info.toString();
    }
}
