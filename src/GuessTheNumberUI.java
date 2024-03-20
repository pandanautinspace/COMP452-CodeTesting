import java.awt.*;
import javax.swing.*;
import com.formdev.flatlaf.*;

/**
 * Creates the UI for the guess-the-number game
 *
 * NOTE: Do not edit this file other than the one lambda expression
 *       noted in the makeCardsPanel method
 */
public class GuessTheNumberUI {

    private final static Dimension CARD_DIM = new Dimension(240, 300);

    public static void main(String... args) {
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        SwingUtilities.invokeLater(() -> new GuessTheNumberUI().displayGUI());
    }

    // JFrame contains content pane, which contains JPanel (CardLayout),
    // which contains a JPanel (BoxLayout) for each screen (i.e., the cards)
    private void displayGUI() {
        JFrame frame = new JFrame("Guess the Number");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the cardsPanel to the main content pane
        JPanel cardsPanel = makeCardsPanel();
        frame.getContentPane().add(cardsPanel, BorderLayout.CENTER);

        frame.setMinimumSize(CARD_DIM);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * Creates the main card panel and fills it with the cards
     * @return the card panel
     */
    private JPanel makeCardsPanel(){
        JPanel cardsPanel = new JPanel();

        cardsPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // See https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html for CardLayout
        CardLayout cardLayout = new CardLayout();
        cardsPanel.setLayout(cardLayout);

        ///////////////////////////////
        // Create each screen (i.e., card) and add to the cardLayout

        // HOME
        JPanel homePanel = makeHomePanel(cardsPanel);
        addToCards(cardsPanel, homePanel, ScreenID.HOME.name());

        // GAME_OVER
        GameOverPanel gameOverPanel = new GameOverPanel(cardsPanel);
        addToCards(cardsPanel, gameOverPanel, ScreenID.GAME_OVER.name());

        // HUMAN_PLAY
        // TODO: your refactoring should include some changes to the lambda expression in the following line
        // HINT: Look at what GameOverPanel.setGameResults does now. Your code should do the same operations,
        //       but refactor how those are structured, which means the lambda will need to change.
        JPanel humanGuessesPanel = new HumanGuessesPanel(cardsPanel, (gameResult -> {
            gameOverPanel.setGameResults(gameResult);
            if(gameResult.humanWasPlaying){
                gameResult.writeResults();
            }
        }));
        addToCards(cardsPanel, humanGuessesPanel, ScreenID.HUMAN_PLAY.name());

        // COMPUTER_PLAY_LAUNCH
        JPanel computerPlayLaunchPanel = makeComputerGuessingLaunchPanel(cardsPanel);
        addToCards(cardsPanel, computerPlayLaunchPanel, ScreenID.COMPUTER_PLAY_LAUNCH.name());

        // COMPUTER_PLAY
        JPanel computerGuessesPanel = new ComputerGuessesPanel(cardsPanel, gameOverPanel::setGameResults);
        addToCards(cardsPanel, computerGuessesPanel, ScreenID.COMPUTER_PLAY.name());

        // STATS
        JPanel statsPanel = new StatsPanel(cardsPanel);
        addToCards(cardsPanel, statsPanel, ScreenID.STATS.name());

        return cardsPanel;
    }

    private JPanel makeHomePanel(JPanel cardsPanel){
        JPanel panel = new JPanel();

        // BoxLayout with PAGE_AXIS will create a vertical stack
        // of components; see https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JRadioButton humanPlayer = new JRadioButton("Person Guesses");
        humanPlayer.setActionCommand(ScreenID.HUMAN_PLAY.name());
        humanPlayer.setSelected(true);
        humanPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(humanPlayer);

        panel.add(Box.createRigidArea(new Dimension(0,6)));

        JRadioButton compPlayer = new JRadioButton("Computer Guesses");
        compPlayer.setActionCommand(ScreenID.COMPUTER_PLAY_LAUNCH.name());
        compPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(compPlayer);

        panel.add(Box.createRigidArea(new Dimension(0,10)));

        ButtonGroup playerSelect = new ButtonGroup();
        playerSelect.add(humanPlayer);
        playerSelect.add(compPlayer);

        JButton newGame = new JButton("New Game");
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGame.addActionListener(e -> {
            // See itemStateChanged in https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/CardLayoutDemoProject/src/layout/CardLayoutDemo.java
            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            boolean isCompPlayer = (playerSelect.getSelection().getActionCommand().equals(
                    ScreenID.COMPUTER_PLAY_LAUNCH.name()));

            if(isCompPlayer){
                cardLayout.show(cardsPanel, ScreenID.COMPUTER_PLAY_LAUNCH.name());
            }
            else{
                cardLayout.show(cardsPanel, ScreenID.HUMAN_PLAY.name());
            }
        });
        panel.add(newGame);
        panel.add(Box.createVerticalGlue());

        JButton viewStats = new JButton ("View Stats");
        viewStats.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewStats.addActionListener(e -> {
            // See itemStateChanged in https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/CardLayoutDemoProject/src/layout/CardLayoutDemo.java
            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            cardLayout.show(cardsPanel, ScreenID.STATS.name());
        });
        panel.add(viewStats);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        return panel;
    }

    private JPanel makeComputerGuessingLaunchPanel(JPanel cardsPanel){
        JPanel result = new JPanel();

        result.setLayout(new BoxLayout(result, BoxLayout.PAGE_AXIS));

        JLabel message = new JLabel("Pick a number between 1 and " + HumanGuessesGame.UPPER_BOUND);
        result.add(message);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);

        result.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton ready = new JButton("Ready!");
        ready.addActionListener(e -> {
            // See itemStateChanged in https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/CardLayoutDemoProject/src/layout/CardLayoutDemo.java
            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            cardLayout.show(cardsPanel, ScreenID.COMPUTER_PLAY.name());
        });
        result.add(ready);
        ready.setAlignmentX(Component.CENTER_ALIGNMENT);

        return result;
    }

    private void addToCards(JPanel cardsPanel, JPanel card, String name){
        card.setPreferredSize(CARD_DIM);
        card.setMinimumSize(CARD_DIM);
        cardsPanel.add(card, name);
    }
}



