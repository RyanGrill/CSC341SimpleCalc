
public class Calculator 
{
	public static void main(String[] args) 
	{
		IFrameBuilder frameBuilder = new CalculatorFrameBuilder();
		frameBuilder.buildButtonPanel();
		frameBuilder.buildDisplayPanel();
		frameBuilder.buildAppFrame();
	}
}


