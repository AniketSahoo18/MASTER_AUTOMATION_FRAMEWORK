package coreUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipFile;

public class ZipUtil {

    private ZipUtil(){}

    public static String readFileInsideZipWithoutExtracting(String pathToZipFile, String fileNameWithExtension) {

        StringBuilder outputData = new StringBuilder();
        String line;
        try(ZipFile zipFile = new ZipFile(pathToZipFile);
            InputStream is = zipFile.getInputStream(zipFile.getEntry(fileNameWithExtension));
            BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            while((line = reader.readLine()) != null) {
                outputData.append(line);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(String.format("IOException occured while loading the file '%s'", pathToZipFile), e);
        }

        return outputData.toString();
    }
}
