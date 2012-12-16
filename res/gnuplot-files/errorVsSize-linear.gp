set xlabel 'Size of Training Data set'
set ylabel 'Error Percentage(%)'

set style line 1 lt "dotted" lw 1 lc rgb "red"
set style line 2 lt 3 lw 2 lc rgb "red"
set style line 3 lt 4 lw 1 lc rgb "green"
set style line 4 lt 3 lw 2 lc rgb "green"
set style line 5 lt 5 lw 1 lc rgb "blue"
set style line 6 lt 3 lw 2 lc rgb "blue"

plot '../weka/experiments/raw-results/sizeAccuracy/sizeVsAccuracy-linear10.txt'title 'Training dataset with 10% noise' with l ls 1, \
	'../weka/experiments/raw-results/sizeAccuracy/sizeVsAccuracyTest-linear10.txt'title 'Test dataset with 10% noise' with l ls 2, \
	'../weka/experiments/raw-results/sizeAccuracy/sizeVsAccuracy-linear20.txt'title 'Training dataset with 20% noise' with l ls 3, \
	'../weka/experiments/raw-results/sizeAccuracy/sizeVsAccuracyTest-linear20.txt'title 'Test dataset with 20% noise' with l ls 4, \
	'../weka/experiments/raw-results/sizeAccuracy/sizeVsAccuracy-linear30.txt'title 'Training dataset with 30% noise' with l ls 5, \
	'../weka/experiments/raw-results/sizeAccuracy/sizeVsAccuracyTest-linear30.txt'title 'Test dataset with 30% noise' with l ls 6
	