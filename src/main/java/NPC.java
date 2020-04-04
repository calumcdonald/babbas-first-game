import org.newdawn.slick.*;

import java.util.Random;

public class NPC extends Entity{

    private Animation sprite, up, down, left, right;

    public NPC(double x, double y) throws SlickException {
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
        int direction = new Random().nextInt(3);
        long delta = 1;

        if(direction == 0){
            double newY = y - delta * 0.1;
            sprite = up;
            sprite.update(delta);
            y = newY;
            collisionBox.setLocation((int)x, (int)y);
        }
        else if(direction == 1){
            double newY = y + delta * 0.1;
            sprite = down;
            sprite.update(delta);
            y = newY;
            collisionBox.setLocation((int)x, (int)y);
        }
        else if(direction == 2){
            double newX = x - delta * 0.1;
            sprite = left;
            sprite.update(delta);
            x = newX;
            collisionBox.setLocation((int)x, (int)y);
        }
        else if(direction == 3){
            double newX = x + delta * 0.1;
            sprite = right;
            sprite.update(delta);
            x = newX;
            collisionBox.setLocation((int)x, (int)y);
        }
    }
}
