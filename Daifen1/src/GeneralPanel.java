
import javax.swing.*;
import java.awt.*;


public class GeneralPanel extends JComponent{

    private JPanel generalPanel;

    private JPanel graphicContainer;
    private GraphicPanel graphicPanel = new GraphicPanel(true);

    private JPanel actionPanel;
    private JLabel nomRoyaume;


    public GeneralPanel() {

//      graphicPanel.setPreferredSize( new Dimension( graphicContainer.getWidth(),graphicContainer.getHeight()) );


        graphicContainer.getLayout().preferredLayoutSize(graphicContainer);
      graphicContainer.add(graphicPanel);
    }

    public void paint(Graphics g){
         graphicPanel.setPreferredSize( new Dimension( graphicContainer.getWidth(),graphicContainer.getHeight()) );

    }

    public static void main (String[] args){
/*      JFrame newFrame = new JFrame(GraphicsEnvironment.
                getLocalGraphicsEnvironment().
                getDefaultScreenDevice().
                getDefaultConfiguration());
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GeneralPanel generalPanel = new GeneralPanel();
        generalPanel.generalPanel.setPreferredSize(new Dimension(600, 500));
  //      generalPanel.setVisible(true);
        newFrame.setTitle("GraphicPanel...");

        newFrame.getContentPane().add( generalPanel.generalPanel, BorderLayout.CENTER);

        newFrame.pack();


        newFrame.show(); */

          GeneralPanel generalPanelForm = new GeneralPanel();

        final JFrame frame = new JFrame("GeneralPanel Form");
        frame.setContentPane(generalPanelForm.generalPanel);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.show();
        generalPanelForm.generalPanel.repaint();

    }

}
