package states;

import menu.MenuButton;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoresState extends BasicGameState {

    private Image background;
    private MenuButton back;
    private TrueTypeFont ttf;

    public ScoresState(int state){

    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        java.awt.Font font = new java.awt.Font("data/tw_cen_mt.ttf", java.awt.Font.PLAIN, 16);
        ttf = new TrueTypeFont(font, true);

        background = new Image("data/background.png");
        back = new MenuButton("data/menuB.png", 450, 250);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input inp = gc.getInput();
        int xPos = inp.getMouseX();
        int yPos = inp.getMouseY();

        if(inp.isMouseButtonDown(0)) {
            if(back.isSelected(xPos, yPos)) {
                sbg.enterState(0);
            }
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        gc.setShowFPS(false);
        background.draw(0, 0);
        g.setFont(ttf);
        g.drawString("TOP 10 SCORES", background.getWidth()/2 - 58, 200);

        List<Integer> scores = getTopScores();
        if(scores.size() == 0){
            g.drawString("No scores set yet!", background.getWidth()/2 - 75, 220);
        }
        else {
            for (int i = 0; i < scores.size(); i++) {
                g.drawString(i + 1 + "." + " " + scores.get(i) + "seconds", background.getWidth() / 2 - 40, 220 + i * 15);
            }
        }

        back.getImg().draw(back.getX(), back.getY());
    }

    private List<Integer> getTopScores(){
        List<Integer> scores = new ArrayList<>();
        String currentLine;

        try {
            BufferedReader br = new BufferedReader(new FileReader("data/scores.txt"));

            while((currentLine = br.readLine()) != null){
                scores.add(Integer.parseInt(currentLine));
            }
        }catch(IOException io){
            System.out.println("Oopsie.");
        }

        Collections.sort(scores);
        if(scores.size() < 10){
            scores = scores.subList(0, scores.size());
        }
        else{
            scores = scores.subList(0, 10);
        }


        return scores;
    }
}
