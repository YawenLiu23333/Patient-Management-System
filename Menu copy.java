
package project1;

//import the linked list class
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
//import the scanner class
import java.util.Scanner;

import javax.sound.midi.SysexMessage;

public class Menu {

    List patientList = new List();

    public Menu(String fileName) {
        // open file, read data, pass into linked list object
        try {
            File patientData = new File(fileName);
            Scanner myReader = new Scanner(patientData);
            String line = "";
            String[] data;
            String date[];
            while (myReader.hasNextLine()) {
                line = myReader.nextLine();

                // System.out.println(line);
                data = line.split("\\|");

                // System.out.println(data[0]);
                // System.out.println(data[1]);
                // System.out.println(data[2]);
                // System.out.println(data[3]);
                // System.out.println(data[4]);
                // System.out.println(data[5]);
                // System.out.println(data[6]);
                // System.out.println(data[7]);

                Patient patient = new Patient();
                patient.setName(data[0]);
                patient.setID(data[1]);
                patient.setAddress(data[2]);
                patient.setHeight(Integer.parseInt(data[3]));
                patient.setWeight(Double.parseDouble(data[4]));
                date = data[5].split("-");
                patient.setDOB(
                        LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])));
                date = data[6].split("-");
                // patient.getIni(data[6]);
                patient.setIniVisit(
                        LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])));
                // patient.getLast(data[7]);
                date = data[7].split("-");
                patient.setLastVisit(
                        LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])));
                patientList.add(patient);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    // display() will display all the patients in the list
    public void displayList() {
        if (!patientList.isEmpty()) {
            System.out.println(patientList.toString());
        }
    }

    public void addNewPatient(Scanner in) {

        Patient newPatient = new Patient();
        // Scanner in = new Scanner(System.in);
        System.out.println("Please enter the patient's ID");
        String id = in.next();
        if (patientList.contain(id)) {
            System.out.println("Patient already exists! Please try again.");
            return;
        }
        newPatient.setID(id);

        System.out.println("Please enter the patient's Name");
        String name = in.next();
        newPatient.setName(name);

        System.out.println("Please enter the patient's Address");
        String address = in.next();
        address += in.nextLine();
        newPatient.setAddress(address);

        System.out.println("Please enter the patient's height in inches");
        int height = in.nextInt();
        newPatient.setHeight(height);

        System.out.println("Please enter the patient's weight");
        double weight = in.nextDouble();
        newPatient.setWeight(weight);

        System.out.println("Please enter the patient's Date of Birth in mm-dd-yyyy format");
        String line = in.next();
        line += in.nextLine();
        String[] date = line.split("-");
        newPatient
                .setDOB(LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[0]), Integer.parseInt(date[1])));

        System.out.println("Please enter the patient's Date of Initial Visit in mm-dd-yyyy format");
        line = in.next();
        line += in.nextLine();
        date = line.split("-");
        newPatient.setIniVisit(
                LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[0]), Integer.parseInt(date[1])));

        System.out.println("Please enter the patient's Date of Last Visit in mm-dd-yyyy format");
        line = in.next();
        line += in.nextLine();
        date = line.split("-");
        newPatient.setLastVisit(
                LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[0]), Integer.parseInt(date[1])));
        patientList.add(newPatient);
    }

    public void displayPatient(String id) {
        Patient patient = patientList.get(id);
        if (patient == null) {
            System.out.println("Patient does not exist! Please try again.\n");
            return;
        }
        String patientString = "Patient: \n";
        patientString = patientString + "Name: " + patient.getName() + "\n";
        patientString = patientString + "ID: " + patient.getID() + "\n";
        patientString = patientString + "Address: " + patient.getAddress() + "\n";
        int height = patient.getHeight();
        patientString = patientString + "Height: " + height / 12 + "ft" + height % 12 + "in" + "\n";
        patientString = patientString + "Weight: " + patient.getWeight() + "\n";
        patientString = patientString + "Age: " + patient.getAge() + "\n";
        patientString = patientString + "Time as Patient: " + patient.get_time_as_patient() + "\n";
        int lastVisit = patient.get_time_since_last_visit();
        patientString = patientString + "Time since Last Visit: " + lastVisit + "\n";
        if (lastVisit >= 3) {
            patientString = patientString + "Patient is overdue for a visit! \n";
        }

        System.out.println(patientString);
    }

    public void deletePatient(String id) {
        boolean delete = patientList.remove(id);
        if (!delete) {
            System.out.println("Patient does not exist! Please try again.\n");
        }
    }

    public void averageAge() {

        patientList.reset();
        Patient patient = patientList.getNext();
        int count = 0;
        double averageAge = 0;
        // add all patient ages together
        while (count < patientList.size()) {
            averageAge += patient.getAge();
            patient = patientList.getNext();
            count++;
        }
        // divides sum of ages by number of patients
        System.out.printf("%.1f \n", averageAge / patientList.size());
    }

    public void displayYoungest() {

        patientList.reset();
        Patient patient = patientList.getNext();
        Patient youngest = patient;
        int age = patient.getAge();
        int count = 0;
        // iterates through list to find youngest patient by age
        while (count < patientList.size()) {
            if (patient.getAge() < age) {
                youngest = patient;
                age = patient.getAge();
            }
            patient = patientList.getNext();
            count++;
        }
        displayPatient(youngest.getID());
    }

    public void displayOverdue() {

        patientList.reset();
        Patient patient = patientList.getNext();
        int overdue;
        int count = 0;
        String listString = "";
        //iterates through list to find patients whose last visit is equal to or more than 3 years
        while (count < patientList.size()) {
            overdue = patient.get_time_since_last_visit();
            if (overdue >= 3) {
                listString = listString + patient.getName() + ", " + patient.getID() + "\n";
            }
            patient = patientList.getNext();
            count++;
        }
        System.out.println(listString);
    }

    public void saveData(String fileName) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            patientList.reset();
            int count = 0;
            Patient patient = patientList.getNext();
            while (count < patientList.size()) {
                myWriter.write(patient.getName() + "|" +
                        patient.getID() + "|" +
                        patient.getAddress() + "|" +
                        patient.getHeight() + "|" +
                        patient.getWeight() + "|" +
                        patient.getDOB() + "|" +
                        patient.getIni() + "|" +
                        patient.getLast() + "\n");
                patient = patientList.getNext();
                count++;
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void chooseOption() {
        System.out.println("1. Display list");
        System.out.println("2. Add a new patient");
        System.out.println("3. Show information for a patient");
        System.out.println("4. Delete a patient");
        System.out.println("5. Show average patient age");
        System.out.println("6. Show information for the youngest patient");
        System.out.println("7. Show notification list");
        System.out.println("8. Quit");
    }

    public void runProgram() {
        boolean run = true;

        Scanner in = new Scanner(System.in);
        String id;
        int option;
        while (run) {
            chooseOption();
            option = in.nextInt();
            //switch case for options
            //added empty println for readability in console
            switch (option) {
                case 1:
                    System.out.println();
                    displayList();
                    break;
                case 2:
                    addNewPatient(in);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("\nPlease enter patient ID");
                    id = in.next();
                    System.out.println();
                    displayPatient(id);
                    break;
                case 4:
                    System.out.println("\nPlease enter patient ID");
                    id = in.next();
                    deletePatient(id);
                    break;
                case 5:
                    System.out.println();
                    averageAge();
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    displayYoungest();
                    break;
                case 7:
                    System.out.println();
                    displayOverdue();
                    break;
                case 8:
                    System.out.println("\nWould you like to save patient information to a file? Y or N");
                    String answer = in.next();
                    if (answer.equals("Y")) {
                        System.out.println("Please input file name");
                        answer = in.next();
                        try {
                            File outputFile = new File(answer + ".txt");
                            while (!outputFile.createNewFile()) {
                                System.out.println("File already exists. Please enter a new file name");
                                answer = in.next();
                                outputFile = new File(answer + ".txt");
                            }
                            System.out.println("File created: " + outputFile.getName());
                            saveData(answer + ".txt");
                        } catch (Exception e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                    }
                    in.close();
                    run = false;
            }
        }
    }

    public static void main(String args[]) {
        Menu menu = new Menu(args[0]);
        menu.runProgram();

    }

}
