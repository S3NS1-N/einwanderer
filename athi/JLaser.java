import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Plaser (Player-Laser) Class: only does these two methods --> moves/look for Edge and checks for a collisions between Jet and Invader
 * 
 * @author Athavan Ranganathan
 * @version 3.0
 */
public class JLaser extends Actor
{
    public JLaser(){
        
    }
    /**
     * this method is only executed when the "act" or "run" button is pressed
     */
    public void act()
    {
        setLocation(getX(), getY() - 2);
        lookForEdge();
    }
    
    private void lookForEdge(){
        if (isAtEdge()){
            getWorld().removeObject(this);
        } 
    }
}
