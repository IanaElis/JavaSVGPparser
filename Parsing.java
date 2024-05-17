import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parsing implements Parser{
    @Override
    public List<Figures> parseSVG(List<String> fileContent) {
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
        List<Figures> figuresList = new ArrayList<>();
        Figures fig = null;
        for(String str : fileContent) {
            Matcher matcher = p1.matcher(str);
            while (matcher.find()) {
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

                if (type.equals("rect")) {
                    int width=0,height=0,rx=0,ry=0, strokeWidth=0;
                    String fill=null,stroke=null;
                    if (matchW.find())
                    {
                        width = Math.abs(Integer.parseInt(matchW.group(1)));
                    }
                    if (matchH.find())
                    {
                        height = Math.abs(Integer.parseInt(matchH.group(1)));
                    }
                    if (matchRX.find())
                    {
                        rx = Math.abs(Integer.parseInt(matchRX.group(1)));
                    }
                    if (matchRY.find())
                    {
                        ry = Math.abs(Integer.parseInt(matchRY.group(1)));
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
                    fig = new Figures.Builder("rectangle", x, y)
                            .width(width).height(height)
                            .rx(rx).ry(ry)
                            .fill(fill).stroke(stroke).strokeWidth(strokeWidth)
                            .build();
                } else if (type.equals("circle")) {
                    int radius=0,strokeWidth=0;
                    String fill=null,stroke=null;
                    if(matchR.find()){
                        radius = Math.abs(Integer.parseInt(matchR.group(2)));
                    }
                    if (matchF.find()) // <
                    {
                        fill = matchF.group(1);
                    }
                    if (matchS.find()) // <
                    {
                        stroke = matchS.group(1);
                    }
                    if (matchSW.find()) // <
                    {
                        strokeWidth = Integer.parseInt(matchSW.group(1));
                    }
                    fig = new Figures.Builder(type, x, y).radius(radius)
                            .fill(fill).stroke(stroke).strokeWidth(strokeWidth)
                            .build();
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
                    if (matchS.find()) // <
                    {
                        stroke = matchS.group(1);
                    }
                    if (matchSW.find()) // <
                    {
                        strokeWidth = Integer.parseInt(matchSW.group(1));
                    }
                    fig = new Figures.Builder(type, x, y).x2(x2).y2(y2)
                            .stroke(stroke).strokeWidth(strokeWidth)
                            .build();
                }
                if(fig != null){
                    figuresList.add(fig);
                }
            }
        }
        return figuresList;
    }
    @Override
    public Figures parseString(String s) {
        Pattern p1 = Pattern.compile("^(rectangle|circle|line)\\b");
        Pattern createFp = Pattern.compile("\\b(red|yellow|green|blue|black)\\b");
        Pattern createSWp = Pattern.compile("\\b(red|yellow|green|blue|black)\\s*(\\d+)");
        Pattern x2p = Pattern.compile("\\b(x2)\\s*=\\s*\"(\\d+)\"");
        Pattern y2p = Pattern.compile("\\b(y2)\\s*=\\s*\"(\\d+)\"");
        Matcher matcher = p1.matcher(s);

        Figures fig = null;

        if (matcher.find()) {
            String type = matcher.group();

            Matcher mFp = createFp.matcher(s);
            Matcher mSWp = createSWp.matcher(s);
            Matcher matchX2 = x2p.matcher(s);
            Matcher matchY2 = y2p.matcher(s);
            Scanner scanner = new Scanner(s);
            scanner.useDelimiter(" ");
            scanner.next();
            int x, y;

            x = Math.abs(Integer.parseInt(scanner.next()));
            y = Math.abs(Integer.parseInt(scanner.next()));

            if (type.equals("rectangle")) {
                int width = 0, height = 0, rx = 0, ry = 0, strokeWidth=0;
                String fill = null, stroke = null;
                if (scanner.hasNextInt()) {
                    width = Math.abs(scanner.nextInt());
                }
                if (scanner.hasNextInt())
                {
                    height = Math.abs(scanner.nextInt());
                }
                if (width == 0 || height == 0) {
                    System.out.println("The rectangle must have width and height");
                    return null;
                }

                if (scanner.hasNext() && mFp.find())
                {
                    fill = mFp.group(1);
                    //scanner.next();
                }

                //if(!scanner.hasNextInt() && scanner.hasNext()) stroke = scanner.next();

                if (scanner.hasNext() && mSWp.find())
                {
                    stroke = mSWp.group(1);
                    strokeWidth = Integer.parseInt(mSWp.group(2));
                }
                if (scanner.hasNextInt()) // <
                {
                    rx = Math.abs(scanner.nextInt());
                }
                if (scanner.hasNextInt()) // <
                {
                    ry = Math.abs(scanner.nextInt());
                }
                fig = new Figures.Builder("rectangle", x, y)
                        .width(width).height(height)
                        .fill(fill).stroke(stroke).strokeWidth(strokeWidth)
                        .rx(rx).ry(ry)
                        .build();
            } else if (type.equals("circle")) {
                int radius = 0, strokeWidth=0;
                String fill = null, stroke = null;
                if (scanner.hasNextInt()) {
                    radius = Math.abs(scanner.nextInt());
                }
                if(radius == 0) {
                    System.out.println("The circle must have radius");
                    return null;
                }

                if (scanner.hasNext() && mFp.find())
                {
                    fill = mFp.group(1);
                    //scanner.next();
                }

                //if(!scanner.hasNextInt()) stroke = scanner.next();

                if (scanner.hasNext() && mSWp.find())
                {
                    stroke = mSWp.group(1);
                    strokeWidth = Integer.parseInt(mSWp.group(2));
                }
                fig = new Figures.Builder(type, x, y).radius(radius)
                        .fill(fill).stroke(stroke).strokeWidth(strokeWidth)
                        .build();
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
                if (scanner.hasNext() && mSWp.find())
                {
                    stroke = mSWp.group(1);
                    strokeWidth = Integer.parseInt(mSWp.group(2));
                }
                fig = new Figures.Builder(type, x, y).x2(x2).y2(y2)
                        .stroke(stroke).strokeWidth(strokeWidth)
                        .build();
            }
            scanner.close();
        }
        else
            System.out.println("Please check the figure type");
        return fig;
    }
@Override
 public int[] parseTranslation(String[] s){
        int res = 0, res1 = 0;
         Pattern hp = Pattern.compile("\\b(horizontal)\\s*=\\s*(\\d+)");
         Matcher mHp = hp.matcher(Arrays.toString(s));
         if(mHp.find()) res = Integer.parseInt(mHp.group(2));
         else System.out.println("The value of horizontal translation was not found");
        //int res1 = 0;
        Pattern vp = Pattern.compile("\\b(vertical)\\s*=\\s*(\\d+)");
        Matcher mVp = vp.matcher(Arrays.toString(s));
        if(mVp.find()) res1 = Integer.parseInt(mVp.group(2));
        else System.out.println("The value of horizontal translation was not found");
        int[] array = new int[2];
        array[0] = res;
        array[1] = res1;
         return array;
    }
    @Override
    public List<Figures> parseWithin(String[] s, List<Figures> figuresList ){
        String figureType = s[1];
        int x = Math.abs(Integer.parseInt(s[2]));
        int y = Math.abs(Integer.parseInt(s[3]));
        List<Figures> withinShape = new ArrayList<>();
        if(figureType.equals("rectangle")){
            int width = Integer.parseInt(s[4]);
            int height = Integer.parseInt(s[5]);
            for(Figures f:figuresList){
                if(f.getType().equals("rectangle")){
                    if(f.getX()>=x && f.getY()>=y && (f.getX()+f.getWidth())<=(x+width)
                            && (f.getY()+f.getHeight())<=(y+height)) withinShape.add(f);
                }
                if(f.getType().equals("circle")){
                    if(f.getX()>=x && f.getY()>=y && (f.getX()+f.getRadius())<=(x+width)
                            && (f.getY()+f.getRadius())<=(y+height)) withinShape.add(f);
                }
                if(f.getType().equals("line")){
                    if(f.getX()>=x && f.getY()>=y && f.getX2()<=(x+width)
                            && f.getY2()<=(y+height)) withinShape.add(f);
                }
            }
        }
        if(figureType.equals("circle")){
            int r = Integer.parseInt(s[4]);
            for(Figures f:figuresList){
                if(Math.sqrt(Math.pow(f.getX()-x,2)+Math.pow(f.getY()-y,2))<=r) {
                    if (f.getType().equals("rectangle")) {
                        if(Math.sqrt(Math.pow(f.getX()+f.getWidth()-x,2)+Math.pow(f.getY()+f.getHeight()-y,2))<=r)
                            withinShape.add(f);
                    }
                    if (f.getType().equals("circle")){
                        if((f.getX()+f.getRadius())<=r+x && (f.getY()+f.getRadius())<=r+y
                                && Math.abs(f.getX()-f.getRadius())<=Math.abs(x-r) && Math.abs(f.getY()-f.getRadius())<=Math.abs(y-r)) withinShape.add(f);
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
}
