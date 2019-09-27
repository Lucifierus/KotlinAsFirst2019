@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0   //обнуляю счетчик
    var number = n
    do {
        number /= 10
        count++
    } while (number !== 0)
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var numberOfFib1 = 1   //первое число в ряде равно 1
    var numberOfFib2 = 1   //второе число в ряде равно 2
    var sumFib1Fib2 = 0    // fib(n+2) = fib(n) + fib(n+1)
    var count = 0 //обнуляем счетчик
    while (count + 2 < n) {  //цикл while, пока (счетчик + 2) строго меньше заданного n. count + 2, так как учтены уже первые 2 числа ряда
        sumFib1Fib2 = numberOfFib1 + numberOfFib2     //fib(n+2) = fib(n) + fib(n+1)
        numberOfFib1 = numberOfFib2
        numberOfFib2 = sumFib1Fib2
        count++  //увеличиваем счетчик на +1
    }
    return numberOfFib2
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */


fun lcm(m: Int, n: Int): Int {
    var k = 0
    if (maxOf(m, n) % minOf(m, n) == 0) {
        k = maxOf(n, m)
    } else {
        k = max(n, m)
        do {
            k += max(n, m)
        } while (k % n !== k % m)
    }
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    if (isPrime(n)) return n
    var divisor = 2
    for (i in divisor..n) {
        if (n % divisor == 0) {
            break
        }
        divisor++
    }
    return divisor
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var answer = true
    for (i in 2..minOf(m, n)) {
        if (m % i == 0 && n % i == 0) {
            answer = false
            break
        }
    }
    return answer
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var k = 1
    var answer = false

    if (m < 1 && n >= 0) {
        return true
    }

    for (i in m..n) {
        if (sqr(k) in m..n) {
            answer = true
        } else {
            if (k * k > n) {
                answer = false
            } else {
                k++
            }
        }
    }
    return answer
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var count = 0
    var number = x

    do {
        if (number == 1) {
            break
        }
        count++
        if (number % 2 == 0) {
            number /= 2
        } else {
            number = 3 * number + 1
        }

    } while (number !== 1)
    return count
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */

fun ultimateSinCos(eps: Double, i: Int, x: Double, firstMember: Double): Double {
    val firstNumber = x % (2 * PI)                                                      //ОДИНАКОВОЕ
    var alternation = -1 //умножение на -1, через каждый член поледовательности         //ОДИНАКОВОЕ
    var newConsistent = 1.0                                                             //ОДИНАКОВОЕ
    var answer = firstMember
    var i = i

    while (abs(newConsistent) >= eps) {
        newConsistent = firstNumber.pow(i) / factorial(i) * alternation
        answer += newConsistent
        alternation *= -1
        i += 2
    }
    return answer
}

fun sin(x: Double, eps: Double): Double = ultimateSinCos(eps, 3, x, x % (2 * PI))
//i = 3, firstMember - первичное значение переменной answer (первый член последовательности).


/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double = ultimateSinCos(eps, 2, x, 1.0)
//i = 2, firstMember - первичное значение переменной answer (первый член последовательности).


/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var newNumber = 0
    var number = n
    while (number > 0) {
        newNumber = newNumber * 10 + number % 10
        number /= 10
    }
    return newNumber
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = revert(n) == n

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var number = n / 10
    var digit = n % 10
    while (number > 0) {
        if (number % 10 != digit) return true
        digit = number % 10
        number /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var answer = 1 //это вывести в ответ
    var amountDigits = 0 //количество цифр, которые были найдены (от одного до n)
    var numberzzz = 0 //переменная, которая будет очищаться и наполняться
    var i = 1

    while (amountDigits < n) {
        numberzzz = i * i
        amountDigits += digitNumber(numberzzz)
        i++
    }

    if (digitNumber(numberzzz) == 1) return numberzzz
    if (amountDigits == n) return numberzzz % 10
    while (n !== amountDigits) {
        answer = numberzzz / 10.toDouble().pow(amountDigits - n).toInt()
        amountDigits -= (amountDigits - n)
    }
    if (digitNumber(answer) > 1) { answer %= 10 }

    return answer
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int = TODO()
