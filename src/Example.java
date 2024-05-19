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
		box.setHorVelocity(2);
		box.setPaintChar('*');
		
		wall1.setPosition(0, 0);
		wall1.setDimensions(3, 20);
		wall1.setPaintChar('|');
		
		wall2.setPosition(97, 0);
		wall2.setDimensions(3, 20);
		wall2.setPaintChar('|');
		
		while(true)
		{
			background.clearCanvas();
			
			box.simulateEntity();
			
			if(box.detectClipping(wall1))
				box.undoClipping(wall1, 3, false);
			if(box.detectClipping(wall2))
				box.undoClipping(wall2, 3, false);
			
			if(box.detectCollisionLeft(wall1) || box.detectCollisionRight(wall2))
				box.setHorVelocity(-box.getHorVelocity());
			
			Entity.displayEntities(arr, background);
			console.preRefresh(background);
			Thread.sleep(20);
		}
	}
}