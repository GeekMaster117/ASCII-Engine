package engine;

public class Entity
{
	private int width, height;
	private volatile int x, y;
	private volatile double simX, simY;
	private volatile double horVelocity, verVelocity, horAcceleration, verAcceleration;
	private char paintChar;
	
	private long time;
	private Thread entitySimulation = null;
	
	private final Object xLock = new Object();
	private final Object simXLock = new Object();
	private final Object yLock = new Object();
	private final Object simYLock = new Object();
	private final Object horVelocityLock = new Object();
	private final Object verVelocityLock = new Object();
	private final Object horAccelerationLock = new Object();
	private final Object verAccelerationLock = new Object();
	
	public Entity()
	{
		this.setDimensions(1, 1);
		this.setPosition(0, 0);
		this.setVelocity(0.0000, 0.0000);
		this.setAcceleration(0.0000, 0.0000);
	}
	
	public void setDimensions(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public void setPosition(int x, int y)
	{
		synchronized (xLock)
		{
			this.x = x;
		}
		synchronized (simXLock)
		{
			this.simX = x;
		}
		synchronized (yLock)
		{
			this.y = y;
		}
		synchronized (simYLock)
		{
			this.simY = y;
		}
	}
	
	public void setXPosition(int x)
	{
		synchronized (xLock)
		{
			this.x = x;
		}
	}
	
	public void setYPosition(int y)
	{
		synchronized (yLock)
		{
			this.y = y;
		}
	}
	
	private void setSimXPosition(double x)
	{
		synchronized (simXLock)
		{
			this.simX = x;
		}
	}
	
	private void setSimYPosition(double y)
	{
		synchronized (simYLock)
		{
			this.simY = y;
		}
	}
	
	public int getXPosition()
	{
		synchronized (xLock)
		{
			return this.x;
		}
	}
	
	public int getYPosition()
	{
		synchronized (yLock)
		{
			return this.y;
		}
	}
	
	private double getSimXPosition()
	{
		synchronized (simXLock)
		{
			return this.simX;
		}
	}
	
	private double getSimYPosition()
	{
		synchronized (simYLock)
		{
			return this.simY;
		}
	}
	
	public void setPaintChar(char paintChar)
	{
		this.paintChar = paintChar;
	}
	
	public char getPaintChar()
	{
		return this.paintChar;
	}
	
	public void setHorVelocity(double horVelocity)
	{
		synchronized (horVelocityLock)
		{
			this.horVelocity = horVelocity;
		}
	}
	
	public double getHorVelocity()
	{
		synchronized (horVelocityLock)
		{
			return this.horVelocity;
		}
	}
	
	public void setVerVelocity(double verVelocity)
	{
		synchronized (verVelocityLock)
		{
			this.verVelocity = verVelocity;
		}
	}
	
	public double getVerVelocity()
	{
		synchronized (verVelocityLock)
		{
			return this.verVelocity;
		}
	}
	
	public void setVelocity(double horVelocity, double verVelocity)
	{
		synchronized (horVelocityLock)
		{
			this.horVelocity = horVelocity;
		}
		synchronized (verVelocityLock)
		{
			this.verVelocity = verVelocity;
		}
	}
	
	public void setHorAcceleration(double horAcceleration)
	{
		synchronized (horAccelerationLock)
		{
			this.horAcceleration = horAcceleration;
		}
	}
	
	public double getHorAcceleration()
	{
		synchronized (horAccelerationLock)
		{
			return this.horAcceleration;
		}
	}
	
	public void setVerAcceleration(double verAcceleration)
	{
		synchronized (verAccelerationLock)
		{
			this.verAcceleration = verAcceleration;
		}
	}
	
	public double getVerAcceleration()
	{
		synchronized (verAccelerationLock)
		{
			return this.verAcceleration;
		}
	}
	
	public void setAcceleration(double horAcceleration, double verAcceleration)
	{
		synchronized (horAccelerationLock)
		{
			this.horAcceleration = horAcceleration;
		}
		synchronized (verAccelerationLock)
		{
			this.verAcceleration = verAcceleration;
		}
	}
	
	public boolean detectCanvasCollision(Canvas cs)
	{
		if(this.getYPosition() != 0)	
			for(int i = this.getXPosition(); i <= this.getXPosition() + this.getWidth() - 1; ++i)
				if(cs.getData(this.getYPosition() - 1, i) != ' ')
					return true;
		if(this.getYPosition() + this.getHeight() - 1 < cs.getHeight() - 1)
			for(int i = this.getXPosition(); i <= this.getXPosition() + this.getWidth() - 1; ++i)
				if(cs.getData(this.getYPosition() + this.getHeight(), i) != ' ')
					return true;
		if(this.getXPosition() != 0)
			for(int i = this.getYPosition(); i <= this.getYPosition() + this.getHeight() - 1; ++i)
				if(cs.getData(i, this.getXPosition() - 1) != ' ')
					return true;
		if(this.getXPosition() + this.getWidth() - 1 < cs.getWidth() - 1)
			for(int i = this.getYPosition(); i <= this.getYPosition() + this.getHeight() - 1; ++i)
				if(cs.getData(i, this.getXPosition() + this.getWidth()) != ' ')
					return true;
		return false;
	}
	
	public boolean detectCollision(Entity en)
	{
		if(en.getXPosition() > this.getXPosition() + this.getWidth())
			return false;
		if(this.getXPosition() > en.getXPosition() + en.getWidth())
			return false;
		if(en.getYPosition() > this.getYPosition() + this.getHeight())
			return false;
		if(this.getYPosition() > en.getYPosition() + en.getHeight())
			return false;
		return true;
	}
	
	public boolean detectCollisionMultiple(Entity[] en)
	{
		for(int i = 0; i < en.length; ++i)
			if(this.detectCollision(en[i]))
				return true;
		return false;
	}
	
	public boolean detectCollisionUp(Entity en)
	{
		if(en.getXPosition() >= this.getXPosition() + this.getWidth())
			return false;
		if(this.getXPosition() >= en.getXPosition() + en.getWidth())
			return false;
		if(this.getYPosition() == en.getYPosition() + en.getHeight())
			return true;
		return false;
	}
	
	public boolean detectCollisionDown(Entity en)
	{
		if(en.getXPosition() >= this.getXPosition() + this.getWidth())
			return false;
		if(this.getXPosition() >= en.getXPosition() + en.getWidth())
			return false;
		if(en.getYPosition() == this.getYPosition() + this.getHeight())
			return true;
		return false;
	}
	
	public boolean detectCollisionLeft(Entity en)
	{
		if(en.getYPosition() >= this.getYPosition() + this.getHeight())
			return false;
		if(this.getYPosition() >= en.getYPosition() + en.getHeight())
			return false;
		if(this.getXPosition() == en.getXPosition() + en.getWidth())
			return true;
		return false;
	}
	
	public boolean detectCollisionRight(Entity en)
	{
		if(en.getYPosition() >= this.getYPosition() + this.getHeight())
			return false;
		if(this.getYPosition() >= en.getYPosition() + en.getHeight())
			return false;
		if(en.getXPosition() == this.getXPosition() + this.getWidth())
			return true;
		return false;
	}
	
	public boolean detectClipping(Entity en)
	{
		if(en.getXPosition() >= this.getXPosition() + this.getWidth())
			return false;
		if(this.getXPosition() >= en.getXPosition() + en.getWidth())
			return false;
		if(en.getYPosition() >= this.getYPosition() + this.getHeight())
			return false;
		if(this.getYPosition() >= en.getYPosition() + en.getHeight())
			return false;
		return true;
	}
	
	public boolean detectClippingMultiple(Entity[] en)
	{
		for(int i = 0; i < en.length; ++i)
			if(this.detectClipping(en[i]))
				return true;
		return false;
	}
	
	public void bounceOf(Entity en)
	{
		if((this.detectCollisionUp(en) && this.getVerVelocity() < 0) 
				|| 
				(this.detectCollisionDown(en) && this.getVerVelocity() > 0))
			this.setVerVelocity(-this.getVerVelocity());
		if((this.detectCollisionLeft(en) && this.getHorVelocity() < 0) 
				|| 
				(this.detectCollisionRight(en) && this.getHorVelocity() > 0))
			this.setHorVelocity(-this.getHorVelocity());
	}
	
	public boolean undoClipping(Entity en, int maxTries, boolean resetIfFailed)
	{
		if(!this.detectClipping(en))
			return true;
		
		this.stopSimulation();
		
		int prevX = this.getXPosition(), prevY = this.getYPosition();
		double angle = Math.atan2(this.getYPosition() - en.getYPosition(), this.getXPosition() - en.getXPosition());
		angle = Math.toDegrees(angle);
		if(angle < 0)
			angle += 360;
		if (angle >= 337.5 || angle < 22.5)
		{
            for(int i = 0; i < maxTries; ++i)
            {
            	this.setXPosition(this.getXPosition() + 1);
            	if(!this.detectClipping(en))
            	{
            		this.startSimluation();
            		return true;
            	}
            }
        }
		else if (angle >= 22.5 && angle < 67.5)
		{
			for(int i = 0; i < maxTries; ++i)
			{
				this.setXPosition(this.getXPosition() - 1);
				this.setYPosition(this.getYPosition() + 1);
				if(!this.detectClipping(en))
				{
					this.startSimluation();
					return true;
				}
			}
        }
		else if (angle >= 67.5 && angle < 112.5)
		{
			for(int i = 0; i < maxTries; ++i)
			{
				this.setYPosition(this.getYPosition() + 1);
				if(!this.detectClipping(en))
				{
					this.startSimluation();
					return true;
				}
			}
        }
		else if (angle >= 112.5 && angle < 157.5)
		{
			for(int i = 0; i < maxTries; ++i)
			{
				this.setXPosition(this.getXPosition() + 1);
				this.setYPosition(this.getYPosition() + 1);
				if(!this.detectClipping(en))
				{
					this.startSimluation();
					return true;
				}
			}
        }
		else if (angle >= 157.5 && angle < 202.5)
		{
			for(int i = 0; i < maxTries; ++i)
			{
				this.setXPosition(this.getXPosition() - 1);
				if(!this.detectClipping(en))
				{
					this.startSimluation();
					return true;
				}
			}
        }
		else if (angle >= 202.5 && angle < 247.5)
		{
			for(int i = 0; i < maxTries; ++i)
			{
				this.setXPosition(this.getXPosition() + 1);
				this.setYPosition(this.getYPosition() - 1);
				if(!this.detectClipping(en))
				{
					this.startSimluation();
					return true;
				}
			}
        }
		else if (angle >= 247.5 && angle < 292.5)
		{
			for(int i = 0; i < maxTries; ++i)
			{
				this.setYPosition(this.getYPosition() - 1);
				if(!this.detectClipping(en))
				{
					this.startSimluation();
					return true;
				}
			}
        }
		else if (angle >= 292.5 && angle < 337.5)
		{
			for(int i = 0; i < maxTries; ++i)
			{
				this.setXPosition(this.getXPosition() - 1);
				this.setYPosition(this.getYPosition() - 1);
				if(!this.detectClipping(en))
				{
					this.startSimluation();
					return true;
				}
			}
        }
		if(resetIfFailed)
		{
			this.setXPosition(prevX);
			this.setYPosition(prevY);
		}
		
		this.startSimluation();
		return false;
	}
	
	public void startSimluation()
	{
		if(this.entitySimulation != null)
			return;
		this.entitySimulation = new Thread(() -> {
			this.time = System.currentTimeMillis();
			while(!this.entitySimulation.isInterrupted())
			{
				double timeSeconds = ((double) (System.currentTimeMillis() - this.time)) / 1000.0000;
				this.time = System.currentTimeMillis();
				this.setHorVelocity(this.getHorVelocity() + (this.getHorAcceleration() * timeSeconds));
				this.setVerVelocity(this.getVerVelocity() + (this.getVerAcceleration() * timeSeconds));
				this.setSimXPosition(this.getSimXPosition() + (this.getHorVelocity() * timeSeconds));
				this.setSimYPosition(this.getSimYPosition() + (this.getVerVelocity() * timeSeconds));
				
				if(this.getSimXPosition() > this.getXPosition())
					this.setXPosition((int) Math.floor(this.getSimXPosition()));
				else
					this.setXPosition((int) Math.ceil(this.getSimXPosition()));
				
				if(this.getSimYPosition() > this.getYPosition())
					this.setYPosition((int) Math.floor(this.getSimYPosition()));
				else
					this.setYPosition((int) Math.ceil(this.getSimYPosition()));
			}
			this.entitySimulation = null;
		});
		this.entitySimulation.start();
	}
	
	public void stopSimulation()
	{
		if(this.entitySimulation == null)
			return;
		this.entitySimulation.interrupt();
	}
	
	public void displayEntity(Canvas cs)
	{
		cs.insertSolidRect(this.paintChar, this.getXPosition(), this.getYPosition(), this.getWidth(), this.getHeight());
	}
	
	public static void displayEntities(Entity[] en, Canvas cs)
	{
		for(int i = 0; i < en.length; ++i)
			en[i].displayEntity(cs);
	}
	
	public void displayEntityBorder(Canvas cs)
	{
		cs.insertRect(this.paintChar, this.getXPosition(), this.getYPosition(), this.getWidth(), this.getHeight());
	}
	
	public static void displayEntitiesBorder(Entity[] en, Canvas cs)
	{
		for(int i = 0; i < en.length; ++i)
			en[i].displayEntityBorder(cs);
	}
}
