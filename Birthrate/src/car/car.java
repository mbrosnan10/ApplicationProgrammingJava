
package car;


public class car {
private String  CARREG;
    private String MAKE; //could use a char instead of a String
    private String MODEL;
    private String COLOUR;
    private String LOCATION;
    private int PRICE;
    private int OWNERS;
    private int MILEAGE;

    public car(String CARREG, String MAKE,String MODEL,String COLOUR,String LOCATION, int PRICE, int OWNERS, int MILEAGE) {
        this.CARREG = CARREG;
        this.MAKE = MAKE;
        this.MODEL = MODEL;
        this.COLOUR = COLOUR;
        this.LOCATION = LOCATION;
         this.PRICE = PRICE;
        this.OWNERS = OWNERS;
        this.MILEAGE = MILEAGE;
        
    }

    /**
     * @return the timeZone
     */
    public String getCARREG() {
        return CARREG;
    }

    public void setCARREG(String CARREG) {
        this.CARREG = CARREG;
    }

    /**
     * @return the gender
     */
    public String getMAKE() {
        return MAKE;
    }

    public void setMAKE(String MAKE) {
        this.MAKE = MAKE;
    }
      public String getMODEL() {
        return MODEL;
    }

    public void setMODEL(String MODEL) {
        this.MODEL = MODEL;
    }
    public String getCOLOUR() {
        return COLOUR;
    }

    public void setCOLOUR(String COLOUR) {
        this.COLOUR = COLOUR;
    }
 public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public int getPRICE() {
        return PRICE;
    }
    public void setYear(int PRICE) {
        this.PRICE = PRICE;
    }
 public int getOWNERS() {
        return OWNERS;
    }
    public void setOWNERS(int OWNERS) {
        this.OWNERS = OWNERS;
    }
    public int getMILEAGE() {
        return MILEAGE;
    }
    public void setMILEAGE(int MILEAGE) {
        this.MILEAGE = MILEAGE;
    }


    @Override
    public String toString() {
    return CARREG + "\t" + MAKE + "\t"+ MODEL +"\t"+ COLOUR + "\t" + LOCATION + "\t" + PRICE + "\t" + OWNERS + "\t" +MILEAGE ;
    

      
    }
}
