# Basic test loop.
A percolation object is created, and cells are 
opened until the object percolates.

    // PercolationStats.java:24
    Percolation perc = new Percolation(n); // initialize percolation matrix

    while (!perc.percolates()) {           // open cells untill the matrix percolates
      int[] cell = cells.next();
      perc.open(cell[0], cell[1]);
    }

The while loop will be run `~ P` times, where `P` is the average
 percent of cells opened. Testing has shown `P` is `0.592`

    T(n) = Perc_new(n) +  (P * n) * (Perc_percolates + Perc_open)

## Perc_new

The percolation data structure uses a two dimentional array of cells,
and a Union datastructure. Each cell in the top and bottom row are joined
to the other cells in their row.

    Perc_new = Array_new + Union_new
               + (n * Union_union) + (n * Union_union)
             = Array_new + Union_new + (2n * Union_union)

    T = Array_new + Union_new + (2n * Union_union)


## Perc_percolates
Checks is the top and bottom row are connected.
Ignoring the  special case when the grid is 1 x 1 this is just a call 
to union.connected.

    Perc_percolates = Union_connected

    T = Union_connected

## Perc_open

Looks up a cell in the two dimentianal array, and sets it to open.
Joins that cell with any adjacent open cells. 2d coordinates need
to be mapped to the linear index of the Union.

    Perc_open = Perc_valid_coord + Array_lookup + Bool_assign
                + 4 * (2 * Perc_idx + Perc_is_open + Union_union)

    T = C + 4 * Union_union    // 4 at the most, usualy less than

## Perc_is_full
Checks if a cell is open, and connected to the top.

    Perc_is_full = Perc_is_open + Union_connected + Perc_idx

    T = C + Union_connected


# Constant Time

## Bool_assign
## Array_lookup
## Int_comp
## Int_add
## Int_mult

## Perc_is_open
Checks if a cell is open

    Perc_is_open = Perc_valid_coord + Array_lookup

## Perc_valid_coord
Checks that the passed coords are inside the grid.

    Perc_valid_coord = 4 * Int_comp

## Perc_idx
Maps a coordinate to a linear index

    Perc_idx = Perc_valid_coord + Int_add + Int_mult


# QuickUnionUF

## Union_new

Instantiating a new Union object with n^2 groups.
creates an array on n^2, sets each cell to it's index

    Union_new = Array_new + (n^2) * (Array_lookup + Int_assign)

    T = C + (n^2) + (B)

## Union_union
Joins two groups

    Union_union = 2 * Union_find + Int_comp + Array_lookup + Int_assign

    T = C + 2 * Union_find

## Union_find

## Union_connected
Checks if two indexes are in the same cells

    Union_union = 2 * Union_find + Int_comp

    T = C + 2 * Union_find

# Unknown

## Array_new

Allocating a N x N array of booleans.


