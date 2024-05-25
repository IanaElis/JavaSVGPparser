import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Commands {
    private File file;
    private List<String> fileContent;
    private List<Figures> figuresList;

    public void open(File f){
        file = f;
        fileContent = new ArrayList<>();
        try {
            if(!file.createNewFile()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String str = scanner.nextLine();
                    fileContent.add(str);
                }
                scanner.close();
                Parser parser = new Parsing();
                figuresList = parser.parseSVG(fileContent);
            }
            System.out.println("Successfully opened " + file.getName());
        } catch (Exception e) {
                System.out.println("Exception " + e);
        }
    }

    public void save(){
        try {
            int index=0;
            Pattern beginning = Pattern.compile("<\\bsvg\\b>");
            for(String str : fileContent) {
                Matcher bgn = beginning.matcher(str);
                if(bgn.find()) index = fileContent.indexOf(str);
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(int i=0;i<=index;i++) {
                String str = fileContent.get(i);
                writer.write(str);
                writer.newLine();
            }
            for(Figures fig : figuresList){
                writer.write(fig.toString());
                writer.newLine();
            }
            writer.write("</svg>");
            writer.close();
            System.out.println("Successfully saved " + file.getName());
        } catch (Exception e) {
            System.out.println("Exception "+ e);
        }
    }

    public void saveas(File userFile){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(userFile));
            int index=0;
            Pattern beginning = Pattern.compile("<\\bsvg\\b>");
            for(String str : fileContent) {
                Matcher bgn = beginning.matcher(str);
                if(bgn.find()) index = fileContent.indexOf(str);
            }
            for(int i=0;i<=index;i++) {
                String str = fileContent.get(i);
                writer.write(str);
                writer.newLine();
            }
            for(Figures fig : figuresList){
                writer.write(fig.toString());
                writer.newLine();
            }
            writer.write("</svg>");
            writer.close();
            System.out.println("Successfully saved " + userFile.getName());
        } catch (Exception e) {
            System.out.println("Ops, open the file first");
        }
    }

    public void help(){
        System.out.println("The following commands are supported:");
        System.out.println("open <file>                 opens <file>");
        System.out.println("close                       closes currently opened file");
        System.out.println("save                        saves the currently opened file");
        System.out.println("saveas <file>               saves the currently opened file in <file>");
        System.out.println("create <figure>             creates new figure");
        System.out.println("erase <n>                   erases figure <n>");
        System.out.println("translate [<n>] v=y h=x     translates figure <n> \n" +
                "                            or all the figures, if the number <n> is not specified");
        System.out.println("within <option>             prints all the figures which are located inside the area.\n" +
                "                            The shape of the area can be specified in <option>, either circle or rectangle");
        System.out.println("print                       prints all the figures");
        System.out.println("help                        prints this information");
        System.out.println("exit                        exits the program");
    }
    public void close(){
        fileContent.clear();
        figuresList.clear();
        System.out.println("Successfully closed " + file.getName());
    }

    public void printFigures(){
        System.out.println("Printing");
        if(figuresList.isEmpty()) {
            System.out.println("The file is empty");
        }
        int i=1;
        for(Figures figures : figuresList){
            System.out.print(i + ". ");
            figures.display();
            i++;
        }
    }

    public void createFigure(String s){
        Parser parser = new Parsing();
        Figures fig = parser.parseString(s);
        if(fig!=null){
        figuresList.add(fig);
        System.out.println("Successfully created " + fig.getType() + "(" + figuresList.size() + ")");
        }
    }

    public void eraseFigure(int n){
        if (n > 0 && n <= figuresList.size()) {
            Figures fig = figuresList.get(n - 1);
            figuresList.remove(n - 1);
            System.out.println("Successfully erased " + fig.getType() + "(" + n + ")");
        } else {
            System.out.println("There is no figure with such index");
        }
    }

    public void translateN(String[] s){
        int n = Integer.parseInt(s[1]);
        Parser parser = new Parsing();
        int[] arr = parser.parseTranslation(s);
        //int y = parser.parseTranslationY(s);
        if(arr[0]==0 || arr[1]==0) System.out.println("The values for translation are zeros.");
        if(n > 0 && n <= figuresList.size()){
            Figures fig = figuresList.get(n-1);
            fig.setX(arr[0]);
            fig.setY(arr[1]);
            fig.setX2(arr[0]);
            fig.setY2(arr[1]);
            System.out.println(fig.getType() + "(" + n + ")" + " is successfully translated");
        }
        else {
            System.out.println("There is no figure with such index");
        }
    }

    public void translateAll(String[] s){
        Parser parser = new Parsing();
        int[] arr = parser.parseTranslation(s);
        //int y = parser.parseTranslationY(s);
        if(arr[0]==0 || arr[1]==0) System.out.println("The values for translation are zeros.");
        else {
            for (Figures fig : figuresList) {
                fig.setX(arr[0]);
                fig.setY(arr[1]);
                fig.setX2(arr[0]);
                fig.setY2(arr[1]);
            }
            System.out.println("All figures are successfully translated");
        }
    }

    public void within(String[] s){
        Parser parser = new Parsing();
        List<Figures> withinShape = parser.parseWithin(s,figuresList);
        StringBuilder str = new StringBuilder();
        for(int i=1; i < s.length; i++) {
            str.append(s[i]);
            str.append(" ");
        }
        if(withinShape.isEmpty()) System.out.println("No figures are found within "+ str);
        else {
            int i=1;
            for(Figures f:withinShape){
                System.out.print(i + ". ");
                f.display();
                i++;
            }
        }
    }


}
