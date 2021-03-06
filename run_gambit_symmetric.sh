
#!/bin/bash


no_actions=$1
iterations=$2
game_type=$3

# TODO flag -r for computation of $iteration games of the sizes [2 .. $no_actions]
# TODO flag -g to include computation of Nash equilibria via gambit


#while true; do

echo "New iteration of script..."

cd GAMUT

mkdir -p games/$game_type/$no_actions
mkdir -p games/$game_type/counters

cd gamut


timestamp=$(date +"%Y-%m-%d_%H-%M-%S")


echo $iterations

for i in $(seq $iterations); do
	# change this line if you want to generate other game classes
	java -cp build/classes/main edu.stanford.multiagent.gamer.Main -g $game_type -actions $no_actions -f ../games/$game_type/$no_actions/${game_type}_${no_actions}_${timestamp}_$i.game -output GambitOutput
	#java -jar gamut.jar -g RandomGame -actions 2 -players 4 -f ../games/RandomGame_${timestamp}_$i.game -output GambitOutput
done

cd ..
cd ..

# iterate over all game files in current directory
#for f in GAMUT/games/*/*.game; do
for f in GAMUT/games/$game_type/$no_actions/*.game; do

	# this block computes nash equilibria for the generated games. Uncomment if you want and/or need that.

	#if [ ! -f ${f%.game}.nash ]; then 
	#	echo "Running gambit on file $f"
    #	gambit-enumpoly $f -q | tee ${f%.game}.nash #$f.nash	# executing gambit with command line output of the NE
    #	#gambit-enumpoly $f -q > ${f%.game}.nash #$f.nash 		# executing gambit without command line output of the NE
	#fi


	if [ ! -f ${f%.game}.saddle ]; then
		echo "Running SaddlePy for strict saddles on file $f"
		python2 ../SaddlePy/main.py $f GAMUT/games/$game_type #"s" #$saddle_type # Change if you're using python 3
	fi
	
	#if [ "$saddle_type"=="s" ]; then
	#	if [ ! -f ${f%.game}.saddle ]; then
	#		echo "Running SaddlePy for strict saddles on file $f"
	#		python2 ../SaddlePy/main.py $f GAMUT/games/$game_type #"s" #$saddle_type # Change if you're using python 3
	#	fi
	#fi

	# TODO weak saddles

	#if [ "$saddle_type"=="v" ]; then
	#	if [ ! -f ${f%.game}.vsaddle ]; then
	#		echo "Running SaddlePy for very weak saddles on file $f"
	#		python ../SaddlePy/main.py $f $saddle_type
	#	fi
	#fi


	# TODO catch invalid saddle types

done

#no_actions=$(($no_actions-1))

#if [ $no_actions -gt 1 ]; then
#	sh run_gambit_symmetric.sh $no_actions $iterations $game_type
#fi


