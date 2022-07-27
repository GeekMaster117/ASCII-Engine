package engineexceptions.checkers;

import java.nio.file.Path;

import engineexceptions.exceptions.IllegalFileException;

public interface ScreenException {
	public void CheckSameClassException(Path path) throws IllegalFileException;
}
