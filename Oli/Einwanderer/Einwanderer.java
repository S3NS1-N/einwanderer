import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Parent Class for all "Invaders". Contains common functions that all invaders use, like moving and shooting.
 * 
 * @author Oliver Ammann
 * @version 3.0
 */
public class Einwanderer extends Actor
{
    private int leftOrRight = 1;
    private int stepSize = 6;
    private int maxMovementSpace = 60;
    
    private int movementDelay = 2500;
    
    /**
     * This method moves the invaders from side to side and checks if the reached the maximum or minimum X Coordinate and move one row down and change direction.
     * @param minXMovement The start / minimum Position of the invader.
     * @param maxXMovement The maximum X Coordinate of the invader (minXMovement + 60).
     */
    public void moveSideToSide(int minXMovement, int maxXMovement) {
        if ((getX() == maxXMovement && leftOrRight == 1) || (getX() == minXMovement && leftOrRight == -1)){
            setLocation(getX(), getY() + 20);
            leftOrRight *= -1;
            Greenfoot.playSound("invaderStepSound.wav");
        } else {
            setLocation(getX() + stepSize * leftOrRight, getY());
        }
        
    }
    
    /**
     * This method checks for contact with player-laser-shot and removes the invader if necessary.
     */
    public void checkforLaserContact(){
        if (isTouching(Plaser.class)){
            removeTouching(Plaser.class);
            
            setImage("invaderExplosion.png");
            Greenfoot.playSound("invaderDestroyedSound.wav");
            
            Greenfoot.delay(3);
            getWorld().removeObject(this);
        }
    }
    
    /**
     * This method checks if an Invader is able to shoot (randomized) and fires if true.
     */
    public void checkShotAndShoot(int chance){
        if (getObjectsAtOffset(0, 20, Einwanderer.class).size() == 0 && getObjectsAtOffset(10 * leftOrRight, 20, Einwanderer.class).size() == 0 && getObjectsAtOffset(10 * leftOrRight, 40, Einwanderer.class).size() == 0 && Greenfoot.getRandomNumber(chance) == 1){
            Alaser laser = new Alaser();
            getWorld().addObject(laser,getX(), getY() + 2);
        }
    }

    /**
    * This method returns the maximum X coordinate with the help of the start X position.
    */
    public int getMaxXMovement(int minXMovement){
        return maxMovementSpace + minXMovement;
    }
    
    /**
    * This method returns the time in Milliseconds
    */
    public long getTime(){
        return System.currentTimeMillis();
    }
    
    /**
    * This method returns the movement delay of the wave for the invaders.
    */
    public int getMovementDelay(int wave){
        return movementDelay - (wave * 600);
    }
    
    /**
    * This method returns the first movement delay for each row of invaders (they move a little async)
    */
    public long setFirstMovementDelay(int row){
        return getTime() - (row * 100);
    }
}
