import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.List;
import static java.lang.Math.pow;

public class Frame extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new Frame("Series");
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    static class Toy{
        String name;
        double price;
        int minAge;
        int maxAge;
        Toy(String name,double price,int minAge,int maxAge)throws IllegalArgumentException{
            this.name=name;
            this.price=price;
            this.minAge=minAge;
            this.maxAge=maxAge;
        }
        public String toString(){
            return "name is "+name+";price is "+price+";min age is "+minAge+";max age is "+maxAge;
        }
    }
    Frame(String title){
        super(title);
        setLayout(new FlowLayout());
        JButton inputFileNameButton = new JButton("read file");
        add(inputFileNameButton);
        JLabel inputFileNameLabel = new JLabel("input file name");
        add(inputFileNameLabel);
        JTextField inputFileNameTextField = new JTextField(15);
        add(inputFileNameTextField);
        JButton addButton = new JButton("add toy");
        add(addButton);
        JLabel ageLabel = new JLabel("input age");
        add(ageLabel);
        JTextField ageTextField = new JTextField(5);
        add(ageTextField);
        JLabel sumLabel = new JLabel("input sum");
        add(sumLabel);
        JTextField sumTextField = new JTextField(5);
        add(sumTextField);

        JTextArea inputListTextArea= new JTextArea(5,30);
        inputListTextArea.setLineWrap(true);
        add(inputListTextArea);
        JButton showVariantsButton = new JButton("show variants");
        add(showVariantsButton);
        JLabel variantsLabel = new JLabel("variants:");
        add(variantsLabel);
        JLabel variants = new JLabel("");
        add(variants);
        JButton sortNameButton = new JButton("show this variant");
        add(sortNameButton);
        JTextField variantTextField = new JTextField(5);
        add(variantTextField);
        JTextArea sortListTextArea = new JTextArea(5,30);
        add(sortListTextArea);
        //sortListTextArea.setLineWrap(true);

        inputFileNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Toy> toyList = create(inputFileNameTextField);
                    for (Toy i:toyList){
                        inputListTextArea.append(i +"\n");
                    }
                } catch (FileNotFoundException ew) {
                    JOptionPane.showMessageDialog(Frame.this,"file not found");
                } catch (NumberFormatException  ex) {
                    JOptionPane.showMessageDialog(Frame.this, "Number format error");
                } catch (IllegalArgumentException ed) {
                    JOptionPane.showMessageDialog(Frame.this,"illegal arguments");
                } catch (NullPointerException | NoSuchElementException ex) {
                    JOptionPane.showMessageDialog(Frame.this,"not enough data");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "................");
                }

            }
        });

        showVariantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<List<Toy>> resultat = result(create(inputFileNameTextField),ageTextField,sumTextField);
                    variants.setText(String.valueOf(resultat.size()));
                }catch (FileNotFoundException e0) {
                    JOptionPane.showMessageDialog(Frame.this,"file not found");
                }catch (NullPointerException | NumberFormatException e2) {
                    JOptionPane.showMessageDialog(Frame.this, "Number format error or empty string");
                }catch (IllegalArgumentException e3) {
                    JOptionPane.showMessageDialog(Frame.this,"illegal arguments");
                }catch (NoSuchElementException e4) {
                    JOptionPane.showMessageDialog(Frame.this,"not enough data");
                }catch (IOException e5) {
                    JOptionPane.showMessageDialog(Frame.this, "something wrong");
                }

            }
        });

        sortNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    if (variants.getText().equals("0")) {
                        JOptionPane.showMessageDialog(new JFrame("Error"), "you can't buy anything");
                    }else {
                        sortListTextArea.setText("");
                        List<Toy> toyList = create(inputFileNameTextField);
                        List<List<Toy>> resultat = result(toyList, ageTextField, sumTextField);
                        List<Toy> toyListItog = resultat.get(Integer.parseInt(variantTextField.getText()) - 1);
                        for (Toy item : toyListItog) {
                            sortListTextArea.append(item + "\n");
                        }
                    }
                }catch (FileNotFoundException e0) {
                    JOptionPane.showMessageDialog(Frame.this,"file not found");
                }catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(Frame.this, "empty string");
                }catch (IllegalArgumentException e3) {
                    JOptionPane.showMessageDialog(Frame.this,"illegal arguments");
                }catch (IndexOutOfBoundsException e4) {
                    JOptionPane.showMessageDialog(Frame.this, "you chose illegal variant");
                }catch (NoSuchElementException e5) {
                    JOptionPane.showMessageDialog(Frame.this,"not enough data");
                }catch (IOException e6) {
                    JOptionPane.showMessageDialog(Frame.this,"something wrong");
                }

            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddDialog(inputFileNameTextField);
            }
        });
    }
    private void openAddDialog(JTextField inputFileNameTextField) {
        JDialog addDialog = new JDialog(this, "add toy", true);
        addDialog.setSize(300, 200);
        addDialog.setLayout(new FlowLayout());

        addDialog.add(new JLabel("name"));
        JTextField nameField = new JTextField(10);
        addDialog.add(nameField);
        addDialog.add(new JLabel("price"));
        JTextField priceField = new JTextField(5);
        addDialog.add(priceField);
        addDialog.add(new JLabel("min age"));
        JTextField minAgeField = new JTextField(5);
        addDialog.add(minAgeField);
        addDialog.add(new JLabel("max age"));
        JTextField maxAgeField = new JTextField(5);
        addDialog.add(maxAgeField);
        JButton addButton = new JButton("add");
        addDialog.add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Double.parseDouble(priceField.getText())<0 |Integer.parseInt(minAgeField.getText())<0 |Integer.parseInt(maxAgeField.getText())<0){
                        JOptionPane.showMessageDialog(Frame.this, "at least one of count fields is less 0");
                    }else {
                        Toy newToy = new Toy(nameField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(minAgeField.getText()), Integer.parseInt(maxAgeField.getText()));
                        FileWriter writer = new FileWriter(inputFileNameTextField.getText(), true);
                        writer.write("\n");
                        writer.write("-" + nameField.getText() + "-" + priceField.getText() + "-" + minAgeField.getText() + "-" + maxAgeField.getText());
                        writer.close();
                    }
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(Frame.this, "the path is wrong");
                }catch (NumberFormatException e2) {
                    JOptionPane.showMessageDialog(Frame.this, "Illegal arguments");
                }catch (IOException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "We can't create this toy");
                }
                addDialog.setVisible(false);
            }
        });
        addDialog.setVisible(true);
    }
    List<Toy> create(JTextField input) throws NullPointerException,NumberFormatException,NoSuchElementException,IOException{
        File file = new File(input.getText());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        List<Toy> toyList = new ArrayList<>();
        while((line = bufferedReader.readLine()) != null){
            StringTokenizer parsedLine = new StringTokenizer(line, "-", false);
            Toy def = new Toy(parsedLine.nextToken(), Double.parseDouble(parsedLine.nextToken()), Integer.parseInt(parsedLine.nextToken()), Integer.parseInt(parsedLine.nextToken()));
            toyList.add(def);
        }
        bufferedReader.close();
        return toyList;
    }
    List<Toy> createSort(List<Toy> toyList,JTextField ageTextField,JTextField sumTextField) throws NullPointerException,NumberFormatException,NoSuchElementException{
        List<Toy> sortToyList = new ArrayList<>();
        for (Toy i:toyList){
            if (i.maxAge>=Integer.parseInt(ageTextField.getText()) && i.minAge<=Integer.parseInt(ageTextField.getText())&& i.price<=Double.parseDouble(sumTextField.getText())){
                sortToyList.add(i);
            }
        }
        return sortToyList;
    }
    List<List<Toy>> result(List<Toy> toyList,JTextField ageTextField,JTextField sumTextField)throws NullPointerException,NumberFormatException,NoSuchElementException,IOException{
        List<Toy> sortToyList = createSort(toyList,ageTextField,sumTextField);

        int count = sortToyList.size();
        int temp=count;
        int[] array = new int[count];

        List<List<Toy>> resultat = new ArrayList<>();
        for(int i = 0;i< pow(2,temp);i++){
            double sum =0;
            count=temp;
            int g=i;
            while(i !=0) {
                int b;
                b = i%2;
                array[count-1]=b;
                i = i/2;
                count--;
            }
            i=g;
            for (int l =0;l<array.length;l++){
                if(array[l]==1){
                    sum+=sortToyList.get(l).price;
                }
            }
            if (sum<=Double.parseDouble(sumTextField.getText())&& sum>0){
                List<Toy> var = new ArrayList<>();
                for (int l =0;l<array.length;l++){
                    if(array[l]==1){
                        var.add(sortToyList.get(l));
                    }
                }
                resultat.add(var);
            }
        }
        return resultat;
    }
}