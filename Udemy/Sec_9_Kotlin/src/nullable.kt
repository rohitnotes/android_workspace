fun main(args: Array<String>) {

    //nullable
    var age : Int? = 28
    age = 45
    age = null

    if(age !=null){
        age !!  // Change to non nullable
    }

    class Dog{

    }

    var myDog: Dog? = Dog()
    myDog = null


    // Example
    var dogs = mapOf("Fido" to 8)
    var dogage = println(dogs["Fido"])  // This returns null



}