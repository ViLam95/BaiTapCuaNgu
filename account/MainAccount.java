package account;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainAccount {
    private static void writeFile(List<Account> accountList){
        File f = new File("/Users/viquoclam/Documents/Java - module 2/NguOi/account/Register");
        try{
            if(!f.exists()){
                throw new FileNotFoundException();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f));
            for (Account acc: accountList) {
                bufferedWriter.write(acc.toString() + "\n");
            }
            bufferedWriter.close();
        }catch (IOException e){
            System.out.println("Error" + e.getMessage());
        }
    }
    private static void readFile(List<Account> accountList) throws IOException{
        try{
            File f = new File("/Users/viquoclam/Documents/Java - module 2/NguOi/account/Register");
            FileReader fileReader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine())!= null){
                String[] ch =  line.split(",");
                accountList.add (new Account(Integer.parseInt(ch[0]),ch[1],ch[2],ch[3],ch[4],ch[5]));

            }
            bufferedReader.close();
            fileReader.close();
        }catch (IOException e){
            System.out.println("Error" + e.getMessage());
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<Account> accountList = new ArrayList<>();
        readFile(accountList);
        int choice;
        do{
            System.out.println("==========  Please choose your choice!  ==========");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Quit");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    System.out.println("Enter username:");
                    String username = scanner.nextLine();
                    System.out.println("Enter password:");
                    String password = scanner.nextLine();
                    for (Account acc: accountList) {
                        if (acc.getUsername().equals(username) && acc.getPassword().equals(password)){

                            System.out.println("Hello " + acc.getFullname() + ".");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter username:");
                    String usr = scanner.nextLine();
                    boolean flag = false;
                    for (Account acc:accountList) {
                        if(acc.getUsername().equals(usr)){
                            System.out.println("Username already exists.");
                            flag = true;
                            break;
                        }
                    }
                    if (!flag){
                        System.out.println("Enter password:");
                        String pass = scanner.nextLine();
                        System.out.println("Enter full name");
                        String fullname = scanner.nextLine();
                        System.out.println("Enter phone number:");
                        String phoneNumber = scanner.nextLine();
                        System.out.println("Enter address");
                        String address = scanner.nextLine();
                        int id = accountList.size() + 1;
                        Account account = new Account(id,usr,pass,fullname,phoneNumber,address);
                        accountList.add(account);
                        writeFile(accountList);
                        System.out.println("Successfully created!");
                    }
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }while (true);
    }
}
