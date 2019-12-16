@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
val listOfMonths = listOf(
    "января", "февраля", "марта", "апреля", "мая", "июня",
    "июля", "августа", "сентября", "октября", "ноября", "декабря"
)

fun dateStrDigitChecker(day: Int, month: Int, year: Int): Boolean = day <= daysInMonth(month, year)

fun dateStrToDigit(str: String): String {
    if (!str.matches(Regex("""[0-9]+\s[а-я]+\s[0-9]+"""))) return "" //checker
    val parts = str.split(" ")
    val day = parts[0].toIntOrNull()
    val year = parts[2].toIntOrNull()
    val month = listOfMonths.indexOf(parts[1]) + 1

    if (day == null || year == null || !dateStrDigitChecker(day, month, year) || parts[1] !in listOfMonths) return ""

    return String.format("%02d.%02d.%d", day, month, year)
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    if (!digital.matches(Regex("""[0-9]+\.[0-9]+\.[0-9]+"""))) return ""
    val parts = digital.split(".")
    val day = parts[0].toIntOrNull()
    val month = parts[1].toIntOrNull()
    val year = parts[2].toIntOrNull()

    if (day == null || month == null || year == null ||
        day < 1 || month !in 1..12 || year < 0 ||
        !dateStrDigitChecker(day, month, year) || parts.size != 3
    ) return ""

    return String.format("%d %s %d", day, listOfMonths[month - 1], year)
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    val answer = phone.filter { it !in setOf('(', ')', '-', ' ') }
    val setOfLegal = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '(', ')', '+', ' ')
    val checker1 = phone.toSet().union(setOfLegal) == setOfLegal //проверка на посторонние символы
    val checker2 = answer.contains(Regex("""[0123456789]""")) //проверка есть ли вообще цифры
    var checker3 = true
    var checker4 = true
    var checker5 = true
    var checker6 = true

    if ('+' in answer) {
        checker3 = answer.matches(Regex("""\+[0-9]*""")) //проверка на количество символов +
        checker5 = phone[0] == '+' // проверяет, если есть плюс, на первом ли он месте
        checker6 = answer.matches(Regex("""\+\d\d+""")) //проверка на наличие 2х или более цифр после плюса
    }

    if ('(' in phone) {
        val something = phone.filter { it !in listOf(' ', '-') }
        checker4 = something.contains(Regex("""\(\d+\)"""))  //если есть скобки, то проверка на наличие цифр в них
    }

    if (!checker1 || !checker2 || !checker3 || !checker4 || !checker5 || !checker6) return ""
    return answer
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    var answer = -1
    if (!jumps.matches(Regex("""(\d+|-|%)(\s(\d+|-|%))*"""))) return answer
    val jump = jumps.split(" ", "%", "-")
    for (part in jump) {
        if (part != "" && part.toInt() > answer) answer = part.toInt()
    }
    return answer
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    var answer = -1
    if (!jumps.matches(Regex("""\d+\s([+%\-])+(\s\d+\s([-+%])+)*"""))) return -1
    val parts = jumps.split(" ")
    for (i in parts.indices) {
        if (parts[i].contains("+") && parts[i - 1].toInt() > answer) {
            answer = parts[i - 1].toInt()
        }
    }
    return answer
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    require(expression.matches(Regex("""\d+(\s([+\-])\s\d+)*""")))
    val parts = expression.split(" ")
    var answer = parts[0].toInt()
    for (z in 1..parts.size - 2 step 2) {
        if (parts[z] == "+") answer += parts[z + 1].toInt()
        if (parts[z] == "-") answer -= parts[z + 1].toInt()
    }
    return answer
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    var answer = 0
    val words = str.split(" ")
    if (words.size == 1) return -1
    for (i in 0 until words.size - 1) {
        if (words[i].toLowerCase() == words[i + 1].toLowerCase()) return answer
        answer += words[i].length + 1
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    var answer = ""
    if (!description.matches(Regex("""[^ ]+\s[0-9]+(\.[0-9]+)?(;\s[^ ]+\s[0-9]+(\.[0-9]+)?)*"""))) return answer
    var maxPrice = 0.0
    val allPairs = mutableListOf<Pair<String, Double>>()
    val parts = description.split("; ")

    for (goodsAndPrice in parts) { //заполняю это все в пары (товар to цена)
        val something = goodsAndPrice.split(" ")
        allPairs.add(something[0] to something[1].toDouble())
    }

    for ((first, second) in allPairs) { //ищу самый дорогой элемент
        if (second >= maxPrice) {
            maxPrice = second
            answer = first
        }
    }
    return answer
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()


fun moneyCoin(sum: Double, coins: String): List<Pair<Int, Double>> {
    require(coins.matches(Regex("""\d+(\.\d+)?(,\s\d+(\.\d+)?)*""")))
    val parts = coins.split(", ")
    val listOfMoney = mutableListOf<Double>()
    for (part in parts) {
        listOfMoney.add(part.toDouble())
    }
    var newSum = sum
    var i = 0
    val listMyCoins = mutableListOf<String>()
    while (newSum >= 0.000001) {
        if (newSum >= listOfMoney[i]) {
            newSum -= listOfMoney[i]
            listMyCoins.add(listOfMoney[i].toString())
        } else i++
    }

    val mapOfNumberCoins = mutableMapOf<String, Int>()
    for (element in listMyCoins) {
        mapOfNumberCoins[element] = mapOfNumberCoins.getOrDefault(element, 0) + 1
    }

    val myPairs = mutableListOf<Pair<Int, Double>>()
    for ((key, value) in mapOfNumberCoins) {
        myPairs.add(value to key.toDouble())
    }
    return myPairs
}