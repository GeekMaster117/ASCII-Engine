package engine;

import java.lang.System;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import engineexceptions.ExceptionChecker;

public class Screen{
	ExceptionChecker exc;
	
	public void StartConsole(Path path)
	{
		if(path.toString().endsWith(".class"))
		{
			String str = path.toString();
			str = str.substring(0, str.length() - 6);
			path = Paths.get(str);
		}	
		CheckSameClassException(path);
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
		for(int i = 0;i < obj.height;i++)
		{
			for(int j = 0;j < obj.width;j++)
				System.out.print(obj.data[i][j]);
			System.out.println();
		}
	}
	
	public void PreRefresh(Canvas obj)
	{
		ClearConsole();
		display(obj);
	}
	
	public void PostRefresh(Canvas obj)
	{
		display(obj);
		ClearConsole();
	}
	
	public void ClearConsole()
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
