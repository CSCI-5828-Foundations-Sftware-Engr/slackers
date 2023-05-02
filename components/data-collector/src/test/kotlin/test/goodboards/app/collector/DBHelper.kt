package test.goodboards.app.collector

import com.goodboards.db.DBConnection
import com.goodboards.db.DBInterface
import com.goodboards.db.SystemWrapper
import com.goodboards.db.DBCredentials

// TODO refactor DB Connection management
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

    fun getDatabaseCredentials() : DBCredentials {
        val DB_URL: String = SystemWrapper.getenv("JDBC_DATABASE_URL")
        val DB_USENAME : String =  SystemWrapper.getenv("DATABASE_USERNAME")
        val DB_PASSWORD : String = SystemWrapper.getenv("DATABASE_PASSWORD")
        return DBCredentials(DB_URL, DB_USENAME, DB_PASSWORD)
    }
}