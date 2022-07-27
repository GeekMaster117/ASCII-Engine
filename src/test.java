import engine.*;

public class test {

	public static void main(String[] args) {
		Screen console = new Screen();
		Canvas background = new Canvas(100,20);
		while(true)
		{
			for(int i = 0;i < 100;i++)
			{
				background.ClearCanvas();
				background.InsertXLine('*',50,10,i,0);
				console.PreRefresh(background);
			}
			for(int i = 0;i < 20;i++)
			{
				background.ClearCanvas();
				background.InsertXLine('*',50,10,99,i);
				console.PreRefresh(background);
			}
			for(int i = 99;i >= 0;i--)
			{
				background.ClearCanvas();
				background.InsertXLine('*',50,10,i,19);
				console.PreRefresh(background);
			}
			for(int i = 19;i >= 0;i--)
			{
				background.ClearCanvas();
				background.InsertXLine('*',50,10,0,i);
				console.PreRefresh(background);
			}
		}
	}
}