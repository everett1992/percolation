# Percolation
Algorithms Project 1
Caleb Everett
TCNJ Fall 2013

Dependencies:
  - jdk
  - apache-ant (build)
  - ruby (visualizer script)

## Percolation visualizer:
```
  $ ant visualizer
```
or
```
  $ ./input.rb | ant visualizer
```
or
```
  $ ant visualizer < file.txt
```

## Percolation Stats:
```
  $ ant stats -Dn=250 -Dt=20
```

## Percolation Timing:
```
  $ ant timing
```
Used by me for timing analysis

## Analysis
Doubling N quadruples the number of cells (N x N) / (N*2 x N*2) = 4
So I would expect exponential growth when N doubles.
The data does not support this and I expect it is becuase small N's
are erratic and more likely to percolate with less than P percent of
spaces open.

    
    ---------- QuickUnionPathCompressionUF (path compression true) ----------
    QuickUnionPathCompressionUF: doubling N from 16 to 4096 with T = 200
    N:    16, time: 0.031000
    N:    32, time: 0.027000
    N:    64, time: 0.052000
    N:   128, time: 0.227000
    N:   256, time: 1.119000
    N:   512, time: 6.478000
    N:  1024, time: 35.279000
    N:  2048, time: 186.939000
    N:  4096, time: 963.848000
    --
    QuickUnionPathCompressionUF: doubling T from 16 to 4096 with N = 200
    T:    16, time: 0.042000
    T:    32, time: 0.082000
    T:    64, time: 0.177000
    T:   128, time: 0.351000
    T:   256, time: 0.696000
    T:   512, time: 1.388000
    T:  1024, time: 2.787000
    T:  2048, time: 5.550000
    T:  4096, time: 11.096000
    
    ---------- QuickUnionUF (path compression false) ----------
    QuickUnionUF: doubling N from 16 to 4096 with T = 200
    N:    16, time: 0.026000
    N:    32, time: 0.023000
    N:    64, time: 0.062000
    N:   128, time: 0.284000
    N:   256, time: 1.609000
    N:   512, time: 13.218000
    N:  1024, time: 108.440000
    N:  2048, time: 967.618000
    N:  4096, time: 9460.144000
    --
    QuickUnionUF: doubling T from 16 to 4096 with N = 200
    T:    16, time: 0.067000
    T:    32, time: 0.131000
    T:    64, time: 0.260000
    T:   128, time: 0.528000
    T:   256, time: 1.069000
    T:   512, time: 2.179000
    T:  1024, time: 4.291000
    T:  2048, time: 8.595000
    T:  4096, time: 17.207000
