package br.com.github.apihemofilia.apihemofilia.log;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author macgarcia
 */
public class FactoryLog {

    private static Logger logger = buildLog();

    private static final String DIR_NAME = "log";
    private static final String LOG_FILE = "arq_log.log";

    private static Logger buildLog() {
        montarDiretorio();
        try {
            logger = Logger.getLogger(FactoryLog.class.getName());
            FileHandler fh = new FileHandler(DIR_NAME + File.separator + LOG_FILE, true);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            fh.setEncoding(StandardCharsets.UTF_8.name());
            logger.addHandler(fh);
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(FactoryLog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logger;
    }

    private static void montarDiretorio() {
        File file = new File(DIR_NAME);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static Logger getLog() {
        return logger;
    }

}
