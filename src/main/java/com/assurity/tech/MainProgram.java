package com.assurity.tech;

public class MainProgram {
    public static void main(String[] args) {
        try {
            if (args.length<1){
                System.out.println ("Please pass a URL String to receive its response" );
                System.exit (1);
            }
            String url = args[0];
            ArgumentValidator.checkArgumentNullOrEmpty (url);
            URLReader.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


