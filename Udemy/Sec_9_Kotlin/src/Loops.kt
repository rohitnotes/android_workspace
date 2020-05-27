fun main(args: Array<String>) {

//Loops
// Simple for loop
    for (x in 1..10) { // starts from 1 to 10 and assign that to x
        println(x)
    }

// for loop for list
    var favCandyList = listOf("Snickers", "kitkat", "Milkyway")
    for (candy in favCandyList) {
        println(candy)
    }

// Loop through the number 1 to 200 and print out all odd numbers
    for (x in 1..20) {
        if (x % 2 == 1) {
            println(x)
        }
    }
}