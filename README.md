# Symmetry-Mario

This project corresponds to the computational model based on symmetry for generating visually pleasing maps of platform games, described in https://www.aaai.org/ocs/index.php/AIIDE/AIIDE16/paper/viewFile/14016/13596 .

Namely, in this work we introduced a computational model based on the concept of symmetry for generating visually pleasing
game maps. We cast the problem of generating symmetrical maps as an optimization task and propose a B&B approach
to solve it. We used two types of symmetry as objective funcion: One we called "The Vertical Symmetry Function" and other we called "The All Symmetries Function".

The generated small levels can be concatenated for generating comlete playable levels of the game Infinite Mario Bros (IMB), a clone of the Super Mario Bros game from Nintendo.

How to Run?

Step1: Generating the small levels: You should run the class GenerationForm.java, from the package dk.itu.mario.engine. After running it, four files are created: two files named "tela1" and "tela2" in the folder Screens/Telas, and two files named "info1" and "info2" in the folder Screens/infoTelas. The files tela1 and info1 are the information related to the small levels generated using the "The Vertical Symmetry Function" (See the Readme), and the files tela2 and info2 are the information related to the small levels generated using the "The All Symmetries Function".

Step2: Visualizing the created levels: You should run the class LoadScreenShotsForm.java from the package dk.itu.mario.engine. After running it, you will see displayed each of the screens that exist in the folder "Telas". Each screen will appear for some seconds, one after the other, and a screenshot of each one will be saved in the root.
