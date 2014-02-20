package com.sinius15.invisablecow;
/**
 * @date 20-2-2014
 * @author Sijmen
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window;

public class Hair extends Window{
	private static final long serialVersionUID = 1L;

	public Hair(int x, int y, int w, int h){
	      super(null);
	      setBounds(x, y, w, h);
	      setVisible(true);
	      setAlwaysOnTop(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.red);
		g.fillRect(0, 0, 9000, 9000);
	}

}
