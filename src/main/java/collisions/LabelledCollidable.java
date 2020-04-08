package collisions;

import collisions.Collidable;
import map.Map;
import org.newdawn.slick.geom.Rectangle;

public class LabelledCollidable implements Collidable {

    private Rectangle rect;
    private String description;

    public LabelledCollidable(float x, float y, String description){
        rect = new Rectangle(x, y, Map.TILE_SIZE, Map.TILE_SIZE);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean collidesWith(Collidable c) {
        return rect.intersects(c.getRectangle());
    }


    public Rectangle getRectangle() {
        return rect;
    }
}
