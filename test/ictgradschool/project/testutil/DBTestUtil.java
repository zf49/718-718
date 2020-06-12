package ictgradschool.project.testutil;

import ictgradschool.project.util.DBConnectionUtils;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

public class DBTestUtil {
    public static void resetDBData() throws IOException, SQLException {
        runScript("scripts/db-init.sql");
        runScript("scripts/test-data-init.sql");
    }

    public static void runScript(String scriptPath) throws IOException, SQLException {
        Connection con = DBConnectionUtils.getConnectionFromClasspath("database.properties");
        ScriptRunner scriptRunner = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader(scriptPath));
        scriptRunner.runScript(reader);
    }
}
