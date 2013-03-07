import random
from crypto.util.primeNumbers import PrimeNumberUtilities

__author__ = 'kornicameister'


class SchnorrAlgorithm(object):
    def generatePrimes(self, testAccuracy=10):
        qPrime = pow(2, 159)
        pPrime = 1

        while not PrimeNumberUtilities.execMillerRabin(qPrime, testAccuracy):
            qPrime += 1

        for i in range(1, 4097):
            randomM = random.randint(pow(2, 1023), pow(2, 1024))
            randomMr = randomM % (2 * qPrime)
            pPrime = randomM - randomMr + 1
            if PrimeNumberUtilities.execMillerRabin(pPrime, testAccuracy):
                break
            else:
                qPrime += 1
                while not PrimeNumberUtilities.execMillerRabin(qPrime, testAccuracy):
                    qPrime += 1

        return pPrime, qPrime