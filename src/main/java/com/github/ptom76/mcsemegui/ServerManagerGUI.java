package com.github.ptom76.mcsemegui;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.bukkit.Bukkit.getLogger;


public class ServerManagerGUI extends JFrame implements ActionListener, MouseListener {
    private static MainCommandSave scc = new MainCommandSave();
    private static LoadLanguage Langs;
    private JTabbedPane mainLogPanel;//コンソールのタブ付きパネル
    private static JScrollPane ChatPanel; //チャットパネル表示
    private static JScrollPane CommandPanel; //コマンドパネル表示
    private static JScrollPane McSEmeGUIPanel; //GUIのログパネル表示
    private static JScrollPane MarkCommandPanel;
    private JMenuBar mb1;//メニューバー
    private JMenu me1;//メニュー
    private JMenuItem mi1;//メニューアイテム
    private JMenu helpmenu1;//
    private JMenu settingMenu;
    private JMenu memoryInfoUpdateSpeed;
    private JMenuItem memoryInfoUpdateStop;
    private JMenuItem memoryInfoUpdateLowSpeed;
    private JMenuItem memoryInfoUpdateNormalSpeed;
    private JMenuItem memoryInfoUpdateHighSpeed;
    private JMenuItem memoryInfoUpdateMaxSpeed;
    private JMenuItem help1;private JMenuItem help2;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private static JList list1;
    private JButton enterButton;
    public static JTextField textField1;
    private static JScrollPane scrollPane1;
    private static JScrollPane scrollPane2;
    private JList list2;
    private JLabel label2;
    private JLabel label3;
    public static JTextArea textArea1;
    private static DefaultListModel model = new DefaultListModel<>();
    private static DefaultListModel modelChat = new DefaultListModel<>();
    private static DefaultListModel modelCommand = new DefaultListModel<>();
    private static DefaultListModel modelMcSEmeGUI = new DefaultListModel<>();
    public static DefaultListModel modelMarkCommand = new DefaultListModel<>();
    private DefaultListModel model2 = new DefaultListModel<>();
    private JSplitPane splitPane1;
    private JSplitPane splitPane2;
    private JPopupMenu popup;
    //private JPopupMenu markCommandListPopupMenu;
    private JMenuItem pmCopy;
    private JMenuItem pmPaste;
    private JMenuItem pmCut;
    private JMenuItem pmAllSelect;
    private JMenuItem pmCommandMark;
    //private JMenuItem mclpmDelete;
    //private JMenuItem mclpmEdit;
    private String memoryInfoString[] = new String[0];
    private static JList listChat = new JList();
    private static JList listCommand = new JList();
    private static JList listMcSEmeGUI = new JList();
    public static JList listMarkCommand = new JList();
    private static JScrollBar scrollBar;
    public ServerManagerGUI() {
        getLogger().info("Language:" + Langs.lang.language);
        Container cp1 = getContentPane();
        this.pack();
        this.setTheme();

        String cp = System.getProperty("java.class.path");
        String fs = System.getProperty("file.separator");
        String acp = (new File(cp)).getAbsolutePath();
        this.setTitle(acp + " - " + "McSEmeGUI");
        this.setResizable(true);
        this.setSize(900, 500);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ClassLoader cl = this.getClass().getClassLoader();
        ImageIcon icon = new ImageIcon(cl.getResource("icon.png"));
        this.setIconImage(icon.getImage());
        mainLogPanel = new JTabbedPane();
        popup = new JPopupMenu();
        //markCommandListPopupMenu = new JPopupMenu();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel2.setLayout(new BorderLayout());
        panel3.setLayout(new BorderLayout());
        panel4.setLayout(new BorderLayout());
        enterButton = new JButton(Langs.lang.decision);
        label2 = new JLabel(Langs.lang.player);
        label3 = new JLabel(Langs.lang.status);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label3.setHorizontalAlignment(JLabel.CENTER);
        textField1 = new JTextField(0);
        textField1.setPreferredSize(new Dimension(100, 20));
        textArea1 = new JTextArea();
        textArea1.setEditable(false);
        list1 = new JList();
        list2 = new JList();
        listChat = new JList();
        listCommand = new JList();
        listMcSEmeGUI = new JList();
        listMarkCommand = new JList();
        scrollPane1 = new JScrollPane(list1);
        scrollPane2 = new JScrollPane(list2);
        ChatPanel = new JScrollPane(listChat);
        CommandPanel = new JScrollPane(listCommand);
        McSEmeGUIPanel = new JScrollPane(listMcSEmeGUI);
        MarkCommandPanel = new JScrollPane(listMarkCommand);
        panel1.add(mainLogPanel, BorderLayout.CENTER);//panel1はコンソール
        panel1.add(panel3, BorderLayout.SOUTH);
        panel2.add(label2, BorderLayout.NORTH);
        splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel2);
        splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane2, panel4);
        panel2.add(splitPane2, BorderLayout.CENTER);
        panel3.add(textField1, BorderLayout.CENTER);
        panel3.add(enterButton, BorderLayout.EAST);
        panel4.add(label3, BorderLayout.NORTH);
        panel4.add(textArea1, BorderLayout.CENTER);//パネル4にtextを入れる
        mainLogPanel.addTab(Langs.lang.console,null, scrollPane1,"全体のログ");
        mainLogPanel.addTab(Langs.lang.chat,null, ChatPanel,"チャットログ");
        mainLogPanel.addTab(Langs.lang.command,null, CommandPanel,"プレイヤーの入力したコマンドのログ");
        mainLogPanel.addTab("McSEmeGUI",null, McSEmeGUIPanel,"このプラグインのログ");
        mainLogPanel.addTab(Langs.lang.markCommand,null, MarkCommandPanel,"plugin/McSEmeGUI/save_commands.json");
        cp1.add(splitPane1, BorderLayout.CENTER);
        mb1 = new JMenuBar();
        me1 = new JMenu(Langs.lang.file);
        settingMenu = new JMenu(Langs.lang.setting);
        helpmenu1 = new JMenu(Langs.lang.help);
        mi1 = new JMenuItem(Langs.lang.close);
        help1 = new JMenuItem(Langs.lang.pluginsInformation);
        help2 = new JMenuItem(Langs.lang.help);
        memoryInfoUpdateSpeed = new JMenu(Langs.lang.statusUpdateFrequency);
        memoryInfoUpdateStop = new JMenuItem(Langs.lang.stop+"(0)");
        memoryInfoUpdateLowSpeed = new JMenuItem(Langs.lang.lowSpeed+"(1)");
        memoryInfoUpdateNormalSpeed = new JMenuItem(Langs.lang.normalSpeed+"(2)");
        memoryInfoUpdateHighSpeed = new JMenuItem(Langs.lang.highSpeed+"(3)");
        memoryInfoUpdateMaxSpeed = new JMenuItem(Langs.lang.maxSpeed+"(4)");
        mb1.add(me1);
        mb1.add(settingMenu);
        mb1.add(helpmenu1);
        me1.add(mi1);
        settingMenu.add(memoryInfoUpdateSpeed);
        memoryInfoUpdateSpeed.add(memoryInfoUpdateStop);
        memoryInfoUpdateSpeed.add(memoryInfoUpdateLowSpeed);
        memoryInfoUpdateSpeed.add(memoryInfoUpdateNormalSpeed);
        memoryInfoUpdateSpeed.add(memoryInfoUpdateHighSpeed);
        memoryInfoUpdateSpeed.add(memoryInfoUpdateMaxSpeed);
        helpmenu1.add(help1);
        helpmenu1.add(help2);
        setJMenuBar(mb1);
        setVisible(true);
        enterButton.addActionListener(this);
        textField1.addActionListener(this);
        mi1.addActionListener(this);
        help1.addActionListener(this);
        help2.addActionListener(this);
        pmCopy = new JMenuItem(Langs.lang.copy+" Ctrl+C");pmPaste = new JMenuItem(Langs.lang.paste+" Ctrl+V");pmCut = new JMenuItem(Langs.lang.cut+" Ctrl+X");pmAllSelect = new JMenuItem(Langs.lang.allSelect+" Ctrl+A");
        pmCommandMark = new JMenuItem(Langs.lang.commandMarkCommandSave+" Ctrl+B");
        popup.add(pmCommandMark);
        popup.addSeparator();
        popup.add(pmCut);popup.add(pmCopy);popup.add(pmPaste);popup.add(pmAllSelect);
        ImageIcon star = new ImageIcon(cl.getResource("star.png"));
        pmCommandMark.setIcon(star);
        memoryInfoUpdateStop.addActionListener(this);
        memoryInfoUpdateLowSpeed.addActionListener(this);
        memoryInfoUpdateNormalSpeed.addActionListener(this);
        memoryInfoUpdateHighSpeed.addActionListener(this);
        memoryInfoUpdateMaxSpeed.addActionListener(this);
        pmCommandMark.addActionListener(this);
        pmPaste.addActionListener(this);
        pmCut.addActionListener(this);
        pmCopy.addActionListener(this);
        pmAllSelect.addActionListener(this);
        textField1.addMouseListener(this);
        addWindowListener(new WindowClosing());
        TextUndoManager ud = new TextUndoManager();
        textField1.getDocument().addUndoableEditListener(ud);
        textField1.addKeyListener(ud);
        listMarkCommand.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList listMarkCommand = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = listMarkCommand.locationToIndex(evt.getPoint());
                    textField1.setText((String)listMarkCommand.getModel().getElementAt(listMarkCommand.locationToIndex(evt.getPoint())));
                } else if (evt.getClickCount() == 3) {
                    int index = listMarkCommand.locationToIndex(evt.getPoint());
                }
            }
        });
        addToConsole(Langs.lang.enabled, "gui");
        try {
            MemoryInfoEveryCheck.main(memoryInfoString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void mouseReleased(MouseEvent mouseEvents){
        showPopup(mouseEvents);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvents) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvents) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvents) {

    }

    public void mousePressed(MouseEvent mouseEvents){
        showPopup(mouseEvents);
    }
    private void showPopup(MouseEvent mouseEvents){
        if (mouseEvents.isPopupTrigger()) {
            setTheme();
            popup.show(mouseEvents.getComponent(), mouseEvents.getX(), mouseEvents.getY());
        }
    }

    public void actionPerformed(ActionEvent e1)
    {
        if ((e1.getSource() == enterButton) || (e1.getSource() == textField1))
        {
            String console_command = textField1.getText();
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.dispatchCommand(console, console_command);
            addToConsole("GUI>"+ console_command, "gui");
            textField1.setText("");
        }
        if (e1.getSource() == memoryInfoUpdateStop)
        {
            MemoryInfoEveryCheck.speed = 0;
        }
        if (e1.getSource() == memoryInfoUpdateLowSpeed)
        {
            MemoryInfoEveryCheck.speed = 1000;
            try {
                MemoryInfoEveryCheck.main(memoryInfoString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (e1.getSource() == memoryInfoUpdateNormalSpeed)
        {
            MemoryInfoEveryCheck.speed = 250;
            try {
                MemoryInfoEveryCheck.main(memoryInfoString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (e1.getSource() == memoryInfoUpdateHighSpeed)
        {
            MemoryInfoEveryCheck.speed = 50;
            try {
                MemoryInfoEveryCheck.main(memoryInfoString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (e1.getSource() == memoryInfoUpdateMaxSpeed)
        {
            MemoryInfoEveryCheck.speed = 1;
            try {
                MemoryInfoEveryCheck.main(memoryInfoString);
            } catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        if (e1.getSource() == mi1)
        {
            int ans = JOptionPane.showConfirmDialog(ServerManagerGUI.this, Langs.lang.closeMessage);
            if(ans == JOptionPane.YES_OPTION)
            {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "stop");
            }
        }
        if (e1.getSource() == help1) {
            //ヘルプのコンテナ
            Container cp2 = getContentPane();
            JOptionPane.showMessageDialog(
                    cp2.getParent(),
                    "<html>McSEmeGUI Plugin<br><br>Version 1.0.3<br><br>McSEmeGUIはMinecraftServer用のコンソールGUI<br>を表示するSpigotプラグインです。<br><br>byKao(Pitan 音MAD)</html>",
                    Langs.lang.pluginsInformation,
                    JOptionPane.PLAIN_MESSAGE,
                    null);
        }
        if (e1.getSource() == help2)
        {
            Container cp2 = getContentPane();
            JOptionPane.showMessageDialog(
                    cp2.getParent(),
                    "<html>Wiki:<a href= \"https://github.com/PTOM76/McSEmeGUI/wiki\" >https://github.com/PTOM76/McSEmeGUI/wiki</a><br>\"使い方.txt\"はjarの中に入ってます、</html>",
                    Langs.lang.help,
                    JOptionPane.PLAIN_MESSAGE,
                    null);
            cp2.setCursor(new Cursor(Cursor.HAND_CURSOR));
            cp2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent mouseEvent) {
                    try {
                        String url = null;
                        Desktop.getDesktop().browse(new URI(url));
                    } catch (URISyntaxException | IOException ex) {

                    }
                }
            });
        }
        if (e1.getSource() == pmCopy)
        {
            textField1.copy();
        }
        if (e1.getSource() == pmCut)
        {
            textField1.cut();
        }
        if (e1.getSource() == pmPaste)
        {
            textField1.paste();
        }
        if (e1.getSource() == pmAllSelect)
        {
            textField1.selectAll();
        }
        if (e1.getSource() == pmCommandMark)
        {
            scc.saveMarkCommands();
        }
    }
    class WindowClosing extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            int ans = JOptionPane.showConfirmDialog(ServerManagerGUI.this, Langs.lang.closeMessage);
            if(ans == JOptionPane.YES_OPTION)
            {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "stop");
            }
        }
    }
    void setTheme()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex1)
        {
            ex1.printStackTrace();
        }
    }
    public static <modelType> void addToConsole(String text1 ,String modelType)
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dt = new SimpleDateFormat("HH:mm:ss");
        model.addElement("[" + dt.format(cal.getTime()) + " "+Langs.lang.info+ "]:" +  text1);
        list1.setModel(model);
        scrollBar = scrollPane1.getVerticalScrollBar();
        scrollBar.setValue(scrollBar.getMaximum());

        if (modelType == "chat") {
            modelChat.addElement("[" + dt.format(cal.getTime()) + " "+Langs.lang.info+ "]:" + text1);
            listChat.setModel(modelChat);
            scrollBar = ChatPanel.getVerticalScrollBar();
            scrollBar.setValue(scrollBar.getMaximum());

        }
        if (modelType == "command") {
            modelCommand.addElement("[" + dt.format(cal.getTime()) + " "+Langs.lang.info+ "]:" + text1);
            listCommand.setModel(modelCommand);
            scrollBar = CommandPanel.getVerticalScrollBar();
            scrollBar.setValue(scrollBar.getMaximum());
        }
        if (modelType == "gui") {
            modelMcSEmeGUI.addElement("[" + dt.format(cal.getTime()) + " "+Langs.lang.info+ "]:" + text1);
            listMcSEmeGUI.setModel(modelMcSEmeGUI);
            scrollBar = McSEmeGUIPanel.getVerticalScrollBar();
            scrollBar.setValue(scrollBar.getMaximum());
        }
    }
    public void addPlayersList(String pls)
    {
        model2.addElement(pls);
        list2.setModel(model2);
    }
    public void removePlayersList(String pls)
    {
        model2.removeElement(pls);
        list2.setModel(model2);
    }
    public static void addCommandMark(String acm)
    {
        modelMarkCommand.addElement(acm);
        listMarkCommand.setModel(modelMarkCommand);
    }
}
