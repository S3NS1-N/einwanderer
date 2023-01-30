import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
     * Act - do whatever the Ship wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        control();
        fire();
        checkForLaserContact();    
    }
    private void control(){
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - 2, getY());
        } 
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + 2, getY());
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
        }
    }
    
    private void deathAnimation(){
        setImage("shipExplosion0.png");  
        Greenfoot.delay(50);
        setImage("shipExplosion1.png");  
        Greenfoot.delay(50);
    }
}
