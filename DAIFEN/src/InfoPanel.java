
import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: pfouche
 * Date: Mar 24, 2004
 * Time: 3:25:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class InfoPanel {
    public JPanel infoPanel;
    public JLabel seigneurName;
    public JLabel moon;
    public JLabel race;
    public JLabel continentName;
    public JPanel progressBarPanel;
    public JProgressBar progressBar = new JProgressBar();
    public JLabel textInfo;

    public InfoPanel() {
        progressBar.setBorderPainted(true);
        progressBar.setValue( 0 );
        progressBar.setStringPainted(true);

        progressBarPanel.add( progressBar );
        progressBarPanel.setVisible(false);
    }
}
