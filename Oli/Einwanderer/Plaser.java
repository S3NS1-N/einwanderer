import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Alaser: Player-Laser Class - Just moves and checks for edge collisions
 * 
 * @author Oliver Ammann
 * @version 3.0
 */
public class Plaser extends Actor
{
    public Plaser(){
        
    }
    /**
     * Act - do whatever the Plaser wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(getX(), getY() - 2);
        checkEdgeContact();
    }
    
    private void checkEdgeContact(){
        if (isAtEdge()){
            getWorld().removeObject(this);
        } 
    }
}
