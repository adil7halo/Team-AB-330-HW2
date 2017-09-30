import java.io.*;
import java.util.*;

/*
 *  NoteSystem Class
 */
public class NoteSystem {
 
  HashMap<String,Note> notes = new HashMap<String,Note>();
  
  // Constructor
  public NoteSystem(String directory) {
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
  
  // Generate report of notes containing one or more mentions
  public void report1() {
    System.out.println("Notes containing one or more mentions:");
    for (String noteName: notes.keySet()) {
      Note note = notes.get(noteName);
      if (note.hasMentions()) {
        System.out.println(noteName);
      }
    }
  }
  
  // Generate report of notes organized by mentions
  public void report2() {
    System.out.println("Notes organized by mentions:");
    for (String noteName: notes.keySet()) {
      Note note = notes.get(noteName);
      if (note.hasMentions()) {
        note.displayMentions();
      }
    }
  }
  
  // Generate report of notes containing one or more keywords
  public void report3() {
    System.out.println("Notes containing one or more keywords:");
    for (String noteName: notes.keySet()) {
      Note note = notes.get(noteName);
      if (note.hasKeywords()) {
        System.out.println(noteName);
      }
    }
  }
  
  // Generate report of notes organized by keywords
  public void report4() {
    System.out.println("Notes organized by keywords:");
    for (String noteName: notes.keySet()) {
      Note note = notes.get(noteName);
      if (note.hasKeywords()) {
        note.displayKeywords();
      }
    }
  }
  
  // Generate report of notes containing keyword/mention
  public void report5(String term) {
    System.out.println("Notes containing keyword/mention "+term+":");
    for (String noteName: notes.keySet()) {
      Note note = notes.get(noteName);
      if (note.search(term)) {
        System.out.println(noteName);
      }
    }
  }
  
  // Generate report of notes in topological order
  public void report6() {
    System.out.println("Notes in topological order:");
    for (String noteName: notes.keySet()) {
      System.out.println(noteName);
    }
  }
  
  // Display menu
  public static void displayMenu() {
    System.out.println("Generate report:");
    System.out.println("1. Report of all notes containing one or more mentions");
    System.out.println("2. Report of all notes, organized by mention");
    System.out.println("3. Report of all notes containing one or more keywords");
    System.out.println("4. Report of all notes, organized by keyword");
    System.out.println("5. Report of all notes by selected mention/keyword");
    System.out.println("6. Report of all notes in topological order");
    System.out.println("7. Exit");
  }
  
  // Main method for execution
  public static void main(String[] args) {
    NoteSystem notes = new NoteSystem(".");
    Scanner s = new Scanner(System.in);
    while (true) {
      displayMenu();
      System.out.print("Choice: ");
      String input = s.nextLine();
      if (input.equals("1")) {
        notes.report1();
      } else if (input.equals("2")) {
        notes.report2();
      } else if (input.equals("3")) {
        notes.report3();
      } else if (input.equals("4")) {
        notes.report4();
      } else if (input.equals("5")) {
        System.out.print("Enter mention/keyword to search: ");
        String term = s.nextLine();
        notes.report5(term);
      } else if (input.equals("6")) {
        notes.report6();
      } else if (input.equals("7")) {
        return;
      } else {
        System.out.println("Error: Invalid input.");
      }
    }
  }
  
  
}

