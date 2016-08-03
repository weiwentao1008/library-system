package com.library.web.exception;

import java.io.PrintStream;

public class WebException extends RuntimeException {

	private Throwable cause=null;
	public int what;
	public WebException()
	{
		what=0;
	}
	public WebException(int code,String message)
	{
		super(message);
		what=code;
	}
	public WebException(String message)
	{
		super(message);
	}
	public WebException(int code,Throwable ex)
	{
		super(ex);
		cause=ex;
		what=code;
	}
	public WebException(Throwable ex)
	{
		super(ex);
		cause=ex;
	}
	@Override
	public void printStackTrace(PrintStream ps){
		if(cause == null){
		super.printStackTrace(ps);
		}else{
		ps.println(this);
		cause.printStackTrace(ps);
		}
		}
	
}
