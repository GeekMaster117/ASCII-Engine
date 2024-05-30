package engine;

import java.lang.System;
import java.io.IOException;
import java.nio.file.Path;

import engineExceptions.ExceptionChecker;
import engineExceptions.exceptions.IllegalFileException;

public class Screen{
	
	private ExceptionChecker exc = new ExceptionChecker();
	
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
	
	public void display(Canvas cs)
	{
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < cs.getHeight(); ++i)
		{
			for(int j = 0;j < cs.getWidth(); ++j)
				str.append(cs.getData(j, i));
			if(i < cs.getHeight() - 1)
				str.append('\n');
		}
		System.out.print(str);
	}
	
	public void preRefresh(Canvas cs)
	{
		clearConsole();
		display(cs);
	}
	
	public void postRefresh(Canvas cs)
	{
		display(cs);
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
