/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;
import java.time.LocalDate;
/**
 *
 * @author Asif
 */
public class getAll {

    
    
    class User {
    private int ccusid;
    private String fname,lname,phone,address;
    
    //try{
    public User(int ccusid,String fname,String lname,String phone,String address)
    {
        this.ccusid=ccusid;
        this.fname=fname;
        this.lname=lname;
        this.phone=phone;
        this.address=address;
    }
    
    public int getccusid(){
        return ccusid;
    }
    public String getfname(){
        return fname;
    }
    public String getlname(){
        return lname;
    }
    public String getphone(){
        return phone;
    }
    public String getaddress(){
        return address;
    }

}
    
        
    
    class zWood{
    private int wId,wPricePerUnit, wQuantity;
    private String wName; 

    
    
    zWood(int wId,  String wName, int wPricePerUnit,int  wQuantity) {
        this.wId = wId;
        this.wName = wName;
        this.wPricePerUnit = wPricePerUnit;
        this.wQuantity = wQuantity;
    }


    public int getwId() {
        return wId;
    }

    public String getwName() {
        return wName;
    }

    public int getwPricePerUnit() {
        return wPricePerUnit;
    }
    
    public int getwQuantity() {
        return wQuantity;
    }
    }
    
    
    
    class zFurniture {
    int fId, fmakingCost;
    String fName;

    public zFurniture(int fId,  String fName, int fmakingCost) {
        this.fId = fId;
        this.fmakingCost = fmakingCost;
        this.fName = fName;
    }

    public int getfId() {
        return fId;
    }

    public int getfMakingCost() {
        return fmakingCost;
    }

    public String getfName() {
        return fName;
    }
    
    }
    
    class customerOrderedItem{
    
        
        int fid, wid,iq,wq;
        float subCost;
        String mesure;

        public customerOrderedItem(int fid, int wid, int iq, float subCost, int wq, String mesure) {
            
            this.fid = fid;
            this.wid = wid;
            this.iq = iq;
            this.wq = wq;
            this.subCost = subCost;
            
            this.mesure = mesure;
        }

        
        
        public int getFid() {
            return fid;
        }

        public int getWid() {
            return wid;
        }

        public int getIq() {
            return iq;
        }

        public int getWq() {
            return wq;
        }

        public float getSubCost() {
            return subCost;
        }

        

        public String getMesure() {
            return mesure;
        }
        
        
        
    
    }
    
    class aOrderInfo{
    private int oid,uid;
    private String d1,d2; 

        public aOrderInfo(int oid, int uid, String d1, String d2) {
            this.oid = oid;
            this.uid = uid;
            this.d1 = d1;
            this.d2 = d2;
        }

        public int getOid() {
            return oid;
        }

        public int getUid() {
            return uid;
        }

        public String getD1() {
            return d1;
        }

        public String getD2() {
            return d2;
        }

    
    
    }
    
    
    class aOrderedItem{
    
        
        int fid, wid,iq,wq,oid;
        float subCost;
        String mesure;

        public aOrderedItem(int oid,int fid, int wid, int iq, float subCost, int wq, String mesure) {
            this.oid =oid;
            this.fid = fid;
            this.wid = wid;
            this.iq = iq;
            this.wq = wq;
            this.subCost = subCost;
            
            this.mesure = mesure;
        }

        public int getOid() {
            return oid;
        }
        
        public int getFid() {
            return fid;
        }

        public int getWid() {
            return wid;
        }

        public int getIq() {
            return iq;
        }

        public int getWq() {
            return wq;
        }

        public float getSubCost() {
            return subCost;
        }

        

        public String getMesure() {
            return mesure;
        }
        
        
        
    
    }
    
    
    
}
    
    




 