import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * "second" invader class. this class uses primarily the methods from the parant class. it contains just the following method: act()
 * 
 * @author Athavan Ranganathan
 * @version 3.0
 */
public class InvaderDos extends Invader
{
    private int minXspace;
    private int maxXMovement;
    private long lastMoveTime;
    private long lastShotTime;

    
    private int FireProb;
    
    private int wave;
    
    private GreenfootImage image1 = new GreenfootImage("CrabInvader0.png");
    private GreenfootImage image2 = new GreenfootImage("CrabInvader1.png");
    
    /**
     * this (main) method does the following: check for contact with plaser, movement and cooldowns (loop). the diffrence to the first invader is the lower cooldown.
     */
    public void act()
    {
        if (lastMoveTime + getMovementGalaxyDelay(wave) <= getTime()){
            setImage(image1);
            moveLeftRight(minXspace, maxXMovement);
            lastMoveTime = getTime();
        } else if (lastShotTime + 600 <= getTime()){
            verifyNshoot(7 - wave);
            lastShotTime = getTime();
        }
        if (lastMoveTime + 400 <= getTime()){
            setImage(image2);
        }
        checkforLaserContact();
    }
    
    /**
     * This class is the class of the second kind of invader. It just contains the act() method and mainly utilises methods from the parent class.
     * @param wave the current wave
     * @param row the row the specific invader is in on screen
     * @param startx the start X coordinate of the invader
     */
    public InvaderDos(int wave, int row, int startx){
        this.minXspace = startx;
        this.maxXMovement = getMaxGalaxyXMovement(minXspace);
        this.wave = wave;
        
        lastMoveTime = setFirstMovementGalaxyDelay(row);
        lastShotTime = getTime();
        FireProb = (int) 8 / (wave + 1);
    }
}
