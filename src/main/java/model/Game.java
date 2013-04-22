package model;

import java.util.Random;

/*
 * Document: Game.java
 * Author:        Boesch Chris    (1025952)
 *                Fuerst Patrick  (0927543)
 *                Musil Thomas    (1167504)              
 */
public class Game {
    
    private Player player1;
    private Player player2;
    private int round;
    private long gameStartTime;
    private int lastDiceResult;
    private String leader;
    private Random generator = new Random();
    private boolean p1Oel;
    private boolean p2Oel;
    private boolean running;
    private String duration;
    private String diceID;
    private boolean reseted;
    private boolean init;
    
    public Game(){
        this.player1 = new Player("Super Mario");
        this.player2 = new Player("Super C");
        reseted = false;
        this.round = 1;
        this.gameStartTime = System.currentTimeMillis();
        this.lastDiceResult = 0;
        this.leader = "mehrere";
        player1.setOldCarPosition(0);
        player2.setOldCarPosition(0);
        running = true;
        duration = "";
        diceID = "img/wuerfel0.png";
        reseted = true;    
        p1Oel = false; 
        p2Oel = false;
    }
    
    public Game(String playerOneName, String playerTwoName){
        this.player1 = new Player(playerOneName);
        this.player2 = new Player(playerTwoName);
        reseted = false;
        this.round = 1;
        this.gameStartTime = System.currentTimeMillis();
        this.lastDiceResult = 0;
        this.leader = "mehrere";
        player1.setOldCarPosition(0);
        player2.setOldCarPosition(0);
        running = true;
        duration = "";
        diceID = "img/wuerfel0.png";
        reseted = true;        
        p1Oel = false; 
        p2Oel = false;
    }
    
    /**
     * reset Game
     */
    public void resetGame(){ 
        if(p1Oel) 
            resetP1();
        if(p2Oel)
            resetP2();
        if(reseted) {
            player1.setCarPosition(0);
            player2.setCarPosition(0);
        }
        this.round = 1;
        this.gameStartTime = System.currentTimeMillis();
        this.lastDiceResult = 0;
        this.leader = "mehrere";
        player1.setOldCarPosition(0);
        player2.setOldCarPosition(0);
        running = true;
        duration = "";
        diceID = "img/wuerfel0.png";
        reseted = true;       
        p1Oel = false; 
        p2Oel = false;
    }
    
    
    /**
     * @return the player1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * @param player1 the player1 to set
     */
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    /**
     * @return the player2
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * @param player2 the player2 to set
     */
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    /**
     * @return the round
     */
    public int getRound() {
        return round;
    }

    private int getGameDurationSeconds() {
        return (int) (Math.round(System.currentTimeMillis()/1000 - gameStartTime/1000) % 60);
    }
    
    private int getGameDurationMinutes() {
        return (int) (Math.round(System.currentTimeMillis()/1000 - gameStartTime/1000)/60);
    }
    
    public String getGameDuration() {
        if (running) {
            if (getGameDurationMinutes() < 10) {
                duration = "0" + getGameDurationMinutes();
            } else {
                duration = "" + getGameDurationMinutes();
            }
            duration += ":";
            if (getGameDurationSeconds() < 10) {
                duration += "0" + getGameDurationSeconds();
            } else {
                duration += "" + getGameDurationSeconds();
            }
        }
        return duration;
    }

    /**
     * @return the leader
     */
    public String getLeader() {
        return leader;
    }

    /**
     * @param leader the leader to set
     */
    public void setLeader(String leader) {
        this.leader = leader;
    }
    
    public void updateLeader() {
        if(player1.getCarPosition() > player2.getCarPosition()) 
            leader = player1.getName();
        else if(player1.getCarPosition() < player2.getCarPosition())
            leader = player2.getName();
        else 
            leader = "mehrere";
    }
            
    public void play() {
        if(p1Oel) 
            resetP1();
        if(p2Oel)
            resetP2();
        p2Oel = false;  
        p1Oel = false;
        if(reseted) {
            player1.setCarPosition(0);
            player2.setCarPosition(0);
        }
        reseted = false;
        player1.setOldCarPosition(player1.getCarPosition());
        player2.setOldCarPosition(player2.getCarPosition());
        if(!running) {
            return;
        }

        round++;
        
        updatePositionPlayer1(getRandomNumber());
        checkOel();
        if(checkWinner()) 
            return;
        
        updatePositionPlayer2(getRandomNumber());
        checkOel();
        if(checkWinner()) 
            return;
  
        updateLeader(); 
    }
    
    private void resetP1() {
        player1.setCarPosition(0);
        player1.setOldCarPosition(0);
    }
    
    private void resetP2() {
        player2.setCarPosition(0);
        player2.setOldCarPosition(0);
    }
    
    private int getRandomNumber() {
        return (generator.nextInt(3) + 1);
    }
    
    private boolean checkWinner() {
        if(player1.getCarPosition()>= 6) {
            player1.setCarPosition(6);   
            running = false;
            updateLeader();
            return true;
        } else if(player2.getCarPosition() >= 6) {
            player2.setCarPosition(6);
            running = false;
            updateLeader();
            return true;
        } else {
            return false;
        }
    }

    private void updatePositionPlayer1(int dice) {
        setDiceID(dice);
        player1.setCarPosition(player1.getCarPosition()+dice);
    }

    public int getLastDiceResult() {
        return lastDiceResult;
    }

    public void updatePositionPlayer2(int dice) {
        this.lastDiceResult = dice;
        player2.setCarPosition(player2.getCarPosition()+dice);
    }
    
    private void checkOel() {
        if(player1.getCarPosition() == 2 || player1.getCarPosition() == 5)
            p1Oel = true;
        if(player2.getCarPosition() == 2 || player2.getCarPosition() == 5)
            p2Oel = true; 
    }
    
    public boolean isRunning() {
        return running;
    }
    
    public boolean isP1Oel() {
        return p1Oel;
    }
    
    public boolean isP2Oel() {
        return p2Oel;
    }
    
    public boolean isInit() {
        return init;
    }
    
    public String getDiceID() {
        return diceID;
    }
    
    public void setDiceID(int i) {
        switch(i) {
            case 1: 
                this.diceID = "img/wuerfel1.png";
                break;
            case 2: 
                this.diceID = "img/wuerfel2.png";
                break;
            case 3: 
                this.diceID = "img/wuerfel3.png";
                break;
            default:
                this.diceID = "img/wuerfel0.png";
                break;
        }
    }   
}
