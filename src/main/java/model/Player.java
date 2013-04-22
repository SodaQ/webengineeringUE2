package model;

/*
 * Document: Player.java
 * Author:        Boesch Chris    (1025952)
 *                Fuerst Patrick  (0927543)
 *                Musil Thomas    (1167504)  
 */
public class Player {
    
    private String name;
    // define the position of the car
    // zero if the car stands on the start
    private int carPosition;
    private int oldCarPosition;
      
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
    
    public void setCarPosition(int pos) {
        this.carPosition = pos;
    }
    
    public int getCarPosition() {
        return carPosition;
    }
    
    public void setOldCarPosition(int pos) {
        this.oldCarPosition = pos;
    }
    
    public int getOldCarPosition() {
        return oldCarPosition;
    }
       
    public String getCarPositionString() {
        int i = this.getCarPosition(); 
        switch(i) {
            case 0: 
                return "#start_road";
            case 1: 
                return "#road_1";
            case 2: 
                return "#road_2";
            case 3: 
                return "#road_3";
            case 4: 
                return "#road_4";
            case 5: 
                return "#road_5";
            case 6:
                return "#finish_road";
            default: 
                return null;    
        }
    }
    
    public String getCarPositionString(int i) {
        switch(i) {
            case 0: 
                return "#start_road";
            case 1: 
                return "#road_1";
            case 2: 
                return "#road_2";
            case 3: 
                return "#road_3";
            case 4: 
                return "#road_4";
            case 5: 
                return "#road_5";
            case 6:
                return "#finish_road";
            default: 
                return null;    
        }
    }   
}
