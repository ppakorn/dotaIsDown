class Solution {
    var answers: [String] = []
    var n = 0
    
    func generateParenthesis(_ n: Int) -> [String] {
        self.n = n
        generateParamCharacter(current: "", left: 0, right: 0)
        return answers
    }
    
    private func generateParamCharacter(current: String, left: Int, right: Int) {
        if current.count == n*2 {
            answers.append(current)
            return
        }
        
        if left < n {
            generateParamCharacter(current: current + "(", left: left + 1, right: right)
        }
        if left > right {
            generateParamCharacter(current: current + ")", left: left, right: right + 1)
        }
    }
}

let s = Solution()
print(s.generateParenthesis(3))
