package gui;

public class LoopThread extends Thread{
	
	 private boolean running;
	 private double max_fps;
	 private GraphicsAndListeners g;

	public LoopThread(GraphicsAndListeners g){
		   running=false;
	       max_fps=60;
	       this.g=g;
	}
	
	   public void setRunning(boolean running){
	       this.running=running;
	   }

	    @Override
	    public void run() {
	        long startTime;
	        long frameDurationMillis;
	        long targetTime=(long)(1000/max_fps);
	        long waitTime;
	        long totalTime=0;
	        int frameCounter=0;
	        double averageFps;

	        while(running){
	            startTime= System.nanoTime();
	            
	            if(g.playing){
	        		g.space.update(1/max_fps);
	        		g.repaint();
	        		}
	          
	            frameDurationMillis=(System.nanoTime()-startTime)/1000000;
	            waitTime=targetTime-frameDurationMillis;

	            try{
	                if(waitTime>0){
	                    this.sleep(waitTime);
	                }
	            }catch(Exception e){ e.printStackTrace();}

	            totalTime=totalTime+(System.nanoTime()-startTime);
	            frameCounter++;

	            if(frameCounter==max_fps){
	                averageFps=((double)frameCounter*1000)/((double)totalTime/1000000);
	                frameCounter=0;
	                totalTime=0;
	                ///uncomment to print the average fps
	                 System.out.println(averageFps);
	            }

	        }

	    }

}
