package de.neusta.bootstrap.scanner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

public class PackageScannerTest {

    private static final Logger LOG = Logger.getLogger(PackageScannerTest.class);

    @Test
    public void testScanPackage() throws Exception {

        PackageScanner packageScanner = new PackageScanner();
        List<String> scannedClasses = packageScanner.getSubTypesOf("de.neusta.bootstrap.test");
        assertThat(scannedClasses.size(), is(2));
    }
    
}
