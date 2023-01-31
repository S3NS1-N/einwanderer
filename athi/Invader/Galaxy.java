import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * MyWorld Class "Space" manages all the spawns, level changes and player lives
 * 
 * @author Athavan Ranganathan
 * @version 3.0
 * 
 * 
 */
public class Galaxy extends World
{
    private int wave;
    
    private int spaceXstart     = 15;
    private int spaceYstart     = 30;
    private int spaceSize       = 15;
    private int alienrow        = 12;
    
    private int playerHealth    = 3;
    private ArrayList<ActorLite> lifeCounter = new ArrayList<ActorLite>();

    private ActorLite lifeDisplay = new ActorLite();
    private ActorLite display = new ActorLite();
    private ActorLite waveDisplay = new ActorLite();
    
    /**
    * first constructor: constructor for world (without params) to start the first wave
    */
    public Galaxy()
    {  
        // Create new world: 600x400 cells & cell size: 1x1 pixels.
        super(255, 200, 3);
        wave = 0; //1
        
        prepare();
    }
    /**
    * second contstructor: for world (with params) to initiate the next waves
    * @param wave Set to current-wave + 1 to initiate a new world and a new wave
    */
    public Galaxy(int wave)
    {    
        // Create new world: 600x400 cells & cell size: 1x1 pixels.
        super(255, 200, 3);
        this.wave = wave;
        
        prepare();
    }
    
    /**
    *   act method: checks for player and invader if they're alive and prepares the next wave
    */
    public void act(){
        IsShipDeath();
        NewLevel();
    }
    
    private void prepare(){
        addObject(new Jet(), 125, 180);
        InvaderSpawn();
        ShipLife();
        
        waveDisplay.setImage(new GreenfootImage("Wave: " + (wave + 1), 25, Color.WHITE, Color.BLACK));
        addObject(waveDisplay, 240, 5);
    }
    
    private void ShipLife(){
        lifeDisplay = new ActorLite();
        lifeDisplay.setImage("spaceship.gif");
        lifeDisplay.getImage().scale(lifeDisplay.getImage().getWidth() / 2,  lifeDisplay.getImage().getHeight() / 2);
        lifeCounter.add(lifeDisplay);
        addObject(lifeDisplay, 8, 193);
        
        lifeDisplay = new ActorLite();
        lifeDisplay.setImage("spaceship.gif");
        lifeDisplay.getImage().scale(lifeDisplay.getImage().getWidth() / 2,  lifeDisplay.getImage().getHeight() / 2);
        lifeCounter.add(lifeDisplay);
        addObject(lifeDisplay, 16, 193);
        
        lifeDisplay = new ActorLite();
        lifeDisplay.setImage("spaceship.gif");
        lifeDisplay.getImage().scale(lifeDisplay.getImage().getWidth() / 2,  lifeDisplay.getImage().getHeight() / 2);
        lifeCounter.add(lifeDisplay);
        addObject(lifeDisplay, 24, 193);
    }
    
    private void IsShipDeath(){
        if (getObjects(Jet.class).size() == 0){
            if (playerHealth == 0){
                gameOver();
            } else{
                playerHealth -= 1;
                UpdateShipLife();               
                RespawnShip();
            }
        }
    }
    private void RespawnShip(){
        removeObjects(getObjects(IVLaser.class));
        removeObjects(getObjects(JLaser.class));
        addObject(new Jet(), 125, 180);
        Greenfoot.delay(10);
    }
    
    private void UpdateShipLife(){
        if (playerHealth != lifeCounter.size()){
            removeObject(lifeCounter.get(lifeCounter.size()-1));
            lifeCounter.remove(lifeCounter.get(lifeCounter.size()-1));
        }
    }
    
    private void NewLevel(){
        if (getObjects(Invader.class).size() == 0){ 
            if (wave == 2){
                win();    
            } else {
                Greenfoot.setWorld(new Galaxy(wave + 1));    
            }
        }
    }
    
    private void InvaderSpawn(){
        for (int row = 0; row<=4; row++){
            if (row == 0){
                for (int i = 0; i<alienrow; i++){
                    addObject(new InvaderTres(wave, row + 1, spaceXstart+(15*i)), spaceXstart+(15*i), spaceYstart);
                }
            }
            if (row == 1 || row == 2){
                for (int i = 0; i<alienrow; i++){
                    addObject(new InvaderDos(wave, row + 1, spaceXstart+(15*i)), spaceXstart+(15*i), spaceYstart + row * spaceSize);
                }
            }
            if (row == 3 || row == 4){
                for (int i = 0; i<alienrow; i++){
                    addObject(new InvaderUno(wave, row + 1,spaceXstart+(15*i)), spaceXstart+(15*i), spaceYstart + row * spaceSize);
                }
            }
        } 
    }
    
    /**
    * Gameover Method: is triggered when it leads to a collision or all player lives are used. then appears a game over screen and the game ends
    */
    public void gameOver(){
        display.setImage(new GreenfootImage("GAME OVER :(", 125, Color.RED, Color.BLACK));
        removeObjects(getObjects(IVLaser.class));
        removeObjects(getObjects(JLaser.class));
        addObject(display, 126, 100);
        Greenfoot.delay(10);
        Greenfoot.stop();
    }
    /**
    * win Method: this method is triggered when all levels (waves) are done. then appears a big "YOU WIN" screen and the game ends
    */
    public void win(){
        display.setImage(new GreenfootImage("YOU WON :)", 125, Color.GREEN, Color.BLACK));
        removeObjects(getObjects(IVLaser.class));
        removeObjects(getObjects(JLaser.class));
        removeObjects(getObjects(Invader.class));
             
        addObject(display, 126, 100);
        Greenfoot.delay(10);
        Greenfoot.stop();
    }
}

    
