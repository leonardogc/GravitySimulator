package gui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import logic.Space;

public class GraphicsAndListeners extends JPanel implements KeyListener, MouseListener, ActionListener{
	
	private Space space;
	private GraphicInterface graphics;
	private Timer t;
	private boolean playing;
	private int counter;
	private long start;
	
	public GraphicsAndListeners(GraphicInterface g){
		addKeyListener(this);
		addMouseListener(this);
		
		space=new Space();
		this.graphics=g;
		
		playing=false;
		
		counter=0;
		
		t=new Timer(10,this);
		
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
	 super.paintComponent(g);
	 
	 double scaleFactor=0.5;
	 double sumX= 1000*(1-scaleFactor)/2;
	 double sumY=600*(1-scaleFactor)/2;
	 
	 sumX=130;
	 sumY=50;
	
	for(int i=0;i<space.particles.size();i++){
	 g.fillOval((int)((space.particles.get(i).posX-space.particles.get(i).diameter/2)*scaleFactor+sumX),
			    (int)((space.particles.get(i).posY-space.particles.get(i).diameter/2)*scaleFactor+sumY),
			    (int)(space.particles.get(i).diameter*scaleFactor), 
			    (int)(space.particles.get(i).diameter*scaleFactor));
	 }
	 

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode()){ 
		case KeyEvent.VK_LEFT: 
			if(playing){
				playing=false; 
			}
			else{
			playing=true; 
			}
			break;	
		case KeyEvent.VK_RIGHT:
			playing=true; 
			t.start();
		break;  
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		graphics.panel.requestFocusInWindow();
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(playing){
		space.update(0.015);
		repaint();
		}
		
		if(counter==0){
			start=System.nanoTime();
		}
		counter++;
		if(counter ==1000){
			System.out.println("Average frame duration: " + ((System.nanoTime()-start)/(double)1000000)/counter);
			counter=0;
		}
		
	}


}
