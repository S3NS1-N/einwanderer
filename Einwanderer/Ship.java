import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ship extends Player {
    
    private int cooldown;
    private int lastShotTime;
    private GreenfootSound shot = new GreenfootSound("laser.mp3");
    
    public Ship(){
        lastShotTime = -2;
        cooldown = 2;
    }
    /**
     * Act - do whatever the Ship wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        control();
    }
    private void control(){
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - 5, getY());
        } 
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + 5, getY());
        }
        /*if (Greenfoot.isKeyDown("w")) {
            setLocation(getX(), getY()-4);
        } */
        /*if (Greenfoot.isKeyDown("s")) {
            setLocation(getX(), getY()+4);
        }*/
    }
    private void fire(){
        if (lastShotTime + cooldown <= getTime()){
            plaser laser = new plaser();
            getWorld().addObject(laser,getX(), getY());
            lastShotTime = getTime();
            
            shot.play();
        }
    }
}
