import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class OpenFile {
    private String extension;
    private boolean fileIsLoaded;

    public String getExtension() {
        return extension;
    }

    public File getFile() {
        extension = null;

        JFileChooser fileChooser = new JFileChooser();
        boolean isXlsx = false;
        File file = null;

        //Při výběru jiného formátu než je excel formát, bude požadovat nový soubor
        while (!isXlsx) {

            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Microsoft excel files", "xlsx", "xls");
            fileChooser.setFileFilter(filter);

            //výběr souboru
            int response = fileChooser.showOpenDialog(null);

            //Při kliknutí na "cancel" se dialog vypne
            if (response == JFileChooser.CANCEL_OPTION) {
                //Při vypnutí okna bez výběru souboru se vrátí prázdný soubor a nic se nevypíše
                return null;
            }

            //Ověření zda byl soubor úspěšně vybrán
            if (response == JFileChooser.APPROVE_OPTION) {
                // Získání cesty k souboru
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                //Ověřuje, zda byl otevřen excel soubor
                if (checkXlxs(file.getAbsolutePath())) {
                    isXlsx = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Please choose excel file");
                }
            }
        }
        return file;
    }

    //Kontrola zda se jedná o .xlsx, nebo . xls soubor
    private boolean checkXlxs(String path) {
        int counter = 0;
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '.') {
                counter++;
            }
        }
        if (counter == 1) {
            String extension = null;
            int i = path.lastIndexOf('.');
            if (i > 0) {
                extension = path.substring(i);
            }

            assert extension != null;
            if (extension.toLowerCase().matches(".xlsx|.xls")) {
                this.extension = extension;
                return true;
            }
        }
        return false;
    }
}
