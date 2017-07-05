package com.bk.site;

public class TextWrapper
{
	public String text;
	public boolean isVisible;
	public TextWrapper(String text, boolean isVisible)
	{
		this.text = text;
		this.isVisible = isVisible;
	}
	@Override
	public String toString()
	{
		return "TextWrapper [text=" + text + ", isVisible=" + isVisible + "]";
	}
	
}
