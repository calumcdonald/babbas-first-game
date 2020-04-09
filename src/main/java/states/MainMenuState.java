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
    private NPC npc;

    public MainMenuState(int state){

    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("data/background.png");
        start = new MenuButton("data/start.png", background.getWidth()/2 - 32, 250);
        scores = new MenuButton("data/scores.png", background.getWidth()/2 - 32, 290);
        exit = new MenuButton("data/exit.png", background.getWidth()/2 - 32, 330);

        npc = new NPC(100, 100);
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
        }

        npc.update(gc);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        gc.setShowFPS(false);
        background.draw(0, 0);
        g.drawString("BABBA", background.getWidth()/2 - 23, 200);
        npc.getSprite().draw(npc.getX(), npc.getY());

        start.getImg().draw(start.getX(), start.getY());
        scores.getImg().draw(scores.getX(), scores.getY());
        exit.getImg().draw(exit.getX(), exit.getY());
    }
}
