package il.ac.bgu.finalproject.server.Domain.Controllers;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
    private static Logger instance = null;
    protected MyLogger() { }
    public static Logger getInstance() {
        if(instance == null) {
            instance = Logger.getLogger("TheLogger");
            FileHandler fh;

            try {
                fh = new FileHandler("src\\main\\java\\il\\ac\\bgu\\finalproject\\server\\Domain\\Controllers\\Logger\\mylog.log");
                instance.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return instance;
    }
}
