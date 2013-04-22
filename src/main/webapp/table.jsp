<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
  "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<%-- 
    Document   :    table
    Author     :    Boesch Chris    (1025952)
                    Fuerst Patrick  (0927543)
                    Musil Thomas    (1167504)
--%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<jsp:useBean id="game" scope="session"  class="model.Game" />


<html xmlns="http://www.w3.org/1999/xhtml" lang="de" xml:lang="de">
    <head>
        <title xml:lang="de">Formel 0 - Spielen</title>
        <meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>
        <meta name="keywords" content="CSS, XHTML, JavaScript"/>
        <meta name="description" content="UE2 fuer WebEngineering TU Wien"/>
        <meta name="author" content="Boesch Chris (1025952), Musil Thomas (1167504), Fuerst Patrick (0927543)"/>
        <link rel="stylesheet" type="text/css" href="styles/screen.css" />
        <script src="js/jquery.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="container">
            <div id="bordercontainer">
                <div id="header">
                    <div id="header_left"><h1 class="accessibility">Formel 0</h1></div>
                    <div id="header_right"></div>
                </div>
                <div id="navigation">
                    <h2 class="accessibility">Navigation</h2>
                    <ul title="Navigation">
                        <li><a id="startNewGame" href="GameServlet?action=newGame" tabindex="1">Neues Spiel</a></li>
                        <li><a href="#" tabindex="2">Ausloggen</a></li>
                    </ul>
                </div>
                <div id="main-area">
                    <div class="info">
                        <h2>Spielinformationen</h2>
                        <table summary="Diese Tabelle zeigt Informationen zum aktuellen Spiel">
                            <tr><th id="leaderLabel" class="label">F&uuml;hrender</th><td id="leader" class="data"> <%= game.getLeader()%></td></tr>
                            <tr><th id="roundLabel" class="label">Runde</th><td id="round" class="data"><%= game.getRound() %></td></tr>
                            <tr><th id="timeLabel" class="label">Zeit</th><td id="time" class="data"><%= game.getGameDuration()%></td></tr>
                            <tr><th id="computerScoreLabel" class="label">W&uuml;rfelergebnis <em>Super C</em></th><td id="computerScore" class="data"><%= game.getLastDiceResult()%></td></tr>
                        </table>  
                        <h2>Spieler</h2>
                        <table summary="Diese Tabelle listet die Namen der Spieler auf">
                            <tr><th id="player1NameLabel" class="label">Spieler 1</th><td id="player1Name" class="data"><%= game.getPlayer1().getName() %></td></tr>
                            <tr><th id="player2NameLabel" class="label">Spieler 2</th><td id="player2Name" class="data"><%= game.getPlayer2().getName() %></td></tr>
                        </table>    	  
                    </div>
                    <div class="field">
                        <h2 class="accessibility">Spielbereich</h2>
                        <ol id="road">
                            <li id="start_road">
                                <span class="accessibility">Startfeld</span>
                                <span id="player1">
                                    <span class="accessibility"><em>Spieler 1</em></span>
                                </span>
                                <span id="player2">
                                    <span class="accessibility"><em>Spieler 2</em></span>
                                </span>
                            </li>
                            <li class="empty_road" id="road_1">
                                <span class="accessibility">Feld 2</span>
                            </li>
                            <li class="oil_road" id="road_2">
                                <span class="accessibility">Feld 3 Oelfleck</span>
                                
                            </li>
                            <li class="empty_road" id="road_3">
                                <span class="accessibility">Feld 4</span>
                            </li>
                            <li class="empty_road" id="road_4">
                                <span class="accessibility">Feld 5</span>
                            </li>
                            <li class="oil_road" id="road_5">
                                <span class="accessibility">Feld 6 Oelfleck</span>
                            </li>
                            <li id="finish_road">
                                <span class="accessibility">Zielfeld</span>
                            </li>
                        </ol>
                    </div>
                    <div class="player">
                        <h2 class="accessibility">W&uuml;rfelbereich</h2>
                        <span class="accessibility">An der Reihe ist</span><div id="currentPlayerName">Super Mario</div>
                        <a id="dice" href="GameServlet?action=dice" tabindex="4">
                            <img id="diceImage" src="img/wuerfel0.png" alt="W&uuml;rfel mit einer Eins" />	
                        </a>
                    </div>
                </div>
            </div>
            <div id="footer">
                &copy; 2013 Formel 0
            </div>
        </div>

        <script type="text/javascript">
            //<![CDATA[

            var pos1 = '${player1Pos}';
            var pos2 = '${player2Pos}';
            var pos1Old = '${player1OldPos}';
            var pos2Old = '${player2OldPos}';
            
            var oel1 = false;
            var oel2 = false;
            oel1 = ${P1Oel};
            oel2 = ${P2Oel};
            
            var dice = '${dice}';
            
            var running = new Boolean(false);
            running = '${running}';
            
            if(running || (!running && (oel1||oel2) ))
                start();
                          
            // call this function once before starting the animations
            function prepareAnimation() {
                $("#animationDone").remove();
            }
            
            // call this function once after all animations have finished
            function completeAnimation() {
                var div = $(document.createElement('div'));
                div.attr('id', 'animationDone');
                div.addClass('hide');
                $("body").append(div);
            }

            function start() {
                $("#diceImage").attr('src', dice);
           
                $("#player1").appendTo(pos1Old);
                document.getElementById('player1').style.visibility = 'visible';
                $("#player2").appendTo(pos2Old);
                document.getElementById('player2').style.visibility = 'visible';

                prepareAnimation();
                $("#player1").fadeOut(700, function() {
                    $("#player1").appendTo(pos1);
                    $("#player1").fadeIn(700,function() {
                        if(oel1) {
                            $("#player1").fadeOut(700, function() {
                            $("#player1").appendTo("#start_road");
                            $("#player1").fadeIn(700, function() {
                                $("#player2").fadeOut(700, function() {
                                $("#player2").appendTo(pos2);
                                $("#player2").fadeIn(700,function() {
                                if(oel2) {
                                    $("#player2").fadeOut(700, function() {
                                    $("#player2").appendTo("#start_road");
                                    $("#player2").fadeIn(700,null);
                                    });
                                }
                                });
                                });
                            });
                            });
                        } else {
                            $("#player2").fadeOut(700, function() {
                            $("#player2").appendTo(pos2);
                            $("#player2").fadeIn(700,function() {
                                if(oel2) {
                                    $("#player2").fadeOut(700, function() {
                                    $("#player2").appendTo("#start_road");
                                    $("#player2").fadeIn(700,null);
                                    });
                                }
                            });
                            });
                        }
                    });
                });
                completeAnimation();              
            }
            //]]>
        </script>

    </body>
</html>