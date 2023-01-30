import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Space extends World
{
    private boolean stopAllAction;
    
    private int wave;
    private int gridxstart      = 15;
    private int gridystart      = 30;
    private int gridspace       = 15;
    private int aliens_in_row   = 12;
    
    private int playerLives     = 3;
    
    private ArrayList<SimpleActor> lifeCounter = new ArrayList<SimpleActor>();
    private SimpleActor lifeDisplay = new SimpleActor();
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Space()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(255, 200, 3);
        wave = 0;
        
        prepare();
    }
    public Space(int wave)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(255, 200, 3);
        wave = this.wave;
        
        prepare();
    }
    
    public void act(){
        checkForPlayerDeath();      
    }
    
    private void prepare(){
        addObject(new Ship(), 125, 180);
        AlienSpawnControl();
        setupLifeCounter();
        stopAllAction = false;
    }
    
    private void AlienSpawnControl(){
        for (int row = 0; row<=4; row++){
            if (row == 0){
                for (int i = 0; i<aliens_in_row; i++){
                    addObject(new EinwandererThree(wave, row + 1, gridxstart+(15*i)), gridxstart+(15*i), gridystart);
                }
            }
            if (row == 1 || row == 2){
                for (int i = 0; i<aliens_in_row; i++){
                    addObject(new EinwandererTwo(wave, row + 1, gridxstart+(15*i)), gridxstart+(15*i), gridystart + row * gridspace);
                }
            }
            if (row == 3 || row == 4){
                for (int i = 0; i<aliens_in_row; i++){
                    addObject(new EinwandererOne(wave, row + 1,gridxstart+(15*i)), gridxstart+(15*i), gridystart + row * gridspace);
                }
            }
        } 
    }
    
    private void setupLifeCounter(){
        lifeDisplay = new SimpleActor();
        lifeDisplay.setImage("spaceship.gif");
        lifeDisplay.getImage().scale(lifeDisplay.getImage().getWidth() / 2,  lifeDisplay.getImage().getHeight() / 2);
        lifeCounter.add(lifeDisplay);
        addObject(lifeDisplay, 8, 193);
        
        lifeDisplay = new SimpleActor();
        lifeDisplay.setImage("spaceship.gif");
        lifeDisplay.getImage().scale(lifeDisplay.getImage().getWidth() / 2,  lifeDisplay.getImage().getHeight() / 2);
        lifeCounter.add(lifeDisplay);
        addObject(lifeDisplay, 16, 193);
        
        lifeDisplay = new SimpleActor();
        lifeDisplay.setImage("spaceship.gif");
        lifeDisplay.getImage().scale(lifeDisplay.getImage().getWidth() / 2,  lifeDisplay.getImage().getHeight() / 2);
        lifeCounter.add(lifeDisplay);
        addObject(lifeDisplay, 24, 193);
    }
    
    private void checkForPlayerDeath(){
        if (getObjects(Ship.class).size() == 0){
            if (playerLives == 0){
                //Game Over
            } else{
                playerLives -= 1;
                refreshLifeCounter();               
                playerRespawn();

                stopAllAction = false;
            }
        }
    }
    private void playerRespawn(){
        removeObjects(getObjects(Alaser.class));
        removeObjects(getObjects(Plaser.class));
        addObject(new Ship(), 125, 180);
        Greenfoot.delay(40);
    }
    
    private void refreshLifeCounter(){
        if (playerLives != lifeCounter.size()){
            removeObject(lifeCounter.get(lifeCounter.size()-1));
            lifeCounter.remove(lifeCounter.get(lifeCounter.size()-1));
        }
    }
}

    
