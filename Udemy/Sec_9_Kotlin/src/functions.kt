fun main(args: Array<String>) {

    //create fun
    fun hello(){
        println("Hello")

    }
    hello()
    hello()


    // Return
    fun helloWorld() : String{
        return "Hello World"
    }
    var ret = helloWorld()
    println(ret)

    // in and out
    fun helloPeople(name : String) : String{
        println("What is the name of city $name")
        return name;
    }
    var city = helloPeople("Detroit")
    println(city)

    // add
    fun addNumber(num1 : Int, num2 : Int) : Int{
        return num1 + num2
    }

    fun addNumbers(num1 : Int, num2 : Int) = num1 + num2


}