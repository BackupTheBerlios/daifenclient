import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Patrick
 * Date: 5 mai 2004
 * Time: 00:44:19
 * To change this template use File | Settings | File Templates.
 */
class GraphicPanel extends JPanel{
    private final int marge = 5;

    private final double pi = 3.14;
    private final double angleStep = pi / 50;
    private double globalRotation=0;
    private double angleSelection = 0;
    private boolean isInRotation = false;

    private ArrayList spriteList = new ArrayList();
    private GraphicPanelMouseListener mouseListener = new GraphicPanelMouseListener();

    public GraphicPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        setBackground(Color.BLACK);

        this.addMouseListener(mouseListener);
    }

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0, getWidth(), getHeight());

        for ( int i=spriteList.size()-1; i >= 0; i--){
            ((Sprite)(spriteList.get(i))).setPosition(calculPositionCirculaire(i, spriteList.size()-1));
            ((Sprite)(spriteList.get(i))).drawSprite( (Graphics2D)g );
        }
    }

    public void addSprite(String filename, String label, boolean isSelected){
        spriteList.add( new Sprite(filename, label, isSelected) );
        updateSpritesPosition();
        repaint();
    }

   /* public void drawSprite( Graphics2D g, Sprite sprite) {
        // Definition de l'espace vertical entre l'image et le label en pixel.
        final int espaceImageLabel = 12;

        g.drawImage( sprite.getImageIcon().getImage(),
                (int)(sprite.getPosition().x - (sprite.getImageIcon().getIconWidth()/2)),
                (int)(sprite.getPosition().y - (sprite.getImageIcon().getIconHeight()/2)),
                sprite.getImageIcon().getIconWidth(),
                sprite.getImageIcon().getIconHeight(),
                this);
        if ( sprite.isSelected()) {
            g.setColor(Color.RED);
            g.drawRoundRect((int)(sprite.getPosition().x - (sprite.getImageIcon().getIconWidth()/2)),
                    (int)(sprite.getPosition().y - (sprite.getImageIcon().getIconHeight()/2)),
                    sprite.getImageIcon().getIconWidth(),
                    sprite.getImageIcon().getIconHeight(),
                    10,10);
        }

        g.setColor(Color.WHITE);
        g.drawString( sprite.getLabel(),
                (float)(sprite.getPosition().x -(sprite.getImageIcon().getIconWidth()/2) + espaceImageLabel ),
                (float)(sprite.getPosition().y + (sprite.getImageIcon().getIconHeight()/2) + espaceImageLabel));
    }*/


    private void updateSpritesPosition(){
        for ( int i=0; i < spriteList.size(); i++){
            ((Sprite)(spriteList.get(i))).setPosition(calculPositionCirculaire(i, spriteList.size()-1));
        }
    }

    private Point calculPositionCirculaire( int positionCirculaire, int nbPosition ) {

        Point centrePanel = new Point(
                (int)(this.getWidth()/2),
                (int)(this.getHeight()/2)
        );

        if ( positionCirculaire == 0) {
            return centrePanel;
        }
        else {
            // calcul du rayon
            int rayonMax = (int)((centrePanel.x) - ((Sprite)(spriteList.get(positionCirculaire))).getImageIcon().getIconWidth()/2);
            int rayonTmp = (int)((centrePanel.y) -((Sprite)(spriteList.get(positionCirculaire))).getImageIcon().getIconHeight()/2);
            if ( rayonTmp < rayonMax){
                rayonMax = rayonTmp;
            }
            rayonMax -= marge;

            // Calcul de l'angle
            double alpha = (((positionCirculaire-1) * 2*pi / nbPosition) + globalRotation) % (2*pi);
              if (alpha < 0) {
                alpha = (2*pi) + alpha;
            }
            ((Sprite)spriteList.get(positionCirculaire)).setAngle( alpha );


            int x = (int) (centrePanel.x + (Math.cos(alpha)*rayonMax));
            int y = (int) (centrePanel.y + (Math.sin(alpha)*rayonMax));

            return new Point( x, y);
        }
    }

    public int getSelectedSprite() {
        for ( int i =0; i < spriteList.size(); i++ ) {
            if ( ((Sprite)spriteList.get(i)).isSelected() ) {
                return i;
            }
        }
        return -1;
    }

    public void selectSprite(int index) {
        unselectAll(false);

        ((Sprite)spriteList.get(index)).setSelected(true);

        repaint();
    }

    private void unselectAll( boolean refresh ) {
        for ( int i =0; i < spriteList.size(); i++ ) {
            ((Sprite)spriteList.get(i)).setSelected(false);
        }

        if ( refresh ) {
            repaint();
        }
    }

    class GraphicPanelMouseListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public void mouseEntered(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public void mouseExited(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public void mousePressed(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public void mouseReleased(MouseEvent e) {
                Point mousePosition = e.getPoint();

            int selectedZoneWidth = 0;
            int selectedZoneHeight = 0;
            Point spritePosition = null;


            for ( int i =0; i < spriteList.size(); i++ ) {
                selectedZoneWidth = ((Sprite)spriteList.get(i)).getImageIcon().getIconWidth();
                selectedZoneHeight = ((Sprite)spriteList.get(i)).getImageIcon().getIconHeight();
                spritePosition = ((Sprite)spriteList.get(i)).getPosition();


                if (    !isInRotation &&
                        mousePosition.x  > spritePosition.x - (selectedZoneWidth/2) &&
                        mousePosition.x  < spritePosition.x + (selectedZoneWidth/2) &&
                        mousePosition.y  > spritePosition.y - (selectedZoneHeight/2) &&
                        mousePosition.y  < spritePosition.y + (selectedZoneHeight/2) ) {

                    selectSprite(i);

                    SelectionThread selectionThread = new SelectionThread();
                    selectionThread.start();
                }
            }
        }

    }

    public static void main (String[] args){
        JFrame newFrame = new JFrame(GraphicsEnvironment.
                getLocalGraphicsEnvironment().
                getDefaultScreenDevice().
                getDefaultConfiguration());
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicPanel graphicPanel = new GraphicPanel(true);
        graphicPanel.setPreferredSize(new Dimension(600, 500));

        newFrame.setTitle("GraphicPanel...");

        newFrame.getContentPane().add( graphicPanel, BorderLayout.CENTER);

        newFrame.pack();

        newFrame.show();


        String picturesPath = "./src/pictures/";
        // mon royaume
        String filename = picturesPath + "royaumeElfe.jpg";
        graphicPanel.addSprite( filename, "Argawaen", true);
        // les autres royaumes
        filename = picturesPath + "royaumeOrc.jpg";
        graphicPanel.addSprite( filename, "Stollvor", false);
        filename = picturesPath + "royaumeNain.jpg";
        graphicPanel.addSprite( filename, "Portekwa", false);
        filename = picturesPath + "royaumeHumain.jpg";
        graphicPanel.addSprite( filename, "St-Marcelin", false);
        filename = picturesPath + "royaumeOrc.jpg";
        graphicPanel.addSprite( filename, "titi", false);
        filename = picturesPath + "royaumeOrc.jpg";
        graphicPanel.addSprite( filename, "toto", false);

    }

    private class SelectionThread extends Thread {

        private final int sleepTime = 15;

        public SelectionThread() {
            super();
        }

       public void run(){
            int sensRotation;
            double deltaAngle;
            long startDate = 0;
            long endDate = 0;

            int selectedIndex = getSelectedSprite();


            // si c'est le sprite central ===> aucune rotation
            if (selectedIndex == 0) {
                return;
            }
            else {
                isInRotation = true;
            }

            // choix du sens de rotation pour aller au plus court
           double angle = ((Sprite)(spriteList.get(selectedIndex))).getAngle();
            if ( angle < angleSelection + pi  && angle > angleSelection) {
                sensRotation = -1;
            }
            else {
                sensRotation = 1;
            }

            while ( Math.abs( ((Sprite)(spriteList.get(selectedIndex))).getAngle() - angleSelection ) >= angleStep ) {

                startDate = (new Date()).getTime();
/*               deltaAngle = Math.abs( ((Sprite)(spriteList.get(selectedIndex))).getAngle() - angleSelection );
                if ( deltaAngle <  angleStep ) {
                    globalRotation += deltaAngle * sensRotation;
                }
                else {
                    globalRotation += angleStep * sensRotation;
                }
 */
                globalRotation += (angleStep * sensRotation);

                updateSpritesPosition();
                repaint();

                endDate = (new Date()).getTime();

                long deltaDate = endDate - startDate;
                if (deltaDate < sleepTime) {
                    try {
                        Thread.sleep(sleepTime - deltaDate );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

           isInRotation = false;

        }

    }

}