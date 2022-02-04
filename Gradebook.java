import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Gradebook 
{
	
	// rounds to second decimal-- i did it with Math.round first but then it got weird
	public static double round2(double num) 
	{
		boolean positive = true;
		if (num < 0)
		{
			positive = false;
			num = -num;
		}
		
		double inflated = num * 1000;
		double extra = inflated % 10;

		if (extra >= 5) 
		{

			inflated += 10;

		}

		int restored = (int) (inflated / 10);
		double result = (double) restored / 100;
		
		if (false == positive)
		{
			result = -result;
		}
		
		return result;
	}

	
	// method for finding the homework score
	public static double hwScore(Scanner sc)
	{
		int assignNum = Integer.parseInt(sc.nextLine());
		double totPoints = 0;
		
		for (int i = 0; i < assignNum; i++)
		{
			totPoints += Double.parseDouble(sc.nextLine());
		}
			
		double outOf = 0;
				
		// adds up total points possible
		for (int i = 0; i < assignNum; i++)
		{
			outOf += Double.parseDouble(sc.nextLine());
		}
				
		double avgHW = round2((totPoints/outOf) * 100);
		
		return(avgHW);
		
	}
	
	// method for finding scores for midterm and final
	public static double testScore(Scanner sc)
	{
		double pointsEarned = Double.parseDouble(sc.nextLine());
		double pointsPossible = Double.parseDouble(sc.nextLine());
		double testScore = round2((pointsEarned/pointsPossible) * 100);
		return testScore;
		
	}
	
	// method for finding letter grade
	public static String letterGrade(double x)
	{
		String finalGrade = "";
		
		if (x < 60)
		{
			finalGrade = "F";
		}
		else if (x < 70)
		{
			finalGrade = "D";
		}
		else if (x < 80)
		{
			finalGrade = "C";
		}
		else if (x < 90)
		{
			finalGrade = "B";
		}
		else if (x > 90)
		{
			finalGrade = "A";
		}
		
		return finalGrade;
	}
	
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException
	{
		/*The first couple lines create a new File object in Java that we'll 
		 * tell the Scanner to look at (instead of System.in)  
		 * Essentially, our trap door now opens onto that file.  
		 * If the file were not in the project folder, you would give the path instead of 
		 * just the file name:  "C:\Users\...\gradebookData.txt"
		 * 
		 * The next couple lines are just to prove you've got that file in the right place
		 * in your workspace.  They just read in the first line of the file. 
		 * Once it's working, you can delete the print statement.  
		 */
		

		//initializing
		double avgHW = 0;
		double midterm = 0;
		double Final = 0;
		String studentName = "";
		String assign = "";
		
		
		File inputFile = new File("gradebookData.txt");  
		Scanner input = new Scanner(inputFile);
		
	
		while (!(studentName.equals("end")))
		{	
			studentName = input.nextLine();
			assign = input.nextLine();
		
			// Gets value of HW
			if (assign.equals("HW"))
			{
				avgHW = hwScore(input);		
						
			}
			
			assign = input.nextLine();
			
			
			
			//Gets value of Midterm
			if (assign.equals("Midterm"))
			{
				midterm = testScore(input);
			}
			
			assign = input.nextLine();
			
		
			//Gets value of Final
			if (assign.equals("Final"))
			{
				Final = testScore(input);
			}
			
			double finalGrade = (0.5 * avgHW) + (0.2 * midterm) + (0.3 * Final);
			finalGrade = round2(finalGrade);

			//prints out all grades
			System.out.println(studentName);
			System.out.println("Overall = " + finalGrade + "%, " + letterGrade(finalGrade));
			System.out.println("Homework = " + avgHW + "%");
			System.out.println("Midterm = " + midterm + "%");
			System.out.println("Final Exam = " + Final + "%");
			System.out.println();
			
			
		}
			
		/* What kind of loop is going to go here to read in all the data?
		 * Keep in mind that the data file you are using is STRUCTURED the same way
		 * as the data file I'll use to grade you, but I'm planning to have more students,
		 * more assignments, etc.
		 */ 
		
		
		
		input.close();
	}
}