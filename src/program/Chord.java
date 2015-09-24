package program;
import java.util.*;
import java.io.*;
import java.net.*;
import java.applet.*;

public class Chord {

  private String name = "-";
  private int id;
  private String image = Options.getBlankImage();
  private String sound = "";
  static List<Chord> chordList = new LinkedList<Chord>();

  // constructor with the necessary attributes
  public Chord(String name, String image) {
    this.name = name;
    this.image = image;
    id = chordList.size()+1;
    chordList.add(this);
  }
  // constructor with the necessary attributes including sound path.
  public Chord(String name, String image, String sound) {
    this.name = name;
    this.image = image;
    this.sound = sound;
    id = chordList.size()+1;
    chordList.add(this);
  }

  // Returns the id of the stated chord.
  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  // Returns the name of the stated chord.
  public String getName() { return name; }
  // Returns the name of the stated chord with the given id.
  public static String getNameById(int id) {
    String name = "";
    for(Chord chord : chordList){
      if(chord.getId() == id){
        name = chord.getName();
      }
    }
    return name;
  }
  // Sets the image name for the stated chord. example: "A-Dur.jpg"
  public void setName(String name) { this.name = name; }

  // Returns the complete image path of the stated chord.
  public String getImage() { return Options.getFolderImages()+image; }
  // Returns the complete image path of the stated chord with the given id.
  public static String getImageById(int id) {
    String image = "";
    for(Chord chord : chordList){
      if(chord.getId() == id){
        image = chord.getImage();
      }
    }
    return image;
  }
  public void setImage(String image) { this.image = image; }
  
  // Returns the complete sound path of the stated chord.
  public String getSound() { return Options.getFolderSounds()+sound; }
  // Returns the complete sound path of the stated chord with the given id.
  public static String getSoundById(int id) {
    String sound = "";
    for(Chord chord : chordList){
      if(chord.getId() == id){
        sound = chord.getSound();
      }
    }
    return sound;
  }
  public void setSound(String sound) { this.sound = sound; }
  
  
  // Plays the sound of the chord with the given id.
  public static void playSoundById(int id) throws MalformedURLException, InterruptedException {
    File f = new File(Chord.getSoundById(id));
    AudioClip sound = Applet.newAudioClip(f.toURI().toURL());
    sound.play();
  }

  // Returns the amount of active chord instances.
  public static int getChordCount() { return chordList.size(); }

  // Returns true if the given chord is the last instanced one.
  public boolean isLastChord(Chord chord) {
    if (chord.getId() < this.getChordCount()){
      return false;
    } else {
      return true;
    }
  }
  
}