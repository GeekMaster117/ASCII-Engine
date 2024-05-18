package engineExceptions.checkers;

import java.nio.file.Path;

import engineExceptions.exceptions.IllegalFileException;

public interface ScreenException {
	public void checkSameClassException(Path path) throws IllegalFileException;
}
