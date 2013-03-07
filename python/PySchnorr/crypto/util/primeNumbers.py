from math import sqrt
import random

__author__ = 'kornicameister'


class PrimeNumberUtilities(object):
    @staticmethod
    def genPrimeNumbers(limit):
        pp = 2
        ep = [pp]
        pp += 1
        tp = [pp]
        ss = [2]
        while pp < int(limit):
            pp += ss[0]
            test = True
            sqrtResult = sqrt(pp)
            for a in tp:
                if a > sqrtResult:
                    break
                if pp % a == 0:
                    test = False
                    break
            if test:
                tp.append(pp)

        ep.reverse()
        [tp.insert(0, a) for a in ep]
        return tp

    @staticmethod
    def execMillerRabin(primeNumber, accuracy):
        """
        MillerRabin is simple algorithm for determining whether a number is prime.
        :param primeNumber: number to be tested against being primary
        :param accuracy: tests accuracy
        """

        class MillerRabin:
            def execute(self, primeNumber, accuracy):
                d, s = self.shift(primeNumber)
                for repeat in range(accuracy):
                    a = 0
                    while a == 0:
                        a = random.randrange(primeNumber)
                    if not self.passMR(a, s, d, primeNumber):
                        return False
                return True

            def shift(self, primeNumber):
                d = primeNumber - 1
                s = 0
                while d % 2 == 0:
                    d >>= 1
                    s += 1
                return d, s

            def passMR(self, a, s, d, n):
                a_to_power = pow(a, d, n)
                if a_to_power == 1:
                    return True
                for i in range(s - 1):
                    if a_to_power == n - 1:
                        return True
                    a_to_power = (a_to_power * a_to_power) % n
                return a_to_power == n - 1

        millerRabin = MillerRabin().execute(primeNumber, accuracy)
        print('MillerRabin test, primeNumber =', primeNumber, ' is prime =', millerRabin)
        return millerRabin
