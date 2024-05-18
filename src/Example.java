import engine.*;

public class Example {

	public static void main(String[] args) throws InterruptedException {
		Screen console = new Screen();
		Canvas background = new Canvas(100,20);
		while(true)
		{
			for(int i = 0;i < 20;++i)
			{
				background.clearCanvas();
				background.insertXEllipse('*', 50, 10, i, i / 4);
				console.preRefresh(background);
				Thread.sleep(20);
			}
			for(int i = 20;i > 0;--i)
			{
				background.clearCanvas();
				background.insertXEllipse('*', 50, 10, i, i / 4);
				console.preRefresh(background);
				Thread.sleep(20);
			}
		}
	}
}