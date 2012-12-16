set xlabel 'Number of Iterations'
set ylabel 'Error Percentage(%)'
set title "Number of Iterations vs Training Classification accuracy"
plot 'errorVsIterations-linear10.dat' title 'Linear dataset - 10% noise' with l, \
	'errorVsIterationsTest-linear10.dat' title 'Linear dataset - 10% noise - Test' with l linewidth 2, \
	'errorVsIterations-spherical10.dat' title 'Spherical dataset - 10% noise' with l, \
	'errorVsIterationsTest-spherical10.dat' title 'Spherical dataset - 10% noise - Test' with l linewidth 2, \
	'errorVsIterations-gaussian10.dat' title 'Gaussian dataset - 10% noise' with l, \
	'errorVsIterationsTest-gaussian10.dat' title 'Gaussian dataset - 10% noise - Test' with l linewidth 2
set out 'errorVsIterations.ps'
set terminal postscript landscape enhanced mono dashed lw 1 'Helvetica' 14
replot