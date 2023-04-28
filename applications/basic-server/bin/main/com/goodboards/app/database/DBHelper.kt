package com.goodboards.app.database

import com.goodboards.db.DBConnection
import com.goodboards.db.DBInterface
import com.goodboards.db.SystemWrapper

object DBHelper {

        var dbInterface : DBInterface? = null

        private fun initDBInterface() : DBInterface{
            val dbCredentials = getDatabaseCredentials()
            val dbConnection = DBConnection
            dbConnection.setConnection(dbCredentials.url, dbCredentials.username, dbCredentials.password)
            return DBInterface(dbConnection)
        }

        fun getDBInterface() : DBInterface {
            if (dbInterface == null) {
                dbInterface = initDBInterface()
            }
            return dbInterface!!
        }

        fun getDatabaseCredentials() : DatabaseInit.DatabaseCredential {
            val DB_URL: String = SystemWrapper.getenv("DATABASE_URL")
            val DB_USENAME : String =  SystemWrapper.getenv("DATABASE_USERNAME")
            val DB_PASSWORD : String = SystemWrapper.getenv("DATABASE_PASSWORD")
            return DatabaseInit.DatabaseCredential(DB_URL, DB_USENAME, DB_PASSWORD)
        }
}