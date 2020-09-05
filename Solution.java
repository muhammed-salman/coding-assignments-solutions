import java.io.*;
import java.util.*;
import java.lang.*;

class Station{
    private int id;
    private String name;
    private int nextStationDistance;

    Station(int id, String name, int nextStationDistance){
        this.id = id;
        this.name = name;
        this.nextStationDistance = nextStationDistance;
    }

    // getter Methods
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getNextStationDistance(){
        return nextStationDistance;
    }

    // setter Methods
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setNextStationDistance(int nextStationDistance){
        this.nextStationDistance = nextStationDistance;
    }
}

class calculateFare{
    public float calculate(Ticket t){
        float fare;
        if (t.getAge() < 3)
            return 0.0f;
        if (t.getNoOfStops()<=5){
            fare = Ticket.getBasePrice();
        }
        else if(t.getNoOfStops() > Ticket.getTotalStops()-5){
            fare = Ticket.getFlatPrice();
        } 
        else{
            System.out.println((float)Math.ceil((t.getNoOfStops()-5)/5.0));
            fare = Ticket.getBasePrice() + (float)Math.ceil((t.getNoOfStops()-5)/5.0) * (float)Ticket.getIncrementCharge();
        }
        fare = t.getIsReturn() ? fare*1.75f : fare;
        if (t.getAge() < 10)
            return 0.75f * fare;
        else
            return fare;
    }
}

class Ticket {
    private Station from;
    private Station to;
    private boolean isReturn;
    private int noOfStops;
    private float distance;
    private float fare;
    private int age;
    private static float basePrice = 10.0f;
    private static float flatPrice = 20.0f;
    private static float incrementCharge = 5.0f;
    private static int totalStops = 18;

    Ticket(Station from, Station to, int age, boolean isReturn){
        this.from = from;
        this.to = to;
        this.age = age;
        this.isReturn = isReturn;
        this.noOfStops = Math.abs(this.from.getId() - this.to.getId());
        this.distance = 0.0f;
    }

    // getter method
    public boolean getIsReturn(){
        return this.isReturn;
    }
    public int getNoOfStops(){
        return this.noOfStops;
    }
    public float getDistance(){
        return this.distance;
    }
    public float getFare(){
        return this.fare;
    }
    public int getAge(){
        return this.age;
    }
    public static float getBasePrice(){
        return Ticket.basePrice;
    }
    public static float getFlatPrice(){
        return Ticket.flatPrice;
    }
    public static float getIncrementCharge(){
        return Ticket.incrementCharge;
    }
    public static float getTotalStops(){
        return Ticket.totalStops;
    }

    public void calculate(Station[] stations){
        if (stations.length > 1){
            for(int i = 0; i < stations.length; i++)
                this.distance += stations[i].getNextStationDistance();
        }
        this.fare = (new calculateFare()).calculate(this);
    }

    public void printTicket(){
        if(this.isReturn){
            System.out.println("\nReturn Ticket\nFrom: "+this.from.getName()+"\tTo: "+this.to.getName()+"\tFrom: "+this.from.getName());
        }
        else{
            System.out.println("\nFrom: "+this.from.getName()+"\nTo: "+this.to.getName());
        }
        System.out.println(this.noOfStops+" Stops\nDistance: "+this.distance+"\nRs "+this.fare);
        System.out.println("Purchase Date: "+java.time.LocalDate.now());  
        System.out.println("Purchase Time: "+java.time.LocalTime.now());  
    }

}

class Solution{
    public static void main(String args[]){
        String[] stationsName = new String[]{"Chennai Beach","Chennai Fort","Chennai Park","Chennai Egmore","Chetpet","Nungambakkam","Kodambakkam","Mambalam","Saidapet","Guindy","St. Thomas Mount","Pazhavanthangal","Meenambakkam","Trisulam","Pallavaram","Chromepet","Tambaram Sanatorium","Tambaram"};

        Station[] stations = new Station[stationsName.length];

        for(int i=0; i<stationsName.length; i++)
            stations[i] = new Station(i+1,stationsName[i],5);
        
        Ticket t1 = new Ticket(stations[0],stations[9],18,false);
        Ticket t2 = new Ticket(stations[0],stations[12],15,true);
        Ticket t3 = new Ticket(stations[17],stations[12],12,false);
        Ticket t4 = new Ticket(stations[0],stations[15],20,false);
        Ticket t5 = new Ticket(stations[0],stations[17],22,false);
        Ticket t6 = new Ticket(stations[0],stations[17],27,true);
        Ticket t7 = new Ticket(stations[17],stations[0],56,false);
        Ticket t8 = new Ticket(stations[17],stations[0],2,false);
        Ticket t9 = new Ticket(stations[17],stations[0],9,false);
        t1.calculate(Arrays.copyOfRange(stations,0,10));
        t2.calculate(Arrays.copyOfRange(stations,0,13));
        t3.calculate(Arrays.copyOfRange(stations,12,18));
        t4.calculate(Arrays.copyOfRange(stations,0,16));
        t5.calculate(Arrays.copyOfRange(stations,0,18));
        t6.calculate(Arrays.copyOfRange(stations,0,18));
        t7.calculate(Arrays.copyOfRange(stations,0,18));
        t8.calculate(Arrays.copyOfRange(stations,0,18));
        t9.calculate(Arrays.copyOfRange(stations,0,18));
        t1.printTicket();
        t2.printTicket();
        t3.printTicket();
        t4.printTicket();
        t5.printTicket();
        t6.printTicket();
        t7.printTicket();
        t8.printTicket();
        t9.printTicket();
    }
}