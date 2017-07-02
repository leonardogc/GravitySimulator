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
	int vx;//[-20,20]
	int vy;
	int m;//[1,40]
	
	particles=new Vector<Particle>();
	/*particles.addElement(new Particle(0,0,0,0,1200000));
	particles.addElement(new Particle(500,0,0,120,1200));
	particles.addElement(new Particle(515,0,0,142,0.00012));*/
	
	for(int i=0;i<25;i++){
		for(int i2=0;i2<40;i2++){
		    //vx=Math.abs(r.nextInt(41)-20);
			//vy=Math.abs(r.nextInt(41)-20);
			vx=r.nextInt(41)-20;
			vy=r.nextInt(41)-20;
			m=r.nextInt(40)+1;
			
			/*if(i<=((double)22/37)*i2 && i<=((double)-22/37)*i2+22){
			vx=vx*-1;
			vy=0;
			}else if(i<=((double)22/37)*i2 && i>=((double)-22/37)*i2+22){
				vy=vy*-1;
				vx=0;
			}
             else if(i>=((double)22/37)*i2 && i>=((double)-22/37)*i2+22){
				vy=0;
			}
              else if(i>=((double)22/37)*i2 && i<=((double)-22/37)*i2+22){
            	vx=0;
              }*/
             
			
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
					posX=(particles.get(i).posX*(particles.get(i).mass/mass))+(particles.get(i2).posX*(particles.get(i2).mass/mass));
					posY=(particles.get(i).posY*(particles.get(i).mass/mass))+(particles.get(i2).posY*(particles.get(i2).mass/mass));
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
