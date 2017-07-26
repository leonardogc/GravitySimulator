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
	int vx;//[-80,80]
	int vy;
	double m;//[0.1,10]
	
	particles=new Vector<Particle>();
	
	/*particles.add(new Particle(0,0,0,0,120000,10));
	particles.add(new Particle(500,0,0,120,120,10));
	particles.add(new Particle(515,0,0,142,0.000012,10));*/
	
	/*particles.add(new Particle(0,0,-100,0,48000,20));
	particles.add(new Particle(300,0,0,-100,48000,20));
	particles.add(new Particle(300,300,100,0,48000,20));
	particles.add(new Particle(0,300,0,100,48000,20));*/
	
	
//	for(int i=0;i<50;i++){
//		for(int i2=0;i2<100;i2++){
////		    vx=Math.abs(r.nextInt(161)-80);
////			vy=Math.abs(r.nextInt(161)-80);
//			vx=r.nextInt(161)-80;
//			vy=r.nextInt(161)-80;
//			m=(double)(r.nextInt(100)+1)/10;
//			
//			/*if(i<=((double)25/50)*i2 && i<=((double)-25/50)*i2+25){
//			vx=vx*-1;
//			vy=0;
//			}else if(i<=((double)25/50)*i2 && i>=((double)-25/50)*i2+25){
//				vy=vy*-1;
//				vx=0;
//			}
//             else if(i>=((double)25/50)*i2 && i>=((double)-25/50)*i2+25){
//				vy=0;
//			}
//              else if(i>=((double)25/50)*i2 && i<=((double)-25/50)*i2+25){
//            	vx=0;
//              }*/
//             
//			
//		particles.addElement(new Particle(i2*10,i*10,vx,vy,m));
//			
//	  }
//	}
	
	
	
//	for(int i=0;i<10;i++){
//	for(int i2=0;i2<10;i2++){
//		vx=r.nextInt(21)-10;
//		vy=r.nextInt(21)-10;
//		m=(double)(r.nextInt(100)+1)/10;
//		
//		vx=0;
//		vy=0;
//		m=30;
//	
//	particles.addElement(new Particle(i2*40,i*40,vx,vy,m));
//		
//  }
//}
	
	for(int i=0;i<10;i++){
		for(int i2=0;i2<5;i2++){
			vx=r.nextInt(21)-10;
			vy=r.nextInt(21)-10;
			m=(double)(r.nextInt(100)+1)/10;
			
			vx=0;
			vy=0;
			m=30;
		
		particles.addElement(new Particle(i2*40-70,i*40,vx,vy,m));
			
	  }
	}
	
	for(int i=0;i<10;i++){
		for(int i2=5;i2<10;i2++){
			vx=r.nextInt(21)-10;
			vy=r.nextInt(21)-10;
			m=(double)(r.nextInt(100)+1)/10;
			
			vx=0;
			vy=0;
			m=30;
		
		particles.addElement(new Particle(70+i2*40,i*40,vx,vy,m));
			
	  }
	}
	
	particles.addElement(new Particle(180,-2500,0,100,300));
	
	
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
	
	update_collisions_v3();
	
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

public void update_collisions_v2(){
	double dx;
	double dy;
	double r;
	double x;
	double y;
	double mass;
	double velX;
	double velY;
	double targetDistance;
	boolean found=true;
	
	
	while(found){
			
			found=false;
			
			
			for(int i =0;i< particles.size();i++){
				for(int i2=i+1;i2<particles.size();i2++){	
					
					if(!particles.get(i).selected && !particles.get(i2).selected){
						
						dx=particles.get(i).posX-particles.get(i2).posX;
						dy=particles.get(i).posY-particles.get(i2).posY;
						r=Math.sqrt(dx*dx+dy*dy);
						targetDistance=(particles.get(i).diameter+particles.get(i2).diameter)/2;
						
					if(r<targetDistance-1){
					found=true;
					mass=particles.get(i).mass+particles.get(i2).mass;
					
					x=(targetDistance-r)*Math.abs(dx)/r;
					y=(targetDistance-r)*Math.abs(dy)/r;
					
					if(dx<0){
						particles.get(i).posX=particles.get(i).posX-(particles.get(i).mass/mass)*x;
						particles.get(i2).posX=particles.get(i2).posX+(particles.get(i2).mass/mass)*x;
					}
					else{
						particles.get(i).posX=particles.get(i).posX+(particles.get(i).mass/mass)*x;
						particles.get(i2).posX=particles.get(i2).posX-(particles.get(i2).mass/mass)*x;
					}
					
					if(dy<0){
						particles.get(i).posY=particles.get(i).posY-(particles.get(i).mass/mass)*y;
						particles.get(i2).posY=particles.get(i2).posY+(particles.get(i2).mass/mass)*y;
					}
					else{
						particles.get(i).posY=particles.get(i).posY+(particles.get(i).mass/mass)*y;
						particles.get(i2).posY=particles.get(i2).posY-(particles.get(i2).mass/mass)*y;
					}
					
					
					velX=((particles.get(i).mass*particles.get(i).velX)+(particles.get(i2).mass*particles.get(i2).velX))/mass;
					velY=((particles.get(i).mass*particles.get(i).velY)+(particles.get(i2).mass*particles.get(i2).velY))/mass;
					
					particles.get(i).velX=velX;
					particles.get(i2).velX=velX;
					particles.get(i).velY=velY;
					particles.get(i2).velY=velY;
					
					particles.get(i).selected=true;
					particles.get(i2).selected=true;
					
					}
				}		
			}
		}
	
		if(found){
			
		    for(int i=0;i<particles.size();i++){
					particles.get(i).selected=false;
				}
			}
	  }
	}

public void update_collisions_v3(){
	double dx;
	double dy;
	double r;
	double x;
	double y;
	double ex[]=new double[2];
	double ey[]=new double[2];
	double ex_1[]=new double[2];
	double ey_1[]=new double[2];
	double v1_ex;
	double v1_ey;
	double v2_ex;
	double v2_ey;
	double targetDistance;
	boolean found=true;
	
	
	while(found){
			
			found=false;
			
			
			for(int i =0;i< particles.size();i++){
				for(int i2=i+1;i2<particles.size();i2++){	
					
					if(!particles.get(i).selected && !particles.get(i2).selected){
						
						dx=particles.get(i).posX-particles.get(i2).posX;
						dy=particles.get(i).posY-particles.get(i2).posY;
						r=Math.sqrt(dx*dx+dy*dy);
						targetDistance=(particles.get(i).diameter+particles.get(i2).diameter)/2;
						
					if(r<targetDistance-1){
					found=true;
					
					x=(targetDistance-r)*Math.abs(dx)/r;
					y=(targetDistance-r)*Math.abs(dy)/r;
					
					if(dx<0){
						particles.get(i).posX=particles.get(i).posX-0.5*x;
						particles.get(i2).posX=particles.get(i2).posX+0.5*x;
					}
					else{
						particles.get(i).posX=particles.get(i).posX+0.5*x;
						particles.get(i2).posX=particles.get(i2).posX-0.5*x;
					}
					
					if(dy<0){
						particles.get(i).posY=particles.get(i).posY-0.5*y;
						particles.get(i2).posY=particles.get(i2).posY+0.5*y;
					}
					else{
						particles.get(i).posY=particles.get(i).posY+0.5*y;
						particles.get(i2).posY=particles.get(i2).posY-0.5*y;
					}
					
					ex[0]=dx/r;
					ex[1]=dy/r;
					
					ey[0]=-ex[1];
					ey[1]=ex[0];
					
					ex_1[0]=ex[0];
					ex_1[1]=-ex[1];
					ey_1[0]=-ey[0];
					ey_1[1]=ey[1];
					
					v1_ex=particles.get(i).velX*ex[0]+particles.get(i).velY*ex[1];
					v1_ey=particles.get(i).velX*ey[0]+particles.get(i).velY*ey[1];
					v2_ex=particles.get(i2).velX*ex[0]+particles.get(i2).velY*ex[1];
					v2_ey=particles.get(i2).velX*ey[0]+particles.get(i2).velY*ey[1];
					
					if(v1_ex*particles.get(i).mass+v2_ex*particles.get(i2).mass==0){
					v1_ex=0;
					v2_ex=0;
					}
					else if(v1_ex*particles.get(i).mass+v2_ex*particles.get(i2).mass<0){
						v2_ex=(v1_ex*particles.get(i).mass+v2_ex*particles.get(i2).mass)/particles.get(i2).mass;
						v1_ex=0;
					}
					else if(v1_ex*particles.get(i).mass+v2_ex*particles.get(i2).mass>0){
						v1_ex=(v1_ex*particles.get(i).mass+v2_ex*particles.get(i2).mass)/particles.get(i).mass;
						v2_ex=0;
					}
					
					particles.get(i).velX=v1_ex*ex_1[0]+v1_ey*ex_1[1];
					particles.get(i).velY=v1_ex*ey_1[0]+v1_ey*ey_1[1];
					particles.get(i2).velX=v2_ex*ex_1[0]+v2_ey*ex_1[1];
					particles.get(i2).velY=v2_ex*ey_1[0]+v2_ey*ey_1[1];
					
					
					
					particles.get(i).selected=true;
					particles.get(i2).selected=true;
					
					}
				}		
			}
		}
	
		if(found){
			
		    for(int i=0;i<particles.size();i++){
					particles.get(i).selected=false;
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
