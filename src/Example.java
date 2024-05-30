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
			
			if(box.detectClipping(wall1))
			{
				box.stopSimulation();
				box.undoClipping(wall1, 6, false);
				box.startSimluation();
			}
			else if(box.detectClipping(wall2))
			{
				box.stopSimulation();
				box.undoClipping(wall2, 6, false);
				box.startSimluation();
			}
			
			if(box.detectCollisionLeft(wall1))
				box.setHorVelocity(Math.abs(box.getHorVelocity()));
			else if(box.detectCollisionRight(wall2))
				box.setHorVelocity(-Math.abs(box.getHorVelocity()));
			
			Entity.displayEntities(arr, background);
			console.preRefresh(background);
			Thread.sleep(20);
		}
	}
}