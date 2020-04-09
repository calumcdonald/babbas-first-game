package states;

import collisions.Collidable;
import collisions.Collision;
import entities.Entity;
import entities.Player;
import map.Map;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayState extends BasicGameState {

    public static final int SCALE = 2;
    public static final int TILE_SIZE = 32;

    private Map level1, level2, level3, map;
    private Player babba;
    private long startTime, timeElapsedSecs;
    private TrueTypeFont ttf;

    public PlayState(int state){

    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        startTime = System.nanoTime();
        java.awt.Font font = new java.awt.Font("data/tw_cen_mt.ttf", java.awt.Font.PLAIN, 12);
        ttf = new TrueTypeFont(font, true);

        level1 = new Map("data/babba.tmx", 0);
        level2 = new Map("data/babba2.tmx", 1);
        level3 = new Map("data/babba3.tmx", 2);
        map = level1;

        babba = new Player(TILE_SIZE + 8, TILE_SIZE + 8);

        level1.addEntity(babba);
        level2.addEntity(babba);
        level3.addEntity(babba);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Collidable starToRemove = null;

        for(Entity e : map.getEntities()){
            e.update(gc);

            Collision c = e.checkCollision(map.getColliders());
            if(c == null) {
                e.setNextLocation();
            }
            else if(c.getc1().getDescription().equals("player") && c.getc2().getDescription().equals("portal")){
                if(map.getId() == 0){
                    map = level2;
                    e.setLocation(TILE_SIZE * 8 + 8, TILE_SIZE + 8);
                }
                else if(map.getId() == 1){
                    map = level3;
                    e.setLocation(TILE_SIZE + 8, TILE_SIZE + 8);
                }
                else if(map.getId() == 2){
                    if(babba.getScore() != 15) {
                        map = level1;
                        e.setLocation(TILE_SIZE + 8, TILE_SIZE + 8);
                    }
                    else{
                        sbg.enterState(3);
                        System.out.println("Your score: " + timeElapsedSecs + "secs.");
                    }
                }
                map.updateCollisions();
            }
            else if(c.getc1().getDescription().equals("player") && c.getc2().getDescription().equals("star")){
                starToRemove = c.getc2();
            }
        }

        if(starToRemove != null){
            babba.updateScore(1);
            map.removeEntity(starToRemove);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        g.scale(SCALE, SCALE);
        gc.setShowFPS(false);
        map.getMap().render(0, 0);

        for(Entity e : map.getEntities()){
            e.getSprite().draw(e.getX(), e.getY());
        }

        //Timer
        long currentTime = System.nanoTime();
        long timeElapsed = currentTime - startTime;
        timeElapsedSecs = timeElapsed / 1000000000;
        String string = "Time: " + Long.toString(timeElapsedSecs) + "s.";
        g.setFont(ttf);
        g.setColor(Color.black);
        g.drawString(string, 10, 10);
        g.setColor(Color.white);

        //renderCollisionBoxes(g);
    }

    public void renderCollisionBoxes(Graphics g){
        for(Collidable wall: map.getColliders()){
            if(wall.getDescription().equals("wall")){
                g.setColor(Color.white);
            }
            else if(wall.getDescription().equals("portal")){
                g.setColor(Color.magenta);
            }
            g.draw(wall.getCollisionBox());
            g.setColor(Color.white);
        }

        for(Entity e : map.getEntities()){
            if(e.getDescription().equals("star")){
                g.setColor(Color.yellow);
            }
            else if(e.getDescription().equals("player")) {
                g.setColor(Color.red);
            }
            g.draw(e.getCollisionBox());
            g.setColor(Color.white);
        }
    }
}
