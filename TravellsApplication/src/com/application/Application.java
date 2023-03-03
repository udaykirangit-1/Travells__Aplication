package com.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.admin.Admin;
import com.customexcption.CustomException;
import com.journeydetails.JourneyDetails;
import com.user.User;

public class Application {

	static Map<String, User> users = new HashMap<>();
	static Boolean logStatus = false;
	static Scanner scanner = new Scanner(System.in);
	static boolean f = true;
	static User preuser = new User("Udaykiran", "rachagolla", "9392408561", "male", "uday@gmail.com", "Kiran@1551", 0,
			"Active", "N");
	static Map<String, Admin> admins = new HashMap<>();
	static Admin admin = new Admin("admin@gmail.com", "admin");

	static Map<String, User> loggedMap = null;
	static User loggedUser = null;

	static Map<String, Integer> journeyFair = new HashMap<>();
	static ArrayList<String> sourceDestinationList = new ArrayList<>();
	static Map<String, JourneyDetails> userjourneyDetailsMap = new HashMap<>();

	static Integer counInteger = 0;

	static {
		users.put(preuser.getMail(), preuser);
		admins.put(admin.getMail(), admin);
		journeyFair.put("VSKP-RJY", 500);
		journeyFair.put("RJY-VSKP", 500);
		journeyFair.put("VSKP-HYD", 1500);
		journeyFair.put("HYD-VSKP", 1500);
		journeyFair.put("VSKP-KER", 2000);
		journeyFair.put("HYD-BAN", 2500);
		journeyFair.put("HYD-KGP", 2800);
		journeyFair.put("HYD-CHE", 2200);
		journeyFair.put("RJY-HYD", 1200);
		journeyFair.put("TIRU-HYD", 1800);
		sourceDestinationList.add("VSKP");
		sourceDestinationList.add("RJY");
		sourceDestinationList.add("HYD");
		sourceDestinationList.add("KER");
		sourceDestinationList.add("BAN");
		sourceDestinationList.add("KGP");
		sourceDestinationList.add("CHE");
		sourceDestinationList.add("TIRU");
	}

	public static void main(String[] args) throws IOException {

		logoDisplay();
		System.out.println();
		listOfActions();

		int k;
		while (f) {
			System.out.print("Please Enter your choice of action");
			k = scanner.nextInt();

			switch (k) {
			case 1:
				loggedMap = userLogin(scanner, users);
				loggedUser = getLoggedUser(loggedMap, loggedUser);
				break;
			case 2:
				userRegister(scanner, users);
				break;
			case 3:
				unblockUser();
				break;
			case 4:
				planJourney();
				break;
			case 5:
				editJourney();
				break;
			case 6:
				bookingStatus();
				break;
			case 7:
				logout();
				break;
			case 8:
				exit();
				f = false;
				break;
			default:
				System.out.println("Enter number between 1 to 7");
				break;
			}

		}

	}

	private static void exit() {
		System.out.println("Exiting from application.........");
		System.out.println("Come again Tata......");
	}

	private static void logout() {
		try {
			if (logStatus) {
				System.out.println(loggedUser.getFirstName() + " Successfully logged out");
				loggedUser.setLoggeString("N");
				logStatus = false;
				users.put(loggedUser.getMail(), loggedUser);
				System.out.println(users);
			} else {
				throw new CustomException("User is not logged in");
			}
		} catch (CustomException e) {
			System.out.println(e.getMessage());
		} finally {
			listOfActions();
		}
	}

	private static void bookingStatus() {
		try {
			if (logStatus) {
				Set<String> journeySet = userjourneyDetailsMap.keySet();
				if (!journeySet.isEmpty()) {
					System.out.println(loggedUser.getFirstName() + " please find");
					System.out.println("Booking Status of all your planned/booked/cancelled Journey's");
					for (String jSet : journeySet) {
						String s = jSet.substring(0, jSet.length() - 1);
						if (loggedUser.getMail().equals(s)) {
							System.out.println(userjourneyDetailsMap.get(jSet));
						}
					}
				} else {
					System.out.println("No Bookings/Planned Journey for the user" + loggedUser.getFirstName());
				}
			}

			else {
				throw new CustomException("-------------------Please Login------------------");
			}
		} catch (CustomException e) {
			System.out.println(e.getMessage());
		} finally {
			listOfActions();
		}
	}

	private static void editJourney() {
		System.out.println("Edit/Cancel Planned/Booked Journey " + loggedUser.getFirstName());
		try {
			if (logStatus) {
				System.out.println(loggedUser);
				System.out.println(userjourneyDetailsMap);
				Set<String> journeySet = userjourneyDetailsMap.keySet();
				if (!journeySet.isEmpty()) {
					ArrayList<String> journeyArrayList = new ArrayList<>();
					for (String jSet : journeySet) {
						String s = jSet.substring(0, jSet.length() - 1);
						if (loggedUser.getMail().equals(s)) {
							JourneyDetails journeyDetails = userjourneyDetailsMap.get(jSet);
							journeyArrayList.add(journeyDetails.getSourceDestination());
						}
					}
					System.out.println(journeyArrayList);
					System.out.println("Please enter which journey you want to edit from above");
					String sourceDestinationString = scanner.next();
					if (journeyArrayList.contains(sourceDestinationString)) {
						editPlans();
						int option = scanner.nextInt();
						switch (option) {
						case 1:
							for (String jSet : journeySet) {
								String s1 = jSet.substring(0, jSet.length() - 1);
								JourneyDetails journeyDetails = userjourneyDetailsMap.get(jSet);
								if (journeyDetails.getSourceDestination().equals(sourceDestinationString)
										&& loggedUser.getMail().equals(s1)) {
									if (userjourneyDetailsMap.get(jSet).getBookingStatus().equals("Booked")) {
										userjourneyDetailsMap.get(jSet).setBookingStatus("Cancelled");
										System.out.println("Booking Cancelled Refund amonut is initiated...");
										userjourneyDetailsMap.put(jSet, journeyDetails);
									} else if (userjourneyDetailsMap.get(jSet).getBookingStatus()
											.equals("Not Booked")) {
										userjourneyDetailsMap.get(jSet).setBookingStatus("Booked");
									}
									System.out.println(
											"Please go to Booking Status menu to check status of your each journey");
								}
							}
							break;
						case 2:
							System.out.println("Please enter the reschedule date");
							String dateRes = scanner.next();
							LocalDate localDateRes = LocalDate.parse(dateRes);
							DayOfWeek dayOfWeekRes = localDateRes.getDayOfWeek();
							String dayRes = dayOfWeekRes.toString();
							for (String jSet : journeySet) {
								String s1 = jSet.substring(0, jSet.length() - 1);
								JourneyDetails journeyDetails = userjourneyDetailsMap.get(jSet);
								if (journeyDetails.getSourceDestination().equals(sourceDestinationString)
										&& loggedUser.getMail().equals(s1)) {
									userjourneyDetailsMap.get(jSet).setDate(dateRes);
								}
							}
							System.out.println("Date of travel changed");
							System.out.println("Please go to Booking Status menu to check status of your each journey");

							break;
						case 3:
							System.out.println("Please enter the number of passengers");
							int passengers = scanner.nextInt();
							for (String jSet : journeySet) {
								String s1 = jSet.substring(0, jSet.length() - 1);
								JourneyDetails journeyDetails = userjourneyDetailsMap.get(jSet);
								if (journeyDetails.getSourceDestination().equals(sourceDestinationString)
										&& loggedUser.getMail().equals(s1)) {
									userjourneyDetailsMap.get(jSet).setNoOfPassengers(passengers);
								}
							}
							System.out.println("Number of passengers changed");
							System.out.println("Please go to Booking Status menu to check status of your each journey");
							break;

						default:
							break;
						}
					} else {
						System.out.println("No plans found");
					}
				}

				else {
					System.out.println("No Bookings/Planned Journey found for the user" + loggedUser.getFirstName());
				}
			} else {
				throw new CustomException("Please Login");
			}
		} catch (CustomException e) {
			System.out.println(e.getMessage());
		} finally {
			listOfActions();
		}
	}

	private static void editPlans() {
		System.out.println("1.Cancel/Edit booking Status of Planned Journey");
		System.out.println("2.Edit Date of Planned Journey");
		System.out.println("3.Edit Number of Passengers of Booked Journey");
		System.out.println("Please select the option from above list:");
	}

	private static void planJourney() {
		try {
			if (logStatus) {

				System.out.println("Plan Your Journey " + loggedUser.getFirstName());

				System.out.println(sourceDestinationList);
				System.out.println("Please enter the Source from above list");
				String source = scanner.next();

				System.out.println(sourceDestinationList);
				System.out.println("Please enter the Destination from above list");
				String destination = scanner.next();

				String sourDes = source.concat("-").concat(destination);

				if (journeyFair.get(sourDes) != null) {
					JourneyDetails journeyDetails = new JourneyDetails();

					journeyDetails.setSourceDestination(sourDes);
					counInteger++;
					System.out.println("Please enter the date of journey in yyyy-mm-dd format");
					String date = scanner.next();
					journeyDetails.setDate(date);

					System.out.println("Please Enter Number of Passengers");
					Integer passengers = scanner.nextInt();
					journeyDetails.setNoOfPassengers(passengers);

					LocalDate localDate1 = LocalDate.parse(date);
					DayOfWeek dayOfWeek = localDate1.getDayOfWeek();
					String day = dayOfWeek.toString();
					if (day.equals("SUNDAY") || day.equals("SATURDAY")) {
						double totalBill = journeyDetails.getNoOfPassengers() * (journeyFair.get(sourDes) + 200);
						double gst = totalBill * 10 / 100;
						double finalBill = totalBill + gst;
						journeyDetails.setBill(finalBill);
						System.out.println("Total fare: " + journeyDetails.getBill());
						booking(journeyDetails);

					} else {
						double totalBill = journeyDetails.getNoOfPassengers() * journeyFair.get(sourDes);
						double gst = totalBill * 10 / 100;
						double finalBill = totalBill + gst;
						journeyDetails.setBill(finalBill);
						System.out.println("Total fare: " + journeyDetails.getBill());
						booking(journeyDetails);
					}
					System.out.println(loggedUser.getFirstName() + " please find your Journey details");
					System.out.println(journeyDetails);
					userjourneyDetailsMap.put(loggedUser.getMail() + counInteger.toString(), journeyDetails);
					System.out.println("List of plans for the user " + loggedUser.getFirstName());
					System.out.println(userjourneyDetailsMap);
				} else {
					throw new CustomException("No routes found please try again with other source and destination");
				}

			} else {
				throw new CustomException("Please Login");
			}
		} catch (CustomException e) {

			System.out.println(e.getMessage());
		} finally {
			listOfActions();
		}
	}

	private static void booking(JourneyDetails journeyDetails) {
		System.out.println("Please enter Y/N to Continue Booking journey");
		String bookString = scanner.next();
		if (bookString.equals("Y")) {
			journeyDetails.setBookingStatus("Booked");
			System.out.println("------------------------Journey planned and Booked Successfully----------------------");
		} else if (bookString.equals("N")) {
			journeyDetails.setBookingStatus("Not Booked");
			System.out
					.println("------------------------Journey planned but not yet Booked----------------------------");
		}
	}

	private static User getLoggedUser(Map<String, User> loggedMap, User loggedUser) {
		Set<String> keySet = loggedMap.keySet();
		for (String email : keySet) {
			User user2 = loggedMap.get(email);
			if (user2.getLoggeString().equals("Y")) {
				loggedUser = loggedMap.get(email);
			}
		}
		return loggedUser;
	}

	private static void unblockUser() {
		System.out.println("Unblock Account");
		System.out.println("Please enter mail id of user whose acc should be unlocked");
		String mailLocked = scanner.next();
		User blockedUser = users.get(mailLocked);
		if (blockedUser.getAccountStatus().equals("Block")) {
			System.out.println("Please login with Admin credentials");
			System.out.println("Please enter admin mailid");
			String mailString = scanner.next();
			Admin admin = admins.get(mailString);
			if (admin != null) {
				System.out.println("Please Enter Password");
				String pString = scanner.next();
				if (admin.getPassword().equals(pString)) {
					blockedUser.setAccountStatus("Active");
					blockedUser.setFailedCount(0);
					users.put(mailLocked, blockedUser);
					System.out.println(mailLocked + " User unblocked Successfully");
				}
			}
		} else {
			System.out.println(
					"---------------------" + mailLocked + " account in active state---------------------------");
		}
		listOfActions();
	}

	private static void userRegister(Scanner scanner, Map<String, User> users) {
		if (logStatus) {

			System.out.println("------------------------------" + loggedUser.getFirstName()
					+ " Already Logged in-------------------------------");
			listOfActions();
		} else {
			System.out.println("Please Enter details to Register");
			User user = new User();
			System.out.println("Please enter mailid");
			validMail(user);
			String validMailString = user.getMail();
			try {
				if (users.get(validMailString) != null) {
					throw new CustomException("-----------------------" + validMailString
							+ " already exists please login or register with new mail-------------------------");
				} else {

					System.out.println("Please enter Firstname");
					validFirstName(user);

					System.out.println("Please enter LastName");
					validLastName(user);

					System.out.println("Please enter MobileNumber");
					validMobileNumber(user);

					System.out.println("Please enter Gender");
					validGender(user);

					System.out.println("Please set the Password");
					validPassword(user);

					user.setAccountStatus("Active");
					user.setFailedCount(0);
					user.setLoggeString("N");

					users.put(validMailString, user);
					System.out.println("----------------------------" + user.getFirstName()
							+ " Sucessfully Registered--------------------");
					System.out.println(users.get(validMailString));

				}

			} catch (CustomException e) {
				System.out.println(e.getMessage());
			} finally {
				listOfActions();
			}

		}

	}

	private static void validPassword(User user) {
		boolean flag = true;
		while (flag) {
			String passwordString = scanner.next();
			if (passwordString.matches("[A-Z]{1}[a-z]+[@$][0-9]+")) {
				flag = false;
				user.setPassword(passwordString);
			} else {
				System.out.println("Please set the Valid Password");
			}
		}
	}

	private static void validGender(User user) {
		boolean flag = true;
		while (flag) {
			String genderString = scanner.next();
			if (genderString.equals("Female") || genderString.equals("Male")) {
				flag = false;
				user.setGender(genderString);
			} else {
				System.out.println("Please enter Correct Gender");
			}
		}
	}

	private static void validMobileNumber(User user) {
		boolean flag = true;
		while (flag) {
			String numberString = scanner.next();
			if (numberString.length() == 10) {
				flag = false;
				user.setMobileNumber(numberString);
			} else {
				System.out.println("Please enter Valid MobileNumber");
			}
		}
	}

	private static void validFirstName(User user) {
		boolean flag = true;
		while (flag) {
			String firstName = scanner.next();
			if (firstName.matches("[A-Z][a-z]+")) {
				flag = false;
				user.setFirstName(firstName);
			} else {
				System.out.println("Please enter valid FirstName");

			}
		}
	}

	private static void validLastName(User user) {
		boolean flag = true;
		while (flag) {
			String lastName = scanner.next();
			if (lastName.matches("[A-Z][a-z]+")) {
				flag = false;
				user.setLastName(lastName);
			} else {
				System.out.println("Please enter valid LastName");
			}
		}
	}

	private static void validMail(User user) {
		boolean flag = true;
		while (flag) {
			String mailString = scanner.next();
			if (mailString.matches("\\w+@gmail.com")) {
				flag = false;
				user.setMail(mailString);
			} else {
				System.out.println("Please enter valid Mailid");
			}
		}

	}

	private static Map<String, User> userLogin(Scanner scanner, Map<String, User> users) {
		if (logStatus) {
			System.out.println("-------------------------------" + loggedUser.getFirstName()
					+ " Already Logged in-------------------------------");
			listOfActions();
		} else {
			System.out.println("Please Login");
			System.out.println("Please enter your Mailid");
			String usermail = scanner.next();
			try {
				User user = users.get(usermail);
				if (user != null) {
					if (user.getMail().equals(usermail)) {
						if (user.getAccountStatus().equals("Block")) {
							throw new CustomException(user.getFirstName()
									+ "----------------------Account Blocked please unblock------------------------");
						}
						System.out.println("Please Enter Password");
						String pwd = scanner.next();
						if (pwd.equals(user.getPassword())) {
							logStatus = true;
							user.setLoggeString("Y");
							System.out.println("-----------------------" + user.getFirstName()
									+ " Logged in-------------------------");
							// System.out.println("Current logged in user deatils");
							// System.out.println(user);
							users.put(usermail, user);
						} else {
							logStatus = false;
							Integer failCountInteger = user.getFailedCount();
							user.setFailedCount(failCountInteger + 1);
							if (failCountInteger == 3) {
								user.setAccountStatus("Block");
								throw new CustomException(
										"------------------------------------Wrong Password Account Blocked Please Unblock your account----------------------------");
							} else {
								throw new CustomException(
										"--------------------------Wrong Password Please try again----------------------------");
							}
						}
					}
				} else {
					throw new CustomException(
							"-----------------------------------------------User not found Please Register-----------------------------------------");
				}

			} catch (CustomException e) {
				System.out.println(e.getMessage());
			} finally {
				listOfActions();
			}
		}
		System.out.println("List of Users we have");
		System.out.println(users);
		return users;
	}

	private static void listOfActions() {
		System.out.println("*****************************************************************************************");
		System.out.println("1.User Login");
		System.out.println("2.User Registration");
		System.out.println("3.Account Unblock");
		System.out.println("4.Plan Journey");
		System.out.println("5.Edit Travel Plan");
		System.out.println("6.Booking Status");
		System.out.println("7.Logout");
		System.out.println("8.Exit from App");
		System.out.println("*****************************************************************************************");
	}

	private static void logoDisplay() throws IOException {
		try {
			File file = new File("C:\\Users\\uday kiran\\Desktop/miniFile.txt");
			// created file object
			FileInputStream fileInputStream = new FileInputStream(file);
			// using fileinput stream takes file
			// System.out.println(file.exists());
			int t;
			while ((t = fileInputStream.read()) != -1)// reading the file until there is nothing in it ie end
			{
				System.out.print((char) t);

			}
			fileInputStream.close();
		}

		catch (FileNotFoundException e) {
			System.out.println("****Logo Not Found****"); // if file is not found then this will be printed
			System.exit(0);
		}

	}
}
