import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is for the jellyfish alien. 
 * Only contains the act() method and mainly utilises methods from the parent class.
 * 
 * @author Senthil Nagendran
 * @version 1.2
 */
public class Alien1 extends Alien
{
    private long lastMoveTime;
    private long lastShotTime;
    private int minXMovement;
    private int maxXMovement;
    private int wave;
    
    private GreenfootImage image1 = new GreenfootImage("JellyFishInvader1.png");
    private GreenfootImage image2 = new GreenfootImage("JellyFishInvader0.png");
    
    /**
     * Constructor with params for Alien1 class.
     * Only consists of act() method and mainly utilises methods from the parent class.
     * @param wave the current wave
     * @param row the row which this alien is in 
     * @param startx the X coordinate  of the alien in the beginning
     */
    public Alien1(int wave, int row, int startx){
        this.minXMovement = startx;
        this.maxXMovement = getMaxXMovement(minXMovement);
        this.wave = wave;
        
        lastMoveTime = setFirstMovementDelay(row);
        lastShotTime = getTime();
    }
    
    /**
     * Loop for fire cooldown, movement and checking for contact with playershot.
     */
    public void act()
    {
        if (lastMoveTime + getMovementDelay(wave) <= getTime()){
            setImage(image2);
            alienMovement(minXMovement,maxXMovement);
            lastMoveTime = getTime();
        } else if (lastShotTime + 800 <= getTime()){
            checkShotAndShoot(8 - wave);
            lastShotTime = getTime();
        }   
        if (lastMoveTime + 400 <= getTime()){
            setImage(image1);
        }
        checkforShotContact();
    }
}
