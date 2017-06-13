package de.neusta.bootstrap.scanner;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import de.mwolff.command.chainbuilder.XMLChainBuilder;
import de.neusta.bootstrap.scanner.PackageScanner;

public class PackageScannerTest {

    private static final Logger LOG = Logger.getLogger(PackageScanner.class);

    @Test
    public void testScanPackage() throws Exception {

        PackageScanner packageScanner = new PackageScanner();
        
        Set<Class<?>> scannedClasses = packageScanner.getSubTypesOf("de.neusta.bootstrap.scanner", Object.class);

        assertThat(scannedClasses.size(), is(2));
        
        for (Class<?> classEntry : scannedClasses) {
            LOG.info(classEntry.getName());
        }
        
    }
    
}
