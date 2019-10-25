# Symmetry-Mario

This project corresponds to a computational model based on symmetry for generating visually pleasing maps of platform games, described in https://www.aaai.org/ocs/index.php/AIIDE/AIIDE16/paper/viewFile/14016/13596 .

Namely, in this work, we introduced a computational model based on the concept of symmetry for generating visually pleasing
game maps. The level generation problem is divided into two problems.
First, one chooses a set of objects. Then, our method has to define the objects’ x and y coordinates in a grid space of size L×k ·W (L rows and k ·W columns). We assume that the set of objects is provided as input, and our goal is to find visually pleasing solutions to the problem of choosing the coordinates of those objects.


We cast the problem of generating symmetrical maps as an optimization task and propose a branch-and-bound (B&B) approach to solve it. We used two types of symmetry as the objective function: One we named "The Vertical Symmetry Function" and another we named "The All Symmetries Function".

The generated small levels can be concatenated for generating complete playable levels of the game Infinite Mario Bros (IMB), a clone of the Super Mario Bros game from Nintendo.

How to Run?

Step1: Generating the small levels: You should run the class GenerationForm.java, from the package dk.itu.mario.engine. After running it, four files are created: two files named "tela1" and "tela2" in the folder Screens/Telas, and two files named "info1" and "info2" in the folder Screens/infoTelas. The files tela1 and info1 correspond to the information related to the small level generated using the "The Vertical Symmetry Function", and the files tela2 and info2 correspond to the information related to the small level generated using the "The All Symmetries Function". For choosing the number of objects desired to put into the screen just set this number in the variable numElementsGlobalParamet.

Step2: Visualizing the created levels: You should run the class LoadScreenShotsForm.java from the package dk.itu.mario.engine. After running it, you will see displayed each of the small levels that exist in the folder "Telas". Each level will appear for some seconds, one after the other, and a screenshot of each one will be saved in the root.
