package logic;
import java.util.Vector;

public class CollisionThread extends Thread{
	public Vector<Particle> particles;
	
	public CollisionThread(Vector<Particle> particles) {
		this.particles=particles;
	}
	
	@Override
	public void run() {
		double dx;
		double dy;
		double r;
		double mass;
		double velX;
		double velY;
		double posX;
		double posY;
		int current;
		boolean found=true;
		
		Vector<Particle> newParticles=new Vector<Particle>();
		
		
		while(found){
				
				newParticles.clear();
				
				found=false;
				
				for(int i =0;i< particles.size();i++){
					for(int i2=i+1;i2<particles.size();i2++){	
						
						if(!particles.get(i).selected && !particles.get(i2).selected){
							
							dx=particles.get(i).posX-particles.get(i2).posX;
							dy=particles.get(i).posY-particles.get(i2).posY;
							r=Math.sqrt(dx*dx+dy*dy);
							
						if(r<(particles.get(i).diameter/2)+(particles.get(i2).diameter/2)){
						found=true;
						
						mass=particles.get(i).mass+particles.get(i2).mass;
						posX=(particles.get(i).posX*(particles.get(i).mass/mass))+(particles.get(i2).posX*(particles.get(i2).mass/mass));
						posY=(particles.get(i).posY*(particles.get(i).mass/mass))+(particles.get(i2).posY*(particles.get(i2).mass/mass));
						velX=((particles.get(i).mass*particles.get(i).velX)+(particles.get(i2).mass*particles.get(i2).velX))/mass;
						velY=((particles.get(i).mass*particles.get(i).velY)+(particles.get(i2).mass*particles.get(i2).velY))/mass;
						
						newParticles.add(new Particle(posX,posY,velX,velY,mass));
						
						particles.get(i).selected=true;
						particles.get(i2).selected=true;
						
						}
					}		
				}
			}
		
			if(found){
				
				current=0;
				
				while(current!=particles.size()){
					
					if(particles.get(current).selected){
						particles.remove(current);
					}
					else{
						current++;
					}
				
				}
				
					for(int i=0;i<newParticles.size();i++){
						particles.add(newParticles.get(i));	
					}
				}
		  }
	}
}
