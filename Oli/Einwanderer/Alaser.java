import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Alaser: Alien-Laser Class - Just moves and checks for edge collisions
 * 
 * @author Oliver Ammann
 * @version 3.0
 */
public class Alaser extends Actor
{
    /**
     * Act - do whatever the Alaser wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Alaser(){
        
    }
    public void act()
    {
        setLocation(getX(), getY() + 1);
        checkEdgeContact();
    }
    
    private void checkEdgeContact(){
        if (isAtEdge()){
            getWorld().removeObject(this);
        } 
    }
}
