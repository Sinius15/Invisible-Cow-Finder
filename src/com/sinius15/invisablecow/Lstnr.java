package com.sinius15.invisablecow;
/**
 * @date 20-2-2014
 * @author Sijmen 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Lstnr implements ChangeListener, ActionListener{

	private Runnable runner;
	
	public Lstnr(Runnable r){
		this.runner = r;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		runner.run();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		runner.run();
	}

}
