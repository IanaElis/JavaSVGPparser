import java.util.List;

public interface Parser {
    List<Figure> parseSVG(List<String> fileContent);
    Figure parseUserInput(String s);
    int[] parseTranslation(String[] s);
    List<Figure> parseWithin(String[] s, List<Figure> figuresList);
    int searchEoHeader(List<String> fileContent);
}
