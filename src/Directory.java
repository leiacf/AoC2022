import java.util.*;

public class Directory {
    
    HashMap<String, Integer> files = new HashMap<>();
    ArrayList<Directory> directories = new ArrayList<>();
    String name;
    long size = 0;
    Directory parent = null;

    Directory(String name, Directory parent){
        this.name = name;
        this.parent = parent;
    }

    protected void addFile(String file, int size){
        files.put(file, size);
    }

    protected void addChild(String name){
        directories.add(new Directory(name, this));
    }

    protected boolean hasChild(String search){
        
        for (Directory directory : directories) {
            if (directory.getName().equals(search)){
                return true;
            }
        }
        
        return false;
    }

    protected boolean hasFile(String name){
        
        if (files.containsKey(name)){ 
            return true;
        }
        
        return false;
    }

    protected Directory getParent(){
        return parent;
    }

    protected Directory getChild(String search){

        for (Directory directory : directories) {
            if (directory.getName().equals(search)){
                return directory;
            }
        }

        return null;
    }

    protected ArrayList<Directory> getChildren(){
        return directories;
    }

    protected String getName(){
        return name;
    }

    protected long getSize(){

        if (size == 0){
            
            for(Map.Entry<String, Integer> entry : files.entrySet()) {

                int value = entry.getValue();
                size += value;

            }

            for (Directory directory : directories) {
                
                size+= directory.getSize();

            }

        }

        return size;

    }

}