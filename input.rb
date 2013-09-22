#!/usr/bin/ruby

# Generates output for the percolation vizualizer program
# The first argument is the size of the grid and defaults to 20 when no argument is given

n = ARGV[0] || 20
n = n.to_i

puts n

cells = Array.new

for i in (0...n)
  for j in (0...n)
    cells << [i,j]
  end
end

cells.shuffle.each { |c| puts c.join(' ') }
