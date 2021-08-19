import java.sql.*;

public class Connect {
	private Statement statement;
	private Connection connection;

	static final String JDBC_DRIVER = "jdbc:sqlite:shop.db";

	public Connect() {
		try {
			this.connection = DriverManager.getConnection(JDBC_DRIVER);
			this.statement = connection.createStatement();
			System.out.println("Connection Successful");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connection Error");
		}
	}

	/**
	 * Getter method for property statement.
	 *
	 * @return property value of statement
	 */
	public Statement getStatement() {
		return statement;
	}

	/**
	 * Setter method for property statement.
	 *
	 * @param statement value to be assigned to property statement
	 */
	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	/**
	 * Getter method for property connection.
	 *
	 * @return property value of connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Setter method for property connection.
	 *
	 * @param connection value to be assigned to property connection
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void	executeUpdate(String query) {
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Connection Error");
			e.printStackTrace();
		}
	}

	public ResultSet executeQuery(String query) {
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Connection Error");
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public void closeConnection() {
		try {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
