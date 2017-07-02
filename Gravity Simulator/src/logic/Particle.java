package logic;

public class Particle {
	public double posX;
	public double posY;
	public double velX;
	public double velY;
	public double accX;
	public double accY;
	public double mass;
	public boolean delete;
	public double diameter;
	
	
	public Particle(double posX,double posY,double velX,double velY,double mass){
		this.posX=posX;
		this.posY=posY;
		this.velX=velX;
		this.velY=velY;
		this.accX=0;
		this.accY=0;
		this.mass=mass;
		this.delete=false;
		
		diameter=2*Math.pow(mass/4, (double)1/3)*1.7;
	}
	
	public void interactAcc(Particle p,double g){
		updateAcc(p,g);
	}
	
	public void interact(double t){
		updateVel(t);
		updatePos(t);
	}
	
	public void updateAcc(Particle p,double g){
		double dx=posX-p.posX;
		double dy=posY-p.posY;
		double r2=dx*dx+dy*dy;
		double denominator=Math.pow(r2, 1.5);
		
		p.accX=p.accX+(g*mass*dx)/denominator;
		p.accY=p.accY+(g*mass*dy)/denominator;
	}
	
	public void updateVel(double t){
		velX=velX+accX*t;
		velY=velY+accY*t;
	}

	public void updatePos(double t){
		/*posX=posX+velX*t+0.5*accX*t*t;
		posY=posY+velY*t+0.5*accY*t*t;*/
		
		posX=posX+velX*t;
		posY=posY+velY*t;
		
	}
}
