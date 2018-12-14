package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

    /**
     * The util to clean txt file when invoking it
     * @param path the absolute path of the file to be cleaned
     */
    public static void cleanFile(String path){
        try{
            File _file = new File(path);
            if(!_file.exists()){
                _file.createNewFile();
            }

            FileWriter writer = new FileWriter(_file);
            String _clean = "";
            writer.write(_clean);
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}