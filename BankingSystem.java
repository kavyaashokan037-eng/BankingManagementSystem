import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BankingSystem extends JFrame {

    private final Color bg = new Color(250, 242, 255);
    private final Color sidebarColor = new Color(65, 25, 90);
    private final Color purple = new Color(180, 55, 205);
    private final Color pink = new Color(245, 55, 165);

    private JPanel mainPanel;

    public BankingSystem() {
        setTitle("Banking Management System");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900, 600));

        createDashboard();
    }

    private void createDashboard() {

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(bg);

        // HEADER - NO BANK NAME
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(120, 35, 155));
        header.setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("BANKING MANAGEMENT SYSTEM");
        title.setFont(new Font("Segoe UI", Font.BOLD, 25));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel(
            "ACCOUNT & TRANSACTION MANAGEMENT"
        );
        subtitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        subtitle.setForeground(new Color(255, 220, 250));

        JPanel heading = new JPanel();
        heading.setOpaque(false);
        heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));
        heading.add(title);
        heading.add(Box.createVerticalStrut(5));
        heading.add(subtitle);

        header.add(heading, BorderLayout.WEST);
        root.add(header, BorderLayout.NORTH);

        // SIDEBAR
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(sidebarColor);
        sidebar.setBorder(new EmptyBorder(25, 15, 20, 15));
        sidebar.setPreferredSize(new Dimension(235, 600));

        JLabel menuTitle = new JLabel("   MAIN MENU");
        menuTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        menuTitle.setForeground(new Color(255, 190, 245));

        sidebar.add(menuTitle);
        sidebar.add(Box.createVerticalStrut(25));

        addMenu(sidebar, "Dashboard", e -> showHomeDashboard());
        addMenu(sidebar, "Create Account", e -> createAccount());
        addMenu(sidebar, "Deposit Money", e -> depositMoney());
        addMenu(sidebar, "Withdraw Money", e -> withdrawMoney());
        addMenu(sidebar, "Transfer Money", e -> transferMoney());
        addMenu(sidebar, "Check Balance", e -> checkBalance());
        addMenu(sidebar, "Transaction History", e -> transactionHistory());

        sidebar.add(Box.createVerticalGlue());

        JButton exit = menuButton("Exit");

        exit.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                this,
                "Do you want to exit?",
                "Exit",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        sidebar.add(exit);
        root.add(sidebar, BorderLayout.WEST);

        // MAIN CONTENT
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(bg);
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        root.add(mainPanel, BorderLayout.CENTER);
        setContentPane(root);

        showHomeDashboard();
    }

    private void addMenu(
        JPanel sidebar,
        String text,
        ActionListener action
    ) {
        JButton button = menuButton(text);
        button.addActionListener(action);

        sidebar.add(button);
        sidebar.add(Box.createVerticalStrut(13));
    }

    // BRIGHT SIDEBAR BUTTONS
    private JButton menuButton(String text) {

        JButton button = new JButton(text);

        button.setPreferredSize(new Dimension(205, 48));
        button.setMinimumSize(new Dimension(205, 48));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        button.setBackground(purple);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));

        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.setEnabled(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(255, 130, 225), 2
                ),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
            )
        );

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(pink);
                button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(purple);
                button.setForeground(Color.WHITE);
            }
        });

        return button;
    }

    // DASHBOARD - NO TOTAL CARDS, NO BANK NAME
    private void showHomeDashboard() {

        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout(20, 20));

        JPanel welcome = new JPanel();
        welcome.setOpaque(false);
        welcome.setLayout(new BoxLayout(welcome, BoxLayout.Y_AXIS));

        JLabel heading = new JLabel("Welcome to Your Dashboard");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 27));
        heading.setForeground(new Color(100, 25, 135));
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel description = new JLabel(
            "Manage your accounts and transactions easily."
        );
        description.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        description.setForeground(new Color(110, 85, 120));
        description.setAlignmentX(Component.LEFT_ALIGNMENT);

        welcome.add(heading);
        welcome.add(Box.createVerticalStrut(12));
        welcome.add(description);

        mainPanel.add(welcome, BorderLayout.NORTH);

        // CENTER PANEL
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.WHITE);

        center.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(225, 180, 245), 2
                ),
                new EmptyBorder(30, 20, 30, 20)
            )
        );

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel icon = new JLabel("BANKING");
        icon.setFont(new Font("Segoe UI", Font.BOLD, 38));
        icon.setForeground(new Color(180, 55, 205));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel message = new JLabel("Welcome to Your Dashboard");
        message.setFont(new Font("Segoe UI", Font.BOLD, 23));
        message.setForeground(new Color(115, 35, 150));
        message.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel info = new JLabel(
            "Select an option from the menu to get started."
        );
        info.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        info.setForeground(new Color(105, 90, 115));
        info.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(icon);
        content.add(Box.createVerticalStrut(25));
        content.add(message);
        content.add(Box.createVerticalStrut(15));
        content.add(info);

        center.add(content);
        mainPanel.add(center, BorderLayout.CENTER);

        JLabel footer = new JLabel(
            "Secure Transactions  |  Easy Account Management",
            SwingConstants.CENTER
        );
        footer.setFont(new Font("Segoe UI", Font.BOLD, 13));
        footer.setForeground(new Color(145, 55, 165));

        mainPanel.add(footer, BorderLayout.SOUTH);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // CREATE ACCOUNT
    private void createAccount() {

        JTextField nameField = new JTextField(18);
        JTextField numberField = new JTextField(18);
        JTextField depositField = new JTextField(18);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 15));
        panel.add(new JLabel("Account Holder Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Account Number:"));
        panel.add(numberField);
        panel.add(new JLabel("Initial Deposit (₹):"));
        panel.add(depositField);

        int result = JOptionPane.showConfirmDialog(
            this, panel, "Create New Account",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) return;

        String name = nameField.getText().trim();
        String account = numberField.getText().trim();

        try {
            double amount = Double.parseDouble(
                depositField.getText().trim()
            );

            if (name.isEmpty() || account.isEmpty() || amount < 0) {
                showError("Enter valid account details and deposit.");
                return;
            }

            try (Connection con = DatabaseConnection.getConnection()) {

                String sql =
                    "INSERT INTO accounts " +
                    "(account_number, account_holder, balance) " +
                    "VALUES (?, ?, ?)";

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, account);
                    ps.setString(2, name);
                    ps.setDouble(3, amount);
                    ps.executeUpdate();
                }

                if (amount > 0) {
                    recordTransaction(con, account, "DEPOSIT", amount);
                }

                JOptionPane.showMessageDialog(
                    this,
                    "Account Created Successfully!\n" +
                    "Name: " + name +
                    "\nAccount Number: " + account +
                    "\nInitial Deposit: ₹" + amount
                );

            } catch (SQLException ex) {
                showError("Could not create account.\n" + ex.getMessage());
            }

        } catch (NumberFormatException ex) {
            showError("Enter a valid deposit amount.");
        }
    }

    // DEPOSIT MONEY
    private void depositMoney() {

        JTextField accountField = new JTextField(15);
        JTextField amountField = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 15));
        panel.add(new JLabel("Account Number:"));
        panel.add(accountField);
        panel.add(new JLabel("Deposit Amount (₹):"));
        panel.add(amountField);

        int result = JOptionPane.showConfirmDialog(
            this, panel, "Deposit Money",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) return;

        String account = accountField.getText().trim();

        try {
            double amount = Double.parseDouble(amountField.getText().trim());

            if (account.isEmpty() || amount <= 0) {
                showError("Enter a valid account and amount.");
                return;
            }

            try (Connection con = DatabaseConnection.getConnection()) {

                con.setAutoCommit(false);

                try {
                    String sql =
                        "UPDATE accounts SET balance = balance + ? " +
                        "WHERE account_number = ?";

                    int rows;

                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setDouble(1, amount);
                        ps.setString(2, account);
                        rows = ps.executeUpdate();
                    }

                    if (rows == 0) {
                        con.rollback();
                        showError("Account not found.");
                        return;
                    }

                    recordTransaction(con, account, "DEPOSIT", amount);
                    con.commit();

                    JOptionPane.showMessageDialog(
                        this, "Deposit successful!\nAmount: ₹" + amount
                    );

                } catch (SQLException ex) {
                    con.rollback();
                    showError("Deposit failed.\n" + ex.getMessage());
                } finally {
                    con.setAutoCommit(true);
                }

            } catch (SQLException ex) {
                showError("Database error.\n" + ex.getMessage());
            }

        } catch (NumberFormatException ex) {
            showError("Enter a valid amount.");
        }
    }

    // WITHDRAW MONEY
    private void withdrawMoney() {

        JTextField accountField = new JTextField(15);
        JTextField amountField = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 15));
        panel.add(new JLabel("Account Number:"));
        panel.add(accountField);
        panel.add(new JLabel("Withdraw Amount (₹):"));
        panel.add(amountField);

        int result = JOptionPane.showConfirmDialog(
            this, panel, "Withdraw Money",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) return;

        String account = accountField.getText().trim();

        try {
            double amount = Double.parseDouble(amountField.getText().trim());

            if (account.isEmpty() || amount <= 0) {
                showError("Enter a valid account and amount.");
                return;
            }

            try (Connection con = DatabaseConnection.getConnection()) {

                con.setAutoCommit(false);

                try {
                    String sql =
                        "UPDATE accounts SET balance = balance - ? " +
                        "WHERE account_number = ? AND balance >= ?";

                    int rows;

                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setDouble(1, amount);
                        ps.setString(2, account);
                        ps.setDouble(3, amount);
                        rows = ps.executeUpdate();
                    }

                    if (rows == 0) {
                        con.rollback();
                        showError(
                            "Account not found or insufficient balance."
                        );
                        return;
                    }

                    recordTransaction(con, account, "WITHDRAW", amount);
                    con.commit();

                    JOptionPane.showMessageDialog(
                        this, "Withdrawal successful!\nAmount: ₹" + amount
                    );

                } catch (SQLException ex) {
                    con.rollback();
                    showError("Withdrawal failed.\n" + ex.getMessage());
                } finally {
                    con.setAutoCommit(true);
                }

            } catch (SQLException ex) {
                showError("Database error.\n" + ex.getMessage());
            }

        } catch (NumberFormatException ex) {
            showError("Enter a valid amount.");
        }
    }

    // TRANSFER MONEY
    private void transferMoney() {

        JTextField senderField = new JTextField(15);
        JTextField receiverField = new JTextField(15);
        JTextField amountField = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 15));
        panel.add(new JLabel("Sender Account Number:"));
        panel.add(senderField);
        panel.add(new JLabel("Receiver Account Number:"));
        panel.add(receiverField);
        panel.add(new JLabel("Transfer Amount (₹):"));
        panel.add(amountField);

        int result = JOptionPane.showConfirmDialog(
            this, panel, "Transfer Money",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) return;

        String sender = senderField.getText().trim();
        String receiver = receiverField.getText().trim();

        try {
            double amount = Double.parseDouble(amountField.getText().trim());

            if (sender.isEmpty() || receiver.isEmpty() ||
                sender.equals(receiver) || amount <= 0) {
                showError("Enter valid sender, receiver and amount.");
                return;
            }

            try (Connection con = DatabaseConnection.getConnection()) {

                con.setAutoCommit(false);

                try {
                    String debit =
                        "UPDATE accounts SET balance = balance - ? " +
                        "WHERE account_number = ? AND balance >= ?";

                    int debitRows;

                    try (PreparedStatement ps = con.prepareStatement(debit)) {
                        ps.setDouble(1, amount);
                        ps.setString(2, sender);
                        ps.setDouble(3, amount);
                        debitRows = ps.executeUpdate();
                    }

                    if (debitRows == 0) {
                        con.rollback();
                        showError(
                            "Sender account not found or insufficient balance."
                        );
                        return;
                    }

                    String credit =
                        "UPDATE accounts SET balance = balance + ? " +
                        "WHERE account_number = ?";

                    int creditRows;

                    try (PreparedStatement ps = con.prepareStatement(credit)) {
                        ps.setDouble(1, amount);
                        ps.setString(2, receiver);
                        creditRows = ps.executeUpdate();
                    }

                    if (creditRows == 0) {
                        con.rollback();
                        showError("Receiver account not found.");
                        return;
                    }

                    recordTransaction(
                        con, sender, "TRANSFER OUT", amount
                    );
                    recordTransaction(
                        con, receiver, "TRANSFER IN", amount
                    );

                    con.commit();

                    JOptionPane.showMessageDialog(
                        this, "Transfer successful!\nAmount: ₹" + amount
                    );

                } catch (SQLException ex) {
                    con.rollback();
                    showError("Transfer failed.\n" + ex.getMessage());
                } finally {
                    con.setAutoCommit(true);
                }

            } catch (SQLException ex) {
                showError("Database error.\n" + ex.getMessage());
            }

        } catch (NumberFormatException ex) {
            showError("Enter a valid amount.");
        }
    }

    // CHECK BALANCE
    private void checkBalance() {

        String account = JOptionPane.showInputDialog(
            this, "Enter Account Number:",
            "Check Balance", JOptionPane.QUESTION_MESSAGE
        );

        if (account == null || account.trim().isEmpty()) return;

        String sql =
            "SELECT account_holder, balance FROM accounts " +
            "WHERE account_number = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, account.trim());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Account Holder: " + rs.getString("account_holder") +
                        "\nAccount Number: " + account.trim() +
                        "\nAvailable Balance: ₹" +
                        String.format("%.2f", rs.getDouble("balance")),
                        "Account Balance",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    showError("Account not found.");
                }
            }

        } catch (SQLException ex) {
            showError("Could not check balance.\n" + ex.getMessage());
        }
    }

    // TRANSACTION HISTORY
    private void transactionHistory() {

        String account = JOptionPane.showInputDialog(
            this, "Enter Account Number:",
            "Transaction History", JOptionPane.QUESTION_MESSAGE
        );

        if (account == null || account.trim().isEmpty()) return;

        String sql =
            "SELECT transaction_type, amount, transaction_date " +
            "FROM transactions WHERE account_number = ? " +
            "ORDER BY transaction_date DESC";

        StringBuilder history = new StringBuilder();

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, account.trim());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    history.append(rs.getString("transaction_type"))
                        .append(" | ₹")
                        .append(String.format("%.2f", rs.getDouble("amount")))
                        .append(" | ")
                        .append(rs.getTimestamp("transaction_date"))
                        .append("\n");
                }
            }

            if (history.length() == 0) {
                history.append("No transactions found.");
            }

            JTextArea area = new JTextArea(history.toString(), 15, 45);
            area.setEditable(false);
            area.setFont(new Font("Monospaced", Font.PLAIN, 13));
            area.setCaretPosition(0);

            JOptionPane.showMessageDialog(
                this, new JScrollPane(area),
                "Transaction History",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (SQLException ex) {
            showError("Could not load history.\n" + ex.getMessage());
        }
    }

    // SAVE TRANSACTION
    private void recordTransaction(
        Connection con,
        String account,
        String type,
        double amount
    ) throws SQLException {

        String sql =
            "INSERT INTO transactions " +
            "(account_number, transaction_type, amount, transaction_date) " +
            "VALUES (?, ?, ?, NOW())";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, account);
            ps.setString(2, type);
            ps.setDouble(3, amount);
            ps.executeUpdate();
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
            this, message, "Banking Management System",
            JOptionPane.ERROR_MESSAGE
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception ignored) {
                // Use default Swing appearance.
            }

            new BankingSystem().setVisible(true);
        });
    }
}
