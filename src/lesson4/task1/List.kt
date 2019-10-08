@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.digitNumber
import lesson3.task1.minDivisor
import kotlin.math.sqrt
import kotlin.math.pow
import kotlin.system.exitProcess


/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var sum = 0.0
    for (i in 0 until v.size) {
        sum += sqr(v[i])
    }
    return sqrt(sum)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isNotEmpty()) list.sum() / list.size else 0.0

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val average = mean(list)
    for (i in 0 until list.size) {
        list[i] = list[i] - average
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in 0 until a.size) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var poly = 0
    var factorX = 1
    for (i in 0 until p.size) {
        poly += p[i] * factorX
        factorX *= x
    }
    return poly
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) {
        list[i] = list[i] + list[i - 1]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var number = n
    while (number > 1) {
        list.add(minDivisor(number))
        number /= minDivisor(number)
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val list = mutableListOf<Int>()
    var number = n
    if (n == 0) {
        list.add(0, 0)
        return list
    }
    while (number > 0) {
        list.add(0, number % base)
        number /= base
    }
    return list
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val abc = "abcdefghijklmnopqrstuvwxyz"
    val list = convert(n, base).toMutableList()
    var convertation = " "
    for (i in 0 until list.size) {
        if (list[i] > 9) {
            convertation += abc[list[i] - 10]
        } else {
            convertation += list[i]
        }
    }
    return convertation
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var answer = 0
    for (i in 0 until digits.size) {
        answer += digits[i] * base.toDouble().pow(digits.size - i - 1).toInt()
    }
    return answer
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val digits = listOf(
        "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"
    )
    val decades = listOf(
        "десять", "двадцать", "тридцать", "сорок",
        "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"
    )
    val hundreds = listOf(
        "сто", "двести", "триста", "четыреста",
        "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"
    )
    val numbers11_19 = listOf(
        "одиннадцать", "двенадцать", "тринадцать", "четырнадцать",
        "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
    )
    val thousand = listOf(
        "одна тысяча", "две тысячи", "тысячи", "тысяч"
    )
    val answer = mutableListOf<String>()
    var numbers = digitNumber(n)
    var num = n
    var i = 0

    /* if (numbers == 5) {
        if (num / 1000 < 10) numbers--
        else {
            if (num / 1000 in 11..19) {
                answer.add(i, numbers11_19[num / 1000 - 11])
                answer.add(i + 1, thousand[3])
                i++
                numbers -= 2
                num %= 1000
            } else {
                if (num / 1000 % 10 == 0) {
                    answer.add(i, decades[num / 10000 - 1])
                    answer.add(i + 1, thousand[3])
                    i++
                    numbers -= 2
                    num %= 1000
                } else {
                    answer.add(i, decades[num / 1000 % 10 - 1])
                    numbers--
                    i++
                    num %= 1000
                }
            }
        }
       // num %= 10000
    }

    if (numbers == 4) { //если более четырех цифр в числе
        if (num / 1000 == 1) {
            answer.add(i, thousand[0])
        } else {
            if (num / 1000 == 2) {
                answer.add(i, thousand[1])
            } else {
                if (num / 1000 in 3..4) {
                    answer.add(i, digits[num / 1000 - 1])
                    answer.add(i + 1, thousand[2])
                    i++
                } else {
                    answer.add(i, digits[num / 1000 - 1])
                    answer.add(i + 1, thousand[3])
                    i++
                }
            }
        }

        if (num % 1000 != 0 && num % 1000 in 100..999) {
            numbers--
            i++
        }
        if (num % 1000 != 0 && num % 1000 in 10..99) {
            numbers -= 2
            i++
        }
        if (num % 1000 != 0 && num % 1000 in 1..9) {
            numbers -= 3
            i++
        }
        num %= 1000
    } */

    if (numbers > 3) {
        numbers = 3
        if (num % 1000 < 100) {numbers = 2}
        if (num % 1000 == 0) {numbers = 0}
        num %= 1000
    }

    if (numbers == 3) { //для трехзначного числа
        answer.add(i, hundreds[num / 100 - 1])
        if (num % 100 != 0) {
            numbers--
            i++
        }
    }

    if (numbers == 2) { //для двузначного числа
        num %= 100
        if (num < 10) numbers--
        else {
            if (num in 11..19) {
                answer.add(i, numbers11_19[num - 11])
            } else {
                if (num % 10 == 0) {
                    answer.add(i, decades[num / 10 - 1])
                } else {
                    answer.add(i, decades[num / 10 % 10 - 1])
                    numbers--
                    i++
                }
            }
        }
    }

    if (numbers == 1) answer.add(i, digits[num % 10 - 1]) //для числа из одной цифры


    if (digitNumber(n) > 3) {
        var numbersPart2 = digitNumber(n) - 3
        var numPart2 = n / 1000
        i = 0

        if (numbersPart2 == 3) { //для трехзначного числа
            answer.add(i, hundreds[numPart2 / 100 - 1])
            if (numPart2 % 100 != 0) {
                numbersPart2--
                i++
            } else {
                answer.add(i + 1, thousand[3])
            }
        }

        if (numbersPart2 == 2) { //для двузначного числа
            numPart2 %= 100
            if (numPart2 < 10) numbersPart2--
            else {
                if (numPart2 in 11..19) {
                    answer.add(i, numbers11_19[numPart2 - 11])
                    answer.add(i + 1, thousand[3])
                } else {
                    if (numPart2 % 10 == 0) {
                        answer.add(i, decades[numPart2 / 10 - 1])
                        answer.add(i + 1, thousand[3])
                    } else {
                        answer.add(i, decades[numPart2 / 10 % 10 - 1])
                        numbersPart2--
                        i++
                    }
                }
            }
        }

        if (numbersPart2 == 1) { //для числа из одной цифры
            numPart2 %= 10
            if (numPart2 == 1) {
                answer.add(i, thousand[0])
            } else {
                if (numPart2 == 2) {
                    answer.add(i, thousand[1])
                } else {
                    if (numPart2 in 3..4) {
                        answer.add(i, digits[numPart2 - 1])
                        answer.add(i + 1, thousand[2])
                        //i++
                    } else {
                        answer.add(i, digits[numPart2 - 1])
                        answer.add(i + 1, thousand[3])
                        //i++
                    }
                }
            }
        }

    }
    return answer.joinToString(separator = " ")
}