import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

interface IFrameBuilder
{    
    void buildButtonPanel();
    void buildDisplayPanel();
    JFrame buildAppFrame();
}


public class CalculatorFrameBuilder implements IFrameBuilder 
{
	TopPanel calcPnl;
	JPanel btnPnl;
	
	@Override
	public void buildButtonPanel() 
	{
		JButton[] buttons = new JButton[19];
		String[] buttonText = new String[]{"C", "/", "*", "<-", "7", "8", "9", "-", "4", "5",
	            "6", "+", "1", "2", "3", "=", "%", "0", "." };
		int counter = 0;
		btnPnl = new JPanel();
		btnPnl.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(2, 2, 2, 2);
		
		for(int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton(buttonText[i]);
			
			buttons[i].addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					String cmd = e.getActionCommand();
					
					if(cmd.charAt(0) == 'C')
						calcPnl.clear();
					else if(cmd.charAt(0) == '<')
						calcPnl.retreivePrevCalc();
					else if(cmd.charAt(0) == '=')
						calcPnl.performCalc();
					else
						calcPnl.addText(cmd.charAt(0));
				}	
				
			});
		}
		
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                    gbc.gridx = j;
                    gbc.gridy = i;
                    btnPnl.add(buttons[counter++], gbc);
            }
        }
        gbc.gridx = 0;
        gbc.gridy = 3;
        btnPnl.add(buttons[counter++], gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        btnPnl.add(buttons[counter++], gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        btnPnl.add(buttons[counter++], gbc);
        gbc.gridx = 3;
        gbc.gridy = 3;  
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        btnPnl.add(buttons[counter++], gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        btnPnl.add(buttons[counter++], gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        btnPnl.add(buttons[counter++], gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;
        btnPnl.add(buttons[counter++], gbc);
		
	}

	@Override
	public void buildDisplayPanel() 
	{
		calcPnl = new TopPanel();		
	}
	
	@Override
	public JFrame buildAppFrame() 
	{
		JFrame fr = new JFrame("Calculator");
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setSize(200, 250);
		fr.add(btnPnl, BorderLayout.CENTER);
		fr.add(calcPnl, BorderLayout.NORTH);
		fr.setVisible(true);
		return fr;
	}
	
	class TopPanel extends JPanel
	{
		String prevCalc;
		String currentCalc;
		boolean hasOperator;
		JTextField operation;
		
		public TopPanel()
		{
			super();
			prevCalc = "";
			currentCalc = "";
			hasOperator = false;
			this.setLayout(new BorderLayout(2, 2));
			operation = new JTextField();
			operation.setEditable(false);
			this.add(operation, BorderLayout.NORTH);
		}
		
		public void performCalc()
		{
			double calc = 0;
			
			int endFirstNum = currentCalc.indexOf(" ");
			int beginSecondNum = currentCalc.substring(endFirstNum + 1).indexOf(" ") + endFirstNum + 1;
			double firstNum = Double.valueOf(currentCalc.substring(0, endFirstNum ));
			double secondNum = Double.valueOf(currentCalc.substring(beginSecondNum));
			
			
			switch(currentCalc.charAt(endFirstNum + 1))
			{
			case '/':
				calc = firstNum / secondNum;
				break;
			case '*':
				calc = firstNum * secondNum;
				break;
			case '-':
				calc = firstNum - secondNum;
				break;
			case '+':
				calc = firstNum + secondNum;
				break;
			default:
				calc = firstNum % secondNum;
				break;
			}
			
			prevCalc = currentCalc;
			currentCalc = "" + calc;
			
			hasOperator = false;
			updateTextBox();
		}
		
		public void retreivePrevCalc()
		{
			if(prevCalc.isEmpty())
				return;
			
			currentCalc = prevCalc;
			checkOperator();
			updateTextBox();
		}
		
		public void clear()
		{
			currentCalc = "";
			updateTextBox();
		}
		
		public void addText(char buttonValue)
		{
			switch(buttonValue)
			{
			case '*':
				if(hasOperator)
					return;
				
				if(currentCalc.isEmpty())
					return;
				
				currentCalc += " " + buttonValue + " ";
				break;
			case '/':
				if(hasOperator)
					return;
				
				if(currentCalc.isEmpty())
					return;
				
				currentCalc += " " + buttonValue + " ";
				break;
			case '+':
				if(hasOperator)
					return;
				
				if(currentCalc.isEmpty())
					return;
				
				currentCalc += " " + buttonValue + " ";
				break;
			case '-':
				if(hasOperator)
					return;
				
				if(currentCalc.isEmpty())
					return;
				
				if(currentCalc.isEmpty())
					return;
				
				currentCalc += " " + buttonValue + " ";
				break;
			case '%':
				if(hasOperator)
					return;
				
				if(currentCalc.isEmpty())
					return;
				
				currentCalc += " " + buttonValue + " ";
				break;
			default:
				currentCalc += buttonValue;
			}
			
			checkOperator();
			updateTextBox();
		}
		
		private void checkOperator()
		{
			if(currentCalc.contains(" "))
				hasOperator = true;
			else
				hasOperator = false;
		}
		
		private void updateTextBox()
		{
			operation.setText(currentCalc);
		}
		
	}
	
}
