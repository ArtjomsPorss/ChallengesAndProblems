package other;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author artjoms.porss
 * 
 * PROBLEM:
 * Allocate as many 3-person families as possible. Seats must be beside each other with no gap between them.
 * Seats 1A, 2F are taken. 
 * Seats that can be 3-personfamily seats: 1DEF, 1HJK, 2ABC 
 * 
 *   ABC DEFG HJK
 * 1 XOX OOOO OOO
 * 2 OOO OOXO OOO
 * 
 * 
 * GUIDELINES:
 * Pass a number of seat rows and a string with reserved seats to a function
 * int calculation(int numRows, String seatsReserved)
 * numRows = 2,
 * seatsReserved = "1A 1C 2F";
 * The function must return 4, as 4 3-person seats can be reserved.
 * 
 */
public class AirplaneSeatReservation {
	private final String[] seatLetters = "A B C D E F G H J K".split(" ");
	private final String RESERVED = "reserved";
	private final String NOT_RESERVED = "not reserved";

	public static void main(String[] args) {
		int family3Seats = new AirplaneSeatReservation().getFree3SeatsTotal(2,"1A 1C 2F"); //MUST RETURN 4
		
		//TEST
		//int family3Seats = new AirplaneSeatReservation().getFree3SeatsTotal(6,"1A 1C 2F 3B 3J 4A 4B 4C 4F 5E 5K 6A"); //MUST RETURN 9

		System.out.println("Number of family 3-seaters: " + family3Seats);
	}

	private int getFree3SeatsTotal(int numRows, String seatsReserved){
		//Create a Map of free seats
		HashMap<String,String> seatList = initSeatList(numRows);
		//Reserve seats passed as string
		reserveSeats(seatList, seatsReserved);
		
		//Calculate free seats
		int num3Seats = 0;		
		for (int row = 1; row <= numRows; row++) {
			num3Seats += getFree3SeatsInRow(row, seatList);
		}
		
		return num3Seats;
	}

	private int getFree3SeatsInRow(int row, HashMap<String, String> seatList) {
		int num3seatsInRow = 0;
		
		if(hasFree3Seater(seatList,row,1,3)){
			++num3seatsInRow;
		}
		if(hasFree3Seater(seatList,row,4,6) || hasFree3Seater(seatList,row,5,7)){
			++num3seatsInRow;
		}
		if(hasFree3Seater(seatList,row,8,10)){
			++num3seatsInRow;
		}
		
		return num3seatsInRow;
	}

	private boolean hasFree3Seater(HashMap<String, String> seatList, int row, int start, int end) {
		
		for (int currentSeat = start; currentSeat <= end; currentSeat++) {
			String current = seatList.get(row+seatLetters[currentSeat-1]);
			if(current == null || current == RESERVED){
				return false;
			}
		}
		
		return true;
	}

	private void reserveSeats(HashMap<String, String> seatList, String seatsReserved) {
		for (String reservedSeat : Arrays.asList(seatsReserved.split(" "))) {
			seatList.replace(reservedSeat, NOT_RESERVED, RESERVED);			
		}		
	}

	private HashMap<String,String> initSeatList(int numRows) {
		
		HashMap<String,String> seatList = new HashMap<>();
		
		for (int row = 1; row <= numRows; row++) {
			for (int seat = 0; seat < 10; seat++) {
				seatList.put(row+seatLetters[seat], NOT_RESERVED);
			}
		}
		
		return seatList;
	}

}
