import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * MyWorld Class "Space". Controls all spawns, playerlives or Level changes
 * It contains the following variables:
 * -Wave variables that stores the current wave (Starts with wave 0)
 * -grid Variables to control spawn points of enemies in aligned grid
 * -Variables for playerlives
 * -SimpleActor Variables to display certain text on screen
 * 
 * It contains the following methods:
 * 
 * @author Oliver Ammann
 * @version 3.0
 * 
 * 
 */
public class Space extends World
{
    private int wave;
    
    private int gridxstart      = 15;
    private int gridystart      = 30;
    private int gridspace       = 15;
    private int aliens_in_row   = 12;
    
    private int playerLives     = 3;
    private ArrayList<SimpleActor> lifeCounter = new ArrayList<SimpleActor>();

    private SimpleActor lifeDisplay = new SimpleActor();
    private SimpleActor display = new SimpleActor();
    private SimpleActor waveDisplay = new SimpleActor();
    
    /**
    * first, basic constructor for Space world without params to start the first wave
    */
    public Space()
    {  
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(255, 200, 3);
        wave = 0; //1
        
        prepare();
    }
    /**
    * second contstructor for Space world with params to initiate the following waves
    * @param wave Set to current-wave + 1 to initiate a new world and a new wave
    */
    public Space(int wave)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(255, 200, 3);
        this.wave = wave;
        
        prepare();
    }
    
    /**
    * Act method is the main game loop. It checks for player and invader deaths and initiates the next operation accordingly
    */
    public void act(){
        checkForPlayerDeath();
        checkForNextWave();
    }
    
    private void prepare(){
        addObject(new Ship(), 125, 180);
        AlienSpawnControl();
        setupLifeCounter();
        
        waveDisplay.setImage(new GreenfootImage("Wave: " + (wave + 1), 25, Color.WHITE, Color.BLACK));
        addObject(waveDisplay, 240, 5);
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
                gameOver();
            } else{
                playerLives -= 1;
                refreshLifeCounter();               
                playerRespawn();
            }
        }
    }
    private void playerRespawn(){
        removeObjects(getObjects(Alaser.class));
        removeObjects(getObjects(Plaser.class));
        addObject(new Ship(), 125, 180);
        Greenfoot.delay(10);
    }
    
    private void refreshLifeCounter(){
        if (playerLives != lifeCounter.size()){
            removeObject(lifeCounter.get(lifeCounter.size()-1));
            lifeCounter.remove(lifeCounter.get(lifeCounter.size()-1));
        }
    }
    
    private void checkForNextWave(){
        if (getObjects(Einwanderer.class).size() == 0){ 
            if (wave == 2){
                win();    
            } else {
                Greenfoot.setWorld(new Space(wave + 1));    
            }
            
        }
    }
    
    /**
    * Gameover Method - Gets initiated when all playerlives are lost or the invaders collide with the player - Displays "game over" and ends the game.
    */
    public void gameOver(){
        display.setImage(new GreenfootImage("Game Over", 100, Color.RED, Color.BLACK));
        removeObjects(getObjects(Alaser.class));
        removeObjects(getObjects(Plaser.class));
        addObject(display, 126, 100);
        Greenfoot.delay(10);
        Greenfoot.stop();
    }
    /**
    * win Method - Gets initiated when all waves are completed - Displays a big "YOU WIN" and ends the game.
    */
    public void win(){
        display.setImage(new GreenfootImage("You win!!!!!!!!", 100, Color.GREEN, Color.BLACK));
        removeObjects(getObjects(Alaser.class));
        removeObjects(getObjects(Plaser.class));
        removeObjects(getObjects(Einwanderer.class));
             
        addObject(display, 126, 100);
        Greenfoot.delay(10);
        Greenfoot.stop();
    }
}

    
