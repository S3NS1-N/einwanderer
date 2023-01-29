import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Einwanderer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Einwanderer extends Actor
{
    private int leftOrRight = 1;
    private int stepSize = 5;
    
    public void moveSideToSide() {
        setLocation(getX() + stepSize * leftOrRight, getY());
    }
    public long getTime(){
        return System.currentTimeMillis();
    }
}
