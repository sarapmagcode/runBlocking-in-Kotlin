import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME
val time = { formatter.format(LocalDateTime.now()) }

/**
 * Whenever a function calls another suspend function,
 * then it should also be a suspend function
 */
suspend fun getValue(): Double {
    println("entering getValue() at ${time()}")
    delay(3000) // another suspend function
    println("leaving getValue() at ${time()}")
    return Math.random()
}

fun main() {
    // runBlocking() -> not a suspend function
    runBlocking {
        val num1 = async { getValue() }
        val num2 = async { getValue() }
        println("result of num1 + num2 is ${num1.await() + num2.await()}")
    }
}
