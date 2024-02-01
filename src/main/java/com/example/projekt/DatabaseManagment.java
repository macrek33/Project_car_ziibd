package com.example.projekt;



import java.sql.*;
import java.util.ArrayList;

public class DatabaseManagment {

    private final String dbURL = "jdbc:oracle:thin:@155.158.112.45:1521:oltpstud";

    private  Connection  connection;
    protected Statement statement;
    protected ResultSet result;
    protected ResultSetMetaData resultInfo;
    protected StringBuffer builder;
    public DatabaseManagment(String username, String password)
    {
        try
        {
            connection = DriverManager.getConnection(dbURL,username,password);
            statement = connection.createStatement();
            builder = new StringBuffer();
        }
        catch(SQLException sqlerror)
        {

            throw new IllegalArgumentException("Logowanie nie udane! nieprawidłowy login/hasło");
        }
    }

    public ArrayList<Auto> selectAuta()
    {
        try {
            result = statement.executeQuery("select * from auta");
            ArrayList<Auto> auta = new ArrayList<>();

            while (result.next())
            {

                Auto auto = new Auto();
                auto.setNrVin(result.getString(1));
                auto.setMarka(result.getString(2));
                auto.setModel(result.getString(3));
                auto.setRokProdukcji(result.getInt(4));
                auto.setSilnik(result.getString(5));
                auto.setPaliwo(result.getString(6));
                auta.add(auto);


            }
            return auta;
        }
        catch(SQLException sql)
        {


            throw new UnsupportedOperationException("Brakuje takiej tabeli!");
        }
    }

    public void createSchema()
    {
        try {
            String SQL = "create table auta\n" +
                    "(\n" +
                    "numer_vin VARCHAR2(17) CONSTRAINT pk_vin PRIMARY KEY,\n" +
                    "marka VARCHAR2(45) CONSTRAINT NN_marka NOT NULL,\n" +
                    "model  VARCHAR2(45) CONSTRAINT NN_model NOT NULL,\n" +
                    "rok_produkcji INTEGER, \n" +
                    "silnik VARCHAR2(15),\n" +
                    "paliwo VARCHAR2(15)\n" +
                    ")";
            statement.executeUpdate(SQL);
            statement.executeUpdate("insert into auta values('12345678901234567','Mercedes','W220',2002,'CDI 2.7','Diesel')");
        }

        catch(SQLException sql)
        {
           ;
        }

    }

    public void addRow(Auto auto)
    {
        try {
            String sql = "insert into auta" + " values(?,?,?,?,?,?)";
            String rok = Integer.toString(auto.getRokProdukcji());
           // statement.executeUpdate("insert into auta values ('1','2','3','4','5','6')");
            PreparedStatement pstmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS) ;

                pstmt.setString(1, auto.getNrVin());
                pstmt.setString(2, auto.getMarka());
                pstmt.setString(3, auto.getModel());
                pstmt.setInt(4,auto.getRokProdukcji());
                pstmt.setString(5, auto.getSilnik());
                pstmt.setString(6, auto.getPaliwo());



            pstmt.executeQuery();

        }
        catch(SQLIntegrityConstraintViolationException sql)
        {
            throw new UnsupportedOperationException("Istnieje już taki numer Vin!");
        }
        catch(SQLException sql)
        {


            throw new UnsupportedOperationException("Wystąpił błąd w bazie danych, spróbuj ponownie!");
        }
    }

    public void deleteRow(Auto auto)
    {
        try {
            String sql = "delete from auta where numer_vin = ?";
            String rok = Integer.toString(auto.getRokProdukcji());
            // statement.executeUpdate("insert into auta values ('1','2','3','4','5','6')");
            PreparedStatement pstmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS) ;

            pstmt.setString(1, auto.getNrVin());


            pstmt.executeQuery();
        }

        catch(SQLException sql)
        {


            throw new UnsupportedOperationException("Wystąpił błąd w bazie danych, spróbuj ponownie!");
        }
    }

    public void updateRow(Auto auto)
    {
        try {
            String sql = "update auta set marka=?, model=?, rok_produkcji=?, silnik=?, paliwo=? where numer_vin=?";

            PreparedStatement pstmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS) ;

            pstmt.setString(1, auto.getMarka());
            pstmt.setString(2, auto.getModel());
            pstmt.setInt(3, auto.getRokProdukcji());
            pstmt.setString(4, auto.getSilnik());
            pstmt.setString(5, auto.getPaliwo());
            pstmt.setString(6, auto.getNrVin());


            pstmt.executeQuery();
        }

        catch(SQLException sql)
        {


            throw new UnsupportedOperationException("Wystąpił błąd w bazie danych, spróbuj ponownie!");
        }
    }










    public void closeConnect()
    {

        try
        {
            if(!connection.isClosed())
                connection.close();
        }
        catch(SQLException sqlerror)
        {
            throw new UnsupportedOperationException("Wystąpił problem z zakończeniem połączenia! ");
        }

    }





}







