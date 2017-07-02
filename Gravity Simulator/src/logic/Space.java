package logic;

import java.util.Random;
import java.util.Vector;

public class Space {
	
public Vector<Particle> particles;
public double g;


//Everything in SI units except mass, which was divided by 10^11,
//and to compensate the G constant was multiplied by 10^11.

public Space(){
	Random r=new Random();
	int vx;//[-12,12]
	int vy;
	int m;//[1,50]
	
	particles=new Vector<Particle>();
	/*particles.addElement(new Particle(-200,-200,0,50,480000));
	particles.addElement(new Particle(200,-200,-50,0,480000));
	particles.addElement(new Particle(200,200,0,-50,480000));*/
	
	for(int i=0;i<44;i++){
		for(int i2=0;i2<75;i2++){
			vx=r.nextInt(25)-12;
			vy=r.nextInt(25)-12;
			m=r.nextInt(50)+1;
		particles.addElement(new Particle(i2*25,i*25,vx,vy,m));
	  }
	}
	g=6;
	
}

public void update(double t){
	for(int i =0;i< particles.size();i++){
		particles.get(i).accX=0;
		particles.get(i).accY=0;
	}
	
	
	for(int i =0;i< particles.size();i++){
		for(int i2=0;i2<particles.size();i2++){	
			if(i!=i2){
			particles.get(i).interactAcc(particles.get(i2),g);
			}		
		}
	}
	
	for(int i =0;i< particles.size();i++){
		particles.get(i).interact(t);
	}
	
	update_collisions();
}

public void update_collisions(){
	double dx;
	double dy;
	double r;
	int counter=-1;
	double mass;
	double velX;
	double velY;
	double posX;
	double posY;
	int current;
	boolean found;
	double totalDiameters;
	
	Vector<Particle> newParticles=new Vector<Particle>();
	
	while(counter!=0){
			counter=0;
			
			newParticles.clear();
			
			found=false;
			
			for(int i =0;i< particles.size();i++){
				for(int i2=i+1;i2<particles.size();i2++){	
					if(i!=i2 && !found){
						dx=particles.get(i).posX-particles.get(i2).posX;
						dy=particles.get(i).posY-particles.get(i2).posY;
						r=Math.sqrt(dx*dx+dy*dy);
						
					if(r<(particles.get(i).diameter/2)+(particles.get(i2).diameter/2)){
					found=true;
					counter++;
					
					totalDiameters=particles.get(i).diameter+particles.get(i2).diameter;
					
					mass=particles.get(i).mass+particles.get(i2).mass;
					posX=(particles.get(i).posX*(particles.get(i).diameter/totalDiameters))+(particles.get(i2).posX*(particles.get(i2).diameter/totalDiameters));
					posY=(particles.get(i).posY*(particles.get(i).diameter/totalDiameters))+(particles.get(i2).posY*(particles.get(i2).diameter/totalDiameters));
					velX=((particles.get(i).mass*particles.get(i).velX)+(particles.get(i2).mass*particles.get(i2).velX))/mass;
					velY=((particles.get(i).mass*particles.get(i).velY)+(particles.get(i2).mass*particles.get(i2).velY))/mass;
					
					newParticles.add(new Particle(posX,posY,velX,velY,mass));
					
					particles.get(i).delete=true;
					particles.get(i2).delete=true;
					
					i=particles.size();
					i2=particles.size();
					}
				}		
			}
		}
	
			current=0;
			
			while(current!=particles.size()){
				
				if(particles.get(current).delete){
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
