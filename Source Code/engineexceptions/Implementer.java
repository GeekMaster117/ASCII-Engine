package engineexceptions;

import java.nio.file.Path;
import engineexceptions.highlevelexceptions.*;
import engineexceptions.lowlevelexceptions.OutOfBoundException;

public abstract class Implementer implements CanvasException,ScreenException{
	public void CheckCanvasDimensionException(int width,int height) throws OutOfBoundException
	{
		if(width < 0)
			throw new OutOfBoundException(width,0,Integer.MAX_VALUE);
		if(height < 0)
			throw new OutOfBoundException(height,0,Integer.MAX_VALUE);
	}
	public void CheckCanvasBoundException(int x,int y,int width,int height) throws OutOfBoundException
	{
		if(x < 0 || x >= width)
			throw new OutOfBoundException(x,0,width);
		if(y < 0 || y >= height)
			throw new OutOfBoundException(y,0,height);
	}
	public void CheckSameClassException(Path TargetPath)
	{
		if(Thread.currentThread().getStackTrace()[3].getClass().equals(TargetPath.getClass()));
	}
}
