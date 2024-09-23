# **Tic Tac Toe**

<br>

### README

<h3 align="left">Languages :</h3>
<p align="left"> <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> </p>


<h3 align="left">How the project works :</h3>
<h4 align="justify">
The Tic tac toe project is a program which you can play
Tic tac toe against the computer. The game is played via the console.
By entering a number from one to nine, the player who first has three
of his symbols in a row wins the game.There are no special rules, the general
tic tac toe rules apply. The playing field is drawn using columns and rows.
This creates a 9x9 playing field. Each individual field is assigned its own index.
The program accepts the number entered by the player, checks whether the index is present
in the playing field and whether the playing field is already occupied by
another piece. If this is not the case, the program places the player's playing
figure on the desired field. After each turn of one of the two players,
the board is scanned to see if there is a row in which there are three identical
tiles in a row of three. If this is the case, the game is over and the player whose
tiles are in a row of three wins. Then you are asked if you want to play another round,
if the answer is positive you start a new round. Each round is counted in
order to have a score at the end. The computer always starts a game by setting
it to five unless it is occupied. If this is occupied, the computer starts
to follow the path with which you can win.During the course of the game,
the computer scans all possible ways in which the player could win.
If one of them is true, it prevents it by blocking the third game field that
is missing to win with a game color. If there is a possibility for the computer
to win. Does he take advantage of this by filling up the rows and so where
there were previously two of his pieces, there are now three.
</h4>
<br>

<h3 align="left">the reason for the project :</h3>
<h4 align="justify">
The programme can be used for tic tac toe games in a development environment or
in a cmd.However, it was mainly created for learning purposes.
</h4>
<br>

<h3 align="left">how to run :</h3>
<h4 align="justify">
The programme can be executed either in a development environment or in the console.There
are then two possibilities: either you navigate to the folder of the project
with cd and call the Main class in combination with Javac to build a Main.class.
This is then called again without .java, this time with the command Java. The other
possibility is to use maven.  </h4>

<h3 align="left">self notes</h3>
<h4 align="justify">
With the help of the pmd Ruls plugin in maven the code was reworked a bit. the codestyle
was improved as well as design and errorprone were removed. The appropriate rules were
added to the pmd-rules.xml file piece by piece.

The project includes several unit tests, most of which do not yet work and are of no
use. But more important are the JUnit 5 Parameterised which work to some extent and
are also useful to ensure that the code still works after changes.
</h4>


<p align="left">
</p>


