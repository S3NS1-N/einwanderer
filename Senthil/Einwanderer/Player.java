import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Parent class of playerjet. Contains useful functions for the player class. 
 * 
 * @author Senthil Nagendran
 * @version 1.2
 */
public class Player extends Actor
{
    /**
     * @return Returns the current time in milliseconds
     */
    public long getTime(){
        return System.currentTimeMillis();
    }
}
