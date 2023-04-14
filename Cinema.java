package cinema;

import java.util.Scanner;

public class Cinema {
    static String[][] cinema;
    static final String SEAT = "S";
    static final String RESERVED_SEAT = "B";
    static final int MAX_SEATS = 60;
    static final int TICKET_NORMAL_PRICE = 10;
    static final int TICKET_LOW_PRICE = 8;
    static int ticketsFinalPrice = 0;
    static int N_ROW;
    static int N_SEATS;
    static int numberOfTickets = 0;
    static float percentageOfCinema = 0;
    static int currentIncome = 0;
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        createCinema();
    }
    public static void createCinema () {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        N_ROW = rows;
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        N_SEATS = seats;
        cinema = new String[rows+1][seats+1];

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (i == 0 && j == 0) {
                    cinema[i][j] = " ";
                } else if (i == 0) {
                    cinema[i][j] = Integer.toString(j);
                } else if (j == 0) {
                    cinema[i][j] = Integer.toString(i);
                } else {
                    cinema[i][j] = SEAT;
                }
            }
        }
        printMenu();
    }
    public static void printCinema(final String[][] cinema) {
        System.out.println("\nCinema:");
        for (String[] row : cinema) {
            for (String column : row) {
                System.out.print(column +" ");
            }
            System.out.println();
        }
    }
    public static void printMenu () {
            System.out.println("""
                                    
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit""");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    printCinema(cinema);
                    System.out.println();
                    printMenu();
                    break;
                case 2:
                    ticketPrice(N_ROW);
                    System.out.println();
                    printMenu();
                    break;
                case 3:
                    System.out.printf("Number of purchased tickets: %d\n", numberOfTickets);
                    System.out.printf("Percentage: %.2f%%\n",(float)(numberOfTickets)/(N_ROW*N_SEATS)*100);
                    System.out.println("Current income: $" + currentIncome);
                    calculateTotalPossibleIncome(N_ROW,N_SEATS);
                    printMenu();
                case 0:
                    return;
                default:
                    System.out.println("Invalid input, Please try again");
                    printMenu();
                    break;
            }
        }
    public static void ticketPrice(final int rows) {
        int rowNumber;
        int seatNumber;
        boolean isValid = false;

        while (!isValid) {
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
            if (rowNumber < 1 || rowNumber > N_ROW || seatNumber < 1 || seatNumber > N_SEATS) {
                System.out.println("Wrong input");
            } else if (cinema[rowNumber][seatNumber].equals(RESERVED_SEAT)) {
                System.out.println("That ticket has already been purchased!");
            } else {
                cinema[rowNumber][seatNumber] = RESERVED_SEAT;
                int ticketCost = 0;
                if (N_ROW*N_SEATS <=60) {
                    ticketCost = 10;
                }
                    else if (rowNumber <= N_ROW / 2) {
                        ticketCost = TICKET_NORMAL_PRICE;
                        ticketsFinalPrice += ticketCost;
                    } else {
                        ticketCost = TICKET_LOW_PRICE;
                        ticketsFinalPrice += ticketCost;
                    }
                    System.out.println("Ticket price: $" + ticketCost);
                    numberOfTickets += 1;
                    currentIncome +=ticketCost;
                    isValid = true;
                }
                {

                }
            }
        }
    public static void calculateTotalPossibleIncome (
            int N_ROW, int N_SEATS){
        int totalSeats = N_ROW*N_SEATS;
        if (totalSeats <= 60) {
            System.out.println("Total income: $" + totalSeats * TICKET_NORMAL_PRICE);
        } else {
            int frontHalf = ((N_ROW/2) * N_SEATS) * TICKET_NORMAL_PRICE;
            int backHalf = ((N_ROW - N_ROW / 2) * N_SEATS) * TICKET_LOW_PRICE;
            System.out.println("Total income: $" + (frontHalf + backHalf));
        }
    }
}
