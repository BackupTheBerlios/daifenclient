
import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Patrick
 * Date: 5 mai 2004
 * Time: 00:45:42
 * To change this template use File | Settings | File Templates.
 */
public class Sprite extends JComponent {
   private Point position;
   private double angle;
   private ImageIcon imageIcon;
   private String imageFileName;
   private String label;
   private boolean isSelected;

  /*  public Sprite( JPanel drawingArea, String imageFileName, String label, Dimension position ) {

        this.drawingArea = drawingArea;

        if ( imageFileName == null ) {
            imageFileName = "";
        }
        this.imageFileName = imageFileName;

        if ( label == null ) {
            label = "";
        }
        this.label = label;

        if ( position == null ){
            position = new Dimension( 0, 0);
            }
            this.position = position;

            initializeSprite();
            }
*/
  public Sprite (String imageFileName, String label, boolean isSelected) {

      if ( imageFileName == null ) {
          imageFileName = "";
      }
      this.imageFileName = imageFileName;

      if ( label == null ) {
          label = "";
      }
      this.label = label;

      loadImage(this.imageFileName);

      this.isSelected = isSelected;

  }

    public void drawSprite( Graphics2D g) {
        // Definition de l'espace vertical entre l'image et le label en pixel.
//        final int espaceImageLabel = 12;
        final int espaceImageLabel = 5;

        g.drawImage( imageIcon.getImage(),
                (int)(position.x - (imageIcon.getIconWidth()/2)),
                (int)(position.y - (imageIcon.getIconHeight()/2)),
                imageIcon.getIconWidth(),
                imageIcon.getIconHeight(),
                this);
        if ( isSelected ) {
            g.setColor(Color.RED);
            g.drawRoundRect((int)(position.x - (imageIcon.getIconWidth()/2)),
                    (int)(position.y - (imageIcon.getIconHeight()/2)),
                    imageIcon.getIconWidth(),
                    imageIcon.getIconHeight(),
                    10,10);
        }

        g.setColor(Color.WHITE);
        g.drawString( label,
                (float)(position.x -(imageIcon.getIconWidth()/2) + espaceImageLabel ),
                (float)(position.y + (imageIcon.getIconHeight()/2) + espaceImageLabel));
    }


    private void loadImage(String imageFileName) {
        imageIcon = new ImageIcon( imageFileName, label );
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
        loadImage(imageFileName);
    }


    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

}
