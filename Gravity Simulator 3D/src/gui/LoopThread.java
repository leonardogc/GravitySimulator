package gui;

import java.awt.event.WindowEvent;
import java.io.IOException;

public class LoopThread extends Thread{
	
	 private boolean running;
	 private double max_fps;
	 private GraphicsAndListeners g;
	 private int numberOfParticles; 
	 private double seconds_per_rotation;

	public LoopThread(GraphicsAndListeners g, double max_fps){
		   running=false;
	       this.max_fps=max_fps;
	       numberOfParticles=0;
	       seconds_per_rotation=10;
	       this.g=g;
	}
	
	   public void setRunning(boolean running){
	       this.running=running;
	   }

	   
	    @Override
	    public void run() {
	        long startTime;
	        long frameDuration;
	        long targetTime=(long)(1000000000/max_fps);
	        long waitTime;
	        long waitTimeMill;
	        int waitTimeNano;
	        long totalTime=0;
	        int frameCounter=0;
	        double averageFps;
	     

	        while(running){
	            startTime= System.nanoTime();

	            if(g.playing){
	            	g.space.update(1/max_fps);
	            	g.repaint();

	            	if(g.take_pictures) {
	            		if(g.counter % (int)(max_fps/60) == 0){

	            			g.takePicture();

	            			/*if(g.pictureNumber == 1801) {
	            				running=false;
	            			}*/
	            		}
	            		
	            		g.counter++;
	            	}

	            	if(numberOfParticles!=g.space.particles.size()){
	            		numberOfParticles=g.space.particles.size();
	            		System.out.println("Number of Particles: "+numberOfParticles);
	            	}
	            	
	            	/*try {
						g.writeFile("settings.txt");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
	            }
	            else {
	            	 g.repaint();
	            }
	            
	            
	            if(g.space.rotate == true) {
            		g.space.angle+=Math.toRadians(360/(max_fps*seconds_per_rotation));
            		if(g.space.angle >= 2*Math.PI) {
            			g.space.angle=0;
            		}
            	}
	           

	            frameDuration=System.nanoTime()-startTime;
	            waitTime=targetTime-frameDuration;
	            waitTimeMill = waitTime/1000000;
	            waitTimeNano = (int)(waitTime - waitTimeMill*1000000);

	            try{
	                if(waitTime>0){
	                    this.sleep(waitTimeMill, waitTimeNano);
	                }
	            }catch(Exception e){ e.printStackTrace();}

	            
	            totalTime=totalTime+(System.nanoTime()-startTime);
	            frameCounter++;

	            if(frameCounter==max_fps){
	                averageFps=((double)frameCounter*1000)/((double)totalTime/1000000);
	                frameCounter=0;
	                totalTime=0;
	                ///uncomment to print the average fps
	                 System.out.println("FPS: "+averageFps);
	            }

	        }
	        
	        g.graphics.frame.dispatchEvent(new WindowEvent(g.graphics.frame, WindowEvent.WINDOW_CLOSING));

	    }

}
