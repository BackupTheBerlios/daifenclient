
import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: pfouche
 * Date: Mar 31, 2004
 * Time: 2:03:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Sprite extends JComponent {
    Dimension position;
    ImageIcon imageIcon;
    String imageFileName;
    String label;

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
  public Sprite ( String imageFileName, String label) {

      if ( imageFileName == null ) {
          imageFileName = "";
      }
      this.imageFileName = imageFileName;

      if ( label == null ) {
          label = "";
      }
      this.label = label;

      loadImage(this.imageFileName);

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


    public Dimension getPosition() {
        return position;
    }

    public void setPosition(Dimension position) {
        this.position = position;
    }

}
