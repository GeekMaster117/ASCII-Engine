package engineexceptions.highlevelexceptions;

import engineexceptions.lowlevelexceptions.OutOfBoundException;

public interface CanvasException {
	public void CheckCanvasDimensionException(int width,int height) throws OutOfBoundException;
	public void CheckCanvasBoundException(int x,int y,int width,int height) throws OutOfBoundException;
}
