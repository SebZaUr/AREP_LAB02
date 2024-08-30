package arep.lab2;

import java.io.*;
import java.net.Socket;

public class App {
    public static SimpleWebServer server;
    public static SportsHTTPService sportServer;

    public static void main(String[] agrs ){
        LambdaServer.serverStaticFile("target/classes/webroot");
        sportServer = SportsHTTPService.getInstance();
        LambdaServer.get("/App/hello",(req) ->{
            String name = req.split("=")[1];
            String resp = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type:text/html\r\n"
                    + "\r\n"
                    + "<h1> Welcome to AREP TEST 1! </h1>";

            if (req != "") {
                resp += "<p> Welcome : " + name + "</p>";
            }
            return resp;
        });

        LambdaServer.get("/App/pi", (req) -> {
            return String.valueOf(Math.PI);
        });

        LambdaServer.get("/App/sport", (request -> {
            String sport = request.split("\\=")[1];
            return SportsHTTPService.getCountries(sport);
        }));

        try{
            if (!SimpleWebServer.isRunning())
                server = SimpleWebServer.getInstance();
        }catch(Exception e){
            System.err.println("Error en el servidor");
            System.exit(1);
        }
    }
}