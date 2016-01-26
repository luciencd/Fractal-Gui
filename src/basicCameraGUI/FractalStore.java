package basicCameraGUI;

import java.util.ArrayList;

public class FractalStore {
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private double resolution;
	private ArrayList<Coordinate<Double,Double>> points;
	
	
	public FractalStore(double x1_,double y1_,double x2_,double y2_,double resolution_,ArrayList<Coordinate<Double,Double>> points_) {
		x1=x1_;
		y1=y1_;
		x2=x2_;
		y2=y2_;
		resolution = resolution_;
		points = points_;
	}
	public double getX1(){
		return x1;
	}
	public double getX2(){
		return x2;
	}
	public double getY1(){
		return y1;
	}
	public double getY2(){
		return y2;
	}
	public double getResolution(){
		return resolution;
	}
	public ArrayList<Coordinate<Double,Double>> getPoints(){
		return points;
	}
	
	public void print(){
		System.out.println("fractalStore print: x1("+x1+") x2("+x2+") y1("+y1+") y2("+y2+")"+"res("+resolution+")");
	}

}
