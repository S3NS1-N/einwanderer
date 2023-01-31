import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * "third" invader class. this class uses primarily the methods from the parant class. it contains just the following method: act()
 * 
 * @author Athavan Ranganathan
 * @version 3.0
 */
public class InvaderTres extends Invader
{
    private int minXMovement;
    private int maxXMovement;
    private long lastMoveTime;
    private long lastShotTime;
    
    private int FireProb;
    
    private int wave;
    
    private GreenfootImage image1 = new GreenfootImage("squidInvader1.png");
    private GreenfootImage image2 = new GreenfootImage("squidInvader0.png");
    
    /**
     * this (main) method does the following: check for contact with plaser, movement and cooldowns (loop). the diffrence to the first invader is the lower cooldown.
     */
    public void act()
    {
        if (lastMoveTime + getMovementGalaxyDelay(wave) <= getTime()){
            setImage(image1);
            moveLeftRight(minXMovement,maxXMovement);
            lastMoveTime = getTime();
        } else if (lastShotTime + 400 <= getTime()) {
            verifyNshoot(6 - wave);
            lastShotTime = getTime();   
        }
        if (lastMoveTime + 400 <= getTime()){
            setImage(image2);
        }
        checkforLaserContact();
    }
    
    /**
     * @param wave the current wave
     * @param row the row the specific invader is in on screen
     * @param startx the start X coordinate of the invader
     */
    public InvaderTres(int wave, int row, int startx){
        this.minXMovement = startx;
        this.maxXMovement = getMaxGalaxyXMovement(minXMovement);
        this.wave = wave;
        
        lastShotTime = getTime();
        lastMoveTime = setFirstMovementGalaxyDelay(row);
        FireProb = (int) 6 / (wave + 1);
    }
}
