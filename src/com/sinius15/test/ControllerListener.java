package com.sinius15.test;

import javax.swing.JOptionPane;

import ch.aplu.xboxcontroller.XboxController;
import ch.aplu.xboxcontroller.XboxControllerAdapter;


public class ControllerListener {

	public XboxController xc;
	private int leftVibrate = 0; 
	private int rightVibrate = 0; 

	public ControllerListener(){ 
		
		xc = new XboxController((is64bit() ? "xboxcontroller64" : "xboxcontroller"), 1, 50, 50);

		if (!xc.isConnected()){
			JOptionPane.showMessageDialog(null, "Xbox controller not connected.", "Fatal error", JOptionPane.ERROR_MESSAGE);
			xc.release();
			return;
		}

		xc.addXboxControllerListener(new XboxControllerAdapter(){
			public void leftTrigger(double value){
				leftVibrate = (int)(65535 * value * value);
				xc.vibrate(leftVibrate, rightVibrate);
			}
			public void rightTrigger(double value){
				rightVibrate = (int)(65535 * value * value);
				xc.vibrate(leftVibrate, rightVibrate);
			}
			@Override
			public void buttonA(boolean pressed) {
				if(pressed)
					System.out.println("A");
			}
		});

		JOptionPane.showMessageDialog(null, "Press left or right trigger, Ok to quit.", "Press", JOptionPane.PLAIN_MESSAGE);
		xc.release();
		System.exit(0);
	}
	
	static boolean is64bit(){
		return System.getProperty("sun.arch.data.model").equals("64");
	}
	
	public static void main(String[] args){
		new ControllerListener();
	}

}
