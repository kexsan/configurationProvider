package et.com.commomHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {

   /*// @Autowired
    private DataSource dataSource;

    public TableService(@Qualifier("primaryDataSource")DataSource dataSource) {
        this.dataSource = dataSource;
    }*/


    @Autowired
    @Qualifier("oraDataSource")
    private DataSource oraDataSource;

    @Autowired
    @Qualifier("pgSqlDataSource")
    private DataSource pgSqlDataSource;



    public List<String> getOraTableNames(String schemaName) {
        List<String> tableNames = new ArrayList<>();

        try (Connection connection = oraDataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();

            // Получение списка всех таблиц в базе данных
            try (ResultSet resultSet = metaData.getTables(null, schemaName, "%", new String[]{"TABLE"})) {
                while (resultSet.next()) {
                    String tableName = resultSet.getString("TABLE_NAME");
                    tableNames.add(tableName);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableNames;
    }


    public List<String> getPgSqlTableNames(String schemaName) {
        List<String> tableNames = new ArrayList<>();

        try (Connection connection = pgSqlDataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();

            // Получение списка всех таблиц в базе данных
            try (ResultSet resultSet = metaData.getTables(null, schemaName, "%", new String[]{"TABLE"})) {
                while (resultSet.next()) {
                    String tableName = resultSet.getString("TABLE_NAME");
                    tableNames.add(tableName);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableNames;
    }


}