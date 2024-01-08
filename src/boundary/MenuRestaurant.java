package boundary;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import control.ControlAnnulation;
import control.ControlReservation;
import control.ControlRestaurant;

import javax.swing.*;
import java.awt.*;

public class MenuRestaurant {
    private final ControlAnnulation controlAnnulation;
    private final ControlReservation controlReservation;
    private final ControlRestaurant controlRestaurant;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextPane localisationTextPane;
    private JLabel label1;
    private JTextPane descriptionTextPane;
    private JTextPane carteTextPane;
    private JSpinner spinner1;
    private JButton chercherDisponibiliteButton;
    private JList list1;
    private JButton confirmerReservationButton;
    private JList list2;
    private JButton annulerReservationButton;
    private JList list3;
    private JTextArea textArea1;

    public MenuRestaurant(ControlReservation controlReservation, ControlRestaurant controlRestaurant, ControlAnnulation controlAnnulation) {
        this.controlReservation = controlReservation;
        this.controlRestaurant = controlRestaurant;
        this.controlAnnulation = controlAnnulation;
        $$$setupUI$$$();
        label1.setText(controlRestaurant.getNom());
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        label1 = new JLabel("Restaurant name");
        label1.setFont(label1.getFont().deriveFont(26f));
        descriptionTextPane = new JTextPane();
        descriptionTextPane.setText(controlRestaurant.getDescription());
        localisationTextPane = new JTextPane();
        localisationTextPane.setText(controlRestaurant.getLocalisation());
        carteTextPane = new JTextPane();
        carteTextPane.setText(controlRestaurant.getCarte());
        spinner1 = new JSpinner();
        spinner1.setModel(new SpinnerNumberModel(1, 1, 20, 1));
        chercherDisponibiliteButton = new JButton("Chercher Disponibilité");
        list1 = new JList();
        int[] dataClient = new int[4];
        chercherDisponibiliteButton.addActionListener(e -> {
            dataClient[1] = (int) spinner1.getValue();
            String[] joursLibres = controlReservation.getJoursLibres(dataClient[1]);

            // Create a DefaultListModel
            DefaultListModel<String> listModel = new DefaultListModel<>();

            // Add each day to the model
            for (String jourLibre : joursLibres) {
                listModel.addElement(jourLibre);
            }

            // Set the model to the JList
            list1.setModel(listModel);

            list1.setEnabled(true);
            list1.setVisible(true);
        });
        list3 = new JList();
        list1.addListSelectionListener(e -> {
            int[] indiceJour = controlReservation.getIndiceJoursLibres(dataClient[1]);
            dataClient[0] = indiceJour[list1.getSelectedIndex()];
            String[] heuresLibres = controlReservation.getHorairesLibres(dataClient);

            DefaultListModel<String> listModel = new DefaultListModel<>();

            for (String heureLibre : heuresLibres) {
                listModel.addElement(heureLibre);
            }

            // Set the model to the JList
            list3.setModel(listModel);

            list3.setEnabled(true);
            list3.setVisible(true);
        });
        list2 = new JList();
        confirmerReservationButton = new JButton("Confirmer Réservation");
        list3.addListSelectionListener(e -> {
            int[] indiceHoraire = controlReservation.getIndiceHorairesLibres(dataClient);
            dataClient[2] = indiceHoraire[list3.getSelectedIndex()];
            confirmerReservationButton.setEnabled(true);
        });
        confirmerReservationButton.addActionListener(e -> {
            System.out.println(10);
            String userInput = JOptionPane.showInputDialog(null, "Please enter your name for the reservation:");
            if (userInput == null || userInput.trim().isEmpty()) {
                return;
            }
            String code = controlReservation.makeReservation(dataClient, userInput.trim());
            JOptionPane.showMessageDialog(null, "Votre code de réservation est : " + code + "\nMerci de vous en rappeler !");
            DefaultListModel<String> listModel = new DefaultListModel<>();
            listModel.addElement(code);
            list2.setEnabled(true);
            list2.setModel(listModel);
            list1.clearSelection();
            list3.clearSelection();
        });
        annulerReservationButton = new JButton("Annuler Réservation");
        annulerReservationButton.addActionListener(e -> {
            String code = (String) list2.getSelectedValue();
            list2.remove(list2.getSelectedIndex());
            controlAnnulation.annuler(code);
            list2.clearSelection();
            list2.revalidate();
            list2.repaint();
        });
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1 = new JTabbedPane();
        panel1.add(tabbedPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FormLayout("fill:22px:grow,fill:d:noGrow,fill:d:grow,fill:d:noGrow,fill:d:grow", "center:27px:noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:d:grow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:d:grow"));
        panel2.setVisible(false);
        tabbedPane1.addTab("Acceuil", panel2);
        label1.setDoubleBuffered(false);
        label1.setEnabled(true);
        label1.setFocusCycleRoot(false);
        label1.setFocusTraversalPolicyProvider(false);
        label1.setForeground(new Color(-16777216));
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setInheritsPopupMenu(true);
        label1.setOpaque(true);
        label1.setText("");
        label1.setVerticalTextPosition(3);
        CellConstraints cc = new CellConstraints();
        panel2.add(label1, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.FILL));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer1, cc.xyw(1, 1, 2, CellConstraints.FILL, CellConstraints.CENTER));
        localisationTextPane.setEditable(false);
        panel2.add(localisationTextPane, cc.xyw(1, 9, 5, CellConstraints.FILL, CellConstraints.FILL));
        final JLabel label2 = new JLabel();
        label2.setText("Addresse :");
        panel2.add(label2, cc.xy(1, 7));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer2, cc.xyw(4, 1, 2, CellConstraints.FILL, CellConstraints.CENTER));
        descriptionTextPane.setEditable(false);
        panel2.add(descriptionTextPane, cc.xyw(1, 5, 5, CellConstraints.FILL, CellConstraints.FILL));
        final JLabel label3 = new JLabel();
        label3.setText("Description");
        panel2.add(label3, cc.xy(1, 3));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Carte", panel3);
        carteTextPane.setEditable(false);
        panel3.add(carteTextPane, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 6, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setEnabled(false);
        tabbedPane1.addTab("Réservation", panel4);
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(0, 5, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(130, 11), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Nombres de Personnes :");
        panel4.add(label4, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(130, 17), null, 0, false));
        spinner1 = new JSpinner();
        panel4.add(spinner1, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        chercherDisponibiliteButton.setText("Chercher Disponibilité");
        panel4.add(chercherDisponibiliteButton, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(3, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list2.setEnabled(false);
        panel4.add(list2, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        list3 = new JList();
        panel4.add(list3, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer8, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        annulerReservationButton.setEnabled(true);
        annulerReservationButton.setText("Annuler Réservation");
        panel4.add(annulerReservationButton, new com.intellij.uiDesigner.core.GridConstraints(6, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        confirmerReservationButton.setEnabled(true);
        confirmerReservationButton.setText("Confirmer Réservation");
        panel4.add(confirmerReservationButton, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        list1.setEnabled(false);
        panel4.add(list1, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        label2.setLabelFor(localisationTextPane);
        label3.setLabelFor(descriptionTextPane);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
