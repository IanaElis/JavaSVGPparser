import java.util.Objects;

public class Figures  {
        private String type;
        private int x;
        private int y;
        private int x2;
        private int y2;
        private String fill;
        private String stroke;
        private int strokeWidth;
        private int width;
        private int height;
        private int rx;
        private int ry;
        private  int radius;

    private Figures(Builder builder) {
        this.type = builder.type;
        this.x = builder.x;;
        this.y = builder.y;
        this.x2 = builder.x2;;
        this.y2 = builder.y2;
        this.fill = builder.fill;
        this.stroke = builder.stroke;
        this.strokeWidth = builder.strokeWidth;
        this.width = builder.width;
        this.height = builder.height;
        this.rx=builder.rx;
        this.ry=builder.ry;
        this.radius=builder.radius;
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
    public int getX2() {
        return x2;
    }
    public int getY2() {
        return y2;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getRadius() {
        return radius;
    }
    public void setX(int x) {
        this.x += x;
    }
    public void setY(int y) {
        this.y += y;
    }
    public void setX2(int x) {
        this.x2 += x;
    }
    public void setY2(int y) {
        this.y2 += y;
    }

    public void display(){
        StringBuilder info = new StringBuilder();
        info.append(type).append(" ");
        info.append(x).append(" ");
        info.append(y).append(" ");
        if(x2 != 0) info.append(x2).append(" ");
        if(y2 != 0) info.append(y2).append(" ");
        if(radius!=0) info.append(radius).append(" ");
        if(width!=0) info.append(width).append(" ");
        if(height!=0) info.append(height).append(" ");
        if(Objects.nonNull(fill)) info.append(fill).append(" ");
        if(Objects.nonNull(stroke)) info.append(stroke).append(" ");
        if(strokeWidth!=0) info.append(strokeWidth).append(" ");
        if(rx!=0) info.append(rx).append(" ");
        if(ry!=0) info.append(ry).append(" ");
        System.out.println(info);
    }

    public String toString(){
        StringBuilder info = new StringBuilder();
        info.append("<");
        if(type.equals("rectangle")) info.append("rect ");
        else if(type.equals("circle")) info.append("circle ");
        else info.append("line");
        if(type.equals("line")) {
            info.append("x1=\"").append(x).append("\" ");
            info.append("y1=\"").append(y).append("\" ");
        }
        info.append("x=\"").append(x).append("\" ");
        info.append("y=\"").append(y).append("\" ");
        if(x2 != 0) info.append("x2=\"").append(x2).append("\" ");
        if(y2 != 0) info.append("y2=\"").append(y2).append("\" ");
        if(radius!=0) info.append("r=\"").append(radius).append("\" ");
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

    public static class Builder{
            private String type;
            private int x;
            private int y;
            private int x2;
            private int y2;
            private String fill;
            private String stroke;
            private int strokeWidth;
            private int width=0;
            private int height=0;
            private int rx=0;
            private int ry=0;
            private  int radius=0;

            public Builder(String type, int x, int y){
                this.type = type;
                this.x = x;
                this.y = y;
            }
            public Builder x2(int x2){
                this.x2 = x2;
                return this;
            }

            public Builder y2(int y2){
                this.y2 = y2;
                return this;
            }
            public Builder fill(String fill){
                this.fill = fill;
                return this;
            }
            public Builder stroke(String stroke){
                this.stroke = stroke;
                return this;
            }
            public Builder strokeWidth(int strokeWidth){
                this.strokeWidth = strokeWidth;
                return this;
            }
            public Builder width(int width){
                this.width = width;
                return  this;
            }
            public Builder height(int height){
                this.height = height;
                return this;
            }
            public Builder rx(int rx){
                this.rx =rx;
                return this;
            }

            public Builder ry(int ry){
                this.ry =ry;
                return this;
            }
            public Builder radius(int radius){
                this.radius =radius;
                return this;
            }
            public Figures build(){
                return new Figures(this);
            }

        }
}
