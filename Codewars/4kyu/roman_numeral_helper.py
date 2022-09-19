import unittest

roman_to_numeral = {
    'I': 1,
    'V': 5,
    'X': 10,
    'L': 50,
    'C': 100,
    'D': 500,
    'M': 1000
}

numeral_to_roman = {
    1000: 'M',
    100: 'C',
    10: 'X',
    1: 'I'
}

numeral_to_roman5 = {
    500: 'D',
    50: 'L',
    5: 'V',
}


class RomanNumerals:
    @staticmethod
    def to_roman(n):
        roman = ''
        for i, s in numeral_to_roman.items():
            if n >= i:
                t = n // i
                if i == 1000:
                    roman += s * t
                elif t == 9:
                    roman += s
                    roman += numeral_to_roman[i * 10]
                elif t > 5:
                    roman += numeral_to_roman5[i * 5]
                    roman += s * (t - 5)
                elif t == 4:
                    roman += s
                    roman += numeral_to_roman5[i * 5]
                else:
                    roman += s * t
                n = n % i
        return roman

    @staticmethod
    def from_roman(roman):
        sum = 0
        for i, s in enumerate(roman):
            val = roman_to_numeral[s]
            if i == len(roman) - 1:
                sum += val
                continue

            next_char = roman[i + 1]
            next_val = roman_to_numeral[next_char]
            if next_val > val:
                sum -= val
            else:
                sum += val
        return sum


tc = unittest.TestCase()
tc.assertEqual(RomanNumerals.to_roman(1000), 'M', '1000 should == "M"')
tc.assertEqual(RomanNumerals.to_roman(1990), 'MCMXC', '1990 should == "MCMXC"')
tc.assertEqual(RomanNumerals.to_roman(21), 'XXI', '21 should == XXI')
tc.assertEqual(RomanNumerals.to_roman(2008), 'MMVIII', '2008 should == MMVIII')
tc.assertEqual(RomanNumerals.to_roman(1666), 'MDCLXVI', '2008 should == MDCLXVI')

tc.assertEqual(RomanNumerals.from_roman('XXI'), 21, 'XXI should == 21')
tc.assertEqual(RomanNumerals.from_roman('MMVIII'), 2008, 'MMVIII should == 2008')
tc.assertEqual(RomanNumerals.from_roman('M'), 1000, 'M should == 1000')
tc.assertEqual(RomanNumerals.from_roman('MCMXC'), 1990, 'MCMXC should == 1990')
tc.assertEqual(RomanNumerals.from_roman('MDCLXVI'), 1666, 'MDCLXVI should == 1666')
