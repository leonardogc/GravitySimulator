package logic;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Space {

	public boolean draw_grid=false;
	public boolean enable_obstacles=true;
	public Mode mode=Mode.PoolBallsAccurate;
	public Gravity gravity=Gravity.Iterative;

	public double mult_m_by=1;
	public double mult_kg_by=Math.pow(10, -12);
	public double mult_s_by=1;

	public double g=6.674*Math.pow(10, -11)*Math.pow(mult_m_by, 3)*Math.pow(mult_kg_by, -1)*Math.pow(mult_s_by, -2);
	
	public double extra=0.01;

	public enum Gravity{
		Tree, Iterative, NoGravityInteraction
	}

	public enum Mode{
		Merging, PoolBallsFast, PoolBallsAccurate, PoolBallsFastAccurate, NoCollisions
	}

	public ArrayList<Particle> particles;
	public ArrayList<Obstacle> obstacles;
	public Random rand;
	public Octree octree;


	public Space(){
		rand=new Random();
		double vx=0;//[-80,80] used in simulation
		double vy=0;
		double m;//[0.1,10] used in simulation

		particles=new ArrayList<Particle>();
		obstacles=new ArrayList<Obstacle>();

		if(mode == Mode.Merging){

			//solar system

			/*particles.add(new Particle(0,0,0,0,120000,10));
	particles.add(new Particle(500,0,0,120,120,10));
	particles.add(new Particle(516,0,0,141.14,0.000012,10));*/


			//four particles (interesting)
			/*particles.add(new Particle(0,0,0,-100,0,0,48000,20,1));
		particles.add(new Particle(300,0,0,0,0,-100,48000,20,1));
		particles.add(new Particle(300,0,300,100,0,0,48000,20,1));
		particles.add(new Particle(0,0,300,0,0,100,48000,20,1));*/

			//		double v = 120; //118
			//		double a = 45; //45
			//		
			//		double v2 = v * Math.cos(a);
			//		double v3 = v * Math.sin(a);
			//		
			//		double ratio = 1.0/3.0;
			//
			//		particles.add(new Particle(0,0,0,-v2+v2*ratio,0,v3-v3*ratio,50000,20));
			//		particles.add(new Particle(200,0,0,v2+v2*ratio,0,-v3-v3*ratio,50000,20));
			//		particles.add(new Particle(400,0,0,-v2+v2*ratio,0,v3-v3*ratio,50000,20));


			/*particles.add(new Particle(0,0,0,74,50000,30));
	particles.add(new Particle(300,0,0,-74,50000,30));*/


			for(int i=0;i<20;i++){
				for(int i2=0;i2<20;i2++){
					//		    vx=Math.abs(r.nextInt(161)-80);
					//			vy=Math.abs(r.nextInt(161)-80);
					vx=rand.nextInt(161)-80;
					vy=rand.nextInt(161)-80;
					m=(double)(rand.nextInt(400)+1)/10;

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

					particles.add(new Particle(i2*50-500,i*50-500,vx,vy,m,rand.nextInt(4)));
				}
			}


			//		for(int i=0;i<10;i++){
			//			for(int i2=0;i2<10;i2++){
			//				for(int i3=0;i3<10;i3++){
			//					double x=rand.nextInt(1001)-500;
			//					double y=rand.nextInt(1001)-500;
			//					double z=rand.nextInt(1001)-500;
			//					
			//					if(x==0) {
			//						i3--;
			//						continue;
			//					}
			//					
			//					double v=(Math.abs(x)/500)*(rand.nextInt(80)+1);
			//					
			//					double r=Math.sqrt(((y*y)/(x*x))+1);
			//					
			//					if(x > 0) {
			//					vx=v*((-y/x)/r);
			//					vy=v*(1/r);
			//					}
			//					else if (x < 0){
			//						vx=-v*((-y/x)/r);
			//						vy=-v*(1/r);
			//					}
			//					vz=0;
			//					
			//					m=(double)(rand.nextInt(1000)+1)/10;
			//
			//					particles.addElement(new Particle(x,z,y,vx,vz,vy,m,rand.nextInt(4)));
			//				}
			//			}
			//		}

			//		for(int i=0;i<10;i++){
			//			for(int i2=0;i2<10;i2++){
			//				for(int i3=0;i3<10;i3++){
			//					double r=rand.nextInt(501);
			//					
			//					double a1=rand.nextInt(360)+1;
			//					double a2=rand.nextInt(181)-90;
			//					
			//					
			//					double z=r*Math.sin(Math.toRadians(a2));
			//					
			//
			//					double r2=r*Math.cos(Math.toRadians(a2));
			//					double x=r2*Math.cos(Math.toRadians(a1));
			//					double y=r2*Math.sin(Math.toRadians(a1));
			//					
			//					if(x==0) {
			//						i3--;
			//						continue;
			//					}
			//					
			//					double v=(Math.abs(r2)/500)*2000;
			//					
			//					
			//					if(x > 0) {
			//					vx=v*((-y/x)/r);
			//					vy=v*(1/r);
			//					}
			//					else if (x < 0){
			//						vx=-v*((-y/x)/r);
			//						vy=-v*(1/r);
			//					}
			//					vz=0;
			//					
			//					m=(double)(rand.nextInt(100)+1)/10;
			//
			//					particles.addElement(new Particle(x,z,y,vx,vz,vy,m,rand.nextInt(4)));
			//				}
			//			}
			//		}

		}
		else if(mode != Mode.NoCollisions) {

			for(int i=0;i<10;i++){
				for(int i2=-20;i2<5;i2++){
					vx=rand.nextInt(21)-10;
					vy=rand.nextInt(21)-10;
					m=(double)(rand.nextInt(500)+1)/10;

					/*vx=0;
						vy=0;*/
					m=50;

					particles.add(new Particle(i2*40-260,i*40-180,vx,vy,m,rand.nextInt(4)));	
				}
			}



			for(int i=0;i<10;i++){
				for(int i2=5;i2<30;i2++){
					vx=rand.nextInt(21)-10;
					vy=rand.nextInt(21)-10;
					m=(double)(rand.nextInt(500)+1)/10;

					/*vx=0;
						vy=0;*/
					m=50;

					particles.add(new Particle(i2*40-120,i*40-180,vx,vy,m,rand.nextInt(4)));

				}
			}

			//calculate pi

			/*particles.add(new Particle(25,0,0,0,1,50,rand.nextInt(4)));
			particles.add(new Particle(75,0,-50,0,100.0,50,rand.nextInt(4)));

			ArrayList<double[]> points = new ArrayList();

			points.add(new double[]{0, -80});
			points.add(new double[]{0, 80});

			obstacles.add(new Obstacle(points));*/


			/*for(int i=0;i<22;i++){
			for(int i2=0;i2<22;i2++){
				if(i2 % 2==0) {
					particles.addElement(new Particle(i2*15-143,0,i*16-143,0,0,0,50,rand.nextInt(4)));
				}
				else {
					particles.addElement(new Particle(i2*15-143,0,i*16-135,0,0,0,50,rand.nextInt(4)));
				}
			}
		}*/

			//particles.addElement(new Particle(500,0,0,-20000,0,0,100,rand.nextInt(4)));
			//particles.addElement(new Particle(0,0,500,0,0,-20000,100,rand.nextInt(4)));
			//particles.addElement(new Particle(-500,0,0,20000,0,0,100,rand.nextInt(4)));
			//particles.addElement(new Particle(0,0,-500,0,0,20000,100,rand.nextInt(4)));

			//particles.addElement(new Particle(500,0,0,-20000,0,0,100,rand.nextInt(4)));
			//particles.addElement(new Particle(500,0,-90,-20000,0,0,100,rand.nextInt(4)));
			//particles.addElement(new Particle(500,0,90,-20000,0,0,100,rand.nextInt(4)));
			//particles.addElement(new Particle(500,0,180,-20000,0,0,100,rand.nextInt(4)));

			//particles.addElement(new Particle(-500,0,0,20000,0,0,100,rand.nextInt(4)));
			//particles.addElement(new Particle(-500,0,-90,20000,0,0,100,rand.nextInt(4)));
			//particles.addElement(new Particle(-500,0,90,20000,0,0,100,rand.nextInt(4)));
			//particles.addElement(new Particle(-500,0,180,20000,0,0,100,rand.nextInt(4)));

			/*particles.addElement(new Particle(180,-4500,0,300,100));
		particles.addElement(new Particle(180,4860,0,-300,100));
		particles.addElement(new Particle(4930,180,-300,0,100));
		particles.addElement(new Particle(-4570,180,300,0,100));*/


			/*particles.add(new Particle(400,330,-100,0,10,30,1));

		particles.add(new Particle(150+4*Math.sin(Math.PI/3)*30,330,0,0,10,30,1));

		particles.add(new Particle(150+3*Math.sin(Math.PI/3)*30,315,0,0,10,30,1));
		particles.add(new Particle(150+3*Math.sin(Math.PI/3)*30,345,0,0,10,30,1));

		particles.add(new Particle(150+2*Math.sin(Math.PI/3)*30,300,0,0,10,30,1));
		particles.add(new Particle(150+2*Math.sin(Math.PI/3)*30,330,0,0,10,30,1));
		particles.add(new Particle(150+2*Math.sin(Math.PI/3)*30,360,0,0,10,30,1));

		particles.add(new Particle(150+Math.sin(Math.PI/3)*30,285,0,0,10,30,1));
		particles.add(new Particle(150+Math.sin(Math.PI/3)*30,315,0,0,10,30,1));
		particles.add(new Particle(150+Math.sin(Math.PI/3)*30,345,0,0,10,30,1));
		particles.add(new Particle(150+Math.sin(Math.PI/3)*30,375,0,0,10,30,1));

		particles.add(new Particle(150,270,0,0,10,30,1));
		particles.add(new Particle(150,300,0,0,10,30,1));
		particles.add(new Particle(150,330,0,0,10,30,1));
		particles.add(new Particle(150,360,0,0,10,30,1));
		particles.add(new Particle(150,390,0,0,10,30,1));*/
		}
		else {

			/*public double mult_m_by=1.0/1000000000;
		public double mult_kg_by=Math.pow(10, -24);
		public double mult_s_by=10.0/(365*24*60*60);*/

			particles.add(new Particle(0,0,0,0,1.98855*Math.pow(10,30)*mult_kg_by,20,1)); //sun

			particles.add(new Particle(0,-46000000000.0*mult_m_by,-58980*mult_m_by/mult_s_by,0,0.3302*Math.pow(10,24)*mult_kg_by,10,1)); //mercury

			particles.add(new Particle(107480000000.0*mult_m_by*(-Math.sin(Math.PI/12)),107480000000.0*mult_m_by*Math.cos(Math.PI/12),
					35260*Math.cos(Math.PI/12)*mult_m_by/mult_s_by,35260*Math.sin(Math.PI/12)*mult_m_by/mult_s_by,
					4.8685*Math.pow(10,24)*mult_kg_by,10,1)); //venus

			particles.add(new Particle(0,-147090000000.0*mult_m_by,
					-30290*mult_m_by/mult_s_by,0,
					5.9736*Math.pow(10,24)*mult_kg_by,10,1)); //earth

			particles.add(new Particle(206620000000.0*mult_m_by*Math.cos(Math.PI/10),206620000000.0*mult_m_by*Math.sin(Math.PI/10),
					26500*Math.sin(Math.PI/10)*mult_m_by/mult_s_by,-26500*Math.cos(Math.PI/10)*mult_m_by/mult_s_by,
					0.64185*Math.pow(10,24)*mult_kg_by,10,1)); //mars


			/*public double mult_m_by=400.0/12742000;
		public double mult_kg_by=Math.pow(10, -24);
		public double mult_s_by=1.0;

		particles.add(new Particle(0,0,0,0,0,0,5.9736*Math.pow(10,24)*mult_kg_by,12742000*mult_m_by,1)); //earth
		particles.add(new Particle(0,0,-((12742000/2)+408000)*mult_m_by,7670*mult_m_by/mult_s_by,0,0,420000*mult_kg_by,10,1));//iss*/


			/*public double mult_m_by=1.0/1000000;
		public double mult_kg_by=Math.pow(10, -24);
		public double mult_s_by=1.0/(24*60*60);

		particles.add(new Particle(0,0,0,0,0,0,5.9736*Math.pow(10,24)*mult_kg_by,20,1)); //earth
		particles.add(new Particle(0,0,-362600000*mult_m_by,1082*mult_m_by/mult_s_by,0,0,7.3476*Math.pow(10,22)*mult_kg_by,20,1));//moon
			 */
		}

	}

	public void update(double t){

		if(gravity == Gravity.Tree) {
			octree = new Octree(particles,g);

			octree.calculate_acc_vel_pos(particles, t);
		}
		else if(gravity == Gravity.Iterative) {

			for(int i =0;i< particles.size();i++){
				Particle p1 = particles.get(i);

				for(int i2=i+1;i2<particles.size();i2++){	
					updateAcceleration(p1,particles.get(i2));
				}

				p1.updateVel(t);
				p1.updatePos(t);
				p1.setAcc0();
			}
		}
		else if(gravity == Gravity.NoGravityInteraction) {
			for(int i =0;i< particles.size();i++){
				Particle p1 = particles.get(i);
				
				p1.updateVel(t);
				p1.updatePos(t);
			}
		}
		
		switch(mode) {
		case Merging:
			update_collisions_merging();
			break;
		case PoolBallsFast:
		case PoolBallsAccurate:
			update_perfectly_elastic_collisions();
			break;
		case PoolBallsFastAccurate:
			update_collisions_PoolBallsFastAcccurate();
			break;
		default:
			break;
		}

		if(enable_obstacles) {
			update_obstacle_collisions();
		}
	}

	public void update_obstacle_collisions() {
		for(int i =0;i< particles.size();i++){
			for(int i2=0; i2 < obstacles.size();i2++) {
				check_obstacle_collision(particles.get(i),obstacles.get(i2));
			}
		}
	}

	public boolean check_obstacle_collision(Particle p, Obstacle o) {
		//sides
		for(int i = 0; i < o.points.size(); i++) {
			if(i+1 == o.points.size()) {
				check_line_obstacle_collision(o.points.get(i),o.points.get(0), p);
			}
			else {
				check_line_obstacle_collision(o.points.get(i),o.points.get(i+1), p);
			}
		}

		//points

		for(int i = 0; i < o.points.size(); i++) {
			check_point_obstacle_collision(o.points.get(i), p);
		}

		return false;
	}

	public boolean check_line_obstacle_collision(double[] p1,double[] p2, Particle p) {
		double vector_x=p2[0]-p1[0];
		double vector_y=p2[1]-p1[1];

		double vector_t=Math.sqrt(Math.pow(vector_x,2) + Math.pow(vector_y,2));

		vector_x/=vector_t;
		vector_y/=vector_t;

		double k=vector_x*(p.pos[0]-p1[0])+vector_y*(p.pos[1]-p1[1]);
		
		double x=p1[0]+k*vector_x;
		double y=p1[1]+k*vector_y;
		
		//
		
		double vector_2_x= x-p.pos[0];
		double vector_2_y= y-p.pos[1];

		double vector_2_t=Math.sqrt(Math.pow(vector_2_x,2) + Math.pow(vector_2_y,2));

		vector_2_x/=vector_2_t;
		vector_2_y/=vector_2_t;

		if(vector_2_t <= p.diameter/2) {
			if(Math.sqrt(Math.pow(p2[0]-x,2) + Math.pow(p2[1]-y,2)) <= vector_t && Math.sqrt(Math.pow(p1[0]-x,2) + Math.pow(p1[1]-y,2)) <= vector_t) {
				double offset = (p.diameter/2)+extra-vector_2_t;
				
				p.pos[0] = p.pos[0] - offset*vector_2_x;
				p.pos[1] = p.pos[1] - offset*vector_2_y;

				double eVel=p.vel[0]*vector_2_x+p.vel[1]*vector_2_y;

				if(eVel > 0) {
					p.vel[0]+=-2*eVel*vector_2_x;
					p.vel[1]+=-2*eVel*vector_2_y;
				}
				
				return true;
			}
		}

		return false;
	}


	public boolean check_point_obstacle_collision(double[] p1, Particle p) {
		double ex=p1[0]-p.pos[0];
		double ey=p1[1]-p.pos[1];

		double et=Math.sqrt(Math.pow(ex,2) + Math.pow(ey,2));

		ex/=et;
		ey/=et;

		if(et <= p.diameter/2) {
			double offset = (p.diameter/2)+extra-et;
			
			p.pos[0] = p.pos[0] - offset*ex;
			p.pos[1] = p.pos[1] - offset*ey;
			
			double eVel=p.vel[0]*ex+p.vel[1]*ey;

			if(eVel > 0) {
				p.vel[0]+=-2*eVel*ex;
				p.vel[1]+=-2*eVel*ey;
			}
			
			return true;
		}

		return false;
	}

	public void update_collisions_merging(){
		double dx;
		double dy;
		double r;
		double mass;
		double velX;
		double velY;
		double posX;
		double posY;
		int color=0;

		for(int i =0;i< particles.size();i++){
			for(int i2=i+1;i2<particles.size();i2++){	
				Particle p1 = particles.get(i);
				Particle p2 = particles.get(i2);

				dx=p1.pos[0]-p2.pos[0];
				dy=p1.pos[1]-p2.pos[1];
				r=Math.sqrt(dx*dx+dy*dy);

				if(r<=(p1.diameter/2)+(p2.diameter/2)){

					mass=p1.mass+p2.mass;
					posX=(p1.pos[0]*(p1.mass/mass))+(p2.pos[0]*(p2.mass/mass));
					posY=(p1.pos[1]*(p1.mass/mass))+(p2.pos[1]*(p2.mass/mass));
					velX=((p1.mass*p1.vel[0])+(p2.mass*p2.vel[0]))/mass;
					velY=((p1.mass*p1.vel[1])+(p2.mass*p2.vel[1]))/mass;

					if(p1.mass > p2.mass) {
						if(p1.color == Color.RED) {
							color=0;
						}
						else if(p1.color == Color.BLUE) {
							color=1;
						}
						else if(p1.color == Color.GREEN) {
							color=2;
						}
						else if(p1.color == Color.MAGENTA) {
							color=3;
						}
					}
					else {
						if(p2.color == Color.RED) {
							color=0;
						}
						else if(p2.color == Color.BLUE) {
							color=1;
						}
						else if(p2.color == Color.GREEN) {
							color=2;
						}
						else if(p2.color == Color.MAGENTA) {
							color=3;
						}
					}

					particles.add(i,new Particle(posX,posY,velX,velY,mass,color));

					particles.remove(i2+1);
					particles.remove(i+1);
					i2--;
				}		
			}
		}
	}


	public void update_perfectly_elastic_collisions(){
		double dx;
		double dy;
		double r;
		double x;
		double y;
		double ex[]=new double[2];
		double v1_ex;
		double v2_ex;
		double v1_ex_after=0;
		double v2_ex_after=0;
		double targetDistance;
		double proportion1=0.5;
		double proportion2=0.5;


		for(int i =0;i< particles.size();i++){
			Particle p1 = particles.get(i);

			for(int i2=i+1;i2<particles.size();i2++){	
				Particle p2 = particles.get(i2);

				dx=p1.pos[0]-p2.pos[0];
				dy=p1.pos[1]-p2.pos[1];
				r=Math.sqrt(dx*dx+dy*dy);
				targetDistance=(p1.diameter+p2.diameter)/2;

				if(r<=targetDistance){

					ex[0]=dx/r;
					ex[1]=dy/r;

					v1_ex=p1.vel[0]*ex[0]+p1.vel[1]*ex[1];

					v2_ex=p2.vel[0]*ex[0]+p2.vel[1]*ex[1];


					v1_ex_after=((p1.mass-p2.mass)*v1_ex + 2*p2.mass*v2_ex)/(p1.mass+p2.mass);
					
					v2_ex_after=((p2.mass-p1.mass)*v2_ex + 2*p1.mass*v1_ex)/(p1.mass+p2.mass);
					

					if(Math.abs(v1_ex_after)==Math.abs(v2_ex_after)) {
						proportion1=0.5;
						proportion2=0.5;
					}
					else if(Math.abs(v1_ex_after) > Math.abs(v2_ex_after)) {
						proportion1=1;
						proportion2=0;
					}
					else {
						proportion1=0;
						proportion2=1;
					}

					p1.vel[0]=p1.vel[0]+((v1_ex_after - v1_ex)*ex[0]);
					p1.vel[1]=p1.vel[1]+((v1_ex_after - v1_ex)*ex[1]);

					p2.vel[0]=p2.vel[0]+((v2_ex_after - v2_ex)*ex[0]);
					p2.vel[1]=p2.vel[1]+((v2_ex_after - v2_ex)*ex[1]);


					x=(targetDistance+extra-r)*ex[0];
					y=(targetDistance+extra-r)*ex[1];


					p1.pos[0]=p1.pos[0]+proportion1*x;
					p2.pos[0]=p2.pos[0]-proportion2*x;

					p1.pos[1]=p1.pos[1]+proportion1*y;
					p2.pos[1]=p2.pos[1]-proportion2*y;

					if(this.mode == Mode.PoolBallsAccurate) {
						i=-1;
						break;
					}
				}
			}		
		}
	}
	
	/*public void update_collisions_PoolBallsAccurate(){
		for(int i =0;i< particles.size();i++){
			Particle p1 = particles.get(i);

			for(int i2=i+1;i2<particles.size();i2++){	
				Particle p2 = particles.get(i2);

				if(handleBallBallCollision(p1, p2)) {
					i = -1;
					break;
				}
			}		
		}
	}
	
	public boolean handleBallBallCollision(Particle p1, Particle p2) {
		double dx=p1.pos[0]-p2.pos[0];
		double dy=p1.pos[1]-p2.pos[1];
		double d=Math.sqrt(dx*dx+dy*dy);
		double targetDistance=(p1.diameter+p2.diameter)/2;

		if(d<targetDistance){
			double a=Math.pow(p1.vel[0]-p2.vel[0], 2)+Math.pow(p1.vel[1]-p2.vel[1], 2);
			double b=(2*p1.pos[0]-2*p2.pos[0])*(p1.vel[0]-p2.vel[0])+(2*p1.pos[1]-2*p2.pos[1])*(p1.vel[1]-p2.vel[1]);
			double c=Math.pow(p1.pos[0], 2)+Math.pow(p2.pos[0], 2)+Math.pow(p1.pos[1], 2)+Math.pow(p2.pos[1], 2)-2*p1.pos[0]*p2.pos[0]-2*p1.pos[1]*p2.pos[1]-Math.pow(targetDistance, 2);
			
			if(b*b-4*a*c >= 0 && 2*a != 0) {
				double t = (-b-Math.sqrt(b*b-4*a*c))/(2*a);
				
				p1.pos[0] = p1.pos[0] + p1.vel[0]*t;
				p1.pos[1] = p1.pos[1] + p1.vel[1]*t;
				
				p2.pos[0] = p2.pos[0] + p2.vel[0]*t;
				p2.pos[1] = p2.pos[1] + p2.vel[1]*t;
			}
			else {
				double x=(targetDistance-d)*(dx/d);
				double y=(targetDistance-d)*(dy/d);


				p1.pos[0]=p1.pos[0]+0.5*x;
				p2.pos[0]=p2.pos[0]-0.5*x;

				p1.pos[1]=p1.pos[1]+0.5*y;
				p2.pos[1]=p2.pos[1]-0.5*y;
			}
			
			dx=p1.pos[0]-p2.pos[0];
			dy=p1.pos[1]-p2.pos[1];
			d=Math.sqrt(dx*dx+dy*dy);
			
			double[] ex = new double[2];
			
			ex[0]=dx/d;
			ex[1]=dy/d;

			double v1_ex=p1.vel[0]*ex[0]+p1.vel[1]*ex[1];

			double v2_ex=p2.vel[0]*ex[0]+p2.vel[1]*ex[1];


			double v2_ex_after=((p2.mass-p1.mass)*v2_ex + 2*p1.mass*v1_ex)/(p2.mass+p1.mass);

			double v1_ex_after=v2_ex-v1_ex+v2_ex_after;

			p1.vel[0]=p1.vel[0]+((v1_ex_after - v1_ex)*ex[0]);
			p1.vel[1]=p1.vel[1]+((v1_ex_after - v1_ex)*ex[1]);

			p2.vel[0]=p2.vel[0]+((v2_ex_after - v2_ex)*ex[0]);
			p2.vel[1]=p2.vel[1]+((v2_ex_after - v2_ex)*ex[1]);
			
			return true;
		}
		
		return false;
	}*/


	public void update_collisions_PoolBallsFastAcccurate(){
		int next=1;
		while(true) {
			if(next==1) {
				next=update_collisions_PoolBallsFastAcccurate_1();
			}
			else if(next == 2) {
				next=update_collisions_PoolBallsFastAcccurate_2();
			}
			else {
				break;
			}
		}
	}

	public int update_collisions_PoolBallsFastAcccurate_1(){
		double dx;
		double dy;
		double r;
		double x;
		double y;
		double ex[]=new double[2];
		double v1_ex;
		double v2_ex;
		double v1_ex_after=0;
		double v2_ex_after=0;
		double targetDistance;
		double proportion1=0.5;
		double proportion2=0.5;


		for(int i =0;i< particles.size();i++){
			Particle p1 = particles.get(i);

			for(int i2=i+1;i2<particles.size();i2++){	
				Particle p2 = particles.get(i2);

				dx=p1.pos[0]-p2.pos[0];
				dy=p1.pos[1]-p2.pos[1];
				r=Math.sqrt(dx*dx+dy*dy);
				targetDistance=(p1.diameter+p2.diameter)/2;

				if(r<=targetDistance){

					ex[0]=dx/r;
					ex[1]=dy/r;

					v1_ex=p1.vel[0]*ex[0]+p1.vel[1]*ex[1];

					v2_ex=p2.vel[0]*ex[0]+p2.vel[1]*ex[1];


					v1_ex_after=((p1.mass-p2.mass)*v1_ex + 2*p2.mass*v2_ex)/(p1.mass+p2.mass);

					v2_ex_after=((p2.mass-p1.mass)*v2_ex + 2*p1.mass*v1_ex)/(p1.mass+p2.mass);

					
					if(Math.abs(v1_ex_after)==Math.abs(v2_ex_after)) {
						proportion1=0.5;
						proportion2=0.5;
					}
					else if(Math.abs(v1_ex_after) > Math.abs(v2_ex_after)) {
						proportion1=1;
						proportion2=0;
					}
					else {
						proportion1=0;
						proportion2=1;
					}

					p1.vel[0]=p1.vel[0]+((v1_ex_after - v1_ex)*ex[0]);
					p1.vel[1]=p1.vel[1]+((v1_ex_after - v1_ex)*ex[1]);

					p2.vel[0]=p2.vel[0]+((v2_ex_after - v2_ex)*ex[0]);
					p2.vel[1]=p2.vel[1]+((v2_ex_after - v2_ex)*ex[1]);


					x=(targetDistance+extra-r)*ex[0];
					y=(targetDistance+extra-r)*ex[1];


					p1.pos[0]=p1.pos[0]+proportion1*x;
					p2.pos[0]=p2.pos[0]-proportion2*x;

					p1.pos[1]=p1.pos[1]+proportion1*y;
					p2.pos[1]=p2.pos[1]-proportion2*y;

					if(i > particles.size()/2) {
						return 2;
					}
					else {
						return 1;
					}
				}
			}		
		}

		return 0;
	}

	public int update_collisions_PoolBallsFastAcccurate_2(){
		double dx;
		double dy;
		double r;
		double x;
		double y;
		double ex[]=new double[2];
		double v1_ex;
		double v2_ex;
		double v1_ex_after=0;
		double v2_ex_after=0;
		double targetDistance;
		double proportion1=0.5;
		double proportion2=0.5;


		for(int i =particles.size()-1;i>=0;i--){
			Particle p1 = particles.get(i);

			for(int i2=i-1;i2>=0;i2--){	
				Particle p2 = particles.get(i2);

				dx=p1.pos[0]-p2.pos[0];
				dy=p1.pos[1]-p2.pos[1];
				r=Math.sqrt(dx*dx+dy*dy);
				targetDistance=(p1.diameter+p2.diameter)/2;

				if(r<=targetDistance){

					ex[0]=dx/r;
					ex[1]=dy/r;

					v1_ex=p1.vel[0]*ex[0]+p1.vel[1]*ex[1];

					v2_ex=p2.vel[0]*ex[0]+p2.vel[1]*ex[1];

					
					v1_ex_after=((p1.mass-p2.mass)*v1_ex + 2*p2.mass*v2_ex)/(p1.mass+p2.mass);

					v2_ex_after=((p2.mass-p1.mass)*v2_ex + 2*p1.mass*v1_ex)/(p1.mass+p2.mass);
					

					if(Math.abs(v1_ex_after)==Math.abs(v2_ex_after)) {
						proportion1=0.5;
						proportion2=0.5;
					}
					else if(Math.abs(v1_ex_after) > Math.abs(v2_ex_after)) {
						proportion1=1;
						proportion2=0;
					}
					else {
						proportion1=0;
						proportion2=1;
					}

					p1.vel[0]=p1.vel[0]+((v1_ex_after - v1_ex)*ex[0]);
					p1.vel[1]=p1.vel[1]+((v1_ex_after - v1_ex)*ex[1]);

					p2.vel[0]=p2.vel[0]+((v2_ex_after - v2_ex)*ex[0]);
					p2.vel[1]=p2.vel[1]+((v2_ex_after - v2_ex)*ex[1]);

					
					x=(targetDistance+extra-r)*ex[0];
					y=(targetDistance+extra-r)*ex[1];


					p1.pos[0]=p1.pos[0]+proportion1*x;
					p2.pos[0]=p2.pos[0]-proportion2*x;

					p1.pos[1]=p1.pos[1]+proportion1*y;
					p2.pos[1]=p2.pos[1]-proportion2*y;

					if(i < particles.size()/2) {
						return 1;
					}
					else {
						return 2;
					}
				}
			}		
		}

		return 0;
	}


	public void updateAcceleration(Particle p1, Particle p2){
		double dxP2=p1.pos[0]-p2.pos[0];
		double dyP2=p1.pos[1]-p2.pos[1];

		double r2=dxP2*dxP2+dyP2*dyP2;
		double r = Math.sqrt(r2);

		double versor_2[] = {dxP2/r,dyP2/r};

		double a2 = (g*p1.mass)/r2;
		double a1 = (g*p2.mass)/r2;

		p2.acc[0] = p2.acc[0] + versor_2[0]*a2;
		p2.acc[1] = p2.acc[1] + versor_2[1]*a2;

		p1.acc[0] = p1.acc[0] - versor_2[0]*a1;
		p1.acc[1] = p1.acc[1] - versor_2[1]*a1;
	}

}
