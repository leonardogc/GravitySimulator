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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import logic.Particle;
import logic.Space;
import logic.Space.Gravity;
import logic.Space.Mode;

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
	public int counter;
	public double max_fps;
	
	
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
		counter=0;
		take_pictures=false;
		
		max_fps=120;
		
		/*try {
			readFile("settings.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		thread=new LoopThread(this, max_fps);
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
			
			playing=!playing; 
		
			System.out.println("Playing: " + playing);
		break;  
		case KeyEvent.VK_LEFT:
			playing=false;
			space=new Space();
			break;
		case KeyEvent.VK_UP:
			scaleFactor*=1.1;
			break;
		case KeyEvent.VK_DOWN:
			scaleFactor/=1.1;
			break;
		case KeyEvent.VK_A:
			space.angle+=Math.toRadians(1);
			break;	
		case KeyEvent.VK_D:
			space.angle-=Math.toRadians(1);
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
		case KeyEvent.VK_S:
			try {
				if(!playing) {
					writeFile("settings.txt");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		graphics.panel.requestFocus();
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
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void readFile(String s) throws IOException {
		String line="";
		
		File file = new File(s);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		space.particles.clear();
        
        while ((line = br.readLine()) != null) {
        	String[] lineComp = line.split(";");
        	
        	switch(lineComp[0]) {
        	case "draw_grid":
        		if(lineComp[1].equals("true")) {
        			space.draw_grid=true;
        		}
        		else if(lineComp[1].equals("false")) {
        			space.draw_grid=false;
        		}
        		break;
        	case "sort":
        		if(lineComp[1].equals("true")) {
        			space.sort=true;
        		}
        		else if(lineComp[1].equals("false")) {
        			space.sort=false;
        		}
        		break;
        	case "mode":
        		
        		switch(lineComp[1]) {
        		case "Merging":
        			space.mode=Mode.Merging;
        			break;
        		case "PoolBallsFast":
        			space.mode=Mode.PoolBallsFast;
        			break;
        		case "PoolBallsAccurate":
        			space.mode=Mode.PoolBallsAccurate;
        			break;
        		case "PoolBallsFastAccurate":
        			space.mode=Mode.PoolBallsFastAccurate;
        			break;
        		case "NoCollisions":
        			space.mode=Mode.NoCollisions;
        			break;
        		}
        		
        		break;
        	case "gravity":
        		
        		switch(lineComp[1]) {
        		case "Tree":
        			space.gravity=Gravity.Tree;
        			break;
        		case "Iterative":
        			space.gravity=Gravity.Iterative;
        			break;
        		case "NoGravity":
        			space.gravity=Gravity.NoGravity;
        			break;
        		}
        		
        		break;
        	case "angle":
        		space.angle=Double.parseDouble(lineComp[1]);
        		break;
        	case "rotate":
        		if(lineComp[1].equals("true")) {
        			space.rotate=true;
        		}
        		else if(lineComp[1].equals("false")) {
        			space.rotate=false;
        		}
        		break;
        	case "g":
        		space.g=Double.parseDouble(lineComp[1]);
        		break;
        	case "dx":
        		dx=Integer.parseInt(lineComp[1]);
        		break;
        	case "dy":
        		dy=Integer.parseInt(lineComp[1]);
        		break;
        	case "scaleFactor":
        		scaleFactor=Double.parseDouble(lineComp[1]);
        		break;
        	case "pictureNumber":
        		pictureNumber=Integer.parseInt(lineComp[1]);
        		break;
        	case "take_pictures":
        		if(lineComp[1].equals("true")) {
        			take_pictures=true;
        		}
        		else if(lineComp[1].equals("false")) {
        			take_pictures=false;
        		}
        		break;
        	case "max_fps":
        		max_fps=Double.parseDouble(lineComp[1]);
        		break;
        	case "counter":
        		counter=Integer.parseInt(lineComp[1]);
        		break;
        	default:
        		space.particles.add(new Particle(Double.parseDouble(lineComp[0]), Double.parseDouble(lineComp[1]), Double.parseDouble(lineComp[2]), Double.parseDouble(lineComp[3]), Double.parseDouble(lineComp[4]), Double.parseDouble(lineComp[5]), Double.parseDouble(lineComp[6]), Double.parseDouble(lineComp[7]), Integer.parseInt(lineComp[8])));
        		break;
        	}
        }
        
        br.close();
	}
	
	
	public void writeFile(String s) throws IOException {
		System.out.println("Saving...");
		
		File file = new File(s);
        FileOutputStream os = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(os);  
        
        //Settings
        
        osw.write("draw_grid;");
        osw.write(space.draw_grid ? "true;\n" : "false;\n");
        
        osw.write("sort;");
        osw.write(space.sort ? "true;\n" : "false;\n");
        
        osw.write("mode;");
        osw.write(space.mode+";\n");
        
        osw.write("gravity;");
        osw.write(space.gravity+";\n");
        
        osw.write("angle;");
        osw.write(space.angle+";\n");
        
        osw.write("rotate;");
        osw.write(space.rotate ? "true;\n" : "false;\n");
        
        osw.write("g;");
        osw.write(space.g+";\n");
 
        
        ////
        
        osw.write("dx;");
        osw.write(dx+";\n");
        
        osw.write("dy;");
        osw.write(dy+";\n");
        
        osw.write("scaleFactor;");
        osw.write(scaleFactor+";\n");
        
        osw.write("pictureNumber;");
        osw.write(pictureNumber+";\n");
        
        osw.write("take_pictures;");
        osw.write(take_pictures ? "true;\n" : "false;\n");
        
        osw.write("counter;");
        osw.write(counter+";\n");
        
        osw.write("max_fps;");
        osw.write(max_fps+";\n");
        
        
        //Objects
		
        
        for(int i = 0; i< space.particles.size(); i++) {
        	osw.write(space.particles.get(i).pos[0]+";");
        	osw.write(space.particles.get(i).pos[1]+";");
        	osw.write(space.particles.get(i).pos[2]+";");
        	
        	osw.write(space.particles.get(i).vel[0]+";");
        	osw.write(space.particles.get(i).vel[1]+";");
        	osw.write(space.particles.get(i).vel[2]+";");
        	
        	osw.write(space.particles.get(i).mass+";");
        	
        	osw.write(space.particles.get(i).diameter+";");
        	
        	int color=0;
        	
        	if(space.particles.get(i).color == Color.RED) {
				color=0;
			}
			else if(space.particles.get(i).color == Color.BLUE) {
				color=1;
			}
			else if(space.particles.get(i).color == Color.GREEN) {
				color=2;
			}
			else if(space.particles.get(i).color == Color.MAGENTA) {
				color=3;
			}
        	
        	osw.write(color+";");
        	
        	if(i != space.particles.size()-1) {
        		 osw.write("\n");
        	}
        }
        
        osw.close();
        
        System.out.println("Saved!");
	}

}
