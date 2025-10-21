import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client3 {
    public static void main(String[] args) {
        try (Socket s = new Socket("localhost", 2005)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
            Scanner sc = new Scanner(System.in);
            boolean again = true;
            while (again) // client 3ndh boucle ghi w7da ta3 ki yeg3od y3awed ymed les nmr w operation ctt
            {
                System.out.print("Enter first number: ");
                float a = sc.nextFloat();
                pw.println(a);

                System.out.print("Enter operator (+, -, *, /): ");
                String op = sc.next();
                pw.println(op);

                System.out.print("Enter second number: ");
                float b = sc.nextFloat();
                pw.println(b);

                String result = br.readLine();
                System.out.println("Result from server: " + result);

                String question = br.readLine();
                System.out.println(question);

                String reponse = sc.next();
                pw.println(reponse);

                if(reponse.equalsIgnoreCase("N"))again=false;
            }

            System.out.println("Connection closed.");
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
