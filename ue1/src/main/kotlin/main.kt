import java.lang.Integer.parseInt
import java.lang.NumberFormatException

fun main(args: Array<String>) {
    println("Guess the number I'm thinking about")
    var trys = 1
    val secret = Array<Int>(4) { 0 };
    var userInput = Array<Int>(4) { 1 };

    secret[0] = getRandomNumber(arrayOf(-1).toIntArray());
    secret[1] = getRandomNumber(secret.toList().subList(0, 1).toIntArray());
    secret[2] = getRandomNumber(secret.toList().subList(0, 2).toIntArray());
    secret[3] = getRandomNumber(secret.toList().subList(0, 3).toIntArray());

    while (!userInput.contentEquals(secret)) {
        var numbersCorrect = 0;
        var numbersCorrectPosition = 0;
        userInput = readUserInput();

        for (i in 0..3) {
            if (userInput[i] == secret[i]) {
                numbersCorrectPosition++;
            }
            for (j in 0..3) {
                if (userInput[j] == secret[i]) {
                    numbersCorrect++;
                }
            }
        }

        println("$numbersCorrect:$numbersCorrectPosition")
        if (!userInput.contentEquals(secret)) {
            trys++
            println("Try again!")
        }
        Thread.sleep(1000);
        println();
    }
    println("Congratulations, you win after $trys trys!")
}

fun readUserInput(): Array<Int> {
    var tmp: String
    var keepReading = true
    val retVal = Array<Int>(4) { 0 }
    while (keepReading) {
        print("Insert your guess: ")
        tmp = readLine().toString();
        if (tmp.length != 4) {
            println("Please enter a 4 digit long number between 0000 and 9999")
            continue;
        }
        for (i in 3 downTo 0) {
            retVal[i] = parseToNumber(tmp[i])
        }
        if (!retVal.contains(-1)) {
            println("You entered something that is not a number.")
            keepReading = false;
        }
    }
    return retVal;
}

private fun parseToNumber(tmp: Char): Int {
    val num: Int
    try {
        num = parseInt(tmp.toString());
    } catch (e: NumberFormatException) {
        println("Your input '$tmp' is not a number!")
        return -1
    }
    return num
}

private fun getRandomNumber(i: IntArray): Int {
    var n: Int
    do {
        n = (0..9).random()
    } while (i.contains(n))
    return n;
}
