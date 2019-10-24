package beauty;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import Auxiliar.InformacoesTelas;
import Auxiliar.VariaveisGlobais;
import dk.itu.mario.level.Level;

public class Screen {

	public Screen(){};
	
	public void SaveScreen(Level level, int [] odds, ElementsToPlace objElem){
		
		VariaveisGlobais.tag_platform = false;
		VariaveisGlobais.tag_straigth = false;
		VariaveisGlobais.tag_hill_straigth = false;
		VariaveisGlobais.tag_tubes = false;
		VariaveisGlobais.tag_jump = false;
		VariaveisGlobais.tag_cannos = false;
		
		//width = 89;
		//level = new Level(width, 15);
		//level = createLevelTela(seed, difficulty, type);
        salvaTela(level, "tela");
        salvaInfoTela(level,odds,objElem);
        //fixWalls();
        
	}
	
	private void salvaTela(Level tela, String nome){
    	//nome = nome+".tel";
    	FileOutputStream fos;
		try {
			fos = new FileOutputStream(nome);
			DataOutputStream dos = new DataOutputStream(fos);
			tela.save(dos);
			//System.out.println("\n\tFase salva com sucesso");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
private void salvaInfoTela(Level level, int [] odds, ElementsToPlace objElem){
    	
    	InformacoesTelas info = new InformacoesTelas();
    	
    	info.setTagPlatform(VariaveisGlobais.tag_platform);
    	info.setTagStraigth(VariaveisGlobais.tag_straigth);
    	info.setTagHillStraigth(VariaveisGlobais.tag_hill_straigth);
    	info.setTagTubes(VariaveisGlobais.tag_tubes);
    	info.setTagJump(VariaveisGlobais.tag_jump);
    	info.setTagCannos(VariaveisGlobais.tag_cannos);
    	
    	//To change
        info.setQuantBuracos(odds[objElem.getOddsJump()]);
        info.setQuantCanhoes(odds[objElem.getOddsCannons()]);
        info.setQuantMoedas(odds[objElem.getCoins()]);
        for(int i = 0; i < level.tam_spritePrimitivo; i++)
        	info.addInimigo(level.spritePrimitivo[i]);        
        
        try {
			info.salvaInfoTela("infoTela", info);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
