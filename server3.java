import java.io.*;
import java.net.*;

public class server3 {
    public static void main(String[] args)
    {
        try (ServerSocket ss = new ServerSocket(2005))
        {
            System.out.println("Server waiting...");

            while (true) //boucle exterior hna kol client jdid yebda
            {
                Socket s = ss.accept();
                System.out.println("Client connected!");

                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);

                boolean again=true;
                while (again) // boucle interior hna kol calcul jdid yebda 
                {
                    float a = Float.parseFloat(br.readLine());
                    String op = br.readLine();
                    float b = Float.parseFloat(br.readLine());

                    
                    System.out.println("Received: " + a + " " + op + " " + b);

                    float result = 0;
                    boolean valid = true;

                    switch (op) {
                        case "+": result = a + b; break;
                        case "-": result = a - b; break;
                        case "*": result = a * b; break;
                        case "/":
                            if (b == 0)
                            {
                                pw.println("Error: division by zero!");
                                valid = false;
                            }
                            else
                            {
                                result = a / b;
                            }break;

                        default: pw.println("Error: unknown operator!");valid = false;
                    }
                    if (valid)
                    {
                        pw.println(result);
                        System.out.println("Sent back: " + result);
                    }
                    pw.println("do you want to use the calculator again?(Y or N)");
                    String reponse;
                    reponse = br.readLine();
                    if(reponse.equalsIgnoreCase("y")){again=true;}
                    else if(reponse.equalsIgnoreCase("N")){again=false;}
                }
                
                s.close();
                System.out.println("Connection closed.");
            }
            
        }
        catch (Exception e)
        { 
            e.printStackTrace();
        }
    }
}
