package com.goodboards.app.database

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.goodboards.db.DriverManagerWrapper
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.util.*

object UUIDHelper{
    fun randomUUID(): UUID = UUID.randomUUID()!!
}


object ConnectionHelper{
    fun getConnection():Connection{
        val databaseCredential = DBHelper.getDatabaseCredentials()
        return DriverManagerWrapper.getConnection(databaseCredential.url, databaseCredential.username, databaseCredential.password)!!
    }
}

// TODO move to use DBInterface
object DatabaseInit {

    val GAME_TABLE_NAME: String = "goodboards.games"

    fun getInsertGameStatement(id: String, jsonGame: JsonNode): String {
        return "INSERT INTO $GAME_TABLE_NAME(id, name, description) VALUES ('${id}', '${
            jsonGame.get("Name").textValue()
        }', '${jsonGame.get("Description").textValue()}');"
    }

    fun readGameJsonIntoDB(jsonFilePath: String): Boolean {

        // Read Json
        //val jsonString = File(jsonFilePath).readText(Charsets.UTF_8)
        val jsonString = this::class.java.classLoader.getResource(jsonFilePath).readText()

        // Parse the JSON data
        val objectMapper = ObjectMapper()
        val games: JsonNode = objectMapper.readTree(jsonString)


        // Connect to the Heroku database
        val conn = ConnectionHelper.getConnection()
        for (game in games) {
            val gameInsertStatement = getInsertGameStatement(UUIDHelper.randomUUID().toString(), game)
            conn.createStatement().use {
                val preparedStatement = conn.prepareStatement(gameInsertStatement)
                preparedStatement.executeUpdate()
                preparedStatement.close()
            }
        }

        return true;
    }
    data class DatabaseCredential(val url: String, val username: String, val password: String)
}
