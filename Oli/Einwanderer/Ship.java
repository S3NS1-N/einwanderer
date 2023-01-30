import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is the space-ship that the player controls and fires on the invaders with. Controls with A and D, Space to fire.
 * 
 * @author Oliver Ammann
 * @version 3.0
 */
public class Ship extends Player {
    
    private int cooldown;
    private long lastShotTime;
    private GreenfootSound shot = new GreenfootSound("laser.mp3");
    
    public Ship(){
        lastShotTime = -1200;
        cooldown = 1200;
    }
    /**
     * Actor method controls all the movement and lets the player fire and also checks for contact with alien-laser.
     */
    public void act()
    {
        control();
        fire();
        checkForLaserContact();   
    }
    
    private void control(){
        if (Greenfoot.isKeyDown("a") && (getX() >= 15)) {
            setLocation(getX() - 2, getY());
        } else if ((getX() <= 15))  {
            setLocation(getX() + 1, getY());    
        }
        if (Greenfoot.isKeyDown("d") && (getX() <= 240)) {
            setLocation(getX() + 2, getY());
        } else if ((getX() >= 240)) {
            setLocation(getX() - 1, getY()); 
        }
    }
    private void fire(){
        if (Greenfoot.isKeyDown("space") && lastShotTime + cooldown <= getTime()){
            Plaser laser = new Plaser();
            getWorld().addObject(laser,getX(), getY() - 2);
            lastShotTime = getTime();
            
            shot.play();
        }
    }
    private void checkForLaserContact(){
        if (isTouching(Alaser.class)){
            removeTouching(Alaser.class);
            deathAnimation();
            getWorld().removeObject(this);
            
        } else if (isTouching(Einwanderer.class)){
            deathAnimation();
            ((Space) getWorld()).gameOver();
        }
    }
    
    private void deathAnimation(){
        Greenfoot.playSound("shipExplosion.wav");
        setImage("shipExplosion0.png");  
        Greenfoot.delay(50);
        setImage("shipExplosion1.png");  
        Greenfoot.delay(50);
    }
}
