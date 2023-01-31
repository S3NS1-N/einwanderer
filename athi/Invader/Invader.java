import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Parent Class for all "Invaders". Includes common functions that all invaders use, such as moving and shooting.
 * 
 * @author Athavan Ranganathan
 * @version 3.0
 */
public class Invader extends Actor
{
    private int MovementGalaxyMax   = 60;
    private int MovementGalaxyDelay = 2500;
    private int mooveSize           = 6;
    private int LeftnRight          = 1;
        
    /**
     * this method does the following: moves the invaders (left/right). if the max or min x cooridate is reached, they are moved one row down and switch to the opposite direction.
     * @param minXMovement The start / minimum Position of the invader.
     * @param maxXMovement The maximum X Coordinate of the invader (minXMovement + 60).
     */
    public void moveLeftRight(int minXMovement, int maxXMovement) {
        if ((getX() == maxXMovement && LeftnRight == 1) || (getX() == minXMovement && LeftnRight == -1)){
            setLocation(getX(), getY() + 20);
            LeftnRight *= -1;
        } else {
            setLocation(getX() + mooveSize * LeftnRight, getY());
        }
    }
    
    /**
    * this method does the following: returns the maximum X coordinate using the start X position.
    */
    public int getMaxGalaxyXMovement(int minXMovement){
        return MovementGalaxyMax + minXMovement;
    }
    
    /**
    * this method does the following: returns the time in ms.
    */
    public long getTime(){
        return System.currentTimeMillis();
    }
    
    /**
    * this method does the following: returns the movement delay (wave) for the invaders.
    */
    public int getMovementGalaxyDelay(int wave){
        return MovementGalaxyDelay - (wave * 800);
    }
    
    /**
    * this method does the following: returns the first movement delay (every invaders row)
    */
    public long setFirstMovementGalaxyDelay(int row){
        return getTime() - (row * 200);
    }
    
    /**
     * this method does the following: checks if there is a contact between the plaser and an invader. if yes, this invader is removed.
     */
    public void checkforLaserContact(){
        if (isTouching(JLaser.class)){
            removeTouching(JLaser.class);
            
            setImage("invaderExplosion.png");
            Greenfoot.playSound("invaderDestroyedSound.wav");
            
            Greenfoot.delay(2);
            getWorld().removeObject(this);
        }
    }
    
    /**
     * this method does the following: checks if the invader is allowed to shoot. if yes, he shoots. the shots are shot randomly
     */
    public void verifyNshoot(int chance){
        if (getObjectsAtOffset(0, 20, Invader.class).size() == 0 && Greenfoot.getRandomNumber(chance) == 1){
            IVLaser laser = new IVLaser();
            getWorld().addObject(laser,getX(), getY() + 2);
        }
    }
}
