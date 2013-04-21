package Controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Game;


/**
 *
 * @author Mayerhofer
 */
public class GameServlet extends HttpServlet {

    private Game game;
    private String diceID;
    
    @Override
    public void init() throws ServletException {
        super.init();
    }
  
    /**
     * Responsible for Game
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if(action==null) {
            return;
        }
        
         Game game =(Game)request.getSession().getAttribute("game");
         
        
         
         // if this is a new session and there is no game attribute 
         // create a new game
         if(game == null){
            game = new Game();
            HttpSession session = request.getSession(true);
            session.setAttribute("game", game);
         }
         
          
        if(action.equals("newGame")) { 
            
            request.setAttribute("running", game.isRunning());
            request.setAttribute("player1OldPos", game.getPlayer1().getCarPositionString());
            request.setAttribute("player2OldPos", game.getPlayer2().getCarPositionString());
            
            request.setAttribute("player1Pos", "#start_road");
            request.setAttribute("player2Pos", "#start_road");
            request.setAttribute("dice", "img/wuerfel0.png");
            game.resetGame();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/table.jsp");
            dispatcher.forward(request, response);
             
        } else if(action.equals("dice")) {
            game.play();
            diceID = game.getDiceID();
            request.setAttribute("running", game.isRunning());
            request.setAttribute("player1Pos", game.getPlayer1().getCarPositionString());
            request.setAttribute("player2Pos", game.getPlayer2().getCarPositionString());
            request.setAttribute("player1OldPos", game.getPlayer1().getCarPositionString(game.getPlayer1().getOldCarPosition()));
            request.setAttribute("player2OldPos", game.getPlayer2().getCarPositionString(game.getPlayer2().getOldCarPosition()));
            request.setAttribute("P1Oel", game.isP1Oel());
            request.setAttribute("P2Oel", game.isP2Oel());
            request.setAttribute("dice", diceID);
            request.getRequestDispatcher("table.jsp").forward(request, response);
            
            //RequestDispatcher rd = getServletContext().getRequestDispatcher("/table.jsp");
            //request.setAttribute("player1Pos", game.getPlayer1() );
            //rd.forward(request, response);
            
            //request.getRequestDispatcher("table.jsp").forward(request, response);
                  
        }
    }

    /**
     * Responsible for updating a user and creating new users.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


         
         
        
        /*
        HttpSession session = request.getSession(true);         
        User user =(User)session.getAttribute("user");
        boolean newuser = false;
        if(user == null) {
            user = new User();            
            newuser = true;
            user.setUsername(request.getParameter("username"));
        } else {
            user = userpool.getUser(user.getUsername());
        }
        user.setFirstname(request.getParameter("firstname"));
        user.setLastname(request.getParameter("lastname"));        
        user.setPassword(request.getParameter("password"));

        String[] inter = request.getParameterValues("interests");
        if(!newuser) {
            user.clearInterests();
        }
        if(inter != null ) {
            List<String> interests = Arrays.asList(inter);
            if(interests.contains("webEngineering")) {
                user.addInterest(Interest.WEBENINEERING);                
            }
            if(interests.contains("modelEngineering")) {
                user.addInterest(Interest.MODELENGINEERING);                
            }
            if(interests.contains("semanticWeb")) {
                user.addInterest(Interest.SEMANTICWEB);                
            }
            if(interests.contains("objectOrientedModeling")) {
                user.addInterest(Interest.OBJECTORIENTEDMODELING);                
            }
            if(interests.contains("businessInformatics")) {
                user.addInterest(Interest.BUSINESSINFORMATICS);                
            }         
        } 
        
        session.setAttribute("user", user);
        
        if(newuser) {
            userpool.registerUser(user);
        }           
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userpage.jsp");
        dispatcher.forward(request, response);
 * 
 */
    }
    

    
    @Override
    public String getServletInfo() {
        return "Servlet for registration";
    }
}
