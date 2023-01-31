import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is the space-ship that the player controls and fires on the invaders with. Controls with A and D, Space to fire.
 * 
 * @author Senthil Nagendran
 * @version 1.2
 */
public class Jet extends Player {
    
    private int cooldown;
    private long lastshotTime;
    private GreenfootSound shotSound = new GreenfootSound("laser.mp3");
    
    public Jet(){
        lastshotTime = -1200;
        cooldown = 1200;
    }
    /**
     * Actor method controls all the movement and lets the player fire and also checks for contact with alien-shot.
     */
    public void act()
    {
        control();
        fire();
        checkForshotContact();   
    }
    
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
    private void fire(){
        if (Greenfoot.isKeyDown("space") && lastshotTime + cooldown <= getTime()){
            playerShot shot = new playerShot();
            getWorld().addObject(shot,getX(), getY() - 2);
            lastshotTime = getTime();
            
            shotSound.play();
        }
    }
    private void checkForshotContact(){
        if (isTouching(enemyShot.class)){
            removeTouching(enemyShot.class);
            deathAnimation();
            getWorld().removeObject(this);
            
        } else if (isTouching(Alien.class)){
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
