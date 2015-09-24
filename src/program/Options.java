package program;
public class Options {

  static String folderImages = "src/images/";
  static String folderSounds = "src/sounds/";
  static String folderSystem = "src/system/";
  
  static int columnCountStandard = 1;
  static boolean previewActive = false;

  // get+set methods
  public static String getFolderImages() { return folderImages; }
  public static void setFolderImages(String folder) { folderImages = folder; }

  public static String getFolderSounds() { return folderSounds; }
  public static void setFolderSounds(String folder) { folderSounds = folder; }
  
  public static String getFolderSystem() { return folderSystem; }
  public static void setFolderSystem(String folder) { folderSystem = folder; }

  public static boolean getPreviewActive() { return previewActive; }
  public static void setPreviewActive(boolean trueOrFalse) { previewActive = trueOrFalse; }
  
  public static int getColumnCountStandard() { return columnCountStandard; }
  public static void setColumnCountStandard(int newStandard) { columnCountStandard = newStandard; }
  

  public static String getBlankImage() { return (Options.getFolderSystem()+"blank.jpg"); }

}