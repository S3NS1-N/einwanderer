import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Parent class of player-ship. Contains useful functions for the player class. Kind of unecessary at the moment.
 * 
 * @author Oliver Ammann
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
