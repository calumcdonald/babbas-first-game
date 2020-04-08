import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Star extends Entity{

    public static final int SCORE_VALUE = 10;

    private Animation sprite;
    private int id;

    public Star(float x, float y, int id) throws SlickException {
        super(x, y);
        this.id = id;

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

    private int getId(){
        return id;
    }
}
