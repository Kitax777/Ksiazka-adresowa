package sample;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Main {

    private static Vector<Dane> V = new Vector<>();
    public static void main(String[] args){
        V = null;
        try {
            System.out.println("UUU");
            FileInputStream fileIn = new FileInputStream("Dane.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            V = (Vector<Dane>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }


        JFrame frame = new JFrame();
        JTable table = new JTable();

        frame.setResizable(false);

        Object[] columns = {"Imie","Nazwisko","Telefon","Ulica","Nr domu","Nr mieszkania","Kod Pocztowy","Urzad pocztowy"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        table.setModel(model);
        table.setDefaultEditor(Object.class, null);

        for(int i = 0;i<V.size();i++){
            Object[] row = new Object[8];
            row[0] = V.get(i).getImie();
            row[1] = V.get(i).getNazwisko();
            row[2] = V.get(i).getTelefon();
            row[3] = V.get(i).getAdres().getUlica();
            row[4] = V.get(i).getAdres().getNrDomu();
            row[5] = V.get(i).getAdres().getNrMieszkania();
            row[6] = V.get(i).getAdres().getKodPocztowy();
            row[7] = V.get(i).getAdres().getUrzadPocztowy();
            model.addRow(row);
        }


        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,12);
        table.setFont(font);
        table.setRowHeight(20);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();

        JTextField textImie = new JTextField();
        JTextField textNazwisko = new JTextField();
        JTextField textTelefon = new JTextField();
        JTextField textUlica = new JTextField();
        JTextField textNrDomu = new JTextField();
        JTextField textNrMieszkania = new JTextField();
        JTextField textKodPocztowy = new JTextField();
        JTextField textUrzadPocztowy = new JTextField();
        JTextField textWyszukaj = new JTextField();

        JButton bDodaj = new JButton("Dodaj");
        JButton bModyfikuj = new JButton("Modyfikuj");
        JButton bUsun = new JButton("Usun");
        JButton bWyszukaj = new JButton("Wyszukaj");
        JButton bZapisz = new JButton("Zapisz");

        textImie.setBounds(20,220,100,25);
        textNazwisko.setBounds(140,220,100,25);
        textTelefon.setBounds(260,220,100,25);
        textUlica.setBounds(380,220,100,25);
        textNrDomu.setBounds(500,220,100,25);
        textNrMieszkania.setBounds(620,220,100,25);
        textKodPocztowy.setBounds(740,220,100,25);
        textUrzadPocztowy.setBounds(860,220,100,25);
        textWyszukaj.setBounds(140,310,100,25);


        bDodaj.setBounds(740, 265, 100, 25);
        bModyfikuj.setBounds(740, 310, 100, 25);
        bUsun.setBounds(860, 310, 100, 25);
        bWyszukaj.setBounds(260, 310, 100, 25);
        bZapisz.setBounds(860, 265, 100, 25);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 980, 200);

        frame.setLayout(null);

        frame.add(pane);

        frame.add(textImie);
        frame.add(textNazwisko);
        frame.add(textTelefon);
        frame.add(textUlica);
        frame.add(textNrDomu);
        frame.add(textNrMieszkania);
        frame.add(textKodPocztowy);
        frame.add(textUrzadPocztowy);
        frame.add(textWyszukaj);

        frame.add(bDodaj);
        frame.add(bModyfikuj);
        frame.add(bUsun);
        frame.add(bWyszukaj);
        frame.add(bZapisz);

        Object[] row = new Object[8];

        bDodaj.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                V.add(new Dane(textImie.getText(),
                        textNazwisko.getText(),
                        Integer.parseInt(textTelefon.getText()),
                        textUlica.getText(),
                        textNrDomu.getText(),
                        Integer.parseInt(textNrMieszkania.getText()),
                        textKodPocztowy.getText(),
                        textUrzadPocztowy.getText()));

                row[0] = V.lastElement().getImie();
                row[1] = V.lastElement().getNazwisko();
                row[2] = V.lastElement().getTelefon();
                row[3] = V.lastElement().getAdres().getUlica();
                row[4] = V.lastElement().getAdres().getNrDomu();
                row[5] = V.lastElement().getAdres().getNrMieszkania();
                row[6] = V.lastElement().getAdres().getKodPocztowy();
                row[7] = V.lastElement().getAdres().getUrzadPocztowy();

                textImie.setText(null);
                textNazwisko.setText(null);
                textTelefon.setText(null);
                textUlica.setText(null);
                textNrDomu.setText(null);
                textNrMieszkania.setText(null);
                textKodPocztowy.setText(null);
                textUrzadPocztowy.setText(null);

                model.addRow(row);
            }
        });

        bUsun.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                int i = table.getSelectedRow();
                for (int j = 0; j<V.size();j++){
                    if(table.getRowSorter().convertRowIndexToView(j)==i){
                        i = j;
                        break;
                    }
                }
                if(i >= 0){
                    V.remove(i);
                    model.removeRow(i);
                    textImie.setText(null);
                    textNazwisko.setText(null);
                    textTelefon.setText(null);
                    textUlica.setText(null);
                    textNrDomu.setText(null);
                    textNrMieszkania.setText(null);
                    textKodPocztowy.setText(null);
                    textUrzadPocztowy.setText(null);
                }
                else{
                    System.out.println("Blad przy usuwaniu");
                }
            }
        });

        table.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e){

                int i = table.getSelectedRow();
                for (int j = 0; j<V.size();j++){
                    if(table.getRowSorter().convertRowIndexToView(j)==i){
                        i=j;
                        break;
                    }
                }
                textImie.setText(V.get(i).getImie());
                textNazwisko.setText(V.get(i).getNazwisko());
                textTelefon.setText(V.get(i).getTelefon().toString());
                textUlica.setText(V.get(i).getAdres().getUlica());
                textNrDomu.setText(V.get(i).getAdres().getNrDomu());
                textNrMieszkania.setText(V.get(i).getAdres().getNrMieszkania().toString());
                textKodPocztowy.setText(V.get(i).getAdres().getKodPocztowy());
                textUrzadPocztowy.setText(V.get(i).getAdres().getUrzadPocztowy());
            }
        });

        bModyfikuj.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                int i = table.getSelectedRow();
                for (int j = 0; j<V.size();j++){
                    if(table.getRowSorter().convertRowIndexToView(j)==i){
                        i = j;
                        break;
                    }
                }

                if(i >= 0)
                {

                    V.get(i).setImie(textImie.getText());
                    V.get(i).setNazwisko(textNazwisko.getText());
                    V.get(i).setTelefon(Integer.parseInt(textTelefon.getText()));
                    V.get(i).getAdres().setUlica(textUlica.getText());
                    V.get(i).getAdres().setNrDomu(textNrDomu.getText());
                    V.get(i).getAdres().setNrMieszkania(Integer.parseInt(textNrMieszkania.getText()));
                    V.get(i).getAdres().setKodPocztowy(textKodPocztowy.getText());
                    V.get(i).getAdres().setUrzadPocztowy(textUrzadPocztowy.getText());

                    model.setValueAt(V.get(i).getImie(),i,0);
                    model.setValueAt(V.get(i).getNazwisko(),i,1);
                    model.setValueAt(V.get(i).getTelefon(),i,2);
                    model.setValueAt(V.get(i).getAdres().getUlica(),i,3);
                    model.setValueAt(V.get(i).getAdres().getNrDomu(),i,4);
                    model.setValueAt(V.get(i).getAdres().getNrMieszkania(),i,5);
                    model.setValueAt(V.get(i).getAdres().getKodPocztowy(),i,6);
                    model.setValueAt(V.get(i).getAdres().getUrzadPocztowy(),i,7);

                }
                else{
                    System.out.println("Blad przy modyfikacji");
                }
            }
        });

        bWyszukaj.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                int i = 0;
                for(; i < V.size(); i++){
                    if(V.get(i).getNazwisko().equals(textWyszukaj.getText())){
                        break;
                    }
                }

                int columnIndexToSort = 1;
                sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));

                sorter.setSortKeys(sortKeys);
                sorter.sort();

                for (int j = 0; j<V.size();j++){
                    if(table.getRowSorter().convertRowIndexToModel(j)==i){
                        table.setRowSelectionInterval(j,j);
                        textImie.setText(V.get(i).getImie());
                        textNazwisko.setText(V.get(i).getNazwisko());
                        textTelefon.setText(V.get(i).getTelefon().toString());
                        textUlica.setText(V.get(i).getAdres().getUlica());
                        textNrDomu.setText(V.get(i).getAdres().getNrDomu());
                        textNrMieszkania.setText(V.get(i).getAdres().getNrMieszkania().toString());
                        textKodPocztowy.setText(V.get(i).getAdres().getKodPocztowy());
                        textUrzadPocztowy.setText(V.get(i).getAdres().getUrzadPocztowy());
                        break;
                    }
                }


            }
        });

        bZapisz.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    FileOutputStream fileOut =
                            new FileOutputStream("Dane.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(V);
                    out.close();
                    fileOut.close();
                    System.out.printf("Zapisano w Dane.ser");
                } catch (IOException i) {
                    i.printStackTrace();
                }


            }
        });

        frame.setSize(980,390);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



    }
}
