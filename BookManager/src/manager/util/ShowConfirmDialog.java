package manager.util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowConfirmDialog extends JDialog implements ActionListener {
	Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screensize = tk.getScreenSize();
    int height = (int)screensize.getHeight();
    int width = (int)screensize.getWidth();
    private double enlargement_x=width/1920;
    private double enlargement_y=height/1080;
    
    JButton setButton; //ȷ�� 
    JButton denyButton;//��
    JButton cancelButton;//ȡ��

	private int result;//���ذ�ʲô��ť����-0����-1��ȡ��-2
  
    ShowConfirmDialog()
    {
    	result=-1;    	
    }
    public  ShowConfirmDialog(JFrame prentFrame,String title , String inform)
    {
    	
    	// ���ø���Ĺ��캯����
        super(prentFrame, title, false); // ������������false��ʾ���������������塣Ϊtrue��ʾ���ܹ�������������       
        result=-1;
        //����һ���߳�
        new Thread(new Runnable(){
            @Override
            public void run() {
//            	synchronized(this)
//            	{
                  initGUI(inform);
//                }
        	}
        }).start();
    }
    
    private void initGUI(String inform)
    {
    	 //�����м�����
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());//��������borderlayout
        
         // ����Label�������ı���
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        contentPane.add(p1, "Center");
        JLabel label = new JLabel(inform);
        label.setFont(new Font("����", Font.PLAIN, 35));
        setBounds((int)(width / 2-180* enlargement_x),(int)(height/2 ),
        		(int)(640*enlargement_x),(int)(320*enlargement_y));
        p1.add(label);
        
        //���Ӱ�ť
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.CENTER));  
        contentPane.add(p2, "South");
        
        setButton = new JButton("ȷ ��");        
        setButton.setFont( new Font("����", Font.PLAIN,(int)(20*enlargement_x)) );
        
        setButton.setPreferredSize(new Dimension((int)(85*enlargement_x),(int)(40*enlargement_y)));
        p2.add(setButton);
        
        denyButton = new JButton("��");
        denyButton.setFont( new Font("����", Font.PLAIN,(int)(20*enlargement_x)) );
       
        denyButton.setPreferredSize(new Dimension((int)(85*enlargement_x),(int)(40*enlargement_y)));
        p2.add(denyButton);    
        
        cancelButton = new JButton("ȡ��");
        cancelButton.setFont( new Font("����", Font.PLAIN,(int)(20*enlargement_x)) );
       
        cancelButton.setPreferredSize(new Dimension((int)(85*enlargement_x),(int)(40*enlargement_y)));
        p2.add(cancelButton);            
        // �����Ի��򲼾ִ�С
        pack();
        setVisible(true);  
        setButton.addActionListener(this);//��������
        denyButton.addActionListener(this);//��������
        cancelButton.addActionListener(this);//��������
//   	 try {
//		 if(result==-1)
//		//	 wait();
//			 Thread.sleep(3000);//����3000ms
//    } catch (Exception e1) {
//        e1.printStackTrace();
//    }
    }
    /**
     * �¼�����
     */
    public void actionPerformed(ActionEvent event) {    
        Object source = event.getSource();         
        if ((source == setButton))
        {
        	this.result=0;
        	dispose();
            return;
        }  
        else if(source==denyButton)
        {
        	this.result=1;
        	dispose();
        	return;
        }        
        else
        {
        	this.result=2;
        	dispose();
        	return;
        }
    }
    
    public synchronized int getResult() 
    {   	
//    	if(this.result==-1)
//    	{
//    		try {
//				wait();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		notifyAll();
//    		//return;
//    	}
		return result;
		
	}
//    public void setOnAUpdateListener(OnAUpdateListener onAUpdateListener){
//        this.onAUpdateListener = onAUpdateListener;
//    }
//
//    public interface OnAUpdateListener{
//        void updateListener(int a);
//    }
//    public void setA(int a){
//        this.a = a;
//    }
 
}