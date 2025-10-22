import java.io.*;
import java.net.*;

public class server3 extends Thread
{
    private Socket s;

    public server3(Socket s) {
        this.s = s;
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println("Client connected!");

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
            boolean again = true;
            
            while (again)
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
                        }
                        break;
                    default:
                        pw.println("Error: unknown operator!");
                        valid = false;
                }

                if (valid)
                {
                    pw.println(result);
                    System.out.println("Sent back: " + result);
                }

                pw.println("do you want to use the calculator again?(Y or N)");
                String response = br.readLine();
                if (response.equalsIgnoreCase("y")) again = true;
                else again = false;
            }

            System.out.println("Connection closed.");
            s.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        try (ServerSocket ss = new ServerSocket(2005))
        {
            System.out.println("Server waiting for clients...");
            while (true)
            {
                Socket s = ss.accept();
                server3 server = new server3(s);
                server.start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
