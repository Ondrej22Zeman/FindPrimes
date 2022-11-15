import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//GUI
public class Form extends JFrame {
    private JButton chooseExcelFileButton;
    private JTextArea textArea;
    private JPanel mainPanel;
    private JScrollPane scroll;

    public Form() {
        super("FindPrimes");

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();

        OpenFile openFile = new OpenFile();
        GetData getData = new GetData();
        chooseExcelFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");

                ArrayList<Integer> primes = new ArrayList<Integer>();

                try {
                    primes = getData.processData(openFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                //První prvek listu
                int zeroIndex = primes.get(0);

                switch (zeroIndex){
                    case -2 -> JOptionPane.showMessageDialog(null, "Couldnt find any prime numbers");
                    case -1 -> JOptionPane.showMessageDialog(null, "Couldnt find \"Data\" column");
                    case  0 -> {}
                    default -> {
                        for (int prime :
                                primes) {
                            textArea.append(Integer.toString(prime));
                            textArea.append("\n");
                        }
                    }
                }
            }
        });
    }
}
