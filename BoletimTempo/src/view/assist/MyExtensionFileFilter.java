package view.assist;
import java.io.File;  
import java.util.List;  
  
/** A simple windows like file filter. */  
public class MyExtensionFileFilter extends javax.swing.filechooser.FileFilter  
{  
    String[] extensions;  
    String description;  
  
    /** 
     * Creates a file filter with several extensions. 
     *  
     * @param desc The filter description. 
     * @param exts The extensions that the filter supports. 
     */  
    public MyExtensionFileFilter(String desc, String... exts)  
    {    
        extensions = new String[exts.length];  
        for (int i = exts.length - 1; i >= 0; i--)  
            extensions[i] = exts[i].toLowerCase();  
  
        // make sure we have a valid (if simplistic) description  
        description = (desc == null ? exts[0] + " files" : desc);  
    }  
  
    /** 
     * Creates a file filter with several extensions. 
     *  
     * @param descr The filter description. 
     * @param exts The extensions that the filter supports. 
     */  
    public MyExtensionFileFilter(String desc, List<String> exts)  
    {  
        this(desc, exts.toArray(new String[exts.size()]));  
    }  
  
    /** 
     * Verify if its a valid file. This method is automatically called by the 
     * <code>FileChooser</code> object. 
     *  
     * @param f The file to be verified. 
     * @return A boolean indicated if the file was accepted by the filter or 
     *         not. 
     */  
    @Override  
    public boolean accept(File f)  
    {  
        // we always allow directories, regardless of their extension  
        if (f.isDirectory())  
            return true;  
  
        // ok, it's a regular file so check the extension  
        for (String extension : extensions)  
            if (f.getName().toLowerCase().endsWith(extension))  
                return true;  
  
        return false;  
    }  
  
    /** 
     * The description of this kind of files. 
     *  
     * @return file kind description 
     */  
    @Override  
    public String getDescription()  
    {  
        return description;  
    }  
}  
