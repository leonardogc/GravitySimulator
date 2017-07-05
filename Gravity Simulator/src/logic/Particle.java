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
		
		diameter=2*Math.pow(mass/4, (double)1/3)*3.2;
	}
	
	public Particle(double posX,double posY,double velX,double velY,double mass,double diameter){
		this.posX=posX;
		this.posY=posY;
		this.velX=velX;
		this.velY=velY;
		this.accX=0;
		this.accY=0;
		this.mass=mass;
		this.delete=false;
		this.diameter=diameter;
	}
	
	
	public void updateVelocityAndPosition(double t){
		updateVel(t);
		updatePos(t);
	}
	
	
	public void updateVel(double t){
		velX=velX+accX*t;
		velY=velY+accY*t;
	}

	public void updatePos(double t){
		
		posX=posX+velX*t;
		posY=posY+velY*t;
	}
}
