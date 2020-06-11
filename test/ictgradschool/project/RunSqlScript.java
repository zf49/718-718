package ictgradschool.project;

import ictgradschool.project.util.DBConnectionUtils;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

public class RunSqlScript {
	public static void main(String[] args) throws SQLException, IOException {
		String scriptPath = "scripts/db-init.sql";
		run(scriptPath);
	}

	private static void run(String scriptPath) throws IOException, SQLException {
		Connection con = DBConnectionUtils.getConnectionFromClasspath("database.properties");
		ScriptRunner scriptRunner = new ScriptRunner(con);
		Reader reader = new BufferedReader(new FileReader(scriptPath));
		scriptRunner.runScript(reader);
	}
}