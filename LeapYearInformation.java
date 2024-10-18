import java.util.Scanner;
/**
 * This program ask user to input a year greater than 1582, then decide if it is a leap year and displays the inforamtin about the lear year.
 */

public class LeapYearInformation {

    /*
     * This main method asks user to enter a year, validate the input, invokes method to determine if it is a leap year. When it is a leap year, it invokes other menthods to determine which is the day of the week, and the next leap year when it occurs.
     */
    public static void main(String[] args){
        //Initiate the constants
        final int MONTH = 2;
        final int DAY = 29;

        Scanner scan = new Scanner(System.in);
        int year;
        boolean repeat = true;

        do{
            //Prompt user for year until the input is valid
            do{
                System.out.print("\nPlease enter a year greater than 1582: ");
                year = scan.nextInt();
            }while(year <= 1582);
            
            //Invoke the checkLeapYear() method in the if statement
            if(checkLeapYear(year)){
                
                System.out.println("Year " + year + " is a leap year");

                //Invoke the dayOfWeek() method and assign the returned value to dayNum
                int dayNum = dayOfWeek(year, MONTH, DAY);

                //Invoke the nameOfDay() method to get the name of Day 
                String nameOfDay = nameOfDay(dayNum);
                System.out.println("Feb 29, " + year + " is on " + nameOfDay);

                //Invoke the nextLeapYear() method to find the next leap year 
                int nextLeapYear = nextLeapYear(year, MONTH, DAY, dayNum);
                System.out.println("The next leap year where Feb 29 is on " + nameOfDay + " is year " + nextLeapYear);

            }else{
                System.out.println("Year " + year + " is not a leap year");
            }

            System.out.print("Do you want to repeat the program: ");
            String userInput = scan.next();
            repeat = userInput.equalsIgnoreCase("yes");
                
        }while(repeat);

        System.out.println("Program terminates");

    }

    //A method that checks if the year is leap year
    public static boolean checkLeapYear(int year){

        return (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0);

    }

    //A method that returns the number of day on the day 29 Feb of that leap year
    public static int dayOfWeek(int year, int month, int day){
        int startMonth, startYear, leapFactor;
        if(month < 3){
            startMonth = 0;
            startYear = year - 1;
        }else{
            startMonth = (int)(0.4 * month + 2.3);
            startYear = year;
        }
        leapFactor = (startYear / 4) - (startYear / 100) + (startYear / 400);
        return ((365 * year + 31 * (month - 1) + day + leapFactor - startMonth) - 1) % 7;
    }

    //A method convert the day number to the name of the day
    public static String nameOfDay(int dayNum){
        switch(dayNum){
            case 1: 
                return "Monday"; 
            case 2: 
                return "Tuesday";    
            case 3: 
                return "Wednesday";
            case 4: 
                return "Thursday"; 
            case 5: 
                return "Friday"; 
            case 6: 
                return "Saturday"; 
            default: 
                return "Sunday";
        }    
    }

    //A method that decides the next leap year where Feb 29 is on that day of week
    public static int nextLeapYear(int year, int month, int day, int dayNum){
       
        year++; //Check from the next year
        //While loop to find the year
        while(true){

            //Find the next leap year
            if(checkLeapYear(year)){
                //If it satisfies the condition, return the year
                if(dayOfWeek(year, month, day) == dayNum){
                    return year;
                }
            }
            year++; //Year increments 
        }
    }
}