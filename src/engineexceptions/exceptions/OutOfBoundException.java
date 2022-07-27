package engineExceptions.exceptions;

import java.io.Serializable;
import java.lang.Exception;

public class OutOfBoundException extends Exception implements Serializable{
	private static final long serialVersionUID = 575865874;
	
	public OutOfBoundException(int value,int start,int end)
	{
		super("OutOfBoundError in Class \'" + Thread.currentThread().getStackTrace()[4].getClassName() + "\' in Method \'"
				+ Thread.currentThread().getStackTrace()[4].getMethodName()
				+ "\' at Line " + Thread.currentThread().getStackTrace()[4].getLineNumber()
				+ ": Value: " + value + ", LegalBounds: " + start + ", " + end);
	}
}