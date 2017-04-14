package manager.util;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.text.*; 
public class DocColorTest extends JFrame 
  { 
    JTextPane textPane = new JTextPane(); 
    JPanel contPane = new JPanel(); 
    public DocColorTest() 
     { 
          super("DocColorTest"); 
          Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); 
          setBounds((d.width-300)/2,(d.height-200)/2,300,200); 
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
          contPane.setLayout(new BorderLayout()); 
          contPane.add(new JScrollPane(textPane),"Center"); 
         // insertDocument("�����Զ������趨����", Color.RED); 
         // insertDocument("��������Ҫ��������10�֡� ", Color.BLACK); 
          setContentPane(contPane); 
          setVisible(true); 
     }    
 
public void insertDocument(String text , Color textColor)//���ݴ������ɫ�����֣������ֲ����ı��� 
     { 
        SimpleAttributeSet set = new SimpleAttributeSet(); 
        StyleConstants.setForeground(set, textColor);//����������ɫ 
        StyleConstants.setFontSize(set, 12);//���������С 
        Document doc = textPane.getStyledDocument(); 
        try 
        { 
           doc.insertString(doc.getLength(), text, set);//�������� 
        } 
         catch (BadLocationException e) 
          { 
          } 
     } 
}