import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EinwandererTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EinwandererTwo extends Einwanderer
{
    private long lastMoveTime;
    private long lastShotTime;
    private int minXMovement;
    private int maxXMovement;
    private int fireChance;
    
    private int wave;
    
    private GreenfootImage image1 = new GreenfootImage("CrabInvader0.png");
    private GreenfootImage image2 = new GreenfootImage("CrabInvader1.png");
    /**
     * Act - do whatever the EinwandererTwo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public EinwandererTwo(int wave, int row, int startx){
        this.minXMovement = startx;
        this.maxXMovement = getMaxXMovement(minXMovement);
        this.wave = wave;
        
        lastMoveTime = setFirstMovementDelay(row);
        lastShotTime = getTime();
        fireChance = (int) 8 / (wave + 1);
    }
    
    public void act()
    {
        if (lastMoveTime + getMovementDelay(wave) <= getTime()){
            setImage(image1);
            moveSideToSide(minXMovement, maxXMovement);
            lastMoveTime = getTime();
        } else if (lastShotTime + 600 <= getTime()){
            checkShotAndShoot(8 - wave);
            lastShotTime = getTime();
        }
        if (lastMoveTime + 400 <= getTime()){
            setImage(image2);
        }
        checkforLaserContact();
    }
}
