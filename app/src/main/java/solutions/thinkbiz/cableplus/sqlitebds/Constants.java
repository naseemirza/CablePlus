package solutions.thinkbiz.cableplus.sqlitebds;

/**
 * Created by User on 13-Dec-18.
 */

public class Constants {

    //COLUMNS
    static final String ROW_ID="id";
    static final String NAME="name";
    static final String URL="url";
    static final String PRICE="price";
    static final String PROD_ID="Pid";

    //DB PROPERTIES
//    static final String DB_NAME="cc_DB";
//    static final String TB_NAME="cc_TB";
//    //static final String TB_NAME="Cable";
//    static final int DB_VERSION=1;

    static final String DB_NAME="CABLEPLUS_DB";  //prev CABLE_DB
    static final String TB_NAME="CablePlus"; //prev Cable

    static final int DB_VERSION=1;

    //CREATE TABLE STMT
//    static final String CREATE_TB="CREATE TABLE cc_TB(id INTEGER PRIMARY KEY AUTOINCREMENT,"
//            + "name TEXT NOT NULL,url TEXT NOT NULL);";

    static final String CREATE_TB=" CREATE TABLE CablePlus (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " name TEXT NOT NULL, url TEXT NOT NULL, price TEXT NOT NULL, Pid TEXT NOT NULL);";

    //UPGRADE TB
   // static final String DELETE_TB="DELETE FROM "+TB_NAME;
    static final String DROP_TB="DROP TABLE IF EXISTS "+TB_NAME;


}
