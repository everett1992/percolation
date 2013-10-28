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
T(t) | ~0.00308t |   0.00445t |

The relationship between t and the time complexty is clearly linear, which makes sence,
the variavle `t` is only used in an outer `for` loop.

`T(n, t) = t * t(n)`

Doubling n from 16 to 4096 with t = 200

   n | weighted | unweighted |
----:| --------:| ----------:|
  16 |    0.031 |      0.026 |
  32 |    0.027 |      0.032 |
  64 |    0.050 |      0.078 |
 128 |    0.215 |      0.296 |
 256 |    1.119 |      1.833 |
 512 |    7.284 |     14.853 |
1024 |   34.414 |    115.355 |
2048 |  198.708 |   1054.344 |
4096 | 1340.113 |  10157.954 |
T(n) |          |            |

weighted
  T(16) = 0.026
  T(32) = 1.23 * T(n/2)
  T(64) = 2.43 * T(n/2)
 T(128) = 3.79 * T(n/2)
 T(256) = 6.19 * T(n/2)
 T(512) = 8.10 * T(n/2)
T(1024) = 7.76 * T(n/2)
T(2048) = 9.13 * T(n/2)
T(4096) = 9.63 * T(n/2)

// Unweighted
1.82109x10^-7 x^3-0.000160553 x^2+0.083614 x-5.50897
// Weighted
1.63403x10^-8 x^3+0.0000119158 x^2+0.00430624 x-0.345488
