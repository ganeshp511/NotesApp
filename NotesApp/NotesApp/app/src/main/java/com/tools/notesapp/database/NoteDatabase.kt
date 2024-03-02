package com.tools.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tools.notesapp.model.Note

@Database(entities=[Note::class],version=1)
abstract class NoteDatabase: RoomDatabase() {
     abstract fun getNoteDao():NoteDao
     //singleton pattern. companions object is like static in java
     companion object{
         //volatile means it is visible to all threads immediately after a write operation
         @Volatile
         private var instance:NoteDatabase?=null
         //lock is used to make sure only one thread can access the database at a time
         private var LOCK=Any() //any() It is the superclass of all non-nullable types in Kotlin, equivalent to the `java. lang. Object` class in Java.

         //this code ensures that only one instance of the database is created, even in a multi-threaded environment, and returns that instance whenever the invoke function is called with a Context parameter.
         operator fun invoke(context: Context)=instance?: synchronized((LOCK)){
             instance?:createDatabase(context).also{instance=it}
         }
         /*
            1. **invoke()**: This allows you to call an object as if it were a function. When you define the `invoke` operator function in a class, you can invoke instances of that class directly, as if you were calling a function.

            2. **it**: This is a default name for a single parameter in a lambda expression if no explicit parameter is declared. It allows you to reference the single parameter without explicitly naming it.

            3. **synchronized**: This keyword is used in Kotlin to create a synchronized block. It ensures that only one thread can execute the synchronized block of code at a time, which is important for ensuring thread safety in multi-threaded environments.

            4. **LOCK**: This is likely a shared lock object used to synchronize access to critical sections of code. It's common practice to use a dedicated object as a lock to prevent concurrent access to shared resources.

            5. **also()**: This function is used to perform some additional action on an object and then return the object itself. It's often used for side effects that don't change the object's state or behavior.

            In the context of the provided code snippet, these constructs are used as follows:

            - `invoke()` is used to call the object of the class as if it were a function.
            - `it` is used inside the `also` function to reference the newly created instance.
            - `synchronized` ensures that only one thread can execute the block of code that creates the database instance.
            - `LOCK` is likely a shared lock object used for synchronization.
            - `also()` is used to assign the newly created database instance to the `instance` variable before returning it.
         */

         private fun createDatabase(context: Context)= Room.databaseBuilder(
             context.applicationContext,
             NoteDatabase::class.java,
             "note_db"
         ).build()
     }
}