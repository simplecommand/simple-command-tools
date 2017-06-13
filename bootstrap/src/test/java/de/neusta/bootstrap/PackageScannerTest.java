package de.neusta.bootstrap;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

import de.neusta.bootstrap.PackageScanner;

public class PackageScannerTest {

    @Test
    public void testScanPackage() throws Exception {

        PackageScanner packageScanner = new PackageScanner();
        
        Set<Class<?>> scannedClasses = packageScanner.getSubTypesOf("bootstrap", Object.class);

        assertThat(scannedClasses.size(), is(2));
        
        for (Class<?> classEntry : scannedClasses) {
            System.out.println(classEntry.getName());
        }
        
    }
    
}
