package entities;

import entities.Entity;
import org.newdawn.slick.*;

public class Player extends Entity {

    private static final float SPEED = 0.1f;
    private static final float SPRINT_SPEED = 0.3f;

    private Animation sprite, up, down, left, right;
    private int score = 0;

    public Player(float x, float y) throws SlickException{
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
        Input input = gc.getInput();
        float speed;
        long delta = 1;

        if(input.isKeyDown(Input.KEY_LSHIFT)){
            speed = SPRINT_SPEED;
        }
        else{
            speed = SPEED;
        }

        if(input.isKeyDown(Input.KEY_W)){
            float newY = y - speed;
            sprite = up;
            sprite.update(delta);
            nextCollisionBox.setLocation(x, newY);
        }
        else if(input.isKeyDown(Input.KEY_S)){
            float newY = y + speed;
            sprite = down;
            sprite.update(delta);
            nextCollisionBox.setLocation(x, newY);
        }
        else if(input.isKeyDown(Input.KEY_A)){
            float newX = x - speed;
            sprite = left;
            sprite.update(delta);
            nextCollisionBox.setLocation(newX, y);
        }
        else if(input.isKeyDown(Input.KEY_D)){
            float newX = x + speed;
            sprite = right;
            sprite.update(delta);
            nextCollisionBox.setLocation(newX, y);
        }
        else if(input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_D)){
            float newX = x + speed;
            float newY = y + speed;
            sprite = right;
            sprite.update(delta);
            nextCollisionBox.setLocation(newX, newY);
        }
    }

    public void addToScore(int amount){
        score+=amount;
    }

    public int getScore(){
        return score;
    }

    public String getDescription(){
        return "player";
    }
}
