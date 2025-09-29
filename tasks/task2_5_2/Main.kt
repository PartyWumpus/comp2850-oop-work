fun main() {
    val myAge = 29u
    println(myAge::class) // uint

    val universeAge = 13_800_000_000L
    println(universeAge::class) // long

    val status = 'M'
    println(status::class) // char

    val name = "Sarah"
    println(name::class) // string

    val height = 1.78f
    println(height::class) // float

    val root2 = Math.sqrt(2.0)
    println(root2::class) // double
}
