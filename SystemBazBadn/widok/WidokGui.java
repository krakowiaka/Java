package widok;

import kontroler.ZdarzenieGui;
import model.Badanie;
import model.Pacjent;
import model.Model;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.io.FileWriter;


/**
 * Klasa odpowiedzialana za widok aplikacji
 */
public class WidokGui extends JFrame {

    private Model model = null;

    private JButton bDodaj;
    private JButton bPrzegladaj;

    private JTextField TextPESEL, TextNazwisko, TextImie, TextPlec, TextWiek, TextData, TextLeuko,
                                            TextErytro, TextTrombo, TextMono, TextLimfo;
    private JButton przyciskZapisz;
    private JButton sprawdzPacjent;

    private JButton sprawdzWyniki;
    private JButton zapiszTXT;
    private JTextField TextSprawdzPESEL;
    private ArrayList<Badanie> wyniki;
    private ArrayList<Badanie> wynikiLista;

    public JFrame przegladajFrame;
    public JTextArea podgladWynikow;

    /**
     * Konstruktor
     * @param m Model
     */
    public WidokGui(Model m){

        this.model = m;

        this.createGui();
    }

    /**
     * Metoda zwracajaca wprowadzony przez uzytkownika PESEL w formacie Integer - dla okna Dodaj
     * @return wprowadzony przez użytkownika PESEL w formacie Integer
     */
    public int getPESEL(){
        try{
            return Integer.parseInt(this.TextPESEL.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Błednie wpisany PESEL", "Błąd - PESEL", JOptionPane.INFORMATION_MESSAGE);
        }
        return -1;
    }

    /**
     * Metoda zwracajaca wprowadzony przez użytkownika PESEL w formacie Integer - dla okna Przeglądaj
     * @return wprowadzony przez użytkownika PESEL w formacie Integer
     */
    public int getSprawdzPESEL(){
        try {
            return Integer.parseInt(this.TextSprawdzPESEL.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Błednie wpisany PESEL", "Błąd - PESEL", JOptionPane.INFORMATION_MESSAGE);
        }
        return -1;
    }

    /**
     * Metoda wyswietlająca okno informujace o braku pacjenta w bazie
     */
    public void brakPESEL(){
        JOptionPane.showMessageDialog(null, "Brak pacjenta w bazie danych");
    }

    /**
     * Metoda tworzaca pacjenta na podstawie pol edycyjnych
     * @return Pacjent stworzony na podstawie pól edycyjnych
     */
    public Pacjent getPacjent(){
        Pacjent p = null;

        try {
            int PESEL = Integer.parseInt(this.TextPESEL.getText());
            String nazwisko = this.TextNazwisko.getText();
            String imie = this.TextImie.getText();
            String plec = this.TextPlec.getText();
            int wiek = Integer.parseInt(this.TextWiek.getText());

            p = new Pacjent(PESEL, nazwisko, imie, plec, wiek);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Błedne dane Pacjenta", "Błąd - dane pacjenta", JOptionPane.INFORMATION_MESSAGE);
        }

        return(p);
    }

    /**
     * Metoda ustawiajaca pola edycyjne na podstawie Pacjenta
     * @param p Pacjent
     */
    public void setPacjent(Pacjent p){
        if (p!=null){
            this.TextNazwisko.setText(p.getNazwisko());
            this.TextImie.setText(p.getImie());
            this.TextPlec.setText(p.getPlec());
            this.TextWiek.setText(String.valueOf(p.getWiek()));
        }
        else {
            this.TextNazwisko.setText("");
            this.TextImie.setText("");
            this.TextPlec.setText("");
            this.TextWiek.setText("");
            JOptionPane.showMessageDialog(null, "Nie ma takiego PACJENTA!");
        }
    }

    /**
     * Metoda tworzaca badanie na podstawie pol edycyjnych
     * @return Badanie utworzone na podstawie pol edycyjnych
     */
    public Badanie getBadanie(){
        Badanie b = null;
        try{
            int PESEL = Integer.parseInt(this.TextPESEL.getText());
            String dataBadania = this.TextData.getText();
            float leukocyty = Float.parseFloat(this.TextLeuko.getText());
            float erytrocyty = Float.parseFloat(this.TextErytro.getText());
            float trombocyty = Float.parseFloat(this.TextTrombo.getText());
            float monocyty = Float.parseFloat(this.TextMono.getText());
            float limfocyty = Float.parseFloat(this.TextLimfo.getText());
            b = new Badanie(PESEL, dataBadania, leukocyty,erytrocyty, trombocyty, monocyty, limfocyty);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Błedne dane Badania");
        }

        return (b);
    }

    /**
     * Metoda zwracajaca znalezione dla danego pacjenta wyniki badan
     * @param znalezioneWyniki wyniki badan danego pacjenta znalezione w bazie danych
     */
    public void setWyniki (ArrayList<Badanie> znalezioneWyniki){
        if (znalezioneWyniki != null)
            this.wyniki = znalezioneWyniki;
    }

    /**
     * Metoda odpowiedzialna za oczyszczanie pol tekstowych
     */
    public void clearOknoDodaj(){
        this.TextPESEL.setText("123456");
        this.TextNazwisko.setText("Kowalski");
        this.TextImie.setText("Jan");
        this.TextPlec.setText("M");
        this.TextWiek.setText(null);
        this.TextData.setText("DD.MM.YYYY");
        this.TextLeuko.setText(null);
        this.TextErytro.setText(null);
        this.TextTrombo.setText(null);
        this.TextMono.setText(null);
        this.TextLimfo.setText(null);
    }

    /**
     * Metoda zapisujaca raport z badania do pliku TXT
     * @param poleTekstowe JTextArea
     */
    public void zapiszTXT(JTextArea poleTekstowe)  {
        String text = poleTekstowe.getText();

        FileWriter file = null;
        try {
            file = new FileWriter("Raport-z-badania.txt");
            BufferedWriter raport = new BufferedWriter(file);
            raport.append(text);
            raport.close();
            JOptionPane.showMessageDialog(null,"Plik został zapisany!", "Zapisywanie raportu", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Nie udało się zapisać pliku.", "Błąd przy zapisywaniu", JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }

    }

    /**
     * Metoda odpowiedzialna za tworzenie okna z mozliwoscia dodawania
     * nowego Badania lub/i Pacjenta
     */
    public void oknoDodaj(){
        /*
         * Okno do dodawania nowego Pacjenta lub Badania
         */
        JFrame dodajFrame = new JFrame("Dodawanie badania");
        dodajFrame.setSize(600,350);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        dodajFrame.add(mainPanel);

        /*
         * Panel do dodawania nowego Pacjenta/ lub istniejącego wyniki badań krwi
         */
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.X_AXIS));
        mainPanel.add(addPanel);

        /*
         * Lewy Panel odpowiedzialny za dodawanie informacji o Pacjent
         */
        JPanel dodPacjent = new JPanel();
        dodPacjent.setBorder(BorderFactory.createTitledBorder("Dane pacjenta"));
        dodPacjent.setLayout(new BoxLayout(dodPacjent, BoxLayout.Y_AXIS));
        addPanel.add(dodPacjent);

        //Panel PESEL
        JPanel subpanel = new JPanel();
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
        dodPacjent.add(subpanel);

        JLabel LabelPESEL = new JLabel("PESEL:");
        LabelPESEL.setPreferredSize(new Dimension(90,25));
        LabelPESEL.setMinimumSize(new Dimension(90,25));
        LabelPESEL.setMaximumSize(new Dimension(90,25));
        subpanel.add(LabelPESEL);

        TextPESEL = new JTextField("123456");
        TextPESEL.setPreferredSize(new Dimension(90,20));
        TextPESEL.setMinimumSize(new Dimension(90,20));
        TextPESEL.setMaximumSize(new Dimension(90,20));
        subpanel.add(TextPESEL);

        sprawdzPacjent = new JButton("Sprawdz");
        sprawdzPacjent.setActionCommand("checkPacjent");
        sprawdzPacjent.setBackground(Color.decode("#EA6B66"));
        sprawdzPacjent.setPreferredSize(new Dimension(80,20));
        sprawdzPacjent.setMinimumSize(new Dimension(80,20));
        sprawdzPacjent.setMinimumSize(new Dimension(80,20));
        subpanel.add(sprawdzPacjent);

        subpanel.add(Box.createRigidArea(new Dimension(0,40)));

        //Panel Nazwisko
        subpanel = new JPanel();
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
        dodPacjent.add(subpanel);

        JLabel LabelNazwisko = new JLabel("Nazwisko:");
        LabelNazwisko.setPreferredSize(new Dimension(90,25));
        LabelNazwisko.setMinimumSize(new Dimension(90,25));
        LabelNazwisko.setMaximumSize(new Dimension(90,25));
        subpanel.add(LabelNazwisko);

        TextNazwisko = new JTextField("Kowalski");
        TextNazwisko.setPreferredSize(new Dimension(180,20));
        TextNazwisko.setMinimumSize(new Dimension(180,20));
        TextNazwisko.setMaximumSize(new Dimension(180,20));
        subpanel.add(TextNazwisko);

        subpanel.add(Box.createRigidArea(new Dimension(0,40)));

        //Panel Imię
        subpanel = new JPanel();
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
        dodPacjent.add(subpanel);

        JLabel LabelImie = new JLabel("Imię:");
        LabelImie.setPreferredSize(new Dimension(90,25));
        LabelImie.setMinimumSize(new Dimension(90,25));
        LabelImie.setMaximumSize(new Dimension(90,25));
        subpanel.add(LabelImie);

        TextImie = new JTextField("Jan");
        TextImie.setPreferredSize(new Dimension(180,20));
        TextImie.setMinimumSize(new Dimension(180,20));
        TextImie.setMaximumSize(new Dimension(180,20));
        subpanel.add(TextImie);

        subpanel.add(Box.createRigidArea(new Dimension(0,40)));

        //Panel Płeć
        subpanel = new JPanel();
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
        dodPacjent.add(subpanel);

        JLabel LabelPlec = new JLabel("Płeć:");
        LabelPlec.setPreferredSize(new Dimension(90,25));
        LabelPlec.setMinimumSize(new Dimension(90,25));
        LabelPlec.setMaximumSize(new Dimension(90,25));
        subpanel.add(LabelPlec);

        TextPlec = new JTextField("M");
        TextPlec.setPreferredSize(new Dimension(180,20));
        TextPlec.setMinimumSize(new Dimension(180,20));
        TextPlec.setMaximumSize(new Dimension(180,20));
        subpanel.add(TextPlec);

        subpanel.add(Box.createRigidArea(new Dimension(0,40)));

        //Panel Wiek
        subpanel = new JPanel();
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
        dodPacjent.add(subpanel);

        JLabel LabelWiek = new JLabel("Wiek:");
        LabelWiek.setPreferredSize(new Dimension(90,25));
        LabelWiek.setMinimumSize(new Dimension(90,25));
        LabelWiek.setMaximumSize(new Dimension(90,25));
        subpanel.add(LabelWiek);

        TextWiek = new JTextField();
        TextWiek.setPreferredSize(new Dimension(180,20));
        TextWiek.setMinimumSize(new Dimension(180,20));
        TextWiek.setMaximumSize(new Dimension(180,20));
        subpanel.add(TextWiek);

        subpanel.add(Box.createRigidArea(new Dimension(0,40)));

        /*
         * Prawy Panel odpowiedzialny za dodawanie informacji o Badanie
         */
        JPanel dodBadanie = new JPanel();
        dodBadanie.setBorder(BorderFactory.createTitledBorder("Wyniki badania"));
        dodBadanie.setLayout(new BoxLayout(dodBadanie, BoxLayout.Y_AXIS));
        addPanel.add(dodBadanie);



        //Panel Data
        subpanel = new JPanel();
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
        dodBadanie.add(subpanel);

        JLabel LabelData = new JLabel("Data:");
        LabelData.setPreferredSize(new Dimension(90,25));
        LabelData.setMinimumSize(new Dimension(90,25));
        LabelData.setMaximumSize(new Dimension(90,25));
        subpanel.add(LabelData);

        TextData = new JTextField("DD.MM.YYYY");
        TextData.setPreferredSize(new Dimension(180,20));
        TextData.setMinimumSize(new Dimension(180,20));
        TextData.setMaximumSize(new Dimension(180,20));
        subpanel.add(TextData);

        subpanel.add(Box.createRigidArea(new Dimension(0,75)));

        //Panel Leukocyty
        subpanel = new JPanel();
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
        dodBadanie.add(subpanel);

        JLabel LabelLeuko = new JLabel("Leukocyty:");
        LabelLeuko.setPreferredSize(new Dimension(90,25));
        LabelLeuko.setMinimumSize(new Dimension(90,25));
        LabelLeuko.setMaximumSize(new Dimension(90,25));
        subpanel.add(LabelLeuko);

        TextLeuko = new JTextField();
        TextLeuko.setPreferredSize(new Dimension(50,20));
        TextLeuko.setMinimumSize(new Dimension(50,20));
        TextLeuko.setMaximumSize(new Dimension(50,20));
        subpanel.add(TextLeuko);

        JLabel LabelLeukoJed = new JLabel("tys/µl");
        LabelLeukoJed.setPreferredSize(new Dimension(50,20));
        LabelLeukoJed.setMinimumSize(new Dimension(50,20));
        LabelLeukoJed.setMaximumSize(new Dimension(50,20));
        subpanel.add(LabelLeukoJed);

        //Panel Erytrocyty
        subpanel = new JPanel();
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
        dodBadanie.add(subpanel);

        JLabel LabelErytro = new JLabel("Erytrocyty:");
        LabelErytro.setPreferredSize(new Dimension(90,25));
        LabelErytro.setMinimumSize(new Dimension(90,25));
        LabelErytro.setMaximumSize(new Dimension(90,25));
        subpanel.add(LabelErytro);

        TextErytro = new JTextField();
        TextErytro.setPreferredSize(new Dimension(50,20));
        TextErytro.setMinimumSize(new Dimension(50,20));
        TextErytro.setMaximumSize(new Dimension(50,20));
        subpanel.add(TextErytro);

        JLabel LabelErytroJed = new JLabel("mln/µl");
        LabelErytroJed.setPreferredSize(new Dimension(50,20));
        LabelErytroJed.setMinimumSize(new Dimension(50,20));
        LabelErytroJed.setMaximumSize(new Dimension(50,20));
        subpanel.add(LabelErytroJed);

        //Panel Trombocyty
        subpanel = new JPanel();
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
        dodBadanie.add(subpanel);

        JLabel LabelTrombo = new JLabel("Trombocyty:");
        LabelTrombo.setPreferredSize(new Dimension(90,25));
        LabelTrombo.setMinimumSize(new Dimension(90,25));
        LabelTrombo.setMaximumSize(new Dimension(90,25));
        subpanel.add(LabelTrombo);

        TextTrombo = new JTextField();
        TextTrombo.setPreferredSize(new Dimension(50,20));
        TextTrombo.setMinimumSize(new Dimension(50,20));
        TextTrombo.setMaximumSize(new Dimension(50,20));
        subpanel.add(TextTrombo);

        JLabel LabelTromboJed = new JLabel("tys/µl");
        LabelTromboJed.setPreferredSize(new Dimension(50,20));
        LabelTromboJed.setMinimumSize(new Dimension(50,20));
        LabelTromboJed.setMaximumSize(new Dimension(50,20));
        subpanel.add(LabelTromboJed);

        //Panel Monocyty
        subpanel = new JPanel();
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
        dodBadanie.add(subpanel);

        JLabel LabelMono = new JLabel("Monocyty:");
        LabelMono.setPreferredSize(new Dimension(90,25));
        LabelMono.setMinimumSize(new Dimension(90,25));
        LabelMono.setMaximumSize(new Dimension(90,25));
        subpanel.add(LabelMono);

        TextMono = new JTextField();
        TextMono.setPreferredSize(new Dimension(50,20));
        TextMono.setMinimumSize(new Dimension(50,20));
        TextMono.setMaximumSize(new Dimension(50,20));
        subpanel.add(TextMono);

        JLabel LabelMonoJed = new JLabel("tys/µl");
        LabelMonoJed.setPreferredSize(new Dimension(50,20));
        LabelMonoJed.setMinimumSize(new Dimension(50,20));
        LabelMonoJed.setMaximumSize(new Dimension(50,20));
        subpanel.add(LabelMonoJed);

        //Panel Limfocyty
        subpanel = new JPanel();
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
        dodBadanie.add(subpanel);

        JLabel LabelLimfo = new JLabel("Limfocyty:");
        LabelLimfo.setPreferredSize(new Dimension(90,25));
        LabelLimfo.setMinimumSize(new Dimension(90,25));
        LabelLimfo.setMaximumSize(new Dimension(90,25));
        subpanel.add(LabelLimfo);

        TextLimfo = new JTextField();
        TextLimfo.setPreferredSize(new Dimension(50,20));
        TextLimfo.setMinimumSize(new Dimension(50,20));
        TextLimfo.setMaximumSize(new Dimension(50,20));
        subpanel.add(TextLimfo);

        JLabel LabelLimfoJed = new JLabel("tys/µl");
        LabelLimfoJed.setPreferredSize(new Dimension(50,20));
        LabelLimfoJed.setMinimumSize(new Dimension(50,20));
        LabelLimfoJed.setMaximumSize(new Dimension(50,20));
        subpanel.add(LabelLimfoJed);

        //Przycisk ,,Zapisz"
        JPanel ButtonZapisz = new JPanel();
        przyciskZapisz = new JButton("Zapisz wyniki");
        przyciskZapisz.setActionCommand("addRekord");
        przyciskZapisz.setBackground(Color.decode("#EA6B66"));
        ButtonZapisz.add(przyciskZapisz, BorderLayout.CENTER);
        ButtonZapisz.setBorder(BorderFactory.createTitledBorder("Zapisz wyniki"));
        mainPanel.add(ButtonZapisz);

        dodajFrame.add(mainPanel, BorderLayout.CENTER);
        dodajFrame.setVisible(true);
        dodajFrame.setTitle("Dodawanie informacji");

    }

    /**
     * Metoda odpowiedzialna za tworzenie okna z przegladarka badań krwi
     */
    public void oknoPrzegladaj(){

        // Okno do przeglądania badań pacjenta, którego PESEL zostanie wprowadzony
        przegladajFrame = new JFrame("Przegladarka badan");
        przegladajFrame.setSize(600,450);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        przegladajFrame.add(mainPanel);

        //Panel PESEL
        JPanel peselPanel= new JPanel();
        peselPanel.setLayout(new BoxLayout(peselPanel, BoxLayout.X_AXIS));
        mainPanel.add(peselPanel);

        JLabel LabelPESEL = new JLabel("PESEL:");
        LabelPESEL.setPreferredSize(new Dimension(90,25));
        LabelPESEL.setMinimumSize(new Dimension(90,25));
        LabelPESEL.setMaximumSize(new Dimension(90,25));
        peselPanel.add(LabelPESEL);

        TextSprawdzPESEL = new JTextField();
        TextSprawdzPESEL.setPreferredSize(new Dimension(90,20));
        TextSprawdzPESEL.setMinimumSize(new Dimension(90,20));
        TextSprawdzPESEL.setMaximumSize(new Dimension(90,20));
        peselPanel.add(TextSprawdzPESEL);

        sprawdzWyniki = new JButton("Szukaj");
        sprawdzWyniki.setActionCommand("sprawdzWyniki");
        sprawdzWyniki.setBackground(Color.decode("#EA6B66"));
        sprawdzWyniki.setMargin(new Insets(0, 10, 0, 10));
        sprawdzWyniki.setPreferredSize(new Dimension(40,20));
        sprawdzWyniki.setMinimumSize(new Dimension(40,20));
        peselPanel.add(sprawdzWyniki);

        peselPanel.add(Box.createRigidArea(new Dimension(0,40)));

        if (wyniki != null) {

            // Panel do sprawdzania wyników danego pacjenta
            JPanel przegladajPanel = new JPanel();
            przegladajPanel.setLayout(new BoxLayout(przegladajPanel, BoxLayout.X_AXIS));
            mainPanel.add(przegladajPanel);

            /*
             * Lewy panel służacy do wprowadzania nr PESEL i wyboru badania (lewa strona)
             */
            JPanel lewyPrzegladaj = new JPanel();
            lewyPrzegladaj.setBorder(BorderFactory.createTitledBorder("Wyszukane badania"));
            lewyPrzegladaj.setLayout(new BoxLayout(lewyPrzegladaj, BoxLayout.Y_AXIS));
            przegladajPanel.add(lewyPrzegladaj);

            JPanel subpanel = new JPanel();
            subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
            lewyPrzegladaj.add(subpanel);

            /*
             * Prawy Panel odpowiedzialny za podgląd wybranego badania (prawa strona)
             */
            JPanel prawyPrzegladaj = new JPanel();
            prawyPrzegladaj.setBorder(BorderFactory.createTitledBorder("Podgląd badania"));
            prawyPrzegladaj.setLayout(new BoxLayout(prawyPrzegladaj, BoxLayout.Y_AXIS));
            przegladajPanel.add(prawyPrzegladaj);

            String [] daty = new String [wyniki.size()];
            for (int i = 0; i < wyniki.size(); i++){
                daty[i] = wyniki.get(i).getDataBadania();
            }

            JList listaWynikow = new JList(daty);
            listaWynikow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listaWynikow.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            JScrollPane listScroller = new JScrollPane(listaWynikow);
            listScroller.setPreferredSize(new Dimension(200, 400));
            subpanel.add(listScroller, BorderLayout.NORTH);

            subpanel.add(Box.createRigidArea(new Dimension(10,0)));

            subpanel = new JPanel();
            subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
            prawyPrzegladaj.add(subpanel);

            podgladWynikow = new JTextArea("");
            podgladWynikow.setMargin(new Insets(20,20,20,20) );
            subpanel.setSize(250,150);
            subpanel.add(podgladWynikow);

            subpanel = new JPanel();
            subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
            prawyPrzegladaj.add(subpanel);

            zapiszTXT = new JButton("Wygeneruj raport TXT");
            zapiszTXT.setActionCommand("generujTXT");
            zapiszTXT.setBackground(Color.decode("#EA6B66"));
            zapiszTXT.setMargin(new Insets(0, 10, 0, 10));
            zapiszTXT.setPreferredSize(new Dimension(100,20));
            zapiszTXT.setMinimumSize(new Dimension(100,20));
            subpanel.add(zapiszTXT);

            listaWynikow.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent arg0) {
                    if (!arg0.getValueIsAdjusting()) {
                        for (int i = 0; i < wyniki.size(); i++) {
                            if (wyniki.get(i).getDataBadania() == listaWynikow.getSelectedValue())
                                podgladWynikow.setText( "Dane pacjenta \nPESEL: " + wyniki.get(i).getPesel() +
                                                        "\nNazwisko: " + model.findPacjent(wyniki.get(i).getPesel()).getNazwisko() +
                                                        "\nImie: " + model.findPacjent(wyniki.get(i).getPesel()).getImie() +
                                                        "\nPlec: " + model.findPacjent(wyniki.get(i).getPesel()).getPlec() +
                                                        "\nWiek: " + model.findPacjent(wyniki.get(i).getPesel()).getWiek() +
                                                        "\n\nData badania: " + wyniki.get(i).getDataBadania() +
                                                        "\nLeukocyty: " + wyniki.get(i).getLeukocyty() + " tys/µl   (norma 3,8-10 tys/µl)" +
                                                        "\nErytrocyty: " + wyniki.get(i).getErytrocyty() + " mln/µl   (norma 3,8 - 5,8 mln/µl)" +
                                                        "\nTrombocyty: " + wyniki.get(i).getTrombocyty() + " tys/µl   (norma 140-440 tys/µl)" +
                                                        "\nMonoocyty: " + wyniki.get(i).getMonocyty() + " tys/µl   (norma 0,2-1 tys/µl)" +
                                                        "\nLimfocyty: " + wyniki.get(i).getLimfocyty() + " tys/µl   (norma 1-3,5 tys/µl)");
                        }
                    }
                }
            });
        }

        przegladajFrame.add(mainPanel);
        przegladajFrame.setVisible(true);

    }

    /**
     * Metoda definiujaca wyglad aplikacji
     */
    private void createGui(){

        //Panel okna startowego aplikacji
        this.setTitle("ZUAL");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,250);

        //Panel tytułowy
        JPanel welcomeMessage = new JPanel();
        welcomeMessage.setSize(250, 150);
        GridLayout opisGrid = new GridLayout(2,1,0,0);
        welcomeMessage.setLayout(opisGrid);

        JLabel ZUAL = new JLabel("ZUAL",SwingConstants.CENTER);
        ZUAL.setPreferredSize(new Dimension(250, 100));
        ZUAL.setFont(new Font("Calibri", Font.PLAIN, 70));
        welcomeMessage.add(ZUAL, BorderLayout.CENTER);
        JLabel systemOpis = new JLabel("System obsługi bazy badań krwi",SwingConstants.CENTER);
        systemOpis.setPreferredSize(new Dimension(250, 50));
        systemOpis.setFont(new Font("Calibri", Font.PLAIN, 25));
        welcomeMessage.add(systemOpis, BorderLayout.CENTER);

        this.add(welcomeMessage,BorderLayout.CENTER);

        //Panel z przyciskami do wyboru funkcji, z której funkcji chce korzystać użytkownik
        JPanel buttons = new JPanel();

        this.bPrzegladaj = new JButton("Przegladaj");
        this.bPrzegladaj.setBackground(Color.decode("#EA6B66"));
        this.bPrzegladaj.setActionCommand("przegladajBadania");
        this.bPrzegladaj.setMargin(new Insets(5, 10, 5, 10));
        buttons.add(this.bPrzegladaj, BorderLayout.CENTER);

        this.bDodaj = new JButton("Dodaj badanie");
        this.bDodaj.setBackground(Color.decode("#EA6B66"));
        this.bDodaj.setActionCommand("dodajBadanie");
        this.bDodaj.setMargin(new Insets(5, 10, 5, 10));
        buttons.add(this.bDodaj, BorderLayout.CENTER);

        this.add(buttons, BorderLayout.SOUTH);
        this.setVisible(true);

    }

    /**
     * Metoda ustawiajaca kontroler aplikacji
     * @param z Kontroler - zdarzenie
     */
    public void setController(ZdarzenieGui z) {
        if (bDodaj != null && this.bDodaj.getActionListeners().length == 0)
            this.bDodaj.addActionListener(z);
        if (bPrzegladaj != null && this.bPrzegladaj.getActionListeners().length == 0)
            this.bPrzegladaj.addActionListener(z);
        if (sprawdzWyniki != null)
            this.sprawdzWyniki.addActionListener(z);
        if (przyciskZapisz != null)
            this.przyciskZapisz.addActionListener(z);
        if (zapiszTXT != null){
            this.zapiszTXT.addActionListener(z);
        }
        if (sprawdzPacjent != null)
            this.sprawdzPacjent.addActionListener(z);
    }
}
