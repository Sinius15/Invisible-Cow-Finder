package com.sinius15.invisablecow;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;


/**
 * @date 20-2-2014
 * @author Sijmen
 */
public class CowFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JSpinner startX;
	private JSpinner endX;
	private JSpinner startY;
	private JSpinner endY;
	private JButton btnStarta;
	private JButton btnStopx;

	public CowFrame() {
		setResizable(false);
		setTitle("findtheinvisablecow hack");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 219, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		startX = new JSpinner();
		startX.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		startX.setBounds(142, 11, 61, 20);
		contentPane.add(startX);
		
		startY = new JSpinner();
		startY.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		startY.setBounds(142, 45, 61, 20);
		contentPane.add(startY);
		
		endX = new JSpinner();
		endX.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		endX.setBounds(142, 76, 61, 20);
		contentPane.add(endX);
		
		endY = new JSpinner();
		endY.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		endY.setBounds(142, 107, 61, 20);
		contentPane.add(endY);
		
		JLabel lblStartX = new JLabel("start x    pad-left,right");
		lblStartX.setBounds(10, 14, 122, 14);
		contentPane.add(lblStartX);
		
		JLabel lblStartY = new JLabel("start y    pad-up,down");
		lblStartY.setBounds(10, 45, 122, 14);
		contentPane.add(lblStartY);
		
		JLabel lblEndX = new JLabel("end x     stick-left,right");
		lblEndX.setBounds(10, 76, 122, 14);
		contentPane.add(lblEndX);
		
		JLabel lblEndY = new JLabel("end y     stick-up,down");
		lblEndY.setBounds(10, 107, 122, 14);
		contentPane.add(lblEndY);
		
		btnStarta = new JButton("Start (A)");
		btnStarta.setBounds(6, 138, 91, 23);
		contentPane.add(btnStarta);
		
		btnStopx = new JButton("Stop (X)");
		btnStopx.setBounds(107, 138, 96, 23);
		contentPane.add(btnStopx);
	}
	
	public void setGeneralActionListener(Lstnr a){
		this.startX.addChangeListener(a);
		this.startY.addChangeListener(a);
		this.endX.addChangeListener(a);
		this.endY.addChangeListener(a);
		this.btnStarta.addActionListener(a);
		this.btnStopx.addActionListener(a);
	}
	
	public JSpinner getStartX() {
		return startX;
	}
	public JSpinner getEndX() {
		return endX;
	}
	public JSpinner getStartY() {
		return startY;
	}
	public JSpinner getEndY() {
		return endY;
	}
	public JPanel getContentPane() {
		return contentPane;
	}
	public JButton getBtnStart() {
		return btnStarta;
	}
	public JButton getBtnStop() {
		return btnStopx;
	}
}
