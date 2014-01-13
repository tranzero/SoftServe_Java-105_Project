package com.ita.edu.SoftServeUniversity.HelloWorld;

public class HelloWorld {
	
	private String hello = "Hello";
	private String name = null;
	
	

	public HelloWorld () {
		System.out.println(getHello());
	}
	
	public HelloWorld (String name) {
		
		this.concant(name);
		System.out.println(getHello());
	}
	

	public void changeName(String inname) {
		
		this.setName(" " + inname  );
		
	}
	
	public void concant (String inName) {
		this.changeName(inName);
		this.setHello(this.getHello() + this.getName());
		
	}
	/**
	 * @return the hello
	 */
	public String getHello() {
		return hello;
	}

	/**
	 * @param hello the hello to set
	 */
	public void setHello(String hello) {
		this.hello = hello;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
   public static void main (String... args) {
	   
	  HelloWorld hi = new HelloWorld();
	  HelloWorld hy = new HelloWorld("Dude");
	
}
}
