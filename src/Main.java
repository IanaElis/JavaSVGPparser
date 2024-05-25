import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner ins = new Scanner(System.in);
        String com ="nothing";
        boolean fileOpened = false;
        Commands commands = new Commands();

        while (!com.equals("exit")) {
            System.out.print("> ");
            com = ins.nextLine();
            String[] s = com.split(" ");

            if (s[0].equals("open")) {
                if(s.length<2) {
                    System.out.println("Please write the name of the file to " +
                            "open");
                    continue;
                }
                commands.open(new File(s[1]));
                fileOpened = true;
            }
            if(fileOpened) {
                if (s[0].equals("save")) {
                    commands.save();
                }
                if(s[0].equals("saveas")){
                    if(s.length<2) {
                        System.out.println("Please write the name of the file " +
                                "to save to");
                        continue;
                    }
                    commands.saveas(new File(s[1]));
                }
                if(s[0].equals("close")) {
                    commands.close();
                    fileOpened = false;
                    continue;
                }
                if (s[0].equals("print")) {
                    commands.printFigures();
                }
                if(s[0].equals("create")){
                    StringBuilder figureString = new StringBuilder();
                    if(s.length<5) {
                        System.out.println("Please write the figure in svg format");
                        continue;
                    }
                    for(int i=1; i < s.length; i++) {
                        figureString.append(s[i]);
                        figureString.append(" ");
                    }
                    commands.createFigure(figureString.toString());
                }
                if(s[0].equals("erase")){
                    if(s.length<2) {
                        System.out.println("Please write the number of figure to " +
                                "erase");
                        continue;
                    }
                    commands.eraseFigure(Integer.parseInt(s[1]));
                }
                if(s[0].equals("translate")){
                    if(s.length>=4){
                        commands.translateN(s);
                    }
                    else if(s.length==3)
                        commands.translateAll(s);
                    else {
                        System.out.println("Please write how do you want to " +
                                "translate the figure(s)");
                        continue;
                    }
                }
                if(s[0].equals("within")){
                    if(s.length>4)
                        commands.within(s);
                    else{
                        System.out.println("Please write the type of the " +
                                "figure and its coordinates");
                        continue;
                    }
                }
            }
            if(!fileOpened && !s[0].equals("exit") && !s[0].equals("help")) {
                System.out.println("The file was not open. Open the file first");
            }
            if(s[0].equals("help")){
                commands.help();
            }
            if(s[0].equals("exit")){
                System.out.println("Exiting the program...");
            }
        }
    }
}
