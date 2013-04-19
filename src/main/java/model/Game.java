package model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author patrickfuerst
 */
public class Game {
    
    
    private Player player1;
    private Player player2;
    private int round;
    private int gameDurationInSeconds;
    private long gameStartTime;
    private int diceResult;
    private String leader;

    
    public Game(String playerOneName, String playerTwoName){
        
        this.player1 = new Player(playerOneName);
        this.player2 = new Player(playerTwoName);
        this.round = 0;
        this.gameDurationInSeconds = 0;
        this.gameStartTime = System.currentTimeMillis();
        this.diceResult = 0;
        this.leader = "mehrere";
        
        
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

    /**
     * @param round the round to set
     */
    public void setRound(int round) {
        this.round = round;
    }

    /**
     * @return the gameDurationInSeconds
     */
    public int getGameDurationInSeconds() {
        return gameDurationInSeconds;
    }

    /**
     * @param gameDurationInSeconds the gameDurationInSeconds to set
     */
    public void setGameDurationInSeconds(int gameDurationInSeconds) {
        this.gameDurationInSeconds = gameDurationInSeconds;
    }

    /**
     * @return the diceResult
     */
    public int getDiceResult() {
        return diceResult;
    }

    /**
     * @param diceResult the diceResult to set
     */
    public void setDiceResult(int diceResult) {
        this.diceResult = diceResult;
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
            
    /**
     * called if new game is pressed
     */
    public void resetGame(){
        
        this.round = 0;
        this.gameDurationInSeconds = 0;
        this.gameStartTime = System.currentTimeMillis();
        this.diceResult = 0;
        this.leader = "mehrere";
        
    }
    
    
        
    
}
