import java.util.List;

public interface Parser {
    List<Figures> parseSVG(List<String> fileContent);
    Figures parseString(String s);
    int[] parseTranslation(String[] s);
    List<Figures> parseWithin(String[] s, List<Figures> figuresList);
}
