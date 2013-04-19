/*
 * This is our Model class Player.
 *
 */

/**
 *
 * @author patrickfuerst
 */
public class Player {
    
    
    
    
    
    private String name;
    //define the position of the car
    // zero if the car stands on the start
    private int carPosition;
    
    
    public Player(String name){
        this.name = name;
        this.carPosition = 0;
    }
    
    public String getName(){
    
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    /**
     * @return the carPosition
     */
    public int getCarPosition() {
        return carPosition;
    }

    /**
     * @param carPosition the carPosition to set
     */
    public void setCarPosition(int carPosition) {
        this.carPosition = carPosition;
    }
    
}
