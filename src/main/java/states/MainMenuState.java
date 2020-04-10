package states;

import entities.NPC;
import menu.MenuButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {

    private Image background;
    private MenuButton start, scores, exit;
    private TrueTypeFont ttf;

    public MainMenuState(int state){

    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        java.awt.Font font = new java.awt.Font("data/tw_cen_mt.ttf", java.awt.Font.PLAIN, 12);
        ttf = new TrueTypeFont(font, true);

        background = new Image("data/background.png");
        start = new MenuButton("data/start.png", background.getWidth()/2 - 64, 230);
        scores = new MenuButton("data/scores.png", background.getWidth()/2 - 64, 304);
        exit = new MenuButton("data/exit.png", background.getWidth()/2 - 64, 378);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input inp = gc.getInput();
        int xPos = inp.getMouseX();
        int yPos = inp.getMouseY();

        if(inp.isMouseButtonDown(0)) {
            if (start.isSelected(xPos, yPos)){
                sbg.getState(1).init(gc, sbg);
                sbg.enterState(1);
            } else if (exit.isSelected(xPos, yPos)) {
                gc.exit();
            }
            else if (scores.isSelected(xPos, yPos)) {
                sbg.enterState(2);
            }
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        gc.setShowFPS(false);
        background.draw(0, 0);
        g.setFont(ttf);
        g.drawString("BABBA", background.getWidth()/2 - 23, 200);
        start.getImg().draw(start.getX(), start.getY());
        scores.getImg().draw(scores.getX(), scores.getY());
        exit.getImg().draw(exit.getX(), exit.getY());
    }
}
