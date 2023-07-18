package com.question._2;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String logFile = "C:\\Users\\Lenovo\\Desktop\\spring\\Simply360\\Simply360Assignment\\src\\com\\question\\_2\\trace.log";
		String targetJob = "J1";
		String targetTimestamp = "2023-06-29 04:04:04";

		try (Scanner scanner = new Scanner(new FileReader(logFile))) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date targetDate = dateFormat.parse(targetTimestamp);
			boolean found = false;

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(" ", 4);
				String logTimestamp = parts[0] + " " + parts[1];

				if (parts.length == 4 && parts[2].equals(targetJob)) {
					Date logDate = dateFormat.parse(logTimestamp);
					if (logDate.after(targetDate)) {
						System.out.println(line);
						found = true;
						break;
					}
				}
			}

			if (!found) {
				System.out.println("No log entry found for " + targetJob + " after " + targetTimestamp);
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
}
