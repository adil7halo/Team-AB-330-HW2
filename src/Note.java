import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

/*
 *  Note class
 */
public class Note {
 
  private String name;
  private String path;
  private File file;
  private ArrayList<String> mentions;
  private ArrayList<String> keywords;
  private String identifier;
  private ArrayList<String> links;
  private ArrayList<String> urls;
  
  // Constructor
  public Note(File file) {
    mentions = new ArrayList<String>();
    keywords = new ArrayList<String>();
    identifier = null;
    links = new ArrayList<String>();
    urls = new ArrayList<String>();
    this.name = file.getName();
    this.path = file.getPath();
    this.file = file;
    parse();
  }
  
  // Method to parse Note file
  private void parse() {
    Pattern mentionPattern = Pattern.compile("@\\w+");
    Pattern keywordPattern = Pattern.compile("#\\w+");
    Pattern topicPattern = Pattern.compile("!\\w+");
    Pattern linkPattern = Pattern.compile("^\\w+");
    Pattern urlPattern =  Pattern.compile(
        "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    try {
      List<String> lines = Files.readAllLines(Paths.get(path),StandardCharsets.UTF_8);
      for (String line: lines) {
        Matcher matcher = mentionPattern.matcher(line);
        while (matcher.find())
        {
          mentions.add(matcher.group().substring(1));
        }
        matcher = keywordPattern.matcher(line);
        while (matcher.find())
        {
          keywords.add(matcher.group().substring(1));
        }
        if (identifier == null) {
          matcher = topicPattern.matcher(line);
          while (matcher.find())
          {
            identifier = matcher.group().substring(1);
            break;
          }
        }
        matcher = linkPattern.matcher(line);
        while (matcher.find())
        {
          links.add(matcher.group().substring(1));
        }
        matcher = urlPattern.matcher(line);
        while (matcher.find())
        {
          urls.add(matcher.group());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  // Get name of Note
  public String getName() {
    return name;
  }
  
  // Determine if Note has mentions
  public boolean hasMentions() {
    return mentions.size() > 0;
  }
  
  // Display Note's mentions
  public void displayMentions() {
    System.out.println("Note: " + name);
    if (!hasMentions()) {
      System.out.println("Mentions: None");
    } else {
      String result = "Mentions: ";
      for (String mention: mentions) {
        result += mention + " ";
      }
      System.out.println(result);
    }
  }
  
  // Determine if Note has keywords
  public boolean hasKeywords() {
    return keywords.size() > 0;
  }
  
  // Display keywords of Note
  public void displayKeywords() {
    System.out.println("Note: " + name);
    if (!hasKeywords()) {
      System.out.println("Keywords: None");
    } else {
      String result = "Keywords: ";
      for (String keyword: keywords) {
        result += keyword + " ";
      }
      System.out.println(result);
    }
  }
  
  // Search Note for keyword/mention term
  public boolean search(String term) {
    for (String mention: mentions) {
      if (mention.contains(term)) {
        return true;
      }
    }
    for (String keyword: keywords) {
      if (keyword.contains(term)) {
        return true;
      }
    }
    return false;
  }
}