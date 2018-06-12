package app.store.util;

import org.bson.conversions.Bson;

import java.io.File;

public class General {

    public static String parseString(Object s) {
        try {
            return TextHelper.toStandardPersian(s.toString());
        } catch (Exception var2) {
            return "";
        }
    }
    public static String addSeperator(String path) {
        path = parseString(path);
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }

        return path;
    }
}
