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
	
	public Space space;
	public GraphicInterface graphics;
	public boolean playing;
	private int x;
	private int y;
	private int dx;
	private int dy;
	private LoopThread thread;
	int pictureNumber;
	
	public GraphicsAndListeners(GraphicInterface g){
		addKeyListener(this);
		addMouseListener(this);
		
		space=new Space(4);
		this.graphics=g;
		
		playing=false;
		
		dx=0;
		dy=0;
		
		pictureNumber=1;
		
		thread=new LoopThread(this);
		thread.setRunning(true);
		thread.start();
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
	 super.paintComponent(g);
	 
	 double scaleFactor=1;
	
	for(int i=0;i<space.particles.size();i++){
	 g.fillOval((int)((space.particles.get(i).posX-space.particles.get(i).diameter/2)*scaleFactor+dx),
			    (int)((space.particles.get(i).posY-space.particles.get(i).diameter/2)*scaleFactor+dy),
			    (int)(space.particles.get(i).diameter*scaleFactor), 
			    (int)(space.particles.get(i).diameter*scaleFactor));
	 }
	
	if(space.type==4){
	g.drawLine((int)(space.screen_edges[0]*scaleFactor+dx), (int)(space.screen_edges[1]*scaleFactor+dy), (int)(space.screen_edges[2]*scaleFactor+dx), (int)(space.screen_edges[1]*scaleFactor+dy));
	g.drawLine((int)(space.screen_edges[0]*scaleFactor+dx), (int)(space.screen_edges[3]*scaleFactor+dy), (int)(space.screen_edges[2]*scaleFactor+dx), (int)(space.screen_edges[3]*scaleFactor+dy));
	g.drawLine((int)(space.screen_edges[0]*scaleFactor+dx), (int)(space.screen_edges[1]*scaleFactor+dy), (int)(space.screen_edges[0]*scaleFactor+dx), (int)(space.screen_edges[3]*scaleFactor+dy));
	g.drawLine((int)(space.screen_edges[2]*scaleFactor+dx), (int)(space.screen_edges[1]*scaleFactor+dy), (int)(space.screen_edges[2]*scaleFactor+dx), (int)(space.screen_edges[3]*scaleFactor+dy));
	}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode()){ 	
		case KeyEvent.VK_RIGHT:
			if(playing){
				playing=false; 
			}
			else{
			playing=true; 
			}
		break;  
		case KeyEvent.VK_LEFT:
			playing=false;
			space=new Space(space.type);
			repaint();
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
		x=arg0.getX();
		y=arg0.getY();
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		dx=dx+(arg0.getX()-x);
		dy=dy+(arg0.getY()-y);
		if(!playing){
			repaint();
		}
		
		if(!playing && space.type==4){
		double deltaX=(arg0.getX()-dx)-space.particles.get(0).posX;
		double deltaY=(arg0.getY()-dy)-space.particles.get(0).posY;
		double vectorSize=Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));
		
		double vectorX=deltaX/vectorSize;
		double vectorY=deltaY/vectorSize;
		
		space.particles.get(0).velX=vectorX*Math.sqrt(Math.pow(space.particles.get(0).velX,2)+Math.pow(space.particles.get(0).velY,2));
		space.particles.get(0).velY=vectorY*Math.sqrt(Math.pow(space.particles.get(0).velX,2)+Math.pow(space.particles.get(0).velY,2));
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void takePicture() {
	    BufferedImage img = new BufferedImage(graphics.panel.getWidth(), graphics.panel.getHeight(), BufferedImage.TYPE_INT_RGB);
	    graphics.panel.print(img.getGraphics()); // or: panel.printAll(...);
	    try {
	        ImageIO.write(img, "png", new File("images/"+pictureNumber+".png"));
	        pictureNumber++;
	    }
	    catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}

}
