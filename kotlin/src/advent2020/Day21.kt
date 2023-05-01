package advent2020

import java.io.File

class Day21 {

    // Possible Allergic Ingredients
    private val pai = mutableMapOf<String, Set<String>>()
    private val allIngredients = mutableSetOf<String>()
    private val count = mutableMapOf<String, Int>()

    fun execute() {
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2020/Day21.input").forEachLine { line ->
            val x = line.split(" (contains ")
            val ingredients = x[0].split(" ").toSet()
            allIngredients.addAll(ingredients)
            ingredients.forEach { i ->
                count[i] = count[i]?.plus(1) ?: 1
            }

            val allergens = x[1].dropLast(1).split(", ").toSet()
            allergens.forEach { a ->
                pai[a] = pai[a]?.intersect(ingredients) ?: ingredients
            }
        }

        var safeIngredients = allIngredients.toSet()
        pai.forEach { (_, ins) ->
            safeIngredients = safeIngredients.minus(ins)
        }
        println(safeIngredients)

        val sum = safeIngredients.fold(0) { acc, si ->
            acc + (count[si] ?: 0)
        }
        println(sum)

        println(pai)
    }
}

fun main() {
    val d = Day21()
    d.execute()
}