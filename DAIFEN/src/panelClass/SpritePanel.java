package panelClass;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: pfouche
 * Date: Mar 31, 2004
 * Time: 3:27:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpritePanel {
    private JPanel spritePanel;
    private JPanel imagePanel;
    private JLabel label;

    JPanel drawingArea;
    private Dimension position;
    private ImageIcon sprite;
    private JPanel encadrement;


    public SpritePanel( JPanel drawingArea, ImageIcon sprite, String label, Dimension position ) {
        this.drawingArea = drawingArea;
        this.sprite = sprite;
        if ( label == null ) {
        }
        this.label.setText( label );
        if ( position == null ){
            position = new Dimension( 0, 0);
        }
        this.position = position;

        initializeSprite();
    }

    public void initializeSprite() {
           imagePanel.add( new JLabel( sprite ) );
           drawingArea.add( spritePanel );
    }
}
