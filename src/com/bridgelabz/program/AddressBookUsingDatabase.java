package com.bridgelabz.program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.connection.JDBC;

public class AddressBookUsingDatabase {
	Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		
		
		AddressBookUsingDatabase addressBook = new AddressBookUsingDatabase();
		JDBC jdbcConnection  = new JDBC();	
		Connection connection  = jdbcConnection.connection();
	//	addressBook.showAddressBook(connection);
	//	addressBook.updateContactDetails(connection);
	//	addressBook.AddColumn(connection);
	//	addressBook.showEntryInSelectedDate(connection) ;
	//	addressBook.showDataByState(connection) ;
		addressBook.insertNewContact(connection);
	}

	private void showAddressBook(Connection connection) {
		
		try {
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery("Select * from Addressbook");
			while(set.next()) {
				System.out.println(set.getInt(1) +" " + set.getString(2) +" " + set.getString(3) +" " + set.getString(4) +" " + set.getString(5) +" " + set.getString(6) +" " +
						set.getInt(7) +" " + set.getString(8) +" " + set.getString(9) +" " + set.getString(10) );
			}
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void updateContactDetails(Connection connection) {
		System.out.println("Enter key to update 1.Update City 2.Update State 3.Update Zip 4.Update phoneNumber 5.update Date");
		int choice = sc.nextInt();
		switch(choice) {
		case 1 : 
			updateCity(connection);
			break;
		case 2 : 
			updateState(connection);
			break;
		case 3 : 
			updateZip(connection);
			break;
		case 4 : 
			updatePhoneNumber(connection);
			break;
		case 5 : 
			updateDate(connection);
			break;	
			
		default :
			System.out.println("Wrong Input try again!!");
			break;
		}
	}

	private void updatePhoneNumber(Connection connection) {
		System.out.println("Enter the PhoneNumber ");
		String number = sc.next();
		System.out.println("Enter the bookId");
		int bookId = sc.nextInt();
	
		String query = "update AddressBook Set Phonenumber = ? where bookId = ?"; 
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, number);
			ps.setInt(2, bookId);
			ps.execute();
			ps.close();
			connection.close();
			System.out.println("PhoneNumber updated");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}


	private void updateCity(Connection connection) {
		System.out.println("Enter the City ");
		String city = sc.next();
		System.out.println("Enter the bookId");
		int bookId = sc.nextInt();
	
		String query = "update AddressBook Set city = ? where bookId = ?"; 
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, city);
			ps.setInt(2, bookId);
			ps.execute();
			ps.close();
			connection.close();
			System.out.println("City updated");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}

	private void updateState(Connection connection) {
		System.out.println("Enter the State ");
		String state = sc.next();
		System.out.println("Enter the bookId");
		int bookId = sc.nextInt();
	
		String query = "update AddressBook Set state = ? where bookId = ?"; 
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, state);
			ps.setInt(2, bookId);
			ps.execute();
			ps.close();
			connection.close();
			System.out.println("State updated");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	
	private void updateZip(Connection connection) {
		System.out.println("Enter the Zip ");
		 int zip = sc.nextInt();
		System.out.println("Enter the bookId");
		int bookId = sc.nextInt();
	
		String query = "update AddressBook Set zip = ? where bookId = ?"; 
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, zip);
			ps.setInt(2, bookId);
			ps.execute();
			ps.close();
			connection.close();
			System.out.println("Zip updated");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	

	private void updateDate(Connection connection) {
		System.out.println("enter the Entry date");
		String date = sc.next();
		System.out.println("enter the book id");
		int bookId = sc.nextInt();
		
		String query = "update AddressBook Set entryDate = ? where bookId = ? ";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, date);
			ps.setInt(2, bookId);
			ps.execute();
			ps.close();
			connection.close();	
			System.out.println("date updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	private void AddColumn(Connection connection) {
		
		Statement statement = null ; 
		
		try {
			statement = connection.createStatement();
			statement.execute("Alter table AddressBook Add column entryDate date after bookId");
			statement.close();
			connection.close();
			System.out.println("Column added");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
		
	private void showEntryInSelectedDate(Connection connection) {
		
		System.out.println("Enter the Start date ");
		String date = sc.next();
		System.out.println("Enter the End date ");
		String endDate = sc.next();
		
		String query = " select * from AddressBook Where entrydate between ? and ?  ";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, date);
			ps.setString(2,endDate);
			ResultSet set = ps.executeQuery();
			while(set.next()) {
				System.out.println(set.getInt(1) +" " + "Entered on " + set.getString(2) +" " + set.getString(3) +" " + set.getString(4) +" " + set.getString(5) +" " + set.getString(6) +" " + set.getString(7) +" " +
						set.getInt(8) +" " + set.getString(9) +" " + set.getString(10) +" " + set.getString(11) );
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}
	}
	
	private void showDataByState(Connection connection) {
		
		System.out.println("enter the state");
		String state = sc.next();
		
		String query = "Select * from AddressBook where state = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, state);
			ResultSet set = ps.executeQuery();
			while(set.next()) {
				System.out.println(set.getInt(1) +" " + "Entered on " + set.getString(2) +" " + set.getString(3) +" " + set.getString(4) +" " + set.getString(5) +" " + set.getString(6) +" " + set.getString(7) +" " +
						set.getInt(8) +" " + set.getString(9) +" " + set.getString(10) +" " + set.getString(11) );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void insertNewContact(Connection connection) {
		System.out.println("Enter the entryDate");
		String date = sc.next();
		System.out.println("Enter the firstName");
		String firstName = sc.next();
		System.out.println("Enter the lastName");
		String lastName = sc.next();
		System.out.println("Enter the address");
		String address = sc.next();
		System.out.println("Enter the city");
		String city = sc.next();
		System.out.println("Enter the state");
		String state = sc.next();
		System.out.println("Enter the zip");
		int zip = sc.nextInt();
		System.out.println("Enter the Realtive name");
		String name = sc.next();
		System.out.println("Enter the relation");
		String relation = sc.next();
		System.out.println("Enter the PhoneNumber");
		String phoneNumber = sc.next();
		String query = "Insert into AddressBook(entryDate , firstName , lastName ,address , city ,state , zip , name , relation , phoneNumber)  values(?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, date);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, address);
			ps.setString(5,city);
			ps.setString(6, state);
			ps.setInt(7, zip);
			ps.setString(8, name);
			ps.setString(9, relation);
			ps.setString(10, phoneNumber);
			ps.execute();
			ps.close();
			connection.close();
			System.out.println("Contact inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
 	
	
	

}
