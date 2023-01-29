import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EinwandererThree here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EinwandererThree extends Einwanderer
{
    private int movementDelay = 1100;
    private long lastMoveTime;
    /**
     * Act - do whatever the EinwandererThree wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public EinwandererThree(int wave, int row){
        movementDelay -= wave * 100;
        lastMoveTime = getTime() - (row * 200);
    }
    
    public void act()
    {
        if (lastMoveTime + movementDelay >= getTime()){
            moveSideToSide();
        }
    }
}
