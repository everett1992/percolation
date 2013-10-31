# Percolation
Algorithms Project 1
Caleb Everett
TCNJ Fall 2013

This document is a markdown file, and can be found online at https://gist.github.com/everett1992/7257667

This project includes an Ant build file, I've included instructions on how to use ant to build and
run the different parts of this project. If you do not have, or do not want to use Ant you should be able to
compile using `javac`, just make sure the jars in `./lib` are included in the java classpath.

The main method for the vizualizer is in `src/PercolationVisualizer.java`, and the main method
for the statistics is in `src/PercolationStats.java`.

## System Requirements
Tested with:
- Java 1.7.0_45
- Ant 1.9.2
- Ruby 2.0.0 (optional)

## contents
build.xml:
  Describes how ant should build the project
desc.pdf:
  The project specs by mm
README.md:
  This document
vis_input.rb:
  Ruby script to generate visuzliser input
vis_input.txt:
  Pregenerated input for visualizer
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
Output of PercolationTiming.java, `ant timing`
This data is representd in `timing.odt` along side charts.

Doubling t from 16 to 4096 with n = 200

   t |  weighted | unweighted |
----:| ---------:| ----------:|
  16 |     0.047 |      0.062 |
  32 |     0.089 |      0.141 |
  64 |     0.181 |      0.275 |
 128 |     0.366 |      0.563 |
 256 |     0.740 |      1.138 |
 512 |     1.500 |      2.273 |
1024 |     3.164 |      4.456 |
2048 |     6.274 |      9.154 |
4096 |    12.613 |     18.229 |

The relationship between t and the time complexty is clearly linear, which makes sence,
the variavle `t` is only used in an outer `for` loop.

`T(n, t) = t * T(n)`

Doubling n from 16 to 4096 with t = 1

   n | weighted | unweighted |
----:| --------:| ----------:|
  16 |    0.002 |      0.002 |
  32 |    0.002 |      0.002 |
  64 |    0.009 |      0.004 |
 128 |    0.006 |      0.018 |
 256 |    0.019 |      0.015 |
 512 |    0.048 |      0.064 |
1024 |    0.406 |      0.502 |
2048 |    2.035 |      5.952 |
4096 |    7.375 |     46.447 |

// QuickUnionUF
`T(n, t) = t * (2 x 10 ^ -12) * (n ^ 3 * log(n))`

// WeightedQuickUnionUF
`T(n, t) = t * (5 x 10 ^ -7) * n ^ 2`
