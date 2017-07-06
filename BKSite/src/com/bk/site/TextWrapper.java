package com.bk.site;

public class TextWrapper
{
	public String text;
	public boolean isVisible;
	public String action;
	public TextWrapper(String text, boolean isVisible,String action)
	{
		this.text = text;
		this.isVisible = isVisible;
		this.action=action;
	}
	@Override
	public String toString()
	{
		return "TextWrapper [text=" + text + ", isVisible=" + isVisible + ", action= "+action+"]";
	}
	
}
