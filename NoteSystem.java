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
    
    // Generate report of notes in order of most recent
    public void report7() {
        System.out.println("Notes in chronological order:");
        ArrayList<Note> notesSorted = new ArrayList<Note>();
        for (String noteName: notes.keySet()) {
            notesSorted.add(notes.get(noteName));
        }
        Collections.sort(notesSorted, new Comparator<Note>() {
            @Override
            public int compare(Note n1, Note n2) {
                return (int)(n1.getTime() - n2.getTime());
            }
        });
        for (Note note: notesSorted) {
            String noteName = note.getName();
            Date date = new Date(note.getTime());
            System.out.println(noteName + "\t\t" + date);
        }
    }
    
    // Generate report of notes in order of length
    public void report8() {
        System.out.println("Notes in order of length:");
        ArrayList<Note> notesSorted = new ArrayList<Note>();
        for (String noteName: notes.keySet()) {
            notesSorted.add(notes.get(noteName));
        }
        Collections.sort(notesSorted, new Comparator<Note>() {
            @Override
            public int compare(Note n1, Note n2) {
                return (int)(n1.getLength() - n2.getLength());
            }
        });
        for (Note note: notesSorted) {
            String noteName = note.getName();
            String length = note.getLength() + " characters";
            System.out.println(noteName + "\t\t" + length);
        }
    }
    
    public void report9() { //Organized by favorites
        int countTrue = 0;
        int countFalse = 1;
        int sizeOriginal = 0;
        System.out.println("Notes in order of favorites: ");
        ArrayList<Note> notesFavorited = new ArrayList<Note>();//Unsorted array of all notes
        for (String noteName: notes.keySet()) { //Loop to add all notes from hash map to an array
            notesFavorited.add(notes.get(noteName));
        }
        
        sizeOriginal = notesFavorited.size();//Size of unsorted array
        Note sortedNotes[] = new Note[sizeOriginal];//Sorted array intialized
        
        for(int i = 0; i < sizeOriginal; i++) { //Loop to sort all the notes based on favorite
            if(notesFavorited.get(i).getFavorite() == true){//If note is true(favorited), add to beginning of sorted array
                sortedNotes[countTrue] = notesFavorited.get(i);
                countTrue++;
            }
            else { //If note is false(unfavorited), add to end of sorted array
                sortedNotes[sizeOriginal - countFalse] = notesFavorited.get(i);
                countFalse++;
            }
        }
        
        //Loop to print out report sorted by favorites
        for(int i = 0; i < sizeOriginal; i++){
            String noteName;
            noteName = sortedNotes[i].getName();
            if (sortedNotes[i].getFavorite() == true) {
                System.out.println(noteName + ": Favorited");
            }
            else {
                System.out.println(noteName);
            }
        }
    }
    
    public void report0() { //Type in the note you want favorited
        System.out.println("Enter the note you would like to favorite: ");
        Scanner input = new Scanner(System.in); 
        String favoriteAction = input.nextLine();
        
        if(notes.containsKey(favoriteAction)){
            Note starredNote = notes.get(favoriteAction);//Gets the note from the hash map
            
            if (starredNote.getFavorite() == false) { //If nots favorited yet, continue
            starredNote.setFavorite(starredNote);
            System.out.println(starredNote.getName() + " has been favorited!");
            }
            
            else {//If favorited already, continue
            starredNote.setFavorite(starredNote);
            System.out.println(starredNote.getName() + " has been unfavorited!");
            }
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
        System.out.println("7. Report of all notes in chronological order");
        System.out.println("8. Report of all notes in order of length");
        System.out.println("9. Report of all notes in order of favorites");
        System.out.println("0. Type the name of the note you'd like to favorite or unfavorite");
        System.out.println("11. Exit");
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
                notes.report7();
            } else if (input.equals("8")) {
                notes.report8();
            } else if (input.equals("9")) {
                notes.report9();
            } else if (input.equals("0")) {
                notes.report0();
            } else if (input.equals("11")) {
                return;
            } else {
                System.out.println("Error: Invalid input");
            }
        }
    }
    
    
} 