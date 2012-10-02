package exams;

/**
 * SP06 Mid
 * 
 * @author david78k
 *
 */
public class Shape {
	int x, y, w, h;
	Object sizeLock = new Object();
	Object locationLock = new Object();
	
	synchronized void exchangeLocations(Shape other) {
		int tmpX = other.getX();
		int tmpY = other.getY();
		
		other.setX(getX()); other.setY(getY());
		setX(tmpX); setY(tmpY);
	}
	
	synchronized void adjustLocation(int x, int y) {
		setX(x); setY(y);
	}
	
	synchronized void adjustSize(int w, int h) {
		setW(w); setH(h);
	}
	
	synchronized int getX() {return x;}
	synchronized void setX(int x) {		this.x = x;	}
	synchronized int getY() {		return y;	}
	synchronized void setY(int y) {		this.y = y;	}
	synchronized int getW() {		return w;	}
	synchronized void setW(int w) {		this.w = w;	}
	synchronized int getH() {		return h;	}
	synchronized void setH(int h) {		this.h = h;	}
}

class ShapeSolution {
	int x, y, w, h;
	Object sizeLock = new Object();
//	Object locationLock = new Object();
	
	synchronized void exchangeLocations(Shape other) {
		int tmpX = other.getX();
		int tmpY = other.getY();
		
		other.adjustLocation(getX(), getY());
		adjustLocation(tmpX, tmpY); 
//		other.setX(getX()); other.setY(getY());
//		setX(tmpX); setY(tmpY);
	}
	
	synchronized void adjustLocation(int x, int y) {
		setX(x); setY(y);
	}
	
	 void adjustSize(int w, int h) {
		synchronized(sizeLock) {
			setW(w); setH(h);
		}
	}
	
	synchronized int getX() {return x;}
	synchronized void setX(int x) {		this.x = x;	}
	synchronized int getY() {		return y;	}
	synchronized void setY(int y) {		this.y = y;	}
	synchronized int getW() {		return w;	}
	synchronized void setW(int w) {		this.w = w;	}
	synchronized int getH() {		return h;	}
	synchronized void setH(int h) {		this.h = h;	}
}