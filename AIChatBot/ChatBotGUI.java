import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatBotGUI extends JFrame implements ActionListener {

    JTextArea chatArea;
    JTextField messageField;

    JButton sendButton, clearButton;

    public ChatBotGUI() {

        setTitle("AI ChatBot");
        setSize(700,600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(220, 235, 255));

        JLabel title = new JLabel("AI CHATBOT");
        title.setBounds(220, 20, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(0, 51, 153));
        add(title);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBounds(30, 70, 520, 300);
        add(scrollPane);

        messageField = new JTextField();
        messageField.setBounds(30, 390, 360, 35);
        messageField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(messageField);
        messageField.addActionListener(this);

        sendButton = new JButton("Send");
        sendButton.setBounds(410, 390, 140, 35);
        sendButton.setBackground(new Color(0, 102, 204));
        sendButton.setForeground(Color.WHITE);
        sendButton.addActionListener(this);
        add(sendButton);

        clearButton = new JButton("Clear Chat");
        clearButton.setBounds(200, 445, 160, 35);
        clearButton.setBackground(new Color(220, 53, 69));
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(this);
        add(clearButton);

        chatArea.append("Bot : Hello! I am your AI ChatBot.\n");
        chatArea.append("Bot : How can I help you?\n\n");

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == sendButton || e.getSource() == messageField) {

            String message = messageField.getText().trim();

            if (message.equals("")) {
                return;
            }

            chatArea.append("You : " + message + "\n");

            String msg = message.toLowerCase();

            if (msg.contains("hello") || msg.contains("hi")) {
                chatArea.append("Bot : Hello! Nice to meet you.\n\n");
            }
            else if (msg.contains("how are you")) {
                chatArea.append("Bot : I am fine. Thank you!\n\n");
            }
            else if (msg.contains("your name")) {
                chatArea.append("Bot : My name is AI ChatBot.\n\n");
            }
            else if (msg.contains("who created you")) {
                chatArea.append("Bot : I was created using Java Swing.\n\n");
            }
            else if (msg.contains("thank")) {
                chatArea.append("Bot : You're welcome!\n\n");
            }
            else if (msg.contains("bye")) {
                chatArea.append("Bot : Goodbye! Have a nice day.\n\n");
            }
            else if (msg.contains("time")) {
                java.time.LocalTime time = java.time.LocalTime.now();
                chatArea.append("Bot : Current Time : " + time.withNano(0) + "\n\n");
            }
            else if (msg.contains("date")) {
                java.time.LocalDate date = java.time.LocalDate.now();
                chatArea.append("Bot : Today's Date : " + date + "\n\n");
            }
            else if (msg.contains("good morning")) {
                chatArea.append("Bot : Good Morning! Have a great day!\n\n");
            }
            else if (msg.contains("good afternoon")) {
            chatArea.append("Bot : Good Afternoon!\n\n");
            }
            else if (msg.contains("good evening")) {
            chatArea.append("Bot : Good Evening!\n\n");
            }
            else if (msg.contains("good night")) {
            chatArea.append("Bot : Good Night! Sweet Dreams.\n\n");
            }
            else if (msg.contains("help")) {
            chatArea.append("Bot : I can answer greetings, date, time, and simple questions.\n\n");
            }
            else if (msg.contains("java")) {
            chatArea.append("Bot : Java is a powerful object-oriented programming language.\n\n");
            }
            else if (msg.contains("python")) {
           chatArea.append("Bot : Python is simple and popular for AI and Data Science.\n\n");
           }
           else if (msg.contains("codealpha")) {
           chatArea.append("Bot : CodeAlpha provides internship opportunities with practical projects.\n\n");
           }
           else if (msg.contains("college")) {
           chatArea.append("Bot : Study regularly and practice programming every day.\n\n");
           }
           else if (msg.contains("thanks")) {
           chatArea.append("Bot : Happy to help!\n\n");
           }
            else if (msg.contains("hyy")) {
           chatArea.append("Bot : heyy \n\n");
           }
            else {
                chatArea.append("Bot : Sorry, I didn't understand your question.\n\n");
            }

            messageField.setText("");
            chatArea.setCaretPosition(chatArea.getDocument().getLength());
        }

        else if (e.getSource() == clearButton) {

            chatArea.setText("");

            chatArea.append("Bot : Hello! I am your AI ChatBot.\n");
            chatArea.append("Bot : How can I help you?\n\n");
        }
    }

    public static void main(String[] args) {

        new ChatBotGUI();

    }
}