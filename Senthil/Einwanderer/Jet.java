import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * "Blueprint" for fighterjet that the player controls.
 * Inherits from player class.
 * 
 * @author Senthil Nagendran
 * @version 1.2
 */
public class Jet extends Player {
    private int cooldown;
    private long lastShotTime;
    private GreenfootSound shotSound = new GreenfootSound("laser.mp3");
    
    /**
     * Constructor for Jet class.
     */
    public Jet(){
        lastShotTime = -1200;
        cooldown = 1200;
    }
    /**
     * Actor method controls all the movement, lets the player fire and checks for contact with alienshot.
     */
    public void act()
    {
        control();
        fire();
        checkForShotContact();   
    }
    
    //Left to right movement with a certain margin to the edge.
    private void control(){
        if (Greenfoot.isKeyDown("left") && (getX() >= 15)) {
            setLocation(getX() - 2, getY());
        } else if ((getX() <= 15))  {
            setLocation(getX() + 1, getY());    
        }
        if (Greenfoot.isKeyDown("right") && (getX() <= 240)) {
            setLocation(getX() + 2, getY());
        } else if ((getX() >= 240)) {
            setLocation(getX() - 1, getY()); 
        }
    }
    
    //Jet shoots with a certain cooldown. Bullet is spawned in front of jet(Looks better).
    private void fire(){
        if (Greenfoot.isKeyDown("space") && lastShotTime + cooldown <= getTime()){
            playerShot shot = new playerShot();
            getWorld().addObject(shot,getX(), getY() - 2);
            //lastShotTime = getTime();
            
            shotSound.play();
        }
    }
    
    //If Player gets hit by bullet->lose one life. If Player gets collides with enemy->game over
    private void checkForShotContact(){
        if (isTouching(enemyShot.class)){
            removeTouching(enemyShot.class);
            deathAnimation();
            getWorld().removeObject(this);
            
        } else if (isTouching(Alien.class)){
            deathAnimation();
            ((Space) getWorld()).gameOver();
        }
    }
    
    //self-explantory
    private void deathAnimation(){
        Greenfoot.playSound("shipExplosion.wav");
        setImage("shipExplosion0.png");  
        Greenfoot.delay(50);
        setImage("shipExplosion1.png");  
        Greenfoot.delay(50);
    }
}
