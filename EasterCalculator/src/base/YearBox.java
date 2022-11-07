package base;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class YearBox extends JComboBox<String> {

    public YearBox(int x, int y, int width, int height) {
        for (byte i = 0; i <= 50; i++) {
            addItem("" + (2000 + i));
        }
        setBounds(x, y, width, height);
        setFont(new Font("Arial", Font.BOLD, 50));
        setAlignmentX(SwingConstants.CENTER);
    }
}