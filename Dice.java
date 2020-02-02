package domain;

import java.security.SecureRandom;

public class Dice
{
	//3d20 - roll 3 20 sided dice
	public static String throwDice(String diceThrow)
	{
		//convert the string to lowercase
		diceThrow.toLowerCase();
		
		//Split the string on 'd' ->
		//If there is no d specified the array will only contain a length = 1
		String result = ""; //contains the results
		SecureRandom random = new SecureRandom();
		int amountOfSides = 6; //Default amountOfSides
		int amountOfThrows = 1; //Default amountOfThrows
		
		if(diceThrow.contains("d"))
		{
			String[] parts = diceThrow.split("d", -1); 
			//Check if parsable and the parts aren't empty
			if(!isParsable(parts[0]) && isParsable(parts[1]) && !parts[1].equals("") && !parts[0].equals(""))
				throw new IllegalArgumentException("No valid dice throw");
			amountOfSides = parts[1].equals("") ? 6 : Integer.parseInt(parts[1]); //Checks for a sided die
			amountOfThrows = parts[0].equals("") ? 1 : Integer.parseInt(parts[0]); //Checks for amount of throws
		}
		else
		{
			//Check a string without a 'd' character that isn't empty
			if(!isParsable(diceThrow) && !diceThrow.equals(""))
				throw new IllegalArgumentException("No valid dice throw");
		}
		
		//throw the dice
		for(int i = 0; i < amountOfThrows; i++)
		{
			result += String.format("%-5d",random.nextInt(amountOfSides) + 1);
		}
		return String.format("The %d dice %s with a %d-sided die resulted in:%n"
				+ "%s%n", 
				amountOfThrows,
				amountOfThrows == 1 ? "throw" : "throws",
				amountOfSides,
				result);
	}
	
	//See if the string is parsable
	private static boolean isParsable(String diceThrow)
	{
		try
		{
			Integer.parseInt(diceThrow);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
}
