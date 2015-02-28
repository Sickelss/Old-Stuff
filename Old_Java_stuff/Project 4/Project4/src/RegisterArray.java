import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class RegisterArray {
	
	public Checkout rRegisters[];
	public Checkout xRegisters[];
	
	public RegisterArray () throws FileNotFoundException
	{
		File file2 = new File("checkout.txt");
		Scanner sc2 = new Scanner(file2);
		{
			int regular = sc2.nextInt();
			rRegisters = new Checkout[regular];
			for (int i = 0; i <= regular -1; i++)
			{
				float temp1 = sc2.nextFloat();
				float temp2 = sc2.nextFloat();
				rRegisters[i] = new Checkout(temp1, temp2);
			}
			
			int xpress = sc2.nextInt();
			xRegisters = new Checkout[xpress];
			
			for (int i = 0; i <= xpress -1; i++)
			{
				float temp1 = sc2.nextFloat();
				float temp2 = sc2.nextFloat();
				xRegisters[i] = new Checkout(temp1, temp2);
			}
		}
		sc2.close();
	}
	
	
}
