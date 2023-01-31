import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is the space-ship that the player controls and fires on the invaders with. Controls with A and D, Space to fire.
 * 
 * @author Oliver Ammann
 * @version 3.0
 */
public class Ship extends Actor {
    
    private int cooldown;
    private long lastShotTime;
    private GreenfootSound shot = new GreenfootSound("laser.mp3");
    
    private boolean upgrade = false;
    private int upgradeCounter = 20;
    
    public Ship(){
        lastShotTime = getTime() - 1000;
        cooldown = 1000;
    }
    /**
     * Act method controls all the movement and lets the player fire and also checks for contact with alien-laser.
     */
    public void act()
    {
        control();
        fire();
        checkForLaserContact();   
    }
    
    private void control(){
        if (Greenfoot.isKeyDown("a") && (getX() >= 15)) {
            setLocation(getX() - 1, getY());
        } else if ((getX() <= 15))  {
            setLocation(getX() + 1, getY());    
        }
        if (Greenfoot.isKeyDown("d") && (getX() <= 240)) {
            setLocation(getX() + 1, getY());
        } else if ((getX() >= 240)) {
            setLocation(getX() - 1, getY()); 
        }
    }
    private void fire(){
        if (Greenfoot.isKeyDown("space") && lastShotTime + cooldown <= getTime()){
            if (upgrade){
                if (upgradeCounter > 0){
                    Plaser laser1 = new Plaser();
                    Plaser laser2 = new Plaser();
                    getWorld().addObject(laser1,getX() + 2, getY() - 2);
                    getWorld().addObject(laser2,getX() - 2, getY() - 2);
                    lastShotTime = getTime();
                } else {
                    upgrade = false;
                }
                
            } else {
                upgradeCounter = 20;
                
                Plaser laser = new Plaser();
                getWorld().addObject(laser,getX(), getY() - 2);
                lastShotTime = getTime();
                
                shot.play();
            }
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
    
    private long getTime(){
        return System.currentTimeMillis();
    }
    
    /**
     * Method to set the upgrade status for player ship (double the shots)
     */
    public void setUpgrade(){
        upgrade = true;
    }
    
    /**
     * Warp Animation between Waves
     */
    public void warp(){
        Warp warp = new Warp();
        getWorld().addObject(warp, getX(), 40);
        Greenfoot.playSound("lightspeed.mp3");
        Greenfoot.delay(320);
        while(!isTouching(Warp.class)){
            setLocation(getX(), getY() - 3);
            Greenfoot.delay(1);
        }
        Greenfoot.playSound("alert.mp3");
    }
}
