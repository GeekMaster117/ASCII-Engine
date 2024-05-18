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
			exc.checkCanvasDimensionException(width,height);
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
		clearCanvas();
	}
	
	public void changeDimensions(int width,int height)
	{
		try
		{
			exc.checkCanvasDimensionException(width,height);
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
	
	public void clearCanvas()
	{
		for(int i = 0;i < data.length;i++)
			for(int j = 0;j < data[i].length;j++)
				this.data[i][j] = ' ';
	}
	
	public void fillCanvas(char paintChar)
	{
		for(int i = 0;i < this.height;i++)
			for(int j = 0;j < this.width;j++)
				this.data[i][j] = paintChar;
	}
	
	public void insertChar(char paintChar,int x,int y)
	{
		try
		{
			exc.checkCanvasBoundException(x,y,width,height);
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
		this.data[y][x] = paintChar;
	}
	
	public void insertString(String str, int x, int y, int boxWidth, int boxHeight)
	{
		try
		{
			exc.checkCanvasBoundException(x, y, width, height);
			exc.checkCanvasBoundException(x + boxWidth - 1, y + boxHeight - 1, width, height);
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
		int pointerX = x, pointerY = y;
		for(char c: str.toCharArray())
		{
			insertChar(c, pointerX, pointerY);
			if(pointerX >= x + boxWidth - 1)
			{
				pointerX = x;
				++pointerY;
			}
			else
				++pointerX;
			if(pointerY > y + boxHeight - 1)
				break;
		}
	}
	
	public void insertXLine(char paintChar,int x1,int y1,int x2,int y2)
	{
		try
		{
			exc.checkCanvasBoundException(x1,y1,width,height);
			exc.checkCanvasBoundException(x2,y2,width,height);
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
			this.data[y1][x1] = paintChar;
		}
		else if(x1 == x2)
		{
			if(y1 < y2)
				for(int i = y1;i <= y2;i++)this.data[i][x1] = paintChar;
			else
				for(int i = y2;i <= y1;i++)this.data[i][x1] = paintChar;
		}
		else if(y1 == y2)
		{
			if(x1 < x2)
				for(int i = x1;i <= x2;i++)this.data[y1][i] = paintChar;
			else
				for(int i = x2;i <= x1;i++)this.data[y1][i] = paintChar;
		}
		else
		{
			double slope = (double)(y1 - y2)/(x1 - x2),y_point;
			this.data[y1][x1] = paintChar;
			this.data[y2][x2] = paintChar;
			if(x1 < x2)for(int i = x1 + 1;i < x2;i++)
			{
				y_point = (double)(slope * (i - x1)) + y1;
				if(y_point - Math.floor(y_point) < 0.5)y_point = Math.floor(y_point);
				else y_point = Math.ceil(y_point);
				this.data[(int)y_point][i] = paintChar;
			}
			else for(int i = x2 + 1;i < x1;i++)
			{
				y_point = (double)(slope * (i - x1)) + y1;
				if(y_point - Math.floor(y_point) < 0.5)y_point = Math.floor(y_point);
				else y_point = Math.ceil(y_point);
				this.data[(int)y_point][i] = paintChar;
			}
		}
	}
	
	public void insertYLine(char paintChar,int x1,int y1,int x2,int y2)
	{
		try
		{
			exc.checkCanvasBoundException(x1,y1,width,height);
			exc.checkCanvasBoundException(x2,y2,width,height);
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
			this.data[y1][x1] = paintChar;
		}
		else if(x1 == x2)
		{
			if(y1 < y2)
				for(int i = y1;i <= y2;i++)this.data[i][x1] = paintChar;
			else
				for(int i = y2;i <= y1;i++)this.data[i][x1] = paintChar;
		}
		else if(y1 == y2)
		{
			if(x1 < x2)
				for(int i = x1;i <= x2;i++)this.data[y1][i] = paintChar;
			else
				for(int i = x2;i <= x1;i++)this.data[y1][i] = paintChar;
		}
		else
		{
			double slope = (double)(y1 - y2)/(x1 - x2), x_point;
			this.data[y1][x1] = paintChar;
			this.data[y2][x2] = paintChar;
			if(y1 < y2)for(int i = y1 + 1;i < y2;i++)
			{
				x_point = (double)((i - y1) / slope) + x1;
				if(x_point - Math.floor(x_point) < 0.5)x_point = Math.floor(x_point);
				else x_point = Math.ceil(x_point);
				this.data[i][(int)x_point] = paintChar;
			}
			else for(int i = y2 + 1;i < y1;i++)
			{
				x_point = (double)((i - y1) / slope) + x1;
				if(x_point - Math.floor(x_point) < 0.5)x_point = Math.floor(x_point);
				else x_point = Math.ceil(x_point);
				this.data[i][(int)x_point] = paintChar;
			}
		}
	}
	
	public void insertRect(char paintChar, int x, int y, int rectWidth, int rectHeight)
	{
		try
		{
			exc.checkCanvasBoundException(x,y,width,height);
			exc.checkCanvasBoundException(x + rectWidth - 1, y + rectHeight - 1, width, height);
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
		--rectWidth;
		--rectHeight;
		insertYLine(paintChar, x, y, x + rectWidth, y);
		insertYLine(paintChar, x + rectWidth, y, x + rectWidth, y + rectHeight);
		insertYLine(paintChar, x, y + rectHeight, x + rectWidth, y + rectHeight);
		insertYLine(paintChar, x, y, x, y + rectHeight);
	}
	
	public void insertSolidRect(char paintChar, int x, int y, int rectWidth, int rectHeight)
	{
		try
		{
			exc.checkCanvasBoundException(x,y,width,height);
			exc.checkCanvasBoundException(x + rectWidth - 1, y + rectHeight - 1, width, height);
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
		--rectWidth;
		--rectHeight;
		for(int i = x; i <= x + rectWidth; ++i)
			insertYLine(paintChar, i, y, i, y + rectHeight);
	}
	
	public void insertXEllipse(char paintChar, int centerX, int centerY, int majorAxis, int minorAxis)
	{
		try
		{
			exc.checkCanvasBoundException(majorAxis, minorAxis, width, height);
			exc.checkCanvasBoundException(centerX - (majorAxis / 2), centerY, width, height);
			exc.checkCanvasBoundException(centerX, centerY - (minorAxis / 2), width, height);
			exc.checkCanvasBoundException(centerX + (majorAxis / 2), centerY, width, height);
			exc.checkCanvasBoundException(centerX, centerY + (minorAxis / 2), width, height);
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
		for(int i = centerX - majorAxis; i <= centerX + majorAxis; ++i)
		{
			double equation = Math.sqrt((1 - (Math.pow(i - centerX, 2) / Math.pow(majorAxis, 2))) * Math.pow(minorAxis, 2));
			double y1 = centerY + equation;
			double y2 = centerY - equation;
			if(y1 - Math.floor(y1) < 0.5)y1 = Math.floor(y1);
			else y1 = Math.ceil(y1);
			if(y2 - Math.floor(y2) < 0.5)y2 = Math.floor(y2);
			else y2 = Math.ceil(y2);
			this.data[(int)y1][i] = paintChar;
			this.data[(int)y2][i] = paintChar;
		}
	}
	
	public void insertSolidXEllipse(char paintChar, int centerX, int centerY, int majorAxis, int minorAxis)
	{
		try
		{
			exc.checkCanvasBoundException(majorAxis, minorAxis, width, height);
			exc.checkCanvasBoundException(centerX - (majorAxis / 2), centerY, width, height);
			exc.checkCanvasBoundException(centerX, centerY - (minorAxis / 2), width, height);
			exc.checkCanvasBoundException(centerX + (majorAxis / 2), centerY, width, height);
			exc.checkCanvasBoundException(centerX, centerY + (minorAxis / 2), width, height);
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
		for(int i = centerX - majorAxis; i <= centerX + majorAxis; ++i)
		{
			double equation = Math.sqrt((1 - (Math.pow(i - centerX, 2) / Math.pow(majorAxis, 2))) * Math.pow(minorAxis, 2));
			double y1 = centerY + equation;
			double y2 = centerY - equation;
			if(y1 - Math.floor(y1) < 0.5)y1 = Math.floor(y1);
			else y1 = Math.ceil(y1);
			if(y2 - Math.floor(y2) < 0.5)y2 = Math.floor(y2);
			else y2 = Math.ceil(y2);
			for(int j = (int)y2; j <= (int)y1; ++j)
				this.data[j][i] = paintChar;
		}
	}
	
	public void insertYEllipse(char paintChar, int centerX, int centerY, int majorAxis, int minorAxis)
	{
		try
		{
			exc.checkCanvasBoundException(majorAxis, minorAxis, width, height);
			exc.checkCanvasBoundException(centerX - (majorAxis / 2), centerY, width, height);
			exc.checkCanvasBoundException(centerX, centerY - (minorAxis / 2), width, height);
			exc.checkCanvasBoundException(centerX + (majorAxis / 2), centerY, width, height);
			exc.checkCanvasBoundException(centerX, centerY + (minorAxis / 2), width, height);
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
		for(int i = centerY - minorAxis; i <= centerY + minorAxis; ++i)
		{
			double equation = Math.sqrt((1 - (Math.pow(i - centerY, 2) / Math.pow(minorAxis, 2))) * Math.pow(majorAxis, 2));
			double x1 = centerX + equation;
			double x2 = centerX - equation;
			if(x1 - Math.floor(x1) < 0.5)x1 = Math.floor(x1);
			else x1 = Math.ceil(x1);
			if(x2 - Math.floor(x2) < 0.5)x2 = Math.floor(x2);
			else x2 = Math.ceil(x2);
			this.data[i][(int)x1] = paintChar;
			this.data[i][(int)x2] = paintChar;
		}
	}
	
	public void insertSolidYEllipse(char paintChar, int centerX, int centerY, int majorAxis, int minorAxis)
	{
		try
		{
			exc.checkCanvasBoundException(majorAxis, minorAxis, width, height);
			exc.checkCanvasBoundException(centerX - (majorAxis / 2), centerY, width, height);
			exc.checkCanvasBoundException(centerX, centerY - (minorAxis / 2), width, height);
			exc.checkCanvasBoundException(centerX + (majorAxis / 2), centerY, width, height);
			exc.checkCanvasBoundException(centerX, centerY + (minorAxis / 2), width, height);
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
		for(int i = centerY - minorAxis; i <= centerY + minorAxis; ++i)
		{
			double equation = Math.sqrt((1 - (Math.pow(i - centerY, 2) / Math.pow(minorAxis, 2))) * Math.pow(majorAxis, 2));
			double x1 = centerX + equation;
			double x2 = centerX - equation;
			if(x1 - Math.floor(x1) < 0.5)x1 = Math.floor(x1);
			else x1 = Math.ceil(x1);
			if(x2 - Math.floor(x2) < 0.5)x2 = Math.floor(x2);
			else x2 = Math.ceil(x2);
			for(int j = (int)x2; j <= (int)x1; ++j)
				this.data[i][j] = paintChar;
		}
	}
}