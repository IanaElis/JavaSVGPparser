import java.util.Objects;

public class Line implements Figure{
    private String type; // basic shape element type
    private int x1; // the x coordinate of the first point
    private int y1; // the y coordinate of the first point
    private int x2; // the x coordinate of the second point
    private int y2; // the y coordinate of the second point
    private String stroke; // stroke colour
    private int strokeWidth; // stroke width

    public Line(String type, int x1, int y1, int x2, int y2, String stroke,
                int strokeWidth) {
        this.type = type;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.stroke = stroke;
        this.strokeWidth = strokeWidth;
    }
    public String getType() {
        return type;
    }
    public int getX() {
        return x1;
    }
    public int getY() {
        return y1;
    }
    public int getX2() {
        return x2;
    }
    public int getY2() {
        return y2;
    }
    @Override
    public int getWidth(){
        return 0;
    }
    @Override
    public int getHeight() {
        return 0;
    }
    @Override
    public int getRadius() {
        return 0;
    }

    public void setX(int x) {
        this.x1 += x;
    }
    public void setY(int y) {
        this.y1 += y;
    }
    public void setX2(int x) {
        this.x2 += x;
    }
    public void setY2(int y) {
        this.y2 += y;
    }


    @Override
    public void display() {
        StringBuilder info = new StringBuilder();
        info.append(type).append(" ");
        info.append(x1).append(" ");
        info.append(y1).append(" ");
        if(x2 != 0) info.append(x2).append(" ");
        if(y2 != 0) info.append(y2).append(" ");
        if(Objects.nonNull(stroke)) info.append(stroke).append(" ");
        if(strokeWidth!=0) info.append(strokeWidth).append(" ");
        System.out.println(info);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("<");
        info.append("line ");
        info.append("x1=\"").append(x1).append("\" ");
        info.append("y1=\"").append(y1).append("\" ");
        if(x2 != 0) info.append("x2=\"").append(x2).append("\" ");
        if(y2 != 0) info.append("y2=\"").append(y2).append("\" ");
        if(Objects.nonNull(stroke)) info.append("stroke=\"").
                append(stroke).append("\" ");
        if(strokeWidth!=0) info.append("stroke-width=\"").
                append(strokeWidth).append("\" ");
        info.append(" />");
        return info.toString();
    }
}
