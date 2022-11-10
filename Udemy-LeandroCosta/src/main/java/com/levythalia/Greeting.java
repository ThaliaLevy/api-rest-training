package com.levythalia;

public class Greeting {

	private final long id;
	private String content;
	
	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getId() {
		return id;
	}
}
