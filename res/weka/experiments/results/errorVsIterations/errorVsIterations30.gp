set xlabel 'Number of Iterations'
set ylabel 'Error Percentage(%)'
set title "Number of Iterations vs Training Classification accuracy"
plot 'errorVsIterations-linear30.dat' title 'Linear dataset - 33% noise' with l, \
	'errorVsIterationsTest-linear30.dat' title 'Linear dataset - 33% noise - Test' with l linewidth 2, \
	'errorVsIterations-spherical30.dat' title 'Spherical dataset - 33% noise' with l, \
	'errorVsIterationsTest-spherical30.dat' title 'Spherical dataset - 33% noise - Test' with l linewidth 2, \
	'errorVsIterations-gaussian30.dat' title 'Gaussian dataset - 30% noise' with l, \
	'errorVsIterationsTest-gaussian30.dat' title 'Gaussian dataset - 30% noise - Test' with l linewidth 2
set out 'errorVsIterations.ps'
set terminal postscript landscape enhanced mono dashed lw 1 'Helvetica' 14
replot