package basicCameraGUI;

public class Coordinate<T,V>{
	public T x;
	public T y;
	private V value;
	public Coordinate(T x_, T y_){
		x= x_;
		y = y_;
	}
	
	public void setValue(V v){
		value = v;
	}
	public V getValue(){
		return value;
	}
}