import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Plaser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plaser extends Player
{
    private boolean positionlock;

    public Plaser(){
        //getImage().setTransparency(0);
        
        //Variable to get shot in position first
        positionlock = true;
    }
    /**
     * Act - do whatever the Plaser wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(getX(), getY() - 8);
    }
}
