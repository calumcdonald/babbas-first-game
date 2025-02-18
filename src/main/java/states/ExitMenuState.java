package states;

import menu.MenuButton;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExitMenuState extends BasicGameState {

    private Image background;
    private MenuButton exit, restart, scores;
    private TrueTypeFont ttf;

    public ExitMenuState(int state){

    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        java.awt.Font font = new java.awt.Font("data/tw_cen_mt.ttf", java.awt.Font.PLAIN, 16);
        ttf = new TrueTypeFont(font, true);

        background = new Image("data/background.png");
        restart = new MenuButton("data/restartB.png", background.getWidth()/2 - 64, 250);
        scores = new MenuButton("data/scoresB.png", background.getWidth()/2 - 64, 324);
        exit = new MenuButton("data/exitB.png", background.getWidth()/2 - 64, 398);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input inp = gc.getInput();
        int xPos = inp.getMouseX();
        int yPos = inp.getMouseY();

        if(inp.isMouseButtonDown(0)) {
            if (restart.isSelected(xPos, yPos)) {
                sbg.getState(1).init(gc, sbg);
                sbg.enterState(1);
            } else if (exit.isSelected(xPos, yPos)) {
                gc.exit();
            } else if (scores.isSelected(xPos, yPos)){
                sbg.enterState(2);
            }
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        gc.setShowFPS(false);
        background.draw(0, 0);
        g.setFont(ttf);
        g.drawString("FINISH!", background.getWidth()/2 - 30, 200);
        g.drawString("Your score is: " + getLastScoreLine() + "seconds.", background.getWidth()/2 - 90, 220);

        restart.getImg().draw(restart.getX(), restart.getY());
        scores.getImg().draw(scores.getX(), scores.getY());
        exit.getImg().draw(exit.getX(), exit.getY());
    }

    private String getLastScoreLine(){
        String currentLine;
        String lastLine = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("data/scores.txt"));

            while((currentLine = br.readLine()) != null){
                lastLine = currentLine;
            }
        }catch(IOException io){
            System.out.println("poo");
        }
        return lastLine;
    }
}
