package engineExceptions.checkers;

import java.nio.file.Path;

import engineExceptions.exceptions.IllegalFileException;

public interface ScreenException {
	public void CheckSameClassException(Path path) throws IllegalFileException;
}
