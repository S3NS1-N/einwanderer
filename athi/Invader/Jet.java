import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the Ship that the Player controls during the game. The "main man".
 * Controls are with A (move left) and D (move right), Space (fire).
 * 
 * @author Athavan Ranganathan
 * @version 3.0
 */
public class Jet extends Player {
    
    private int cooldown;
    private long lastShotTime;
    private GreenfootSound shot = new GreenfootSound("laser.mp3");
    
    /**
     * this method is only executed when the "act" or "run" button is pressed.
     * it controls the movement, player fire and checks for contact with alien-laser.
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
        if (Greenfoot.isKeyDown("left") && (getX() >= 15)) {
            setLocation(getX() - 2, getY());
        } else if ((getX() <= 15))  {
            setLocation(getX() + 1, getY());    
        }
        if (Greenfoot.isKeyDown("d") && (getX() <= 240)) {
            setLocation(getX() + 2, getY());
        } else if ((getX() >= 240)) {
            setLocation(getX() - 1, getY()); 
        }
        if (Greenfoot.isKeyDown("right") && (getX() <= 240)) {
            setLocation(getX() + 2, getY());
        } else if ((getX() >= 240)) {
            setLocation(getX() - 1, getY()); 
        }
    }
    private void fire(){
        if (Greenfoot.isKeyDown("space") && lastShotTime + cooldown <= getTime()){
            JLaser laser = new JLaser();
            getWorld().addObject(laser,getX(), getY() - 2);
            lastShotTime = getTime();
            
            shot.play();
        }
    }
    
    public Jet(){
        cooldown = 1000;
    } 

    private void checkForLaserContact(){
        if (isTouching(IVLaser.class)){
            removeTouching(IVLaser.class);
            deathAnimation();
            getWorld().removeObject(this);
            
        } else if (isTouching(Invader.class)){
            deathAnimation();
            ((Galaxy) getWorld()).gameOver();
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
