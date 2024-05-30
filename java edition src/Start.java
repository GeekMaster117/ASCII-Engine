import engine.Screen;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Start {

	public static void main(String[] args) {
		Screen console = new Screen();
		Path path = Paths.get("bin\\Example");
		console.startConsole(path);
	}

}
