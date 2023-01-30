import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EinwandererOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
     * Act - do whatever the EinwandererOne wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public EinwandererOne(int wave, int row, int startx){
        this.minXMovement = startx;
        this.maxXMovement = getMaxXMovement(minXMovement);
        this.wave = wave;
        
        lastMoveTime = setFirstMovementDelay(row);
        lastShotTime = getTime();
        fireChance = (int) 10 / (wave + 1);
    }
    
    public void act()
    {
        if (lastMoveTime + getMovementDelay(wave) <= getTime()){
            setImage(image2);
            moveSideToSide(minXMovement,maxXMovement);
            lastMoveTime = getTime();
        } else if (lastShotTime + 800 <= getTime()){
            checkShotAndShoot(9 - wave);
            lastShotTime = getTime();
        }   
        if (lastMoveTime + 400 <= getTime()){
            setImage(image1);
        }
        checkforLaserContact();
    }
}
