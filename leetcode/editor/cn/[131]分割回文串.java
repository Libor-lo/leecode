package editor.cn;//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
//
// 回文串 是正着读和反着读都一样的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：[["a"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 16 
// s 仅由小写英文字母组成 
// 
// Related Topics 深度优先搜索 动态规划 回溯算法 
// 👍 607 👎 0


import com.sun.tools.corba.se.idl.constExpr.ShiftRight;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    public static void main(String[] args) {
        Solution solution = new Solution();
        String example = "abbab";
        List<List<String>> res = solution.partition(example);
        for (int i = 0; i < res.size(); i++) {
            List<String> list = res.get(i);
            System.out.println(list.toString());
        }
    }


    public List<List<String>> partition(String s) {

        List<List<String>> res = new ArrayList<>();
        dfs(0,dynamicPlanning(s),s,new ArrayList<String>(),res);
        return res;

    }

    //用回溯来寻找每个决策（截取字符情况）是否符合要求
    private void dfs(int index, boolean[][] isPartition, String s, List<String> path, List<List<String>> res){

        List<String> temp = path;
        //回溯条件，待下面循环体的i==length即isPartition[？][length-1]为true，此时已有正确的切割结果，把此决策路径添加到res
        if (index == s.length()) {
            res.add(new ArrayList<>(temp));
            return;
        }

        //循环遍历后续字符串到切割情况，记录切割记录，如无法进入回溯条件会原路删除错误的切割记录
        for (int i = index; i < s.length(); i++) {
            if (isPartition[index][i]) {
                temp.add(s.substring(index,i+1));
                dfs(i+1,isPartition,s,temp,res);
                temp.remove(temp.size()-1);
            }

        }

    }


    //动态规划
    private boolean[][] dynamicPlanning(String s){

        int length = s.length();
        char[] chars = s.toCharArray();
        //boolean[left][right]为s的left到right是否为回文字符串
        boolean[][] isPartition = new boolean[length][length];
        //每个位置单独字母必是回文字符串
        for (int i = 0; i < length; i++) {
            isPartition[i][i] = true;
        }

        for (int right = 0; right < length; right++) {
            for (int left = 0; left < right; left++) {
                if(chars[left] == chars[right] && (right - left < 3 || isPartition[left + 1][right - 1])){
                    isPartition[left][right] = true;
                }
            }


        }
        return isPartition;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
