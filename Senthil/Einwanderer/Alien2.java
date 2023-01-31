import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is for the crab alien. 
 * Only contains the act() method and mainly utilises methods from the parent class.
 * 
 * @author Senthil Nagendran
 * @version 1.2
 */
public class Alien2 extends Alien
{
    private long lastMoveTime;
    private long lastShotTime;
    private int minXMovement;
    private int maxXMovement;
    private int wave;
    
    private GreenfootImage image1 = new GreenfootImage("CrabInvader0.png");
    private GreenfootImage image2 = new GreenfootImage("CrabInvader1.png");
    /**
     * Constructor with params for Alien2 class.
     * Only consists of act() method and mainly utilises methods from the parent class.
     * @param wave the current wave
     * @param row the row which this alien is in 
     * @param startx the X coordinate  of the alien in the beginning
     */
    public Alien2(int wave, int row, int startx){
        this.minXMovement = startx;
        this.maxXMovement = getMaxXMovement(minXMovement);
        this.wave = wave;
        
        lastMoveTime = setFirstMovementDelay(row);
        lastShotTime = getTime();
    }
    
    /**
     * Game-Loop for fire cooldowns and movement and checking for contact with player laser (slightly lower cooldowns that first invader).
     */
    public void act()
    {
        if (lastMoveTime + getMovementDelay(wave) <= getTime()){
            setImage(image1);
            alienMovement(minXMovement, maxXMovement);
            lastMoveTime = getTime();
        } else if (lastShotTime + 600 <= getTime()){
            checkShotAndShoot(7 - wave);
            lastShotTime = getTime();
        }
        if (lastMoveTime + 400 <= getTime()){
            setImage(image2);
        }
        checkforShotContact();
    }
}
