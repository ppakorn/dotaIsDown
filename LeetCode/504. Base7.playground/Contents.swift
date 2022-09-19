func convertToBase7(_ num: Int) -> String {
    var str = ""
    let minus = num < 0
    var num = abs(num)
    
    while num / 7 > 0 {
        str += "\(num % 7)"
        num /= 7
    }
    str += "\(num)"
    
    if minus {
        str += "-"
    }
    
    return String(str.characters.reversed())
}

convertToBase7(100)
convertToBase7(-7)
convertToBase7(-898)
convertToBase7(-2345)
