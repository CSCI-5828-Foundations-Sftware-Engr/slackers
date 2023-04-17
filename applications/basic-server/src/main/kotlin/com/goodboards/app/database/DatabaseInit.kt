package com.goodboards.app.database

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.sql.DriverManager
import java.util.*

object EnvHelper {
    fun getEnv(key: String): String = System.getenv(key)!!
}
object DatabaseInit {

    val GAME_TABLE_NAME: String = "Games"

    fun readGameJsonIntoDB(jsonFilePath: String): Boolean{

            // Read Json
            val jsonString = File(jsonFilePath).readText()
            val databaseCredential = getDatabaseCredentials()

            // Parse the JSON data
            val objectMapper = ObjectMapper()
            val games : JsonNode = objectMapper.readTree(jsonString)
            fun getInsertGameStatement(id: String, jsonGame: JsonNode): String {
                return "INSERT INTO $GAME_TABLE_NAME (id, name, description) VALUES ( , ${jsonGame.get("Name")}, ${jsonGame.get("Description")})"
            }

            // Connect to the Heroku database
            DriverManager.getConnection(databaseCredential.url, databaseCredential.username, databaseCredential.password).use { conn -> {
                    for (game in games) {
                        val gameInsertStatement = getInsertGameStatement(UUID.randomUUID().toString(), game)
                        conn.createStatement().use {
                            val preparedStatement = conn.prepareStatement(gameInsertStatement)
                            preparedStatement.executeUpdate()
                            preparedStatement.close()
                        }
                    }
                }

            }

            return true;
        }
        data class DatabaseCredential(val url: String, val username:String, val password: String)
        fun getDatabaseCredentials() : DatabaseCredential  {
            val DB_URL: String = EnvHelper.getEnv("DATABASE_URL")
            val DB_USENAME : String =  EnvHelper.getEnv("DATABASE_USERNAME")
            val DB_PASSWORD : String = EnvHelper.getEnv("DATABASE_PASSWORD")
            return DatabaseCredential(DB_URL, DB_USENAME, DB_PASSWORD)
        }
}
