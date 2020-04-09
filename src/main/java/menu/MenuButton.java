package menu;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class MenuButton {

    private Image img;
    private Rectangle box;
    private int x, y;

    public MenuButton(String imgPath, int x, int y) throws SlickException {
        this.x = x;
        this.y = y;
        img = new Image(imgPath);
        box = new Rectangle(x, y, img.getWidth(), img.getHeight());
    }

    public boolean isSelected(int x, int y){
        Rectangle mouse = new Rectangle(x, y, 1, 1);
        if(mouse.intersects(box)){
            return true;
        }
        return false;
    }

    public Image getImg(){
        return img;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }
}
