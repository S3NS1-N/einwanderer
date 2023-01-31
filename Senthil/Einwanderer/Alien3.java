import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is for the squid alien.
 * Only contains the act() method and mainly utilises methods from the parent class.
 * 
 * @author Senthil Nagendran
 * @version 1.2
 */
public class Alien3 extends Alien
{
    private long lastMoveTime;
    private long lastShotTime;
    private int minXMovement;
    private int maxXMovement;
    private int wave;
    
    private GreenfootImage image1 = new GreenfootImage("squidInvader1.png");
    private GreenfootImage image2 = new GreenfootImage("squidInvader0.png");
    /**
     * Constructor with params for Alien3 class.
     * Only consists of act() method and mainly utilises methods from the parent class.
     * @param wave the current wave
     * @param row the row which this alien is in 
     * @param startx the X coordinate  of the alien in the beginning
     */
    public Alien3(int wave, int row, int startx){
        this.minXMovement = startx;
        this.maxXMovement = getMaxXMovement(minXMovement);
        this.wave = wave;
        
        lastShotTime = getTime();
        lastMoveTime = setFirstMovementDelay(row);
    }
    
    /**
     * Loop for fire cooldowns, movement and checking for contact with playershot
     * The cooldowns for shooting and moving are lower than those of alien1.
     */
    public void act()
    {
        if (lastMoveTime + getMovementDelay(wave) <= getTime()){
            setImage(image1);
            alienMovement(minXMovement,maxXMovement);
            lastMoveTime = getTime();
        } else if (lastShotTime + 400 <= getTime()) {
            checkShotAndShoot(6 - wave);
            lastShotTime = getTime();   
        }
        if (lastMoveTime + 400 <= getTime()){
            setImage(image2);
        }
        checkforShotContact();
    }
}
