package engineExceptions.checkers;

import engineExceptions.exceptions.OutOfBoundException;

public interface CanvasException {
	public void checkCanvasDimensionException(int width,int height) throws OutOfBoundException;
}
