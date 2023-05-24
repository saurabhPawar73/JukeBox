package Main;

import Implementations.UserImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        UserImpl userImpl=new UserImpl();

        System.out.println("Welcome to Jukebox!!\n " +
                " 1. please login to continue....press 1\n" +
                " 2. Don't have an account?...press 2 to create ");

        int option=scanner.nextInt();

                  if(option==1)
                  {
                    userImpl.login();
                  } else if (option==2) {
                boolean flag=userImpl.createAccount();
                  }
                  else {
                      System.out.println("invalid choice inserted");
                        System.exit(0);
                  }
    }

    }