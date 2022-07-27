package engine;

import java.lang.System;
import java.lang.Math;
import java.util.Arrays;

import engineExceptions.ExceptionChecker;
import engineExceptions.exceptions.OutOfBoundException;

import java.io.IOException;

public class Canvas {
	char data[][];
	int width,height;
	ExceptionChecker exc;
	
	public Canvas(int width,int height)
	{
		exc = new ExceptionChecker();
		try
		{
			exc.CheckCanvasDimensionException(width,height);
		}
		catch(OutOfBoundException msg)
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
		this.width = width;
		this.height = height;
		this.data = new char[height][width];
		ClearCanvas();
	}
	
	public void ChangeDimensions(int width,int height)
	{
		try
		{
			exc.CheckCanvasDimensionException(width,height);
		}
		catch(OutOfBoundException msg)
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
		char temp[][] = new char[this.height][this.width];
		temp = Arrays.copyOf(data, this.height);
		this.width = width;
		this.height = height;
		this.data = new char[this.height][this.width];
		for(int i = 0;i < this.height;i++)
			for(int j = 0;j < this.width;j++)
			{
				if(i >= temp.length || j >= temp[0].length)
					this.data[i][j] = ' ';
				else
					this.data[i][j] = temp[i][j];
			}
	}
	
	public void ClearCanvas()
	{
		for(int i = 0;i < data.length;i++)
			for(int j = 0;j < data[i].length;j++)
				this.data[i][j] = ' ';
	}
	
	public void FillCanvas(char PaintChar)
	{
		for(int i = 0;i < this.height;i++)
			for(int j = 0;j < this.width;j++)
				this.data[i][j] = PaintChar;
	}
	
	public void InsertChar(char PaintChar,int x,int y)
	{
		try
		{
			exc.CheckCanvasBoundException(x,y,width,height);
		}
		catch(OutOfBoundException msg)
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
		this.data[y][x] = PaintChar;
	}
	
	public void InsertXLine(char PaintChar,int x1,int y1,int x2,int y2)
	{
		try
		{
			exc.CheckCanvasBoundException(x1,y1,width,height);
			exc.CheckCanvasBoundException(x2,y2,width,height);
		}
		catch(OutOfBoundException msg)
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
		if(x1 == x2 && y1 == y2)
		{
			this.data[y1][x1] = PaintChar;
		}
		else if(x1 == x2)
		{
			if(y1 < y2)
				for(int i = y1;i <= y2;i++)this.data[i][x1] = PaintChar;
			else
				for(int i = y2;i <= y1;i++)this.data[i][x1] = PaintChar;
		}
		else if(y1 == y2)
		{
			if(x1 < x2)
				for(int i = x1;i <= x2;i++)this.data[y1][i] = PaintChar;
			else
				for(int i = x2;i <= x1;i++)this.data[y1][i] = PaintChar;
		}
		else
		{
			double slope = (double)(y1 - y2)/(x1 - x2),y_point;
			this.data[y1][x1] = PaintChar;
			this.data[y2][x2] = PaintChar;
			if(x1 < x2)for(int i = x1 + 1;i < x2;i++)
			{
				y_point = (double)(slope * (i - x1)) + y1;
				if(y_point - Math.floor(y_point) < 0.5)y_point = Math.floor(y_point);
				else y_point = Math.ceil(y_point);
				this.data[(int)y_point][i] = PaintChar;
			}
			else for(int i = x2 + 1;i < x1;i++)
			{
				y_point = (double)(slope * (i - x1)) + y1;
				if(y_point - Math.floor(y_point) < 0.5)y_point = Math.floor(y_point);
				else y_point = Math.ceil(y_point);
				this.data[(int)y_point][i] = PaintChar;
			}
		}
	}
}