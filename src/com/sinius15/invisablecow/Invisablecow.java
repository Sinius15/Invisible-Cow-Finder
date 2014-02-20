package com.sinius15.invisablecow;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ch.aplu.xboxcontroller.XboxController;
import ch.aplu.xboxcontroller.XboxControllerAdapter;
/**
 * @date 20-2-2014
 * @author Sijmen
 */
public class Invisablecow {
	
	public static CowFrame frame = new CowFrame();
	public static boolean run = false;
	public static boolean rSholder = false;
	public static boolean lSholder = false;
	public static boolean butY = false;
	
	public static ArrayList<Hair> hairs = new ArrayList<>();
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e2) {
			e2.printStackTrace();
		}
		XboxController xc = new XboxController((System.getProperty("sun.arch.data.model").equals("64") ? "xboxcontroller64" : "xboxcontroller"), 1, 50, 50);
		if (!xc.isConnected()){
			JOptionPane.showMessageDialog(null, "Xbox controller not connected.", "Fatal error", JOptionPane.ERROR_MESSAGE);
			xc.release();
			return;
		}
		frame.setVisible(true);
		
		frame.getBtnStart().addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
			if(!run)
				doAction((int)frame.getStartX().getValue(), (int)frame.getStartY().getValue(), (int)frame.getEndX().getValue(), (int)frame.getEndY().getValue());
		}});
		frame.getBtnStop().addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
			run = false;
		}});
		frame.setGeneralActionListener(new Lstnr(new Runnable() { @Override public void run() {
			updateScreen();
		}}));
		if(!xc.isConnected())
			xc.addXboxControllerListener(new XboxControllerAdapter(){
				@Override
				public void buttonA(boolean pressed) {
					if(!run)
						doAction((int)frame.getStartX().getValue(), (int)frame.getStartY().getValue(), (int)frame.getEndX().getValue(), (int)frame.getEndY().getValue());
					super.buttonA(pressed);
				}
				@Override
				public void buttonX(boolean pressed) {
					run = false;
					super.buttonX(pressed);
				}
				@Override
				public void rightShoulder(boolean pressed) {
					rSholder = pressed;
					super.rightShoulder(pressed);
				}
				@Override
				public void leftShoulder(boolean pressed) {
					lSholder = pressed;
					System.out.println(lSholder);
					super.leftShoulder(pressed);
				}
				@Override
				public void dpad(int direction, boolean pressed) {
					if(!pressed)
						return;
					int multiplier = 10;
					if(rSholder)
						multiplier *= 10;
					if(lSholder)
						multiplier /= 10;
					if(butY){
						if(direction == 0)//up
							frame.getStartY().setValue((int)frame.getStartY().getValue()-multiplier);
						if(direction == 2)//right
							frame.getStartX().setValue((int)frame.getStartX().getValue()+multiplier);
						if(direction == 4)//down
							frame.getStartY().setValue((int)frame.getStartY().getValue()+multiplier);
						if(direction == 6)//left
							frame.getStartX().setValue((int)frame.getStartX().getValue()-multiplier);
					}else{
						if(direction == 0)//up
							frame.getEndY().setValue((int)frame.getEndY().getValue()-multiplier);
						if(direction == 2)//right
							frame.getEndX().setValue((int)frame.getEndX().getValue()+multiplier);
						if(direction == 4)//down
							frame.getEndY().setValue((int)frame.getEndY().getValue()+multiplier);
						if(direction == 6)//left
							frame.getEndX().setValue((int)frame.getEndX().getValue()-multiplier);
					}
					updateScreen();
					super.dpad(direction, pressed);
				}
				@Override
				public void buttonY(boolean pressed) {
					butY = pressed;
					super.buttonY(pressed);
				}
			});
	}
	
	public static void doAction(final int x, final int y, final int w, final int h){
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					run = true;
					Robot rob = new Robot();
					for(int i = x ; i < w; i++){
						for(int j = y ; j < h; j++){
							rob.mouseMove(i, j);
							rob.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//							rob.delay(1);
							rob.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
							if(!run)
								break;
						}
						if(!run)
							break;
					}
					run = false;
				} catch (AWTException e) {
					run = false;
					e.printStackTrace();
				}
				
			}
		});
		t.start();

	}
	
	public static void updateScreen(){
		for(Hair r : hairs){
			r.dispose();
			r = null;
		}
		hairs = new ArrayList<>();
		
		int yS = (int) frame.getStartY().getValue();
		int xS = (int) frame.getStartX().getValue();
		
		int yE = (int) frame.getEndY().getValue();
		int xE = (int) frame.getEndX().getValue();
		
		hairs.add(new Hair(xS, yS, xE-xS, 1));
		hairs.add(new Hair(xS, yE, xE-xS, 1));
		
		hairs.add(new Hair(xS, yS, 1, yE-yS));
		hairs.add(new Hair(xE, yS, 1, yE-yS));
	}
	
}
