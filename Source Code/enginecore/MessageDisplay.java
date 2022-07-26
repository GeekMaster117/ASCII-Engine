package enginecore;

import java.lang.System;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MessageDisplay {
	
	public static Path path = Paths.get(System.getProperty("java.class.path"));
	
	public static void main(String[] args)
	{
		System.out.println(args[0]);
	}
	
}
