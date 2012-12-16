set xlabel 'Number of Iterations'
set ylabel 'Error Percentage(%)'

set style line 1 lt 3 lw 2 lc rgb "red"
set style line 2 lt 3 lw 2 lc rgb "green"
set style line 3 lt 3 lw 2 lc rgb "blue"
set style line 4 lt 3 lw 2 lc rgb "cyan"

plot '../weka/experiments/raw-results/stump/iteraccuracy33spherical.txt'title 'C45 Stump' with l ls 1, \
	'../weka/experiments/raw-results/smo/iteraccuracy33spherical.txt'title 'SVM with Poly Kernel' with l ls 2, \
	'../weka/experiments/raw-results/j48/iteraccuracy33spherical.txt'title 'C45 tree' with l ls 3, \
	'../weka/experiments/raw-results/mlp/iteraccuracy33spherical.txt'title 'Multi-layer Perceptron' with l ls 4
	