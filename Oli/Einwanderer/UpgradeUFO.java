import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The UpgradeUFO has a change to appear every 10 seconds above the invaders - When it gets hit by the player - the player gets an upgrade
 * 
 * @author Oliver Ammann
 * @version 3.0
 */
public class UpgradeUFO extends Actor
{
    public UpgradeUFO(){
        
    }
    /**
     * Act - Method moves the ship from left to right and checks for contacts with edge or player shot
     */
    public void act()
    {
        setLocation(getX() + 1, getY());
        checkForContact();
    }
    
    /**
     * Checks for edge-contact or contact with player shot and reacts
     */
    public void checkForContact(){
        if (isTouching(Plaser.class)){
            removeTouching(Plaser.class);
            
            setImage("invaderExplosion.png");
            Greenfoot.playSound("mysteryShipSound.wav");
            Greenfoot.delay(2);
            
            ((Space)getWorld()).getObjects(Ship.class).get(0).setUpgrade();
            
            getWorld().removeObject(this);
            
            
        } else if (isAtEdge()){
            getWorld().removeObject(this);
        } 
    }
}
