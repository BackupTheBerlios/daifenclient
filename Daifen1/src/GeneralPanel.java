
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerListener;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: pfouche
 * Date: Mar 31, 2004
 * Time: 1:48:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeneralPanel extends JPanel{
 //TODO   public JPanel generalPanel;
    public JPanel actionPanel;
    public MyGraphicPanel graphicPanel;



    //Create the scroll pane and add the table to it.
 //TODO   JScrollPane scrollPane = new JScrollPane();

    public GeneralPanel() {


            // setLayout(new BorderLayout());
       setLayout(new BorderLayout());

        // set the preferred size of the demo
  ////     setPreferredSize(new Dimension(600,600));

 //       this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
 //       setAlignmentX(LEFT_ALIGNMENT);
 //       setAlignmentY(TOP_ALIGNMENT);


        graphicPanel = new MyGraphicPanel(true);
        graphicPanel.setBackground(Color.BLACK);
//        graphicPanel.setForeground(Color.BLACK);
//        graphicPanel.setIgnoreRepaint(false);

        this.add(graphicPanel);
        this.validate();


        // TODO enlever le test
            String picturesPath = "./src/pictures/";

            // mon royaume
            String filename = picturesPath + "royaumeElfe.jpg";
            graphicPanel.addSprite( filename, "Argawaen");
            // les autres royaumes
            filename = picturesPath + "royaumeOrc.jpg";
            graphicPanel.addSprite( filename, "Stollvor" );
            filename = picturesPath + "royaumeNain.jpg";
            graphicPanel.addSprite( filename, "Portekwa" );
            filename = picturesPath + "royaumeHumain.jpg";
            graphicPanel.addSprite( filename, "St-Marcelin" );
            filename = picturesPath + "royaumeOrc.jpg";
            graphicPanel.addSprite( filename, "titi" );
            filename = picturesPath + "royaumeOrc.jpg";
            graphicPanel.addSprite( filename, "toto" );
            // TODO FIN TEST
        graphicPanel.repaint();
    }

 /*   public void paint(Graphics g){
        // TODO
              graphicPanel.repaint();
              graphicPanel.setBackground(Color.BLACK);

               String picturesPath = "./src/pictures/";
               // TODO enlever le test
               // mon royaume
               String filename = picturesPath + "royaumeElfe.jpg";
               new Sprite( graphicPanel, filename, "Argawaen", 0 , 0);
               // les autres royaumes
               filename = picturesPath + "royaumeOrc.jpg";
               new Sprite( graphicPanel, filename, "Stollvor", 1 , 5);
               filename = picturesPath + "royaumeNain.jpg";
               new Sprite( graphicPanel, filename, "Portekwa", 2 , 5);
               filename = picturesPath + "royaumeHumain.jpg";
               new Sprite( graphicPanel, filename, "St-Marcelin", 3 , 5);
               filename = picturesPath + "royaumeOrc.jpg";
               new Sprite( graphicPanel, filename, "titi", 4 , 5);
               filename = picturesPath + "royaumeOrc.jpg";
               new Sprite( graphicPanel, filename, "toto", 5 , 5);
               // TODO FIN TEST


    }
     */
    public void updateGraphicPanel( Continent currentContinent ) {

        String picturesPath = "./src/pictures/";

        // affiche mon royaume
        /*
        String filename = picturesPath + "royaume" + currentContinent.myRoyaume[0].getLord().getRace() +".jpg";
        new Sprite( graghicPanel, filename, currentContinent.myRoyaume[0].getLord().getLogin(), 0 , 0);

        for ( int i=0 ; i < currentContinent.otherRoyaume.length ; i++) {
            filename = picturesPath + "royaume" + currentContinent.otherRoyaume[i].getLord().getRace() +".jpg";
            new Sprite( graghicPanel, filename, currentContinent.otherRoyaume[i].getLord().getLogin(), i+1, currentContinent.otherRoyaume.length);
        }
        */
    }


}

class MyGraphicPanel extends JPanel{

    private ArrayList spriteList = new ArrayList();

    public MyGraphicPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public void paint(Graphics g){
        // TODO

        this.setBackground(Color.BLACK);

        for ( int i=0; i < spriteList.size(); i++){
            drawSprite( (Graphics2D)g, ((Sprite)( spriteList.get(i) )) );
        }
    }

    public void addSprite( String filename, String label){
        spriteList.add( new Sprite(filename, label) );
        updateSpritesPosition();
        repaint();
    }

    public void drawSprite( Graphics2D g, Sprite sprite) {
        // Definition de l'espace vertical entre l'image et le label en pixel.
        int espaceImageLabel = 5;

        g.drawImage( sprite.imageIcon.getImage(),
                (int)(sprite.position.getWidth()-(sprite.imageIcon.getIconWidth()/2)),
                (int)(sprite.position.getHeight()-(sprite.imageIcon.getIconHeight()/2)),
                sprite.imageIcon.getIconWidth(),
                sprite.imageIcon.getIconHeight(),
                this);

        g.drawString( sprite.label,
                (float)(sprite.position.getWidth()-(sprite.imageIcon.getIconWidth()/2) + espaceImageLabel ),
                (float)(sprite.position.getHeight() + (sprite.imageIcon.getIconHeight()/2) + espaceImageLabel));
    }


    private void updateSpritesPosition(){
        for ( int i=0; i < spriteList.size(); i++){
            ((Sprite)(spriteList.get(i))).setPosition(calculPositionCirculaire(i, spriteList.size()-1));
        }
    }

    private Dimension calculPositionCirculaire( int positionCirculaire, int nbPosition ) {

        Dimension centrePanel = new Dimension(
                (int)(this.getWidth()/2),
                (int)(this.getHeight()/2)
        );

//               Dimension centrePanel = new Dimension(300,300);

        if ( positionCirculaire == 0) {
            return centrePanel;
        }
        else {
            // calcul du rayon
            int rayonMax = (int)((centrePanel.getWidth()/2) - ((Sprite)(spriteList.get(positionCirculaire))).imageIcon.getIconWidth()/2);
            int rayonTmp = (int)((centrePanel.getHeight()/2) -((Sprite)(spriteList.get(positionCirculaire))).imageIcon.getIconHeight()/2);
            if ( rayonTmp < rayonMax){
                rayonMax = rayonTmp;
            }

            double alpha = (positionCirculaire-1) * 3.14 * 2 / nbPosition;

            int x = (int) (centrePanel.getWidth() + (Math.cos(alpha)*rayonMax));
            int y = (int) (centrePanel.getHeight() + (Math.sin(alpha)*rayonMax));

            return new Dimension( x, y);
        }
    }
}