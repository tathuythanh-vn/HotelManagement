package sample._BackEnd.TableView;

public class CustomerRoomTable {
    String ROOMNO;
    String ROOMTYPE;
    String NOTE;
    String PRICEDAY;
    String STATUS;

    public CustomerRoomTable(String ROOMNO, String ROOMTYPE, String PRICEDAY, String STATUS, String NOTE){
        this.ROOMNO = ROOMNO;
        this.ROOMTYPE = ROOMTYPE;
        this.PRICEDAY = PRICEDAY;
        this.STATUS = STATUS;
        this.NOTE = NOTE;
    }

    public String getROOMNO() {
        return ROOMNO;
    }

    public void setROOMNO(String ROOMNO) {
        this.ROOMNO = ROOMNO;
    }

    public String getROOMTYPE() {
        return ROOMTYPE;
    }

    public void setROOMTYPE(String ROOMTYPE) {
        this.ROOMTYPE = ROOMTYPE;
    }

    public String getNOTE() {
        return NOTE;
    }

    public void setNOTE(String NOTE) {
        this.NOTE = NOTE;
    }

    public String getPRICEDAY() {
        return PRICEDAY;
    }

    public void setPRICEDAY(String PRICEDAY) {
        this.PRICEDAY = PRICEDAY;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
}
