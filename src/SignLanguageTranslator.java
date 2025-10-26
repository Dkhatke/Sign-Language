import java.awt.*;
import java.io.File;
import java.util.*;
import javax.swing.*;

public class SignLanguageTranslator {

    // HashMap for fast letter → image mapping
    static HashMap<Character, String> signMap = new HashMap<>();

    public static void main(String[] args) {
        // Step 1: Load all alphabet-image mappings
        for (char c = 'A'; c <= 'Z'; c++) {
           signMap.put(c, "../images/" + c + ".jpeg");
        }
        // Step 2: Load number-image mappings (1–10)
        for (int n = 1; n <= 10; n++) {
         signMap.put(Character.forDigit(n % 10, 10), "../images/" + n + ".jpeg");
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String word = sc.nextLine().toUpperCase();

        // Step 2: Show sign image for each character
        // for (char ch : word.toCharArray()) {
        //     if (signMap.containsKey(ch)) {
        //         String imagePath = signMap.get(ch);
        //         File f = new File(imagePath);
        //         if (f.exists()) {
        //             displayImage(imagePath, ch);
        //         } else {
        //             System.out.println("Image not found for " + ch + " → " + imagePath);
        //         }
        //     } else {
        //         System.out.println("Invalid character: " + ch);
        //     }
        // }
        // Step 2: Show sign image for each character or number
        for (int i = 0; i < word.length(); i++) {
         char ch = word.charAt(i);

    // ✅ Special handling for "10"
        if (i < word.length() - 1 && word.substring(i, i + 2).equals("10")) {
        String imagePath = "../images/10.jpeg";
        File f = new File(imagePath);
        if (f.exists()) {
            displayImage(imagePath, '1'); // label popup as '10'
        } else {
            System.out.println("Image not found for 10 → " + imagePath);
        }
        i++; // skip next character ('0')
        continue;
    }

    // ✅ Normal handling for A–Z and 0–9
    if (signMap.containsKey(ch)) {
        String imagePath = signMap.get(ch);
        File f = new File(imagePath);
        if (f.exists()) {
            displayImage(imagePath, ch);
        } else {
            System.out.println("Image not found for " + ch + " → " + imagePath);
        }
    } else {
        System.out.println("Invalid character: " + ch);
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
        Image image = icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(image));

        frame.add(label);
        frame.pack();
        frame.setVisible(true);

        // Keep the image for 1.2 second
        try { Thread.sleep(1200); } catch (Exception e) {}
        frame.dispose();
    }

    // Step 4: Show all alphabet mappings in sorted order using TreeMap
    static void showSortedDictionary() {
    TreeMap<Character, String> sortedMap = new TreeMap<>(signMap);
    System.out.println("\n Sorted Sign Dictionary View:");

    for (Map.Entry<Character, String> entry : sortedMap.entrySet()) {
        char letter = entry.getKey();
        String path = entry.getValue();
        File f = new File(path);
        if (f.exists()) {
            displayImage(path, letter); // Display image popup
        } else {
            System.out.println("Image not found for " + letter + " → " + path);
        }
    }
}

}
