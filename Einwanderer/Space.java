import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Space extends World
{
    private int wave;
    private int gridxstart      = 15;
    private int gridystart      = 20;
    private int gridspace       = 15;
    private int aliens_in_row   = 12;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Space()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(255, 200, 3);
        wave = 1;
        
        prepare();
    }
    public Space(int wave)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(300, 200, 3);
        wave = this.wave;
        
        prepare();
    }
    
    private void prepare(){
        Ship ship = new Ship();
        addObject(ship, 125, 180);
        AlienSpawnControl();
    }
    
    private void AlienSpawnControl(){
        for (int row = 0; row<=4; row++){
            if (row == 0){
                for (int i = 0; i<aliens_in_row; i++){
                    addObject(new EinwandererThree(wave, row + 1), gridxstart+(15*i), gridystart);
                }
            }
            if (row == 1 || row == 2){
                for (int i = 0; i<aliens_in_row; i++){
                    addObject(new EinwandererTwo(wave, row + 1), gridxstart+(15*i), gridystart + row * gridspace);
                }
            }
            if (row == 3 || row == 4){
                for (int i = 0; i<aliens_in_row; i++){
                    addObject(new EinwandererOne(wave, row + 1), gridxstart+(15*i), gridystart + row * gridspace);
                }
            }
        } 
    }
    
    public int getXGridLimit(String maxmin){
        if (maxmin == "max"){
            return gridxstart;
        } else{
            return getWidth() - gridspace;
        }
    }
    
    public int getGridSpace(){
        return gridspace;
    }
}

    
