
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: pfouche
 * Date: Mar 8, 2004
 * Time: 5:07:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainForm extends JPanel {

    // The preferred size of the Application
    private static final int PREFERRED_WIDTH = 720;
    private static final int PREFERRED_HEIGHT = 640;

    // Used only if swingset is an application
    private JFrame frame = null;

    // Menus
    private JMenuBar menuBar = null;

    // Table
    private JTabbedPane tabbedpane = null;

    private GeneralPanel generalPanel = new GeneralPanel();

    private BilanPanel bilanPanel = new BilanPanel(null);

    private JPanel orderPanel;

    private JPanel simulationPanel;

    // Info Panel
    private InfoPanel infoPanel;

    private static Vector swingSets = new Vector();



    // About Box
  //  private JDialog aboutBox = null;


    // Continent courant
    public Continent currentContinent = null;





    public MainForm( GraphicsConfiguration gc ) {


        frame = createFrame(gc);


        // setLayout(new BorderLayout());
        setLayout(new BorderLayout());

        // set the preferred size of the demo
        setPreferredSize(new Dimension(PREFERRED_WIDTH,PREFERRED_HEIGHT));

        initializeApp();

        showMainPanel();

    }





    /**
     * Create a frame for SwingSet2 to reside in if brought up
     * as an application.
     */
    public static JFrame createFrame(GraphicsConfiguration gc) {
        JFrame newFrame = new JFrame(gc);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    return newFrame;
    }

    /**
     * Returns the frame instance
     */
    public JFrame getFrame() {
	return frame;
    }
      /**
     * Bring up the SwingSet2 demo by showing the frame (only
     * applicable if coming up as an application, not an applet);
     */
    public void showMainPanel() {
	    // put swingset in a frame and show it
	    JFrame f = getFrame();
	    f.setTitle("Daifen IHM...");
	    f.getContentPane().add(this, BorderLayout.CENTER);
	    f.pack();

	    Rectangle screenRect = f.getGraphicsConfiguration().getBounds();
            Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(
                    f.getGraphicsConfiguration());

            // Make sure we don't place the demo off the screen.
            int centerWidth = screenRect.width < f.getSize().width ?
                    screenRect.x :
                    screenRect.x + screenRect.width/2 - f.getSize().width/2;
            int centerHeight = screenRect.height < f.getSize().height ?
                    screenRect.y :
                    screenRect.y + screenRect.height/2 - f.getSize().height/2;

            centerHeight = centerHeight < screenInsets.top ?
                    screenInsets.top : centerHeight;

            f.setLocation(centerWidth, centerHeight);
	    f.show();
          swingSets.add(this);

    }

    public void initializeApp() {
        JPanel top = this;
   /*     top.setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        top.setSize(this.getWidth(), this.getHeight());*/

        // Creates Menu bar at the top of the window.
        menuBar = createMenus();
        top.add(menuBar, BorderLayout.NORTH);

        // create tab
        tabbedpane = createTabbedPane();
        top.add(tabbedpane, BorderLayout.CENTER);

        // create info panel
        infoPanel = createInfoPanel();
        top.add(infoPanel.infoPanel, BorderLayout.SOUTH);

       }

     /**
     * Create Tabbed Pane
     */
    public JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.add( "General", this.generalPanel );
        tabbedPane.add( "Bilan", this.bilanPanel.bilanPanel );
        orderPanel = (JPanel) tabbedPane.add( "Ordres", new JPanel() );
        simulationPanel = (JPanel) tabbedPane.add( "Simulation", new JPanel() );

        return tabbedPane;
    }


     /**
     * Create Info Panel
     */
     public InfoPanel createInfoPanel() {
         InfoPanel NewInfoPanel = new InfoPanel();
     return NewInfoPanel;
    }


     /**
     * Create menus
     */
    public JMenuBar createMenus() {
	JMenuItem mi;
	// ***** create the menubar ****
	JMenuBar menuBar = new JMenuBar();
//	menuBar.getAccessibleContext().setAccessibleName(
//	    getString("MenuBar.accessible_description"));

	// ***** create File menu
	JMenu fileMenu = (JMenu) menuBar.add(new JMenu("Continent"));

     mi = (JMenuItem) fileMenu.add(new JMenuItem("Charger un Seigneur")) ;
     mi.addActionListener(new LordLoadAction(this));


        fileMenu.addSeparator();


           mi = (JMenuItem) fileMenu.add(new JMenuItem("Open")) ;

	return menuBar;
    }





    public static void main (String[] args){

        	MainForm mainForm = new MainForm( GraphicsEnvironment.
                                             getLocalGraphicsEnvironment().
                                             getDefaultScreenDevice().
                                             getDefaultConfiguration());

   /*
        MainForm form = new MainForm();

        final JFrame frame = new JFrame ("Petit essai d'interface...");
        frame.setContentPane(form);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.show();
        */
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public BilanPanel getBilanPanel() {
        return bilanPanel;
    }

    public GeneralPanel getGeneralPanel() {
        return generalPanel;
    }

    public void setGeneralPanel(GeneralPanel generalPanel) {
        this.generalPanel = generalPanel;
    }


}



class LordLoadAction extends AbstractAction {
MainForm mainForm;
    protected LordLoadAction(MainForm mainForm) {
        super("LordLoadAction");
    this.mainForm = mainForm;
    }

    public void actionPerformed(ActionEvent e) {

        // In a ComponentDialog, you can show as many message components and
    // as many options as you want:
        Lord lordToLoad;

    // Messages
         Object[] message;
        message = new Object[7];
        message[0] = "Choisissez un seigneur dans la liste. Saisissez votre mot de passe. puis validez.";

        JComboBox cb = new JComboBox();
        //????????????
        cb.addItem("Argawaen");
        cb.addItem("Portekwa");
        cb.addItem("Stollvor");
        //?????????


        message[1] = cb;

        message[2] = "Mot de passe Daifen :";
        // TODO Retirer le mot de passe en dure
        message[3] = new JPasswordField("Wctfs096");
        message[4] = new JCheckBox("Mot de passe Yahoo different :", false);
        message[5] = new JPasswordField();
        message[6] = "Ou creez un nouveau seigneur en cliquant sur le bouton <Creer un Seigneur>";

      //?????  ((JPasswordField)message[5]).setVisible( ((JCheckBox)message[4]).isSelected() );

        // Check Box Action
 /*       ((JCheckBox)message[4]).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((JPasswordField)message[5]).setVisible( ((JCheckBox)message[4]).isSelected() );
            }
        });   */

        // Options
        String[] options = {
            "Valider",
            "Creer un Seigneur",
            "Annuler"
        };
        int result = JOptionPane.showOptionDialog(
                mainForm,                                   // the parent that the dialog blocks
                message,                                    // the dialog message array
                "Charger un Seigneur...", // the title of the dialog window
                JOptionPane.DEFAULT_OPTION,                 // option type
                JOptionPane.INFORMATION_MESSAGE,            // message type
                null,                                       // optional icon, use null to use the default icon
                options,                                    // options string array, will be made into buttons
                options[0]                                  // option that should be made into a default button
        );
        switch(result) {
            case 0: // Valider
                if ( ((JCheckBox)message[4]).isSelected() ){
                    lordToLoad = new Lord ( ((String)((JComboBox)message[1]).getSelectedItem()), new String( ((JPasswordField)message[3]).getPassword()), new String( ((JPasswordField)message[5]).getPassword()) );
                }
                else{
                    lordToLoad = new Lord ( ((String)((JComboBox)message[1]).getSelectedItem()), new String( ((JPasswordField)message[3]).getPassword()), new String( ((JPasswordField)message[3]).getPassword()) );
                }

                lordLoad ( lordToLoad );

                break;
            case 1: // Creer un Seigneur
                showLordCreationWindow("");
//                    JOptionPane.showMessageDialog(getDemoPanel(), getString("OptionPaneDemo.component_r2"));
                break;
            default: // Annuler
                break;
        }

    }


    public void showLordCreationWindow( String lordName ) {

        // In a ComponentDialog, you can show as many message components and
        // as many options as you want:

        // Messages
        Object[]      message = new Object[6];
        message[0] = "Non du Seigneur:";
        message[1] = new JTextField( lordName );

        message[2] = "saisisser un Mot de passe Daifen :";
        message[3] = new JPasswordField();
        message[4] = "Saisisser le Mot de passe Yahoo :";
        message[5] = new JPasswordField();


        // Options
        String[] options = {
            "Creer",
            "Annuler"
        };
        int result = JOptionPane.showOptionDialog(
                mainForm,                                   // the parent that the dialog blocks
                message,                                    // the dialog message array
                "Creer un Seigneur...", // the title of the dialog window
                JOptionPane.DEFAULT_OPTION,                 // option type
                JOptionPane.INFORMATION_MESSAGE,            // message type
                null,                                       // optional icon, use null to use the default icon
                options,                                    // options string array, will be made into buttons
                options[0]                                  // option that should be made into a default button
        );

        switch(result) {
            case 0: // Valider
                // add a lord.

                if ( ((JTextField)message[1]).getText().length() == 0 ) {
                    JOptionPane.showMessageDialog( mainForm ,
                            "Vous n'avez pas saisi le nom du seigneur !...",
                            "Mauvaise saisie !...",
                            JOptionPane.WARNING_MESSAGE );
                    showLordCreationWindow( "Saisisez un nom ici" );
                }
                else if ( ((JPasswordField)message[3]).getPassword().length == 0 ) {
                    JOptionPane.showMessageDialog( mainForm ,
                            "Vous n'avez pas saisi le mot de passe Daifen !...",
                            "Mauvaise saisie du mot de passe !...",
                            JOptionPane.WARNING_MESSAGE );
                    showLordCreationWindow( ((JTextField)message[1]).getText() );
                }
                else if ( ((JPasswordField)message[5]).getPassword().length == 0 ) {
                    JOptionPane.showMessageDialog( mainForm ,
                            "Vous n'avez pas saisi le mot de passe Yahoo !...",
                            "Mauvaise saisie du mot de passe !...",
                            JOptionPane.WARNING_MESSAGE );
                    showLordCreationWindow( ((JTextField)message[1]).getText() );
                }
                else {
                    // load a lord
                    Lord lordToLoad = new Lord( ((JTextField)message[1]).getText(),
                            new String(((JPasswordField)message[3]).getPassword()),
                            new String(((JPasswordField)message[3]).getPassword()) );
                    lordLoad ( lordToLoad );
                }
                break;
            default: // Annuler
                break;

        }
    }

    public void lordLoad ( Lord lordToLoad ){
        int res = JOptionPane.showConfirmDialog( mainForm ,
                "Chargement du seigneur "+ lordToLoad.getLogin() +"...",
                "Chargement !...",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE );

        if ( res ==  JOptionPane.YES_OPTION ){

           ContinentLoadingThread thread = new ContinentLoadingThread( mainForm, lordToLoad );
           thread.start();
        }
    }
}


class ContinentLoadingThread extends Thread {

    MainForm mainForm;
    Lord lordToLoad;

    public ContinentLoadingThread( MainForm mainForm, Lord lordToLoad ) {
        this.mainForm = mainForm;
        this.lordToLoad = lordToLoad;
    }

    public void run (){

        // Progress Bar visible
        InfoPanel infoPanel = mainForm.getInfoPanel();
        infoPanel.progressBar.setValue( 0 );
        infoPanel.progressBarPanel.setVisible(true);
        infoPanel.textInfo.setText("Connexion au site Daifen...");
        infoPanel.textInfo.setVisible(true);

        mainForm.currentContinent = new Continent();
        mainForm.currentContinent.myRoyaume[0] = new Royaume();

        InfoReader infoReader = new InfoReader( lordToLoad );


        infoPanel.textInfo.setText("lune num. loading...");

        mainForm.currentContinent.currentMoon = infoReader.getCurrentTour();
        mainForm.getInfoPanel().moon.setText( ""+mainForm.currentContinent.currentMoon );

        // Progress Bar evolution
        mainForm.getInfoPanel().progressBar.setValue( 5 );
        infoPanel.textInfo.setText("Seigneur loading...");

        // Lord Refresh
        lordToLoad.setRace( infoReader.getRace() );
        mainForm.currentContinent.myRoyaume[0].setLord( lordToLoad );
        mainForm.getInfoPanel().seigneurName.setText( ""+mainForm.currentContinent.myRoyaume[0].getLord().getLogin() );
        mainForm.getInfoPanel().seigneurName.setEnabled(true);
        mainForm.getInfoPanel().race.setText( "("+mainForm.currentContinent.myRoyaume[0].getLord().getRace()+")" );
        mainForm.getInfoPanel().race.setEnabled(true);

        // Progress Bar evolution
        mainForm.getInfoPanel().progressBar.setValue( 10 );
        infoPanel.textInfo.setText("Continent loading...");

        // Continent Refresh

        mainForm.currentContinent.continentName = infoReader.getContinent();
        mainForm.getInfoPanel().continentName.setText( mainForm.currentContinent.continentName );
        mainForm.getInfoPanel().continentName.setEnabled(true);

        // Progress Bar evolution
        mainForm.getInfoPanel().progressBar.setValue( 15 );
        infoPanel.textInfo.setText("Ressources loading...");

        // Ressource Refresh
        mainForm.currentContinent.myRoyaume[0].setRessource( infoReader.getRessources( mainForm.currentContinent.currentMoon ) );
        mainForm.getBilanPanel().updateRessources( mainForm.currentContinent.myRoyaume[0] );

        // Progress Bar evolution
        mainForm.getInfoPanel().progressBar.setValue( 25 );
        infoPanel.textInfo.setText("Connaissances loading...");

        // Connaissance Refresh
        mainForm.currentContinent.myRoyaume[0].setConnaissances( infoReader.getConnaissances( mainForm.currentContinent.currentMoon ) );
        mainForm.getBilanPanel().updateConnaissancesTable( mainForm.currentContinent.myRoyaume[0] );

        // Progress Bar evolution
        mainForm.getInfoPanel().progressBar.setValue( 50 );
        infoPanel.textInfo.setText("Batiments loading...");

        // Buildings refresh
        mainForm.currentContinent.myRoyaume[0].setBuildings( infoReader.getBuildingsArray( mainForm.currentContinent.currentMoon ) );
        mainForm.getBilanPanel().updateBuildingTable( mainForm.currentContinent.myRoyaume[0] );

        // Progress Bar evolution
        mainForm.getInfoPanel().progressBar.setValue( 75 );
        infoPanel.textInfo.setText("Troupes loading...");

        // Troupes Refresh
        mainForm.currentContinent.myRoyaume[0].setTroopers( infoReader.getTroopersArray( mainForm.currentContinent.currentMoon ) );
        mainForm.getBilanPanel().updateTroupesTable( mainForm.currentContinent.myRoyaume[0] );

        // Progress Bar evolution
        mainForm.getInfoPanel().progressBar.setValue( 90 );
        infoPanel.textInfo.setText("Royaumes voisins loading.");

        // Royaumes connus Refresh
        //TODO ????????????? A REMETTRE des que David ma livrer la nouvelle function
 //       mainForm.currentContinent.otherRoyaume = infoReader.getRoyaumesConnus( mainForm.currentContinent.currentMoon );
        mainForm.getBilanPanel().updateRoyaumesConnusTable( mainForm.currentContinent.otherRoyaume );
        mainForm.getGeneralPanel().updateGraphicPanel( mainForm.currentContinent );

        // Progress Bar evolution
        infoPanel.progressBar.setString(null);
        mainForm.getInfoPanel().progressBar.setValue( 100 );
        // Progress Bar Unvisible
        mainForm.getInfoPanel().progressBarPanel.setVisible(false);

        // Text Info invisible
        infoPanel.textInfo.setText("...");
        infoPanel.textInfo.setVisible(false);



    }
}
