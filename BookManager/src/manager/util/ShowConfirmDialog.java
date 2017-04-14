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
    
    JButton setButton; //确定 
    JButton denyButton;//否定
    JButton cancelButton;//取消

	private int result;//返回按什么按钮：是-0；否-1；取消-2
  
    ShowConfirmDialog()
    {
    	result=-1;    	
    }
    public  ShowConfirmDialog(JFrame prentFrame,String title , String inform)
    {
    	
    	// 调用父类的构造函数，
        super(prentFrame, title, false); // 第三个参数用false表示允许激活其他窗体。为true表示不能够激活其他窗体       
        result=-1;
        //开启一个线程
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
    	 //建立中间容器
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());//主容器用borderlayout
        
         // 添加Label和输入文本框
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        contentPane.add(p1, "Center");
        JLabel label = new JLabel(inform);
        label.setFont(new Font("宋体", Font.PLAIN, 35));
        setBounds((int)(width / 2-180* enlargement_x),(int)(height/2 ),
        		(int)(640*enlargement_x),(int)(320*enlargement_y));
        p1.add(label);
        
        //添加按钮
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.CENTER));  
        contentPane.add(p2, "South");
        
        setButton = new JButton("确 定");        
        setButton.setFont( new Font("宋体", Font.PLAIN,(int)(20*enlargement_x)) );
        
        setButton.setPreferredSize(new Dimension((int)(85*enlargement_x),(int)(40*enlargement_y)));
        p2.add(setButton);
        
        denyButton = new JButton("否定");
        denyButton.setFont( new Font("宋体", Font.PLAIN,(int)(20*enlargement_x)) );
       
        denyButton.setPreferredSize(new Dimension((int)(85*enlargement_x),(int)(40*enlargement_y)));
        p2.add(denyButton);    
        
        cancelButton = new JButton("取消");
        cancelButton.setFont( new Font("宋体", Font.PLAIN,(int)(20*enlargement_x)) );
       
        cancelButton.setPreferredSize(new Dimension((int)(85*enlargement_x),(int)(40*enlargement_y)));
        p2.add(cancelButton);            
        // 调整对话框布局大小
        pack();
        setVisible(true);  
        setButton.addActionListener(this);//关联动作
        denyButton.addActionListener(this);//关联动作
        cancelButton.addActionListener(this);//关联动作
//   	 try {
//		 if(result==-1)
//		//	 wait();
//			 Thread.sleep(3000);//运行3000ms
//    } catch (Exception e1) {
//        e1.printStackTrace();
//    }
    }
    /**
     * 事件处理
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
