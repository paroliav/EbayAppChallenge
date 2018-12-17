package au.com.wipro.ebay.utils;

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {

    InputStream is;
    JavaPropsMapper mapper = new JavaPropsMapper();

    private Properties load(String fileName) throws IOException {
        is = ClassLoader.getSystemResourceAsStream(fileName);
        Properties props = new Properties();
        props.load(is);
        return props;
    }

    public <T> T getProperties(String fileName, Class<T> clazz) {
        try {
            return mapper.readPropertiesAs(load(fileName),clazz);
        } catch (IOException e) {
            System.out.println("Properties file :"+fileName+" was not found in classpath");
            e.printStackTrace();
        }
        return null;
    }

}
