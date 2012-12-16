set xlabel 'Number of Iterations'
set ylabel 'Error Percentage(%)'
set title "Number of Iterations vs Training Classification accuracy"
plot 'errorVsIterations-linear20.dat' title 'Linear dataset - 20% noise' with l, \
	'errorVsIterationsTest-linear20.dat' title 'Linear dataset - 20% noise - Test' with l linewidth 2, \
	'errorVsIterations-spherical20.dat' title 'Spherical dataset - 20% noise' with l, \
	'errorVsIterationsTest-spherical20.dat' title 'Spherical dataset - 20% noise - Test' with l linewidth 2, \
	'errorVsIterations-gaussian20.dat' title 'Gaussian dataset - 20% noise' with l, \
	'errorVsIterationsTest-gaussian20.dat' title 'Gaussian dataset - 20% noise - Test' with l linewidth 2
set out 'errorVsIterations.ps'
set terminal postscript landscape enhanced mono dashed lw 1 'Helvetica' 14
replot