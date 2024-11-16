import engine.*;

public class Example {

	public static void main(String[] args) throws InterruptedException
	{
		Screen console = new Screen();
		Canvas background = new Canvas(100,20);
		
		Entity box = new Entity();
		Entity wall1 = new Entity();
		Entity wall2 = new Entity();
		Entity[] arr = {box, wall1, wall2};
		
		box.setPosition(50, 10);
		box.setDimensions(10, 5);
		box.setHorVelocity(5);
		box.setPaintChar('*');
		
		wall1.setPosition(0, 0);
		wall1.setDimensions(3, 20);
		wall1.setPaintChar('|');
		
		wall2.setPosition(97, 0);
		wall2.setDimensions(3, 20);
		wall2.setPaintChar('|');
		
		box.startSimluation();
		
		while(true)
		{
			background.clearCanvas();
			
			box.undoClipping(wall1, 6, false);
			box.undoClipping(wall2, 6, false);
			
			box.bounceOf(wall1);
			box.bounceOf(wall2);
			
			Entity.displayEntities(arr, background);
			console.preRefresh(background);
			Thread.sleep(20);
		}
	}
}