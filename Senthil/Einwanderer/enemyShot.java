import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * enemyShot: Alien-Laser Class - Just moves and checks for edge collisions
 * 
 * @author Senthil Nagendran
 * @version 1.2
 */
public class enemyShot extends Actor
{
    /**
     * Act - do whatever the enemyShot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
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
