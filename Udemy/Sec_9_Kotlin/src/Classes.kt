fun main(args: Array<String>) {

    class Dog{
        var name = ""
        var age = 0
    }

    var myDog = Dog()
    myDog.name = "Milo"
    myDog.age = 3

    // Class + Initializer
    class Cat(var name : String, var age : Int){

    }
    var myCat = Cat("Milo", 4)

    // Class + init or constructor
    class Pet{
        var name : String
        var age : Int

        init{
            name = ""
            age = 0
        }

        constructor(name: String, age : Int){
            this.name = name
            this.age = age
        }

        constructor(){
            name = ""
            age = 0
        }

        fun petInfo(){
            println("$name is $age years old")
        }
    }

}