import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new Frame("lab9");
        frame.setSize(1000, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    Map<String, Integer> map;
    Map<String, Integer> map2;
    Map<String, Integer> map3;
    Map<String, Integer> map4;
    DefaultListModel<Pair<String,Integer>> leftListModel;
    DefaultListModel<Pair<String,Integer>> rightListModel;

    JList<Pair<String,Integer>> leftList;
    JList<Pair<String,Integer>> rightList;
    JList<Pair<String,Integer>> thirdList;
    JList<Pair<String,Integer>> forthList;
    public Frame(String title){
        super(title);
        setLayout(new BorderLayout());
        JPanel leftListPanel = new JPanel();
        JPanel rightListPanel = new JPanel();
        add(leftListPanel,BorderLayout.WEST);
        add(rightListPanel,BorderLayout.EAST);


        map = new Map<>();
        //map.put("42",3);
        //map.put("23",8);
        // map.put("42",324);
        map3=map;


        map2 = new Map<>();
        //map2.put("422",3);
        //map2.put("231",8);
        // map2.put("42",324);
        map4=map2;

        leftListModel = map.getListModel();
        leftList = new JList<Pair<String,Integer>>(leftListModel);
        leftListPanel.add(leftList);
        thirdList=leftList;

        rightListModel = map2.getListModel();
        rightList = new JList<Pair<String,Integer>>(rightListModel);
        rightListPanel.add(rightList);
        forthList=rightList;

        JPanel panelButtons= new JPanel();
        add(panelButtons,BorderLayout.CENTER);
        panelButtons.setLayout(new FlowLayout());

        ButtonGroup groupButton = new ButtonGroup();
        JRadioButton leftButton = new JRadioButton();
        groupButton.add(leftButton);
        JLabel leftLabel = new JLabel("to left");
        leftButton.setSelected(true);
        JRadioButton rightButton = new JRadioButton();
        JLabel rightLabel = new JLabel("to right");
        groupButton.add(rightButton);
        panelButtons.add(leftLabel);
        panelButtons.add(leftButton);
        panelButtons.add(rightLabel);
        panelButtons.add(rightButton);

        JButton sizeButton = new JButton("size");
        panelButtons.add(sizeButton);
        JLabel sizeLabel = new JLabel();
        panelButtons.add(sizeLabel);

        JButton isEmptyButton = new JButton("is empty");
        panelButtons.add(isEmptyButton);
        JLabel isEmptyLabel = new JLabel();
        panelButtons.add(isEmptyLabel);


        JButton isEqualsButton = new JButton("is equals");
        panelButtons.add(isEqualsButton);
        JLabel isEqualsLabel = new JLabel();
        panelButtons.add(isEqualsLabel);

        JButton clearButton = new JButton("clear");
        panelButtons.add(clearButton);

        JButton putButton = new JButton("put");
        panelButtons.add(putButton);
        JLabel keyLabel = new JLabel("key:");
        panelButtons.add(keyLabel);
        JTextField keyField = new JTextField(10);
        panelButtons.add(keyField);
        JLabel valueLabel = new JLabel("value:");
        panelButtons.add(valueLabel);
        JTextField valueField = new JTextField(10);
        panelButtons.add(valueField);

        JButton putAllButton = new JButton("put all");
        panelButtons.add(putAllButton);


        sizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseRadio(leftButton);
                sizeLabel.setText(String.valueOf(map.size()));
            }
        });

        isEqualsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseRadio(leftButton);
                isEqualsLabel.setText(String.valueOf(map.equals(map2)));
            }
        });

        putButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    chooseRadio(leftButton);
                    Pair<String, Integer> pair= new Pair<>(keyField.getText(),Integer.parseInt(valueField.getText()));
                    map.put(pair);
                    leftList.setModel(map.getListModel());
                } catch (NullPointerException exception1){
                    JOptionPane.showMessageDialog(Frame.this, "Needs 2 parametrs");
                }catch (NumberFormatException exception){
                    JOptionPane.showMessageDialog(Frame.this, "Illegal arguments");
                }

            }
        });

        isEmptyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                chooseRadio(leftButton);
                isEmptyLabel.setText(String.valueOf(map.isEmpty()));
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseRadio(leftButton);
                map.clear();
                leftList.setModel(map.getListModel());
            }
        });

        putAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseRadio(leftButton);
                map.putAll(map2);
                leftList.setModel(map.getListModel());

            }
        });
    }
    public void chooseRadio(JRadioButton button){
        if (button.isSelected()){
                map = map3;
                map2= map4;

                leftList = thirdList;
                rightList = forthList;

        } else{
                map2 = map3;
                map = map4;
                rightList = thirdList;
                leftList = forthList;

        }
    }
}