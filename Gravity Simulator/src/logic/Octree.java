package logic;

import java.util.ArrayList;

public class Octree {
	public Node children[];
	public double size;
	public double center[]; // center of mass is the center
	public double total_mass;
	public double threshold = 0.5;
	public double g;
	public ArrayList<double[]> squares;

	public Octree(ArrayList<Particle> p,double g) {
		this.squares= new ArrayList<double[]>();

		this.children= new Node[4];
		this.total_mass=0;
		this.g=g;

		calculate_center_and_size(p);
		createChildren();
		add(p);
	}

	public void calculate_acc_vel_pos(ArrayList<Particle> p,double t) {
		for(int i = 0; i < p.size(); i++) {
			Particle par = p.get(i);

			calculate_particle_acc(par);

			par.updateVel_Pos_setAcc0(t);
		}
	}

	public void calculate_particle_acc(Particle p) {
		double dx=center[0]-p.pos[0];
		double dy=center[1]-p.pos[1];
		double d2=dx*dx+dy*dy;
		double d=Math.sqrt(d2);

		if((size/d)<threshold) {
			double versor[] = {dx/d,dy/d};

			double a = (g*total_mass)/d2;

			p.acc[0] = p.acc[0] + versor[0]*a;
			p.acc[1] = p.acc[1] + versor[1]*a;
		}
		else {
			for(int i = 0; i < 4; i++) {
				children[i].calculate_particle_acc(p);
			}
		}
	}

	public void calculate_center_and_size(ArrayList<Particle> p) {
		double highest=0;
		double center[] = new double[] {0, 0};
		double mass=0;

		for(int i=0; i< p.size();i++) {
			Particle par = p.get(i);

			center[0]+=par.pos[0]*par.mass;
			center[1]+=par.pos[1]*par.mass;

			mass+=par.mass;
		}

		center[0]=center[0]/mass;
		center[1]=center[1]/mass;

		for(int i=0; i< p.size();i++) {
			Particle par = p.get(i);

			if(Math.abs(par.pos[0]-center[0]) > highest) {
				highest=Math.abs(par.pos[0]-center[0]);
			}

			if(Math.abs(par.pos[1]-center[1]) > highest) {
				highest=Math.abs(par.pos[1]-center[1]);
			}
		}

		this.center=center;
		this.size=highest*2;
	}

	public void add(ArrayList<Particle> p) {
		for(int i=0; i< p.size();i++) {
			Particle par = p.get(i);

			children[checkPos(par)].add(par);
			total_mass+=par.mass;
		}
	}

	public void createChildren() {
		children[0] = new Node(new double[]{center[0]-size/4, center[1]+size/4},size/2,g,threshold,squares);
		children[1] = new Node(new double[]{center[0]+size/4, center[1]+size/4},size/2,g,threshold,squares);
		children[2] = new Node(new double[]{center[0]+size/4, center[1]-size/4},size/2,g,threshold,squares);
		children[3] = new Node(new double[]{center[0]-size/4, center[1]-size/4},size/2,g,threshold,squares);
	}

	public int checkPos(Particle p) {
		if(p.pos[0]<=center[0] && p.pos[1] > center[1]) {
			return 0;
		}
		else if(p.pos[0]>center[0] && p.pos[1] > center[1]) {
			return 1;
		}
		else if(p.pos[0]>center[0] && p.pos[1] <= center[1]) {
			return 2;
		}
		else if(p.pos[0]<=center[0] && p.pos[1] <= center[1]) {
			return 3;
		}
		return -1;
	}
}
