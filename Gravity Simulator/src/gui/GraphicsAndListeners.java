package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import logic.Space;
import logic.Space.Gravity;

public class GraphicsAndListeners extends JPanel implements KeyListener, MouseListener, ActionListener, MouseMotionListener{
	
	public Space space;
	public GraphicInterface graphics;
	public boolean playing;
	private int x;
	private int y;
	private int dx;
	private int dy;
	private double scaleFactor;
	private LoopThread thread;
	public int pictureNumber; //only used for taking pictures
	public boolean take_pictures;
	
	
	public GraphicsAndListeners(GraphicInterface g){
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		space=new Space();
		this.graphics=g;
		
		playing=false;
		
		dx=0;
		dy=0;
		
		scaleFactor=1;
		
		pictureNumber=1;
		take_pictures=false;
		
		thread=new LoopThread(this);
		thread.setRunning(true);
		thread.start();
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
	 super.paintComponent(g);
	
	 double x;
	 
	for(int i=0;i<space.particles.size();i++){
		
		g.setColor(space.particles.get(i).color);
		
		x=Math.cos(space.angle)*space.particles.get(i).pos[0] + Math.sin(space.angle)*space.particles.get(i).pos[1];
		 
		/*g.fillOval((int)((space.particles.get(i).pos[0]-space.particles.get(i).diameter/2)*scaleFactor+dx),
			    (int)((space.particles.get(i).pos[1]-space.particles.get(i).diameter/2)*scaleFactor+dy),
			    (int)(space.particles.get(i).diameter*scaleFactor), 
			    (int)(space.particles.get(i).diameter*scaleFactor));*/
		
		g.fillOval((int)((x-space.particles.get(i).diameter/2)*scaleFactor+dx),
			    (int)((space.particles.get(i).pos[2]-space.particles.get(i).diameter/2)*scaleFactor+dy),
			    (int)(space.particles.get(i).diameter*scaleFactor), 
			    (int)(space.particles.get(i).diameter*scaleFactor));
	}

	if(playing && space.angle==0 && space.draw_grid==true && space.gravity==Gravity.Tree) {
		g.setColor(Color.BLACK);
		
		for(int i =0 ; i<space.octree.squares.size();i++) {
			g.drawRect((int)((space.octree.squares.get(i)[0]-(space.octree.squares.get(i)[2]/2))*scaleFactor+dx),
					   (int)((space.octree.squares.get(i)[1]-(space.octree.squares.get(i)[2]/2))*scaleFactor+dy),
					   (int)(space.octree.squares.get(i)[2]*scaleFactor),
					   (int)(space.octree.squares.get(i)[2]*scaleFactor));
		}
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
			
			System.out.println("Playing: " + playing);
		break;  
		case KeyEvent.VK_LEFT:
			playing=false;
			space=new Space();
			repaint();
			break;
		case KeyEvent.VK_UP:
			scaleFactor*=1.1;
			if(!playing){
			repaint();
			}
			break;
		case KeyEvent.VK_DOWN:
			scaleFactor/=1.1;
			if(!playing){
			repaint();
			}
			break;
		case KeyEvent.VK_A:
			space.angle+=Math.toRadians(1);
			if(!playing){
			repaint();
			}
			break;	
		case KeyEvent.VK_D:
			space.angle-=Math.toRadians(1);
			if(!playing){
			repaint();
			}
			break;	
		case KeyEvent.VK_R:
			if(space.rotate){
				space.rotate=false; 
			}
			else{
				space.rotate=true; 
			}
			
			System.out.println("Rotate: " + space.rotate);
			break;		
		case KeyEvent.VK_Z:
			space.angle=0;
			
			System.out.println("Angle: " + space.angle);
			if(!playing){
				repaint();
				}
			break;	
			
		case KeyEvent.VK_P:
			this.take_pictures=!this.take_pictures;
			
			if(this.take_pictures) {
				System.out.println("Taking Pictures");
			}
			else {
				System.out.println("Stopped Taking Pictures");
			}
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



	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		dx=dx+(e.getX()-x);
		dy=dy+(e.getY()-y);
		
		x=e.getX();
		y=e.getY();
		
		if(!playing){
			repaint();
		}
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
