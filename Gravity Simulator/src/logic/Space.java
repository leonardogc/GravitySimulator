package logic;

import java.util.Random;
import java.util.Vector;


public class Space {
	
public Vector<Particle> particles;
public Vector<Particle> particles_1;
public Vector<Particle> particles_2;
public Vector<Particle> particles_3;
public Vector<Particle> particles_4;
public Vector<Vector<Particle>> particle_vector;
public Vector<CollisionThread> collision_threads;

public double g=66;
public double coefficientOfFriction=0; //0.5
public double coefficientOfRestitution=1; //0.8
public int type;

public int[] screen_edges;


//Everything in SI units except mass, which was divided by 10^12,
//and to compensate the G constant was multiplied by 10^12.

public Space(int type){
	this.type=type;
	
	Random r=new Random();
	int vx;//[-80,80] used in simulation
	int vy;
	double m;//[0.1,10] used in simulation
	
	particles=new Vector<Particle>();
	
	particles_1=new Vector<Particle>();
	particles_2=new Vector<Particle>();
	particles_3=new Vector<Particle>();
	particles_4=new Vector<Particle>();
	
	particle_vector = new Vector<Vector<Particle>>();
	
	collision_threads = new Vector<CollisionThread>();
	
	for(int i = 0; i < 16; i++) {
		particle_vector.add(new Vector<Particle>());
	}
	
	if(type==1){
	//solar system
		
	/*particles.add(new Particle(0,0,0,0,120000,10));
	particles.add(new Particle(500,0,0,120,120,10));
	particles.add(new Particle(516,0,0,141.14,0.000012,10));*/


		//four particles (interesting)
		/*particles.add(new Particle(0,0,-100,0,48000,20));
	particles.add(new Particle(300,0,0,-100,48000,20));
	particles.add(new Particle(300,300,100,0,48000,20));
	particles.add(new Particle(0,300,0,100,48000,20));*/
		
		double v = 120; //118
		double a = 45; //45
		
		double v2 = v * Math.cos(a);
		double v3 = v * Math.sin(a);
		
		double ratio = 1.0/3.0;

		particles.add(new Particle(0,0,-v2+v2*ratio,v3-v3*ratio,50000,20));
		particles.add(new Particle(200,0,v2+v2*ratio,-v3-v3*ratio,50000,20));
		particles.add(new Particle(400,0,-v2+v2*ratio,v3-v3*ratio,50000,20));


		/*particles.add(new Particle(0,0,0,74,50000,30));
	particles.add(new Particle(300,0,0,-74,50000,30));*/

		
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
	
	}
	else if(type==2 || type==3) {
		
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
		
		/*particles.addElement(new Particle(180,-4500,0,300,100));
		particles.addElement(new Particle(180,4860,0,-300,100));
		particles.addElement(new Particle(4930,180,-300,0,100));
		particles.addElement(new Particle(-4570,180,300,0,100));*/
		
	}
	else if(type==4){
		screen_edges=new int[]{0,0,1184,662};
		
		/*particles.add(new Particle(1000,327,-1000,0,1,30));
		
		particles.add(new Particle(149,268,0,0,1,30));
		particles.add(new Particle(149,299,0,0,1,30));
		particles.add(new Particle(149,330,0,0,1,30));
		particles.add(new Particle(149,361,0,0,1,30));
		particles.add(new Particle(149,392,0,0,1,30));
		
		particles.add(new Particle(150+1+Math.sin(Math.PI/3)*30,284,0,0,1,30));
		particles.add(new Particle(150+1+Math.sin(Math.PI/3)*30,315,0,0,1,30));
		particles.add(new Particle(150+1+Math.sin(Math.PI/3)*30,346,0,0,1,30));
		particles.add(new Particle(150+1+Math.sin(Math.PI/3)*30,376,0,0,1,30));
		
		particles.add(new Particle(150+2+2*Math.sin(Math.PI/3)*30,299,0,0,1,30));
		particles.add(new Particle(150+2+2*Math.sin(Math.PI/3)*30,330,0,0,1,30));
		particles.add(new Particle(150+2+2*Math.sin(Math.PI/3)*30,361,0,0,1,30));
		
		particles.add(new Particle(150+3+3*Math.sin(Math.PI/3)*30,315,0,0,1,30));
		particles.add(new Particle(150+3+3*Math.sin(Math.PI/3)*30,346,0,0,1,30));
		
		particles.add(new Particle(150+4+4*Math.sin(Math.PI/3)*30,330,0,0,1,30));*/
		
        particles.add(new Particle(1000,330,-1000,0,1,30));
		
		particles.add(new Particle(150,270,0,0,1,30));
		particles.add(new Particle(150,300,0,0,1,30));
		particles.add(new Particle(150,330,0,0,1,30));
		particles.add(new Particle(150,360,0,0,1,30));
		particles.add(new Particle(150,390,0,0,1,30));
		
		particles.add(new Particle(150+Math.sin(Math.PI/3)*30,285,0,0,1,30));
		particles.add(new Particle(150+Math.sin(Math.PI/3)*30,315,0,0,1,30));
		particles.add(new Particle(150+Math.sin(Math.PI/3)*30,345,0,0,1,30));
		particles.add(new Particle(150+Math.sin(Math.PI/3)*30,375,0,0,1,30));
		
		particles.add(new Particle(150+2*Math.sin(Math.PI/3)*30,300,0,0,1,30));
		particles.add(new Particle(150+2*Math.sin(Math.PI/3)*30,330,0,0,1,30));
		particles.add(new Particle(150+2*Math.sin(Math.PI/3)*30,360,0,0,1,30));
		
		particles.add(new Particle(150+3*Math.sin(Math.PI/3)*30,315,0,0,1,30));
		particles.add(new Particle(150+3*Math.sin(Math.PI/3)*30,345,0,0,1,30));
		
		particles.add(new Particle(150+4*Math.sin(Math.PI/3)*30,330,0,0,1,30));
		
	      /*for(int i=0; i<10;i++) {
			for(int i2=0; i2<10;i2++) {
					//particles.add(new Particle(200+30*i2,200+30*i,r.nextInt(201)-100,r.nextInt(201)-100,1,30));
				particles.add(new Particle(200+50*i2,100+50*i,100,0,1,30));
			}
		}*/
		
		/*particles.add(new Particle(200,200,100,0,1,30));
		particles.add(new Particle(300,200,100,0,1,30));*/
	}
}

public void update(double t){

		if(type!=4){			
			for(int i =0;i< particles.size();i++){
				particles.get(i).accX=0;
				particles.get(i).accY=0;
			}
			
			
			for(int i =0;i< particles.size();i++){
				for(int i2=i+1;i2<particles.size();i2++){	
					updateAcceleration(particles.get(i),particles.get(i2));
				}
			}
		}
		else if(type==4){
			updatePoolAcceleration();
		}
		

	for(int i =0;i< particles.size();i++){
		particles.get(i).updateVel(t);
		particles.get(i).updatePos(t);
	}
	
	if(type==1){
		if(particles.size() < 100) {
		update_collisions();
		}
		else {
		update_collisions_with_thread();
		}
	}else if(type==2){
		update_collisions_v2();
	}
	else if(type==3 || type==4){
		update_collisions_V3andV4();
	}
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

public void update_collisions_with_thread(){
	double dx;
	double dy;
	double ax=0;
	double ay=0;
	double ax2=0;
	double ay2=0;
	double ax3=0;
	double ay3=0;
	double ax4=0;
	double ay4=0;
	double ax5=0;
	double ay5=0;
	double r;
	double mass;
	double velX;
	double velY;
	double posX;
	double posY;
	int current;
	boolean found=true;
	
	Vector<Particle> newParticles=new Vector<Particle>();
	
	for(int i=0 ; i<particles.size(); i++) {
		ax=ax+particles.get(i).posX;
		ay=ay+particles.get(i).posY;
	}
	
	ax=ax/particles.size();
	ay=ay/particles.size();
	
	particles_1.clear();
	particles_2.clear();
	particles_3.clear();
	particles_4.clear();
	
	for(int i=0 ; i < particles.size(); i++) {
		if(particles.get(i).posX <= ax && particles.get(i).posY <= ay) {
			particles_1.add(particles.get(i));
		}
		else if(particles.get(i).posX <= ax && particles.get(i).posY > ay) {
			particles_2.add(particles.get(i));
		}
		else if(particles.get(i).posX > ax && particles.get(i).posY <= ay) {
			particles_3.add(particles.get(i));
		}
		else if(particles.get(i).posX > ax && particles.get(i).posY > ay) {
			particles_4.add(particles.get(i));
		}
	}
	
	if(particles.size() > 600) {
		
		for(int i=0 ; i < particles_1.size(); i++) {
			ax2=ax2+particles_1.get(i).posX;
			ay2=ay2+particles_1.get(i).posY;
		}
		
		ax2=ax2/particles_1.size();
		ay2=ay2/particles_1.size();
		
		for(int i=0 ; i < particles_2.size(); i++) {
			ax3=ax3+particles_2.get(i).posX;
			ay3=ay3+particles_2.get(i).posY;
		}
		
		ax3=ax3/particles_2.size();
		ay3=ay3/particles_2.size();
		
		for(int i=0 ; i < particles_3.size(); i++) {
			ax4=ax4+particles_3.get(i).posX;
			ay4=ay4+particles_3.get(i).posY;
		}
		
		ax4=ax4/particles_3.size();
		ay4=ay4/particles_3.size();
		
		for(int i=0 ; i < particles_4.size(); i++) {
			ax5=ax5+particles_4.get(i).posX;
			ay5=ay5+particles_4.get(i).posY;
		}
		
		ax5=ax5/particles_4.size();
		ay5=ay5/particles_4.size();
		
		for(int i=0; i< particle_vector.size();i++) {
			particle_vector.get(i).clear();
		}
		
		for(int i=0 ; i < particles_1.size(); i++) {
			if(particles_1.get(i).posX <= ax2 && particles_1.get(i).posY <= ay2) {
				particle_vector.get(0).add(particles_1.get(i));
			}
			else if(particles_1.get(i).posX <= ax2 && particles_1.get(i).posY > ay2) {
				particle_vector.get(1).add(particles_1.get(i));
			}
			else if(particles_1.get(i).posX > ax2 && particles_1.get(i).posY <= ay2) {
				particle_vector.get(2).add(particles_1.get(i));
			}
			else if(particles_1.get(i).posX > ax2 && particles_1.get(i).posY > ay2) {
				particle_vector.get(3).add(particles_1.get(i));
			}
		}
		
		for(int i=0 ; i < particles_2.size(); i++) {
			if(particles_2.get(i).posX <= ax3 && particles_2.get(i).posY <= ay3) {
				particle_vector.get(4).add(particles_2.get(i));
			}
			else if(particles_2.get(i).posX <= ax3 && particles_2.get(i).posY > ay3) {
				particle_vector.get(5).add(particles_2.get(i));
			}
			else if(particles_2.get(i).posX > ax3 && particles_2.get(i).posY <= ay3) {
				particle_vector.get(6).add(particles_2.get(i));
			}
			else if(particles_2.get(i).posX > ax3 && particles_2.get(i).posY > ay3) {
				particle_vector.get(7).add(particles_2.get(i));
			}
		}
		
		for(int i=0 ; i < particles_3.size(); i++) {
			if(particles_3.get(i).posX <= ax4 && particles_3.get(i).posY <= ay4) {
				particle_vector.get(8).add(particles_3.get(i));
			}
			else if(particles_3.get(i).posX <= ax4 && particles_3.get(i).posY > ay4) {
				particle_vector.get(9).add(particles_3.get(i));
			}
			else if(particles_3.get(i).posX > ax4 && particles_3.get(i).posY <= ay4) {
				particle_vector.get(10).add(particles_3.get(i));
			}
			else if(particles_3.get(i).posX > ax4 && particles_3.get(i).posY > ay4) {
				particle_vector.get(11).add(particles_3.get(i));
			}
		}
		
		for(int i=0 ; i < particles_4.size(); i++) {
			if(particles_4.get(i).posX <= ax5 && particles_4.get(i).posY <= ay5) {
				particle_vector.get(12).add(particles_4.get(i));
			}
			else if(particles_4.get(i).posX <= ax5 && particles_4.get(i).posY > ay5) {
				particle_vector.get(13).add(particles_4.get(i));
			}
			else if(particles_4.get(i).posX > ax5 && particles_4.get(i).posY <= ay5) {
				particle_vector.get(14).add(particles_4.get(i));
			}
			else if(particles_4.get(i).posX > ax5 && particles_4.get(i).posY > ay5) {
				particle_vector.get(15).add(particles_4.get(i));
			}
		}
		
		collision_threads.clear();
		
		for(int i=0; i < particle_vector.size();i++) {
			collision_threads.add(new CollisionThread(particle_vector.get(i)));
		}
		
		for(int i=0; i < collision_threads.size();i++) {
			collision_threads.get(i).start();
		}
		
		for(int i = 0; i< collision_threads.size();i++) {
			try {
				collision_threads.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		particles.clear();
		
		for(int i=0; i < collision_threads.size();i++) {
			particles.addAll(collision_threads.get(i).particles);
		}
		
	}
	else {
	
	CollisionThread t1 = new CollisionThread(particles_1);
	CollisionThread t2 = new CollisionThread(particles_2);
	CollisionThread t3 = new CollisionThread(particles_3);
	CollisionThread t4 = new CollisionThread(particles_4);
	
	t1.start();
	t2.start();
	t3.start();
	t4.start();
	
	try {
		t1.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		t2.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		t3.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		t4.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	particles.clear();
	
	particles.addAll(t1.particles);
	particles.addAll(t2.particles);
	particles.addAll(t3.particles);
	particles.addAll(t4.particles);
	
	}
	
	
	while(found){
			
			newParticles.clear();
			
			found=false;
			
			for(int i =0;i< particles.size();i++){
				for(int i2=i+1;i2<particles.size();i2++){	
					
					if(!particles.get(i).selected && !particles.get(i2).selected){
						
						dx=particles.get(i).posX-particles.get(i2).posX;
						dy=particles.get(i).posY-particles.get(i2).posY;
						r=Math.sqrt(dx*dx+dy*dy);
						
					if(r<((particles.get(i).diameter/2)+(particles.get(i2).diameter/2))){
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
				
				particles.addAll(newParticles);
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
	double extra = 0.08;
	
	
	while(found){
			
			found=false;
			
			
			for(int i =0;i< particles.size();i++){
				for(int i2=i+1;i2<particles.size();i2++){	
					
					if(!particles.get(i).selected && !particles.get(i2).selected){
						
						dx=particles.get(i).posX-particles.get(i2).posX;
						dy=particles.get(i).posY-particles.get(i2).posY;
						r=Math.sqrt(dx*dx+dy*dy);
						targetDistance=(particles.get(i).diameter+particles.get(i2).diameter)/2;
						
					if(r<targetDistance){ 
					found=true;
					mass=particles.get(i).mass+particles.get(i2).mass;
					
					
					x=((targetDistance + extra)-r)*dx/r;
					y=((targetDistance + extra)-r)*dy/r;
					
					
					particles.get(i).posX=particles.get(i).posX+(particles.get(i).mass/mass)*x;
					particles.get(i2).posX=particles.get(i2).posX-(particles.get(i2).mass/mass)*x;
					
					particles.get(i).posY=particles.get(i).posY+(particles.get(i).mass/mass)*y;
					particles.get(i2).posY=particles.get(i2).posY-(particles.get(i2).mass/mass)*y;
					
					
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

public void update_collisions_V3andV4(){
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
	double proportion1=0.5;
	double proportion2=0.5;
	double extra = 0.08;
	
	
	while(found){
			
			found=false;
			
			
			for(int i =0;i< particles.size();i++){
				for(int i2=i+1;i2<particles.size();i2++){	
					
					if(!particles.get(i).selected && !particles.get(i2).selected){
						
						dx=particles.get(i).posX-particles.get(i2).posX;
						dy=particles.get(i).posY-particles.get(i2).posY;
						r=Math.sqrt(dx*dx+dy*dy);
						targetDistance=(particles.get(i).diameter+particles.get(i2).diameter)/2;
						
					if(r<targetDistance){
					found=true;
					
					particles.get(i).selected=true;
					particles.get(i2).selected=true;
					
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
					proportion1=0.5;
					proportion2=0.5;
					}
					else if(v1_ex*particles.get(i).mass+v2_ex*particles.get(i2).mass<0){
						v2_ex=(v1_ex*particles.get(i).mass+v2_ex*particles.get(i2).mass)/particles.get(i2).mass;
						v1_ex=0;
						
						proportion1=0;
						proportion2=1;
					}
					else if(v1_ex*particles.get(i).mass+v2_ex*particles.get(i2).mass>0){
						v1_ex=(v1_ex*particles.get(i).mass+v2_ex*particles.get(i2).mass)/particles.get(i).mass;
						v2_ex=0;
						
						proportion1=1;
						proportion2=0;
					}
					
					particles.get(i).velX=v1_ex*ex_1[0]+v1_ey*ex_1[1];
					particles.get(i).velY=v1_ex*ey_1[0]+v1_ey*ey_1[1];
					particles.get(i2).velX=v2_ex*ex_1[0]+v2_ey*ex_1[1];
					particles.get(i2).velY=v2_ex*ey_1[0]+v2_ey*ey_1[1];
					
				
					x=((targetDistance + extra)-r)*dx/r;
					y=((targetDistance + extra)-r)*dy/r;
					
					
					particles.get(i).posX=particles.get(i).posX+proportion1*x;
					particles.get(i2).posX=particles.get(i2).posX-proportion2*x;
					
					particles.get(i).posY=particles.get(i).posY+proportion1*y;
					particles.get(i2).posY=particles.get(i2).posY-proportion2*y;
					
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
	
	
	
	if(type==4){
		for(int i=0;i< particles.size();i++){
			
			if(particles.get(i).posX<=screen_edges[0]+particles.get(i).diameter/2 && particles.get(i).velX<0){
				particles.get(i).velX=-particles.get(i).velX;
				
				particles.get(i).velX=particles.get(i).velX*coefficientOfRestitution;
				particles.get(i).velY=particles.get(i).velY*coefficientOfRestitution;
			}
			else if(particles.get(i).posY<=screen_edges[1]+particles.get(i).diameter/2 && particles.get(i).velY<0){
				particles.get(i).velY=-particles.get(i).velY;
				
				particles.get(i).velX=particles.get(i).velX*coefficientOfRestitution;
				particles.get(i).velY=particles.get(i).velY*coefficientOfRestitution;
			}
			else if(particles.get(i).posX>=screen_edges[2]-particles.get(i).diameter/2 && particles.get(i).velX>0){
				particles.get(i).velX=-particles.get(i).velX;
				
				particles.get(i).velX=particles.get(i).velX*coefficientOfRestitution;
				particles.get(i).velY=particles.get(i).velY*coefficientOfRestitution;
			}
			else if(particles.get(i).posY>=screen_edges[3]-particles.get(i).diameter/2 && particles.get(i).velY>0){
				particles.get(i).velY=-particles.get(i).velY;
				
				particles.get(i).velX=particles.get(i).velX*coefficientOfRestitution;
				particles.get(i).velY=particles.get(i).velY*coefficientOfRestitution;
			}
		}
	}
	
	/*double momentumX=0;
	double momentumY=0;
	
	for(int i=0;i< particles.size();i++) {
	momentumX = momentumX + (particles.get(i).velX * particles.get(i).mass);
	momentumY = momentumY + (particles.get(i).velY * particles.get(i).mass);
	}
	
	System.out.println("Total Momentum X: "+momentumX+"\nTotal Momentum Y: "+momentumY);*/
	
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

public void updatePoolAcceleration(){
	double vectorSize;
	
	for(int i =0;i<particles.size();i++){
		vectorSize=Math.sqrt(Math.pow(particles.get(i).velX,2)+Math.pow(particles.get(i).velY,2));
		if(vectorSize>0){
		particles.get(i).accX=(-particles.get(i).velX/vectorSize)*9.8*coefficientOfFriction;
		particles.get(i).accY=(-particles.get(i).velY/vectorSize)*9.8*coefficientOfFriction;
		}
		else{
			particles.get(i).accX=0;
			particles.get(i).accY=0;
		}
	}
}

}
