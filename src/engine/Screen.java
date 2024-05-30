package engine;

import java.lang.System;
import java.io.IOException;
import java.nio.file.Path;

import engineExceptions.ExceptionChecker;
import engineExceptions.exceptions.IllegalFileException;

public class Screen{
	
	ExceptionChecker exc = new ExceptionChecker();
	
	public void startConsole(Path path)
	{
		try
		{
			exc.checkSameClassException(path);
		}
		catch(IllegalFileException msg)
		{
			try
			{
				Runtime.getRuntime().exec("cmd /c Start cmd.exe /K echo " + msg.getMessage());
			}
			catch(IOException msg1)
			{
				System.out.println(msg1.getMessage());
			}
			System.exit(1);
		}
		try
        {
			Runtime.getRuntime().exec("cmd /c Start cmd.exe /K \"cd " + path.getParent().toString()
					+ " && java " + path.getFileName().toString() + "\"");
        }
        catch (IOException msg)
        {
            System.out.println(msg.getMessage());
            System.exit(1);
        }
	}
	
	public void display(Canvas obj)
	{
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < obj.height; ++i)
		{
			for(int j = 0;j < obj.width; ++j)
				str.append(obj.data[i][j]);
			if(i < obj.height - 1)
				str.append('\n');
		}
		System.out.print(str);
	}
	
	public void preRefresh(Canvas obj)
	{
		clearConsole();
		display(obj);
	}
	
	public void postRefresh(Canvas obj)
	{
		display(obj);
		clearConsole();
	}
	
	public void clearConsole()
	{
		try
		{
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		catch(IOException msg)
		{
			System.out.println(msg.getMessage());
			System.exit(1);
		}
		catch(InterruptedException msg)
		{
			System.out.println(msg.getMessage());
			System.exit(1);
		}
	}
}
