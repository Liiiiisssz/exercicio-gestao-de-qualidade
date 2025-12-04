package org.example.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Prepare {

    public static PreparedStatement conn(String query)throws SQLException{
    Connection conn = Conexao.conectar();
    return conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    }
}
