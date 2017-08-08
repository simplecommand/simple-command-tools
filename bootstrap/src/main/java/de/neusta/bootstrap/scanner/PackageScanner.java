package de.neusta.bootstrap.scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Scans a certain package and returns all classes that belongs in this package
 * (test classes as well).
 * 
 * Uses org.reflections library to scan.
 * 
 * @author mwolff
 *
 */
public class PackageScanner {
    
    private static final Logger LOG = Logger.getLogger(PackageScanner.class);

    /**
     * Gets all classes in a package.
     * 
     * @param packagePath
     *            Full qualified package path e.g. de.neusta.bootstrap.test
     * @return All classes in the selected path
     */
    public List<String> getSubTypesOf(String packagePath) {
        
        
        List<String> resultList = new ArrayList<>();
        LOG.debug("Package path: " + packagePath ) ;
        
        String workPath = packagePath;
        workPath = workPath.replace(".", "/");
        workPath = "src/main/java/" + workPath;
                
        File f = new File(workPath);
        File[] fileArray = f.listFiles();
        
        for (File file : fileArray) {
            String filename = file.getName();
            filename = filename.replace(".java", "");
            filename = packagePath + "." + filename;
            LOG.debug("Scanned file: " + filename);
            resultList.add(filename);
        }
        
        return resultList;
    }

}
