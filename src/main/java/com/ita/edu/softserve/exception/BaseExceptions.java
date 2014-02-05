package com.ita.edu.softserve.exception;



import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

public class BaseExceptions extends Exception {
	private static final long serialVersionUID = -3873888718676301070L;
    private static final Logger logger = Logger.getLogger("errorLog");

    private String msg;

    public BaseExceptions() {
	StringWriter trace = new StringWriter();
	printStackTrace(new PrintWriter(trace));
	logger.error(trace.toString());
    }

    public BaseExceptions(String msg) {
	super(msg);
	StringWriter trace = new StringWriter();
	printStackTrace(new PrintWriter(trace));
	logger.error(trace.toString());
	this.msg = msg;
    }

    public BaseExceptions(String msg, Exception e) {
	super(msg, e);
	StringWriter trace = new StringWriter();
	printStackTrace(new PrintWriter(trace));
	logger.error(trace.toString());
	this.msg = msg;
    }

    public String getMsg() {
	return msg;
    }

    public void setMsg(String msg) {
	this.msg = msg;
    }
}
