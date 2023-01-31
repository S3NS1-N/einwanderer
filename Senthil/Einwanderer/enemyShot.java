import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class inherits from Actor class.
 * Checks if the bullet's touching the edge and moves forward.
 * 
 * @author Senthil Nagendran
 * @version 1.2
 */
public class enemyShot extends Actor
{
    /**
     * This method is called when the 'Act' or 'Run' button 
     * is pressed in the environment.
     */
    public enemyShot(){
        
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
