import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.File;

public class SignLanguageTranslator {

    // HashMap for fast letter → image mapping
    static HashMap<Character, String> signMap = new HashMap<>();

    public static void main(String[] args) {
        // Step 1: Load all alphabet-image mappings
        for (char c = 'A'; c <= 'Z'; c++) {
            signMap.put(c, "images/" + c + ".jpeg");
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String word = sc.nextLine().toUpperCase();

        // Step 2: Show sign image for each character
        for (char ch : word.toCharArray()) {
            if (signMap.containsKey(ch)) {
                String imagePath = signMap.get(ch);
                File f = new File(imagePath);
                if (f.exists()) {
                    displayImage(imagePath, ch);
                } else {
                    System.out.println("❌ Image not found for " + ch + " → " + imagePath);
                }
            } else {
                System.out.println("⚠️ Invalid character: " + ch);
            }
        }

        // Optional feature: show full dictionary in sorted order
        System.out.print("\nDo you want to view all alphabets in sorted order? (y/n): ");
        String ans = sc.nextLine();
        if (ans.equalsIgnoreCase("y")) {
            showSortedDictionary();
        }

        sc.close();
    }

    // Step 3: Function to display a single image in a popup
    static void displayImage(String path, char letter) {
        JFrame frame = new JFrame("Sign for " + letter);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(image));

        frame.add(label);
        frame.pack();
        frame.setVisible(true);

        // Keep the image for 1 second
        try { Thread.sleep(1000); } catch (Exception e) {}
        frame.dispose();
    }

    // Step 4: Show all alphabet mappings in sorted order using TreeMap
    static void showSortedDictionary() {
        TreeMap<Character, String> sortedMap = new TreeMap<>(signMap);
        System.out.println("\n📘 Sorted Sign Dictionary View:");
        for (Map.Entry<Character, String> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }
}
