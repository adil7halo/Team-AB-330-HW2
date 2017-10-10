import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sarah
 */
class startGUI {
    
      HashMap<String,Note> notes = new HashMap<String,Note>();

private JFrame mainFrame;
private JLabel headerLabel;
private JLabel statusLabel;
private JPanel controlPanel;
public void startGUI(){
    //*Lays out the FRAME part of the window
    mainFrame = new JFrame("Note Parser");
    mainFrame.setSize(500,500);
    mainFrame.setLayout(new GridLayout(3, 1));
    mainFrame.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
    headerLabel = new JLabel("", JLabel.CENTER);        
    statusLabel = new JLabel("",JLabel.CENTER);    
    statusLabel.setSize(350,100);

    controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
}
public void openDirectoryGUI(){
    String directory ="";
    System.out.println("Loading notes from directory: " + directory);
    File folder = new File(directory);
    File[] listOfFiles = folder.listFiles();
    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(".txt")) {
        Note note = new Note(listOfFiles[i]);
        notes.put(note.getName(),note);
      }
    }
} 
    void buttonsGUI() {
    //*    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    headerLabel.setText("Which report would you like to create?");
    //*I'd like to add an image here*//
    JButton oneButton = new JButton("Contains Mentions");
    JButton twoButton = new JButton("Organized by Mentions");  
    JButton threeButton = new JButton("Contains Keywords");  
    JButton fourButton = new JButton("Organized by Keywords");  
    JButton fiveButton = new JButton("Contains specific Word/Mention");  
    JButton sixButton = new JButton("In Topological Order");  
    
    oneButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             //*Insert the actions here
             System.out.println("Notes containing one or more mentions:");
                for (String noteName: notes.keySet()) {
                     Note note = notes.get(noteName);
                if (note.hasMentions()) {
                     System.out.println(noteName);
                 }
             }
            statusLabel.setText("PRINTED MENTIONS A");
         }          
      });
    twoButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             //*Insert the actions here
                 System.out.println("Notes organized by mentions:");
                for (String noteName: notes.keySet()) {
                    Note note = notes.get(noteName);
                    if (note.hasMentions()) {
                    note.displayMentions();
                }
            }
            statusLabel.setText("ORG'D MENT'S.");
         }          
      });
    threeButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             //*Insert the actions here
                 System.out.println("Notes containing one or more keywords:");
                 for (String noteName: notes.keySet()) {
                    Note note = notes.get(noteName);
                    if (note.hasKeywords()) {
                         System.out.println(noteName);
                    }
                }
            statusLabel.setText("KEYWORDS PRINTED.");
         }          
      });
    fourButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             //*Insert the actions here
                 System.out.println("Notes organized by keywords:");
                 for (String noteName: notes.keySet()) {
                     Note note = notes.get(noteName);
                     if (note.hasKeywords()) {
                        note.displayKeywords();
                    }
                }

            statusLabel.setText("ORG'D KWORDS.");
         }          
      });
    fiveButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             //*Insert the actions here
                 String term = "";
                 System.out.println("Notes containing keyword/mention "+term+":");
                 for (String noteName: notes.keySet()) {
                    Note note = notes.get(noteName);
                    if (note.search(term)) {
                        System.out.println(noteName);
                     }
                }
            statusLabel.setText("SEARCH PRINTED.");
         }          
      });
    sixButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             //*Insert the actions here
             System.out.println("Notes in topological order:");
             for (String noteName: notes.keySet()) {
                System.out.println(noteName);
            }
            statusLabel.setText("SORTED TOPOLOGICALLY.");
         }          
        });
        
controlPanel.add(oneButton);
controlPanel.add(twoButton);
controlPanel.add(threeButton);
controlPanel.add(fourButton);
controlPanel.add(fiveButton);
controlPanel.add(sixButton);

mainFrame.setVisible(true);

    }
    
}