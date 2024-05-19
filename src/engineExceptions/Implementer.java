package engineExceptions;

import java.nio.file.Path;

import engineExceptions.checkers.*;
import engineExceptions.exceptions.*;

public abstract class Implementer implements CanvasException, ScreenException
{
	public void checkCanvasDimensionException(int width,int height) throws OutOfBoundException
	{
		if(width < 1)
			throw new OutOfBoundException(width,1,Integer.MAX_VALUE);
		if(height < 1)
			throw new OutOfBoundException(height,1,Integer.MAX_VALUE);
	}
	
	public void checkSameClassException(Path TargetPath) throws IllegalFileException
	{
		String TargetClass = TargetPath.getFileName().toString();
		String CallerClass = Thread.currentThread().getStackTrace()[3].getFileName();
		if(CallerClass.endsWith(".java"))
			CallerClass = CallerClass.substring(0, CallerClass.length() - 5);
		else if(CallerClass.endsWith(".class"))
			CallerClass = CallerClass.substring(0, CallerClass.length() - 6);
		if(TargetClass.equals(CallerClass))
			throw new IllegalFileException(TargetClass, "Any file other than \'" + TargetClass + "\'");
	}
}
