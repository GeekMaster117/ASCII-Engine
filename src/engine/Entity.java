package engine;

public class Entity
{
	private int x, y, width, height;
	private double horVelocity, verVelocity, horAcceleration, verAcceleration;
	private char paintChar;
	
	public Entity()
	{
		this.x = 0;
		this.y = 0;
		this.width = 1;
		this.height = 1;
		this.horVelocity = 0.0000;
		this.verVelocity = 0.0000;
		this.horAcceleration = 0.0000;
		this.verAcceleration = 0.0000;
	}
	
	public void setDimensions(int width, int height)
	{
		this.width = width;
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
		this.x = x;
		this.y = y;
	}
	
	public int getXPosition()
	{
		return this.x;
	}
	
	public int getYPosition()
	{
		return this.y;
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
		this.horVelocity = horVelocity;
	}
	
	public double getHorVelocity()
	{
		return this.horVelocity;
	}
	
	public void setVerVelocity(double verVelocity)
	{
		this.verVelocity = verVelocity;
	}
	
	public double getVerVelocity()
	{
		return this.verVelocity;
	}
	
	public void setVelocity(double horVelocity, double verVelocity)
	{
		this.horVelocity = horVelocity;
		this.verVelocity = verVelocity;
	}
	
	public void setHorAcceleration(double horAcceleration)
	{
		this.horAcceleration = horAcceleration;
	}
	
	public double getHorAcceleration()
	{
		return this.horAcceleration;
	}
	
	public void setVerAcceleration(double verAcceleration)
	{
		this.verAcceleration = verAcceleration;
	}
	
	public double getVerAcceleration()
	{
		return this.verAcceleration;
	}
	
	public void setAcceleration(double horAcceleration, double verAcceleration)
	{
		this.horAcceleration = horAcceleration;
		this.verAcceleration = verAcceleration;
	}
	
	public boolean detectCanvasCollision(Canvas cs)
	{
		if(this.y != 0)	
			for(int i = this.x; i <= this.x + this.width - 1; ++i)
				if(cs.data[this.y - 1][i] != ' ')
					return true;
		if(this.y + this.height - 1 < cs.height - 1)
			for(int i = this.x; i <= this.x + this.width - 1; ++i)
				if(cs.data[this.y + this.height][i] != ' ')
					return true;
		if(this.x != 0)
			for(int i = this.y; i <= this.y + this.height - 1; ++i)
				if(cs.data[i][this.x - 1] != ' ')
					return true;
		if(this.x + this.width - 1 < cs.width - 1)
			for(int i = this.y; i <= this.y + this.height - 1; ++i)
				if(cs.data[i][this.x + this.width] != ' ')
					return true;
		return false;
	}
	
	public boolean detectCollision(Entity en)
	{
		if(en.x > this.x + this.width)
			return false;
		if(this.x > en.x + en.width)
			return false;
		if(en.y > this.y + this.height)
			return false;
		if(this.y > en.y + en.height)
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
		if(en.x >= this.x + this.width)
			return false;
		if(this.x >= en.x + en.width)
			return false;
		if(en.y >= this.y + this.height)
			return false;
		if(this.y == en.y + en.height)
			return true;
		return false;
	}
	
	public boolean detectCollisionDown(Entity en)
	{
		if(en.x >= this.x + this.width)
			return false;
		if(this.x >= en.x + en.width)
			return false;
		if(en.y == this.y + this.height)
			return true;
		if(this.y >= en.y + en.height)
			return false;
		return false;
	}
	
	public boolean detectCollisionLeft(Entity en)
	{
		if(en.x >= this.x + this.width)
			return false;
		if(this.x == en.x + en.width)
			return true;
		if(en.y >= this.y + this.height)
			return false;
		if(this.y >= en.y + en.height)
			return false;
		return false;
	}
	
	public boolean detectCollisionRight(Entity en)
	{
		if(en.x == this.x + this.width)
			return true;
		if(this.x >= en.x + en.width)
			return false;
		if(en.y >= this.y + this.height)
			return false;
		if(this.y >= en.y + en.height)
			return false;
		return false;
	}
	
	public boolean detectClipping(Entity en)
	{
		if(en.x >= this.x + this.width)
			return false;
		if(this.x >= en.x + en.width)
			return false;
		if(en.y >= this.y + this.height)
			return false;
		if(this.y >= en.y + en.height)
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
	
	public void undoClipping(Entity en, int maxTries)
	{
		if(!this.detectClipping(en))
			return;
		double angle = Math.atan2(this.y - en.y, this.x - en.x);
		angle = Math.toDegrees(angle);
		if(angle < 0)
			angle += 360;
		if (angle >= 337.5 || angle < 22.5)
		{
            for(int i = 0; i < maxTries; ++i)
            {
            	++this.x;
            	if(!this.detectClipping(en))
            		break;
            }
        }
		else if (angle >= 22.5 && angle < 67.5)
		{
			for(int i = 0; i < maxTries; ++i)
			{
				++this.x;
				--this.y;
				if(!this.detectClipping(en))
            		break;
			}
        }
		else if (angle >= 67.5 && angle < 112.5)
		{
			for(int i = 0; i < maxTries; ++i)
			{
				--this.y;
				if(!this.detectClipping(en))
            		break;
			}
        }
		else if (angle >= 112.5 && angle < 157.5)
		{
			for(int i = 0; i < maxTries; ++i)
			{
				--this.x;
				--this.y;
				if(!this.detectClipping(en))
            		break;
			}
        }
		else if (angle >= 157.5 && angle < 202.5)
		{
			for(int i = 0; i < maxTries; ++i)
			{
				--this.x;
				if(!this.detectClipping(en))
            		break;
			}
        }
		else if (angle >= 202.5 && angle < 247.5)
		{
			for(int i = 0; i < maxTries; ++i)
			{
				--this.x;
				++this.y;
				if(!this.detectClipping(en))
            		break;
			}
        }
		else if (angle >= 247.5 && angle < 292.5)
		{
			for(int i = 0; i < maxTries; ++i)
			{
				++this.y;
				if(!this.detectClipping(en))
            		break;
			}
        }
		else if (angle >= 292.5 && angle < 337.5)
		{
			for(int i = 0; i < maxTries; ++i)
			{
				++this.x;
				++this.y;
				if(!this.detectClipping(en))
            		break;
			}
        }
	}
	
	public void simulateEntity()
	{
		this.horVelocity += this.horAcceleration;
		this.verVelocity += this.verAcceleration;
		this.x += (int) Math.floor(this.horVelocity);
		this.y += (int) Math.floor(this.verVelocity);
	}
	
	public static void simulateEntities(Entity[] en)
	{
		for(int i = 0; i < en.length; ++i)
		{
			en[i].horVelocity += en[i].horAcceleration;
			en[i].verVelocity += en[i].verAcceleration;
			en[i].x += (int) Math.floor(en[i].horVelocity);
			en[i].y += (int) Math.floor(en[i].verVelocity);
		}
	}
	
	public void displayEntity(Canvas cs)
	{
		cs.insertSolidRect(this.paintChar, this.x, this.y, this.width, this.height);
	}
	
	public static void displayEntities(Entity[] en, Canvas cs)
	{
		for(int i = 0; i < en.length; ++i)
			cs.insertSolidRect(en[i].paintChar, en[i].x, en[i].y, en[i].width, en[i].height);
	}
	
	public void displayEntityBorder(Canvas cs)
	{
		cs.insertRect(this.paintChar, this.x, this.y, this.width, this.height);
	}
	
	public static void displayEntitiesBorder(Entity[] en, Canvas cs)
	{
		for(int i = 0; i < en.length; ++i)
			cs.insertRect(en[i].paintChar, en[i].x, en[i].y, en[i].width, en[i].height);
	}
}
