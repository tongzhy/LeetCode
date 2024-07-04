package com.tongzy.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

public class S2477_Minimum_Fuel_Cost_to_Report_to_the_Capital {
    public long minimumFuelCost(int[][] roads, int seats) {
        graph = new List[roads.length + 1];

        for (int i = 0; i < graph.length; ++i)
            graph[i] = new ArrayList<>();

        for (int[] road : roads) {
            final int u = road[0];
            final int v = road[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(0, -1, seats);
        return ans;
    }

    private long ans = 0;
    List<Integer>[] graph;

    private int dfs(int u, int prev, int seats) {
        int people = 1;
        for (final int v : graph[u]) {
            if (v == prev)
                continue;
            people += dfs(v, u, seats);
        }
        if (u > 0)
            // # of cars needed = ceil(people / seats)
            ans += (people + seats - 1) / seats;
        return people;
    }

}
