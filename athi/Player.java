import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Parent Class for all "Player". beneficial functions.
 * 
 * @author Athavan Ranganathan
 * @version 3.0
 */
public class Player extends Actor
{
    /**
     * Returns the current time in milliseconds
     */
    public long getTime(){
        return System.currentTimeMillis();
    }
}
