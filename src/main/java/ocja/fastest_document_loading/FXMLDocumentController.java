/*
 * Need to install javafx on machine to build this
 *
 */
package ocja.fastest_document_loading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Simple application to learn the performance of different streams by reading a
 * large file; TODO - implement second thread that counts time, create a
 * callback that notifies that thread that counting characters is done TODO and
 * the third thread that actually makes file read so that the GUI thread is free
 *
 * @author DOM
 */
public class FXMLDocumentController {

    String filePath = "big.txt";

    @FXML
    private TextArea textArea;

    //choose file to load
    @FXML
    private void pickFile() {
        FileChooser chooser = new FileChooser();
        chooser.setSelectedExtensionFilter(new ExtensionFilter("Text file", "*.txt"));
        File file = chooser.showOpenDialog(JavaFXThreadIOApp.getMainStage());
        if (file != null) {
            filePath = file.toString();
        }
    }

    //114k chars read in 7 seconds
    @FXML
    private void loadBytesSB() {
        if (this.filePath == null) {
            return;
        }

        StringBuilder text = new StringBuilder();
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(filePath);
            byte b = -1;
            while ((b = (byte) fi.read()) != -1) {
                text.append((char) b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        textArea.setText(text.toString());
    }

    //114k chars read in 14 seconds
    @FXML
    private void loadCharsSB() {
        if (this.filePath == null) {
            return;
        }

        StringBuilder text = new StringBuilder();
        //System.out.println(text.length());

        FileReader fr = null;
        try {
            fr = new FileReader(filePath);
            int i = -1;

            //System.out.println(text.length());
            while ((i = fr.read()) != -1) {
                //System.out.println(text.length());
                text.append((char) i);
                //System.out.println(text.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        textArea.setText(text.toString());
    }

    //115k chars in 14 seconds
    @FXML
    private void loadCharsBufferedReaderSB() {
        if (this.filePath == null) {
            return;
        }

        StringBuilder text = new StringBuilder();
        //System.out.println(text.length());

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            int i = -1;

            //System.out.println(text.length());
            while ((i = br.read()) != -1) {
                //System.out.println(text.length());
                text.append((char) i);
                //System.out.println(text.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        textArea.setText(text.toString());
    }

    //114k characters in less than one second
    @FXML
    private void loadLinesScannerSB() {
        if (this.filePath == null) {
            return;
        }

        StringBuilder text = new StringBuilder();
        BufferedReader br = null;

        long start = 0;

        try {
            br = new BufferedReader(new FileReader(filePath));
            Scanner scan = new Scanner(br);

            start = System.currentTimeMillis();

            while (scan.hasNext()) {
                text.append(scan.nextLine() + "\n");
//                if (text.length() > 114000) {
//                    System.exit(0);
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis() - start);

        textArea.setText(text.toString());
    }

    //114k characters in less than one second
    /**
     * Reads text from file in separate thread, then uses FXApplicationThread
     * to display loaded text in TextArea.
     * buffer - BufferedReader stored as - char[] using read method
     * in CharBuffer CharBuffer size - length of the file
     * 
     */
    @FXML
    private void loadWholeBuffReadSB() {
        if (this.filePath == null) {
            return;
        }
        
        Task<String> tsk = new Task<String>() {
            String loadedText = "";

            @Override
            protected String call() throws Exception {
                File f = new File(filePath);
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                char[] cBuff = new char[(int) f.length()];

                fr.read(cBuff);
                loadedText = new String(cBuff).substring(0, cBuff.length / 32);

                return loadedText;
            }

            
            
            //runs on FXApplicationThread when task successfully resolves
            @Override
            protected void succeeded() {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        if (loadedText != null) {
                            textArea.setText(loadedText);
                        }
                    }
                });
            }
        };

        Thread tr = new Thread(tsk);
        tr.start();

    }
}
