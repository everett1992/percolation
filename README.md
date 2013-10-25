# Percolation
Algorithms Project 1
Caleb Everett
TCNJ Fall 2013

This project includes an Ant build file, I've included instructions on how to use ant to build and
run the different parts of this project. If you do not have, or do not want to use Ant you should be able to
compile using `javac`, just make sure the jars in `./lib` are included in the java classpath.

The main method for the vizualizer is in `src/PercolationVisualizer.java`, and the main method
for the statistics is in `src/PercolationStats.java`.

## contents
build.xml:
  Describes how ant should build the project
desc.pdf:
  The project specs by mm
README.md:
  This document
timing.txt:
  scratch work for figuring the time complexities
timing.ods:
  libreoffice spreadsheet for graphing time complexities
src/:
  Source code written by me for this project
lib/:
  Jars of the books source code (these need to be in the java classpath to compile the project)
build/:
  Output directory of built classes and executables


## Usage
### Percolation visualizer:

To test the visualiser with at 25 x 25 grid run:

```
  $ ant visualizer < vis_input.txt
```

If you have Ruby installed you can use this command to generate arbitrary input
for the visualizer, replace <N> with any number.

```
  $ ./vis_input.rb <N> | ant visualizer
```

### Percolation Stats:
To view the statistsics run this, wher n is the size of the grid, and t is the number of test to run.
```
  $ ant stats -Dn=250 -Dt=20
```

### Percolation Timing: (used by me for analysis)
I used this script to time the percolation functions as `t` and `n` doubled.
This takes a while to run. A sample output is included below in the `Analysis` section.

```
  $ ant timing
```

## Analysis

Doubling N quadruples the number of cells (N x N) / (N*2 x N*2) = 4
So I would expect exponential growth when N doubles.
The data does not support this and I expect it is becuase small N's
are erratic and more likely to percolate with less than P percent of
spaces open.

Buildfile: /home/caleb/workspace/percolation/build.xml

compile:

timing:
     [java] 
     [java] ---------- WeightedQuickUnionUF (Weighted true) ----------
     [java] WeightedQuickUnionUF: doubling N from 16 to 4096 with T = 200
     [java] N:    16, time: 0.031000
     [java] N:    32, time: 0.027000
     [java] N:    64, time: 0.050000
     [java] N:   128, time: 0.215000
     [java] N:   256, time: 1.119000
     [java] N:   512, time: 7.284000
     [java] N:  1024, time: 34.414000
     [java] N:  2048, time: 198.708000
     [java] N:  4096, time: 1340.113000
     [java] --
     [java] WeightedQuickUnionUF: doubling T from 16 to 4096 with N = 200
     [java] T:    16, time: 0.047000
     [java] T:    32, time: 0.089000
     [java] T:    64, time: 0.181000
     [java] T:   128, time: 0.366000
     [java] T:   256, time: 0.740000
     [java] T:   512, time: 1.500000
     [java] T:  1024, time: 3.164000
     [java] T:  2048, time: 6.274000
     [java] T:  4096, time: 12.613000
     [java] 
     [java] ---------- QuickUnionUF (Weighted false) ----------
     [java] QuickUnionUF: doubling N from 16 to 4096 with T = 200
     [java] N:    16, time: 0.026000
     [java] N:    32, time: 0.032000
     [java] N:    64, time: 0.078000
     [java] N:   128, time: 0.296000
     [java] N:   256, time: 1.833000
     [java] N:   512, time: 14.853000
     [java] N:  1024, time: 115.355000
     [java] N:  2048, time: 1054.344000
     [java] N:  4096, time: 10157.954000
     [java] --
     [java] QuickUnionUF: doubling T from 16 to 4096 with N = 200
     [java] T:    16, time: 0.062000
     [java] T:    32, time: 0.141000
     [java] T:    64, time: 0.275000
     [java] T:   128, time: 0.563000
     [java] T:   256, time: 1.138000
     [java] T:   512, time: 2.273000
     [java] T:  1024, time: 4.456000
     [java] T:  2048, time: 9.154000
     [java] T:  4096, time: 18.229000

BUILD SUCCESSFUL
Total time: 216 minutes 34 seconds
