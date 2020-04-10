package game;

public class Head {
	
	Dir dir = Dir.RIGHT;
	int x, y;
	
	public Head(int x, int y) {	
		this.x = x;
		this.y = y;		
	}

	public Dir getDir() {
		return dir;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
