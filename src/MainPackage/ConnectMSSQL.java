/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;
import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author abir
 */
public class ConnectMSSQL {
    
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public void connectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=furnitureShop;selectMethod=cursor", "sa", "123456");
             // "jdbc:mysql://localhost:3306/furnitureShop","root","");
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean auth(String $user,String $pass){
        String temp1,temp2;
        try{
            statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM AdminInfo");
            while (resultSet.next()) {
                temp1=resultSet.getString("Username");
                temp2=resultSet.getString("Password");
                
                if($user.equals(temp1)&&$pass.equals(temp2)) return true;
                
            }
            
    }
    catch (Exception e) {
            e.printStackTrace();
    }
       return false; 
}
    
    public void getData(String $passing){
        
        
    try{
        System.out.println("DATABASE NAME IS:"
                    + connection.getMetaData().getDatabaseProductName());
            statement = connection.createStatement();
        resultSet = statement
                    .executeQuery("SELECT * FROM "+$passing);
            while (resultSet.next()) {
                System.out.println("Admin NAME:"
                        + resultSet.getString("Username"));
            }
    }
    catch (Exception e) {
            e.printStackTrace();
    }
}
    

   public int setNewCustomer(String $fname, String $lname,String $phone, String $address){      
   // String cusid= " ";
    int temp = 0;
       try{
       
        
        String demo = "INSERT INTO CUSTOMER(FirstName,LastName,Phone,Address) VALUES('"+$fname+"','"+$lname+"','"+$phone+"','"+$address+"')";
                    statement = connection.createStatement();
                    statement.executeUpdate(demo);
        
                    
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM CUSTOMER order by CustomerId desc");
        if(resultSet.next())
            temp = Integer.parseInt(resultSet.getString("CustomerId"));
        
            
    }
    catch (Exception e) {
            e.printStackTrace();
    }
    //System.out.println(temp);;
    return temp;
   }
   
   
    public String getDataName(String d1,String d2,String d3){
       String s=" ";
       try {
            String query1="SELECT TOP 1 "+ d1 +" FROM "+ d2 +" ORDER BY "+d3+" DESC";
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(query1);
            if(rs.next())
               s =rs.getString(d1);             
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
   }
   public String getNormalName(String d1,String d2,String d3,int n){
       String s=" ";
       try {
            String query1="SELECT "+ d1 +" FROM "+ d2 +" Where "+d3+" = " +n;
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(query1);
            if(rs.next())
               s =rs.getString(d1);             
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
   }
   
   public String getCustomerName(){
       String s=" ";
       try {
            String query1="SELECT TOP 1 FirstName + ' ' + LastName as 'Customer Name' FROM CUSTOMER ORDER BY CustomerId DESC";
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(query1);
            if(rs.next())
               s =rs.getString("Customer Name");             
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
   }
   
    
   
   int setNewOrder(int cusid){
       int oid=0;
       
       try{
            
            LocalDate ld = java.time.LocalDate.now();
            String s="INSERT INTO ORDERR(CustomerId,OrderDate,DeleveryDate) VALUES('"+cusid+"','"+ld+"','"+ld+"')";
            statement = connection.createStatement();
            statement.executeUpdate(s);
          
       
       }catch(Exception e){
            e.printStackTrace();
       }
       
       
       
       return oid; 
   
   }

   
   void setDeleveryDate(String s){
        try{
            LocalDate ld =LocalDate.parse(s);
            String ssql = "update ORDERR SET DeleveryDate= '"+ld+"' where CustomerId = (SELECT TOP 1 CustomerId FROM CUSTOMER ORDER BY CustomerId DESC)";
            statement = connection.createStatement();
            statement.executeUpdate(ssql);
            
        }catch(Exception e){
            e.printStackTrace();
        }
         
    }
   
   void updateWoodqtn(String d1, int d2){
       try{
            
            String ssql = "update Wood SET Quantity = '"+d2+"' where Name = '"+d1+"' ";
            statement = connection.createStatement();
            statement.executeUpdate(ssql);
            
        }catch(Exception e){
            e.printStackTrace();
        }
   
   }

   
   
   
   void placeOrder(int fid,int wid, float cost, float height, float width,float length, int iqtn ,int wqtn,String ef){
    
        try{
            
            int oid=201;
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT *FROM ORDERR ORDER BY OrderId DESC");
                if(resultSet.next())
                    oid=  Integer.parseInt(resultSet.getString("OrderId"));
                
            System.out.println(oid);
           
            
           
            
            
            String forMesurement = "insert into MESUREMENT(Height,Width,Length,WoodQuantity,ExtraFeature) VALUES('"+height+"','"+width+"','"+length+"','"+wqtn+"','"+ef+"')";
            Statement st1=connection.createStatement();
            st1.executeUpdate(forMesurement);
            
            
            int mid=0;
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Mesurement order by MesurementId desc");
            if(resultSet.next())
                mid = Integer.parseInt(resultSet.getString("MesurementId"));
            
            System.out.println(mid);
            
            
            
            
            String forItem = "INSERT INTO ITEM(OrderId,FurnitureId,WoodId,MesurementId,ItemQuantity,Cost) VALUES('"+oid+"','"+fid+"','"+wid+"','"+mid+"','"+iqtn+"','"+cost+"')";
            Statement st2=connection.createStatement();
            st2.executeUpdate(forItem);
           
          
       
       }catch(Exception e){
            e.printStackTrace();
       }
       
    
    }
    
   
   
   public ArrayList<getAll.User>customerList(){
        ArrayList<getAll.User>customersList=new ArrayList<>();
        try {
            
    
            String query1="SELECT * FROM CUSTOMER";
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(query1);
            getAll g = new getAll();
            getAll.User gu;
            while(rs.next()){
                gu=g.new User(rs.getInt("CustomerId"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Phone"),rs.getString("Address"));
                customersList.add(gu);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customersList;
    }
   
   
   
   public ArrayList<getAll.User>searchCustomer(String s){
        ArrayList<getAll.User>customersList=new ArrayList<>();
        try {
            
    
            String query1="SELECT * FROM CUSTOMER Where FirstName like '%"+s+"%'";
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(query1);
            getAll g = new getAll();
            getAll.User gu;
            while(rs.next()){
                gu=g.new User(rs.getInt("CustomerId"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Phone"),rs.getString("Address"));
                customersList.add(gu);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customersList;
    }
   
     
   
   
   public ArrayList<getAll.User>workerList(){
        ArrayList<getAll.User>workersList=new ArrayList<>();
        try {
            
           
            String query1="SELECT * FROM WORKER";
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(query1);
            getAll g =new getAll();
            getAll.User gu;
            while(rs.next()){
               gu =g.new User(rs.getInt("WorkerId"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Phone"),rs.getString("Address"));
                workersList.add(gu);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workersList;
    }
   
   
   public ArrayList<getAll.zWood>woodList(){
        ArrayList<getAll.zWood>woodsList=new ArrayList<>();
        try {
            
           
            String query1="SELECT * FROM WOOD";
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(query1);
            getAll g = new getAll();
            
            getAll.zWood zw;
            while(rs.next()){
                zw=g.new zWood(rs.getInt("WoodId"),rs.getString("Name"),rs.getInt("PricePerUnit"),rs.getInt("Quantity"));
                System.out.println(rs.getInt("PricePerUnit"));
                woodsList.add(zw);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return woodsList;
    }
   
   
   
   
   
   public ArrayList<getAll.zWood>searchWood(String s){
        ArrayList<getAll.zWood>woodsList=new ArrayList<>();
        try {
            
           
            String query1="SELECT * FROM WOOD Where Name like '%"+s+"%'";
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(query1);
            getAll g = new getAll();
            
            getAll.zWood zw;
            while(rs.next()){
                zw=g.new zWood(rs.getInt("WoodId"),rs.getString("Name"),rs.getInt("PricePerUnit"),rs.getInt("Quantity"));
                System.out.println(rs.getInt("PricePerUnit"));
                woodsList.add(zw);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return woodsList;
    }
    
   
   
   
   
   

    public ArrayList<getAll.zFurniture>furnitureList(){
        ArrayList<getAll.zFurniture>furnituresList=new ArrayList<>();
        try {
            
           
            String query1="SELECT * FROM Furniture";
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(query1);
            getAll g = new getAll();
            
            getAll.zFurniture zf;
            while(rs.next()){
                zf=g.new zFurniture(rs.getInt("FurnitureId"),rs.getString("Name"),rs.getInt("MakingCost"));
                furnituresList.add(zf);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return furnituresList;
    }
    
    
    
    
    public ArrayList<getAll.zFurniture>searchFurniture(String s){
        ArrayList<getAll.zFurniture>furnituresList=new ArrayList<>();
        try {
            
           
            String query1="SELECT * FROM Furniture Where Name like '%"+s+"%'";
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(query1);
            getAll g = new getAll();
            
            getAll.zFurniture zf;
            while(rs.next()){
                zf=g.new zFurniture(rs.getInt("FurnitureId"),rs.getString("Name"),rs.getInt("MakingCost"));
                furnituresList.add(zf);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return furnituresList;
    }
    
    
    
    
    
    void setNewFurniture(String name, String ppu){
        
       
        
        try {
            
            int price= Integer.parseInt(ppu);
           
            String forItem = "INSERT INTO FURNITURE(Name,MakingCost) VALUES('"+name+"','"+price+"')";
            Statement st2=connection.createStatement();
            st2.executeUpdate(forItem);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
         
    
    }
    
    

    
    
    
   void setNewWood(String name, String ppu, String qquanti){
        
       
        
        try {
            
            int price= Integer.parseInt(ppu);
            int quantity= Integer.parseInt(qquanti);
           
            String forItem = "INSERT INTO WOOD(Name,PricePerUnit,Quantity) VALUES('"+name+"','"+price+"','"+quantity+"')";
            Statement st2=connection.createStatement();
            st2.executeUpdate(forItem);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
         
    
    } 
   
   
   
   
   
  
    
   void setNewWorker(String name, String lname, String phone,String address){
        
       
        
        try {
            
            
           
            String forItem = "INSERT INTO WORKER(FirstName,LastName,Phone,Address) VALUES('"+name+"','"+lname+"','"+phone+"','"+address+"')";
            Statement st2=connection.createStatement();
            st2.executeUpdate(forItem);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
         
    
    } 
   
   
   
   
   
    public ArrayList<getAll.customerOrderedItem>orderFinal(){
        
        ArrayList<getAll.customerOrderedItem> temp=new ArrayList();
        
        String s=""
        +"select i.FurnitureId,WoodId,i.ItemQuantity,i.Cost,m.WoodQuantity,"
        +"convert(varchar(20),m.Height) + '-' + convert(varchar(20),m.Width) + '-' + convert(varchar(20),m.Length) " 
        +"as 'Mesurement' from ITEM i Inner join MESUREMENT m on i.MesurementId = m.MesurementId "
        +"where i.OrderId  = (SELECT TOP 1 OrderId FROM ORDERR ORDER BY OrderId DESC)";
        
        
        try {
            
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(s);
            getAll g = new getAll();
            
            getAll.customerOrderedItem coi;
            while(rs.next()){
                coi=g.new customerOrderedItem(rs.getInt("FurnitureId"),rs.getInt("WoodId"),rs.getInt("ItemQuantity"),rs.getFloat("Cost"),rs.getInt("WoodQuantity"),rs.getString("Mesurement"));
                temp.add(coi);
                
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return temp;
   
   }
    
    
    public String returnTotalItem(String d1, String d2){
        
        
        
        String s=""
        +"select sum("+d1+") as '"+d1+"' from "+d2+" on i.MesurementId = m.MesurementId "
        +"where i.OrderId  = (SELECT TOP 1 OrderId FROM ORDERR ORDER BY OrderId DESC)";
        
        String s2="";
        
        try {
            
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(s);
            
            if(rs.next()){
                s2=rs.getString(d1);  
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return s2;
   
   }
   
    
    
    
     public ArrayList<getAll.aOrderInfo>aOrderInf(){
        
        ArrayList<getAll.aOrderInfo> temp=new ArrayList();
        
        String s="select * from ORDERR";
        
        
        try {
            
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(s);
            getAll g = new getAll();
            
            getAll.aOrderInfo coi;
            while(rs.next()){
                coi=g.new aOrderInfo(rs.getInt("OrderId"),rs.getInt("CustomerId"),rs.getString("OrderDate"),rs.getString("DeleveryDate"));
                temp.add(coi);
                
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return temp;
   
   }
   
     
     public ArrayList<getAll.aOrderInfo>sOrderInf(int n){
        
        ArrayList<getAll.aOrderInfo> temp=new ArrayList();
        
        String s="select * from ORDERR Where OrderId = '"+n+"'";
        
        
        try {
            
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(s);
            getAll g = new getAll();
            
            getAll.aOrderInfo coi;
            while(rs.next()){
                coi=g.new aOrderInfo(rs.getInt("OrderId"),rs.getInt("CustomerId"),rs.getString("OrderDate"),rs.getString("DeleveryDate"));
                temp.add(coi);
                
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return temp;
   
   }
     
     
      public ArrayList<getAll.aOrderedItem>aOrderFinal(){
        
        ArrayList<getAll.aOrderedItem> temp=new ArrayList();
        
        String s=""
        +"select i.OrderId, i.FurnitureId,WoodId,i.ItemQuantity,i.Cost,m.WoodQuantity,"
        +"convert(varchar(20),m.Height) + '-' + convert(varchar(20),m.Width) + '-' + convert(varchar(20),m.Length) " 
        +"as 'Mesurement' from ITEM i Inner join MESUREMENT m on i.MesurementId = m.MesurementId "
        +"where i.OrderId in (SELECT OrderId FROM ORDERR)";
        
        
        try {
            
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(s);
            getAll g = new getAll();
            
            getAll.aOrderedItem coi;
            while(rs.next()){
                coi=g.new aOrderedItem(rs.getInt("OrderId"),rs.getInt("FurnitureId"),rs.getInt("WoodId"),rs.getInt("ItemQuantity"),rs.getFloat("Cost"),rs.getInt("WoodQuantity"),rs.getString("Mesurement"));
                temp.add(coi);
                
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return temp;
   
   }
   
      
      public ArrayList<getAll.aOrderedItem>sOrderFinal(int n){
        
        ArrayList<getAll.aOrderedItem> temp=new ArrayList();
        
        String s=""
        +"select i.OrderId, i.FurnitureId,WoodId,i.ItemQuantity,i.Cost,m.WoodQuantity,"
        +"convert(varchar(20),m.Height) + '-' + convert(varchar(20),m.Width) + '-' + convert(varchar(20),m.Length) " 
        +"as 'Mesurement' from ITEM i Inner join MESUREMENT m on i.MesurementId = m.MesurementId "
        +"where i.OrderId in (SELECT OrderId FROM ORDERR Where OrderId = '"+n+"')";
        
        
        try {
            
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(s);
            getAll g = new getAll();
            
            getAll.aOrderedItem coi;
            while(rs.next()){
                coi=g.new aOrderedItem(rs.getInt("OrderId"),rs.getInt("FurnitureId"),rs.getInt("WoodId"),rs.getInt("ItemQuantity"),rs.getFloat("Cost"),rs.getInt("WoodQuantity"),rs.getString("Mesurement"));
                temp.add(coi);
                
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return temp;
   
   }
    
    

}
