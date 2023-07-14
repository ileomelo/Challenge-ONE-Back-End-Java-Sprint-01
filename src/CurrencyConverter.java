import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverter extends JFrame {
    private JTextField amountTextField;
    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JLabel resultLabel;

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    // Taxas de câmbio (valores aproximados)
    private static final double[] exchangeRates = {1.0, 0.91, 0.77, 142.0, 4.86 , 1.5}; // USD, EUR, GBP, JPY, BRL, AUD

    // Nomes das moedas
    private static final String[] currencies = {"USD - Dólar Americano", "EUR - Euro", "GBP - Libra Esterlina",
            "JPY - Iene Japonês", "BRL - Real Brasileiro", "AUD - Dólar Australiano"};

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Cria o painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Cria os elementos da interface
        JLabel amountLabel = new JLabel("Valor:");
        amountTextField = new JTextField();

        JLabel fromLabel = new JLabel("De:");
        fromComboBox = new JComboBox<>(currencies);

        JLabel toLabel = new JLabel("Para:");
        toComboBox = new JComboBox<>(currencies);

        JButton convertButton = new JButton("Converter");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        JLabel resultTextLabel = new JLabel("Valor convertido:");
        resultLabel = new JLabel();

        // Adiciona os elementos ao painel
        panel.add(amountLabel);
        panel.add(amountTextField);
        panel.add(fromLabel);
        panel.add(fromComboBox);
        panel.add(toLabel);
        panel.add(toComboBox);
        panel.add(convertButton);
        panel.add(resultTextLabel);
        panel.add(resultLabel);

        // Define o painel como conteúdo da janela
        setContentPane(panel);
        pack();
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountTextField.getText());
            int fromIndex = fromComboBox.getSelectedIndex();
            int toIndex = toComboBox.getSelectedIndex();

            double fromRate = exchangeRates[fromIndex];
            double toRate = exchangeRates[toIndex];

            double convertedAmount = (amount / fromRate) * toRate;
            String toCurrency = currencies[toIndex].substring(0, 3);

            resultLabel.setText(toCurrency + ": " + DECIMAL_FORMAT.format(convertedAmount));
        } catch (NumberFormatException e) {
            resultLabel.setText("Valor inválido");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CurrencyConverter().setVisible(true);
            }
        });
    }
}
