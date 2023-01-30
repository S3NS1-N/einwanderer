import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Einwanderer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Einwanderer extends Actor
{
    private int leftOrRight = 1;
    private int stepSize = 6;
    private int maxMovementSpace = 60;
    
    private int movementDelay = 2500;
    
    public void moveSideToSide(int minXMovement, int maxXMovement) {
        if ((getX() == maxXMovement && leftOrRight == 1) || (getX() == minXMovement && leftOrRight == -1)){
            setLocation(getX(), getY() + 20);
            leftOrRight *= -1;
        } else {
            setLocation(getX() + stepSize * leftOrRight, getY());
        }
    }
    
    public void checkforLaserContact(){
        if (isTouching(Plaser.class)){
            removeTouching(Plaser.class);
            
            setImage("invaderExplosion.png");
            Greenfoot.playSound("invaderDestroyedSound.wav");
            
            Greenfoot.delay(2);
            getWorld().removeObject(this);
        }
    }
    
    public void checkShotAndShoot(int chance){
        if (getObjectsAtOffset(0, 20, Einwanderer.class).size() == 0 && getObjectsAtOffset(10 * leftOrRight, 20, Einwanderer.class).size() == 0 && Greenfoot.getRandomNumber(chance) == 1){
            Alaser laser = new Alaser();
            getWorld().addObject(laser,getX(), getY() + 2);
        }
    }

    public int getMaxXMovement(int minXMovement){
        return maxMovementSpace + minXMovement;
    }
    
    public long getTime(){
        return System.currentTimeMillis();
    }
    
    public int getMovementDelay(int wave){
        return movementDelay - (wave * 800);
    }
    
    public long setFirstMovementDelay(int row){
        return getTime() - (row * 50);
    }
}
