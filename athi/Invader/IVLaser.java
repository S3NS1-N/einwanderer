import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * IVLaser (Invader Laser): checks for edge collisions
 * 
 * @author Athavan Ranganathan
 * @version 3.0
 */
public class IVLaser extends Actor
{
    /**
     * Act - do whatever the Alaser wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public IVLaser(){
        
    }
    public void act()
    {
        setLocation(getX(), getY() + 1);
        isOntheEdge();
    }
    
    private void isOntheEdge(){
        if (isAtEdge()){
            getWorld().removeObject(this);
        } 
    }
}
