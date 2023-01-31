import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Plaser: Player-Laser Class - Just moves and checks for edge collisions
 * 
 * @author Oliver Ammann
 * @version 3.0
 */
public class Plaser extends Actor
{
    public Plaser(){
        
    }
    /**
     * Act - Just moves the laser and checks for Edge-Contact
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
