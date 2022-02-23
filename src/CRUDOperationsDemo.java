import java.sql.*; // folosesc api-ul numit JDBC, specs-urile sale vin cu java

public class CRUDOperationsDemo {

    public static void main(String[] args) {

        CRUDOperationsDemo co = new CRUDOperationsDemo();

        co.demoUpdate();
        // co.demoCreate(); // insert into db one row
        co.demoRead(); // insert into db one row
       // demoUpdate(); // insert into db one row
      // demoDelete(); // insert into db one row
    }

    private void demoRead() {

        // 1. ma conectez la db
        final String URL = "jdbc:postgresql://idc.cluster-custom-cjcsijnttbb2.eu-central-1.rds.amazonaws.com:5432/ionelcondor";
        final String USERNAME = "ftuser";
        final String PASSWORD = "password1";
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. fac un query pe o tabela , intai creez obiectul
            Statement st = conn.createStatement();


            // 3. execut acel query
            ResultSet rs = st.executeQuery("SELECT username, password FROM USERS");

            // 4 . optional, fac ce doresc cu datele din acest ResultSet

            while (rs.next()) {
                String user = rs.getString("username");
                if (user != null)
                    user = user.trim();
                String pwd = rs.getString("password");
                if (pwd != null)
                    pwd = pwd.trim();
                System.out.println(user + "/" + pwd);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        private void demoCreate() {

            try {
                // 1. ma conectez la db
                final String URL = "jdbc:postgresql://idc.cluster-custom-cjcsijnttbb2.eu-central-1.rds.amazonaws.com:5432/ionelcondor";
                final String USERNAME = "ftuser";
                final String PASSWORD = "password1";
                Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                // 2. creez un prepared ststement si il populez cu date
                PreparedStatement pSt = conn.prepareStatement("INSERT INTO USERS (username, PASSWORD) VALUES(?,?)");
                pSt.setString(1,"azadic89");
                pSt.setString(2,"P@ss0rd22");

                // 3. executie
                int val = pSt.executeUpdate();
                System.out.println(val);

                pSt.close();
                conn.close();
            } catch (SQLException e) {
               // e.printStackTrace();
                if(e.getMessage().contains("duplicate key value violates unique constraint")) {
                    System.out.println("baaaaa, ai deja userul in db ");
                }

                if(e.getMessage().contains("does not exist")) {
                    System.out.println("err la conexiune, ce faci oare , ai db-ul up ? ");
                }

            }


        }


    private void demoUpdate() {
        // 1. ma conectez la db
        final String URL = "jdbc:postgresql://idc.cluster-custom-cjcsijnttbb2.eu-central-1.rds.amazonaws.com:5432/ionelcondor";
        final String USERNAME = "ftuser";
        final String PASSWORD = "password1";
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);


            // 2. creez un prepared ststement si il populez cu date
            PreparedStatement pSt = conn.prepareStatement("update users set password=? where username=?"); // or delete from users where username=azadic
            pSt.setString(1,"anaaremere2010$");
            pSt.setString(2,"azadic"); // for delete i would need to remove this one

            // 3. executie
            int val = pSt.executeUpdate();
            System.out.println(val);

            pSt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
