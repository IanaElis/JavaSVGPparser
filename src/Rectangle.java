import java.util.Objects;

public class Rectangle implements Figure{
    private String type; // basic shape element type
    private int x; // x coordinate
    private int y; // y coordinate
    private String fill; // inner colour of the figure
    private String stroke; // stroke colour
    private int strokeWidth; // stroke width
    private int width; // width of the rectangle
    private int height; // height of the rectangle
    private int rx; // property for corners rounding
    private int ry; //property for corners rounding

    public Rectangle(String type, int x, int y, int width, int height,
                     int rx, int ry, String fill, String stroke,
                     int strokeWidth){
        this.type = type;
        this.x = x;
        this.y = y;
        this.fill = fill;
        this.stroke = stroke;
        this.strokeWidth = strokeWidth;
        this.width = width;
        this.height = height;
        this.rx = rx;
        this.ry = ry;
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
    @Override
    public int getX2() {
        return 0;
    }
    @Override
    public int getY2() {
        return 0;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    @Override
    public int getRadius() {
        return 0;
    }
    public void setX(int x) {
        this.x += x;
    }
    public void setY(int y) {
        this.y += y;
    }
    @Override
    public void setX2(int x) {}
    @Override
    public void setY2(int y) {}

    @Override
    public void display() {
        StringBuilder info = new StringBuilder();
        info.append(type).append(" ");
        info.append(x).append(" ");
        info.append(y).append(" ");
        if(width!=0) info.append(width).append(" ");
        if(height!=0) info.append(height).append(" ");
        if(Objects.nonNull(fill)) info.append(fill).append(" ");
        if(Objects.nonNull(stroke)) info.append(stroke).append(" ");
        if(strokeWidth!=0) info.append(strokeWidth).append(" ");
        if(rx!=0) info.append(rx).append(" ");
        if(ry!=0) info.append(ry).append(" ");
        System.out.println(info);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("<");
        info.append("rect ");
        info.append("x=\"").append(x).append("\" ");
        info.append("y=\"").append(y).append("\" ");
        if(width!=0) info.append("width=\"").append(width).append("\" ");
        if(height!=0) info.append("height=\"").append(height).append("\" ");
        if(Objects.nonNull(fill)) info.append("fill=\"").append(fill).append("\" ");
        if(Objects.nonNull(stroke)) info.append("stroke=\"").append(stroke).append("\" ");
        if(strokeWidth!=0) info.append("stroke-width=\"").append(strokeWidth).append("\" ");
        if(rx!=0) info.append("rx=\"").append(rx).append("\" ");
        if(ry!=0) info.append("ry=\"").append(ry).append("\" ");
        info.append(" />");
        return info.toString();
    }
}
