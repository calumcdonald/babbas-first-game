package states;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {

    Image start, exit, scores, background;

    public MainMenuState(int state){

    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        start = new Image("data/start.png");
        exit = new Image("data/exit.png");
        scores = new Image("data/scores.png");
        background = new Image("data/background.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        gc.setShowFPS(false);
        background.draw(0, 0);
        g.drawString("BABBA", background.getWidth()/2 -20, 200);
        start.draw(background.getWidth()/2 - 32, 250);
        scores.draw(background.getWidth()/2 - 32, 290);
        exit.draw(background.getWidth()/2 - 32, 330);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int xPos = Mouse.getX();
        int yPos = Mouse.getY();

        if (Mouse.isButtonDown(0)) {
            sbg.getState(1).init(gc, sbg);
            sbg.enterState(1);
        }
    }
}
