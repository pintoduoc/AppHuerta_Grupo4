package com.example.apphuerta_grupo4.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.apphuerta_grupo4.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Producto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productoDao(): ProductoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Pre-populate the database
                        INSTANCE?.let {
                            CoroutineScope(Dispatchers.IO).launch {
                                it.productoDao().insertAll(PREPOPULATE_DATA)
                            }
                        }
                    }
                })
                .build()
                INSTANCE = instance
                instance
            }
        }

        private val PREPOPULATE_DATA = listOf(
            Producto(nombre = "Lechuga", descripcion = "Lechuga fresca y orgánica", precio = "$1.200 / unidad", imagen = R.drawable.lechuga),
            Producto(nombre = "Tomate", descripcion = "Tomates maduros del huerto", precio = "$1.800 / kilo", imagen = R.drawable.tomate),
            Producto(nombre = "Zanahoria", descripcion = "Zanahorias recién cosechadas", precio = "$1.000 / kilo", imagen = R.drawable.zanahoria)
        )
    }
}
