import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * "first" invader class. this class uses primarily the methods from the parant class. it contains just the following method: act()
 * 
 * @author Athavan Ranganathan
 * @version 3.0
 */
public class InvaderUno extends Invader
{
    private int minXMovement;
    private int maxXMovement;
    private long lastMoveTime;
    private long lastShotTime;

    private int FireProb;
    
    private int wave;
    
    private GreenfootImage image1 = new GreenfootImage("JellyFishInvader1.png");
    private GreenfootImage image2 = new GreenfootImage("JellyFishInvader0.png");
    
    /**
     * this (main) method does the following: check for contact with plaser, movement and cooldowns (loop).
     */
    public void act()
    {
        if (lastMoveTime + getMovementGalaxyDelay(wave) <= getTime()){
            setImage(image2);
            moveLeftRight(minXMovement,maxXMovement);
            lastMoveTime = getTime();
        } else if (lastShotTime + 800 <= getTime()){
            verifyNshoot(8 - wave);
            lastShotTime = getTime();
        }   
        if (lastMoveTime + 400 <= getTime()){
            setImage(image1);
        }
        checkforLaserContact();
    }
    
    /**

     * @param wave the current wave
     * @param row the row the invader is in
     * @param startx the start X coordinate of the invader
     */
    public InvaderUno(int wave, int row, int startx){
        this.minXMovement = startx;
        this.maxXMovement = getMaxGalaxyXMovement(minXMovement);
        this.wave = wave;
        
        lastShotTime = getTime();
        lastMoveTime = setFirstMovementGalaxyDelay(row);
        FireProb = (int) 10 / (wave + 1);
    }
}
