import java.util.*;

public class Directory {
    
    HashMap<String, Integer> files = new HashMap<>();
    ArrayList<Directory> directories = new ArrayList<>();
    String name;
    long size = 0;

    Directory(String name){
        this.name = name;
    }

    protected void addFiles(String file, int size){
        files.put(file, size);
    }

    protected void addDirectory(String name){
        directories.add(new Directory(name));
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