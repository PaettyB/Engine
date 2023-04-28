package de.paettyb.engine.utils;

public class Vec2f {
    public float x,y;
    
    public Vec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public Vec2f() {
        this.y = 0;
        this.x = 0;
    }
    
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
