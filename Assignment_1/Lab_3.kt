
fun main() {
    // 3.a Create a function to print the last digit and first digit of the given Int value.
    // Get integer input from the console.
    println("3.a")
    printDigits(12345)
    println("-----------------------")

    // 3.b Write a function to find the sum of odd squared values in the given array of integers.
    val arr = intArrayOf(1, 2,3,4,5,6)
    println("3.b")
    println(sumOddSquared(arr))
    println("-----------------------")

    // 3.c Write a Program using when expression to find the weight of a person in various planets
    // according to the choice of user input from the console. Assume inputs in pounds.
    val inputWeight = (readlnOrNull() ?: "0").toInt()
    println("3.c")
    println(calculateWeight("Uranus", inputWeight))
}

fun printDigits(number: Int) {
    val str = number.toString()
    print(str.first())
    println(str.last())
}

fun sumOddSquared(list: IntArray): Int {
    var result = 0
    list.forEach { item ->
        if (item % 2 != 0) {
            result += item * item
        }
    }
    return result
}

fun calculateWeight(planet: String, inputWeight: Int) : Double {
    return when (planet) {
        "Venus" -> inputWeight * 0.78
        "Mars" -> inputWeight * 0.39
        "Jupiter" -> inputWeight * 2.65
        "Saturn" -> inputWeight * 1.17
        "Uranus" -> inputWeight * 1.05
        else -> inputWeight * 1.23
    }
}
