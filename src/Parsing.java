import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parsing implements Parser{
    @Override // method to parse SVG files
    public List<Figure> parseSVG(List<String> fileContent) {
        Pattern p1 = Pattern.compile("<[^<>]*?\\b(rect|circle|line)\\b[^<>]*>");
        Pattern xp = Pattern.compile("\\b(x|cx|x1)\\s*=\\s*\"(\\d+)\"");
        Pattern yp = Pattern.compile("\\b(y|cy|y1)\\s*=\\s*\"(\\d+)\"");
        Pattern x2p = Pattern.compile("\\b(x2)\\s*=\\s*\"(\\d+)\"");
        Pattern y2p = Pattern.compile("\\b(y2)\\s*=\\s*\"(\\d+)\"");
        Pattern wp = Pattern.compile("\\bwidth\\s*=\\s*\"([^\"]*)\"");
        Pattern hp = Pattern.compile("\\bheight\\s*=\\s*\"([^\"]*)\"");
        Pattern fp = Pattern.compile("\\bfill\\s*=\\s*\"([^\"]*)\"");
        Pattern sp = Pattern.compile("\\bstroke\\s*=\\s*\"([^\"]*)\"");
        Pattern swp = Pattern.compile("\\bstroke-width\\s*=\\s*\"(\\d+)\"");
        Pattern rxp = Pattern.compile("\\b(rx)\\s*=\\s*\"(\\d+)\"");
        Pattern ryp = Pattern.compile("\\b(ry)\\s*=\\s*\"(\\d+)\"");
        Pattern rp = Pattern.compile("\\b(r)\\s*=\\s*\"(\\d+)\"");

        List<Figure> figuresList = new ArrayList<>();
        for(String str : fileContent) {
            Figure fig = null;
            Matcher matcher = p1.matcher(str);
            if (matcher.find()) {
                int x=0,y=0;
                String tag = matcher.group(0);
                String type = matcher.group(1);

                Matcher matchX = xp.matcher(tag);
                Matcher matchY = yp.matcher(tag);
                Matcher matchX2 = x2p.matcher(tag);
                Matcher matchY2 = y2p.matcher(tag);
                Matcher matchW = wp.matcher(tag);
                Matcher matchH = hp.matcher(tag);
                Matcher matchF = fp.matcher(tag);
                Matcher matchS = sp.matcher(tag);
                Matcher matchSW = swp.matcher(tag);
                Matcher matchRX = rxp.matcher(tag);
                Matcher matchRY = ryp.matcher(tag);
                Matcher matchR = rp.matcher(tag);

                if(matchX.find()){
                    x = Math.abs(Integer.parseInt(matchX.group(2)));
                }
                if(matchY.find()){
                    y = Math.abs(Integer.parseInt(matchY.group(2)));
                }

                if(type.equals("rect")) {
                    int width=0,height=0,rx=0,ry=0, strokeWidth=0;
                    String fill=null,stroke=null;
                    if (matchW.find())
                    {
                        width = Integer.parseInt(matchW.group(1));
                    }
                    if (matchH.find())
                    {
                        height = Integer.parseInt(matchH.group(1));
                    }
                    if (matchRX.find())
                    {
                        rx = Integer.parseInt(matchRX.group(1));
                    }
                    if (matchRY.find())
                    {
                        ry = Integer.parseInt(matchRY.group(1));
                    }
                    if(width <= 0 || height <= 0 ) {
                        System.out.println("The width and the height of " +
                                "the rectangle must be positive values.");
                        return null;
                    }
                    if (matchF.find())
                    {
                        fill = matchF.group(1);
                    }
                    if (matchS.find())
                    {
                        stroke = matchS.group(1);
                    }
                    if (matchSW.find())
                    {
                        strokeWidth = Integer.parseInt(matchSW.group(1));
                    }
                    fig = new Rectangle("rectangle", x, y,width, height,
                            rx, ry, fill, stroke, strokeWidth);
                } else if (type.equals("circle")) {
                    int radius=0,strokeWidth=0;
                    String fill=null,stroke=null;
                    if(matchR.find()){
                        radius = Integer.parseInt(matchR.group(2));
                    }
                    if(radius <= 0){
                        System.out.println("The radius of the circle must be " +
                                "a positive value.");
                        return null;
                    }
                    if (matchF.find())
                    {
                        fill = matchF.group(1);
                    }
                    if (matchS.find())
                    {
                        stroke = matchS.group(1);
                    }
                    if (matchSW.find())
                    {
                        strokeWidth = Integer.parseInt(matchSW.group(1));
                    }
                    fig = new Circle(type, x, y, radius, fill, stroke,
                            strokeWidth);
                }
                else if(type.equals("line")){
                    int x2 = 0, y2=0, strokeWidth=0;
                    String stroke = null;
                    if(matchX2.find()){
                        x2 = Math.abs(Integer.parseInt(matchX2.group(2)));
                    }
                    if(matchY2.find()){
                        y2 = Math.abs(Integer.parseInt(matchY2.group(2)));
                    }
                    if (matchS.find())
                    {
                        stroke = matchS.group(1);
                    }
                    if (matchSW.find())
                    {
                        strokeWidth = Integer.parseInt(matchSW.group(1));
                    }
                    fig = new Line(type, x, y, x2, y2, stroke, strokeWidth);
                }
                if(fig != null){
                    figuresList.add(fig);
                }
            }
        }
        return figuresList;
    }
    @Override
    public Figure parseUserInput(String s) {
        Pattern p1 = Pattern.compile("^(rectangle|circle|line)\\b");
        Pattern colour = Pattern.compile("\\b(red|yellow|green|blue|black)\\b");
        Matcher matcher = p1.matcher(s);
        Figure fig = null;

        if (matcher.find()) {
            String type = matcher.group();

            Matcher mColour = colour.matcher(s);
            Scanner scanner = new Scanner(s);
            scanner.useDelimiter(" ");
            scanner.next();
            int x, y;

            x = Math.abs(scanner.nextInt());
            y = Math.abs(scanner.nextInt());

            if(type.equals("rectangle")) {
                int width = 0, height = 0, rx = 0, ry = 0, strokeWidth=0;
                String fill = null, stroke = null;
                if (scanner.hasNextInt()) {
                    width = scanner.nextInt();
                }
                if (scanner.hasNextInt())
                {
                    height = scanner.nextInt();
                }
                if (scanner.hasNextInt())
                {
                    rx = scanner.nextInt();
                }
                if (scanner.hasNextInt())
                {
                    ry = scanner.nextInt();
                }
                if (width <= 0 || height <= 0) {
                    System.out.println("The width and the height of the " +
                            "rectangle must be positive values.");
                    return null;
                }
                if (scanner.hasNext() && mColour.find())
                {
                    fill = mColour.group(1);
                }
                if (scanner.hasNext() && mColour.find())
                {
                    stroke = mColour.group(1);
                }
                if(scanner.hasNextInt()){
                    strokeWidth = scanner.nextInt();
                }
                fig = new Rectangle("rectangle", x, y,width, height,
                        rx, ry, fill, stroke, strokeWidth);
            } else if (type.equals("circle")) {
                int radius = 0, strokeWidth=0;
                String fill = null, stroke = null;
                if (scanner.hasNextInt()) {
                    radius = scanner.nextInt();
                }
                if(radius <= 0){
                    System.out.println("The radius of the circle must " +
                            "be a positive value.");
                    return null;
                }
                if (scanner.hasNext() && mColour.find())
                {
                    fill = mColour.group(1);
                }
                if (scanner.hasNext() && mColour.find())
                {
                    stroke = mColour.group(1);
                }
                if(scanner.hasNextInt()){
                    strokeWidth = scanner.nextInt();
                }
                fig = new Circle(type, x, y, radius, fill, stroke,
                        strokeWidth);
            }
            else if (type.equals("line")){
                int x2 = 0, y2=0, strokeWidth=0;
                String stroke = null;
                if (scanner.hasNextInt()) {
                    x2 = Math.abs(scanner.nextInt());
                }
                if (scanner.hasNextInt()) {
                    y2 = Math.abs(scanner.nextInt());
                }
                if (scanner.hasNext() && mColour.find())
                {
                    stroke = mColour.group(1);
                }
                if(scanner.hasNextInt()){
                    strokeWidth = scanner.nextInt();
                }
                fig = new Line(type, x, y, x2, y2, stroke, strokeWidth);
            }
            scanner.close();
        }
        else
            System.out.println("Please check the figure type");
        return fig;
    }
    @Override
    public int[] parseTranslation(String[] s){
        int hor = 0, ver = 0;
        Pattern hp = Pattern.compile("\\b(horizontal)\\s*=\\s*(\\d+)");
        Pattern vp = Pattern.compile("\\b(vertical)\\s*=\\s*(\\d+)");
        Matcher mHp = hp.matcher(Arrays.toString(s));
        Matcher mVp = vp.matcher(Arrays.toString(s));
        if(mHp.find()) hor = Integer.parseInt(mHp.group(2));
        else System.out.println("The value of horizontal translation was not found");
        if(mVp.find()) ver = Integer.parseInt(mVp.group(2));
        else System.out.println("The value of horizontal translation was not found");
        int[] array = new int[2];
        array[0] = hor;
        array[1] = ver;
        return array;
    }
    @Override
    public List<Figure> parseWithin(String[] s, List<Figure> figuresList ){
        String figureType = s[1];
        int x = Math.abs(Integer.parseInt(s[2]));
        int y = Math.abs(Integer.parseInt(s[3]));
        List<Figure> withinShape = new ArrayList<>();
        if(figureType.equals("rectangle")){
            int width = Integer.parseInt(s[4]);
            int height = Integer.parseInt(s[5]);

            if (width <= 0 || height <= 0) {
                System.out.println("The width and the height of the rectangle" +
                        " must be positive values.");
                return null;
            }

            for(Figure f:figuresList){
                if(f.getType().equals("rectangle")){
                    if(f.getX()>=x && f.getY()>=y && (f.getX()+f.getWidth())<=(x+width)
                            && (f.getY()+f.getHeight())<=(y+height))
                        withinShape.add(f);
                }
                if(f.getType().equals("circle")){
                    if(f.getX()>=x && f.getY()>=y && (f.getX()+f.getRadius())<=(x+width)
                            && (f.getY()+f.getRadius())<=(y+height))
                        withinShape.add(f);
                }
                if(f.getType().equals("line")){
                    if(f.getX()>=x && f.getY()>=y && f.getX2()<=(x+width)
                            && f.getY2()<=(y+height))
                        withinShape.add(f);
                }
            }
        }
        if(figureType.equals("circle")){
            int r = Integer.parseInt(s[4]);
            if(r <= 0){
                System.out.println("The radius of the circle must be a positive value.");
                return null;
            }
            for(Figure f:figuresList){
                if(Math.sqrt(Math.pow(f.getX()-x,2)+Math.pow(f.getY()-y,2))<=r) {
                    if (f.getType().equals("rectangle")) {
                        if(Math.sqrt(Math.pow(f.getX()+f.getWidth()-x,2)+
                                Math.pow(f.getY()+ f.getHeight()-y,2))<=r)
                            withinShape.add(f);
                    }
                    if (f.getType().equals("circle")){
                        if((f.getX()+f.getRadius())<=r+x && (f.getY()+f.getRadius())<=r+y
                                && Math.abs(f.getX()-f.getRadius())<=Math.abs(x-r) &&
                                Math.abs(f.getY()-f.getRadius())<=Math.abs(y-r))
                            withinShape.add(f);
                    }
                    if (f.getType().equals("line")) {
                        if(Math.sqrt(Math.pow(f.getX2()-x,2)+Math.pow(f.getY2()-y,2))<=r)
                            withinShape.add(f);
                    }
                }
            }
        }
        return withinShape;
    }
    @Override
    public int searchEoHeader(List<String> fileContent) {
        int index=0;
        Pattern beginning = Pattern.compile("<\\bsvg\\b>");
        for(String str : fileContent) {
            Matcher bgn = beginning.matcher(str);
            if(bgn.find()) index = fileContent.indexOf(str);
        }
        return index;
    }
}
