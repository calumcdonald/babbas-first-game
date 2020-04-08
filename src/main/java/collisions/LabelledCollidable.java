package collisions;

import org.newdawn.slick.geom.Rectangle;

public class LabelledCollidable implements Collidable {

    private Rectangle rect;
    private String description;

    public LabelledCollidable(Rectangle rect, String description){
        this.rect = rect;
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
