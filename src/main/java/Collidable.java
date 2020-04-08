import org.newdawn.slick.geom.Rectangle;

public interface Collidable {
    String getDescription();
    boolean collidesWith(Collidable c);
    Rectangle getRectangle();
}
