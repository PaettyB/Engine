package de.paettyb.engine.utils;

public class Vec2i {
    public int x,y;
    
    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public  Vec2i(){
        x = 0;
        y = 0;
    }
    
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
