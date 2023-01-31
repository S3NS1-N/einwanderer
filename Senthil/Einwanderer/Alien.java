import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Parent Class for all enemys. Contains common functions that all aliens use. For example: moving and shooting.
 * Inherits from Actor class.
 * 
 * @author Senthil Nagendran
 * @version 1.2
 */
public class Alien extends Actor
{
    private int alienDirection = 1;
    private int stepSize = 6;
    private int maxMovementSpace = 60;
    private int movementDelay = 2500;
    
    /**
     * This method moves the aliens from side-to-side, checks if they reached the end (max or min X Coordinate)
     * and moves them one row down and changes their direction.
     * @param minXMovement The start / minimum Position of the invader.
     * @param maxXMovement The maximum X Coordinate of the invader (minXMovement + 60).
     */
    public void alienMovement(int minXMovement, int maxXMovement) {
        if ((getX() == maxXMovement && alienDirection == 1) || (getX() == minXMovement && alienDirection == -1)){
            setLocation(getX(), getY() + 20);
            alienDirection *= -1;
        } else {
            setLocation(getX() + stepSize * alienDirection, getY());
        }
    }
    
    /**
     * This method checks if the enemy got hit by a bullet and removes the alien if necessary.
     */
    public void checkforShotContact(){
        if (isTouching(playerShot.class)){
            removeTouching(playerShot.class);
            
            setImage("invaderExplosion.png");
            Greenfoot.playSound("invaderDestroyedSound.wav");
            
            Greenfoot.delay(2);
            getWorld().removeObject(this);
        }
    }
    
    /**
     * This method checks if an alien is able to shoot (randomized) and fires if true.
     */
    public void checkShotAndShoot(int chance){
        if (getObjectsAtOffset(0, 20, Alien.class).size() == 0 && Greenfoot.getRandomNumber(chance) == 1){
            enemyShot shot = new enemyShot();
            getWorld().addObject(shot,getX(), getY() + 2);
        }
    }

    /**
    * This method returns the maximum X coordinate with the help of the start position.
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
    * This method returns the movement delay of the wave for the aliens.
    * @return movement delay of the wave 
    */
    public int getMovementDelay(int wave){
        return movementDelay - (wave * 800);
    }
    
    /**
    * This method returns the first movement delay for each row of aliens.
    * Each row moves with a certain offset to the previous one.
    * @return Movement-delay for each row
    */
    public long setFirstMovementDelay(int row){
        return getTime() - (row * 100);
    }
}
