package sample._BackEnd.TableView;

public class ManagerRoomTable {
    String ROOMNO;
    String ROOMTYPE;
    String PRICEDAY;
    String NOTE;
    String STATUS;

    public ManagerRoomTable(String ROOMNO, String ROOMTYPE, String PRICEDAY, String NOTE, String STATUS){
        this.ROOMNO = ROOMNO;
        this.ROOMTYPE = ROOMTYPE;
        this.PRICEDAY = PRICEDAY;
        this.NOTE = NOTE;
        this.STATUS = STATUS;
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


    public String getPRICEDAY() {
        return PRICEDAY;
    }

    public void setPRICEDAY(String PRICEDAY) {
        this.PRICEDAY = PRICEDAY;
    }

    public String getNOTE() {
        return NOTE;
    }

    public void setNOTE(String NOTE) {
        this.NOTE = NOTE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.NOTE = STATUS;
    }
}
