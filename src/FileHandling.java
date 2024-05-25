import java.io.File;

public interface FileHandling {
    void open(File f);
    void save();
    void saveas(File userFile);
    void help();
}
