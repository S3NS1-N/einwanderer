import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is the class of the first kind of invader. It just contains the act() method and mainly utilises methods from the parent class
 * 
 * @author Oliver Ammann
 * @version 3.0
 */
public class EinwandererOne extends Einwanderer
{
    private long lastMoveTime;
    private long lastShotTime;
    private int minXMovement;
    private int maxXMovement;
    private int fireChance;
    
    
    private int wave;
    
    private GreenfootImage image1 = new GreenfootImage("JellyFishInvader1.png");
    private GreenfootImage image2 = new GreenfootImage("JellyFishInvader0.png");
    
    /**

     * @param wave the current wave
     * @param row the row the specific invader is in on screen
     * @param startx the start X coordinate of the invader
     */
    public EinwandererOne(int wave, int row, int startx){
        this.minXMovement = startx;
        this.maxXMovement = getMaxXMovement(minXMovement);
        this.wave = wave;
        
        lastMoveTime = setFirstMovementDelay(row);
        lastShotTime = getTime();
        fireChance = 7 - wave;
    }
    
    /**
     * Game-Loop for fire cooldowns and movement and checking for contact with player laser.
     */
    public void act()
    {
        if (lastMoveTime + getMovementDelay(wave) <= getTime()){
            setImage(image2);
            moveSideToSide(minXMovement,maxXMovement);
            lastMoveTime = getTime();
        } else if (lastShotTime + 800 <= getTime()){
            checkShotAndShoot(fireChance);
            lastShotTime = getTime();
        }   
        if (lastMoveTime + 400 <= getTime()){
            setImage(image1);
        }
        checkforLaserContact();
    }
}
