package libs;

import org.apache.hc.client5.http.impl.routing.SystemDefaultRoutePlanner;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.nio.file.Files.readAllBytes;

public class Database {
    //private final String url = "jdbc:postgresql://pgdb:5433/ninjaplus"; -> Se o banco estiver setado em outra porta que não seja a padrão, tem que passar a porta como nesse exemplo.
    private final String url = "jdbc:postgresql://pgdb/ninjaplus";
    private final String user = "postgres";
    private final String pass = "qaninja";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    public void resetMovies() {
        String executionPath = System.getProperty("user.dir");
        String os = System.getProperty("os.name");

        String target;

        if (os.contains("Windows")) {
            target = target = executionPath + "\\src\\main\\resources\\sql\\movies.sql";
        } else {
            target = target = executionPath + "/src/main/resources/sql/movies.sql";
        }

        String moviesSQL;

        try {
            moviesSQL = new String(readAllBytes(Paths.get(target)));

            PreparedStatement query = this.connect().prepareStatement(moviesSQL);
            query.executeQuery();

        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteMovie(String title) {
        String SQL = "delete from public.movies where title = ?;";
        try {
            PreparedStatement query = this.connect().prepareStatement(SQL);
            query.setString(1, title);
            query.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
