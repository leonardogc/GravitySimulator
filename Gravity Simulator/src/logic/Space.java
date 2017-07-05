package logic;

import java.util.Random;
import java.util.Vector;

public class Space {
	
public Vector<Particle> particles;
public double g;


//Everything in SI units except mass, which was divided by 10^12,
//and to compensate the G constant was multiplied by 10^12.

public Space(){
	Random r=new Random();
	int vx;//[-50,50]
	int vy;
	double m;//[0.1,6]
	
	particles=new Vector<Particle>();
	/*particles.add(new Particle(0,0,0,0,120000,10));
	particles.add(new Particle(500,0,0,120,120,10));
	particles.add(new Particle(515,0,0,142,0.000012,10));*/
	
	/*particles.add(new Particle(0,0,-100,0,48000,20));
	particles.add(new Particle(300,0,0,-100,48000,20));
	particles.add(new Particle(300,300,100,0,48000,20));
	particles.add(new Particle(0,300,0,100,48000,20));*/
	
	

	for(int i=0;i<25;i++){
		for(int i2=0;i2<50;i2++){
//		    vx=Math.abs(r.nextInt(101)-50);
//			vy=Math.abs(r.nextInt(101)-50);
			vx=r.nextInt(101)-50;
			vy=r.nextInt(101)-50;
			m=(double)(r.nextInt(100)+1)/10;
			
			/*if(i<=((double)25/50)*i2 && i<=((double)-25/50)*i2+25){
			vx=vx*-1;
			vy=0;
			}else if(i<=((double)25/50)*i2 && i>=((double)-25/50)*i2+25){
				vy=vy*-1;
				vx=0;
			}
             else if(i>=((double)25/50)*i2 && i>=((double)-25/50)*i2+25){
				vy=0;
			}
              else if(i>=((double)25/50)*i2 && i<=((double)-25/50)*i2+25){
            	vx=0;
              }*/
             
			
		particles.addElement(new Particle(i2*20,i*20,vx,vy,m));
			
	  }
	}
	
	g=60;
	
}

public void update(double t){
	
	
	for(int i =0;i< particles.size();i++){
		particles.get(i).accX=0;
		particles.get(i).accY=0;
	}
	
	
	for(int i =0;i< particles.size();i++){
		for(int i2=i+1;i2<particles.size();i2++){	
			updateAcceleration(particles.get(i),particles.get(i2));
		}
	}
	

	for(int i =0;i< particles.size();i++){
		particles.get(i).updateVelocityAndPosition(t);
	}
	
	
	
	update_collisions();
}

public void update_collisions(){
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
					
					if(!particles.get(i).delete && !particles.get(i2).delete){
						
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
					
					particles.get(i).delete=true;
					particles.get(i2).delete=true;
					
					}
				}		
			}
		}
	
		if(found){
			
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

public void updateAcceleration(Particle p1, Particle p2){
	double dxP2=p1.posX-p2.posX;
	double dyP2=p1.posY-p2.posY;
	
	double r2=dxP2*dxP2+dyP2*dyP2;
	double denominator=Math.pow(r2, 1.5);
	
	p2.accX=p2.accX+(g*p1.mass*dxP2)/denominator;
	p2.accY=p2.accY+(g*p1.mass*dyP2)/denominator;
	
	double dxP1=dxP2*-1;
	double dyP1=dyP2*-1;
	
	p1.accX=p1.accX+(g*p2.mass*dxP1)/denominator;
	p1.accY=p1.accY+(g*p2.mass*dyP1)/denominator;
}
}
