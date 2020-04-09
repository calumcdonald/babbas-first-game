package entities;

import collisions.Collidable;
import org.newdawn.slick.*;

import java.util.Random;

public class NPC extends Entity implements Collidable {

    private static final float SPEED = 0.1f;
    
    private Animation sprite, up, down, left, right;
    private float lastMoveTime = System.currentTimeMillis();
    private int direction = 4;

    public NPC(float x, float y) throws SlickException {
        super(x, y);

        Image[] movementUp = {new Image("data/up_1.png"), new Image("data/up_2.png")};
        Image[] movementDown = {new Image("data/down_1.png"), new Image("data/down_2.png")};
        Image[] movementLeft = {new Image("data/left_1.png"), new Image("data/left_2.png")};
        Image[] movementRight = {new Image("data/right_1.png"), new Image("data/right_2.png")};
        int[] duration = {300, 300};

        up = new Animation(movementUp, duration, false);
        down = new Animation(movementDown, duration, false);
        left = new Animation(movementLeft, duration, false);
        right = new Animation(movementRight, duration, false);

        sprite = right;
    }

    @Override
    public Animation getSprite(){
        return sprite;
    }

    @Override
    public void update(GameContainer gc){
        long delta = 1;
        long currentTime = System.currentTimeMillis();

        if(currentTime >= lastMoveTime + 30000) {
            direction = new Random().nextInt(4);
        }

        if(direction == 0){
            float newY = y - SPEED;
            sprite = up;
            sprite.update(delta);
            nextCollisionBox.setLocation(x, newY);
        }
        else if(direction == 1){
            float newY = y + SPEED;
            sprite = down;
            sprite.update(delta);
            nextCollisionBox.setLocation(x, newY);
        }
        else if(direction == 2){
            float newX = x - SPEED;
            sprite = left;
            sprite.update(delta);
            nextCollisionBox.setLocation(newX, y);
        }
        else if(direction == 3){
            float newX = x + SPEED;
            sprite = right;
            sprite.update(delta);
            nextCollisionBox.setLocation(newX, y);
        }
    }

    @Override
    public String getDescription() {
        return "npc";
    }
}
