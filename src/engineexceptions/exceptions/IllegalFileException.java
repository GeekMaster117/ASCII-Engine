package engineexceptions.exceptions;

import java.io.Serializable;
import java.lang.Exception;

public class IllegalFileException extends Exception implements Serializable{
	private static final long serialVersionUID = 817645193;
	
	public IllegalFileException(String GivenFile, String ExpectedFile)
	{
		super("IllegalFileException in Class \'" + Thread.currentThread().getStackTrace()[4].getClassName() + "\' in Method \'"
				+ Thread.currentThread().getStackTrace()[4].getMethodName()
				+ "\' at Line " + Thread.currentThread().getStackTrace()[4].getLineNumber()
				+ ": GivenFile: " + GivenFile + ", ExpectedFile: " + ExpectedFile);
	}
}
