package et.com.commomHelper;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DataSource dataSource;

    public List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();

            // Получение списка всех таблиц в базе данных
            try (ResultSet resultSet = metaData.getTables(null, null, "%", new String[]{"TABLE"})) {
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