import engine.Screen;
import java.nio.file.Path;
import java.nio.file.Paths;

public class start {

	public static void main(String[] args) {
		Screen console = new Screen();
		Path path = Paths.get("D:\\Projects\\ASCII Engine JAVA Edition\\ASCII Engine JAVA Edition\\bin\\test");
		console.StartConsole(path);
	}

}
