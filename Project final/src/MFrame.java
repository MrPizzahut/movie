import java.awt.*;
import java.awt.Toolkit;
import javax.swing.*;

public class MFrame extends JFrame {
	
   public MFrame() {
      this.setSize(1100,800);
     
      // 화면 중앙 처리
      Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension di1 = this.getSize();
      setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
            (int) (di.getHeight() / 2 - di1.getHeight() / 2));
      
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false);   
   }

}