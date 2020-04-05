import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Star extends Entity{

    private static final int SCORE_VALUE = 10;

    private Animation sprite;

    public Star(float x, float y) throws SlickException {
        super(x, y);

        Image[] movement = {new Image("data/star1.png"), new Image("data/star2.png")};
        int[] duration = {300, 300};

        sprite = new Animation(movement, duration, false);
    }

    @Override
    public void update(GameContainer gc) {
        long delta = 1;
        sprite.update(delta);
    }

    @Override
    public Animation getSprite() {
        return sprite;
    }

    public int getScoreValue(){
        return SCORE_VALUE;
    }
}
