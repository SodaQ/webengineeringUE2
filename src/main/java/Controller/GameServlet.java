package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Game;

/*
 * Document: GameServlet.java
 * Author:        Boesch Chris    (1025952)
 *                Fuerst Patrick  (0927543)
 *                Musil Thomas    (1167504)  
 */
public class GameServlet extends HttpServlet {

    private Game game;
    
    @Override
    public void init() throws ServletException {
        super.init();
    }
  
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         String action = request.getParameter("action");
        
         Game game =(Game)request.getSession().getAttribute("game");
                  
         // if this is a new session and there is no game attribute 
         // create a new game
         if(game == null){
            game = new Game();
            HttpSession session = request.getSession(true);
            session.setAttribute("game", game);
         }
         
        if(action != null) {  
            if(action.equals("newGame")) {
                game.resetGame();            
                request.setAttribute("player1OldPos", game.getPlayer1().getCarPositionString());
                request.setAttribute("player2OldPos", game.getPlayer2().getCarPositionString());           
                request.setAttribute("player1Pos", "#start_road");
                request.setAttribute("player2Pos", "#start_road");                      
            } else if(action.equals("dice")) {
                game.play();
                request.setAttribute("player1Pos", game.getPlayer1().getCarPositionString());
                request.setAttribute("player2Pos", game.getPlayer2().getCarPositionString());
                request.setAttribute("player1OldPos", game.getPlayer1().getCarPositionString(game.getPlayer1().getOldCarPosition()));
                request.setAttribute("player2OldPos", game.getPlayer2().getCarPositionString(game.getPlayer2().getOldCarPosition()));                                     
            }
        }
        request.setAttribute("running", game.isRunning());
        request.setAttribute("P1Oel", game.isP1Oel());
        request.setAttribute("P2Oel", game.isP2Oel());
        request.setAttribute("dice", game.getDiceID()); 
        
        request.getRequestDispatcher("table.jsp").forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Servlet for registration";
    }
}
