import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import states.ExitMenuState;
import states.MainMenuState;
import states.PlayState;
import states.ScoresState;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StateGame extends StateBasedGame {

    public static final int MENU = 0;
    public static final int PLAY = 1;
    public static final int SCORES = 2;
    public static final int EXIT = 3;

    public StateGame(String gamename){
        super(gamename);
        this.addState(new MainMenuState(MENU));
        this.addState(new PlayState(PLAY));
        this.addState(new ScoresState(SCORES));
        this.addState(new ExitMenuState(EXIT));
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(MENU).init(gc, this);
        this.getState(SCORES).init(gc, this);
        this.getState(EXIT).init(gc, this);
        this.enterState(MENU);
    }

    public static void main(String[] args){
        try{
            AppGameContainer appgc;
            appgc = new AppGameContainer(new StateGame("Babba's First Game"));
            appgc.setDisplayMode(640, 640, false);
            appgc.start();
        } catch(SlickException ex){
            Logger.getLogger(org.newdawn.slick.Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}